package com.example.exprimonsnousapp.models;

import java.util.Date;

public class Vote {

    // ATTRIBUTS
    private int idVote;
    private String title;
    private String body;
    private int nbChoice;
    private Boolean important;
    private int idUser;
    private int idAdmin;
    private Date voteBegins;
    private Date voteEnds;
    private int idCommunity;

    // CONSTRUCTEURS
    public Vote() {
    }

    public Vote(int idVote, String title, String body, int nbChoice, Boolean important, int idUser, int idAdmin, Date voteBegins, Date voteEnds, int idCommunity) {
        this.idVote = idVote;
        this.title = title;
        this.body = body;
        this.nbChoice = nbChoice;
        this.important = important;
        this.idUser = idUser;
        this.idAdmin = idAdmin;
        this.voteBegins = voteBegins;
        this.voteEnds = voteEnds;
        this.idCommunity = idCommunity;
    }

    // GETTERS & SETTERS
    public int getIdVote() {
        return idVote;
    }

    public void setIdVote(int idVote) {
        this.idVote = idVote;
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

    public int getNbChoice() {
        return nbChoice;
    }

    public void setNbChoice(int nbChoice) {
        this.nbChoice = nbChoice;
    }

    public Boolean getImportant() {
        return important;
    }

    public void setImportant(Boolean important) {
        this.important = important;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    public Date getVoteBegins() {
        return voteBegins;
    }

    public void setVoteBegins(Date voteBegins) {
        this.voteBegins = voteBegins;
    }

    public Date getVoteEnds() {
        return voteEnds;
    }

    public void setVoteEnds(Date voteEnds) {
        this.voteEnds = voteEnds;
    }

    public int getIdCommunity() {
        return idCommunity;
    }

    public void setIdCommunity(int idCommunity) {
        this.idCommunity = idCommunity;
    }


    // TO STRING
    @Override
    public String toString() {
        return "Vote{" +
                "idVote=" + idVote +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", nbChoice=" + nbChoice +
                ", important=" + important +
                ", idUser=" + idUser +
                ", idAdmin=" + idAdmin +
                ", voteBegins=" + voteBegins +
                ", voteEnds=" + voteEnds +
                ", idCommunity=" + idCommunity +
                '}';
    }
}
