package com.example.android.monitoringapp.BITalino;

import java.util.Vector;
import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;
/**
 * Created by axel- on 07/08/2017.
 */

public class DeviceDiscoverer implements DiscoveryListener{
    public Vector<RemoteDevice> remoteDevices = new Vector();
    DiscoveryAgent discoveryAgent;
    public String deviceName;
    String inqStatus = null;

    public DeviceDiscoverer() {
        try {
            LocalDevice e = LocalDevice.getLocalDevice();
            System.err.println(LocalDevice.getLocalDevice());
            this.discoveryAgent = e.getDiscoveryAgent();
            this.discoveryAgent.startInquiry(10390323, this);
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }

    public void deviceDiscovered(RemoteDevice remoteDevice, DeviceClass cod) {
        try {
            this.deviceName = remoteDevice.getFriendlyName(false);
            if(this.deviceName.equalsIgnoreCase("bitalino")) {
                this.remoteDevices.addElement(remoteDevice);
            }
        } catch (Exception var4) {
            var4.printStackTrace();
        }

    }

    public void inquiryCompleted(int discType) {
        if(discType == 0) {
            this.inqStatus = "Scan completed.";
        } else if(discType == 5) {
            this.inqStatus = "Scan terminated.";
        } else if(discType == 7) {
            this.inqStatus = "Scan with errors.";
        }

    }

    public void servicesDiscovered(int transID, ServiceRecord[] servRecord) {
    }

    public void serviceSearchCompleted(int transID, int respCode) {
    }
}
