package com.example.android.monitoringapp;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
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

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_export);
        spinner = (Spinner)findViewById(R.id.spinner_destination_export);
        adapter = ArrayAdapter.createFromResource(this, R.array.data_destination, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), parent.getItemIdAtPosition(position)+" selected", Toast.LENGTH_LONG).show();
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

            DateFormat sdf = new SimpleDateFormat("dd//MM/yyyy");
            Date newDate;
            Date oldDate;

            if(datePickerCheck==1) {
                newDateFrom = day + "/" +  month + "/" + year;
               /* newDate = sdf.parse(newDateFrom, new ParsePosition(0));
                if(to.equals("Select a date to")){*/
                    dateFrom.setText(newDateFrom);
               /* }
                else{
                    oldDate = sdf.parse(to, new ParsePosition(0));
                    if (newDate.compareTo(oldDate) <= 0) {
                        dateFrom.setText(newDateFrom);
                    }
                    else{
                        Toast.makeText(getBaseContext(), "You need to select a date from before a date to, try again", Toast.LENGTH_LONG).show();
                    }
                }*/
            }
            else if(datePickerCheck==2) {
                newDateTo = day + "/" + month + "/" + year;
               /* newDate = sdf.parse(newDateTo, new ParsePosition(0));
                if(to.equals("Select a date to")){*/
                    dateTo.setText(newDateTo);
               /* }
                else{
                    oldDate = sdf.parse(from, new ParsePosition(0));
                    if (newDate.compareTo(oldDate) >= 0) {
                        dateTo.setText(newDateTo);
                    }
                    else{
                        Toast.makeText(getBaseContext(), "You need to select a date from before a date to, try again", Toast.LENGTH_LONG).show();
                    }
                }*/
            }
        }
    };
}
