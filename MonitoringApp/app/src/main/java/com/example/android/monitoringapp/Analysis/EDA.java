package com.example.android.monitoringapp.Analysis;

import java.util.Vector;

import javax.bluetooth.RemoteDevice;

/**
 * Created by axel- on 31/08/2017.
 */

public class EDA {

    public static double detect(int channel3Values[]) {


        int sum_opt = 0;
        float EDA_AVG_OPT = 0;
        int sample_opt = 0;
        int bit = 1024;
        double r = 0.015;
        double l = 0.065;
        double VCC = 3.7;

			/*Début calcul moyenne valeur frame*/
        for (int i = 0; i < channel3Values.length; i++) {

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

        return R_OPT;
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
