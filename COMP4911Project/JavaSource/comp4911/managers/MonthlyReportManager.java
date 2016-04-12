package comp4911.managers;

import java.io.Serializable;
import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import comp4911.models.MonthlyReport;

@Dependent
@Stateless
public class MonthlyReportManager implements Serializable {
	/**
	 * RNG serialVersionUID
	 */
	private static final long serialVersionUID = 7630884777370210276L;
	@PersistenceContext(unitName="COMP4911ClockrProjectDatabase") EntityManager em;
	
		// db query to find monthly report through PK
		public MonthlyReport find(String id) {
			return em.find(MonthlyReport.class, id);
		}
		
		// db query to create new monthly report in db
		public void persist(MonthlyReport mr) {
			em.persist(mr);
		}
		
		// db query to update existing monthly report
		public void merge(MonthlyReport mr) {
			em.merge(mr);
		}
		
		// db query to find status report based on PM empNum
		public MonthlyReport findByPMEmpNum(int id) {
		TypedQuery<MonthlyReport> query = em.createQuery("SELECT m FROM MonthlyReport m "+
					"WHERE m.EmpNum=" + id, MonthlyReport.class);
			java.util.List<MonthlyReport> results = query.getResultList(); 
			MonthlyReport mr = results.get(0);
			return mr;
		}
		
		// db query to find all monthly reports in a project
		public java.util.List<MonthlyReport> getAllByProject(int id) {
			TypedQuery<MonthlyReport> query = em.createQuery("SELECT m FROM MonthlyReport m "+
	        		"WHERE m.monthlyReportId LIKE '" + id + "|%'", MonthlyReport.class);
	        java.util.List<MonthlyReport> reports = query.getResultList();
	        return reports;
		}

		// db query to find all status reports in db
		public java.util.List<MonthlyReport> getAll() {
	        TypedQuery<MonthlyReport> query = em.createQuery("SELECT m FROM MonthlyReport m ",
	                MonthlyReport.class); 
	        java.util.List<MonthlyReport> reports = query.getResultList();
	        return reports;
		}
}
	
	