package com.example.android.monitoringapp.Data;

/**
 * Created by AG on 30/08/2017.
 */

public class DataECG {

    private int id;
    private int value_ECG;

    public DataECG(){}

    public DataECG(int id, int value_ECG) {
        this.id = id;
        this.value_ECG = value_ECG;
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

    @Override
    public String toString() {
        return "Data{" +
                "id=" + id +
                ", value_ECG='" + value_ECG + '\'' +
                '}';
    }
}
