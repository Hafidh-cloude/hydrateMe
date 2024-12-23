package com.example.signup_login;

public class HelperClass {

    String name, berat, username, password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return berat;
    }

    public void setEmail(String email) {
        this.berat = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public HelperClass(String name, String berat, String username, String password) {
        this.name = name;
        this.berat = berat;
        this.username = username;
        this.password = password;
    }

    public HelperClass() {
    }
}
