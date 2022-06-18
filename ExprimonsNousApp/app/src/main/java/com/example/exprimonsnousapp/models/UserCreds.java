package com.example.exprimonsnousapp.models;

public class UserCreds {

    //ATTRIBUTS
    private int idUser;
    private String token;
    private int idCommunity;

    // CONSTRUCTOR
    public UserCreds(int idUser, String token, int idCommunity) {
        this.idUser = idUser;
        this.token = token;
        this.idCommunity = idCommunity;
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

    public int getIdCommunity() {
        return idCommunity;
    }

    public void setIdCommunity(int idCommunity) {
        this.idCommunity = idCommunity;
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
