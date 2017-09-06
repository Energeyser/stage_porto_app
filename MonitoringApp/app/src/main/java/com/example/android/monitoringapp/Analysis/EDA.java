package com.example.android.monitoringapp.Analysis;

import java.util.Vector;

import javax.bluetooth.RemoteDevice;

/**
 * Created by axel- on 31/08/2017.
 */

public class EDA {

    public static double detect(int channel3Values[]) {

        //Déclaration du tableau et des variables pour EDA
		/*
		int[] ADC_EDA= new int[10000];

		int sum_sample = 0;
		int EDA_sample = 10000;
		float EDA_AVG = 0;

		double EDA = 0;
 		double VCC = 3.7;	// déclaration de la tension de la batterie
		int n = 1024;			// nombre de bit de la chaine
		double sigma = 0;	// déclaration de sigma
		double l = 0.06;		// distance entre les 2 électrodes en m
		double r = 0.015;		// rayon des electrodes en m
		double S = 0;		// surface des electrodes
		double R = 0;
		double lambda_Na = 5.01;
		double lambda_Cl = 7.63;
		double C_Na = 0;
		double C_Cl = 0;
		*/


        //optimisation test
		/*paramètres de l'EDA*/

        //int[] channel3Values = new int[10000];

        int sum_opt = 0;
        float EDA_AVG_OPT = 0;
        int sample_opt = 0;
        int bit = 1024;
        double r = 0.015;
        double l = 0.065;
        double VCC = 3.7;

			/*Début calcul moyenne valeur frame*/
        for (int i = 0; i < channel3Values.length; i++) {

            //System.out.println(channel3Values[i]);
            //channel3Values[i] = dataAcquired[i].getChannel3();

            sum_opt += channel3Values[i];
            sample_opt++;
        }

        EDA_AVG_OPT = sum_opt / sample_opt;

			/*Fin Calcul moyenne*/

    /*Test Opt*/
        double EDA_OPT = 0;        // µS
        double R_OPT = 0;        // kOhm
        double surface = 0;        // m
        double Na_Rate = 0;        // mmol/L

        EDA_OPT = getEDA(EDA_AVG_OPT, VCC, bit);
        R_OPT = getImpedance(EDA_OPT) / 1000;
        surface = getSurface(r);
        Na_Rate = getNaRate(EDA_OPT, l, surface) * 1000;

        System.out.println("EDA_OPT = " + EDA_OPT);
        System.out.println("R_OPT = " + R_OPT);
        System.out.println("Surface = " + surface);
        System.out.println("Na Rate = " + Na_Rate);




			/*Fin Test*/

			/*
        frameCounter = 0;    // counter reset

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
*/
        //}

        // trigger digital outputs
        //int[] digital = {0,0,1,1};	/// On %BITalino (r)evolution, the array contents are: I1 I2 O1 O2.
        //device.trigger(digital);

        // stop acquisition
        //device.stop();

        //close bluetooth connection
        //device.close();
        return Na_Rate;
    }



    /*Méthodes utilisées*/
    public static double getEDA (float EDA_AVG, double VCC, int bits)
    {
        double EDA = ((((EDA_AVG*VCC)/bits)-0.574)/0.132)/1000000;
        //System.out.println("opt " + EDA);
        return EDA;
    }

    public static double getImpedance(double EDA) {
        double Impedance = 0;
        Impedance = 1/EDA;
        //System.out.println("R = " + Impedance);
        return Impedance;
    }

    public static double getSurface(double r)
    {
        double S = 3.14 * r * r;
        //System.out.println("S_opt " + S);
        return S;
    }

    public static double getNaRate(double EDA, double l, double S)
    {
        double sigma = 0;	// déclaration de sigma
        double lambda_Na = 5.01;
        double C_Na = 0;
        sigma = (EDA * (l/S))*1000; // sigma en mS/m
        C_Na = sigma / lambda_Na;
        System.out.println("sigma = " + sigma);
        //System.out.println("NaRate = " + C_Na);
        return C_Na;
    }

}
