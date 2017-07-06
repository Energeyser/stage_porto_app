package com.example.android.monitoringapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;


public class CalendarActivity extends AppCompatActivity {

    CalendarView calendar;
    public static String DayDate = "";
    public static int DayWeekDate = 0;
    public static int MonthDate = 0;
    public static int YearDate = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        calendar = (CalendarView)findViewById(R.id.calendar_date);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth){
                if(month<12){
                    month = month + 1; //probleme qd on récupère la mois sinon
                }
                else{
                    month = 1;
                }
                Toast.makeText(getBaseContext(), "Selected date"+dayOfMonth+"/"+month+"/"+year , Toast.LENGTH_LONG).show();
                DayDate = dayOfMonth+"/"+month+"/"+year;
                openDayCalendar(view);
            }
        });

    }

    //passer une variable d'une classe a une autre via une méthode
    public static String returnDayDate(){
        return DayDate;
    };

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

    public void openDayCalendar(View view){
        Intent i = new Intent(this, DayCalendarActivity.class);
        startActivity(i);
    }
}
