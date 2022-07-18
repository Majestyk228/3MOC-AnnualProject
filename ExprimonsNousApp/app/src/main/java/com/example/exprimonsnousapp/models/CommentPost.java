package com.example.exprimonsnousapp.models;

public class CommentPost {

    // ATTRIBUTS
    String fullname;
    String body;
    int idComment;

    //CONSTRUCTOR
    public CommentPost(String fullname, String body, int idComment) {
        this.fullname = fullname;
        this.body = body;
        this.idComment = idComment;
    }


    // GETTERS & SETTERS
    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getIdComment() {
        return idComment;
    }

    public void setIdComment(int idComment) {
        this.idComment = idComment;
    }

    // TO STRING

    @Override
    public String toString() {
        return "CommentPost{" +
                "fullname='" + fullname + '\'' +
                ", body='" + body + '\'' +
                ", idComment=" + idComment +
                '}';
    }
}
