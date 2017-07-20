package com.example.android.monitoringapp.Data;

import android.provider.BaseColumns;

/**
 * API Contract for the Monitoring app.
 */
public final class DataContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private DataContract() {}

    /**
     * Inner class that defines constant values for the data database table.
     * The table contains the following tables :
     * Patient's name/Patient's process number/Month/Day/Mensual/minimum HR/maximum HR/average HR/
     * minimum respiratory rate/maximum respiratory rate/average respiratory rate/ECG description/
     * intra-thoracic fluid content/whole-body fluid content/blood pressure/sodium cloride/alerts sent to doctor
     */
    public static final class DataEntry implements BaseColumns {

        /** Name of database table for data */
        public final static String TABLE_NAME = "data";

        /**
         * Unique ID number for the entry (only for use in the database data).
         *
         * Type: INTEGER
         */
        public final static String _ID = BaseColumns._ID;

        /**
         * Name of the patient.
         *
         * Type: TEXT
         */
        public final static String COLUMN_PATIENT_NAME ="patient_name";

        /**
         * Patient's process number.
         *
         * Type: INTEGER
         */
        public final static String COLUMN_PATIENT_PROCESS_NUMBER = "patient_process_number";

        /**
         * The date (Month).
         *
         * Type: INTEGER
         */
        public final static String COLUMN_MONTH = "month";

        /**
         * Date (Day).
         *
         * Type: INTEGER
         */
        public final static String COLUMN_DAY = "day";

        /**
         * Mensal (?).
         *
         * Type: INTEGER
         */
        public final static String COLUMN_MENSAL = "mensal";

        /**
         * Minimum heart rate for the day.
         *
         * Type: INTEGER
         */
        public final static String COLUMN_MIN_HR = "minimum_hr";

        /**
         * Maximum heart rate for the day.
         *
         * Type: INTEGER
         */
        public final static String COLUMN_MAX_HR = "maximum_hr";

        /**
         * Average heart rate for the day.
         *
         * Type: INTEGER
         */
        public final static String COLUMN_AVERAGE_HR = "average_hr";

        /**
         * Minimum respiratory rate for the day.
         *
         * Type: INTEGER
         */
        public final static String COLUMN_MIN_RESP = "minimum_resp";

        /**
         * Maximum respiratory rate for the day.
         *
         * Type: INTEGER
         */
        public final static String COLUMN_MAX_RESP = "maximum_resp";

        /**
         * Average respiratory rate for the day.
         *
         * Type: INTEGER
         */
        public final static String COLUMN_AVERAGE_RESP = "average_resp";

        /**
         * Description of the ECG.
         *
         * Type: STRING
         */
        public final static String COLUMN_ECG_DESCRIPTION = "ecg_description";

        /**
         * Intra-thoracic fluid content for the day.
         *
         * Type: INTEGER
         */
        public final static String COLUMN_THORACIC_FC = "thoracic_fluid_content";

        /**
         * Whole body fluid content for the day.
         *
         * Type: INTEGER
         */
        public final static String COLUMN_BODY_FC = "body_fluid_content";

        /**
         * Blood pressure for the day.
         *
         * Type: INTEGER
         */
        public final static String COLUMN_BLOOD_PRESSURE = "blood_pressure";

        /**
         * Sodium chloride on the skin for the day.
         *
         * Type: INTEGER
         */
        public final static String COLUMN_SODIUM = "sodium_chloride";

        /**
         * Alert to the doctor.
         * can take multiple values depending on the type of problem detected :
         * 1 : ventricular tachycardia
         * 2 : ventricular fibrillation
         * 3 : arterial fibrillation or flutter
         *
         * Type: INTEGER
         */
        public final static String COLUMN_ALERT = "alert";


    }

}