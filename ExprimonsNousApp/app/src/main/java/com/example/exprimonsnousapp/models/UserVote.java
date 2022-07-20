package com.example.exprimonsnousapp.models;

public class UserVote {

    /**
     * INSERT INTO Votes (idUser, idVote, choice) VALUES (6,1,2);
     * */

    // ATTRIBUTES
    private int idUser;
    private int idVote;
    private int choice;

    // CONSTRUCTOR
    public UserVote(int idUser, int idVote, int choice) {
        this.idUser = idUser;
        this.idVote = idVote;
        this.choice = choice;
    }

    // GETTERS & SETTERS
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdVote() {
        return idVote;
    }

    public void setIdVote(int idVote) {
        this.idVote = idVote;
    }

    public int getChoice() {
        return choice;
    }

    public void setChoice(int choice) {
        this.choice = choice;
    }

    // TO STRING
    @Override
    public String toString() {
        return "UserVote{" +
                "idUser=" + idUser +
                ", idVote=" + idVote +
                ", choice=" + choice +
                '}';
    }
}
