package com.example.exprimonsnousapp.models;

public class NewAccount {
    /**
     *
     *                  String firstnameTXT = firstname.getText().toString();
     *                 String lastnameTXT = lastname.getText().toString();
     *                 String birthdateTXT = birthdate.getText().toString();
     *                 String emailTXT = email.getText().toString();
     *                 String genderTXT = gender_spinner.getSelectedItem().toString();
     *                 String areaCodeTXT = areaCode.getText().toString();
     *                 String passwdTXT = passwd.getText().toString();
     *
     * */

    // ATTRIBUTS
    private String firstName;
    private String lastName;
    private String birthDate;
    private String email;
    private String gender;
    private String areaCode;
    private String password;

    // CONSTRUCTOR
    public NewAccount(String firstName, String lastName, String birthDate, String email, String gender, String areaCode, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
        this.gender = gender;
        this.areaCode = areaCode;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // TO STRING
    @Override
    public String toString() {
        return "NewAccount{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", areaCode='" + areaCode + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
