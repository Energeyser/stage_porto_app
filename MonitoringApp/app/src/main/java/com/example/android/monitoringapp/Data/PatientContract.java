package com.example.android.monitoringapp.Data;

import android.provider.BaseColumns;

/**
 * API Contract for the Monitoring app.
 */
public final class PatientContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private PatientContract() {}

    /**
     * Inner class that defines constant values for the patient database table.
     * There is only one row in the database which corresponds to the patient.
     */
    public static final class PatientEntry implements BaseColumns {

        /** Name of database table for patient */
        public final static String TABLE_NAME = "patient";

        /**
         * Unique ID number for the patient (only for use in the database table).
         *
         * Type: INTEGER
         */
        public final static String _ID = BaseColumns._ID;

        /**
         * Name of the patient.
         *
         * Type: TEXT
         */
        public final static String COLUMN_PATIENT_NAME ="name";

        /**
         * Process number of the patient.
         *
         * Type: INT
         */
        public final static String COLUMN_PATIENT_PROCESS_NUMBER ="processNumber";

        /**
         * Phone of the patient.
         *
         * Type: TEXT
         */
        public final static String COLUMN_PATIENT_PHONE = "phone";

        /**
         * Address of the patient.
         *
         * Type: TEXT
         */
        public final static String COLUMN_PATIENT_ADDRESS = "address";

        /**
         * Name of the Persone Of Trust (POT).
         *
         * Type: TEXT
         */
        public final static String COLUMN_PATIENT_POT_NAME = "pot_name";

        /**
         * Phone of the Persone Of Trust (POT).
         *
         * Type: TEXT
         */
        public final static String COLUMN_PATIENT_POT_PHONE = "pot_phone";


    }

}