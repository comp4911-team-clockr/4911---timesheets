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
	 * 
	 */
	private static final long serialVersionUID = 5463145269486045946L;
	@PersistenceContext(unitName="COMP4911ClockrProjectDatabase") EntityManager em;
	
	public StatusReport find(String id) {
		return em.find(StatusReport.class, id);
	}
	
	public void persist(StatusReport sr) {
		em.persist(sr);
	}
	
	public void merge(StatusReport sr) {
		em.merge(sr);
	}

//	public StatusReport findByUserId(String id) {
//		TypedQuery<StatusReport> query = em.createQuery("SELECT s FROM StatusReport s WHERE s. = :statusReportId ", StatusReport.class);
//		query.setParameter("statusReportId", id);
//		java.util.List<StatusReport> results = query.getResultList(); 
//		StatusReport sr = results.get(0);
//		return sr;
//	}
	
	public java.util.List<StatusReport> getAllByWorkPackage (int id) {
        TypedQuery<StatusReport> query = em.createQuery("SELECT s FROM StatusReport s "+
        		"WHERE s.isActive IS TRUE AND s.wpId=" + id,
                StatusReport.class); 
        java.util.List<StatusReport> reports = query.getResultList();
        return reports;
	}
	
	public java.util.List<StatusReport> getAll() {
        TypedQuery<StatusReport> query = em.createQuery("SELECT s FROM StatusReport s "+
        		"WHERE s.isActive IS TRUE",
                StatusReport.class); 
        java.util.List<StatusReport> reports = query.getResultList();
        return reports;
	}
	
}
