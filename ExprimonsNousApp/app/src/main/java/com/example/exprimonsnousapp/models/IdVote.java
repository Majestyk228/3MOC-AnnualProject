package com.example.exprimonsnousapp.models;

public class IdVote {

    // ATTRIBUTS
    private int idPost;

    // CONSTRUCTEUR
    public IdVote(int idPost) {
        this.idPost = idPost;
    }

    // GETTER & SETTER
    public int getIdPost() {
        return idPost;
    }

    public void setIdPost(int idPost) {
        this.idPost = idPost;
    }

    // TO STRING
    @Override
    public String toString() {
        return "IdVote{" +
                "idPost=" + idPost +
                '}';
    }
}
