package comp4911.models;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="MonthlyReport")
public class MonthlyReport implements Serializable {
	/**
	 * Default serial version ID;
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="MonthlyReportId")
	private String monthlyReportId;	
	
	@Column(name="EmpNum")
	private int pmEmpId;

	@Column(name="ReportDate")
	private Date reportDate;
	
	@Column(name="ProjectBudgetMD")
	private double pBudgetMD;
	
	@Column(name="ProjectBudgetCost")
	private double pBudgetCost;

	@Column(name="ActualToDateMD")
	private double toDateMD;
	
	@Column(name="ActualToDateCost")
	private double toDateCost;

	@Column(name="mdVar")
	private double totalMDVar;

	@Column(name="costVar")
	private double totalCostVar;

	@Column(name="pcCompletion")
	private double percentComplete;

	public String getMonthlyReportId() {
		return monthlyReportId;
	}

	public void setMonthlyReportId(String monthlyReportId) {
		this.monthlyReportId = monthlyReportId;
	}

	public int getPmEmpId() {
		return pmEmpId;
	}

	public void setPmEmpId(int pmEmpId) {
		this.pmEmpId = pmEmpId;
	}

	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public double getpBudgetMD() {
		return pBudgetMD;
	}

	public void setpBudgetMD(double pBudgetMD) {
		this.pBudgetMD = pBudgetMD;
	}

	public double getpBudgetCost() {
		return pBudgetCost;
	}

	public void setpBudgetCost(double pBudgetCost) {
		this.pBudgetCost = pBudgetCost;
	}

	public double getToDateMD() {
		return toDateMD;
	}

	public void setToDateMD(double toDateMD) {
		this.toDateMD = toDateMD;
	}

	public double getToDateCost() {
		return toDateCost;
	}

	public void setToDateCost(double toDateCost) {
		this.toDateCost = toDateCost;
	}

	public double getTotalMDVar() {
		return totalMDVar;
	}

	public void setTotalMDVar(double totalMDVar) {
		this.totalMDVar = totalMDVar;
	}

	public double getTotalCostVar() {
		return totalCostVar;
	}

	public void setTotalCostVar(double totalCostVar) {
		this.totalCostVar = totalCostVar;
	}

	public double getPercentComplete() {
		return percentComplete;
	}

	public void setPercentComplete(double percentComplete) {
		this.percentComplete = percentComplete;
	}


}
