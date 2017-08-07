package com.example.android.monitoringapp.BITalino;

/**
 * Created by axel- on 07/08/2017.
 */

public class Frame {
    public int CRC;
    public int seq;
    public int[] analog = new int[6];
    public int[] digital = new int[4];

    public Frame() {
    }

    public int getCRC() {
        return this.CRC;
    }

    public int getSeq() {
        return this.seq;
    }

    public int getChannel1() {
        return this.analog[0];
    }

    public int getChannel2() {
        return this.analog[1];
    }

    public int getChannel3() {
        return this.analog[2];
    }

    public int getChannel4() {
        return this.analog[3];
    }

    public int getChannel5() {
        return this.analog[4];
    }

    public int getChannel6() {
        return this.analog[5];
    }
}
