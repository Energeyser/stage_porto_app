package com.example.android.monitoringapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import static com.example.android.monitoringapp.R.id.probleme_detected;

public class MonthSummaryActivity extends AppCompatActivity {

    boolean pbDetected = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month_summary);

        if(pbDetected) {
            LinearLayout pbView = (LinearLayout) findViewById(R.id.probleme_detected);
            pbView.setVisibility(View.VISIBLE);
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
