package comp4911.managers;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import comp4911.models.StatusReport;
import comp4911.models.WorkPackage;


@Dependent
@Stateless
public class StatusReportManager implements Serializable {
	/**
	 * RNG serialVersionUID
	 */
	private static final long serialVersionUID = 5463145269486045946L;
	@PersistenceContext(unitName="COMP4911ClockrProjectDatabase") EntityManager em;
	
	// db query to find status report through PK
	public StatusReport find(String id) {
		return em.find(StatusReport.class, id);
	}
	
	// db query to create new status report
	public void persist(StatusReport sr) {
		em.persist(sr);
	}
	
	// db query to update existing status report
	public void merge(StatusReport sr) {
		em.merge(sr);
	}

	// db query to find status report based on responsibility engineer id
	public StatusReport findByREngId(String id) {
		TypedQuery<StatusReport> query = em.createQuery("SELECT s FROM StatusReport s "+
				"WHERE s.RespEngId=" + "'" + id + "'", StatusReport.class);
		java.util.List<StatusReport> results = query.getResultList(); 
		StatusReport sr = results.get(0);
		return sr;
	}
	
	// db query to find all reports in a WP
	public java.util.List<StatusReport> getAllByWorkPackage (int id) {
        TypedQuery<StatusReport> query = em.createQuery("SELECT s FROM StatusReport s "+
        		"WHERE s.wpId=" + id,
                StatusReport.class); 
        java.util.List<StatusReport> reports = query.getResultList();
        return reports;
	}
	
	// db query to find all status reports in db
	public java.util.List<StatusReport> getAll() {
        TypedQuery<StatusReport> query = em.createQuery("SELECT s FROM StatusReport s ",
                StatusReport.class); 
        java.util.List<StatusReport> reports = query.getResultList();
        return reports;
	}
	
	/* db query to find status report by certain variance greater than decimal input
	ex: .5 = 50% variance
	*/
	public java.util.List<StatusReport> getByCostVariance (double cost)
	{
		TypedQuery<StatusReport> query = em.createQuery("Select s from StatusReport s "+
				"WHERE s.costVar > " + cost, StatusReport.class);
		java.util.List<StatusReport> reports = query.getResultList();
		return reports;
	}
	
}
