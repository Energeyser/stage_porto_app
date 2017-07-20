package com.example.android.monitoringapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.monitoringapp.Data.Doctor;
import com.example.android.monitoringapp.Data.DoctorBDD;
import com.example.android.monitoringapp.Data.Patient;
import com.example.android.monitoringapp.Data.PatientBDD;

import static com.example.android.monitoringapp.Data.DoctorContract.DoctorEntry.COLUMN_DOCTOR_PASSWORD;
import static com.example.android.monitoringapp.Data.DoctorContract.DoctorEntry.COLUMN_DOCTOR_USERNAME;

public class LandingActivity extends AppCompatActivity {

    EditText login;
    EditText password;

    /**
     * Database helper that will provide us access to the database
     */
    //New instance of the patientBDD class
    DoctorBDD doctorBDD = new DoctorBDD(this);
    //Creation of a patient
    Doctor doctor = new Doctor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        doctorBDD.open();
        doctor = doctorBDD.getDoctor();
        doctorBDD.close();
    }


    public void onClickValidation(View v) {

        //Get values enter in editText (login + password)
        String log = "";
        String pwd = "";

        login = (EditText) findViewById(R.id.login_doctor);
        password = (EditText) findViewById(R.id.password_doctor);
        log = login.getText().toString().trim();
        pwd = password.getText().toString().trim();

        String user = doctor.getUsername().toString().trim();
        String passwd = doctor.getPassword().toString().trim();

        if (log.equals(user)) {
            if(pwd.equals(passwd)){
                Intent i = new Intent(this, MonthSummaryActivity.class);
                startActivity(i);
                Toast.makeText(getBaseContext(), "Identification OK", Toast.LENGTH_SHORT).show();
            } else{
                Toast.makeText(getBaseContext(), "Problem with your password", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getBaseContext(), "Your identification is false, try again", Toast.LENGTH_LONG).show();
        }
    }
}