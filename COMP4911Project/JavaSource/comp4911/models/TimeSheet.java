package comp4911.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="TimeSheet")
public class TimeSheet implements Serializable {

	/**
	 * Default serial version ID;
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="timeSheetID")
	private int timeSID;
	
	@Column(name="empNum")
	private int empNumber;
	
	@Column(name="weekNum")
	private int weekNumber;
	
	@Transient
    private TimeSheetRows timeSheetRows;
	
	@Column(name="weekEnding")
	private String weekEnding;
	
	@Column(name="satTotalHrs")
    private double satTotalHrs;
	
	@Column(name="sunTotalHrs")
    private double sunTotalHrs;
	
	@Column(name="monTotalHrs")
    private double monTotalHrs;
	
	@Column(name="tuesTotalHrs")
    private double tuesTotalHrs;
	
	@Column(name="wedTotalHrs")
    private double wedTotalHrs;
	
	@Column(name="thursTotalHrs")
    private double thursTotalHrs;
	
	@Column(name="friTotalHrs")
    private double friTotalHrs;
	
	@Column(name="overallTotalHrs")
    private double overallTotalHrs;
	
	@Column(name="overtimeHrs")
    private double overtimeHrs;
	
	@Column(name="flextimeHrs")
    private double flextimeHrs;
	
	
	@Column(name="Signature")
	private String Signature;
	
	@Column(name="approval")
	private boolean approval;
	
    public TimeSheetRows getTimeSheetRows() {
        return timeSheetRows;
    }

    public void setTimeSheetRows(TimeSheetRows timeSheetRows) {
        this.timeSheetRows = timeSheetRows;
    }	

	public TimeSheet(){}
	
	public int getTimeSID() {
		return timeSID;
	}

	public void setTimeSID(int timeSID) {
		this.timeSID = timeSID;
	}

	public int getEmpNumber() {
		return empNumber;
	}

	public void setEmpNumber(int empNumber) {
		this.empNumber = empNumber;
	}

	public int getWeekNumber() {
		return weekNumber;
	}

	public void setWeekNumber(int weekNumber) {
		this.weekNumber = weekNumber;
	}

	public double getSatTotalHrs() {
        return satTotalHrs;
    }

    public void setSatTotalHrs(double satTotalHrs) {
        this.satTotalHrs = satTotalHrs;
    }
    public double getSunTotalHrs() {
        return sunTotalHrs;
    }

    public void setSunTotalHrs(double sunTotalHrs) {
        this.sunTotalHrs = sunTotalHrs;
    }
    
    public double getMonTotalHrs() {
        return monTotalHrs;
    }

    public void setMonTotalHrs(double monTotalHrs) {
        this.monTotalHrs = monTotalHrs;
    }
    
    public double getTuesTotalHrs() {
        return tuesTotalHrs;
    }

    public void setTuesTotalHrs(double tuesTotalHrs) {
        this.tuesTotalHrs = tuesTotalHrs;
    }
    
    public double getWedTotalHrs() {
        return wedTotalHrs;
    }

    public void setWedTotalHrs(double wedTotalHrs) {
        this.wedTotalHrs = wedTotalHrs;
    }
    
    public double getThursTotalHrs() {
        return thursTotalHrs;
    }

    public void setThursTotalHrs(double thursTotalHrs) {
        this.thursTotalHrs = thursTotalHrs;
    }
    
    public double getFriTotalHrs() {
        return friTotalHrs;
    }

    public void setFriTotalHrs(double friTotalHrs) {
        this.friTotalHrs = friTotalHrs;
    }
    
    public double getOverallTotalHrs() {
        return overallTotalHrs;
    }

    public void setOverallTotalHrs(double overallTotalHrs) {
        this.overallTotalHrs = overallTotalHrs;
    }
    public double getOvertimeHrs() {
        return overtimeHrs;
    }

    public void setOvertimeHrs(double overtimeHrs) {
        this.overtimeHrs = overtimeHrs;
    }
    
    public double getFlextimeHrs() {
        return flextimeHrs;
    }

    public void setflextimeHrs(double flextimeHrs) {
        this.flextimeHrs = flextimeHrs;
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


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
