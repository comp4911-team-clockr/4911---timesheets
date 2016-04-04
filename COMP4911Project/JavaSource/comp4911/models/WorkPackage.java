package comp4911.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="WorkPackage")
public class WorkPackage implements Serializable {

	/**
	 * Default serial version ID;
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="WpId")
	private String wpId;
	
	@Column(name="WpNum")
	private String wpNum;
	
	@Column(name="WpTitle")
	private String wpTitle;
	
	@Column(name="Customer")
	private String customer;
	
	@Column(name="RespEngId")
	private String respId;
	
	@Column(name="MDP1")
	private int mdp1;
	
	@Column(name="MDP2")
	private int mdp2;
	
	@Column(name="MDP3")
	private int mdp3;
	
	@Column(name="MDP4")
	private int mdp4;
	
	@Column(name="MDP5")
	private int mdp5;
	
	@Column(name="MDDS")
	private int mdds;
	
	@Column(name="MDSS")
	private int mdss;
	
	@Column(name="ProjectId")
	private int projectId;
	
	@Column(name="IsActive")
	private boolean isActive;

	public WorkPackage(){}
	
	public String getWpId() {
		return wpId;
	}

	public void setWpId(String wpId) {
		this.wpId = wpId;
	}

	public String getWpNum() {
		return wpNum;
	}

	public void setWpNum(String wpNum) {
		this.wpNum = wpNum;
	}

	public String getWpTitle() {
		return wpTitle;
	}

	public void setWpTitle(String wpTitle) {
		this.wpTitle = wpTitle;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getRespId() {
		return respId;
	}

	public void setRespId(String respId) {
		this.respId = respId;
	}

	public int getMdp1() {
		return mdp1;
	}

	public void setMdp1(int mdp1) {
		this.mdp1 = mdp1;
	}

	public int getMdp2() {
		return mdp2;
	}

	public void setMdp2(int mdp2) {
		this.mdp2 = mdp2;
	}

	public int getMdp3() {
		return mdp3;
	}

	public void setMdp3(int mdp3) {
		this.mdp3 = mdp3;
	}

	public int getMdp4() {
		return mdp4;
	}

	public void setMdp4(int mdp4) {
		this.mdp4 = mdp4;
	}

	public int getMdp5() {
		return mdp5;
	}

	public void setMdp5(int mdp5) {
		this.mdp5 = mdp5;
	}

	public int getMdds() {
		return mdds;
	}

	public void setMdds(int mdds) {
		this.mdds = mdds;
	}

	public int getMdss() {
		return mdss;
	}

	public void setMdss(int mdss) {
		this.mdss = mdss;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}