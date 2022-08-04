package objects;

public class LoginCredentialsResponse {
	
	// ATTRIBUTES
	private int idUser;
	
	// CONSTRUCTOR
	public LoginCredentialsResponse(int idUser) {
		this.idUser = idUser;
	}
	
	// GETTER & SETTER
	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	
	// TO STRING
	@Override
	public String toString() {
		return "LoginCredentialsResponse [idUser=" + idUser + "]";
	}	
}
