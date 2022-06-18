package com.example.orderts.Model;

public class User {
    private String phone, pass;
    public User(){}
    public User(String phone, String pass){
        this.phone=phone;
        this.pass=pass;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
