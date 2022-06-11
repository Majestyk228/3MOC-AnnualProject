package com.example.exprimonsnousapp.models;

public class UserCreds {

    //ATTRIBUTS
    private int idUser;
    private String token;

    // CONSTRUCTOR
    public UserCreds(int idUser, String token) {
        this.idUser = idUser;
        this.token = token;
    }

    //GETTERS & SETTERS
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    // toString()

    @Override
    public String toString() {
        return "UserCreds{" +
                "idUser=" + idUser +
                ", token='" + token + '\'' +
                '}';
    }
}
