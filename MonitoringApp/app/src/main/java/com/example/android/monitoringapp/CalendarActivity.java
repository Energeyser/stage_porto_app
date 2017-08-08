package com.example.android.monitoringapp;
import android.content.Intent;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.android.monitoringapp.Data.Data;
import com.example.android.monitoringapp.Data.DataBDD;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import java.util.Date;
import java.util.ArrayList;

import static com.example.android.monitoringapp.MonthSummaryActivity.pbDetected;
import static com.prolificinteractive.materialcalendarview.CalendarDay.from;


public class CalendarActivity extends AppCompatActivity {

    MaterialCalendarView calendar;
    public static String DayDate = "";
    public static int DayWeekDate = 0;
    public static int MonthDate = 0;
    public static int YearDate = 0;

    DataBDD dataBDD = new DataBDD(this);
    Data data = new Data();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        calendar = (MaterialCalendarView) findViewById(R.id.calendar_date);


        //initailization of the calendar
        calendar.state().edit()
                .setFirstDayOfWeek(Calendar.MONDAY)
                .setMinimumDate(from(2017, 04, 03))
                .setMaximumDate(from(2035, 05, 12))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

        //draw a bleu cercle for the current date
        CalendarDay currentDay = new CalendarDay();
        int thisYear = currentDay.getYear();
        int thisMonth = currentDay.getMonth();
        int thisDay = currentDay.getDay();
        calendar.setSelectedDate(from(thisYear, thisMonth, thisDay));

        thisMonth =  thisMonth + 1;

        //put 0 if the number is less than 10 (ex: from 9 to 09)
        if(thisDay < 10 ){
            if(thisMonth < 10){
                DayDate = thisYear+"/"+"0"+thisMonth+"/"+"0"+thisDay;
            }
            DayDate = thisYear+"/"+thisMonth+"/"+"0"+thisDay;
        }
        else if(thisMonth < 10){
            DayDate = thisYear+"/"+"0"+thisMonth+"/"+thisDay;
        }


        Date[] datesAr = new Date[30];
       //look in the database if there is an alert
        dataBDD.open();
        data = dataBDD.getDataWithDate(DayDate);
        datesAr = dataBDD.getLastMonthAlarm();
        dataBDD.close();

        ArrayList<CalendarDay> dates = new ArrayList<CalendarDay>();
        CalendarDay CD = new CalendarDay();
        int i=0;
        for(i=0;i<datesAr.length;i++){
            CD = from(datesAr[i]);
            dates.add(CD);
            calendar.addDecorator(new EventDecorator(Color.RED, dates));
        }

        calendar.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                int dayOfMonth = date.getDay();
                int month = date.getMonth()+1;
                int year = date.getYear();

                //put 0 if the number is less than 10 (ex: from 9 to 09)
                if(dayOfMonth < 10 ){
                    if(month < 10){
                        DayDate = year+"/"+"0"+month+"/"+"0"+dayOfMonth;
                    }
                    DayDate = year+"/"+month+"/"+"0"+dayOfMonth;
                }
                else if(month < 10){
                    DayDate = year+"/"+"0"+month+"/"+dayOfMonth;
                }
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
