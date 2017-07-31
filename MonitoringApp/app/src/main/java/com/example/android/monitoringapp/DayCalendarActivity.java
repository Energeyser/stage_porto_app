package com.example.android.monitoringapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.android.monitoringapp.Data.Data;
import com.example.android.monitoringapp.Data.DataBDD;

import static com.example.android.monitoringapp.CalendarActivity.DayDate;
import static com.example.android.monitoringapp.MonthSummaryActivity.pbDetected;
import static com.example.android.monitoringapp.R.id.title_day_calendar;

public class DayCalendarActivity extends AppCompatActivity {

    Data data = new Data();
    DataBDD dataBDD = new DataBDD(this);
    //initialized TextView with data of the month
    TextView heartMean;
    TextView heartMin;
    TextView heartMax;

    TextView bloodMean;
    TextView waterMean;
    TextView salinityMean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_calendar);

        boolean pbDet = MonthSummaryActivity.problemDetected();
        //if we arrived here because of a problem (see more button)
        if(pbDet){
            //display the date of the issue
            String dayDate = CalendarActivity.returnDayDate();
            TextView titleDayDate = (TextView) findViewById(R.id.title_day_calendar);
            titleDayDate.setText(dayDate);
        }
        else {
            String dayDate = CalendarActivity.returnDayDate();
            TextView titleDayDate = (TextView) findViewById(R.id.title_day_calendar);
            titleDayDate.setText(dayDate);
        }

        //look in the database if there is an alert
        String dayDate = CalendarActivity.returnDayDate();
        System.out.println(dayDate);
        dataBDD.open();
        data = dataBDD.getDataWithDate(dayDate);
        dataBDD.close();

        heartMin = (TextView) findViewById(R.id.value_min_heart_monitor);
        heartMax =(TextView) findViewById(R.id.value_max_heart_monitor);
        heartMean =(TextView) findViewById(R.id.value_mean_heart_monitor);

        heartMin.setText(Integer.toString(data.getMinimum_hr()));
        heartMax.setText(Integer.toString(data.getMaximum_hr()));
        heartMean.setText(Integer.toString(data.getAverage_hr()));

        bloodMean =(TextView) findViewById(R.id.value_mean_blood_pressure);
        bloodMean.setText(data.getBlood_pressure());

        waterMean =(TextView) findViewById(R.id.value_mean_qty_water_lungs);
        waterMean.setText(Integer.toString(data.getThoracic_fluid_content()));

        salinityMean =(TextView) findViewById(R.id.value_mean_salinity_skin);
        salinityMean.setText(Integer.toString(data.getSodium_chloride()));
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

    public void openWeekCalendar(View view){
        Intent i = new Intent(this, WeekCalendarActivity.class);
        startActivity(i);
    }

}
