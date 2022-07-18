package com.example.exprimonsnousapp.models;

public class CommentPost {

    // ATTRIBUTS
    String firstName;
    String lastName;
    //String fullname;
    String body;
    int idComment;
    int anonymous;

    // CONSTRUCTOR
    public CommentPost(String firstName, String lastName, String body, int idComment, int anonymous) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.body = body;
        this.idComment = idComment;
        this.anonymous = anonymous;
    }

    // GETTERS & SETTERS

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public int getAnonymous() {
        return anonymous;
    }

    public void setAnonymous(int anonymous) {
        this.anonymous = anonymous;
    }


    // TO STRING

    @Override
    public String toString() {
        return "CommentPost{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", body='" + body + '\'' +
                ", idComment=" + idComment +
                ", anonymous=" + anonymous +
                '}';
    }
}
