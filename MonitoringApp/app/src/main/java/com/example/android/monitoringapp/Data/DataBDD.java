package com.example.android.monitoringapp.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.android.monitoringapp.Data.DataContract.DataEntry;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static android.R.attr.data;

/**
 * Created by axel- on 19/07/2017.
 * This file contains the methods used to access the data in the database
 */

public class DataBDD {


    private static final String TABLE_DATA = DataEntry.TABLE_NAME;


    private static final int INDEX_ID = 0;
    private static final int INDEX_PATIENT_NAME = 1;
    private static final int INDEX_PATIENT_PROCESS_NUMBER = 2;
    private static final int INDEX_DATE = 3;
    private static final int INDEX_MINIMUM_HR = 4;
    private static final int INDEX_MAXIMUM_HR = 5;
    private static final int INDEX_AVERAGE_HR = 6;
    private static final int INDEX_MINIMUM_RESP = 7;
    private static final int INDEX_MAXIMUM_RESP = 8;
    private static final int INDEX_AVERAGE_RESP = 9;
    private static final int INDEX_MINIMUM_OXY = 10;
    private static final int INDEX_MAXIMUM_OXY = 11;
    private static final int INDEX_AVERAGE_OXY = 12;
    private static final int INDEX_ECG_DESCRIPTION = 13;
    private static final int INDEX_MINIMUM_THORACIC_FLUID_CONTENT = 14;
    private static final int INDEX_MAXIMUM_THORACIC_FLUID_CONTENT = 15;
    private static final int INDEX_AVERAGE_THORACIC_FLUID_CONTENT = 16;
    private static final int INDEX_MINIMUM_BODY_FLUID_CONTENT = 17;
    private static final int INDEX_MAXIMUM_BODY_FLUID_CONTENT = 18;
    private static final int INDEX_AVERAGE_BODY_FLUID_CONTENT = 19;
    private static final int INDEX_MINIMUM_SYSTOLIC_BLOOD_PRESSURE = 20;
    private static final int INDEX_MAXIMUM_SYSTOLIC_BLOOD_PRESSURE = 21;
    private static final int INDEX_AVERAGE_SYSTOLIC_BLOOD_PRESSURE = 22;
    private static final int INDEX_MINIMUM_DIASTOLIC_BLOOD_PRESSURE = 23;
    private static final int INDEX_MAXIMUM_DIASTOLIC_BLOOD_PRESSURE = 24;
    private static final int INDEX_AVERAGE_DIASTOLIC_BLOOD_PRESSURE = 25;
    private static final int INDEX_MINIMUM_SODIUM_CHLORIDE = 26;
    private static final int INDEX_MAXIMUM_SODIUM_CHLORIDE = 27;
    private static final int INDEX_AVERAGE_SODIUM_CHLORIDE = 28;
    private static final int INDEX_ALERT = 29;

    private SQLiteDatabase db;
    private MonitoringAppDbHelper mDbHelper;

    public DataBDD(Context context) {
        // Creating the database and its table
        mDbHelper = new MonitoringAppDbHelper(context);
    }


    public void open() {
        //opens the db for writing
        db = mDbHelper.getWritableDatabase();
    }

    public void close() {
        //closing the access to the db
        db.close();
    }

    public SQLiteDatabase getDb() {
        return db;
    }


