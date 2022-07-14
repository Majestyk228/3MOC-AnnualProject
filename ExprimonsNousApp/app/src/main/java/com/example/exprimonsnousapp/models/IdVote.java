package com.example.exprimonsnousapp.models;

public class IdVote {

    // ATTRIBUTS
    private int idVote;

    // CONSTRUCTEUR
    public IdVote(int idPost) {
        this.idPost = idPost;
    }

    // GETTER & SETTER
    public int getidVote() {
        return idVote;
    }

    public void setidVote(int idVote) {
        this.idVote = idVote;
    }

    // TO STRING
    @Override
    public String toString() {
        return "IdVote{" +
                "idVote=" + idVote +
                '}';
    }
}
