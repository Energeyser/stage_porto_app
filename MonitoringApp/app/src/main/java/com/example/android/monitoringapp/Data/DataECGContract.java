package com.example.android.monitoringapp.Data;
import android.provider.BaseColumns;

/**
 * Created by AG on 30/08/2017.
 */

public class DataECGContract {

    /**
     * Inner class that defines constant values for the data database table.
     * The table contains the following tables :
     * Patient's name/Patient's process number/Month/Day/Mensual/minimum HR/maximum HR/average HR/
     * minimum respiratory rate/maximum respiratory rate/average respiratory rate/ECG description/
     * intra-thoracic fluid content/whole-body fluid content/blood pressure/sodium cloride/alerts sent to doctor
     */
    public static final class DataECGEntry implements BaseColumns {

        /**
         * Name of database table for data
         */
        public final static String TABLE_NAME = "dataECG";

        /**
         * Unique ID number for the entry (only for use in the database data).
         * <p>
         * Type: INTEGER
         */
        public final static String _ID = BaseColumns._ID;

        /**
         * Value of the ECG
         * <p>
         * Type:INTEGER
         */
        public final static String COLUMN_VALUE_ECG = "value_ECG";

        /**
         * Date of the arryhtmia
         * <p>
         * Type:STRING
         */
        public final static String COLUMN_DATE_ARRYTHMIA = "date_arrhythmia";

        /**
         * Hour of the arryhtmia
         * <p>
         * Type:STRING
         */
        public final static String COLUMN_HOUR_ARRYTHMIA = "hour_arrhythmia";

    }
}
