package comp4911.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="TimeSheetRow")
public class TimeSheetRow implements Serializable {
    
    /**
     * Default serial version ID;
     */
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name="TimeSheetRowID")
    private String timeSheetRowId;

	@Column(name="ProjectId")
    private int projectId;
    
    @Column(name="WpId")
    private int workpackId;
    
    @Column(name="WeekTotalHrs")
    private double weekTotalHrs;
    
    @Column(name="SatHrs")
    private double satHrs;
    
    @Column(name="SunHrs")
    private double sunHrs;
    
    @Column(name="MonHrs")
    private double monHrs;
    
    @Column(name="TuesHrs")
    private double tuesHrs;
    
    @Column(name="WedHrs")
    private double wedHrs;
    
    @Column(name="ThursHrs")
    private double thursHrs;
    
    @Column(name="FriHrs")
    private double friHrs;
    
    @Column(name="VacationDays")
    private int vacationDays;

    @Column(name="FlextimeHrs")
    private double flexTimeHrs;
    
    @Column(name="Notes")
    private String notes;
    
    @ManyToOne
    @JoinColumn(name="TimeSheetId")
    private TimeSheet timesheet;
    
    public TimeSheetRow(){}
    
    public TimeSheet getTimeSheet(){
    	return timesheet;
    }
    
    public void setTimeSheet(TimeSheet timesheet){
    	this.timesheet = timesheet;
    }
    
    public String getTimeSheetRowId() {
        return timeSheetRowId;
    }

    public void setTimeSheetRowId(String timeSheetRowId) {
        this.timeSheetRowId = timeSheetRowId;
    }
    
    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
    public int getWorkpackId() {
        return workpackId;
    }

    public void setWorkpackId(int workpackId) {
        this.workpackId = workpackId;
    }
    
    public double getSatHrs() {
        return satHrs;
    }

    public void setSatHrs(double satHrs) {
        this.satHrs = satHrs;
    }
    public double getSunHrs() {
        return sunHrs;
    }

    public void setSunHrs(double sunHrs) {
        this.sunHrs = sunHrs;
    }
    
    public double getMonHrs() {
        return monHrs;
    }

    public void setMonHrs(double monHrs) {
        this.monHrs = monHrs;
    }
    
    public double getTuesHrs() {
        return tuesHrs;
    }

    public void setTuesHrs(double tuesHrs) {
        this.tuesHrs = tuesHrs;
    }
    
    public double getWedHrs() {
        return wedHrs;
    }

    public void setWedHrs(double wedHrs) {
        this.wedHrs = wedHrs;
    }
    
    public double getThursHrs() {
        return thursHrs;
    }

    public void setThursHrs(double thursHrs) {
        this.thursHrs = thursHrs;
    }
    
    public double getFriHrs() {
        return friHrs;
    }

    public void setFriHrs(double friHrs) {
        this.friHrs = friHrs;
    }
    
    public double getWeekTotalHrs() {
        return weekTotalHrs;
    }

    public void setWeekTotalHrs(double weekTotalHrs) {
        this.weekTotalHrs = weekTotalHrs;
    }
    
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
    
	public String toString(){
		return "" + timeSheetRowId + " " + timesheet.getTimeSID() + " " + projectId + " " + workpackId + " " + weekTotalHrs + " " 
				+ sunHrs + " " + monHrs + " " + tuesHrs + " " + wedHrs + " " + thursHrs + " " 
				+ friHrs + " " + notes;
	}

	public int getVacationDays() {
		return vacationDays;
	}

	public void setVacationDays(int vacationDays) {
		this.vacationDays = vacationDays;
	}

	public double getFlexTimeHrs() {
		return flexTimeHrs;
	}

	public void setFlexTimeHrs(double flexTimeHrs) {
		this.flexTimeHrs = flexTimeHrs;
	}

	public TimeSheet getTimesheet() {
		return timesheet;
	}

	public void setTimesheet(TimeSheet timesheet) {
		this.timesheet = timesheet;
	}
}
