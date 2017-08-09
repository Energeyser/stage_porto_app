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
 */

public class DataBDD {


    private static final String TABLE_DATA = DataEntry.TABLE_NAME;


    private static final int INDEX_ID = 0;
    private static final int INDEX_PATIENT_NAME = 1;
    private static final int INDEX_PATIENT_PROCESS_NUMBER = 2;
    private static final int INDEX_DATE = 3;
    private static final int INDEX_MENSAL = 4;
    private static final int INDEX_MINIMUM_HR = 5;
    private static final int INDEX_MAXIMUM_HR = 6;
    private static final int INDEX_AVERAGE_HR = 7;
    private static final int INDEX_MINIMUM_RESP = 8;
    private static final int INDEX_MAXIMUM_RESP = 9;
    private static final int INDEX_AVERAGE_RESP = 10;
    private static final int INDEX_ECG_DESCRIPTION = 11;
    private static final int INDEX_THORACIC_FRLUID_CONTENT = 12;
    private static final int INDEX_BODY_FLUID_CONTENT = 13;
    private static final int INDEX_BLOOD_PRESSURE = 14;
    private static final int INDEX_SODIUM_CHLORIDE = 15;
    private static final int INDEX_ALERT = 16;

    private SQLiteDatabase db;
    private MonitoringAppDbHelper mDbHelper;

