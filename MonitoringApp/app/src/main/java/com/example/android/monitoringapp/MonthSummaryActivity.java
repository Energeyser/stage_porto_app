package com.example.android.monitoringapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.android.monitoringapp.Data.DataBDD;
import com.example.android.monitoringapp.Data.Data;
import com.example.android.monitoringapp.Data.Patient;
import com.example.android.monitoringapp.Data.PatientBDD;

import java.lang.reflect.Array;

import static android.R.attr.data;


public class MonthSummaryActivity extends AppCompatActivity {

    static int pbDetected = 0;
    static boolean pb = false;

    //New instance of the patientBDD class
    PatientBDD patientBDD = new PatientBDD(this);
    //Creation of a patient
    Patient patient = new Patient();

    DataBDD dataBDD = new DataBDD(this);
    Data dataMonth = new Data();

    TextView namePatient;
    TextView namePatientInitials;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month_summary);

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
