package com.example.android.monitoringapp.Data;

/**
 * Created by axel- on 19/07/2017.
 */

public class Patient {
    private int id;
    private int processNumber;
    private String name;
    private String phone;
    private String address;
    private String pot_name;
    private String pot_phone;

    public Patient(){}

    public Patient(String name,int processNumber, String phone, String address,String pot_name,String pot_phone){
        this.name = name;
        this.processNumber = processNumber;
        this.phone = phone;
        this.address = address;
        this.pot_name = pot_name;
        this.pot_phone = pot_phone;
    }

    public int getID(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getProcessNumber(){
        return processNumber;
    }

    public void setProcessNumber(int processNumber){
        this.processNumber = processNumber;
    }

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getPot_name(){
        return pot_name;
    }

    public void setPot_name(String pot_name){
        this.pot_name = pot_name;
    }

    public String getPot_phone(){
        return pot_phone;
    }

    public void setPot_phone(String pot_phone){
        this.pot_phone = pot_phone;
    }

    public String toString(){
        return "ID : "+id+"\nName : "+name+"\nProcess Number : "+processNumber+"\nPhone : "+phone+"\nAddress : "+address+"\nPot_name : "
                +pot_name+"\nPot_phone : "+pot_phone;
    }

}
