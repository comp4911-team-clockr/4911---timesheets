package comp4911.models;

import java.io.Serializable;

public class Credential implements Serializable {

	/**
	 * Default serial version ID;
	 */
	private static final long serialVersionUID = 1L;
	
	private String userId;
	
	private String password;
	
	private String role;
	
	private String digSign;
	
	private String email;
	
	public Credential() {}
	
	public Credential(String userId, String password, String role, String email){
		this.setUserId(userId);
		this.setPassword(password);
		this.setRole(role);
		this.setEmail(email);
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDigSign() {
		return digSign;
	}

	public void setDigSign(String digSign) {
		this.digSign = digSign;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
}