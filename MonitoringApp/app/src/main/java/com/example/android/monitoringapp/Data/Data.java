package com.example.android.monitoringapp.Data;

import java.sql.Date;

import static android.R.attr.name;
import static android.R.attr.password;

/**
 * Created by axel- on 19/07/2017.
 */

public class Data {
    private int id;
    private String patient_name;
    private int patient_process_number;
    private String date;
    private int minimum_hr;
    private int maximum_hr;
    private int average_hr;
    private int minimum_resp;
    private int maximum_resp;
    private int average_resp;
    private int minimum_oxy;
    private int maximum_oxy;
    private int average_oxy;
    private String ecg_description;
    private int minimum_thoracic_fluid_content;
    private int maximum_thoracic_fluid_content;
    private int average_thoracic_fluid_content;
    private int minimum_body_fluid_content;
    private int maximum_body_fluid_content;
    private int average_body_fluid_content;
    private String minimum_systolic_blood_pressure;
    private String maximum_systolic_blood_pressure;
    private String average_systolic_blood_pressure;
    private String minimum_diastolic_blood_pressure;
    private String maximum_diastolic_blood_pressure;
    private String average_diastolic_blood_pressure;
    private int minimum_sodium_chloride;
    private int maximum_sodium_chloride;
    private int average_sodium_chloride;
    private int alert;

    public Data(){}

