package com.example.android.monitoringapp.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.monitoringapp.Data.DoctorContract.DoctorEntry;

/**
 * Database helper for the monitoring app. Manages database creation and version management.
 */
public class DoctorDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = DoctorDbHelper.class.getSimpleName();

    /** Name of the database file */
    private static final String DATABASE_NAME = "doctor.db";

    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Constructs a new instance of {@link DoctorDbHelper}.
     *
     * @param context of the app
     */
    public DoctorDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is created for the first time.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the pets table
        String SQL_CREATE_PATIENT_TABLE =  "CREATE TABLE " + DoctorEntry.TABLE_NAME + " ("
                + DoctorEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DoctorEntry.COLUMN_DOCTOR_NAME + " TEXT NOT NULL, "
                + DoctorEntry.COLUMN_DOCTOR_PHONE + " TEXT NOT NULL, "
                + DoctorEntry.COLUMN_DOCTOR_MAIL + " TEXT, "
                + DoctorEntry.COLUMN_DOCTOR_USERNAME + " TEXT,"
                + DoctorEntry.COLUMN_DOCTOR_PASSWORD + " TEXT);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_PATIENT_TABLE);
    }

    /**
     * This is called when the database needs to be upgraded.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.
    }
}