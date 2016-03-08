package comp4911.controllers;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import comp4911.models.Credential;


@Dependent
@Stateless

/**
 * <p>Basic CRUD Manager</p>
 * @author jackee
 *<p>The class will attempt to handle create, read, update, delete.
 */
public class CredentialManager implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@PersistenceContext(unitName="COMP4911ClockrProjectDatabase") EntityManager em;

	public Credential find(String id) {
		return em.find(Credential.class, id);
	}
	
	public void persist(Credential credential) {
		em.persist(credential);
	}
	
	public void merge(Credential credential) {
		em.merge(credential);
	}
	
	public void remove(Credential credential) {
		credential = this.find(credential.getUserId());
		em.remove(credential);
	}
	
	public Credential getByCredentialId(String credentialID) {
		Credential credential = em.find(Credential.class, credentialID);
		return credential;
	}
	
	 public Credential[] getAll() {
	        TypedQuery<Credential> query = em.createQuery("select c from Credential c",
	                Credential.class); 
	        java.util.List<Credential> credentials = query.getResultList();
	        Credential[] credArray = new Credential[credentials.size()];
	        for (int i=0; i < credArray.length; i++) {
	            credArray[i] = credentials.get(i);
	        }
	        return credArray;
	    }
	
}
