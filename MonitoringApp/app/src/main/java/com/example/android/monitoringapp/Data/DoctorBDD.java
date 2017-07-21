package com.example.android.monitoringapp.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.android.monitoringapp.Data.DoctorContract.DoctorEntry;


/**
 * Created by axel- on 19/07/2017.
 */

public class DoctorBDD {


    private static final String TABLE_DOCTOR = DoctorEntry.TABLE_NAME;


    private static final int INDEX_ID = 0;
    private static final int INDEX_NAME = 1;
    private static final int INDEX_PHONE = 2;
    private static final int INDEX_MAIL = 3;
    private static final int INDEX_USERNAME = 4;
    private static final int INDEX_PASSWORD = 5;

    private SQLiteDatabase db;
    private MonitoringAppDbHelper mDbHelper;

    public DoctorBDD(Context context){
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

    public long insertDoctor(Doctor doctor){
        //Creation of a ContentValues (working like a HashMap)
        ContentValues values = new ContentValues();
        values.put(DoctorEntry.COLUMN_DOCTOR_NAME, doctor.getName());
        values.put(DoctorEntry.COLUMN_DOCTOR_PHONE, doctor.getPhone());
        values.put(DoctorEntry.COLUMN_DOCTOR_MAIL, doctor.getMail());
        values.put(DoctorEntry.COLUMN_DOCTOR_USERNAME, doctor.getUsername());
        values.put(DoctorEntry.COLUMN_DOCTOR_PASSWORD, doctor.getPassword());
        return db.insert(TABLE_DOCTOR,null,values);
    }

    public int updateDoctor(Doctor doctor){
        //The update works more or less like an insertion
        //you just need to specify the id of the doctor to update
        ContentValues values = new ContentValues();
        values.put(DoctorEntry.COLUMN_DOCTOR_NAME, doctor.getName());
        values.put(DoctorEntry.COLUMN_DOCTOR_PHONE, doctor.getPhone());
        values.put(DoctorEntry.COLUMN_DOCTOR_MAIL, doctor.getMail());
        values.put(DoctorEntry.COLUMN_DOCTOR_USERNAME, doctor.getUsername());
        values.put(DoctorEntry.COLUMN_DOCTOR_PASSWORD, doctor.getPassword());
        return db.update(TABLE_DOCTOR, values,null, null);

    }

    public int removeDoctorWithID(int id){
        //removing a doctor with it's id
        return db.delete(TABLE_DOCTOR, DoctorEntry._ID + " = " +id, null);
    }

    public Doctor getDoctor(){
        //Gets in a cursor the data of the doctor (there should be only one doctor in the database, so we do not need to specify and id)
        Cursor cursor = db.query(TABLE_DOCTOR, new String[] { "*" },
                null,
                null, null, null, null, null);
        Doctor doctor = new Doctor();
        if(cursor != null)
        {
            if (cursor.moveToFirst()) {

                doctor.setId(cursor.getInt(INDEX_ID));
                doctor.setName(cursor.getString(INDEX_NAME));
                doctor.setPhone(cursor.getString(INDEX_PHONE));
                doctor.setMail(cursor.getString(INDEX_MAIL));
                doctor.setUsername(cursor.getString(INDEX_USERNAME));
                doctor.setPassword(cursor.getString(INDEX_PASSWORD));
            }
        } else{
            doctor.setName("Test");
            doctor.setPhone("0123456789");
            doctor.setMail("default@mail.pt");
            doctor.setUsername("username");
            doctor.setPassword("password");
            insertDoctor(doctor);
        }
        cursor.close();
        return doctor;
    }

}
