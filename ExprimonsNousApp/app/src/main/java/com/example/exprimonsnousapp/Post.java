package com.example.exprimonsnousapp;

public class Post {
    //attributes
    private String firstname;
    private String lastname;
    private String body;
    private int likes;
    private int dislikes;
    private int nbComments;
    private int nbRewards;

    //constructeur
    public Post(String firstname, String lastname, String body, int likes, int dislikes, int nbComments, int nbRewards) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.body = body;
        this.likes = likes;
        this.dislikes = dislikes;
        this.nbComments = nbComments;
        this.nbRewards = nbRewards;
    }

    public Post(String firstname, String lastname, String body) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.body = body;
        this.likes = 0;
        this.dislikes = 0;
        this.nbComments = 0;
        this.nbRewards = 0;
    }

    //Getters & setters
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public int getNbComments() {
        return nbComments;
    }

    public void setNbComments(int nbComments) {
        this.nbComments = nbComments;
    }

    public int getNbRewards() {
        return nbRewards;
    }

    public void setNbRewards(int nbRewards) {
        this.nbRewards = nbRewards;
    }
}
