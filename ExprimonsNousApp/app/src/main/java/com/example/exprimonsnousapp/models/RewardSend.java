package com.example.exprimonsnousapp.models;

public class RewardSend {

    // ATTRIBUTES
    private int idRewards;
    private int idPost;
    private int idUser;

    // CONSTRUCTOR
    public RewardSend(int idRewards, int idPost, int idUser) {
        this.idRewards = idRewards;
        this.idPost = idPost;
        this.idUser = idUser;
    }

    // GETTERS & SETTERS
    public int getIdRewards() {
        return idRewards;
    }

    public void setIdRewards(int idRewards) {
        this.idRewards = idRewards;
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
        return "RewardSend{" +
                "idRewards=" + idRewards +
                ", idPost=" + idPost +
                ", idUser=" + idUser +
                '}';
    }
}
