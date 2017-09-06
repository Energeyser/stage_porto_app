package com.example.android.monitoringapp.Data;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.example.android.monitoringapp.Data.DataECGContract.DataECGEntry;

/**
 * Created by AG on 30/08/2017.
 */

public class DataECGBDD {

    private static final String TABLE_DATA_ECG = DataECGEntry.TABLE_NAME;

    private static final int INDEX_ID = 0;
    private static final int INDEX_VALUE_ECG = 1;
    private static final int INDEX_DATE_ARRYTHMIA = 2;
    private static final int INDEX_HOUR_ARRYTHMIA = 2;


    private SQLiteDatabase db;
    private MonitoringAppDbHelper mDbHelper;

    public DataECGBDD(Context context) {
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

    public long insertDataECG(DataECG dataECG) {
        //Creation of a ContentValues (working like a HashMap)
        ContentValues values = new ContentValues();
        values.put(DataECGEntry.COLUMN_VALUE_ECG, dataECG.getValue_ECG());
        values.put(DataECGEntry.COLUMN_DATE_ARRYTHMIA, dataECG.getDate_arrhythmia());
        values.put(DataECGEntry.COLUMN_HOUR_ARRYTHMIA, dataECG.getHour_arrhythmia());
        return db.insert(TABLE_DATA_ECG, null, values);
    }

    public int updateDataECG(int id, DataECG dataECG) {
        //The update works more or less like an insertion
        //you just need to specify the id of the data to update
        ContentValues values = new ContentValues();
        values.put(DataECGEntry.COLUMN_VALUE_ECG, dataECG.getValue_ECG());
        values.put(DataECGEntry.COLUMN_DATE_ARRYTHMIA, dataECG.getDate_arrhythmia());
        values.put(DataECGEntry.COLUMN_HOUR_ARRYTHMIA, dataECG.getHour_arrhythmia());
        return db.update(TABLE_DATA_ECG, values, DataECGEntry._ID + " = " + id, null);
    }

    public int removeDataECGWithID(int id) {
        //removing a patient with it's id
        return db.delete(TABLE_DATA_ECG, DataECGEntry._ID + " = " + id, null);
    }
}
