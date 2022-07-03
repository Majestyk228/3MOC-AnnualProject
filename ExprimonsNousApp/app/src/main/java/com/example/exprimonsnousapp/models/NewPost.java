package com.example.exprimonsnousapp.models;

public class NewPost {
    /*
    * {
  "title": "testAPI",
  "body": "ceci est un post ADMIN de test via API",
  "idCommunity": 1,
  "idUser": null,
  "idAdmin": 1
}
    * */

    // ATTRIBUTS
    private String title;
    private String body;
    private int idCommunity;
    private int idUser;
    private String idAdmin;

    // CONSTRUCTOR
    public NewPost(String title, String body, int idCommunity, int idUser, String idAdmin) {
        this.title = title;
        this.body = body;
        this.idCommunity = idCommunity;
        this.idUser = idUser;
        this.idAdmin = idAdmin;
    }

    // GETTER & SETTERS
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

    public int getIdCommunity() {
        return idCommunity;
    }

    public void setIdCommunity(int idCommunity) {
        this.idCommunity = idCommunity;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(String idAdmin) {
        this.idAdmin = idAdmin;
    }

    // TO STRING
    @Override
    public String toString() {
        return "NewPost{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", idCommunity=" + idCommunity +
                ", idUser=" + idUser +
                ", idAdmin='" + idAdmin + '\'' +
                '}';
    }
}
