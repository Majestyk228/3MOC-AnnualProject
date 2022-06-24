package com.example.exprimonsnousapp.models;

public class IdCommunity {

    // ATTRIBUT
    private int idCommunity;

    // CONSTRUCTOR
    public IdCommunity(int idCommunity) {
        this.idCommunity = idCommunity;
    }

    // GETTER & SETTER
    public int getIdCommunity() {
        return idCommunity;
    }

    public void setIdCommunity(int idCommunity) {
        this.idCommunity = idCommunity;
    }

    @Override
    public String toString() {
        return "IdCommunity{" +
                "idCommunity=" + idCommunity +
                '}';
    }
}
