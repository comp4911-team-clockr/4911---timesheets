package comp4911.managers;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import comp4911.models.PayRate;

@Dependent
@Stateless

public class PayRateManager implements Serializable {
	/**
	 * generated serial version uid
	 */
	private static final long serialVersionUID = -2701881394042672209L;
	@PersistenceContext(unitName="COMP4911ClockrProjectDatabase") EntityManager em;

	// finds payrate by its ID
	public PayRate find(String id) {
		return em.find(PayRate.class, id);
	}
		
//	following operations not currently needed: 
//	public void persist(PayRate payRate) {
//		em.persist(payRate);
//	}
//
//	public void merge(PayRate payRate) {
//		em.merge(payRate);
//	}
	
}
