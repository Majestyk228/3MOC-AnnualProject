package com.example.exprimonsnousapp.models;

public class MessageSupport {

    /*
    * {
  "idUser": 1,
  "idCommunity": 2,
  "title": "Title of the mail",
  "body": "Body of the mail"
}
    * */

    // ATTRIBUTES
    private int idUser;
    private int idCommunity;
    private String title;
    private String body;

    // CONSTRUCTOR
    public MessageSupport(int idUser, int idCommunity, String title, String body) {
        this.idUser = idUser;
        this.idCommunity = idCommunity;
        this.title = title;
        this.body = body;
    }

    // GETTERS & SETTER
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    // TO STRING
    @Override
    public String toString() {
        return "MessageSupport{" +
                "idUser=" + idUser +
                ", idCommunity=" + idCommunity +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
