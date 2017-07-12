package com.example.android.monitoringapp;
import android.content.Intent;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.ArrayList;

import static com.prolificinteractive.materialcalendarview.CalendarDay.from;


public class CalendarActivity extends AppCompatActivity {

    MaterialCalendarView calendar;
    public static String DayDate = "";
    public static int DayWeekDate = 0;
    public static int MonthDate = 0;
    public static int YearDate = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        calendar = (MaterialCalendarView) findViewById(R.id.calendar_date);


        //initailization of the calendar
        calendar.state().edit()
                .setFirstDayOfWeek(Calendar.MONDAY)
                .setMinimumDate(from(2016, 4, 3))
                .setMaximumDate(from(2035, 5, 12))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

        //draw a bleu cercle for the current date
        CalendarDay currentDay = new CalendarDay();
        int thisYear = currentDay.getYear();
        int thisMonth = currentDay.getMonth();
        int thisDay = currentDay.getDay();
        calendar.setSelectedDate(from(thisYear, thisMonth, thisDay));


        //if there is a problem
       boolean pbDetected = MonthSummaryActivity.problemDetected();
        if (pbDetected == true) {
            ArrayList<CalendarDay> dates = new ArrayList<CalendarDay>();
            CalendarDay CD = new CalendarDay();

            //what is the date where there was a problem
            int year = 2017;
            int monthOfYear = 06;
            int dayOfMonth = 14;

            CD = from(year, monthOfYear, dayOfMonth);
            dates.add(CD);
            calendar.addDecorator(new EventDecorator(Color.RED, dates));
        }

        calendar.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                int dayOfMonth = date.getDay();
                int month = date.getMonth() + 1;
                int year = date.getYear();
                DayDate = dayOfMonth+"/"+month+"/"+year;
                Toast.makeText(getBaseContext(), "Selected date "+DayDate , Toast.LENGTH_SHORT).show();
                openDayCalendar(widget);
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
