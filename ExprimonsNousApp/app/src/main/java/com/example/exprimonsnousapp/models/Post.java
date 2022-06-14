package com.example.exprimonsnousapp.models;

public class Post {
    //attributes
    //private int idPost;
    private String firstname;
    private String lastname;
    private String body;
    private int likes;
    private int dislikes;
    private int nbComments;
    private int nbRewards;

    // CONSTRUCTEURS
    public Post() { }

    public Post(String firstname, String lastname, String body, int likes, int dislikes, int nbComments, int nbRewards/*,int idPost*/) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.body = body;
        this.likes = likes;
        this.dislikes = dislikes;
        this.nbComments = nbComments;
        this.nbRewards = nbRewards;
        //this.idPost = idPost;
    }


    // GETTERS & SETTERS

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

    public String getLikes() {
        return String.valueOf(likes);
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getDislikes() {
        return String.valueOf(dislikes);
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public String getNbComments() {
        return String.valueOf(nbComments);
    }

    public void setNbComments(int nbComments) {
        this.nbComments = nbComments;
    }

    public String getNbRewards() {
        return String.valueOf(nbRewards);
    }

    public void setNbRewards(int nbRewards) {
        this.nbRewards = nbRewards;
    }

    /*public String getIdPost() {
        return String.valueOf(idPost);
    }

    public void setIdPost(int idPost) {
        this.idPost = idPost;
    }*/
}
