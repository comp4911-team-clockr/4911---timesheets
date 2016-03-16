package comp4911.models;

import java.io.Serializable;
import java.sql.Date;
//import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
//import javax.persistence.Transient;


@Entity
@Table(name="TimeSheet")
public class TimeSheet implements Serializable {

	/**
	 * Default serial version ID;
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="TimesheetId")
	private int timesheetId;
	
	@Column(name="EmpNum")
	private int empNumber;
	
	@Column(name="WeekNum")
	private int weekNumber;
	
	@Column(name="WeekEnding")
	private Date weekEnding;
	
	@Column(name="SatTotalHrs")
    private double satTotalHrs;
	
	@Column(name="SunTotalHrs")
    private double sunTotalHrs;
	
	@Column(name="MonTotalHrs")
    private double monTotalHrs;
	
	@Column(name="TuesTotalHrs")
    private double tuesTotalHrs;
	
	@Column(name="WedsTotalHrs")
    private double wedTotalHrs;
	
	@Column(name="ThursTotalHrs")
    private double thursTotalHrs;
	
	@Column(name="FriTotalHrs")
    private double friTotalHrs;
	
	@Column(name="OverallTotalHrs")
    private double overallTotalHrs;
	
	@Column(name="OvertimeTotalHrs")
    private double overtimeHrs;
	
	@Column(name="FlextimeHrs")
    private double flextimeHrs;
	
	@Column(name="Signature")
	private String Signature;
	
	@Column(name="Approval")
	private String approval;
	
	@Column(name="IsActive")
	private boolean isActive;
	
	@OneToMany(mappedBy="timesheet", fetch = FetchType.EAGER, cascade=CascadeType.ALL, orphanRemoval=true)
	private List<TimeSheetRow> timesheetrows;
	
	public List<TimeSheetRow> getTimeSheetRows(){
		return timesheetrows;
	}
	public void setTimeSheetRows(List<TimeSheetRow> timesheetrows){
		this.timesheetrows = timesheetrows;
	}
	
	public TimeSheet(){}
	
	public int getTimeSID() {
		return timesheetId;
	}

	public void setTimeSID(int timesheetId) {
		this.timesheetId = timesheetId;
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

	public String isApproval() {
		return approval;
	}

	public void setApproval(String approval) {
		this.approval = approval;
	}
	
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	public boolean getIsActive() {
		return isActive;
	}
	
	public String toString(){
		return "" + timesheetId + " " + empNumber + " " + weekNumber + " " + satTotalHrs + " " + sunTotalHrs 
				+ " " + monTotalHrs + " " + tuesTotalHrs + " " + wedTotalHrs + " " + thursTotalHrs + " " 
				+ friTotalHrs + " " + overallTotalHrs + " " + overtimeHrs + " " + flextimeHrs;
	}
}
