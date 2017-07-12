package com.example.android.monitoringapp.Data;

import android.provider.BaseColumns;

/**
 * API Contract for the Monitoring app.
 */
public final class DoctorContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private DoctorContract() {}

    /**
     * Inner class that defines constant values for the doctor database table.
     * There is only one row in the database which corresponds to the doctor.
     */
    public static final class DoctorEntry implements BaseColumns {

        /** Name of database table for doctor */
        public final static String TABLE_NAME = "doctor";

        /**
         * Unique ID number for the doctor (only for use in the database table).
         *
         * Type: INTEGER
         */
        public final static String _ID = BaseColumns._ID;

        /**
         * Name of the doctor.
         *
         * Type: TEXT
         */
        public final static String COLUMN_DOCTOR_NAME ="name";

        /**
         * Phone of the doctor.
         *
         * Type: TEXT
         */
        public final static String COLUMN_DOCTOR_PHONE = "phone";

        /**
         * Address of the doctor.
         *
         * Type: TEXT
         */
        public final static String COLUMN_DOCTOR_MAIL = "mail";

        /**
         * Name of the Persone Of Trust (POT).
         *
         * Type: TEXT
         */
        public final static String COLUMN_DOCTOR_USERNAME = "username";

        /**
         * Phone of the Persone Of Trust (POT).
         *
         * Type: TEXT
         */
        public final static String COLUMN_DOCTOR_PASSWORD = "password";


    }

}