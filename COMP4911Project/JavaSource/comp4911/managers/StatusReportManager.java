//package comp4911.managers;
//
//import java.io.Serializable;
//
//import javax.ejb.Stateless;
//import javax.enterprise.context.Dependent;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.TypedQuery;
//
//import comp4911.models.StatusReport;
//import comp4911.models.WorkPackage;
//
//
//@Dependent
//@Stateless
//public class StatusReportManager implements Serializable {
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 5463145269486045946L;
//	@PersistenceContext(unitName="COMP4911ClockrProjectDatabase") EntityManager em;
//	
//	public StatusReport find(String id) {
//		return em.find(StatusReport.class, id);
//	}
//	
//	public void persist(StatusReport sr) {
//		em.persist(sr);
//	}
//	
//	public void merge(StatusReport sr) {
//		em.merge(sr);
//	}
//	
//	public StatusReport findByUserId(String id) {
//		TypedQuery<StatusReport> query = em.createQuery("SELECT w FROM WorkPackage w WHERE w.wpId = :wpId ", WorkPackage.class);
//		query.setParameter("wpId", id);
//		java.util.List<WorkPackage> results = query.getResultList(); 
//		WorkPackage wp = results.get(0);
//		return wp;
//	}
//}
