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
	@Column(name="ProblemsEncounteredThisPeriod", columnDefinition = "text", length=512)
	private String problemsEncountered;
	
	@Lob
	@Column(name="ProblemsAnticipatedNextPeriod", columnDefinition = "text", length=512)
	private String problemsAnticipated;
	
	@Column(name="TimeVariance")
	private double timeVariance;
	
	@Column(name="MDVariance")
	private double mdVariance;
	
	@Column(name="costVariance")
	private double costVariance;
}
