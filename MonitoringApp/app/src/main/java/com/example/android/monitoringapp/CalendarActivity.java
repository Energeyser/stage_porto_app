package com.example.android.monitoringapp;
import android.content.Intent;
import android.icu.util.Calendar;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;


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
                .setMinimumDate(CalendarDay.from(2016, 4, 3))
                .setMaximumDate(CalendarDay.from(2018, 5, 12))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

        //if there is a problem
       boolean pbDetected = MonthSummaryActivity.problemDetected();
        if (pbDetected) {
            //Collection<CalendarDay> datePB ;
            //calendar.addDecorator(new EventDecorator(Color.RED, datePB));
            //probleme avec la liste de date datePB


          /*  CalendarDay day1 =
             ArrayList<CalendarDay> dates;
             date
             */
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
