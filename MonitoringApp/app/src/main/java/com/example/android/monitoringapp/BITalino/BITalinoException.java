package com.example.android.monitoringapp.BITalino;

/**
 * Created by axel- on 07/08/2017.
 */

import com.example.android.monitoringapp.BITalino.BITalinoErrorTypes;

public class BITalinoException extends Exception{
    private static final long serialVersionUID = 1L;
    public int code;

    public BITalinoException(BITalinoErrorTypes errorType) {
        super(errorType.getName());
        this.code = errorType.getValue();
    }
}
