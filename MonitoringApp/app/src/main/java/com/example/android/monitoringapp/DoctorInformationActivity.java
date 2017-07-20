package com.example.android.monitoringapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.monitoringapp.Data.MonitoringAppDbHelper;
import com.example.android.monitoringapp.Data.DoctorContract.DoctorEntry;

import org.w3c.dom.Text;

public class DoctorInformationActivity extends AppCompatActivity {

    TextView nameDoctor;
    TextView phoneDoctor;
    TextView emailDoctor;
    TextView nameDoctorInitials;

    /** Database helper that will provide us access to the database */
    private MonitoringAppDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_information);

        nameDoctor = (TextView) findViewById(R.id.name_doctor);
        phoneDoctor = (TextView) findViewById(R.id.phone_doctor);
        emailDoctor = (TextView) findViewById(R.id.mail_doctor);
        nameDoctorInitials = (TextView) findViewById(R.id.name_doctor_initials);


        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        mDbHelper = new MonitoringAppDbHelper(this);
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

        String count = "SELECT count(*) FROM doctor";
        Cursor mcursor = db.rawQuery(count, null);
        mcursor.moveToFirst();
        int icount = mcursor.getInt(0);
        if (icount > 0) {

            // Define a projection that specifies which columns from the database
            // you will actually use after this query.
            String[] projection = {
                    DoctorEntry._ID,
                    DoctorEntry.COLUMN_DOCTOR_NAME,
                    DoctorEntry.COLUMN_DOCTOR_PHONE,
                    DoctorEntry.COLUMN_DOCTOR_MAIL,
                    DoctorEntry.COLUMN_DOCTOR_USERNAME,
                    DoctorEntry.COLUMN_DOCTOR_PASSWORD};

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

                    nameDoctor.setText(currentName);
                    phoneDoctor.setText(currentPhone);
                    emailDoctor.setText(currentMail);
                    nameDoctorInitials.setText(currentName.substring(0,1));
                }
            } finally {
                // Always close the cursor when you're done reading from it. This releases all its
                // resources and makes it invalid.
                cursor.close();
            }
        }else{
            try {
                ContentValues values = new ContentValues();
                values.put(DoctorEntry.COLUMN_DOCTOR_NAME, "Carla");
                values.put(DoctorEntry.COLUMN_DOCTOR_PHONE, "+351 22 508 1400");
                values.put(DoctorEntry.COLUMN_DOCTOR_MAIL, "martins.carlamrt@gmail.com");
                values.put(DoctorEntry.COLUMN_DOCTOR_USERNAME, "Admin");
                values.put(DoctorEntry.COLUMN_DOCTOR_PASSWORD, "password");

                long newRowId = db.insert(DoctorEntry.TABLE_NAME, null, values);
                displayDoctorInfo();
            } finally {
                // Always close the cursor when you're done reading from it. This releases all its
                // resources and makes it invalid.
                mcursor.close();
            }
        }
    }
}