    public long insertData(Data data) {
        //Creation of a ContentValues (working like a HashMap)
        ContentValues values = new ContentValues();
        values.put(DataEntry.COLUMN_PATIENT_NAME, data.getPatient_name());
        values.put(DataEntry.COLUMN_PATIENT_PROCESS_NUMBER, data.getPatient_process_number());
        values.put(DataEntry.COLUMN_DATE, data.getDate());
        values.put(DataEntry.COLUMN_MIN_HR, data.getMinimum_hr());
        values.put(DataEntry.COLUMN_MAX_HR, data.getMaximum_hr());
        values.put(DataEntry.COLUMN_AVERAGE_HR, data.getAverage_hr());
        values.put(DataEntry.COLUMN_MAX_RESP, data.getMaximum_resp());
        values.put(DataEntry.COLUMN_MIN_RESP, data.getMinimum_resp());
        values.put(DataEntry.COLUMN_AVERAGE_RESP, data.getAverage_resp());
        values.put(DataEntry.COLUMN_MIN_OXY, data.getMinimum_oxy());
        values.put(DataEntry.COLUMN_MAX_OXY, data.getMaximum_oxy());
        values.put(DataEntry.COLUMN_AVERAGE_OXY, data.getAverage_oxy());
        values.put(DataEntry.COLUMN_ECG_DESCRIPTION, data.getEcg_description());
        values.put(DataEntry.COLUMN_MIN_THORACIC_FC, data.getMinimum_thoracic_fluid_content());
        values.put(DataEntry.COLUMN_MAX_THORACIC_FC, data.getMaximum_thoracic_fluid_content());
        values.put(DataEntry.COLUMN_AVERAGE_THORACIC_FC, data.getAverage_thoracic_fluid_content());
        values.put(DataEntry.COLUMN_MIN_BODY_FC, data.getMinimum_body_fluid_content());
        values.put(DataEntry.COLUMN_MAX_BODY_FC, data.getMaximum_body_fluid_content());
        values.put(DataEntry.COLUMN_AVERAGE_BODY_FC, data.getAverage_body_fluid_content());
        values.put(DataEntry.COLUMN_MIN_SYSTOLIC_BP, data.getMinimum_systolic_blood_pressure());
        values.put(DataEntry.COLUMN_MAX_SYSTOLIC_BP, data.getMaximum_systolic_blood_pressure());
        values.put(DataEntry.COLUMN_AVERAGE_SYSTOLIC_BP, data.getAverage_systolic_blood_pressure());
        values.put(DataEntry.COLUMN_MIN_DIASTOLIC_BP, data.getMinimum_diastolic_blood_pressure());
        values.put(DataEntry.COLUMN_MAX_DIASTOLIC_BP, data.getMaximum_diastolic_blood_pressure());
        values.put(DataEntry.COLUMN_AVERAGE_DIASTOLIC_BP, data.getAverage_diastolic_blood_pressure());
        values.put(DataEntry.COLUMN_MIN_SODIUM, data.getMinimum_sodium_chloride());
        values.put(DataEntry.COLUMN_MAX_SODIUM, data.getMaximum_sodium_chloride());
        values.put(DataEntry.COLUMN_AVERAGE_SODIUM, data.getAverage_sodium_chloride());
        values.put(DataEntry.COLUMN_ALERT, data.getAlert());

        return db.insert(TABLE_DATA, null, values);
    }

    public int updateData(int id, Data data) {
        //The update works more or less like an insertion
        //you just need to specify the id of the data to update
        ContentValues values = new ContentValues();
        values.put(DataEntry.COLUMN_PATIENT_NAME, data.getPatient_name());
        values.put(DataEntry.COLUMN_PATIENT_PROCESS_NUMBER, data.getPatient_process_number());
        values.put(DataEntry.COLUMN_DATE, data.getDate());
        values.put(DataEntry.COLUMN_MIN_HR, data.getMinimum_hr());
        values.put(DataEntry.COLUMN_MAX_HR, data.getMaximum_hr());
        values.put(DataEntry.COLUMN_AVERAGE_HR, data.getAverage_hr());
        values.put(DataEntry.COLUMN_MAX_RESP, data.getMaximum_resp());
        values.put(DataEntry.COLUMN_MIN_RESP, data.getMinimum_resp());
        values.put(DataEntry.COLUMN_AVERAGE_RESP, data.getAverage_resp());
        values.put(DataEntry.COLUMN_MIN_OXY, data.getMinimum_oxy());
        values.put(DataEntry.COLUMN_MAX_OXY, data.getMaximum_oxy());
        values.put(DataEntry.COLUMN_AVERAGE_OXY, data.getAverage_oxy());
        values.put(DataEntry.COLUMN_ECG_DESCRIPTION, data.getEcg_description());
        values.put(DataEntry.COLUMN_MIN_THORACIC_FC, data.getMinimum_thoracic_fluid_content());
        values.put(DataEntry.COLUMN_MAX_THORACIC_FC, data.getMaximum_thoracic_fluid_content());
        values.put(DataEntry.COLUMN_AVERAGE_THORACIC_FC, data.getAverage_thoracic_fluid_content());
        values.put(DataEntry.COLUMN_MIN_BODY_FC, data.getMinimum_body_fluid_content());
        values.put(DataEntry.COLUMN_MAX_BODY_FC, data.getMaximum_body_fluid_content());
        values.put(DataEntry.COLUMN_AVERAGE_BODY_FC, data.getAverage_body_fluid_content());
        values.put(DataEntry.COLUMN_MIN_SYSTOLIC_BP, data.getMinimum_systolic_blood_pressure());
        values.put(DataEntry.COLUMN_MAX_SYSTOLIC_BP, data.getMaximum_systolic_blood_pressure());
        values.put(DataEntry.COLUMN_AVERAGE_SYSTOLIC_BP, data.getAverage_systolic_blood_pressure());
        values.put(DataEntry.COLUMN_MIN_DIASTOLIC_BP, data.getMinimum_diastolic_blood_pressure());
        values.put(DataEntry.COLUMN_MAX_DIASTOLIC_BP, data.getMaximum_diastolic_blood_pressure());
        values.put(DataEntry.COLUMN_AVERAGE_DIASTOLIC_BP, data.getAverage_diastolic_blood_pressure());
        values.put(DataEntry.COLUMN_MIN_SODIUM, data.getMinimum_sodium_chloride());
        values.put(DataEntry.COLUMN_MAX_SODIUM, data.getMaximum_sodium_chloride());
        values.put(DataEntry.COLUMN_AVERAGE_SODIUM, data.getAverage_sodium_chloride());
        values.put(DataEntry.COLUMN_ALERT, data.getAlert());
        return db.update(TABLE_DATA, values, DataEntry._ID + " = " + id, null);

    }

