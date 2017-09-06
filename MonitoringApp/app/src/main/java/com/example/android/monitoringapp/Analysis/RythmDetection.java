package com.example.android.monitoringapp.Analysis;

import java.util.ArrayList;

/**
 * Created by axel- on 28/08/2017.
 */

public class RythmDetection {



    public static Results detect(int ECG[], int PPG[]) {
        float[] highPassArray = {0};
        float[] lowPassArray = {0};
        int[] qrsArray = {0};
        Results results = new Results();	// Class with all the results


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
        results = cfMeasure(qrsArray, ECG, results);
        results = bpMeasure(PPG,results);


        System.out.println("results : cf = "+ results.cf + ", RRMissed = "+ results.RRMissed+", arrythmia = "+results.arrythmia + ", BP = " + results.BP + ", DBP = "+results.DBP+", SBP = "+results.SBP);

        return results;

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

    public static Results cfMeasure(int qrsArray[], int[] channel2Values, Results results) {


        int RRAverage1 = 0;
        int RRAverage2 = 0;

        int cf = 0;

        int nbQrsDetection = 0;
        int[] recentRRValuesArray = new int[8];
        int[] acceptableRRValuesArray = new int[8];
        int RRcounter = 0;
        int limitInit = 0;
        int RRLowLimit = 0;
        int RRHighLimit = 0;
        int RRMissedLimit = 0;
        int RRMissed = 0;
        int arrythmia = 0;
        ArrayList<Integer> totalAverage = new ArrayList<Integer>();

        int qrsArrayLength = qrsArray.length;

        for(int i = 0; i<qrsArrayLength; i++) {

            if(RRMissed == 0) {

                if(qrsArray[i] == 1) {

                    if(nbQrsDetection < recentRRValuesArray.length) {

                        nbQrsDetection++;	// increment after the detection of the first peak

                    }else {

                        nbQrsDetection = recentRRValuesArray.length + 1;

                    }

                    if(nbQrsDetection >= 2) {	// when the first peak is detected, we need to wait the second one otherwise RRcounter = 0

                        recentRRValuesArray = integerArrayShift(recentRRValuesArray);	// right shift on the array to store the last RR values
                        recentRRValuesArray[0] = RRcounter;
                        //System.out.println(RRcounter);

                        if((nbQrsDetection - 1) >= recentRRValuesArray.length) {

                            if(limitInit == 0) {

                                limitInit = 1;
                                RRAverage1 = integerArrayAverage(recentRRValuesArray);	// instant average of the 8 last RR values
                                RRAverage2 = RRAverage1;
                                RRLowLimit = (92*RRAverage2) / 100;
                                RRHighLimit = (116*RRAverage2) / 100;
                                RRMissedLimit = (166*RRAverage2) / 100;

                                if(RRcounter > RRMissedLimit) RRMissed = 1;

                                totalAverage.add(RRAverage2);
                                results.indexOne.add(i);
                                results.amplitudeOne.add(channel2Values[i]);
                                //System.out.println(i);
                                //System.out.println(channel2Values[i]);
                                //System.out.println("RRAverage2 = " + RRAverage2);
                                //System.out.println("RRLowLimit = " + RRLowLimit);
                                //System.out.println("RRHighLimit = " + RRHighLimit);
                                //System.out.println("RRMissedLimit = " + RRMissedLimit);

                            }else {

                                RRAverage1 = integerArrayAverage(recentRRValuesArray);	// instant average of the 8 last RR values
                                //System.out.println("AVG1 = " + RRAverage1);

                            }

                            if(limitInit == 1) limitInit++;	// This step is to don't calculate a second time the first limits
                            else {
                                limitInit = 3;
                            }

                            if(limitInit == 3) {

                                if((RRcounter >= RRLowLimit) && (RRcounter <= RRHighLimit)) {

                                    acceptableRRValuesArray = integerArrayShift(acceptableRRValuesArray);

                                    acceptableRRValuesArray[0] = RRcounter;
                                    RRAverage2 = integerArrayAverage(recentRRValuesArray);	// instant average of the 8 last RR values
                                    RRLowLimit = (92*RRAverage2) / 100;
                                    RRHighLimit = (116*RRAverage2) / 100;
                                    RRMissedLimit = (166*RRAverage2) / 100;
                                    //System.out.println("RRAverage2 = " + RRAverage2);
                                    totalAverage.add(RRAverage2);
                                    results.indexOne.add(i);
                                    results.amplitudeOne.add(i);
                                    //System.out.println(i);

                                    if(RRcounter > RRMissedLimit) RRMissed = 1;

                                }else {

                                    arrythmia = 1;
                                    //System.out.println("Battement non régulier: arythmie");

                                }

                            }

                        }

                    }

                    RRcounter = 0;	// reset counter

                }

                if(nbQrsDetection >= 1) {

                    RRcounter++;	// counter of the values between to R detections

                }

                //System.out.println(qrsArray[i]);	// array of points, 1 for the QRS, 0 for the others

            }else {

                i = qrsArrayLength;	// exit the loop if the RR is missed

            }

        }

        if(RRMissed == 0) {

            for (int value : totalAverage) {
                cf += value;
                //System.out.println(value);
            }

            if(totalAverage.size() != 0) {

                cf = cf / totalAverage.size();
                cf = 60000 / cf;	// 1 min = 60000 ms
                //System.out.println(cf);
            }

        }else {

            cf = 0;

        }

        results.cf = cf;
        results.RRMissed = RRMissed;
        results.arrythmia = arrythmia;
        return results;

    }


    ////////////////////////////////////////////////////////////
    // Function to shift an array by the right side (1 value) //
    ////////////////////////////////////////////////////////////

    public static int[] integerArrayShift(int array[]) {

        int i;
        int tmp = array[(array.length) - 1];

        for(i = (array.length - 1); i > 0; i--) {
            array[i] = array[i - 1];
        }

        array[0] = tmp;

        return array;

    }

    ///////////////////////////////////////////////////
    // Function to calculate the average of an array //
    ///////////////////////////////////////////////////

    public static int integerArrayAverage(int array[]) {

        int sum = 0;
        int mean = 0;

        for(int i = 0; i < array.length; i++) {
            sum = sum + array[i];
        }

        mean = sum / array.length;

        return mean;

    }

    ////////////////////////////////////////
    // High pass filter 				  //
    // y1[n] = 1/M * Sum[m=0, M-1] x[n-m] //
    // y2[n] = x[n - (M+1)/2]             //
    ////////////////////////////////////////

    public static float[] highPass(int[] sig0, int nsamp) {
        int M = 3;	// M-point moving  average  filter
        float[] highPass = new float[nsamp];
        float constant = (float) 1/M; // M-point moving  average  filter


        for(int i=0; i<sig0.length; i++) {
            float y1 = 0;
            float y2 = 0;

            int y2_index = i-((M+1)/2);
            if(y2_index < 0) {
                y2_index = nsamp + y2_index;
            }
            y2 = sig0[y2_index];

            float y1_sum = 0;
            for(int j=i; j>i-M; j--) {
                int x_index = i - (i-j);
                if(x_index < 0) {
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
        for(int i=0; i<sig0.length; i++) {
            float sum = 0;
            if(i+30 < sig0.length) {
                for(int j=i; j<i+30; j++) {
                    float current = sig0[j] * sig0[j];
                    sum += current;
                }
            }
            else if(i+30 >= sig0.length) {
                int over = i+30 - sig0.length;
                for(int j=i; j<sig0.length; j++) {
                    float current = sig0[j] * sig0[j];
                    sum += current;
                }
                for(int j=0; j<over; j++) {
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

        for(int i=0; i<500; i++) {
            if(lowPass[i] > treshold) {
                treshold = lowPass[i];
            }

        }

        int frame = 500;

        for(int i=0; i<lowPass.length; i+=frame) {
            float max = 0;
            int index = 0;
            if(i + frame > lowPass.length) {
                index = lowPass.length;
            }
            else {
                index = i + frame;
            }
            for(int j=i; j<index; j++) {
                if(lowPass[j] > max) max = lowPass[j];
            }
            boolean added = false;
            for(int j=i; j<index; j++) {
                if(lowPass[j] > treshold && !added) {
                    QRS[j] = 1;
                    added = true;
                }
                else {
                    QRS[j] = 0;
                }
            }

            double gama = (Math.random() > 0.5) ? 0.15 : 0.20;
            double alpha = 0.01 + (Math.random() * ((0.1 - 0.01)));

            treshold = alpha * gama * max + (1 - alpha) * treshold;
            //System.out.println("treshold" + treshold);

        }

        return QRS;

    }

    ////////////////////
    // Blood Pressure //
    ////////////////////

    public static Results bpMeasure(int[] channel6Values, Results results) {

        // indexOne : index of the one value we have to refer to measure PPT times
        int ppgMaxValue = 0;
        int indexMaxValue = 0;
        int ppgMinValue = 0;
        int indexMinValue = 0;

        ArrayList<Integer> PPTb = new ArrayList<Integer>();
        ArrayList<Integer> PPTt = new ArrayList<Integer>();

        Integer[] indexOnes = new Integer[results.indexOne.size()];
        results.indexOne.toArray(indexOnes);	// transform the list into an array

        for(int i = 0; i<results.indexOne.size(); i++) {	// measures of different times between the ECG signal and PPG signal
            //System.out.println(i);
            //System.out.println(results.indexOne.size());

			/*
			for(int z = 0; z<results.channel6Values.length; z++) {
				System.out.println(results.channel6Values[z]);
			}*/

            if(i < results.indexOne.size() - 1) {

                for(int j = indexOnes[i]; j<indexOnes[i + 1]; j++) {	// detection of the PPG wave between 2 picks

                    if(channel6Values[j] >= ppgMaxValue) {

                        ppgMaxValue = channel6Values[j];
                        indexMaxValue = j;
                        //System.out.println(ppgMaxValue);
                        //indexOnes = onesArray[i];
                        //System.out.println(channel6Values);

                    }

                    //System.out.println(channel6Values[j]);

                }

                ppgMinValue = ppgMaxValue;

                for(int k = indexMaxValue; k>indexOnes[i]; k--) {	// detection of the PPG wave between 2 picks

                    if(channel6Values[k] < ppgMinValue) {

                        ppgMinValue = channel6Values[k];
                        indexMinValue = k;
                        //System.out.println(ppgMinValue);

                    }

                }

                //System.out.println(indexMinValue);

                if((ppgMaxValue - ppgMinValue) > 0) {

                    PPTb.add(indexMinValue - indexOnes[i]);
                    PPTt.add(indexMaxValue - indexOnes[i]);
                    //System.out.println(PPTtAvg[indexAvg]);

                }

                //System.out.println(PPTb);
                //System.out.println("i = " + i);

            }

        }

        int PPTbValueInMs = 0;

        if(PPTb.size() != 0) {	// if there is some values of PPT

            for (int value : PPTb) {
                PPTbValueInMs += value;
            }

            PPTbValueInMs = PPTbValueInMs/PPTb.size();

            //System.out.println("PPTb = " + PPTbValueInMs);

            // Correlation between PPT values and Blood Pressure thanks to those curves

            double DBP = 0;	// DBP in mmHg
            double SBP = 0;	// SBP in mmHg
            double BP = 0; // Blood pressure average

            DBP = -0.35*PPTbValueInMs + 153.68;
            //System.out.println("Blood Pressure (DBP in mmHg) = " + DBP);
            results.DBP = (int)DBP;
            SBP = -0.55*PPTbValueInMs + 245.1;
            //System.out.println("Blood Pressure (SBP in mmHg) = " + SBP);
            results.SBP = (int)SBP;
            BP = (SBP + 2*DBP) / 3;
            //System.out.println("Blood Pressure (BP in mmHg) = " + BP);
            results.BP = (int)BP;


        }
        return results;

    }

}
