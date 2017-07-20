package com.example.android.monitoringapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.android.monitoringapp.Data.Patient;
import com.example.android.monitoringapp.Data.PatientBDD;
import com.example.android.monitoringapp.Data.MonitoringAppDbHelper;
import com.example.android.monitoringapp.Data.PatientContract.PatientEntry;

import static com.example.android.monitoringapp.MonthSummaryActivity.problemDetected;
import static com.example.android.monitoringapp.R.id.probleme_detected;

public class MonthSummaryActivity extends AppCompatActivity {

    static boolean pbDetected = false;
    static boolean pb = false;

    //New instance of the patientBDD class
    PatientBDD patientBDD = new PatientBDD(this);
    //Creation of a patient
    Patient patient = new Patient();

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
        namePatientInitials = (TextView) findViewById(R.id.name_patient_initials);
        namePatientInitials.setText(patient.getName().substring(0,1));

        if(pbDetected) {
            LinearLayout pbView = (LinearLayout) findViewById(R.id.probleme_detected);
            pbView.setVisibility(View.VISIBLE);
        }


    }


    public static boolean problemDetected(){
        if(pbDetected) {
            pb = true;
        }
        return pb;
    }

    public void openSeeMorePb(View view){
        if(pbDetected) {
            Intent i = new Intent(this, DayCalendarActivity.class);
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
