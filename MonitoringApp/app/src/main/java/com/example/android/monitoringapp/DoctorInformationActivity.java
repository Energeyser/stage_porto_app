package com.example.android.monitoringapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.monitoringapp.Data.DoctorDbHelper;
import com.example.android.monitoringapp.Data.DoctorContract.DoctorEntry;

public class DoctorInformationActivity extends AppCompatActivity {

    TextView phoneDoctor;
    TextView emailDoctor;

    /** Database helper that will provide us access to the database */
    private DoctorDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_information);

        phoneDoctor = (TextView) findViewById(R.id.phone_doctor);


        emailDoctor = (TextView) findViewById(R.id.mail_doctor);


        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        mDbHelper = new DoctorDbHelper(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDoctorInfo();
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

    public void openDoctorModify(View view){
        Intent i = new Intent(this, DoctorModifyActivity.class);
        startActivity(i);
    }

    /**
     * Temporary helper method to display information in the onscreen TextView about the state of
     * the patient database.
     */
    private void displayDoctorInfo() {
        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                DoctorEntry._ID,
                DoctorEntry.COLUMN_DOCTOR_NAME,
                DoctorEntry.COLUMN_DOCTOR_PHONE,
                DoctorEntry.COLUMN_DOCTOR_MAIL,
                DoctorEntry.COLUMN_DOCTOR_USERNAME,
                DoctorEntry.COLUMN_DOCTOR_PASSWORD };

        // Perform a query on the patient table
        Cursor cursor = db.query(
                DoctorEntry.TABLE_NAME,   // The table to query
                projection,            // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                   // The sort order


        try {

            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(DoctorEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(DoctorEntry.COLUMN_DOCTOR_NAME);
            int phoneColumnIndex = cursor.getColumnIndex(DoctorEntry.COLUMN_DOCTOR_PHONE);
            int mailColumnIndex = cursor.getColumnIndex(DoctorEntry.COLUMN_DOCTOR_MAIL);
            int usernameColumnIndex = cursor.getColumnIndex(DoctorEntry.COLUMN_DOCTOR_USERNAME);
            int passwordColumnIndex = cursor.getColumnIndex(DoctorEntry.COLUMN_DOCTOR_PASSWORD);

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                String currentPhone = cursor.getString(phoneColumnIndex);
                String currentMail = cursor.getString(mailColumnIndex);
                String currentUsername = cursor.getString(usernameColumnIndex);
                String currentPassword = cursor.getString(passwordColumnIndex);

                phoneDoctor.setText(currentPhone);
                emailDoctor.setText(currentMail);

            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }
}
