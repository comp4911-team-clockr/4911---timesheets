package comp4911.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
//import javax.persistence.Transient;


@Entity
@Table(name="Credentials")
public class Credential implements Serializable {

	/**
	 * Default serial version ID;
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="UserId")
	private String userId;
	
	@Column(name="Password")
	private String password;
	
	@Column(name="RoleId")
	private int roleId;

	@Column(name="UserRole")
	private String role;
	
	@Column(name="DigiSign")
	private String digiSign;
	
	@Column(name="Email")
	private String email;
	
	@Column(name="RecoveryAnswer1")
	private String recovery1;
	
	@Column(name="RecoveryAnswer2")
	private String recovery2;
	
	@Column(name="RecoveryAnswer3")
	private String recovery3;
	
	@OneToOne(mappedBy="credential")
	private Employee employee;
	
	public Credential() {}
	
	public Credential(String userId, String password, int roleId, String role, String email){
		this.setUserId(userId);
		this.setPassword(password);
		this.setRoleId(roleId);
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
	
	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDigiSign() {
		return digiSign;
	}

	public void setDigiSign(String digiSign) {
		this.digiSign = digiSign;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public String getRecovery1() {
		return recovery1;
	}

	public void setRecovery1(String recovery1) {
		this.recovery1 = recovery1;
	}

	public String getRecovery2() {
		return recovery2;
	}

	public void setRecovery2(String recovery2) {
		this.recovery2 = recovery2;
	}

	public String getRecovery3() {
		return recovery3;
	}

	public void setRecovery3(String recovery3) {
		this.recovery3 = recovery3;
	}

}