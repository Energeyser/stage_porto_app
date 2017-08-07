package com.example.android.monitoringapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.android.monitoringapp.Alert.Alert;
import com.example.android.monitoringapp.Data.DataBDD;
import com.example.android.monitoringapp.Data.Data;
import com.example.android.monitoringapp.Data.Patient;
import com.example.android.monitoringapp.Data.PatientBDD;

import java.lang.reflect.Array;

import static android.R.attr.data;
import static com.example.android.monitoringapp.R.id.value_min_heart_monitor;


public class MonthSummaryActivity extends AppCompatActivity {

    static int pbDetected = 0;
    static boolean pb = false;

    //New instance of the patientBDD class
    PatientBDD patientBDD = new PatientBDD(this);
    //Creation of a patient
    Patient patient = new Patient();

    DataBDD dataBDD = new DataBDD(this);
    Data dataMonth = new Data();
    Data dataTest = new Data();

    TextView namePatient;
    TextView namePatientInitials;

    //initialized TextView with data of the month
    TextView heartMean;
    TextView heartMin;
    TextView heartMax;

    TextView thoracicBloodMean;
    TextView bloodMin;
    TextView bloodMax;

    TextView waterMean;
    TextView waterMin;
    TextView waterMax;

    TextView salinityMean;
    TextView salinityMin;
    TextView salinityMax;

    TextView oxygeneMean;
    TextView oxygeneMin;
    TextView oxygeneMax;

    TextView pressurePulmonaryMean;
    TextView pressurePulmonaryMin;
    TextView pressurePulmonaryMax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month_summary);
        //boolean messageSent;
        //Alert alert = new Alert();

        //messageSent = alert.SendMessage("+33673579282","Test");

        patientBDD.open();
        patient = patientBDD.getPatient();
        patientBDD.close();

        namePatient = (TextView) findViewById(R.id.name_patient);
        namePatient.setText(patient.getName());

        //get first two letters of the name (initiales)
        String str = patient.getName();
        String [] tab = str.split(" ");
        String tmp ="";

        for(int i = 0; i< tab.length;i++) {
            tmp = tmp.concat(tab[i].substring(0,1));
        }

        namePatientInitials = (TextView) findViewById(R.id.name_patient_initials);
        namePatientInitials.setText(tmp);

        //look in the database if there is an alert
        dataBDD.open();
        dataMonth = dataBDD.getLastMonth();
        dataBDD.close();

        System.out.println(dataMonth.toString());

        pbDetected = dataMonth.getAlert();

        if(pbDetected != 0) {
            LinearLayout pbView = (LinearLayout) findViewById(R.id.probleme_detected);
            pbView.setVisibility(View.VISIBLE);
        }

        heartMin = (TextView) findViewById(R.id.value_min_heart_monitor);
        heartMax =(TextView) findViewById(R.id.value_max_heart_monitor);
        heartMean =(TextView) findViewById(R.id.value_mean_heart_monitor);

        heartMin.setText(Integer.toString(dataMonth.getMinimum_hr()));
        heartMax.setText(Integer.toString(dataMonth.getMaximum_hr()));
        heartMean.setText(Integer.toString(dataMonth.getAverage_hr()));

        thoracicBloodMean =(TextView) findViewById(R.id.value_mean_blood_pressure_systolic);
        thoracicBloodMean.setText(dataMonth.getBlood_pressure());

        waterMean =(TextView) findViewById(R.id.value_mean_intra_thoracic_fluid_content);
        waterMean.setText(Integer.toString(dataMonth.getThoracic_fluid_content()));

        salinityMean =(TextView) findViewById(R.id.value_mean_sodium_chloride);
        salinityMean.setText(Integer.toString(dataMonth.getSodium_chloride()));

    }


    public static boolean problemDetected(){
        if(pbDetected != 0) {
            pb = true;
        }
        return pb;
    }

    public void openSeeMorePb(View view){
        if(pbDetected != 0) {
            Intent i = new Intent(this, CalendarActivity.class);
            startActivity(i);
        }
    }

    public void openMonthSummary(View view){
        Intent i = new Intent(this, MonthSummaryActivity.class);
        startActivity(i);
    }

    public void openCalendar(View view){
        Intent i = new Intent(this, CalendarActivity.class);
        startActivity(i);
    }

    public void openExport(View view){
        Intent i = new Intent(this, ExportActivity.class);
        startActivity(i);
    }

    public void openDoctorInformation(View view){
        Intent i = new Intent(this, DoctorInformationActivity.class);
        startActivity(i);
    }

    public void openPatientInformation(View view){
        Intent i = new Intent(this, PatientInformationsActivity.class);
        startActivity(i);
    }

}
