package com.example.exprimonsnousapp.models;

public class NewComment {

    // ATTRIBUTS
    private int idCommunity;
    private String body;
    private boolean anonymous;
    private int idPost;
    private int idUser;

    // CONSTRUCTOR
    public NewComment(int idCommunity, String body, boolean anonymous, int idPost, int idUser) {
        this.idCommunity = idCommunity;
        this.body = body;
        this.anonymous = anonymous;
        this.idPost = idPost;
        this.idUser = idUser;
    }

    // GETTERS & SETTERS
    public int getIdCommunity() {
        return idCommunity;
    }

    public void setIdCommunity(int idCommunity) {
        this.idCommunity = idCommunity;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public boolean isAnonymous() {
        return anonymous;
    }

    public void setAnonymous(boolean anonymous) {
        this.anonymous = anonymous;
    }

    public int getIdPost() {
        return idPost;
    }

    public void setIdPost(int idPost) {
        this.idPost = idPost;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    // TO STRING
    @Override
    public String toString() {
        return "NewComment{" +
                "idCommunity=" + idCommunity +
                ", body='" + body + '\'' +
                ", anonymous=" + anonymous +
                ", idPost=" + idPost +
                ", idUser=" + idUser +
                '}';
    }

}
