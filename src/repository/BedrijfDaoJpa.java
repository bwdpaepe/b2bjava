package repository;


import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import domein.Bedrijf;
import domein.BestellingStatus;

public class BedrijfDaoJpa extends GenericDaoJpa<Bedrijf> implements BedrijfDao  {
    public BedrijfDaoJpa() {
        super(Bedrijf.class);
    }

	@Override
	public List<Object[]> findCustomersWithOrdersWithSpecificStatus(Long leverancierId, BestellingStatus status) throws EntityNotFoundException
	{
		try {
			Query query = em.createNamedQuery("Bedrijf.findKlantenWithOpenOrdersByLeverancier", Object[].class)
                 .setParameter("bedrijfId", leverancierId)
                 .setParameter("status", status);
			// FOR DEBUGGING
			//String sql = query.unwrap(Query.class).toString();
			//System.out.println(sql);
			return query.getResultList();
        } catch (NoResultException ex) {
            throw new EntityNotFoundException("Geen klanten met bestellingen met status '" + status + "' voor bedrijf met ID: " + leverancierId.toString());
        } 
	}
    
}
