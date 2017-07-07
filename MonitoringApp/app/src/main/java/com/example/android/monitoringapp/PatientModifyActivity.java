package com.example.android.monitoringapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.android.monitoringapp.R.id.address_patient;

public class PatientModifyActivity extends AppCompatActivity {

    EditText phonePatient;
    EditText addressPatient;
    EditText namePersonTrust;
    EditText phonePersonTrust;

    private Button Click;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_modify);

        //Initialized every EditText
       // if (phonePatient.getText().toString().equals("") && addressPatient.getText().toString().equals("") && namePersonTrust.getText().toString().equals("") && phonePersonTrust.getText().toString().equals("")) {
            phonePatient = (EditText) findViewById(R.id.phone_patient);
            phonePatient.setText("+351 22 508 1400");

            addressPatient = (EditText) findViewById(address_patient);
            addressPatient.setText("17 rue aervaevaef");

            namePersonTrust = (EditText) findViewById(R.id.name_person_trust);
            namePersonTrust.setText("c_martins");

            phonePersonTrust = (EditText) findViewById(R.id.phone_person_trust);
            phonePersonTrust.setText("blablablablabla");
      //  }
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

    public boolean isPhoneValid(CharSequence target) {
        if (target == null)
            return false;
        return android.util.Patterns.PHONE.matcher(target).matches();
    }

    //verify if EditTexts are not empty
    public void verifyChanges(View v) {
        String tmp_modification="";
        boolean phonePatientValid = false;
        boolean phonePersonTrustValid = false;

        //phone ok ?
        tmp_modification = phonePatient.getText().toString();
        phonePatientValid = isPhoneValid(tmp_modification);
        tmp_modification = phonePersonTrust.getText().toString();
        phonePersonTrustValid = isPhoneValid(tmp_modification);


        if ((v == Click) &&  (phonePatient.getText().toString().equals("") || addressPatient.getText().toString().equals("") || namePersonTrust.getText().toString().equals("") || phonePersonTrust.getText().toString().equals("")) ){
            Toast.makeText(getBaseContext(), "One of the field is empty, please fill it to validate changes" , Toast.LENGTH_LONG).show();
        }
        else if (phonePatientValid && phonePersonTrustValid){
            tmp_modification = phonePatient.getText().toString();
            phonePatient.setText(tmp_modification);
            tmp_modification = addressPatient.getText().toString();
            addressPatient.setText(tmp_modification);
            tmp_modification = phonePersonTrust.getText().toString();
            phonePersonTrust.setText(tmp_modification);
            tmp_modification = namePersonTrust.getText().toString();
            namePersonTrust.setText(tmp_modification);
            Toast.makeText(getBaseContext(), "Validation of the changes have been made." , Toast.LENGTH_SHORT).show();
            openMonthSummary(v);
        }
        else{
            Toast.makeText(getBaseContext(), "Phone number is wrong, please correst the field" , Toast.LENGTH_SHORT).show();
        }
    }
}
