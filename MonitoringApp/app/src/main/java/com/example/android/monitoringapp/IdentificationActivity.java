package com.example.android.monitoringapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.monitoringapp.Data.Doctor;
import com.example.android.monitoringapp.Data.DoctorBDD;

import static android.R.attr.password;

public class IdentificationActivity extends AppCompatActivity {

    EditText login;
    EditText password;

    TextView nameDoctor;
    TextView nameDoctorInitials;

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
        setContentView(R.layout.activity_identification);

        doctorBDD.open();
        doctor = doctorBDD.getDoctor();
        doctorBDD.close();

        nameDoctor = (TextView) findViewById(R.id.name_doctor);
        nameDoctor.setText(doctor.getName());
        nameDoctorInitials = (TextView) findViewById(R.id.name_doctor_initials);
        nameDoctorInitials.setText(doctor.getName().substring(0,1));
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
                Intent i = new Intent(this, DoctorModifyActivity.class);
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
