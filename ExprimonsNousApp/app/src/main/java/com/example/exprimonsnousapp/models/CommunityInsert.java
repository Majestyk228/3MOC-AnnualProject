package com.example.exprimonsnousapp.models;

public class CommunityInsert {

    // ATTRIBUTS
    private int idUser;
    private int idCommunity;

    // CONSTRUCTOR
    public CommunityInsert(int idUser, int idCommunity) {
        this.idUser = idUser;
        this.idCommunity = idCommunity;
    }

    // GETTERS & SETTERS
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdCommunity() {
        return idCommunity;
    }

    public void setIdCommunity(int idCommunity) {
        this.idCommunity = idCommunity;
    }

    // TO STRING
    @Override
    public String toString() {
        return "CommunityInsert{" +
                "idUser=" + idUser +
                ", idCommunity=" + idCommunity +
                '}';
    }
}
