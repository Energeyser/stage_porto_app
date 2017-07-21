package com.example.android.monitoringapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import com.example.android.monitoringapp.Data.Patient;
import com.example.android.monitoringapp.Data.PatientBDD;

public class PatientInformationsActivity extends AppCompatActivity {

    TextView namePatient;
    TextView phonePatient;
    TextView addressPatient;
    TextView phonePersonTrustPatient;
    TextView namePersonTrustPatient;
    TextView namePatientInitials;


    //New instance of the patientBDD class
    PatientBDD patientBDD = new PatientBDD(this);
    //Creation of a patient
    Patient patient = new Patient();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_patient_informations);

        patientBDD.open();
        patient = patientBDD.getPatient();
        patientBDD.close();

        namePatient = (TextView) findViewById(R.id.name_patient);
        namePatient.setText(patient.getName());
        phonePatient = (TextView) findViewById(R.id.phone_patient);
        phonePatient.setText(patient.getPhone());
        addressPatient = (TextView) findViewById(R.id.address_patient);
        addressPatient.setText(patient.getAddress());
        namePersonTrustPatient = (TextView) findViewById(R.id.name_person_trust);
        namePersonTrustPatient.setText(patient.getPot_name());
        phonePersonTrustPatient = (TextView) findViewById(R.id.phone_person_trust);
        phonePersonTrustPatient.setText(patient.getPot_phone());
        //get first two letters of the name (initiales)
        String str = patient.getName();
        String [] tab = str.split(" ");
        String tmp ="";

        for(int i = 0; i< tab.length;i++) {
            tmp = tmp.concat(tab[i].substring(0,1));
        }
        namePatientInitials = (TextView) findViewById(R.id.name_patient_initials);
        namePatientInitials.setText(tmp);


    }

    public void openMonthSummary(View view) {
        Intent i = new Intent(this, MonthSummaryActivity.class);
        startActivity(i);
    }

    public void openCalendar(View view) {
        Intent i = new Intent(this, CalendarActivity.class);
        startActivity(i);
    }

    public void openExport(View view) {
        Intent i = new Intent(this, ExportActivity.class);
        startActivity(i);
    }

    public void openDoctorInformation(View view) {
        Intent i = new Intent(this, DoctorInformationActivity.class);
        startActivity(i);
    }

    public void openPatientModify(View view) {
        Intent i = new Intent(this, PatientModifyActivity.class);
        startActivity(i);
    }

}
