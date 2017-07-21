package com.example.android.monitoringapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.android.monitoringapp.Data.Doctor;
import com.example.android.monitoringapp.Data.DoctorBDD;


public class DoctorInformationActivity extends AppCompatActivity {

    TextView nameDoctor;
    TextView phoneDoctor;
    TextView emailDoctor;
    TextView nameDoctorInitials;

    //New instance of the doctorBDD class
    DoctorBDD doctorBDD = new DoctorBDD(this);
    //Creation of a patient
    Doctor doctor = new Doctor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_doctor_information);

        doctorBDD.open();
        doctor = doctorBDD.getDoctor();
        doctorBDD.close();
        System.out.println(doctorBDD.toString());

        nameDoctor = (TextView) findViewById(R.id.name_doctor);
        nameDoctor.setText(doctor.getName());
        phoneDoctor = (TextView) findViewById(R.id.phone_doctor);
        phoneDoctor.setText(doctor.getPhone());
        emailDoctor = (TextView) findViewById(R.id.mail_doctor);
        emailDoctor.setText(doctor.getMail());
        //get first two letters of the name (initiales)
        String str = doctor.getName();
        String [] tab = str.split(" ");
        String tmp ="";

        for(int i = 0; i< tab.length;i++) {
            tmp = tmp.concat(tab[i].substring(0,1));
        }
        nameDoctorInitials = (TextView) findViewById(R.id.name_doctor_initials);
        nameDoctorInitials.setText(tmp);
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

    public void openIdentificationActivity(View view){
        Intent i = new Intent(this, IdentificationActivity.class);
        startActivity(i);
    }

}
