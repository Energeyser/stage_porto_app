package com.example.android.monitoringapp.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.android.monitoringapp.Data.PatientContract.PatientEntry;

import static android.R.attr.id;


/**
 * Created by axel- on 19/07/2017.
 */

public class PatientBDD {


    private static final String TABLE_PATIENT = PatientEntry.TABLE_NAME;


    private static final int INDEX_ID = 0;
    private static final int INDEX_NAME = 1;
    private static final int INDEX_PHONE = 2;
    private static final int INDEX_ADDRESS = 3;
    private static final int INDEX_POT_NAME = 4;
    private static final int INDEX_POT_PHONE = 5;

    private SQLiteDatabase db;
    private MonitoringAppDbHelper mDbHelper;

    public PatientBDD(Context context){
        // Creating the database and its table
        mDbHelper = new MonitoringAppDbHelper(context);
    }


    public void open(){
        //opens the db for writing
        db = mDbHelper.getWritableDatabase();
    }

    public void close(){
        //closing the access to the db
        db.close();
    }

    public SQLiteDatabase getDb(){
        return db;
    }

    public long insertPatient(Patient patient){
        //Creation of a ContentValues (working like a HashMap)
        ContentValues values = new ContentValues();
        values.put(PatientEntry.COLUMN_PATIENT_NAME, patient.getName());
        values.put(PatientEntry.COLUMN_PATIENT_PHONE, patient.getPhone());
        values.put(PatientEntry.COLUMN_PATIENT_ADDRESS, patient.getAddress());
        values.put(PatientEntry.COLUMN_PATIENT_POT_NAME, patient.getPot_name());
        values.put(PatientEntry.COLUMN_PATIENT_POT_PHONE, patient.getPot_phone());
        return db.insert(TABLE_PATIENT,null,values);
    }

    public int updatePatient(Patient patient){
        //The update works more or less like an insertion
        //you just need to specify the id of the patient to update
        ContentValues values = new ContentValues();
        values.put(PatientEntry.COLUMN_PATIENT_NAME, patient.getName());
        values.put(PatientEntry.COLUMN_PATIENT_PHONE, patient.getPhone());
        values.put(PatientEntry.COLUMN_PATIENT_ADDRESS, patient.getAddress());
        values.put(PatientEntry.COLUMN_PATIENT_POT_NAME, patient.getPot_name());
        values.put(PatientEntry.COLUMN_PATIENT_POT_PHONE, patient.getPot_phone());
        return db.update(TABLE_PATIENT, values,null, null);

    }

    public int removePatientWithID(int id){
        //removing a patient with it's id
        return db.delete(TABLE_PATIENT, PatientEntry._ID + " = " +id, null);
    }

    public Patient getPatient(){
        //Gets in a cursor the data of the patient (there should be only one patient in the database, so we do not need to specify and id)
        Cursor cursor = db.query(TABLE_PATIENT, new String[] { "*" },
                null,
                null, null, null, null, null);
        Patient patient = new Patient();
        if(cursor != null)
        {
            if (cursor.moveToFirst()) {

                patient.setId(cursor.getInt(INDEX_ID));
                patient.setName(cursor.getString(INDEX_NAME));
                patient.setPhone(cursor.getString(INDEX_PHONE));
                patient.setAddress(cursor.getString(INDEX_ADDRESS));
                patient.setPot_name(cursor.getString(INDEX_POT_NAME));
                patient.setPot_phone(cursor.getString(INDEX_POT_PHONE));
            }
        } else{
            patient.setName("Test");
            patient.setPhone("0123456789");
            patient.setAddress("Default Adress");
            patient.setPot_name("Test");
            patient.setPot_phone("0123456789");
            insertPatient(patient);
        }
        cursor.close();
        return patient;
    }

}