    public Data(int id, String patient_name, int patient_process_number, String date, int minimum_hr, int maximum_hr, int average_hr, int minimum_resp, int maximum_resp, int average_resp, int minimum_oxy, int maximum_oxy, int average_oxy, String ecg_description, int minimum_thoracic_fluid_content, int maximum_thoracic_fluid_content, int average_thoracic_fluid_content, int minimum_body_fluid_content, int maximum_body_fluid_content, int average_body_fluid_content, String minimum_systolic_blood_pressure, String maximum_systolic_blood_pressure, String average_systolic_blood_pressure, String minimum_diastolic_blood_pressure, String maximum_diastolic_blood_pressure, String average_diastolic_blood_pressure, int minimum_sodium_chloride, int maximum_sodium_chloride, int average_sodium_chloride, int alert) {
        this.id = id;
        this.patient_name = patient_name;
        this.patient_process_number = patient_process_number;
        this.date = date;
        this.minimum_hr = minimum_hr;
        this.maximum_hr = maximum_hr;
        this.average_hr = average_hr;
        this.minimum_resp = minimum_resp;
        this.maximum_resp = maximum_resp;
        this.average_resp = average_resp;
        this.minimum_oxy = minimum_oxy;
        this.maximum_oxy = maximum_oxy;
        this.average_oxy = average_oxy;
        this.ecg_description = ecg_description;
        this.minimum_thoracic_fluid_content = minimum_thoracic_fluid_content;
        this.maximum_thoracic_fluid_content = maximum_thoracic_fluid_content;
        this.average_thoracic_fluid_content = average_thoracic_fluid_content;
        this.minimum_body_fluid_content = minimum_body_fluid_content;
        this.maximum_body_fluid_content = maximum_body_fluid_content;
        this.average_body_fluid_content = average_body_fluid_content;
        this.minimum_systolic_blood_pressure = minimum_systolic_blood_pressure;
        this.maximum_systolic_blood_pressure = maximum_systolic_blood_pressure;
        this.average_systolic_blood_pressure = average_systolic_blood_pressure;
        this.minimum_diastolic_blood_pressure = minimum_diastolic_blood_pressure;
        this.maximum_diastolic_blood_pressure = maximum_diastolic_blood_pressure;
        this.average_diastolic_blood_pressure = average_diastolic_blood_pressure;
        this.minimum_sodium_chloride = minimum_sodium_chloride;
        this.maximum_sodium_chloride = maximum_sodium_chloride;
        this.average_sodium_chloride = average_sodium_chloride;
        this.alert = alert;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public int getPatient_process_number() {
        return patient_process_number;
    }

    public void setPatient_process_number(int patient_process_number) {
        this.patient_process_number = patient_process_number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getMinimum_hr() {
        return minimum_hr;
    }

    public void setMinimum_hr(int minimum_hr) {
        this.minimum_hr = minimum_hr;
    }

    public int getMaximum_hr() {
        return maximum_hr;
    }

    public void setMaximum_hr(int maximum_hr) {
        this.maximum_hr = maximum_hr;
    }

    public int getAverage_hr() {
        return average_hr;
    }

    public void setAverage_hr(int average_hr) {
        this.average_hr = average_hr;
    }

    public int getMinimum_resp() {
        return minimum_resp;
    }

    public void setMinimum_resp(int minimum_resp) {
        this.minimum_resp = minimum_resp;
    }

    public int getMaximum_resp() {
        return maximum_resp;
    }

    public void setMaximum_resp(int maximum_resp) {
        this.maximum_resp = maximum_resp;
    }

    public int getAverage_resp() {
        return average_resp;
    }

    public void setAverage_resp(int average_resp) {
        this.average_resp = average_resp;
    }

    public int getMinimum_oxy() {
        return minimum_oxy;
    }

    public void setMinimum_oxy(int minimum_oxy) {
        this.minimum_oxy = minimum_oxy;
    }

    public int getMaximum_oxy() {
        return maximum_oxy;
    }

    public void setMaximum_oxy(int maximum_oxy) {
        this.maximum_oxy = maximum_oxy;
    }

    public int getAverage_oxy() {
        return average_oxy;
    }

    public void setAverage_oxy(int average_oxy) {
        this.average_oxy = average_oxy;
    }

    public String getEcg_description() {
        return ecg_description;
    }

    public void setEcg_description(String ecg_description) {
        this.ecg_description = ecg_description;
    }

    public int getMinimum_thoracic_fluid_content() {
        return minimum_thoracic_fluid_content;
    }

    public void setMinimum_thoracic_fluid_content(int minimum_thoracic_fluid_content) {
        this.minimum_thoracic_fluid_content = minimum_thoracic_fluid_content;
    }

    public int getMaximum_thoracic_fluid_content() {
        return maximum_thoracic_fluid_content;
    }

    public void setMaximum_thoracic_fluid_content(int maximum_thoracic_fluid_content) {
        this.maximum_thoracic_fluid_content = maximum_thoracic_fluid_content;
    }

    public int getAverage_thoracic_fluid_content() {
        return average_thoracic_fluid_content;
    }

    public void setAverage_thoracic_fluid_content(int average_thoracic_fluid_content) {
        this.average_thoracic_fluid_content = average_thoracic_fluid_content;
    }

    public int getMinimum_body_fluid_content() {
        return minimum_body_fluid_content;
    }

    public void setMinimum_body_fluid_content(int minimum_body_fluid_content) {
        this.minimum_body_fluid_content = minimum_body_fluid_content;
    }

    public int getMaximum_body_fluid_content() {
        return maximum_body_fluid_content;
    }

    public void setMaximum_body_fluid_content(int maximum_body_fluid_content) {
        this.maximum_body_fluid_content = maximum_body_fluid_content;
    }

    public int getAverage_body_fluid_content() {
        return average_body_fluid_content;
    }

    public void setAverage_body_fluid_content(int average_body_fluid_content) {
        this.average_body_fluid_content = average_body_fluid_content;
    }

    public String getMinimum_systolic_blood_pressure() {
        return minimum_systolic_blood_pressure;
    }

    public void setMinimum_systolic_blood_pressure(String minimum_systolic_blood_pressure) {
        this.minimum_systolic_blood_pressure = minimum_systolic_blood_pressure;
    }

    public String getMaximum_systolic_blood_pressure() {
        return maximum_systolic_blood_pressure;
    }

    public void setMaximum_systolic_blood_pressure(String maximum_systolic_blood_pressure) {
        this.maximum_systolic_blood_pressure = maximum_systolic_blood_pressure;
    }

    public String getAverage_systolic_blood_pressure() {
        return average_systolic_blood_pressure;
    }

    public void setAverage_systolic_blood_pressure(String average_systolic_blood_pressure) {
        this.average_systolic_blood_pressure = average_systolic_blood_pressure;
    }

    public String getMinimum_diastolic_blood_pressure() {
        return minimum_diastolic_blood_pressure;
    }

    public void setMinimum_diastolic_blood_pressure(String minimum_diastolic_blood_pressure) {
        this.minimum_diastolic_blood_pressure = minimum_diastolic_blood_pressure;
    }

    public String getMaximum_diastolic_blood_pressure() {
        return maximum_diastolic_blood_pressure;
    }

    public void setMaximum_diastolic_blood_pressure(String maximum_diastolic_blood_pressure) {
        this.maximum_diastolic_blood_pressure = maximum_diastolic_blood_pressure;
    }

    public String getAverage_diastolic_blood_pressure() {
        return average_diastolic_blood_pressure;
    }

    public void setAverage_diastolic_blood_pressure(String average_diastolic_blood_pressure) {
        this.average_diastolic_blood_pressure = average_diastolic_blood_pressure;
    }

    public int getMinimum_sodium_chloride() {
        return minimum_sodium_chloride;
    }

    public void setMinimum_sodium_chloride(int minimum_sodium_chloride) {
        this.minimum_sodium_chloride = minimum_sodium_chloride;
    }

    public int getMaximum_sodium_chloride() {
        return maximum_sodium_chloride;
    }

    public void setMaximum_sodium_chloride(int maximum_sodium_chloride) {
        this.maximum_sodium_chloride = maximum_sodium_chloride;
    }

    public int getAverage_sodium_chloride() {
        return average_sodium_chloride;
    }

    public void setAverage_sodium_chloride(int average_sodium_chloride) {
        this.average_sodium_chloride = average_sodium_chloride;
    }

    public int getAlert() {
        return alert;
    }

    public void setAlert(int alert) {
        this.alert = alert;
    }

    @Override
    public String toString() {
        return "Data{" +
                "id=" + id +
                ", patient_name='" + patient_name + '\'' +
                ", patient_process_number=" + patient_process_number +
                ", date='" + date + '\'' +
                ", minimum_hr=" + minimum_hr +
                ", maximum_hr=" + maximum_hr +
                ", average_hr=" + average_hr +
                ", minimum_resp=" + minimum_resp +
                ", maximum_resp=" + maximum_resp +
                ", average_resp=" + average_resp +
                ", minimum_oxy=" + minimum_oxy +
                ", maximum_oxy=" + maximum_oxy +
                ", average_oxy=" + average_oxy +
                ", ecg_description='" + ecg_description + '\'' +
                ", minimum_thoracic_fluid_content=" + minimum_thoracic_fluid_content +
                ", maximum_thoracic_fluid_content=" + maximum_thoracic_fluid_content +
                ", average_thoracic_fluid_content=" + average_thoracic_fluid_content +
                ", minimum_body_fluid_content=" + minimum_body_fluid_content +
                ", maximum_body_fluid_content=" + maximum_body_fluid_content +
                ", average_body_fluid_content=" + average_body_fluid_content +
                ", minimum_systolic_blood_pressure='" + minimum_systolic_blood_pressure + '\'' +
                ", maximum_systolic_blood_pressure='" + maximum_systolic_blood_pressure + '\'' +
                ", average_systolic_blood_pressure='" + average_systolic_blood_pressure + '\'' +
                ", minimum_diastolic_blood_pressure='" + minimum_diastolic_blood_pressure + '\'' +
                ", maximum_diastolic_blood_pressure='" + maximum_diastolic_blood_pressure + '\'' +
                ", average_diastolic_blood_pressure='" + average_diastolic_blood_pressure + '\'' +
                ", minimum_sodium_chloride=" + minimum_sodium_chloride +
                ", maximum_sodium_chloride=" + maximum_sodium_chloride +
                ", average_sodium_chloride=" + average_sodium_chloride +
                ", alert=" + alert +
                '}';
    }
}
