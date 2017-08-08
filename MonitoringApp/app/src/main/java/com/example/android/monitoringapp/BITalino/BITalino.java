package com.example.android.monitoringapp.BITalino;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.example.android.monitoringapp.BITalino.BITalinoErrorTypes;
import com.example.android.monitoringapp.BITalino.BITalinoException;
import com.example.android.monitoringapp.BITalino.DeviceDiscoverer;
import com.example.android.monitoringapp.BITalino.Frame;
import java.util.Vector;
import javax.bluetooth.RemoteDevice;
import javax.microedition.khronos.opengles.Connector;
import javax.microedition.io.StreamConnection;

/**
 * Created by axel- on 07/08/2017.
 */

public class BITalino {
    private int[] analogChannels = null;
    private int number_bytes = 0;
    private StreamConnection hSocket = null;
    private DataInputStream iStream = null;
    private DataOutputStream oStream = null;

    public BITalino() {
    }

    public Vector<RemoteDevice> findDevices() throws InterruptedException {
        DeviceDiscoverer finder = new DeviceDiscoverer();

        while(finder.inqStatus == null) {
            Thread.sleep(1000L);
        }

        finder.inqStatus = null;
        return finder.remoteDevices;
    }

    public void open(String macAdd) throws Throwable {
        this.open(macAdd, 1000);
    }

    public void open(String macAdd, int samplingRate) throws BITalinoException {
        if(macAdd.split(":").length > 1) {
            macAdd = macAdd.replace(":", "");
        }

        if(macAdd.length() != 12) {
            throw new BITalinoException(BITalinoErrorTypes.MACADDRESS_NOT_VALID);
        } else {
            try {
                this.hSocket = (StreamConnection)Connector.open("btspp://" + macAdd + ":1", 3);
                this.iStream = this.hSocket.openDataInputStream();
                this.oStream = this.hSocket.openDataOutputStream();
                Thread.sleep(2000L);
            } catch (Exception var5) {
                this.close();
            }

            try {
                byte e = 0;
                switch(samplingRate) {
                    case 1:
                        e = 0;
                        break;
                    case 10:
                        e = 1;
                        break;
                    case 100:
                        e = 2;
                        break;
                    case 1000:
                        e = 3;
                        break;
                    default:
                        this.close();
                }

                int e1 = e << 6 | 3;
                this.Write(e1);
            } catch (Exception var4) {
                throw new BITalinoException(BITalinoErrorTypes.SAMPLING_RATE_NOT_DEFINED);
            }
        }
    }

    public void start(int[] anChannels) throws Throwable {
        this.analogChannels = anChannels;
        if(this.analogChannels.length > 6 | this.analogChannels.length == 0) {
            throw new BITalinoException(BITalinoErrorTypes.ANALOG_CHANNELS_NOT_VALID);
        } else {
            int bit = 1;
            int[] var6 = anChannels;
            int var5 = anChannels.length;

            int nChannels;
            for(int e = 0; e < var5; ++e) {
                nChannels = var6[e];
                if(nChannels < 0 | nChannels > 5) {
                    throw new BITalinoException(BITalinoErrorTypes.ANALOG_CHANNELS_NOT_VALID);
                }

                bit |= 1 << 2 + nChannels;
            }

            nChannels = this.analogChannels.length;
            if(nChannels <= 4) {
                this.number_bytes = (int)Math.ceil((double)((12.0F + 10.0F * (float)nChannels) / 8.0F));
            } else {
                this.number_bytes = (int)Math.ceil((double)((52.0F + 6.0F * (float)(nChannels - 4)) / 8.0F));
            }

            try {
                this.Write(bit);
            } catch (Exception var7) {
                throw new BITalinoException(BITalinoErrorTypes.BT_DEVICE_NOT_CONNECTED);
            }
        }
    }

    public void stop() throws BITalinoException {
        try {
            this.Write(0);
        } catch (Exception var2) {
            throw new BITalinoException(BITalinoErrorTypes.BT_DEVICE_NOT_CONNECTED);
        }
    }

    public void close() throws BITalinoException {
        try {
            this.hSocket.close();
            this.iStream.close();
            this.oStream.close();
            this.hSocket = null;
            this.iStream = null;
            this.oStream = null;
        } catch (Exception var2) {
            throw new BITalinoException(BITalinoErrorTypes.BT_DEVICE_NOT_CONNECTED);
        }
    }

    public void Write(int data) throws BITalinoException {
        try {
            this.oStream.write(data);
            this.oStream.flush();
            Thread.sleep(1000L);
        } catch (Exception var3) {
            throw new BITalinoException(BITalinoErrorTypes.LOST_COMMUNICATION);
        }
    }

    public void battery(int value) throws BITalinoException {
        if(value >= 0 && value <= 63) {
            int Mode = value << 2;
            this.Write(Mode);
        } else {
            throw new BITalinoException(BITalinoErrorTypes.THRESHOLD_NOT_VALID);
        }
    }

