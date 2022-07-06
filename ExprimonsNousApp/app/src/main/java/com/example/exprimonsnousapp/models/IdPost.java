package com.example.exprimonsnousapp.models;

public class IdPost {
    // ATTRIBUT
    private String idPost;

    // CONSTRUCTOR
    public IdPost(String idPost) {
        this.idPost = idPost;
    }

    // GETTER & SETTER
    public String getIdPost() {
        return idPost;
    }

    public void setIdPost(String idPost) {
        this.idPost = idPost;
    }

    // TO STRING
    @Override
    public String toString() {
        return "IdPost{" +
                "idPost=" + idPost +
                '}';
    }
}
