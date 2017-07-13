package com.example.android.monitoringapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.monitoringapp.Data.PatientContract.PatientEntry;
import com.example.android.monitoringapp.Data.PatientDbHelper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.R.attr.id;
import static com.example.android.monitoringapp.R.id.address_patient;

public class PatientModifyActivity extends AppCompatActivity {

    EditText namePatient;
    EditText phonePatient;
    EditText addressPatient;
    EditText namePersonTrust;
    EditText phonePersonTrust;
    TextView namePatientInitials;

    /** Database helper that will provide us access to the database */
    private PatientDbHelper mDbHelper;

    private Button Click;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_modify);

        //Initialized every EditText
       // if (phonePatient.getText().toString().equals("") && addressPatient.getText().toString().equals("") && namePersonTrust.getText().toString().equals("") && phonePersonTrust.getText().toString().equals("")) {
            namePatient = (EditText) findViewById(R.id.name_patient);
            phonePatient = (EditText) findViewById(R.id.phone_patient);
            addressPatient = (EditText) findViewById(address_patient);
            namePersonTrust = (EditText) findViewById(R.id.name_person_trust);
            phonePersonTrust = (EditText) findViewById(R.id.phone_person_trust);
        namePatientInitials = (TextView) findViewById(R.id.name_patient_initials);
      //  }
        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        mDbHelper = new PatientDbHelper(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayPatientInfo();
    }


    public void openMonthSummary(View view){
        Intent i = new Intent(this, MonthSummaryActivity.class);
        startActivity(i);
    }

    public void openPatientInformation(View view){
        Intent i = new Intent(this, PatientInformationsActivity.class);
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

    public boolean isPhoneValid(CharSequence target) {
        if (target == null)
            return false;
        return android.util.Patterns.PHONE.matcher(target).matches();
    }

    //verify if EditTexts are not empty
    public void verifyChanges(View v) {
        String tmp_modification="";
        boolean phonePatientValid = false;
        boolean phonePersonTrustValid = false;

        //phone ok ?
        tmp_modification = phonePatient.getText().toString();
        phonePatientValid = isPhoneValid(tmp_modification);
        tmp_modification = phonePersonTrust.getText().toString();
        phonePersonTrustValid = isPhoneValid(tmp_modification);


        if ((v == Click) &&  (namePatient.getText().toString().equals("") || phonePatient.getText().toString().equals("") || addressPatient.getText().toString().equals("") || namePersonTrust.getText().toString().equals("") || phonePersonTrust.getText().toString().equals("")) ){
            Toast.makeText(getBaseContext(), "One of the field is empty, please fill it to validate changes" , Toast.LENGTH_LONG).show();
        }
        else if (phonePatientValid && phonePersonTrustValid){
            tmp_modification = namePatient.getText().toString();
            namePatient.setText(tmp_modification);
            tmp_modification = phonePatient.getText().toString();
            phonePatient.setText(tmp_modification);
            tmp_modification = addressPatient.getText().toString();
            addressPatient.setText(tmp_modification);
            tmp_modification = phonePersonTrust.getText().toString();
            phonePersonTrust.setText(tmp_modification);
            tmp_modification = namePersonTrust.getText().toString();
            namePersonTrust.setText(tmp_modification);
            //Toast.makeText(getBaseContext(), "Validation of the changes have been made." , Toast.LENGTH_SHORT).show();
            updatePatient();
            openPatientInformation(v);
        }
        else{
            Toast.makeText(getBaseContext(), "Phone number is wrong, please correct the field" , Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Temporary helper method to display information in the onscreen TextView about the state of
     * the patient database.
     */
    private void displayPatientInfo() {
        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                PatientEntry._ID,
                PatientEntry.COLUMN_PATIENT_NAME,
                PatientEntry.COLUMN_PATIENT_PHONE,
                PatientEntry.COLUMN_PATIENT_ADDRESS,
                PatientEntry.COLUMN_PATIENT_POT_NAME,
                PatientEntry.COLUMN_PATIENT_POT_PHONE };

        // Perform a query on the pets table
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
                    namePatient.setText(currentName);
                    phonePatient.setText(currentPhone);
                    addressPatient.setText(currentAddress);
                    namePersonTrust.setText(currentPotName);
                    phonePersonTrust.setText(currentPotPhone);
                    namePatientInitials.setText(currentName.substring(0,1));
                }
            } finally {
                // Always close the cursor when you're done reading from it. This releases all its
                // resources and makes it invalid.
                cursor.close();
            }
    }


    /**
     * Get user input from editor and update the patient info into database.
     */
    private void updatePatient() {
        // Read from input fields
        // Use trim to eliminate leading or trailing white space
        String nameString = namePatient.getText().toString().trim();
        String phoneString = phonePatient.getText().toString().trim();
        String addressString = addressPatient.getText().toString().trim();
        String phoneTrustString = phonePersonTrust.getText().toString().trim();
        String nameTrustString = namePersonTrust.getText().toString().trim();


        // Create database helper
        PatientDbHelper mDbHelper = new PatientDbHelper(this);

        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and pet attributes from the editor are the values.
        ContentValues values = new ContentValues();
        values.put(PatientEntry.COLUMN_PATIENT_NAME, nameString);
        values.put(PatientEntry.COLUMN_PATIENT_PHONE, phoneString);
        values.put(PatientEntry.COLUMN_PATIENT_ADDRESS, addressString);
        values.put(PatientEntry.COLUMN_PATIENT_POT_PHONE, phoneTrustString);
        values.put(PatientEntry.COLUMN_PATIENT_POT_NAME, nameTrustString);

        String where = "id=?";
        String[] whereArgs = new String[] {String.valueOf('1')};

        try {
            db.update(PatientEntry.TABLE_NAME, values, null,null);
            // Otherwise, the update was successful and we can display a toast.
            Toast.makeText(this, "Update Successful", Toast.LENGTH_SHORT).show();
        }
        catch(Exception e){
            // If the row ID is < 1, then there was an error with update.
            Toast.makeText(this, "Error with updating patient", Toast.LENGTH_SHORT).show();
        }
    }
}
