package com.example.exprimonsnousapp.models;

public class UserLoginCreds {

    // ATTRIBUTS
    private String email;
    private String password;

    // CONSTRUCTOR
    public UserLoginCreds(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // GETTERS & SETTERS
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // toString()
    @Override
    public String toString() {
        return "UserLoginCreds{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
