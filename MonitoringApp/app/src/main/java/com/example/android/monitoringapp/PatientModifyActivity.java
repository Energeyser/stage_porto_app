package com.example.android.monitoringapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PatientModifyActivity extends AppCompatActivity {

    EditText phonePatient;
    EditText emailPatient;
    EditText namePersonTrust;
    EditText phonePersonTrust;

    private Button Click;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_modify);

        //Initialized every EditText
        phonePatient=(EditText)findViewById(R.id.phone_patient);
        phonePatient.setText("+351 22 508 1400");

        emailPatient=(EditText)findViewById(R.id.address_patient);
        emailPatient.setText("carla.martins@gmail.com");

        namePersonTrust=(EditText)findViewById(R.id.name_person_trust);
        namePersonTrust.setText("c_martins");

        phonePersonTrust=(EditText)findViewById(R.id.phone_person_trust);
        phonePersonTrust.setText("blablablablabla");

        Click = (Button)findViewById(R.id.button_validate_info_patient);
        //Click.setOnClickListener(this);
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

    //verify if EditTexts are not empty
    public void onClick(View v) {
        if ((v == Click) &&  (phonePatient.getText().toString().equals("") || emailPatient.getText().toString().equals("") || namePersonTrust.getText().toString().equals("") || phonePersonTrust.getText().toString().equals("")) ){
            //problem
        }
    }
}
