package com.example.android.monitoringapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class DoctorInformationActivity extends AppCompatActivity {

    TextView phoneDoctor;
    TextView emailDoctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_information);

        phoneDoctor = (TextView) findViewById(R.id.phone_doctor);
        phoneDoctor.setText("+351 22 508 1400");

        emailDoctor = (TextView) findViewById(R.id.mail_doctor);
        emailDoctor.setText("carla.martins@gmail.com");
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

    public void openDoctorModify(View view){
        Intent i = new Intent(this, DoctorModifyActivity.class);
        startActivity(i);
    }
}
