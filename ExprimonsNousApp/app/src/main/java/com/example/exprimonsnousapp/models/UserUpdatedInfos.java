package com.example.exprimonsnousapp.models;

public class UserUpdatedInfos {

    // ATTRIBUTS
    private String firstName;
    private String lastName;
    private String birthDate;
    private String email;
    private String gender;
    private String areaCode;

    // CONSTRUCTEUR

    public UserUpdatedInfos(String firstName, String lastName, String birthDate, String email, String gender, String areaCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
        this.gender = gender;
        this.areaCode = areaCode;
    }


    // GETTER & SETTER

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

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    // ToString()

    @Override
    public String toString() {
        return "UserUpdatedInfos{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", areaCode='" + areaCode + '\'' +
                '}';
    }
}
