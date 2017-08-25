package com.example.android.monitoringapp.device;

import android.app.Application;
import android.content.Context;

/**
 * 
 * @author bgamecho
 *
 */
public class BITlog extends Application {

	public static String MAC="20:16:12:22:50:97";
	public static int SamplingRate=1000;
	public static int frameRate= 30;
	public static int[] channels = {0,1,2,3,4,5};

	public static boolean digitalOutputs = false;
	
    private static Context mContext;

    public static Context getContext() {
        return mContext;
    }

    public static void setContext(Context mContext) {
        BITlog.mContext = mContext;
    }

}
