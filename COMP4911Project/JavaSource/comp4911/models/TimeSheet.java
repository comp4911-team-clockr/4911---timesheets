package comp4911.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TimeSheet")
public class TimeSheet implements Serializable {

	/**
	 * Default serial version ID;
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="timeSID")
	private int timeSID;
	
	@Column(name="timeSName")
	private String timeSName;
	
	@Column(name="weekID")
	private int weekID;
	
	@Column(name="Signature")
	private String Signature;
	
	@Column(name="approval")
	private boolean approval;
	
	@Column(name="FlexTime")
	private int FlextTime;

	public TimeSheet(){}
	
	public int getTimeSID() {
		return timeSID;
	}

	public void setTimeSID(int timeSID) {
		this.timeSID = timeSID;
	}

	public String getTimeSName() {
		return timeSName;
	}

	public void setTimeSName(String timeSName) {
		this.timeSName = timeSName;
	}

	public int getWeekID() {
		return weekID;
	}

	public void setWeekID(int weekID) {
		this.weekID = weekID;
	}

	public String getSignature() {
		return Signature;
	}

	public void setSignature(String signature) {
		Signature = signature;
	}

	public boolean isApproval() {
		return approval;
	}

	public void setApproval(boolean approval) {
		this.approval = approval;
	}

	public int getFlextTime() {
		return FlextTime;
	}

	public void setFlextTime(int flextTime) {
		FlextTime = flextTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
