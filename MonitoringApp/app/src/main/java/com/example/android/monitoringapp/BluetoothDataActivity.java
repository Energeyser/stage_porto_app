package com.example.android.monitoringapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

import com.example.android.monitoringapp.Analysis.EDA;
import com.example.android.monitoringapp.Analysis.Results;
import com.example.android.monitoringapp.Analysis.RythmDetection;
import com.example.android.monitoringapp.Data.Data;
import com.example.android.monitoringapp.Data.DataBDD;
import com.example.android.monitoringapp.Data.Patient;
import com.example.android.monitoringapp.Data.PatientBDD;
import com.bitalino.comm.BITalinoFrame;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.monitoringapp.device.BITlog;
import com.example.android.monitoringapp.device.BitalinoThread;


import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import static android.R.attr.data;

public class BluetoothDataActivity extends AppCompatActivity {

    static final String TAG = "Main Activity";
    public OutputStreamWriter fout = null;

    public BitalinoThread bitalinoThread;

    public Chronometer chronometer;


    public Button buttonStart;
    public Button buttonStop;

    public StoreLooperThread looperThread;

    public int[] ECG = new int[10000];
    public int[] channel3Values = new int[10000];
    public int[] PPG = new int[10000];

    //public double sodium;
    private double impedance;

    //public int BPM;
    public int numOfSample = 1;

    public Results results = new Results();

    TextView text;



    //Log.v(TAG, "MobileBit Activity --OnCreate()--");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth_data);

        text = (TextView) findViewById(R.id.results);

        looperThread = new StoreLooperThread();
        looperThread.start();

