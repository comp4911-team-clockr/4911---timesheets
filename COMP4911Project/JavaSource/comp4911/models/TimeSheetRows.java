package comp4911.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="TimeSheetRow")
public class TimeSheetRows implements Serializable {
    
    /**
     * Default serial version ID;
     */
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name="rowID")
    private int timeSheetRowId;
    
    @Column(name="projectID")
    private int projectId;
    
    @Column(name="wpID")
    private int workpackId;
    
    @Column(name="weekTotalHrs")
    private double weekTotalHrs;
    
    @Column(name="satHrs")
    private double satHrs;
    
    @Column(name="sunHrs")
    private double sunHrs;
    
    @Column(name="monHrs")
    private double monHrs;
    
    @Column(name="tuesHrs")
    private double tuesHrs;
    
    @Column(name="wedHrs")
    private double wedHrs;
    
    @Column(name="thursHrs")
    private double thursHrs;
    
    @Column(name="friHrs")
    private double friHrs;
    
    @Column(name="notes")
    private String notes;
    
    public TimeSheetRows(){}
    
    public int getTimeSheetRowId() {
        return timeSheetRowId;
    }

    public void setTimeSheetRowId(int timeSheetRowId) {
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
    
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    
}