    public int removeDataWithID(int id) {
        //removing a patient with it's id
        return db.delete(TABLE_DATA, DataEntry._ID + " = " + id, null);
    }

    public Data getDataWithDate(String date) {
        //Gets in a cursor the data of the patient
        Cursor cursor = db.query(TABLE_DATA, new String[]{"*"}, DataEntry.COLUMN_DATE + " = \"" + date + "\"", null, null, null, null);
        Data data = new Data();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                data.setId(cursor.getInt(INDEX_ID));
                data.setPatient_name(cursor.getString(INDEX_PATIENT_NAME));
                data.setPatient_process_number(cursor.getInt(INDEX_PATIENT_PROCESS_NUMBER));
                data.setDate(cursor.getString(INDEX_DATE));
                data.setMinimum_hr(cursor.getInt(INDEX_MINIMUM_HR));
                data.setMaximum_hr(cursor.getInt(INDEX_MAXIMUM_HR));
                data.setAverage_hr(cursor.getInt(INDEX_AVERAGE_HR));
                data.setMinimum_resp(cursor.getInt(INDEX_MINIMUM_RESP));
                data.setMaximum_resp(cursor.getInt(INDEX_MAXIMUM_RESP));
                data.setAverage_resp(cursor.getInt(INDEX_AVERAGE_RESP));
                data.setMinimum_oxy(cursor.getInt(INDEX_MINIMUM_OXY));
                data.setMaximum_oxy(cursor.getInt(INDEX_MAXIMUM_OXY));
                data.setAverage_oxy(cursor.getInt(INDEX_AVERAGE_OXY));
                data.setEcg_description(cursor.getString(INDEX_ECG_DESCRIPTION));
                data.setMinimum_thoracic_fluid_content(cursor.getInt(INDEX_MINIMUM_THORACIC_FLUID_CONTENT));
                data.setMaximum_thoracic_fluid_content(cursor.getInt(INDEX_MAXIMUM_THORACIC_FLUID_CONTENT));
                data.setAverage_thoracic_fluid_content(cursor.getInt(INDEX_AVERAGE_THORACIC_FLUID_CONTENT));
                data.setMinimum_body_fluid_content(cursor.getInt(INDEX_MINIMUM_BODY_FLUID_CONTENT));
                data.setMaximum_body_fluid_content(cursor.getInt(INDEX_MAXIMUM_BODY_FLUID_CONTENT));
                data.setAverage_body_fluid_content(cursor.getInt(INDEX_AVERAGE_BODY_FLUID_CONTENT));
                data.setMinimum_systolic_blood_pressure(cursor.getInt(INDEX_MINIMUM_SYSTOLIC_BLOOD_PRESSURE));
                data.setMaximum_systolic_blood_pressure(cursor.getInt(INDEX_MAXIMUM_SYSTOLIC_BLOOD_PRESSURE));
                data.setAverage_systolic_blood_pressure(cursor.getInt(INDEX_AVERAGE_SYSTOLIC_BLOOD_PRESSURE));
                data.setMinimum_diastolic_blood_pressure(cursor.getInt(INDEX_MINIMUM_DIASTOLIC_BLOOD_PRESSURE));
                data.setMaximum_diastolic_blood_pressure(cursor.getInt(INDEX_MAXIMUM_DIASTOLIC_BLOOD_PRESSURE));
                data.setAverage_diastolic_blood_pressure(cursor.getInt(INDEX_AVERAGE_DIASTOLIC_BLOOD_PRESSURE));
                data.setMinimum_sodium_chloride(cursor.getDouble(INDEX_MINIMUM_SODIUM_CHLORIDE));
                data.setMaximum_sodium_chloride(cursor.getDouble(INDEX_MAXIMUM_SODIUM_CHLORIDE));
                data.setAverage_sodium_chloride(cursor.getDouble(INDEX_AVERAGE_SODIUM_CHLORIDE));
                data.setAlert(cursor.getInt(INDEX_ALERT));
            }
        } else {
            System.out.println("Error when retrieving the data from the database");
        }
        cursor.close();
        return data;
    }

    public Data getLastMonth() {
        Calendar theEnd = Calendar.getInstance();
        Calendar theStart = (Calendar) theEnd.clone();

        theStart.add(Calendar.DAY_OF_MONTH, -30);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String start = dateFormat.format(theStart.getTime());
        String end = dateFormat.format(theEnd.getTime());

        // Now you have date boundaries in TEXT format
        Cursor cursor = db.query(TABLE_DATA, new String[]{"*"}, DataEntry.COLUMN_DATE + " BETWEEN '" + start + "' AND '" + end + "'", null, null, null, null);

        Data data = new Data();
        Data dataSum = new Data();

        System.out.println(cursor.getColumnCount());
        if (cursor != null) {
            while (cursor.moveToNext()) {
                dataSum.setMinimum_hr(dataSum.getMinimum_hr() + cursor.getInt(INDEX_MINIMUM_HR));
                dataSum.setMaximum_hr(dataSum.getMaximum_hr() + cursor.getInt(INDEX_MAXIMUM_HR));
                dataSum.setAverage_hr(dataSum.getAverage_hr() + cursor.getInt(INDEX_AVERAGE_HR));
                dataSum.setMinimum_resp(dataSum.getMinimum_resp() + cursor.getInt(INDEX_MINIMUM_RESP));
                dataSum.setMaximum_resp(dataSum.getMaximum_resp() + cursor.getInt(INDEX_MAXIMUM_RESP));
                dataSum.setAverage_resp(dataSum.getAverage_resp() + cursor.getInt(INDEX_AVERAGE_RESP));
                dataSum.setMinimum_oxy(dataSum.getMinimum_oxy() + cursor.getInt(INDEX_MINIMUM_OXY));
                dataSum.setMaximum_oxy(dataSum.getMaximum_oxy() + cursor.getInt(INDEX_MAXIMUM_OXY));
                dataSum.setAverage_oxy(dataSum.getAverage_oxy() + cursor.getInt(INDEX_AVERAGE_OXY));
                dataSum.setMinimum_thoracic_fluid_content(dataSum.getMinimum_thoracic_fluid_content() + cursor.getInt(INDEX_MINIMUM_THORACIC_FLUID_CONTENT));
                dataSum.setMaximum_thoracic_fluid_content(dataSum.getMaximum_thoracic_fluid_content() + cursor.getInt(INDEX_MAXIMUM_THORACIC_FLUID_CONTENT));
                dataSum.setAverage_thoracic_fluid_content(dataSum.getAverage_thoracic_fluid_content() + cursor.getInt(INDEX_AVERAGE_THORACIC_FLUID_CONTENT));
                dataSum.setMinimum_body_fluid_content(dataSum.getMinimum_body_fluid_content() + cursor.getInt(INDEX_MINIMUM_BODY_FLUID_CONTENT));
                dataSum.setMaximum_body_fluid_content(dataSum.getMaximum_body_fluid_content() + cursor.getInt(INDEX_MAXIMUM_BODY_FLUID_CONTENT));
                dataSum.setAverage_body_fluid_content(dataSum.getAverage_body_fluid_content() + cursor.getInt(INDEX_AVERAGE_BODY_FLUID_CONTENT));
                dataSum.setMinimum_systolic_blood_pressure(dataSum.getMinimum_systolic_blood_pressure() + cursor.getInt(INDEX_MINIMUM_SYSTOLIC_BLOOD_PRESSURE));
                dataSum.setMaximum_systolic_blood_pressure(dataSum.getMaximum_systolic_blood_pressure() + cursor.getInt(INDEX_MAXIMUM_SYSTOLIC_BLOOD_PRESSURE));
                dataSum.setAverage_systolic_blood_pressure(dataSum.getAverage_systolic_blood_pressure() + cursor.getInt(INDEX_AVERAGE_SYSTOLIC_BLOOD_PRESSURE));
                dataSum.setMinimum_diastolic_blood_pressure(dataSum.getMinimum_diastolic_blood_pressure() + cursor.getInt(INDEX_MINIMUM_DIASTOLIC_BLOOD_PRESSURE));
                dataSum.setMaximum_diastolic_blood_pressure(dataSum.getMaximum_diastolic_blood_pressure() + cursor.getInt(INDEX_MAXIMUM_DIASTOLIC_BLOOD_PRESSURE));
                dataSum.setAverage_diastolic_blood_pressure(dataSum.getAverage_diastolic_blood_pressure() + cursor.getInt(INDEX_AVERAGE_DIASTOLIC_BLOOD_PRESSURE));
                dataSum.setMinimum_sodium_chloride(dataSum.getMinimum_sodium_chloride() + cursor.getDouble(INDEX_MINIMUM_SODIUM_CHLORIDE));
                dataSum.setMaximum_sodium_chloride(dataSum.getMaximum_sodium_chloride() + cursor.getDouble(INDEX_MAXIMUM_SODIUM_CHLORIDE));
                dataSum.setAverage_sodium_chloride(dataSum.getAverage_sodium_chloride() + cursor.getDouble(INDEX_AVERAGE_SODIUM_CHLORIDE));
                if (cursor.getInt(INDEX_ALERT) != 0) {
                    dataSum.setAlert(cursor.getInt(INDEX_ALERT));
                }
                //System.out.println(dataSum.toString());
            }
        } else {
            System.out.println("Error when retrieving the data from the database");
        }
        if (cursor.getCount() != 0) {
            data.setMinimum_hr(dataSum.getMinimum_hr() / cursor.getCount());
            data.setMaximum_hr(dataSum.getMaximum_hr() / cursor.getCount());
            data.setAverage_hr(dataSum.getAverage_hr() / cursor.getCount());
            data.setMinimum_resp(dataSum.getMinimum_resp() / cursor.getCount());
            data.setMaximum_resp(dataSum.getMaximum_resp() / cursor.getCount());
            data.setAverage_resp(dataSum.getAverage_resp() / cursor.getCount());
            data.setMinimum_oxy(dataSum.getMinimum_resp() / cursor.getCount());
            data.setMaximum_oxy(dataSum.getMaximum_oxy() / cursor.getCount());
            data.setAverage_oxy(dataSum.getAverage_oxy() / cursor.getCount());
            data.setMinimum_thoracic_fluid_content(dataSum.getMinimum_thoracic_fluid_content() / cursor.getCount());
            data.setMaximum_thoracic_fluid_content(dataSum.getMaximum_thoracic_fluid_content() / cursor.getCount());
            data.setAverage_thoracic_fluid_content(dataSum.getAverage_thoracic_fluid_content() / cursor.getCount());
            data.setMinimum_body_fluid_content(dataSum.getMinimum_body_fluid_content() / cursor.getCount());
            data.setMaximum_body_fluid_content(dataSum.getMaximum_body_fluid_content() / cursor.getCount());
            data.setAverage_body_fluid_content(dataSum.getAverage_body_fluid_content() / cursor.getCount());
            data.setMinimum_systolic_blood_pressure(dataSum.getMinimum_systolic_blood_pressure() / cursor.getCount());
            data.setMaximum_systolic_blood_pressure(dataSum.getMaximum_systolic_blood_pressure() / cursor.getCount());
            data.setAverage_systolic_blood_pressure(dataSum.getAverage_systolic_blood_pressure() / cursor.getCount());
            data.setMinimum_diastolic_blood_pressure(dataSum.getMinimum_diastolic_blood_pressure() / cursor.getCount());
            data.setMaximum_diastolic_blood_pressure(dataSum.getMaximum_diastolic_blood_pressure() / cursor.getCount());
            data.setAverage_diastolic_blood_pressure(dataSum.getAverage_diastolic_blood_pressure() / cursor.getCount());
            data.setMinimum_sodium_chloride(dataSum.getMinimum_sodium_chloride() / cursor.getCount());
            data.setMaximum_sodium_chloride(dataSum.getMaximum_sodium_chloride() / cursor.getCount());
            data.setAverage_sodium_chloride(dataSum.getAverage_sodium_chloride() / cursor.getCount());
            if (dataSum.getAlert() != 0) {
                data.setAlert(dataSum.getAlert());
            }
            cursor.close();
        } else {
            data.setMinimum_hr(0);
            data.setMaximum_hr(0);
            data.setAverage_hr(0);
            data.setMinimum_resp(0);
            data.setMaximum_resp(0);
            data.setAverage_resp(0);
            data.setMinimum_oxy(0);
            data.setMaximum_oxy(0);
            data.setAverage_oxy(0);
            data.setMinimum_thoracic_fluid_content(0);
            data.setMaximum_thoracic_fluid_content(0);
            data.setAverage_thoracic_fluid_content(0);
            data.setMinimum_body_fluid_content(0);
            data.setMaximum_body_fluid_content(0);
            data.setAverage_body_fluid_content(0);
            data.setMinimum_diastolic_blood_pressure(0);
            data.setMaximum_diastolic_blood_pressure(0);
            data.setAverage_diastolic_blood_pressure(0);
            data.setMinimum_systolic_blood_pressure(0);
            data.setMaximum_systolic_blood_pressure(0);
            data.setAverage_systolic_blood_pressure(0);
            data.setMinimum_sodium_chloride(0);
            data.setMaximum_sodium_chloride(0);
            data.setAverage_sodium_chloride(0);
            data.setAlert(0);
            cursor.close();
        }
        //System.out.println(data.toString());
        return data;
    }

    public Cursor getDataExport(String start, String end) {
        Cursor cursor = db.query(TABLE_DATA, new String[]{"*"}, null, null, null, null, null);
        return cursor;
    }

    public Data getDataForWeek(String startDate, String endDate) {

        //Cursor cursor = db.rawQuery("SELECT * FROM data WHERE timestamp BETWEEN "+start+" AND "+end);
        Cursor cursor = db.query(TABLE_DATA, new String[]{"*"}, DataEntry.COLUMN_DATE + " BETWEEN '" + startDate + "' AND '" + endDate + "'", null, null, null, null);

        Data data = new Data();
        Data dataSum = new Data();


        if (cursor != null) {
            while (cursor.moveToNext()) {
                dataSum.setMinimum_hr(dataSum.getMinimum_hr() + cursor.getInt(INDEX_MINIMUM_HR));
                dataSum.setMaximum_hr(dataSum.getMaximum_hr() + cursor.getInt(INDEX_MAXIMUM_HR));
                dataSum.setAverage_hr(dataSum.getAverage_hr() + cursor.getInt(INDEX_AVERAGE_HR));
                dataSum.setMinimum_resp(dataSum.getMinimum_resp() + cursor.getInt(INDEX_MINIMUM_RESP));
                dataSum.setMaximum_resp(dataSum.getMaximum_resp() + cursor.getInt(INDEX_MAXIMUM_RESP));
                dataSum.setAverage_resp(dataSum.getAverage_resp() + cursor.getInt(INDEX_AVERAGE_RESP));
                dataSum.setMinimum_oxy(dataSum.getMinimum_oxy() + cursor.getInt(INDEX_MINIMUM_OXY));
                dataSum.setMaximum_oxy(dataSum.getMaximum_oxy() + cursor.getInt(INDEX_MAXIMUM_OXY));
                dataSum.setAverage_oxy(dataSum.getAverage_oxy() + cursor.getInt(INDEX_AVERAGE_OXY));
                dataSum.setMinimum_thoracic_fluid_content(dataSum.getMinimum_thoracic_fluid_content() + cursor.getInt(INDEX_MINIMUM_THORACIC_FLUID_CONTENT));
                dataSum.setMaximum_thoracic_fluid_content(dataSum.getMaximum_thoracic_fluid_content() + cursor.getInt(INDEX_MAXIMUM_THORACIC_FLUID_CONTENT));
                dataSum.setAverage_thoracic_fluid_content(dataSum.getAverage_thoracic_fluid_content() + cursor.getInt(INDEX_AVERAGE_THORACIC_FLUID_CONTENT));
                dataSum.setMinimum_body_fluid_content(dataSum.getMinimum_body_fluid_content() + cursor.getInt(INDEX_MINIMUM_BODY_FLUID_CONTENT));
                dataSum.setMaximum_body_fluid_content(dataSum.getMaximum_body_fluid_content() + cursor.getInt(INDEX_MAXIMUM_BODY_FLUID_CONTENT));
                dataSum.setAverage_body_fluid_content(dataSum.getAverage_body_fluid_content() + cursor.getInt(INDEX_AVERAGE_BODY_FLUID_CONTENT));
                dataSum.setMinimum_systolic_blood_pressure(dataSum.getMinimum_systolic_blood_pressure() + cursor.getInt(INDEX_MINIMUM_SYSTOLIC_BLOOD_PRESSURE));
                dataSum.setMaximum_systolic_blood_pressure(dataSum.getMaximum_systolic_blood_pressure() + cursor.getInt(INDEX_MAXIMUM_SYSTOLIC_BLOOD_PRESSURE));
                dataSum.setAverage_systolic_blood_pressure(dataSum.getAverage_systolic_blood_pressure() + cursor.getInt(INDEX_AVERAGE_SYSTOLIC_BLOOD_PRESSURE));
                dataSum.setMinimum_diastolic_blood_pressure(dataSum.getMinimum_diastolic_blood_pressure() + cursor.getInt(INDEX_MINIMUM_DIASTOLIC_BLOOD_PRESSURE));
                dataSum.setMaximum_diastolic_blood_pressure(dataSum.getMaximum_diastolic_blood_pressure() + cursor.getInt(INDEX_MAXIMUM_DIASTOLIC_BLOOD_PRESSURE));
                dataSum.setAverage_diastolic_blood_pressure(dataSum.getAverage_diastolic_blood_pressure() + cursor.getInt(INDEX_AVERAGE_DIASTOLIC_BLOOD_PRESSURE));
                dataSum.setMinimum_sodium_chloride(dataSum.getMinimum_sodium_chloride() + cursor.getDouble(INDEX_MINIMUM_SODIUM_CHLORIDE));
                dataSum.setMaximum_sodium_chloride(dataSum.getMaximum_sodium_chloride() + cursor.getDouble(INDEX_MAXIMUM_SODIUM_CHLORIDE));
                dataSum.setAverage_sodium_chloride(dataSum.getAverage_sodium_chloride() + cursor.getDouble(INDEX_AVERAGE_SODIUM_CHLORIDE));
                if (cursor.getInt(INDEX_ALERT) != 0) {
                    dataSum.setAlert(cursor.getInt(INDEX_ALERT));
                }
                System.out.println("summary: " + dataSum.getMinimum_hr() + "   " + dataSum.getMaximum_hr() + "   " + dataSum.getAverage_hr());
            }
        } else {
            System.out.println("Error when retrieving the data from the database");
        }

        if (cursor.getCount() != 0) {
            data.setMinimum_hr(dataSum.getMinimum_hr() / cursor.getCount());
            data.setMaximum_hr(dataSum.getMaximum_hr() / cursor.getCount());
            data.setAverage_hr(dataSum.getAverage_hr() / cursor.getCount());
            data.setMinimum_resp(dataSum.getMinimum_resp() / cursor.getCount());
            data.setMaximum_resp(dataSum.getMaximum_resp() / cursor.getCount());
            data.setAverage_resp(dataSum.getAverage_resp() / cursor.getCount());
            data.setMinimum_oxy(dataSum.getMinimum_resp() / cursor.getCount());
            data.setMaximum_oxy(dataSum.getMaximum_oxy() / cursor.getCount());
            data.setAverage_oxy(dataSum.getAverage_oxy() / cursor.getCount());
            data.setMinimum_thoracic_fluid_content(dataSum.getMinimum_thoracic_fluid_content() / cursor.getCount());
            data.setMaximum_thoracic_fluid_content(dataSum.getMaximum_thoracic_fluid_content() / cursor.getCount());
            data.setAverage_thoracic_fluid_content(dataSum.getAverage_thoracic_fluid_content() / cursor.getCount());
            data.setMinimum_body_fluid_content(dataSum.getMinimum_body_fluid_content() / cursor.getCount());
            data.setMaximum_body_fluid_content(dataSum.getMaximum_body_fluid_content() / cursor.getCount());
            data.setAverage_body_fluid_content(dataSum.getAverage_body_fluid_content() / cursor.getCount());
            data.setMinimum_systolic_blood_pressure(dataSum.getMinimum_systolic_blood_pressure() / cursor.getCount());
            data.setMaximum_systolic_blood_pressure(dataSum.getMaximum_systolic_blood_pressure() / cursor.getCount());
            data.setAverage_systolic_blood_pressure(dataSum.getAverage_systolic_blood_pressure() / cursor.getCount());
            data.setMinimum_diastolic_blood_pressure(dataSum.getMinimum_diastolic_blood_pressure() / cursor.getCount());
            data.setMaximum_diastolic_blood_pressure(dataSum.getMaximum_diastolic_blood_pressure() / cursor.getCount());
            data.setAverage_diastolic_blood_pressure(dataSum.getAverage_diastolic_blood_pressure() / cursor.getCount());
            data.setMinimum_sodium_chloride(dataSum.getMinimum_sodium_chloride() / cursor.getCount());
            data.setMaximum_sodium_chloride(dataSum.getMaximum_sodium_chloride() / cursor.getCount());
            data.setAverage_sodium_chloride(dataSum.getAverage_sodium_chloride() / cursor.getCount());
            if (dataSum.getAlert() != 0) {
                data.setAlert(dataSum.getAlert());
            }
            cursor.close();
        } else {
            data.setMinimum_hr(0);
            data.setMaximum_hr(0);
            data.setAverage_hr(0);
            data.setMinimum_resp(0);
            data.setMaximum_resp(0);
            data.setAverage_resp(0);
            data.setMinimum_oxy(0);
            data.setMaximum_oxy(0);
            data.setAverage_oxy(0);
            data.setMinimum_thoracic_fluid_content(0);
            data.setMaximum_thoracic_fluid_content(0);
            data.setAverage_thoracic_fluid_content(0);
            data.setMinimum_body_fluid_content(0);
            data.setMaximum_body_fluid_content(0);
            data.setAverage_body_fluid_content(0);
            data.setMinimum_diastolic_blood_pressure(0);
            data.setMaximum_diastolic_blood_pressure(0);
            data.setAverage_diastolic_blood_pressure(0);
            data.setMinimum_systolic_blood_pressure(0);
            data.setMaximum_systolic_blood_pressure(0);
            data.setAverage_systolic_blood_pressure(0);
            data.setMinimum_sodium_chloride(0);
            data.setMaximum_sodium_chloride(0);
            data.setAverage_sodium_chloride(0);
            data.setAlert(0);
            cursor.close();
        }
        return data;
    }

    public Date[] getLastMonthAlarm() {

        Calendar theEnd = Calendar.getInstance();
        Calendar theStart = (Calendar) theEnd.clone();

        theStart.add(Calendar.DAY_OF_MONTH, -30);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String start = dateFormat.format(theStart.getTime());
        String end = dateFormat.format(theEnd.getTime());

        //Cursor cursor = db.rawQuery("SELECT * FROM data WHERE timestamp BETWEEN "+start+" AND "+end);
        Cursor cursor = db.query(TABLE_DATA, new String[]{"*"}, DataEntry.COLUMN_DATE + " BETWEEN '" + start + "' AND '" + end + "'", null, null, null, null);

        Date[] date = new Date[30];

        int idArray = 0;
        String dateArray = "";
        SimpleDateFormat dateForm = new SimpleDateFormat("yyyy/MM/dd");
        Date dateAr = new Date();

        String dateErr = "0000/00/00";
        Date dateError = new Date();

        if(cursor != null) {
            while (cursor.moveToNext()) {
                if (cursor.getInt(INDEX_ALERT) != 0) {
                    dateArray = cursor.getString(INDEX_DATE);
                    try {
                        dateAr = dateForm.parse(dateArray);
                        date[idArray] = dateAr;
                        idArray++;
                    } catch (Exception e) {
                        System.err.println("Format de date invalide. Usage : dd/MM/YYYY");
                        System.err.println(e.getMessage());
                    }
                }
            }
            if(date[0] == null){
                try {
                    dateError = dateForm.parse(dateErr);
                    date[0] = dateError;
                }
                catch (Exception e) {
                    System.err.println("Format de date invalide. Usage : dd/MM/YYYY");
                    System.err.println(e.getMessage());
                }
                return date;
            }
        }
        else{
            System.out.println("Error when retrieving the data from the database");
        }
        cursor.close();
        return date;
    }

}
