package com.example.android.monitoringapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.monitoringapp.Data.DoctorContract.DoctorEntry;
import com.example.android.monitoringapp.Data.DoctorDbHelper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DoctorModifyActivity extends AppCompatActivity {

    EditText nameDoctor;
    EditText phoneDoctor;
    EditText mailDoctor;
    EditText usernameDoctor;
    EditText passwordDoctor;
    TextView nameDoctorInitials;

    Button Click;

    /** Database helper that will provide us access to the database */
    private DoctorDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_modify);

        //if(phoneDoctor.getText().toString().equals("") || emailDoctor.getText().toString().equals("") || usernameDoctor.getText().toString().equals("") || passwordDoctor.getText().toString().equals("")){
            //Initialized every EditText
            nameDoctor = (EditText) findViewById(R.id.name_doctor);
            phoneDoctor = (EditText) findViewById(R.id.phone_doctor);
            mailDoctor = (EditText) findViewById(R.id.mail_doctor);
            usernameDoctor = (EditText) findViewById(R.id.username_doctor);
            passwordDoctor = (EditText) findViewById(R.id.password_doctor);
            nameDoctorInitials = (TextView) findViewById(R.id.name_doctor_initials);
       // }
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

    //Verify email address
  /* public boolean isEmailValid(String email)
    {
        String regExpn =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        +"((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        +"([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        +"([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(regExpn,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if(matcher.matches())
            return true;
        else
            return false;
    }*/

   public boolean isEmailValid(String email) {
        if (email == null)
            return false;
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public boolean isPhoneValid(CharSequence target) {
        if (target == null)
            return false;
        return android.util.Patterns.PHONE.matcher(target).matches();
    }

    //verify if EditTexts are not empty
    public void onClickChanges(View v) {
        String tmp_modification="";
        boolean mailValid = false;
        boolean phoneValid = false;

        //email ok ?
        tmp_modification = mailDoctor.getText().toString();
        mailValid = isEmailValid(tmp_modification);

        //phone ok ?
        tmp_modification = phoneDoctor.getText().toString();
        phoneValid = isPhoneValid(tmp_modification);


        if ((v == Click) &&  (phoneDoctor.getText().toString().equals("") || mailDoctor.getText().toString().equals("") || usernameDoctor.getText().toString().equals("") || passwordDoctor.getText().toString().equals("")) ){
            Toast.makeText(getBaseContext(), "One of the field is empty, please fill it to validate changes" , Toast.LENGTH_LONG).show();
        }
        else if(mailValid && phoneValid){
            tmp_modification = phoneDoctor.getText().toString();
            phoneDoctor.setText(tmp_modification);
            tmp_modification = mailDoctor.getText().toString();
            mailDoctor.setText(tmp_modification);
            tmp_modification = usernameDoctor.getText().toString();
            usernameDoctor.setText(tmp_modification);
            tmp_modification = passwordDoctor.getText().toString();
            passwordDoctor.setText(tmp_modification);
            Toast.makeText(getBaseContext(), "Update successful." , Toast.LENGTH_SHORT).show();
            updateDoctor();
            openDoctorInformation(v);
        }
        else{
            Toast.makeText(getBaseContext(), "Email or phone number are wrong, please correct the field" , Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Temporary helper method to display information in the onscreen TextView about the state of
     * the doctor database.
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

        // Perform a query on the pets table
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
                mailDoctor.setText(currentMail);
                usernameDoctor.setText(currentUsername);
                passwordDoctor.setText(currentPassword);
                nameDoctorInitials.setText(currentName.substring(0,1));
            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }


    /**
     * Get user input from editor and update the doctor info into database.
     */
    private void updateDoctor() {
        // Read from input fields
        // Use trim to eliminate leading or trailing white space
        String nameString = nameDoctor.getText().toString().trim();
        String phoneString = phoneDoctor.getText().toString().trim();
        String addressString = mailDoctor.getText().toString().trim();
        String phoneTrustString = usernameDoctor.getText().toString().trim();
        String nameTrustString = passwordDoctor.getText().toString().trim();


        // Create database helper
        DoctorDbHelper mDbHelper = new DoctorDbHelper(this);

        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and pet attributes from the editor are the values.
        ContentValues values = new ContentValues();
        values.put(DoctorEntry.COLUMN_DOCTOR_NAME, nameString);
        values.put(DoctorEntry.COLUMN_DOCTOR_PHONE, phoneString);
        values.put(DoctorEntry.COLUMN_DOCTOR_MAIL, addressString);
        values.put(DoctorEntry.COLUMN_DOCTOR_USERNAME, phoneTrustString);
        values.put(DoctorEntry.COLUMN_DOCTOR_PASSWORD, nameTrustString);

        String where = "id=?";
        String[] whereArgs = new String[] {String.valueOf('1')};

        try {
            db.update(DoctorEntry.TABLE_NAME, values, null,null);
            // Otherwise, the update was successful and we can display a toast.
            Toast.makeText(this, "Update Successful", Toast.LENGTH_SHORT).show();
        }
        catch(Exception e){
            // If the row ID is < 1, then there was an error with update.
            Toast.makeText(this, "Error with updating doctor", Toast.LENGTH_SHORT).show();
        }
    }
}
