package com.example.android.monitoringapp.Data;

/**
 * Created by AG on 30/08/2017.
 */

public class DataECG {

    private int id;
    private int value_ECG;
    private String date_arrhythmia;
    private String hour_arrhythmia;

    public DataECG(){}

    public DataECG(int id, int value_ECG, String date_arrhythmia, String hour_arrhythmia) {
        this.id = id;
        this.value_ECG = value_ECG;
        this.date_arrhythmia = date_arrhythmia;
        this.hour_arrhythmia = hour_arrhythmia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValue_ECG() {
        return value_ECG;
    }

    public void setValue_ECG(int value_ECG) {
        this.value_ECG = value_ECG;
    }

    public String getDate_arrhythmia() {
        return date_arrhythmia;
    }

    public void setDate_arrhythmia(String date_arrhythmia) {
        this.date_arrhythmia = date_arrhythmia;
    }

    public String getHour_arrhythmia() {
        return hour_arrhythmia;
    }

    public void setHour_arrhythmia(String hour_arrhythmia) {
        this.hour_arrhythmia = hour_arrhythmia;
    }

    @Override
    public String toString() {
        return "Data{" +
                "id=" + id +
                ", value_ECG='" + value_ECG + '\'' +
                ", date_arrhythmia='" + date_arrhythmia + '\'' +
                ", hour_arrhythmia='" + hour_arrhythmia + '\'' +
                '}';
    }
}
