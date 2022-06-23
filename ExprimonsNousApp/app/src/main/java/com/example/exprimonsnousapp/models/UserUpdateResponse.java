package com.example.exprimonsnousapp.models;

public class UserUpdateResponse {

    // ATTRIBUT
    private String message;

    // CONSTRUCTEUR
    public UserUpdateResponse(String message) {
        this.message = message;
    }

    // GETTER & SETTER

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    // TO STRING

    @Override
    public String toString() {
        return "UserUpdateResponse{" +
                "message='" + message + '\'' +
                '}';
    }
}
