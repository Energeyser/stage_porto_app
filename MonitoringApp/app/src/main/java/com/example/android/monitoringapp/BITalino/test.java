package com.example.android.monitoringapp.BITalino;
import java.util.Vector;

import javax.bluetooth.RemoteDevice;

import com.example.android.monitoringapp.BITalino.*;


/**
 * Created by axel- on 07/08/2017.
 */

public class test {

    BITalino device = new BITalino();

    // find devices
    Vector<RemoteDevice> devices = device.findDevices();
		System.out.println(devices);

    // connect to BITalino device
    String macAddress = "20:16:12:22:50:97";
    int SamplingRate = 1000;
		device.open(macAddress,SamplingRate);

    //get BITalino version
    String versionName = device.version();

    // start acquisition on analog channels 0 and 4
    int[] aChannels = {0,1,2,3,4,5};
    int frameCounter = 0;
    Frame[][] oneMinData = {{},{},{},{},{},{},{}};	// multidimensional array

		device.start(aChannels);

    //while(true) {

    Frame[] temp1 = null;
    Frame[] temp2 = null;

    //read 10000 samples reprensents 10s of measures at 1000 Hz
    dataAcquired = device.read(10000);


			while(frameCounter < 10000) {

        System.out.println(dataAcquired[frameCounter].getChannel2());
        // Measure Process


        frameCounter++;

    }

    frameCounter = 0;	// counter reset

    /// Store 1 min of values, 6 paquets of 10s
    /// Total: 60000 values

    temp1 = oneMinData[0]; // Save the paquet
    oneMinData[0] = dataAcquired;
    temp2 = oneMinData[1];
    oneMinData[1] = temp1;
    temp1 = oneMinData[2];
    oneMinData[2] = temp2;
    temp2 = oneMinData[3];
    oneMinData[3] = temp1;
    temp1 = oneMinData[4];
    oneMinData[4] = temp2;
    temp2 = oneMinData[5];
    oneMinData[5] = temp1;

    //}

    // trigger digital outputs
    //int[] digital = {0,0,1,1};	/// On %BITalino (r)evolution, the array contents are: I1 I2 O1 O2.
    //device.trigger(digital);

    // stop acquisition
    //device.stop();

    //close bluetooth connection
    //device.close();

}
}
