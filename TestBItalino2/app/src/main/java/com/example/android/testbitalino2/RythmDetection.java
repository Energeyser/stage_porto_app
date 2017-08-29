package com.example.android.testbitalino2;

/**
 * Created by axel- on 28/08/2017.
 */

import com.bitalino.comm.BITalinoFrame;

public class RythmDetection {



    public static int detect(int ECG[]) {
        float[] highPassArray = {0};
        float[] lowPassArray = {0};
        int[] qrsArray = {0};
        int BPM = 0;

        System.out.println(ECG);

        //device.start(aChannels);

        //while(true) {

        //int[] temp1 = null;
        //int[] temp2 = null;


        // get all the values of the analog channel 2 (ECG) & 6 (PPG)
/*
        for (int frameCounter = 0; frameCounter < dataAcquired.length; frameCounter++) {

            channel2Values[frameCounter] = dataAcquired[frameCounter].getChannel2();
            //channel6Values[frameCounter] = dataAcquired[frameCounter].getChannel6();

        }*/

        highPassArray = highPass(ECG, ECG.length);
        lowPassArray = lowPass(highPassArray, highPassArray.length);
        qrsArray = QRS(lowPassArray, lowPassArray.length);
        BPM = cfMeasure(qrsArray, qrsArray.length);

        System.out.println("BPM = "+ BPM);

        return BPM;
        //}

        // trigger digital outputs
        //int[] digital = {0,0,1,1};	/// On %BITalino (r)evolution, the array contents are: I1 I2 O1 O2.
        //device.trigger(digital);

        // stop acquisition
        //device.stop();

        //close bluetooth connection
        //device.close();

        //}
    }


    ////////////////////////
    // Cardiac frequency  //
    ////////////////////////

    public static int cfMeasure(int qrsArray[], int qrsArrayLength) {

        int instantAvg = 0;
        int pwmDetect = 0;    // latch to create the PWM signal
        int signalDown = 0;    // first part of PWM signal
        int signalUp = 0;    // second part of PWM signal
        int flag = 0;    // detection to reset counters of signalUp & Down
        float confidenceThreshold = 0.05f;    // confidence threshold to choose the normal ECG activity
        float upValOfCT = 0;    // upper value of the confidence threshold
        float downValOfCT = 0;    // lower value of the confidence threshold
        int[] ecgAvgNormal = new int[10000];    // Array of Normal activity of the ECG
        int index = 0;    // index of the ecgAvgNormal[]
        int sum = 0; // sum of all the averages
        int cf = 0;

        for (int i = 0; i < qrsArrayLength; i++) {

            if (qrsArray[i] == 1) {

                flag = 1;

                if (pwmDetect == 0) {
                    pwmDetect = 1;
                } else {
                    pwmDetect = 0;
                }

                instantAvg = (signalUp + signalDown) / 2;
                upValOfCT = instantAvg * confidenceThreshold + instantAvg;
                downValOfCT = instantAvg - instantAvg * confidenceThreshold;

                if (((signalDown <= upValOfCT) && (signalDown >= downValOfCT)) && ((signalUp <= upValOfCT) && (signalUp >= downValOfCT))) {

                    ecgAvgNormal[index] = instantAvg;
                    index++;

                }

            } else {
                flag = 0;
            }

            if (pwmDetect == 1) {
                signalUp++;
                if (flag == 1) signalUp = 0;    // flag reset
            } else {
                signalDown++;
                if (flag == 1) signalDown = 0;    // flag reset
            }

            //System.out.println(qrsArray[i]);	// array of points, 1 for the QRS, 0 for the others

        }

        for (int i = 0; i < index; i++) {    // index is the last value, some of the values have been deleted to have the right average
            sum = sum + ecgAvgNormal[i];

        }

        if ((sum != 0) && (index != 0)) {
            cf = 60000 / (sum / index);    // 1 min = 60000 ms
            //System.out.println("cf = " + cf);
        }

        return cf;

    }

    ////////////////////////////////////////
    // High pass filter 				  //
    // y1[n] = 1/M * Sum[m=0, M-1] x[n-m] //
    // y2[n] = x[n - (M+1)/2]             //
    ////////////////////////////////////////

    public static float[] highPass(int[] sig0, int nsamp) {
        int M = 5;    // M-point moving  average  filter
        float[] highPass = new float[nsamp];
        float constant = (float) 1 / M; // M-point moving  average  filter


        for (int i = 0; i < sig0.length; i++) {
            float y1 = 0;
            float y2 = 0;

            int y2_index = i - ((M + 1) / 2);
            if (y2_index < 0) {
                y2_index = nsamp + y2_index;
            }
            y2 = sig0[y2_index];

            float y1_sum = 0;
            for (int j = i; j > i - M; j--) {
                int x_index = i - (i - j);
                if (x_index < 0) {
                    x_index = nsamp + x_index;
                }
                y1_sum += sig0[x_index];
            }

            y1 = constant * y1_sum;
            highPass[i] = y2 - y1;

        }

        return highPass;
    }

    /////////////////////
    // Low pass filter //
    /////////////////////

    public static float[] lowPass(float[] sig0, int nsamp) {
        float[] lowPass = new float[nsamp];
        for (int i = 0; i < sig0.length; i++) {
            float sum = 0;
            if (i + 30 < sig0.length) {
                for (int j = i; j < i + 30; j++) {
                    float current = sig0[j] * sig0[j];
                    sum += current;
                }
            } else if (i + 30 >= sig0.length) {
                int over = i + 30 - sig0.length;
                for (int j = i; j < sig0.length; j++) {
                    float current = sig0[j] * sig0[j];
                    sum += current;
                }
                for (int j = 0; j < over; j++) {
                    float current = sig0[j] * sig0[j];
                    sum += current;
                }
            }

            lowPass[i] = sum;
        }

        return lowPass;

    }

    ////////////////////////
    // QRS peak detection //
    ////////////////////////

    public static int[] QRS(float[] lowPass, int nsamp) {
        int[] QRS = new int[nsamp];

        double treshold = 0;

        for (int i = 0; i < 500; i++) {
            if (lowPass[i] > treshold) {
                treshold = lowPass[i];
            }
        }

        int frame = 250;

        for (int i = 0; i < lowPass.length; i += frame) {
            float max = 0;
            int index = 0;
            if (i + frame > lowPass.length) {
                index = lowPass.length;
            } else {
                index = i + frame;
            }
            for (int j = i; j < index; j++) {
                if (lowPass[j] > max) max = lowPass[j];
            }
            boolean added = false;
            for (int j = i; j < index; j++) {
                if (lowPass[j] > treshold && !added) {
                    QRS[j] = 1;
                    added = true;
                } else {
                    QRS[j] = 0;
                }
            }

            double gama = (Math.random() > 0.5) ? 0.15 : 0.20;
            double alpha = 0.01 + (Math.random() * ((0.1 - 0.01)));

            treshold = alpha * gama * max + (1 - alpha) * treshold;

        }

        return QRS;

    }

}
