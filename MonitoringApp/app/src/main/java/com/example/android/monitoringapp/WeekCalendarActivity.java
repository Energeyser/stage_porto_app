package com.example.android.monitoringapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.android.monitoringapp.Data.Data;
import com.example.android.monitoringapp.Data.DataBDD;

import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.lang.String;

import static android.media.CamcorderProfile.get;

public class WeekCalendarActivity extends AppCompatActivity {

    DataBDD dataBDD = new DataBDD(this);
    Data dataWeek = new Data();

    //initialized TextView with data of the month
    TextView heartMean;
    TextView heartMin;
    TextView heartMax;

    TextView systolicBloodMean;
    TextView diastolicBloodMean;
    TextView waterMean;
    TextView salinityMean;

    String dateStart = "";
    String dateEnd = "";

    int cptWeek = 0;

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
        if (cpt_week == 1) {
            if (cpt_year == 1) {
                dateStart = putZero(dayweek1,month,year);
                dateEnd = putZero(dayNumber,month+1,year+1);
                titleDateWeek1.setText("This week (from " + (dayweek1) + "/" + month + "/" + year + " to " + dayNumber + "/" + (month + 1) + "/" + (year + 1) + ")");
                cpt_year = 0;
            } else {
                dateStart = putZero(dayweek1,month,year);
                dateEnd = putZero(dayNumber,month+1,year);
                titleDateWeek1.setText("This week (from " + (dayweek1) + "/" + month + "/" + year + " to " + dayNumber + "/" + (month + 1) + "/" + year + ")");
            }
            cpt_week = 0;
        } else {
            dateStart = putZero(dayweek1,month,year);
            dateEnd = putZero(dayNumber,month,year);
            titleDateWeek1.setText("This week (from " + (dayweek1) + "/" + month + "/" + year + " to " + dayNumber + "/" + month + "/" + year + ")");
        }
        fillWeekFields(dateStart, dateEnd);

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
        if (cpt_week == 1) {
            if (cpt_year == 1) {
                dateStart = putZero(dayweek2,month,year);
                dateEnd = putZero(dayweek1,month+1,year+1);
                titleDateWeek2.setText("Week from " + (dayweek2) + "/" + month + "/" + year + " to " + dayweek1 + "/" + (month + 1) + "/" + (year + 1));
                cpt_year = 0;
            } else {
                dateStart = putZero(dayweek2,month,year);
                dateEnd = putZero(dayweek1,month+1,year);
                titleDateWeek2.setText("Week from " + (dayweek2) + "/" + month + "/" + year + " to " + dayweek1 + "/" + (month + 1) + "/" + year);
            }
            cpt_week = 0;
        } else {
            dateStart = putZero(dayweek2,month,year);
            dateEnd = putZero(dayweek1,month,year);
            titleDateWeek2.setText("Week from " + (dayweek2) + "/" + month + "/" + year + " to " + dayweek1 + "/" + month + "/" + year);
        }
        fillWeekFields(dateStart, dateEnd);

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
        if (cpt_week == 1) {
            if (cpt_year == 1) {
                dateStart = putZero(dayweek3,month,year);
                dateEnd = putZero(dayweek2,month+1,year+1);
                titleDateWeek3.setText("Week from " + (dayweek3) + "/" + month + "/" + year + " to " + dayweek2 + "/" + (month + 1) + "/" + (year + 1));
                cpt_year = 0;
            } else {
                dateStart = putZero(dayweek3,month,year);
                dateEnd = putZero(dayweek2,month+1,year);
                titleDateWeek3.setText("Week from " + (dayweek3) + "/" + month + "/" + year + " to " + dayweek2 + "/" + (month + 1) + "/" + year);
            }
            cpt_week = 0;
        } else {
            dateStart = putZero(dayweek3,month,year);
            dateEnd = putZero(dayweek2,month,year);
            titleDateWeek3.setText("Week from " + (dayweek3) + "/" + month + "/" + year + " to " + dayweek2 + "/" + month + "/" + year);
        }
        fillWeekFields(dateStart, dateEnd);

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
        if (cpt_week == 1) {
            if (cpt_year == 1) {
                dateStart = putZero(dayweek4,month,year);
                dateEnd = putZero(dayweek3,month+1,year+1);
                titleDateWeek4.setText("Week from " + (dayweek4) + "/" + month + "/" + year + " to " + dayweek3 + "/" + (month + 1) + "/" + (year + 1));
                cpt_year = 0;
            } else {
                dateStart = putZero(dayweek3,month,year);
                dateEnd = putZero(dayweek2,month+1,year);
                titleDateWeek4.setText("TWeek from " + (dayweek4) + "/" + month + "/" + year + " to " + dayweek3 + "/" + (month + 1) + "/" + year);
            }
            cpt_week = 0;
        } else {
            dateStart = putZero(dayweek3,month,year);
            dateEnd = putZero(dayweek2,month,year);
            titleDateWeek4.setText("Week from " + (dayweek4) + "/" + month + "/" + year + " to " + dayweek3 + "/" + month + "/" + year);
        }
        fillWeekFields(dateStart, dateEnd);

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
        if (cpt_week == 1) {
            if (cpt_year == 1) {
                dateStart = putZero(dayweek5,month,year);
                dateEnd = putZero(dayweek4,month+1,year+1);
                titleDateWeek5.setText("Week from " + (dayweek5) + "/" + month + "/" + year + " to " + dayweek4 + "/" + (month + 1) + "/" + (year + 1));
                cpt_year = 0;
            } else {
                dateStart = putZero(dayweek5,month,year);
                dateEnd = putZero(dayweek4,month+1,year);
                titleDateWeek5.setText("Week from " + (dayweek5) + "/" + month + "/" + year + " to " + dayweek4 + "/" + (month + 1) + "/" + year);
            }
            cpt_week = 0;
        } else {
            dateStart = putZero(dayweek5,month,year);
            dateEnd = putZero(dayweek4,month,year);
            titleDateWeek5.setText("Week from " + (dayweek5) + "/" + month + "/" + year + " to " + dayweek4 + "/" + month + "/" + year);
        }
        fillWeekFields(dateStart, dateEnd);
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

