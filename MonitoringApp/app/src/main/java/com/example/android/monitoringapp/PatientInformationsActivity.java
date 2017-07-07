package com.example.android.monitoringapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PatientInformationsActivity extends AppCompatActivity {

    TextView phonePatient;
    TextView addressPatient;
    TextView phonePersonTrustPatient;
    TextView namePersonTrustPatient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_informations);

        phonePatient = (TextView) findViewById(R.id.phone_patient);
        phonePatient.setText("+351 22 508 1400");

        addressPatient = (TextView) findViewById(R.id. address_patient);
        addressPatient.setText("15 rua da, eqrfvqêfvq");

        namePersonTrustPatient = (TextView) findViewById(R.id. name_person_trust);
        namePersonTrustPatient.setText("Jane DOE");

        phonePersonTrustPatient = (TextView) findViewById(R.id. phone_person_trust);
        phonePersonTrustPatient.setText("+351 55 802 669");

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

    public void openPatientModify(View view){
        Intent i = new Intent(this, PatientModifyActivity.class);
        startActivity(i);
    }
}
