package com.example.android.monitoringapp;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Date;

import com.example.android.monitoringapp.Data.Data;
import com.example.android.monitoringapp.Data.DataBDD;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import java.text.SimpleDateFormat;

public class ExportActivity extends AppCompatActivity {

    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;

    private DatePicker dpResult;
    private Button validateChangesExport;

    private int year = 2017;
    private int month = 07;
    private int day = 03;

    private int datePickerCheck = 0;

    static final int DATE_DIALOG_ID = 999;
    Button btnexport;
    DataBDD dataBDD = new DataBDD(this);
    Data data = new Data();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_export);
        btnexport = (Button) findViewById(R.id.button_destination_export);
        btnexport.setOnClickListener(myhandler);
        spinner = (Spinner) findViewById(R.id.spinner_destination_export);
        adapter = ArrayAdapter.createFromResource(this, R.array.data_destination, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), parent.getItemIdAtPosition(position) + " selected", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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

    public void openExportSuccessful(View view){
        Intent i = new Intent(this, ExportSuccessfulActivity.class);
        startActivity(i);
    }

    public void openDateFrom(View view){
        showDialog(999);
        datePickerCheck = 1;

    }

    public void openDateTo(View view){
        showDialog(999);
        datePickerCheck = 2 ;
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        return null;
    }

    View.OnClickListener myhandler = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            Toast.makeText(getBaseContext(), " Ouais ouais le click", Toast.LENGTH_LONG).show();
            try {
                File sdCardDir = Environment.getExternalStorageDirectory();
                String filename = "MyBackUp.csv";
                File saveFile = new File(sdCardDir, filename);
                FileWriter fw = new FileWriter(saveFile);

                BufferedWriter bw = new BufferedWriter(fw);
                dataBDD.open();
                Cursor cursor = dataBDD.getDataExport("2017/07/25","2017/07/24");

                int rowcount;
                int colcount;
                rowcount = cursor.getCount();
                colcount = cursor.getColumnCount();
                if (rowcount > 0) {
                    cursor.moveToFirst();

                    for (int i = 0; i < colcount; i++) {
                        if (i != colcount - 1) {

                            bw.write(cursor.getColumnName(i) + ",");

                        } else {

                            bw.write(cursor.getColumnName(i));

                        }
                    }
                    bw.newLine();

                    for (int i = 0; i < rowcount; i++) {
                        cursor.moveToPosition(i);

                        for (int j = 0; j < colcount; j++) {
                            if (j != colcount - 1)
                                bw.write(cursor.getString(j) + ",");
                            else
                                bw.write(cursor.getString(j));
                        }
                        bw.newLine();
                    }
                    bw.flush();
                }
            } catch (Exception ex) {
                System.out.println(ex);

            } finally {
                dataBDD.close();
            }

        }
    };



    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
              year = arg1;
              month = arg2 + 1 ;
              day = arg3 ;

            TextView dateFrom;
            String newDateFrom;

            TextView dateTo;
            String newDateTo;

            dateFrom = (TextView) findViewById(R.id.date_from_export_calendar);
            dateTo = (TextView) findViewById(R.id.date_to_export_calendar);
            String from = (String)dateFrom.getText();
            String to = (String)dateTo.getText();

            System.out.println("from: "+from+ " to: "+to);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date dateF = new Date();
            Date dateT = new Date();
            try {
                dateF = dateFormat.parse(from);
                dateT = dateFormat.parse(to);
            } catch (Exception e) {
                System.err.println("Format de date invalide. Usage : dd/MM/YYYY");
                System.err.println(e.getMessage());
            }

            //current day
            CalendarDay currentDay = new CalendarDay();
            int thisYear = currentDay.getYear();
            int thisMonth = currentDay.getMonth() + 1;
            int thisDay = currentDay.getDay();
            String curDay = putZero(thisDay, thisMonth, thisYear);
            Date todayDay = new Date();
            todayDay = null;

            //new dates
            SimpleDateFormat dateForm = new SimpleDateFormat("yyyy/MM/dd");
            Date newDat = new Date();
            newDat = null;

            //put the string current day in date type
            try {
                todayDay = dateForm.parse(curDay);
            } catch (Exception e) {
                System.err.println("Format de date invalide. Usage : dd/MM/YYYY");
                System.err.println(e.getMessage());
            }


            if(datePickerCheck==1) {
                newDateFrom = putZero(day, month, year);
                if(to == ""){
                    dateFrom.setText(newDateFrom);
                }
                else {
                    try {
                        newDat = dateForm.parse(newDateFrom);
                    } catch (Exception e) {
                        System.err.println("Format de date invalide. Usage : dd/MM/YYYY");
                        System.err.println(e.getMessage());
                    }
                    if (newDat.after(todayDay)){
                        Toast.makeText(getBaseContext(), " You need to choose a date before today", Toast.LENGTH_LONG).show();
                    }
                    else if (newDat.after(dateT)) {
                        Toast.makeText(getBaseContext(), " The date from is after the date to, correct please", Toast.LENGTH_LONG).show();
                    } else {
                        dateFrom.setText(newDateFrom);
                    }
                }
            }
            else if(datePickerCheck==2) {
                newDateTo = putZero(day, month, year);
                if(from == ""){
                    dateTo.setText(newDateTo);
                }
                else {
                    try {
                        newDat = dateForm.parse(newDateTo);
                    } catch (Exception e) {
                        System.err.println("Format de date invalide. Usage : dd/MM/YYYY");
                        System.err.println(e.getMessage());
                    }
                    if (newDat.after(todayDay)){
                        Toast.makeText(getBaseContext(), " You need to choose a date before today", Toast.LENGTH_LONG).show();
                    }
                    else if (newDat.before(dateF)) {
                        Toast.makeText(getBaseContext(), " The date to is before the date from, correct please", Toast.LENGTH_LONG).show();
                    }
                    else{
                        dateTo.setText(newDateTo);
                    }
                }
            }
        }
    };

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
}