    public String putZero(int dayOfMonth, int month, int year){
        String DayDate ="";
        //put 0if the number is less than 10(ex:from 9to 09)
        if(dayOfMonth< 10) {
            if (month < 10) {
                DayDate = year + "/" + "0" + month + "/" + "0" + dayOfMonth;
            }
            DayDate = year + "/" + month + "/" + "0" + dayOfMonth;
        }
        else if(month< 10) {
            DayDate = year + "/" + "0" + month + "/" + dayOfMonth;
        }

        return DayDate;
    }

    public void fillWeekFields(String startDate, String endDate){

        dataBDD.open();
        dataWeek = dataBDD.getDataForWeek(startDate, endDate);
        dataBDD.close();
        System.out.println(dataWeek.toString());

        if(cptWeek == 0){
            heartMin = (TextView) findViewById(R.id.value_min_heart_monitor_week1);
            heartMax = (TextView) findViewById(R.id.value_max_heart_monitor_week1);
            heartMean = (TextView) findViewById(R.id.value_mean_heart_monitor_week1);

            heartMin.setText(Integer.toString(dataWeek.getMinimum_hr()));
            heartMax.setText(Integer.toString(dataWeek.getMaximum_hr()));
            heartMean.setText(Integer.toString(dataWeek.getAverage_hr()));

            systolicBloodMean = (TextView) findViewById(R.id.value_mean_blood_pressure_systolic_week1);
            systolicBloodMean.setText(Integer.toString(dataWeek.getAverage_systolic_blood_pressure()));

            diastolicBloodMean = (TextView) findViewById(R.id.value_mean_blood_pressure_diastolic_week1);
            diastolicBloodMean.setText(Integer.toString(dataWeek.getAverage_diastolic_blood_pressure()));

            waterMean = (TextView) findViewById(R.id.value_mean_intra_thoracic_fluid_content_week1);
            waterMean.setText(Integer.toString(dataWeek.getAverage_thoracic_fluid_content()));

            salinityMean = (TextView) findViewById(R.id.value_mean_sodium_chloride_week1);
            salinityMean.setText(Integer.toString(dataWeek.getAverage_sodium_chloride()));
        }
        else if(cptWeek == 1){
            heartMin = (TextView) findViewById(R.id.value_min_heart_monitor_week2);
            heartMax = (TextView) findViewById(R.id.value_max_heart_monitor_week2);
            heartMean = (TextView) findViewById(R.id.value_mean_heart_monitor_week2);

            heartMin.setText(Integer.toString(dataWeek.getMinimum_hr()));
            heartMax.setText(Integer.toString(dataWeek.getMaximum_hr()));
            heartMean.setText(Integer.toString(dataWeek.getAverage_hr()));

            systolicBloodMean = (TextView) findViewById(R.id.value_mean_blood_pressure_systolic_week2);
            systolicBloodMean.setText(Integer.toString(dataWeek.getAverage_systolic_blood_pressure()));

            diastolicBloodMean = (TextView) findViewById(R.id.value_mean_blood_pressure_diastolic_week2);
            diastolicBloodMean.setText(Integer.toString(dataWeek.getAverage_diastolic_blood_pressure()));

            waterMean = (TextView) findViewById(R.id.value_mean_intra_thoracic_fluid_content_week2);
            waterMean.setText(Integer.toString(dataWeek.getAverage_thoracic_fluid_content()));

            salinityMean = (TextView) findViewById(R.id.value_mean_sodium_chloride_week2);
            salinityMean.setText(Integer.toString(dataWeek.getAverage_sodium_chloride()));
        }
        else if (cptWeek == 2){
            heartMin = (TextView) findViewById(R.id.value_min_heart_monitor_week3);
            heartMax = (TextView) findViewById(R.id.value_max_heart_monitor_week3);
            heartMean = (TextView) findViewById(R.id.value_mean_heart_monitor_week3);

            heartMin.setText(Integer.toString(dataWeek.getMinimum_hr()));
            heartMax.setText(Integer.toString(dataWeek.getMaximum_hr()));
            heartMean.setText(Integer.toString(dataWeek.getAverage_hr()));

            systolicBloodMean = (TextView) findViewById(R.id.value_mean_blood_pressure_systolic_week3);
            systolicBloodMean.setText(Integer.toString(dataWeek.getAverage_systolic_blood_pressure()));

            diastolicBloodMean = (TextView) findViewById(R.id.value_mean_blood_pressure_diastolic_week3);
            diastolicBloodMean.setText(Integer.toString(dataWeek.getAverage_diastolic_blood_pressure()));

            waterMean = (TextView) findViewById(R.id.value_mean_intra_thoracic_fluid_content_week3);
            waterMean.setText(Integer.toString(dataWeek.getAverage_thoracic_fluid_content()));

            salinityMean = (TextView) findViewById(R.id.value_mean_sodium_chloride_week3);
            salinityMean.setText(Integer.toString(dataWeek.getAverage_sodium_chloride()));
        }
        else if( cptWeek == 3){
            heartMin = (TextView) findViewById(R.id.value_min_heart_monitor_week4);
            heartMax = (TextView) findViewById(R.id.value_max_heart_monitor_week4);
            heartMean = (TextView) findViewById(R.id.value_mean_heart_monitor_week4);

            heartMin.setText(Integer.toString(dataWeek.getMinimum_hr()));
            heartMax.setText(Integer.toString(dataWeek.getMaximum_hr()));
            heartMean.setText(Integer.toString(dataWeek.getAverage_hr()));

            systolicBloodMean = (TextView) findViewById(R.id.value_mean_blood_pressure_systolic_week4);
            systolicBloodMean.setText(Integer.toString(dataWeek.getAverage_systolic_blood_pressure()));

            diastolicBloodMean = (TextView) findViewById(R.id.value_mean_blood_pressure_diastolic_week4);
            diastolicBloodMean.setText(Integer.toString(dataWeek.getAverage_diastolic_blood_pressure()));

            waterMean = (TextView) findViewById(R.id.value_mean_intra_thoracic_fluid_content_week4);
            waterMean.setText(Integer.toString(dataWeek.getAverage_thoracic_fluid_content()));

            salinityMean = (TextView) findViewById(R.id.value_mean_sodium_chloride_week4);
            salinityMean.setText(Integer.toString(dataWeek.getAverage_sodium_chloride()));
        }
        else if (cptWeek==4){
            heartMin = (TextView) findViewById(R.id.value_min_heart_monitor_week5);
            heartMax = (TextView) findViewById(R.id.value_max_heart_monitor_week5);
            heartMean = (TextView) findViewById(R.id.value_mean_heart_monitor_week5);

            heartMin.setText(Integer.toString(dataWeek.getMinimum_hr()));
            heartMax.setText(Integer.toString(dataWeek.getMaximum_hr()));
            heartMean.setText(Integer.toString(dataWeek.getAverage_hr()));

            systolicBloodMean = (TextView) findViewById(R.id.value_mean_blood_pressure_systolic_week5);
            systolicBloodMean.setText(Integer.toString(dataWeek.getAverage_systolic_blood_pressure()));

            diastolicBloodMean = (TextView) findViewById(R.id.value_mean_blood_pressure_diastolic_week5);
            diastolicBloodMean.setText(Integer.toString(dataWeek.getAverage_diastolic_blood_pressure()));

            waterMean = (TextView) findViewById(R.id.value_mean_intra_thoracic_fluid_content_week5);
            waterMean.setText(Integer.toString(dataWeek.getAverage_thoracic_fluid_content()));

            salinityMean = (TextView) findViewById(R.id.value_mean_sodium_chloride_week5);
            salinityMean.setText(Integer.toString(dataWeek.getAverage_sodium_chloride()));
        }
        cptWeek++;
    }
}
