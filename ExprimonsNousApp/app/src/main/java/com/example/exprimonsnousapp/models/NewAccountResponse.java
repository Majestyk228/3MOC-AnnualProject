package com.example.exprimonsnousapp.models;

public class NewAccountResponse {

    // ATTRIIBUTS
    private String message;
    private int idUser;

    // CONSTRUCTOR
    public NewAccountResponse(String message, int idUser) {
        this.message = message;
        this.idUser = idUser;
    }

    // GETTERS & SETTERS
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    //TO STRING
    @Override
    public String toString() {
        return "NewAccountResponse{" +
                "message='" + message + '\'' +
                ", idUser=" + idUser +
                '}';
    }
}
