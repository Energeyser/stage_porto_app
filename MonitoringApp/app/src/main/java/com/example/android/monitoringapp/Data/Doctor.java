package com.example.android.monitoringapp.Data;

/**
 * Created by axel- on 19/07/2017.
 */

public class Doctor {
    private int id;
    private String name;
    private String phone;
    private String mail;
    private String username;
    private String password;

    public Doctor(){}

    public Doctor(int id,String name, String phone, String mail, String username, String password){
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.mail = mail;
        this.username = username;
        this.password = password;
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

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getMail(){
        return mail;
    }

    public void setMail(String address){
        this.mail = mail;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String toString(){
        return "ID : "+id+"\nName : "+name+"\nPhone : "+phone+"\nMail : "+mail+"\nUsername : "
                +username+"\nPassword : "+password;
    }

}
