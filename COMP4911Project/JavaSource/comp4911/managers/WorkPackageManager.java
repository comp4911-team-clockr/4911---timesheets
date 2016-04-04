package comp4911.managers;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
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
		wp = find(wp.getWpId());
		em.remove(wp);
	}
	
	public WorkPackage findByUserId(String id) {
		TypedQuery<WorkPackage> query = em.createQuery("SELECT w FROM WorkPackage w WHERE w.wpId = :wpId ", WorkPackage.class);
		query.setParameter("wpId", id);
		java.util.List<WorkPackage> results = query.getResultList(); 
		WorkPackage wp = results.get(0);
		return wp;
	}
	
	public java.util.List<WorkPackage> getAll() {
	        TypedQuery<WorkPackage> query = em.createQuery("SELECT p FROM WorkPackage p",
	                WorkPackage.class); 
	        java.util.List<WorkPackage> workPacks = query.getResultList();
	        return workPacks;
	    }
	
}