    public void trigger(int[] digitalArray) throws BITalinoException {
        if(digitalArray.length != 4) {
            throw new BITalinoException(BITalinoErrorTypes.DIGITAL_CHANNELS_NOT_VALID);
        } else {
            int data = 3;

            for(int i = 0; i < digitalArray.length; ++i) {
                if(digitalArray[i] < 0 | digitalArray[i] > 1) {
                    throw new BITalinoException(BITalinoErrorTypes.DIGITAL_CHANNELS_NOT_VALID);
                }

                data |= digitalArray[i] << 2 + i;
            }

            this.Write(data);
        }
    }

    public String version() throws BITalinoException, IOException {
        try {
            this.Write(7);
            byte[] e = new byte[30];
            String test = "";
            int i = 0;

            do {
                this.iStream.read(e, i, 1);
                ++i;
                test = new String(new byte[]{e[i - 1]});
            } while(!test.equals("\n"));

            return new String(e);
        } catch (Exception var4) {
            throw new BITalinoException(BITalinoErrorTypes.LOST_COMMUNICATION);
        }
    }

    private Frame[] decode(byte[] buffer) throws IOException, BITalinoException {
        try {
            Frame[] e = new Frame[1];
            int j = this.number_bytes - 1;
            byte i = 0;
            boolean CRC = false;
            int x0 = 0;
            int x1 = 0;
            int x2 = 0;
            int x3 = 0;
            boolean out = false;
            boolean inp = false;
            int var15 = buffer[j - 0] & 15 & 255;

            for(int bytes = 0; bytes < this.number_bytes; ++bytes) {
                for(int bit = 7; bit > -1; --bit) {
                    int var17 = buffer[bytes] >> bit & 1;
                    if(bytes == this.number_bytes - 1 && bit < 4) {
                        var17 = 0;
                    }

                    int var16 = x3;
                    x3 = x2;
                    x2 = x1;
                    x1 = var16 ^ x0;
                    x0 = var17 ^ var16;
                }
            }

            if(var15 == (x3 << 3 | x2 << 2 | x1 << 1 | x0)) {
                e[i] = new Frame();
                e[i].seq = (short)((buffer[j - 0] & 240) >> 4) & 15;
                e[i].digital[0] = (short)(buffer[j - 1] >> 7 & 1);
                e[i].digital[1] = (short)(buffer[j - 1] >> 6 & 1);
                e[i].digital[2] = (short)(buffer[j - 1] >> 5 & 1);
                e[i].digital[3] = (short)(buffer[j - 1] >> 4 & 1);
                switch(this.analogChannels.length - 1) {
                    case 5:
                        e[i].analog[5] = (short)(buffer[j - 7] & 63);
                    case 4:
                        e[i].analog[4] = (short)(((buffer[j - 6] & 15) << 2 | (buffer[j - 7] & 192) >> 6) & 63);
                    case 3:
                        e[i].analog[3] = (short)(((buffer[j - 5] & 63) << 4 | (buffer[j - 6] & 240) >> 4) & 1023);
                    case 2:
                        e[i].analog[2] = (short)(((buffer[j - 4] & 255) << 2 | (buffer[j - 5] & 192) >> 6) & 1023);
                    case 1:
                        e[i].analog[1] = (short)(((buffer[j - 2] & 3) << 8 | buffer[j - 3] & 255) & 1023);
                    case 0:
                        e[i].analog[0] = (short)(((buffer[j - 1] & 15) << 6 | (buffer[j - 2] & 252) >> 2) & 1023);
                }
            } else {
                e[i] = new Frame();
                e[i].seq = -1;
            }

            return e;
        } catch (Exception var14) {
            throw new BITalinoException(BITalinoErrorTypes.INCORRECT_DECODE);
        }
    }

    public Frame[] read(int nSamples) throws BITalinoException {
        try {
            Frame[] e = new Frame[nSamples];
            byte[] buffer = new byte[this.number_bytes];
            byte[] bTemp = new byte[1];

            for(int i = 0; i < nSamples; ++i) {
                this.iStream.readFully(buffer, 0, this.number_bytes);
                Frame[] f = this.decode(buffer);
                if(f[0].seq != -1) {
                    e[i] = f[0];
                } else {
                    while(f[0].seq == -1) {
                        this.iStream.readFully(bTemp, 0, 1);

                        for(int j = this.number_bytes - 2; j >= 0; --j) {
                            buffer[j + 1] = buffer[j];
                        }

                        buffer[0] = bTemp[0];
                        f = this.decode(buffer);
                    }

                    e[i] = f[0];
                }
            }

            return e;
        } catch (Exception var8) {
            throw new BITalinoException(BITalinoErrorTypes.LOST_COMMUNICATION);
        }
    }
}
