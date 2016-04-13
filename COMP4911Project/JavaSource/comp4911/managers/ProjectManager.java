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
		project.setActive(false);
		merge(project);
	}

	public java.util.List<Project> getAll() {
		TypedQuery<Project> query = em.createQuery("SELECT p FROM Project p WHERE p.isActive=TRUE",
				Project.class);
		java.util.List<Project> projects = query.getResultList();
		return projects;
	}

	// method to get all projects emp is PM of
	public java.util.List<Project> getAllByPM(int empNum)
	{
		TypedQuery<Project> query = em.createQuery("SELECT p FROM Project p WHERE p.isActive=TRUE "+
				"AND p.EmpNum = " + empNum, Project.class);
		java.util.List<Project> projects = query.getResultList();
		return projects;
	}

	public int getNewPK() {
		TypedQuery<Project> query = em.createQuery("SELECT p FROM Project p",
				Project.class);
		java.util.List<Project> projects = query.getResultList();
		if (projects.isEmpty())
			return 1;
		return (projects.size() + 1);
	}
}