    public DataBDD(Context context){
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

    public long insertData(Data data){
        //Creation of a ContentValues (working like a HashMap)
        ContentValues values = new ContentValues();
        values.put(DataEntry.COLUMN_PATIENT_NAME, data.getPatient_name());
        values.put(DataEntry.COLUMN_PATIENT_PROCESS_NUMBER,data.getPatient_process_number());
        values.put(DataEntry.COLUMN_DATE,data.getDate());
        values.put(DataEntry.COLUMN_MENSAL, data.getMensal());
        values.put(DataEntry.COLUMN_MIN_HR, data.getMinimum_hr());
        values.put(DataEntry.COLUMN_MAX_HR, data.getMaximum_hr());
        values.put(DataEntry.COLUMN_AVERAGE_HR, data.getAverage_hr());
        values.put(DataEntry.COLUMN_MAX_RESP, data.getMaximum_resp());
        values.put(DataEntry.COLUMN_MIN_RESP, data.getMinimum_resp());
        values.put(DataEntry.COLUMN_AVERAGE_RESP, data.getAverage_resp());
        values.put(DataEntry.COLUMN_ECG_DESCRIPTION, data.getEcg_description());
        values.put(DataEntry.COLUMN_THORACIC_FC, data.getThoracic_fluid_content());
        values.put(DataEntry.COLUMN_BODY_FC, data.getBody_fluid_content());
        values.put(DataEntry.COLUMN_BLOOD_PRESSURE, data.getBlood_pressure());
        values.put(DataEntry.COLUMN_SODIUM, data.getSodium_chloride());
        values.put(DataEntry.COLUMN_ALERT, data.getAlert());

        return db.insert(TABLE_DATA,null,values);
    }

    public int updateData(int id,Data data){
        //The update works more or less like an insertion
        //you just need to specify the id of the data to update
        ContentValues values = new ContentValues();
        values.put(DataEntry.COLUMN_PATIENT_NAME, data.getPatient_name());
        values.put(DataEntry.COLUMN_PATIENT_PROCESS_NUMBER,data.getPatient_process_number());
        values.put(DataEntry.COLUMN_DATE,data.getDate());
        values.put(DataEntry.COLUMN_MENSAL, data.getMensal());
        values.put(DataEntry.COLUMN_MIN_HR, data.getMinimum_hr());
        values.put(DataEntry.COLUMN_MAX_HR, data.getMaximum_hr());
        values.put(DataEntry.COLUMN_AVERAGE_HR, data.getAverage_hr());
        values.put(DataEntry.COLUMN_MAX_RESP, data.getMaximum_resp());
        values.put(DataEntry.COLUMN_MIN_RESP, data.getMinimum_resp());
        values.put(DataEntry.COLUMN_AVERAGE_RESP, data.getAverage_resp());
        values.put(DataEntry.COLUMN_ECG_DESCRIPTION, data.getEcg_description());
        values.put(DataEntry.COLUMN_THORACIC_FC, data.getThoracic_fluid_content());
        values.put(DataEntry.COLUMN_BODY_FC, data.getBody_fluid_content());
        values.put(DataEntry.COLUMN_BLOOD_PRESSURE, data.getBlood_pressure());
        values.put(DataEntry.COLUMN_SODIUM, data.getSodium_chloride());
        values.put(DataEntry.COLUMN_ALERT, data.getAlert());
        return db.update(TABLE_DATA, values, DataEntry._ID + " = " +id, null);

    }

    public int removeDataWithID(int id){
        //removing a patient with it's id
        return db.delete(TABLE_DATA, DataEntry._ID + " = " +id, null);
    }

    public Data getDataWithDate(String date){
        //Gets in a cursor the data of the patient
        //Cursor cursor = db.query(TABLE_DATA, new String[] {"*"}, DataEntry.COLUMN_DATE + " = '" + date +"'", null, null, null, null);
        Cursor cursor = db.query(TABLE_DATA, new String[] {"*"},DataEntry.COLUMN_DATE +" = \""+date+"\"", null, null, null, null);
        Data data = new Data();
        if(cursor != null)
        {
            if (cursor.moveToFirst()) {
                data.setId(cursor.getInt(INDEX_ID));
                data.setPatient_name(cursor.getString(INDEX_PATIENT_NAME));
                data.setPatient_process_number(cursor.getInt(INDEX_PATIENT_PROCESS_NUMBER));
                data.setDate(cursor.getString(INDEX_DATE));
                data.setMensal(cursor.getInt(INDEX_MENSAL));
                data.setMinimum_hr(cursor.getInt(INDEX_MINIMUM_HR));
                data.setMaximum_hr(cursor.getInt(INDEX_MAXIMUM_HR));
                data.setAverage_hr(cursor.getInt(INDEX_AVERAGE_HR));
                data.setMinimum_resp(cursor.getInt(INDEX_MINIMUM_RESP));
                data.setMaximum_resp(cursor.getInt(INDEX_MAXIMUM_RESP));
                data.setAverage_resp(cursor.getInt(INDEX_AVERAGE_RESP));
                data.setEcg_description(cursor.getString(INDEX_ECG_DESCRIPTION));
                data.setThoracic_fluid_content(cursor.getInt(INDEX_THORACIC_FRLUID_CONTENT));
                data.setBody_fluid_content(cursor.getInt(INDEX_BODY_FLUID_CONTENT));
                data.setBlood_pressure(cursor.getString(INDEX_BLOOD_PRESSURE));
                data.setSodium_chloride(cursor.getInt(INDEX_SODIUM_CHLORIDE));
                data.setAlert(cursor.getInt(INDEX_ALERT));
            }
        } else{
            System.out.println("Error when retrieving the data from the database");
        }
        cursor.close();
        return data;
    }

    public Data getLastMonth(){
       // Cursor cursor = db.query(TABLE_DATA, new String[] {"*"}, DataEntry.COLUMN_DATE +" BETWEEN datetime('now', 'localtime', 'start of month') AND datetime('now', 'localtime');", null, null, null, null);
        //SELECT * FROM data WHERE date BETWEEN datetime('now', 'start of month') AND datetime('now', 'localtime');

        Calendar theEnd = Calendar.getInstance();
        Calendar theStart = (Calendar) theEnd.clone();

        theStart.add(Calendar.DAY_OF_MONTH, -30);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String start = dateFormat.format(theStart.getTime());
        String end = dateFormat.format(theEnd.getTime());

        // Now you have date boundaries in TEXT format

        //Cursor cursor = db.rawQuery("SELECT * FROM data WHERE timestamp BETWEEN "+start+" AND "+end);
        Cursor cursor = db.query(TABLE_DATA, new String[] {"*"}, DataEntry.COLUMN_DATE +" BETWEEN '"+start+"' AND '"+end+"'", null, null, null, null);

        Data data = new Data();
        Data dataSum = new Data();


        if(cursor != null)
        {
            while (cursor.moveToNext()) {
                    dataSum.setMinimum_hr(dataSum.getMinimum_hr()+cursor.getInt(INDEX_MINIMUM_HR));
                    dataSum.setMaximum_hr(dataSum.getMaximum_hr()+cursor.getInt(INDEX_MAXIMUM_HR));
                    dataSum.setAverage_hr(dataSum.getAverage_hr()+cursor.getInt(INDEX_AVERAGE_HR));
                    dataSum.setMinimum_resp(dataSum.getMinimum_resp()+cursor.getInt(INDEX_MINIMUM_RESP));
                    dataSum.setMaximum_resp(dataSum.getMaximum_resp()+cursor.getInt(INDEX_MAXIMUM_RESP));
                    dataSum.setAverage_resp(dataSum.getAverage_resp()+cursor.getInt(INDEX_AVERAGE_RESP));
                    dataSum.setThoracic_fluid_content(dataSum.getThoracic_fluid_content()+cursor.getInt(INDEX_THORACIC_FRLUID_CONTENT));
                    dataSum.setBody_fluid_content(dataSum.getBody_fluid_content()+cursor.getInt(INDEX_BODY_FLUID_CONTENT));
                    dataSum.setSodium_chloride(dataSum.getSodium_chloride()+cursor.getInt(INDEX_SODIUM_CHLORIDE));
                    if(cursor.getInt(INDEX_ALERT) != 0){
                        dataSum.setAlert(cursor.getInt(INDEX_ALERT));
                    }
                    //System.out.println(dataSum.toString());
            }
        } else{
            System.out.println("Error when retrieving the data from the database");
        }
        data.setMinimum_hr(dataSum.getMinimum_hr()/cursor.getCount());
        data.setMaximum_hr(dataSum.getMaximum_hr()/cursor.getCount());
        data.setAverage_hr(dataSum.getAverage_hr()/cursor.getCount());
        data.setMinimum_resp(dataSum.getMinimum_resp()/cursor.getCount());
        data.setMaximum_resp(dataSum.getMaximum_resp()/cursor.getCount());
        data.setAverage_resp(dataSum.getAverage_resp()/cursor.getCount());
        data.setThoracic_fluid_content(dataSum.getThoracic_fluid_content()/cursor.getCount());
        data.setBody_fluid_content(dataSum.getBody_fluid_content()/cursor.getCount());
        data.setSodium_chloride(dataSum.getSodium_chloride()/cursor.getCount());
        if(dataSum.getAlert() != 0){
            data.setAlert(dataSum.getAlert());
        }
        cursor.close();
        //System.out.println(data.toString());
        return data;
    }

    public Cursor getDataExport(String start, String end){
        Cursor cursor = db.query(TABLE_DATA, new String[] {"*"},null, null, null, null, null);
        return cursor;
    }

    public Data getDataForWeek(String startDate, String endDate){

        //Cursor cursor = db.rawQuery("SELECT * FROM data WHERE timestamp BETWEEN "+start+" AND "+end);
        Cursor cursor = db.query(TABLE_DATA, new String[] {"*"}, DataEntry.COLUMN_DATE +" BETWEEN '"+startDate+"' AND '"+endDate+"'", null, null, null, null);

        Data data = new Data();
        Data dataSum = new Data();


        if(cursor != null)
        {
            while (cursor.moveToNext()) {
                dataSum.setMinimum_hr(dataSum.getMinimum_hr()+cursor.getInt(INDEX_MINIMUM_HR));
                dataSum.setMaximum_hr(dataSum.getMaximum_hr()+cursor.getInt(INDEX_MAXIMUM_HR));
                dataSum.setAverage_hr(dataSum.getAverage_hr()+cursor.getInt(INDEX_AVERAGE_HR));
                dataSum.setMinimum_resp(dataSum.getMinimum_resp()+cursor.getInt(INDEX_MINIMUM_RESP));
                dataSum.setMaximum_resp(dataSum.getMaximum_resp()+cursor.getInt(INDEX_MAXIMUM_RESP));
                dataSum.setAverage_resp(dataSum.getAverage_resp()+cursor.getInt(INDEX_AVERAGE_RESP));
                dataSum.setThoracic_fluid_content(dataSum.getThoracic_fluid_content()+cursor.getInt(INDEX_THORACIC_FRLUID_CONTENT));
                dataSum.setBody_fluid_content(dataSum.getBody_fluid_content()+cursor.getInt(INDEX_BODY_FLUID_CONTENT));
                dataSum.setSodium_chloride(dataSum.getSodium_chloride()+cursor.getInt(INDEX_SODIUM_CHLORIDE));
                if(cursor.getInt(INDEX_ALERT) != 0){
                    dataSum.setAlert(cursor.getInt(INDEX_ALERT));
                }
                System.out.println("summaryyyyyyyyyy: " + dataSum.getMinimum_hr() + "   " + dataSum.getMaximum_hr() +"   "+ dataSum.getAverage_hr());
            }
        } else{
            System.out.println("Error when retrieving the data from the database");
        }

       if(cursor.getCount() !=0){
           data.setMinimum_hr(dataSum.getMinimum_hr()/cursor.getCount());
           data.setMaximum_hr(dataSum.getMaximum_hr()/cursor.getCount());
           data.setAverage_hr(dataSum.getAverage_hr()/cursor.getCount());
           data.setMinimum_resp(dataSum.getMinimum_resp()/cursor.getCount());
           data.setMaximum_resp(dataSum.getMaximum_resp()/cursor.getCount());
           data.setAverage_resp(dataSum.getAverage_resp()/cursor.getCount());
           data.setThoracic_fluid_content(dataSum.getThoracic_fluid_content()/cursor.getCount());
           data.setBody_fluid_content(dataSum.getBody_fluid_content()/cursor.getCount());
           data.setSodium_chloride(dataSum.getSodium_chloride()/cursor.getCount());
           if(dataSum.getAlert() != 0){
               data.setAlert(dataSum.getAlert());
           }
           cursor.close();
       }
       else{
           data.setMinimum_hr(0);
           data.setMaximum_hr(0);
           data.setAverage_hr(0);
           data.setMinimum_resp(0);
           data.setMaximum_resp(0);
           data.setAverage_resp(0);
           data.setThoracic_fluid_content(0);
           data.setBody_fluid_content(0);
           data.setSodium_chloride(0);
           data.setAlert(0);
           cursor.close();
       }

        System.out.println("aaaaaaaaaaaaaaaaaaa");
        System.out.println("ahahaha: " + data.toString());
        return data;
    }

    public Date[] getLastMonthAlarm(){

        Calendar theEnd = Calendar.getInstance();
        Calendar theStart = (Calendar) theEnd.clone();

        theStart.add(Calendar.DAY_OF_MONTH, -30);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String start = dateFormat.format(theStart.getTime());
        String end = dateFormat.format(theEnd.getTime());

        //Cursor cursor = db.rawQuery("SELECT * FROM data WHERE timestamp BETWEEN "+start+" AND "+end);
        Cursor cursor = db.query(TABLE_DATA, new String[] {"*"}, DataEntry.COLUMN_DATE +" BETWEEN '"+start+"' AND '"+end+"'", null, null, null, null);

        Date[] date = new Date[30];

        int idArray=0;
        String dateArray ="";
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
                System.out.println("Ca ne marche pas");
                try {
                    dateError = dateForm.parse(dateErr);
                    date[0] = dateError;
                    System.out.println("ahbh " + date[0].toString());
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
