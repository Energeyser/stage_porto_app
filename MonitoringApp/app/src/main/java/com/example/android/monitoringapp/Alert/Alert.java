package com.example.android.monitoringapp.Alert;

import android.content.Intent;
import android.telephony.SmsManager;
import android.widget.Toast;

/**
 * Created by axel- on 31/07/2017.
 */

public class Alert {

    public boolean SendMessage(String number, String textMessage) {
        try {
            SmsManager smsManager =  SmsManager.getDefault();
            smsManager.sendTextMessage(number, null, textMessage, null, null);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
