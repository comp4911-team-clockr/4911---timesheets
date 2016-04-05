package comp4911.managers;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import comp4911.models.Project;

@Dependent
@Stateless

/**
 * <p>Basic CRUD Manager</p>
 * @author jackee
 *<p>The class handles create, read, update, delete.
 */
public class ProjectManager implements Serializable {
	@PersistenceContext(unitName="COMP4911ClockrProjectDatabase") EntityManager em;

	/**
	 *<p>Some Random SerialVersionUID.</p> 
	 */
	private static final long serialVersionUID = -7520188301250130738L;

	public Project find(int id) {
		return em.find(Project.class, id);
	}

	public void persist(Project project) {
		em.persist(project);
	}

	public void merge(Project project) {
		em.merge(project);
	}

	public void remove(Project project) {
		project = find(project.getProjectId());
		em.remove(project);
	}

	public Project findByUserId(String id) {
		TypedQuery<Project> query = em.createQuery("SELECT p FROM Project p WHERE p.projectId = :projectId ", Project.class);
		query.setParameter("projectId", id);
		java.util.List<Project> results = query.getResultList(); 
		Project proj = results.get(0);
		return proj;
	}

	public java.util.List<Project> getAll() {
		TypedQuery<Project> query = em.createQuery("SELECT p FROM Project p",
				Project.class); 
		java.util.List<Project> projects = query.getResultList();
		return projects;
	}

}
