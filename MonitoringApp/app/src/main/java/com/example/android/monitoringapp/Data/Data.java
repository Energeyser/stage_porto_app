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
    private int mensal;
    private int minimum_hr;
    private int maximum_hr;
    private int average_hr;
    private int minimum_resp;
    private int maximum_resp;
    private int average_resp;
    private String ecg_description;
    private int thoracic_fluid_content;
    private int body_fluid_content;
    private String blood_pressure;
    private int sodium_chloride;
    private int alert;

    public Data(){}

    public Data(int id,
                String patient_name,
                int patient_process_number,
                String date,
                int mensal,
                int minimum_hr,
                int maximum_hr,
                int average_hr,
                int minimum_resp,
                int maximum_resp,
                int average_resp,
                String ecg_description,
                int thoracic_fluid_content,
                int body_fluid_content,
                String blood_pressure,
                int sodium_chloride,
                int alert){
        this.id = id;
        this.patient_name = patient_name;
        this.patient_process_number = patient_process_number;
        this.date = date;
        this.mensal = mensal;
        this.minimum_hr = minimum_hr;
        this.maximum_hr = maximum_hr;
        this.average_hr = average_hr;
        this.minimum_resp = minimum_resp;
        this.maximum_resp = maximum_resp;
        this.average_resp = average_resp;
        this.ecg_description = ecg_description;
        this.thoracic_fluid_content = thoracic_fluid_content;
        this.body_fluid_content = body_fluid_content;
        this.body_fluid_content = body_fluid_content;
        this.blood_pressure = blood_pressure;
        this.sodium_chloride = sodium_chloride;
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

    public int getMensal() {
        return mensal;
    }

    public void setMensal(int mensal) {
        this.mensal = mensal;
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

    public String getEcg_description() {
        return ecg_description;
    }

    public void setEcg_description(String ecg_description) {
        this.ecg_description = ecg_description;
    }

    public int getThoracic_fluid_content() {
        return thoracic_fluid_content;
    }

    public void setThoracic_fluid_content(int thoracic_fluid_content) {
        this.thoracic_fluid_content = thoracic_fluid_content;
    }

    public int getBody_fluid_content() {
        return body_fluid_content;
    }

    public void setBody_fluid_content(int body_fluid_content) {
        this.body_fluid_content = body_fluid_content;
    }

    public String getBlood_pressure() {
        return blood_pressure;
    }

    public void setBlood_pressure(String blood_pressure) {
        this.blood_pressure = blood_pressure;
    }

    public int getSodium_chloride() {
        return sodium_chloride;
    }

    public void setSodium_chloride(int sodium_chloride) {
        this.sodium_chloride = sodium_chloride;
    }

    public int getAlert() {
        return alert;
    }

    public void setAlert(int alert) {
        this.alert = alert;
    }

    public String toString(){
        return "ID : "+id+"\nName : "+patient_name+"\nProcess Number : "+patient_process_number+
                "\nDate : "+date+"\nMensal : "
                +mensal+"\nminimum_hr : "+minimum_hr+"\nmaximum_hr : "+maximum_hr
                +"\naverage_hr : "+average_hr+"\nminimum_resp : "+minimum_resp
                +"\nmaximum_resp : "+maximum_resp+"\naverage_resp : "+average_resp
                +"\necg_description : "+ecg_description+"\nthoracic_fluif_content : "+thoracic_fluid_content
                +"\nbody_fluid_content : "+body_fluid_content+"\nblood_pressure : "+blood_pressure
                +"\nsodium_chloride : "+sodium_chloride+"\nalert : "+alert;
    }
   /* int id,
    String patient_name,
    int patient_process_number,
    int date,
    int mensal,
    int minimum_hr,
    int maximum_hr,
    int average_hr,
    int minimum_resp,
    int maximum_resp,
    int average_resp,
    String ecg_description,
    int thoracic_fluid_content,
    int body_fluid_content,
    int blood_pressure,
    int sodium_chloride,
    int alert*/

}
