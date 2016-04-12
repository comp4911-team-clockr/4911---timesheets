package comp4911.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PayRate")
public class PayRate implements Serializable{

	/**
	 * Generated serial version uid
	 */
	private static final long serialVersionUID = -3025525091099108655L;

	@Id
	@Column(name="PayRateId")
	private String payrateId;
	
	@Column(name="CostinMD")
	private double costInMD;
	
	@Column(name="IntRate")
	private double internalRate;
	
	@Column(name="ExtRate")
	private double externalRate;
	
	@Column(name="OvertimeRate")
	private double ovtRate;

	public String getPayrateId() {
		return payrateId;
	}

	public void setPayrateId(String payrateId) {
		this.payrateId = payrateId;
	}

	public double getCostInMD() {
		return costInMD;
	}

	public void setCostInMD(double costInMD) {
		this.costInMD = costInMD;
	}

	public double getInternalRate() {
		return internalRate;
	}

	public void setInternalRate(double internalRate) {
		this.internalRate = internalRate;
	}

	public double getExternalRate() {
		return externalRate;
	}

	public void setExternalRate(double externalRate) {
		this.externalRate = externalRate;
	}

	public double getOvtRate() {
		return ovtRate;
	}

	public void setOvtRate(double ovtRate) {
		this.ovtRate = ovtRate;
	}
	
}