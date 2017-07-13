package com.example.android.monitoringapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import com.example.android.monitoringapp.Data.PatientDbHelper;
import com.example.android.monitoringapp.Data.PatientContract.PatientEntry;

public class PatientInformationsActivity extends AppCompatActivity {

    TextView namePatient;
    TextView phonePatient;
    TextView addressPatient;
    TextView phonePersonTrustPatient;
    TextView namePersonTrustPatient;
    TextView namePatientInitials;

    /**
     * Database helper that will provide us access to the database
     */
    private PatientDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_patient_informations);

        namePatient = (TextView) findViewById(R.id.name_patient);
        phonePatient = (TextView) findViewById(R.id.phone_patient);
        addressPatient = (TextView) findViewById(R.id.address_patient);
        namePersonTrustPatient = (TextView) findViewById(R.id.name_person_trust);
        phonePersonTrustPatient = (TextView) findViewById(R.id.phone_person_trust);
        namePatientInitials = (TextView) findViewById(R.id.name_patient_initials);

        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        mDbHelper = new PatientDbHelper(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayPatientInfo();
    }

    public void openMonthSummary(View view) {
        Intent i = new Intent(this, MonthSummaryActivity.class);
        startActivity(i);
    }

    public void openCalendar(View view) {
        Intent i = new Intent(this, CalendarActivity.class);
        startActivity(i);
    }

    public void openExport(View view) {
        Intent i = new Intent(this, ExportActivity.class);
        startActivity(i);
    }

    public void openDoctorInformation(View view) {
        Intent i = new Intent(this, DoctorInformationActivity.class);
        startActivity(i);
    }

    public void openPatientModify(View view) {
        Intent i = new Intent(this, PatientModifyActivity.class);
        startActivity(i);
    }

    /**
     * Temporary helper method to display information in the onscreen TextView about the state of
     * the patient database.
     */
    private void displayPatientInfo() {
        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String count = "SELECT count(*) FROM patient";
        Cursor mcursor = db.rawQuery(count, null);
        mcursor.moveToFirst();
        int icount = mcursor.getInt(0);
        if (icount > 0) {

            // Define a projection that specifies which columns from the database
            // you will actually use after this query.
            String[] projection = {
                    PatientEntry._ID,
                    PatientEntry.COLUMN_PATIENT_NAME,
                    PatientEntry.COLUMN_PATIENT_PHONE,
                    PatientEntry.COLUMN_PATIENT_ADDRESS,
                    PatientEntry.COLUMN_PATIENT_POT_NAME,
                    PatientEntry.COLUMN_PATIENT_POT_PHONE};

            // Perform a query on the patient table
            Cursor cursor = db.query(
                    PatientEntry.TABLE_NAME,   // The table to query
                    projection,            // The columns to return
                    null,                  // The columns for the WHERE clause
                    null,                  // The values for the WHERE clause
                    null,                  // Don't group the rows
                    null,                  // Don't filter by row groups
                    null);                   // The sort order


            try {

                // Figure out the index of each column
                int idColumnIndex = cursor.getColumnIndex(PatientEntry._ID);
                int nameColumnIndex = cursor.getColumnIndex(PatientEntry.COLUMN_PATIENT_NAME);
                int phoneColumnIndex = cursor.getColumnIndex(PatientEntry.COLUMN_PATIENT_PHONE);
                int addressColumnIndex = cursor.getColumnIndex(PatientEntry.COLUMN_PATIENT_ADDRESS);
                int potNameColumnIndex = cursor.getColumnIndex(PatientEntry.COLUMN_PATIENT_POT_NAME);
                int potPhoneColumnIndex = cursor.getColumnIndex(PatientEntry.COLUMN_PATIENT_POT_PHONE);

                // Iterate through all the returned rows in the cursor
                while (cursor.moveToNext()) {
                    // Use that index to extract the String or Int value of the word
                    // at the current row the cursor is on.
                    int currentID = cursor.getInt(idColumnIndex);
                    String currentName = cursor.getString(nameColumnIndex);
                    String currentPhone = cursor.getString(phoneColumnIndex);
                    String currentAddress = cursor.getString(addressColumnIndex);
                    String currentPotName = cursor.getString(potNameColumnIndex);
                    String currentPotPhone = cursor.getString(potPhoneColumnIndex);

                    namePatientInitials.setText(currentName.substring(0,1));
                    namePatient.setText(currentName);
                    phonePatient.setText(currentPhone);
                    addressPatient.setText(currentAddress);
                    namePersonTrustPatient.setText(currentPotName);
                    phonePersonTrustPatient.setText(currentPotPhone);
                }
            } finally {
                // Always close the cursor when you're done reading from it. This releases all its
                // resources and makes it invalid.
                cursor.close();
            }
        } else {
            try {
                ContentValues values = new ContentValues();
                values.put(PatientEntry.COLUMN_PATIENT_NAME, "John");
                values.put(PatientEntry.COLUMN_PATIENT_PHONE, "+351 22 508 1400");
                values.put(PatientEntry.COLUMN_PATIENT_ADDRESS, "15 rua da, eqrfvqêfvq");
                values.put(PatientEntry.COLUMN_PATIENT_POT_NAME, "Jane DOE");
                values.put(PatientEntry.COLUMN_PATIENT_POT_PHONE, "+351 55 802 669");

                long newRowId = db.insert(PatientEntry.TABLE_NAME, null, values);
                displayPatientInfo();
            } finally {
                // Always close the cursor when you're done reading from it. This releases all its
                // resources and makes it invalid.
                mcursor.close();
            }
        }

    }

/*
    private void insertPatient() {
        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PatientEntry.COLUMN_PATIENT_NAME, "John");
        values.put(PatientEntry.COLUMN_PATIENT_PHONE, "+351 22 508 1400");
        values.put(PatientEntry.COLUMN_PATIENT_ADDRESS, "15 rua da, eqrfvqêfvq");
        values.put(PatientEntry.COLUMN_PATIENT_POT_NAME, "Jane DOE");
        values.put(PatientEntry.COLUMN_PATIENT_POT_PHONE, "+351 55 802 669");

        // Insert a new row for Toto in the database, returning the ID of that new row.
        // The first argument for db.insert() is the pets table name.
        // The second argument provides the name of a column in which the framework
        // can insert NULL in the event that the ContentValues is empty (if
        // this is set to "null", then the framework will not insert a row when
        // there are no values).
        // The third argument is the ContentValues object containing the info for Toto.
        long newRowId = db.insert(PatientEntry.TABLE_NAME, null, values);
    }*/
}