        buttonStart = (Button) findViewById(R.id.button_start);
        buttonStart.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(),"Connecting...",Toast.LENGTH_LONG).show();
                        if(bitalinoThread==null){
                            configureBitalino();
                        }
                        looperThread.resetPackNum();
                        //createFile();

                        try {
                            Toast.makeText(getApplicationContext(),"Connected to Bitalino",Toast.LENGTH_LONG).show();
                            bitalinoThread.start();
                        }catch(Exception e){
                            Toast.makeText(getApplicationContext(),"Error :" + e,Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        buttonStop = (Button) findViewById(R.id.button_stop);
        buttonStop.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if(bitalinoThread!=null){
                            bitalinoThread.finalizeThread();
                            //chronometer.stop();
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            //closeFile();
                            bitalinoThread = null;
                            Toast.makeText(getApplicationContext(),"Connexion stopped",Toast.LENGTH_LONG).show();
                        }

                    }
                });
    }

    private void configureBitalino(){
        try {

            bitalinoThread = new BitalinoThread(looperThread.mHandler, BITlog.MAC  );
            // Configure the devices

            bitalinoThread.setChannels(BITlog.channels);

            bitalinoThread.setSampleRate(BITlog.SamplingRate);

            //Calculate frame number:
            BITlog.frameRate = 1;
            if(BITlog.SamplingRate>1){
                BITlog.frameRate = 3 * BITlog.SamplingRate/10;
            }

            bitalinoThread.setNumFrames(BITlog.frameRate);
            bitalinoThread.setDownsamplingOn(false);
//			bitalinoThread.setMode(BITalinoDevice.LIVE_MODE);

        } catch (Exception e) {
            Log.v(TAG, "Error creating the Bitalino Thread");
            e.printStackTrace();
        }
    }



    public final int MSG_CHRONO = 1;
    public final int MSG_BITALINO = 2;
    public Handler myHandler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message inputMessage) {

            switch (inputMessage.what) {
                // The decoding is done
                case MSG_CHRONO:
                    //chronometer.setBase(SystemClock.elapsedRealtime());
                    //chronometer.start();
                    break;
                case MSG_BITALINO:

                    int pkg =  inputMessage.arg1;
                    Log.v(TAG, "Number of packages received: "+pkg);


                    break;
                default:
                    super.handleMessage(inputMessage);

            }//Switch
        }//handle
    };

    public class StoreLooperThread extends Thread {
        public Handler mHandler;
        private int packNum = 0;


        public void resetPackNum(){
            packNum = 0;
        }


        public void run() {

            Looper.prepare();

            mHandler = new Handler() {

                public void handleMessage(Message msg_in) {
                    // process incoming messages here

                    Bundle myBundle = msg_in.getData();

                    if(myBundle.containsKey("bitalino_data")){
                        Message msg_out = new Message();

                        if(packNum==0){
                            myHandler.sendMessage(myHandler.obtainMessage(MSG_CHRONO));
                        }

                        BITalinoFrame[] frames = new BITalinoFrame[BITlog.frameRate];

                        frames = (BITalinoFrame[]) myBundle.getSerializable("bitalino_data");


                        ArrayList<Integer> channelList = new ArrayList<Integer>();
                        for(int i = 0; i<6; i++){
                            boolean find = false;
                            for(int j=0; j<BITlog.channels.length; j++){
                                if(i == BITlog.channels[j]){
                                    find = true;
                                    channelList.add(i, Integer.valueOf(i));
                                }
                            }
                            if(!find) channelList.add(i, null);
                        }

                        for(BITalinoFrame myBitFrame : frames){

                            packNum++;

                            if(packNum == 10000){
                                resetPackNum();
                                results = RythmDetection.detect(ECG,PPG);
                                impedance = EDA.detect(channel3Values);
                                updateData(results,impedance);

                            }else{
                                ECG[packNum-1] = myBitFrame.getAnalog(1);
                                channel3Values[packNum-1] = myBitFrame.getAnalog(2);
                                PPG[packNum-1] = myBitFrame.getAnalog(5);
                             }

                            if(fout!=null){
                                String line = Integer.valueOf(myBitFrame.getSequence()).toString();
                                if(BITlog.digitalOutputs){
                                    line+=  "\t"+myBitFrame.getDigital(0)+
                                            "\t"+ myBitFrame.getDigital(1)+
                                            "\t"+ myBitFrame.getDigital(2)+
                                            "\t"+ myBitFrame.getDigital(3);

                                }
                                for(int i=0; i<6; i++){
                                    if(channelList.get(i)!=null){
                                        line+="\t"+myBitFrame.getAnalog(i);
                                    }

                                }
                                line+="\n";
                                ;
                                try {
                                    fout.write(line);

                                    //  Log.v(TAG, "Write: \n\t"+line);
                                } catch (IOException e) {
                                    Log.v(TAG, "Error writing to the file "+line);
                                    //e.printStackTrace();
                                }
                            }

                        }

                        msg_out.what = MSG_BITALINO;
                        msg_out.arg1 = packNum;
                        myHandler.sendMessage(msg_out);

                    }else if(myBundle.containsKey("OK")){
                        String str = myBundle.getString("OK");
                        Log.v(TAG, str);
                    }else if(myBundle.containsKey("OFF")){
                        String str = myBundle.getString("OFF");
                        Log.v(TAG, str);
                    }else{
                        Log.i(TAG, " message :  "+ myBundle.keySet()+ " + "+ myBundle.get("what"));
                    }

                }

            };

            Looper.loop();
        }
    }

    public void openLanding(View view){
        Intent i = new Intent(this, LandingActivity.class);
        startActivity(i);
    }

    public void updateData(final Results results, final double impedance){

        DataBDD dataBDD = new DataBDD(this);
        PatientBDD patientBDD = new PatientBDD(this);
        Patient patient;
        Data data; // The data that will be used to update the database

         int maxBPM;
         int minBPM;
         int averageBPM;
        double maxSodium;
        double minSodium;
        double averageSodium;
         int arrythmia = 0;
         int minDBP = 0;
        int maxDBP = 0;
        int averageDBP = 0;
         int minSBP = 0;
        int maxSBP = 0;
        int averageSBP = 0;



        //we check the current date
        String currentDate = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(new Date());

        //we check if an object data exists in the database for the current date
        dataBDD.open();
        data = dataBDD.getDataWithDate(currentDate);


        if (data.getDate() == null){ //if not, we create one with the basic informations we have
            this.numOfSample = 1;
            patientBDD.open();
            patient = patientBDD.getPatient();
            patientBDD.close();
            data.setPatient_name(patient.getName());
            data.setPatient_process_number(patient.getProcessNumber());
            data.setDate(currentDate);
            dataBDD.insertData(data);
            data = dataBDD.getDataWithDate(currentDate);
        }
        System.out.println("Actual data   : " + data.toString());
        dataBDD.close();

        //we compare the new data to the data stored in the database and update them if necessary
        maxBPM = data.getMaximum_hr();
        minBPM = data.getMinimum_hr();
        averageBPM = data.getAverage_hr();
        maxSodium = data.getMaximum_sodium_chloride();
        minSodium = data.getMinimum_sodium_chloride();
        averageSodium = data.getAverage_sodium_chloride();
        minDBP = data.getMinimum_diastolic_blood_pressure();
        maxDBP = data.getMaximum_diastolic_blood_pressure();
        averageDBP = data.getAverage_diastolic_blood_pressure();
        minSBP = data.getMinimum_systolic_blood_pressure();
        maxSBP = data.getMaximum_systolic_blood_pressure();
        averageSBP = data.getAverage_systolic_blood_pressure();
        //arrythmia = data.getAlert();


        /* --- BPM ---*/

        if (this.numOfSample == 1) {
            data.setMinimum_hr(results.cf);
            data.setMaximum_hr(results.cf);
            data.setAverage_hr(results.cf);
        }else if(results.cf > 5) {
            if (results.cf < minBPM) {
                data.setMinimum_hr(results.cf);
            } else if (results.cf > maxBPM) {
                data.setMaximum_hr(results.cf);
            }
            //we calculate the new average based on the old average, the new value and the total number of values
            data.setAverage_hr(averageBPM + (results.cf - averageBPM) / this.numOfSample);
        }

        /* --- Sodium ---*/
        /*if (this.numOfSample == 1){
            data.setMinimum_sodium_chloride(sodium);
            data.setMaximum_sodium_chloride(sodium);
        } else if(sodium < minSodium && sodium > 0){
            data.setMinimum_sodium_chloride(sodium);
        }else if(sodium>maxSodium){
            data.setMaximum_sodium_chloride(sodium);
        }
        data.setAverage_sodium_chloride(averageSodium + (sodium - averageSodium)/this.numOfSample);
        */

        /* --- Diastolic Blood Pressure ---*/
        if (this.numOfSample == 1){
            data.setMinimum_diastolic_blood_pressure(results.DBP);
            data.setMaximum_diastolic_blood_pressure(results.DBP);
            data.setAverage_diastolic_blood_pressure(results.DBP);
        }
        else if(results.DBP > 5) {
            if (results.DBP < minDBP && results.DBP > 0) {
                data.setMinimum_diastolic_blood_pressure(results.DBP);
            } else if (results.DBP > maxDBP) {
                data.setMaximum_diastolic_blood_pressure(results.DBP);
            }
            data.setAverage_diastolic_blood_pressure(averageDBP + (results.DBP - averageDBP) / this.numOfSample);
        }

        /* --- Systolic Blood Pressure ---*/
        if (this.numOfSample == 1){
            data.setMinimum_systolic_blood_pressure(results.SBP);
            data.setMaximum_systolic_blood_pressure(results.SBP);
            data.setAverage_systolic_blood_pressure(results.SBP);
        }
        else if(results.SBP > 5) {
            if (results.SBP < minSBP && results.SBP > 0) {
                data.setMinimum_systolic_blood_pressure(results.SBP);
            } else if (results.SBP > maxSBP) {
                data.setMaximum_systolic_blood_pressure(results.SBP);
            }
            data.setAverage_systolic_blood_pressure(averageSBP + (results.SBP - averageSBP) / this.numOfSample);
        }

        data.setAlert(results.arrythmia);

        if(results.cf > 5) {
            System.out.println("Updated data : " + data.toString());
            dataBDD.open();
            dataBDD.updateData(data.getId(), data);
            dataBDD.close();

            data.toString();

            this.numOfSample++;
        }else{
            System.out.println("Cf is null");
        }


        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                text.setText("BPM : "+results.cf + ", SBP/DBP : "+ results.SBP +"/"+results.DBP+", arrythmia : "+results.arrythmia+"impedance = "+impedance );

            }
        });

    }
}

