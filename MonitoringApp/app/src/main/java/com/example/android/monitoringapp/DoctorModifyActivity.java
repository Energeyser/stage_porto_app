package com.example.android.monitoringapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DoctorModifyActivity extends AppCompatActivity {

    EditText phoneDoctor;
    EditText emailDoctor;
    EditText usernameDoctor;
    EditText passwordDoctor;

    private Button Click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_modify);

        //Initialized every EditText
        phoneDoctor=(EditText)findViewById(R.id.phone_doctor);
        phoneDoctor.setText("+351 22 508 1400");

        emailDoctor=(EditText)findViewById(R.id.mail_doctor);
        emailDoctor.setText("carla.martins@gmail.com");

        usernameDoctor=(EditText)findViewById(R.id.username_doctor);
        usernameDoctor.setText("c_martins");

        passwordDoctor=(EditText)findViewById(R.id.password_doctor);
        passwordDoctor.setText("blablablablabla");

        Click = (Button)findViewById(R.id.button_validate_info_doctor);
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
        if ((v == Click) &&  (phoneDoctor.getText().toString().equals("") || emailDoctor.getText().toString().equals("") || usernameDoctor.getText().toString().equals("") || passwordDoctor.getText().toString().equals("")) ){
                //problem
        }
    }
}
