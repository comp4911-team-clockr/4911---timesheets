package comp4911.managers;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import comp4911.models.WorkPackage;

@Dependent
@Stateless

/**
 * <p>Basic CRUD Manager</p>
 * @author jackee
 *<p>The class handles create, read, update, delete.
 */
public class WorkPackageManager implements Serializable {
	@PersistenceContext(unitName="COMP4911ClockrProjectDatabase") EntityManager em;

	/**
	 *<p>Some Random SerialVersionUID.</p> 
	 */
	
	@Inject
	private PayRateManager prManager;
	
	private static final long serialVersionUID = -7520188301250130738L;

	public WorkPackage find(String id) {
		return em.find(WorkPackage.class, id);
	}
	
	public void persist(WorkPackage wp) {
		em.persist(wp);
	}
	
	public void merge(WorkPackage wp) {
		em.merge(wp);
	}
	
	public void remove(WorkPackage wp) {
		wp.setActive(false);
		merge(wp);
	}
	
	public WorkPackage findByUserId(String id) {
		TypedQuery<WorkPackage> query = em.createQuery("SELECT w FROM WorkPackage w WHERE w.wpId = :wpId ", WorkPackage.class);
		query.setParameter("wpId", id);
		java.util.List<WorkPackage> results = query.getResultList(); 
		WorkPackage wp = results.get(0);
		return wp;
	}
	
	public java.util.List<WorkPackage> getAllByProject(int id) {
        TypedQuery<WorkPackage> query = em.createQuery("SELECT p FROM WorkPackage p "+
        		"WHERE p.isActive=TRUE AND p.projectId=" + id,
                WorkPackage.class); 
        java.util.List<WorkPackage> workPacks = query.getResultList();
        return workPacks;
	}
	
	public java.util.List<WorkPackage> getAll() {
	        TypedQuery<WorkPackage> query = em.createQuery("SELECT p FROM WorkPackage p "+
	        		"WHERE p.isActive=TRUE",
	                WorkPackage.class); 
	        java.util.List<WorkPackage> workPacks = query.getResultList();
	        return workPacks;
	}
	
	public double getAllWorkPackMD(String id) 
	{
		double total = 0;
		Object[] query = em.createQuery("SELECT "
				+ "p.mdp1, p.mdp2, p.mdp3, p.mdp4, p.mdp5, p.mdds, p.mdss,"
				+ "(p.mdp1+p.mdp2+p.mdp3+p.mdp4+p.mdp5+p.mdds+p.mdss)"
				+ " as total" 
				+ " FROM WorkPackage p "+
        		"WHERE p.isActive=TRUE AND" + " p.wpId = " + "'" + id + "'",
                Object[].class)
				.getSingleResult(); 
		String p1 = query[0].toString();
		String p2 = query[1].toString();
		String p3 = query[2].toString();
		String p4 = query[3].toString();
		String p5 = query[4].toString();
		String ds = query[5].toString();
		String ss = query[6].toString();
		//System.out.print(temp1 + " test");
		total += Double.parseDouble(p1);
		total += Double.parseDouble(p2);
		total += Double.parseDouble(p3);
		total += Double.parseDouble(p4);
		total += Double.parseDouble(p5);
		total += Double.parseDouble(ds);
		total += Double.parseDouble(ss);
		System.out.println(total + " total MDs for wp: " + id);
		return total;
	}

	public double getAllWorkPackBudget(String id) 
	{
		double total = 0;
		Object[] query = em.createQuery("SELECT "
				+ "p.mdp1, p.mdp2, p.mdp3, p.mdp4, p.mdp5, p.mdds, p.mdss,"
				+ "(p.mdp1+p.mdp2+p.mdp3+p.mdp4+p.mdp5+p.mdds+p.mdss)"
				+ " as total" 
				+ " FROM WorkPackage p "+
        		"WHERE p.isActive=TRUE AND" + " p.wpId = " + "'" + id + "'",
                Object[].class)
				.getSingleResult(); 
		String p1md = query[0].toString();
		String p2md = query[1].toString();
		String p3md = query[2].toString();
		String p4md = query[3].toString();
		String p5md = query[4].toString();
		String dsmd = query[5].toString();
		String ssmd = query[6].toString();
		Double P1 = Double.parseDouble(p1md);
		System.out.println(P1);
		Double P2 = Double.parseDouble(p2md);
		Double P3 = Double.parseDouble(p3md);
		Double P4 = Double.parseDouble(p4md);
		Double P5 = Double.parseDouble(p5md);
		Double DS = Double.parseDouble(dsmd);
		Double SS = Double.parseDouble(ssmd);	
		total += P1 * prManager.find("P1").getCostInMD();
		total += P2 * prManager.find("P2").getCostInMD();
		total += P3 * prManager.find("P3").getCostInMD();
		total += P4 * prManager.find("P4").getCostInMD();
		total += P5 * prManager.find("P5").getCostInMD();
		total += DS * prManager.find("DS").getCostInMD();
		total += SS * prManager.find("SS").getCostInMD();
		return total;
	
	}
}