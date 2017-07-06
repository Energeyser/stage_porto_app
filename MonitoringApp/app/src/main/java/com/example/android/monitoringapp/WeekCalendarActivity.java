package com.example.android.monitoringapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.lang.String;

import static android.media.CamcorderProfile.get;

public class WeekCalendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_calendar);
         //initialized date of weeks
        initializedDate();

    }

    public void initializedDate(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        month = month + 1;
        int dayNumber = calendar.get(Calendar.DAY_OF_MONTH);

        //This week (this day less 6 days)
        int dayweek1 = dayNumber-7;

        int cpt_week = 0;
        int cpt_year = 0;

        if(dayweek1<1){
            month = month - 1;
            if(month>12){
                month = 1;
                year = year -1;
                cpt_year = 1;
            }
            if(month==1 || month==3 || month==5 || month==7 || month==8 || month==10 || month==12){
                dayweek1 = dayweek1 + 31;
            }
            if(month==4 || month==6 || month==9 || month==11 ){
                dayweek1 = dayweek1 + 30;
            }
            if(month==2){
                dayweek1 = dayweek1 + 28;
            }
            cpt_week = 1;
        }

        TextView titleDateWeek1 = (TextView) findViewById(R.id.date_week_summary_week1);
        if(cpt_week==1) {
            if(cpt_year==1) {
                titleDateWeek1.setText("This week (from " + (dayweek1) + "/" + month + "/" + year + " to " + dayNumber + "/" + (month-1) + "/" + (year-1) + ")");
                cpt_year=0;
            }
            else{
                titleDateWeek1.setText("This week (from " + (dayweek1) + "/" + month + "/" + year + " to " + dayNumber + "/" + (month-1) + "/" + year + ")");
            }
            cpt_week=0;
        }
        else{
            titleDateWeek1.setText("This week (from " + (dayweek1) + "/" + month + "/" + year + " to " + dayNumber + "/" + month + "/" + year + ")");
        }

        //week2
        int dayweek2 = dayweek1-7;

        if(dayweek2<1){
            month = month - 1;
            if(month>12){
                month = 1;
                year = year -1;
                cpt_year = 1;
            }
            if(month==1 || month==3 || month==5 || month==7 || month==8 || month==10 || month==12){
                dayweek2 = dayweek2 + 31;
            }
            if(month==4 || month==6 || month==9 || month==11 ){
                dayweek2 = dayweek2 + 30;
            }
            if(month==2){
                dayweek2 = dayweek2 + 28;
            }
            cpt_week = 1;
        }

        TextView titleDateWeek2 = (TextView) findViewById(R.id.date_week_summary_week2);
        if(cpt_week==1) {
            if(cpt_year==1) {
                titleDateWeek2.setText("This week (from " + (dayweek2) + "/" + month + "/" + year + " to " + dayweek1 + "/" + (month-1) + "/" + (year-1) + ")");
                cpt_year=0;
            }
            else{
                titleDateWeek2.setText("This week (from " + (dayweek2) + "/" + month + "/" + year + " to " + dayweek1 + "/" + (month-1) + "/" + year + ")");
            }
            cpt_week=0;
        }
        else{
            titleDateWeek2.setText("This week (from " + (dayweek2) + "/" + month + "/" + year + " to " + dayweek1 + "/" + month + "/" + year + ")");
        }

        //week3
        int dayweek3 = dayweek2-7;

        if(dayweek3<1){
            month = month - 1;
            if(month>12){
                month = 1;
                year = year -1;
                cpt_year = 1;
            }
            if(month==1 || month==3 || month==5 || month==7 || month==8 || month==10 || month==12){
                dayweek3 = dayweek3 + 31;
            }
            if(month==4 || month==6 || month==9 || month==11 ){
                dayweek3 = dayweek3 + 30;
            }
            if(month==2){
                dayweek3 = dayweek3 + 28;
            }
            cpt_week = 1;
        }

        TextView titleDateWeek3 = (TextView) findViewById(R.id.date_week_summary_week3);
        if(cpt_week==1) {
            if(cpt_year==1) {
                titleDateWeek3.setText("This week (from " + (dayweek3) + "/" + month + "/" + year + " to " + dayweek2 + "/" + (month-1) + "/" + (year-1) + ")");
                cpt_year=0;
            }
            else{
                titleDateWeek3.setText("This week (from " + (dayweek3) + "/" + month + "/" + year + " to " + dayweek2 + "/" + (month-1) + "/" + year + ")");
            }
            cpt_week=0;
        }
        else{
            titleDateWeek3.setText("This week (from " + (dayweek3) + "/" + month + "/" + year + " to " + dayweek2 + "/" + month + "/" + year + ")");
        }

        //week4
        int dayweek4 = dayweek3-7;

        if(dayweek4<1){
            month = month - 1;
            if(month>12){
                month = 1;
                year = year -1;
                cpt_year = 1;
            }
            if(month==1 || month==3 || month==5 || month==7 || month==8 || month==10 || month==12){
                dayweek4 = dayweek4 + 31;
            }
            if(month==4 || month==6 || month==9 || month==11 ){
                dayweek4 = dayweek4 + 30;
            }
            if(month==2){
                dayweek4 = dayweek4 + 28;
            }
            cpt_week = 1;
        }

        TextView titleDateWeek4 = (TextView) findViewById(R.id.date_week_summary_week4);
        if(cpt_week==1) {
            if(cpt_year==1) {
                titleDateWeek4.setText("This week (from " + (dayweek4) + "/" + month + "/" + year + " to " + dayweek3 + "/" + (month-1) + "/" + (year-1) + ")");
                cpt_year=0;
            }
            else{
                titleDateWeek4.setText("This week (from " + (dayweek4) + "/" + month + "/" + year + " to " + dayweek3 + "/" + (month-1) + "/" + year + ")");
            }
            cpt_week=0;
        }
        else{
            titleDateWeek4.setText("This week (from " + (dayweek4) + "/" + month + "/" + year + " to " + dayweek3 + "/" + month + "/" + year + ")");
        }

        //week5
        int dayweek5 = dayweek4-7;

        if(dayweek5<1){
            month = month - 1;
            if(month>12){
                month = 1;
                year = year - 1;
                cpt_year = 1;
            }
            if(month==1 || month==3 || month==5 || month==7 || month==8 || month==10 || month==12){
                dayweek5 = dayweek5 + 31;
            }
            if(month==4 || month==6 || month==9 || month==11 ){
                dayweek5 = dayweek5 + 30;
            }
            if(month==2){
                dayweek5 = dayweek5 + 28;
            }
            cpt_week = 1;
        }

        TextView titleDateWeek5 = (TextView) findViewById(R.id.date_week_summary_week5);
        if(cpt_week==1) {
            if(cpt_year==1) {
                titleDateWeek5.setText("This week (from " + (dayweek5) + "/" + month + "/" + year + " to " + dayweek4 + "/" + (month-1) + "/" + (year-1) + ")");
                cpt_year=0;
            }
            else{
                titleDateWeek5.setText("This week (from " + (dayweek5) + "/" + month + "/" + year + " to " + dayweek4 + "/" + (month-1) + "/" + year + ")");
            }
            cpt_week=0;
        }
        else{
            titleDateWeek5.setText("This week (from " + (dayweek5) + "/" + month + "/" + year + " to " + dayweek4 + "/" + month + "/" + year + ")");
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

    public void openWeekCalendar(View view){
        Intent i = new Intent(this, WeekCalendarActivity.class);
        startActivity(i);
    }

    public void openDayCalendar(View view){
        Intent i = new Intent(this, DayCalendarActivity.class);
        startActivity(i);
    }
}
