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
import android.widget.Toast;
import com.example.android.monitoringapp.Data.DoctorDbHelper;
import com.example.android.monitoringapp.Data.DoctorContract.DoctorEntry;

import static com.example.android.monitoringapp.Data.DoctorContract.DoctorEntry.COLUMN_DOCTOR_PASSWORD;
import static com.example.android.monitoringapp.Data.DoctorContract.DoctorEntry.COLUMN_DOCTOR_USERNAME;

public class LandingActivity extends AppCompatActivity {

    EditText login;
    EditText password;
    /**
     * Database helper that will provide us access to the database
     */


   // private DoctorDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        /*mDbHelper = new DoctorDbHelper(this);
        insertDoctor();*/
    }


    public void onClickValidation(View v) {

        boolean check = true;
       // check = checkDoctor();

        if (check) {
            Intent i = new Intent(this, MonthSummaryActivity.class);
            startActivity(i);
        } else {
            Toast.makeText(getBaseContext(), "Your identification is false, try again", Toast.LENGTH_LONG).show();
        }
    }
    /**
     * Get user input from editor and update the doctor info into database.
     */
   /* private boolean checkDoctor() {

        //Get values enter in editText (login + password)
        String log = "";
        String pwd = "";

        login = (EditText) findViewById(R.id.login_doctor);
        password = (EditText) findViewById(R.id.password_doctor);
        log = login.getText().toString();
        pwd = password.getText().toString();

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        try {
            String selectQueryUsername = "SELECT " + COLUMN_DOCTOR_USERNAME + " FROM " + db;
            String selectQueryPassword = "SELECT " + COLUMN_DOCTOR_PASSWORD + " FROM " + db;

           // Cursor cursor_log = db.rawQuery(selectQueryUsername, null);
            //String user = cursor_log.toString();

            //Cursor cursor_pwd = db.rawQuery(selectQueryPassword, null);
            //String passwd = cursor_pwd.toString();

            Toast.makeText(this, "Problem with your username ! " + selectQueryUsername , Toast.LENGTH_LONG).show();
            Toast.makeText(this, "Problem with your pwd! " + selectQueryPassword, Toast.LENGTH_LONG).show();

            if (log == selectQueryUsername) {
                if (pwd ==  selectQueryPassword) {
                    // Otherwise, the update was successful and we can display a toast.
                    Toast.makeText(this, "Get data Successful", Toast.LENGTH_SHORT).show();
                    return true;
                } else {
                    Toast.makeText(this, "Problem with your password", Toast.LENGTH_SHORT).show();
                    return false;
                }
            } else {
                return false;
            }
        } catch (Exception e) {
            // If the row ID is < 1, then there was an error with update.
            Toast.makeText(this, "Error with login doctor", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void insertDoctor() {
        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DoctorEntry.COLUMN_DOCTOR_USERNAME, "Admin");
        values.put(DoctorEntry.COLUMN_DOCTOR_PASSWORD, "password");
        values.put(DoctorEntry.COLUMN_DOCTOR_NAME, "Carla");
        values.put(DoctorEntry.COLUMN_DOCTOR_PHONE, "+351 22 508 1400");
        values.put(DoctorEntry.COLUMN_DOCTOR_MAIL, "martins.carlamrt@gmail.com");

        // Insert a new row for Toto in the database, returning the ID of that new row.
        // The first argument for db.insert() is the pets table name.
        // The second argument provides the name of a column in which the framework
        // can insert NULL in the event that the ContentValues is empty (if
        // this is set to "null", then the framework will not insert a row when
        // there are no values).
        // The third argument is the ContentValues object containing the info for Toto.
        long newRowId = db.insert(DoctorEntry.TABLE_NAME, null, values);
    }
    */
}