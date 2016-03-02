package comp4911.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="TimeSheetRow")
public class TimeSheetRows implements Serializable {
    
    @Id
    @Column(name="TimeSheetRowID")
    private int timeSheetRowId;
    
    @Column(name="projectID")
    private int projectId;
    
    @Column(name="workpackID")
    private int workpackId;
    
    @Column(name="hours")
    private int hours;
    
    @Column(name="dayofWeek")
    private int day;
    
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
    
    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }
    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
    
}