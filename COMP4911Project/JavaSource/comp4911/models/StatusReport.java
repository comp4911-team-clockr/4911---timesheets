package comp4911.models;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="StatusReport")
public class StatusReport implements Serializable {
	/**
	 * Default serial version ID;
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="StatusReportId")
	private String statusReportId;	
	
	@Column(name="RespEngId")
	private String respEngId;

	@Column(name="ReportDate")
	private Date reportDate;
	
	@Column(name="MDPlanned")
	private double mdPlanned;
	
	@Column(name="MDActual")
	private double mdActual;

	@Lob
	@Column(name="WorkAccomplishedThisPeriod", columnDefinition = "text", length=512)
	private String workAccomplished;
	
	@Lob
	@Column(name="WorkPlannedNextPeriod", columnDefinition = "text", length=512)
	private String workPlanned;
	
	@Lob
	@Column(name="ProblemsEncountered", columnDefinition = "text", length=512)
	private String problemsEncountered;
	
	@Lob
	@Column(name="ProblemsAnticipated", columnDefinition = "text", length=512)
	private String problemsAnticipated;
	
	@Column(name="timeVar")
	private double timeVariance;
	
	@Column(name="mdVar")
	private double mdVariance;
	
	@Column(name="costVar")
	private double costVariance;

	public String getStatusReportId() {
		return statusReportId;
	}

	public void setStatusReportId(String statusReportId) {
		this.statusReportId = statusReportId;
	}

	public String getRespEngId() {
		return respEngId;
	}

	public void setRespEngId(String respEngId) {
		this.respEngId = respEngId;
	}

	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public double getMdPlanned() {
		return mdPlanned;
	}

	public void setMdPlanned(double mdPlanned) {
		this.mdPlanned = mdPlanned;
	}

	public double getMdActual() {
		return mdActual;
	}

	public void setMdActual(double mdActual) {
		this.mdActual = mdActual;
	}

	public String getWorkAccomplished() {
		return workAccomplished;
	}

	public void setWorkAccomplished(String workAccomplished) {
		this.workAccomplished = workAccomplished;
	}

	public String getWorkPlanned() {
		return workPlanned;
	}

	public void setWorkPlanned(String workPlanned) {
		this.workPlanned = workPlanned;
	}

	public String getProblemsEncountered() {
		return problemsEncountered;
	}

	public void setProblemsEncountered(String problemsEncountered) {
		this.problemsEncountered = problemsEncountered;
	}

	public String getProblemsAnticipated() {
		return problemsAnticipated;
	}

	public void setProblemsAnticipated(String problemsAnticipated) {
		this.problemsAnticipated = problemsAnticipated;
	}

	public double getTimeVariance() {
		return timeVariance;
	}

	public void setTimeVariance(double timeVariance) {
		this.timeVariance = timeVariance;
	}

	public double getMdVariance() {
		return mdVariance;
	}

	public void setMdVariance(double mdVariance) {
		this.mdVariance = mdVariance;
	}

	public double getCostVariance() {
		return costVariance;
	}

	public void setCostVariance(double costVariance) {
		this.costVariance = costVariance;
	}

}
