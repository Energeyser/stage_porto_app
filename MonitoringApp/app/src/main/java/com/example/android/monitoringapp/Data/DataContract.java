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
         * The date, format : "yyyy/MM/dd"
         *
         * Type: String
         */
        public final static String COLUMN_DATE = "date";

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
         * Minimum peripheral oxygen saturation.
         *
         * Type: INTEGER
         */
        public final static String COLUMN_MIN_OXY = "minimum_oxy";

        /**
         * Maximum peripheral oxygen saturation.
         *
         * Type: INTEGER
         */
        public final static String COLUMN_MAX_OXY = "maximum_oxy";

        /**
         * Average peripheral oxygen saturation.
         *
         * Type: INTEGER
         */
        public final static String COLUMN_AVERAGE_OXY = "average_oxy";

        /**
         * Description of the ECG.
         *
         * Type: STRING
         */
        public final static String COLUMN_ECG_DESCRIPTION = "ecg_description";

        /**
         * Minimum intra-thoracic fluid content for the day.
         *
         * Type: INTEGER
         */
        public final static String COLUMN_MIN_THORACIC_FC = "minimum_thoracic_fluid_content";

        /**
         * Maximum intra-thoracic fluid content for the day.
         *
         * Type: INTEGER
         */
        public final static String COLUMN_MAX_THORACIC_FC = "maximum_thoracic_fluid_content";

        /**
         * Average intra-thoracic fluid content for the day.
         *
         * Type: INTEGER
         */
        public final static String COLUMN_AVERAGE_THORACIC_FC = "average_thoracic_fluid_content";

        /**
         * Minimum whole body fluid content for the day.
         *
         * Type: INTEGER
         */
        public final static String COLUMN_MIN_BODY_FC = "minimum_body_fluid_content";

        /**
         * Maximum whole body fluid content for the day.
         *
         * Type: INTEGER
         */
        public final static String COLUMN_MAX_BODY_FC = "maximum_body_fluid_content";

        /**
         * Average whole body fluid content for the day.
         *
         * Type: INTEGER
         */
        public final static String COLUMN_AVERAGE_BODY_FC = "average_body_fluid_content";

        /**
         * Minimum systolic blood pressure for the day.
         *
         * Type: STRING
         */
        public final static String COLUMN_MIN_SYSTOLIC_BP = "minimum_systolic_blood_pressure";

        /**
         * Maximum systolic blood pressure for the day.
         *
         * Type: STRING
         */
        public final static String COLUMN_MAX_SYSTOLIC_BP = "maximum_systolic_blood_pressure";

        /**
         * Average systolic blood pressure for the day.
         *
         * Type: STRING
         */
        public final static String COLUMN_AVERAGE_SYSTOLIC_BP = "average_systolic_blood_pressure";

        /**
         * Minimum diastolic blood pressure for the day.
         *
         * Type: STRING
         */
        public final static String COLUMN_MIN_DIASTOLIC_BP = "minimum_diastolic_blood_pressure";

        /**
         * Maximum diastolic blood pressure for the day.
         *
         * Type: STRING
         */
        public final static String COLUMN_MAX_DIASTOLIC_BP = "maximum_diastolic_blood_pressure";

        /**
         * Average diastolic blood pressure for the day.
         *
         * Type: STRING
         */
        public final static String COLUMN_AVERAGE_DIASTOLIC_BP = "average_diastolic_blood_pressure";

        /**
         * Minimum sodium chloride on the skin for the day.
         *
         * Type: DOUBLE
         */
        public final static String COLUMN_MIN_SODIUM = "minimum_sodium_chloride";

        /**
         * Maximum sodium chloride on the skin for the day.
         *
         * Type: DOUBLE
         */
        public final static String COLUMN_MAX_SODIUM = "maximum_sodium_chloride";

        /**
         * Average sodium chloride on the skin for the day.
         *
         * Type: DOUBLE
         */
        public final static String COLUMN_AVERAGE_SODIUM = "average_sodium_chloride";

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