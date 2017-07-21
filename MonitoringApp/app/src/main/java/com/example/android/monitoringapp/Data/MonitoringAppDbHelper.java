package com.example.android.monitoringapp.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.monitoringapp.Data.PatientContract.PatientEntry;
import com.example.android.monitoringapp.Data.DoctorContract.DoctorEntry;

/**
 * Database helper for the monitoring app. Manages database creation and version management.
 */
public class MonitoringAppDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = MonitoringAppDbHelper.class.getSimpleName();

    /** Name of the database file */
    private static final String DATABASE_NAME = "monitoringApp.db";

    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Constructs a new instance of {@link MonitoringAppDbHelper}.
     *
     * @param context of the app
     */
    public MonitoringAppDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is created for the first time.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the patient table
        String SQL_CREATE_PATIENT_TABLE =  "CREATE TABLE " + PatientEntry.TABLE_NAME + " ("
                + PatientEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PatientEntry.COLUMN_PATIENT_NAME + " TEXT NOT NULL, "
                + PatientEntry.COLUMN_PATIENT_PHONE + " TEXT NOT NULL, "
                + PatientEntry.COLUMN_PATIENT_ADDRESS + " TEXT, "
                + PatientEntry.COLUMN_PATIENT_POT_NAME + " TEXT,"
                + PatientEntry.COLUMN_PATIENT_POT_PHONE + " TEXT);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_PATIENT_TABLE);

        //Creation of a ContentValues (working like a HashMap)
        ContentValues values = new ContentValues();
        values.put(PatientEntry._ID,1);
        values.put(PatientEntry.COLUMN_PATIENT_NAME, "Default Name");
        values.put(PatientEntry.COLUMN_PATIENT_PHONE, "0123456789");
        values.put(PatientEntry.COLUMN_PATIENT_ADDRESS, "Default Address");
        values.put(PatientEntry.COLUMN_PATIENT_POT_NAME, "Default Name");
        values.put(PatientEntry.COLUMN_PATIENT_POT_PHONE, "0123456789");
        db.insert(PatientEntry.TABLE_NAME,null,values);

        values.clear();

        // Create a String that contains the SQL statement to create the doctor table
        String SQL_CREATE_DOCTOR_TABLE =  "CREATE TABLE " + DoctorEntry.TABLE_NAME + " ("
                + DoctorEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DoctorEntry.COLUMN_DOCTOR_NAME + " TEXT NOT NULL, "
                + DoctorEntry.COLUMN_DOCTOR_PHONE + " TEXT NOT NULL, "
                + DoctorEntry.COLUMN_DOCTOR_MAIL + " TEXT, "
                + DoctorEntry.COLUMN_DOCTOR_USERNAME + " TEXT,"
                + DoctorEntry.COLUMN_DOCTOR_PASSWORD + " TEXT);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_DOCTOR_TABLE);

        //Creation of a ContentValues (working like a HashMap)
        values.put(DoctorEntry._ID,1);
        values.put(DoctorEntry.COLUMN_DOCTOR_NAME, "Default Name");
        values.put(DoctorEntry.COLUMN_DOCTOR_PHONE, "0123456789");
        values.put(DoctorEntry.COLUMN_DOCTOR_MAIL, "default@mail.pt");
        values.put(DoctorEntry.COLUMN_DOCTOR_USERNAME, "username");
        values.put(DoctorEntry.COLUMN_DOCTOR_PASSWORD, "password");
        db.insert(DoctorEntry.TABLE_NAME,null,values);
    }

    /**
     * This is called when the database needs to be upgraded.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.
    }
}