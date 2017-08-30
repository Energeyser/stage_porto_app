package com.example.android.monitoringapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

import com.example.android.monitoringapp.Analysis.RythmDetection;
import com.example.android.monitoringapp.Data.Data;
import com.example.android.monitoringapp.Data.DataBDD;
import com.example.android.monitoringapp.device.*;
import com.bitalino.comm.BITalinoFrame;
import android.content.Intent;

import com.example.android.monitoringapp.device.BITlog;
import com.example.android.monitoringapp.device.BitalinoThread;


import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class BluetoothDataActivity extends AppCompatActivity {

    static final String TAG = "Main Activity";
    public OutputStreamWriter fout = null;

    public BitalinoThread bitalinoThread;

    public Chronometer chronometer;

    public Button buttonStart;
    public Button buttonStop;

    public StoreLooperThread looperThread;

    public int[] ECG = new int[10000];

    public int BPM;

    //Log.v(TAG, "MobileBit Activity --OnCreate()--");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth_data);

        looperThread = new StoreLooperThread();
        looperThread.start();

        buttonStart = (Button) findViewById(R.id.button_start);
        buttonStart.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if(bitalinoThread==null){
                            configureBitalino();
                        }
                        looperThread.resetPackNum();
                        //createFile();

                        bitalinoThread.start();
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
                                BPM = RythmDetection.detect(ECG);

                            }else{
                                ECG[packNum-1] = myBitFrame.getAnalog(1);
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

    public void updateBPM(int BPM,int index){

        DataBDD dataBDD = new DataBDD(this);
        Data data = new Data();

        int maxBPM;
        int minBPM;
        int averageBPM;
        int newAverageBPM;

        String currentDate = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(new Date());

        dataBDD.open();
        data = dataBDD.getDataWithDate(currentDate);
        dataBDD.close();

        maxBPM = data.getMaximum_hr();
        minBPM = data.getMaximum_hr();
        averageBPM = data.getAverage_hr();

        if(BPM < minBPM){
            minBPM = BPM;
        }else if(BPM>maxBPM){
            maxBPM = BPM;
        }

      //TODO : average



    }
}

