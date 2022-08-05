package controllers;

public class User {
	
	// ATTRIBUTES
	private int idUser;
	private String firstname;
	private String lastname;
	private String email;
	
	// CONSTRUCTOR
	public User(int idUser, String firstname, String lastname, String email) {
		this.idUser = idUser;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
	}

	// GETTERS & SETTERS
	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	// TO STRING
	@Override
	public String toString() {
		return "User [idUser=" + idUser + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email
				+ "]";
	}
}
