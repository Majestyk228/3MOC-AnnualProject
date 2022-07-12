package com.example.exprimonsnousapp.models;

public class VoteOption {

    // ATTRIBUTS
    private int idVoteOptions;
    private String label;

    // CONTRUCTOR
    public VoteOption(int idVoteOptions, String label) {
        this.idVoteOptions = idVoteOptions;
        this.label = label;
    }

    // GETTERS & SETTERS
    public int getIdVoteOptions() {
        return idVoteOptions;
    }

    public void setIdVoteOptions(int idVoteOptions) {
        this.idVoteOptions = idVoteOptions;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }


    // TO STRING
    @Override
    public String toString() {
        return "VoteOption{" +
                "idVoteOptions=" + idVoteOptions +
                ", label='" + label + '\'' +
                '}';
    }
}
