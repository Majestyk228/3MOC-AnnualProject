package com.exprimonsnousprojet.gestiondeprojetfx;

public class User {
    public String firstname;
    public String name;
    public Boolean isAdmin;

    public User(String firstname, String name) {
        this.firstname = firstname;
        this.name = name;
        this.isAdmin = false;
    }

    public User(String firstname, String name, Boolean isAdmin) {
        this.firstname = firstname;
        this.name = name;
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString() {
        return firstname + " " + name;
    }
}
