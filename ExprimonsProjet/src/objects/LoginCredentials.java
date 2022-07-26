package objects;

public class LoginCredentials {
	
	// ATTRIBUTES
	private String email;
	private String password;
	
	// CONSTRUCTOR
	public LoginCredentials(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	
	// GETTERS & SETTERS
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
		return "LoginCredentials [email=" + email + ", password=" + password + "]";
	}
	
}
