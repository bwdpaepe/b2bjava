package repository;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;

import domein.Bedrijf;
import domein.Dienst;

public class DienstDaoJpa extends GenericDaoJpa<Dienst> implements DienstDao {

	public DienstDaoJpa() {
		super(Dienst.class);
	}

	@Override
	public List<Dienst> findDienstenWithBedrijf(Bedrijf bedrijf) throws EntityNotFoundException {
		try {
            return em.createNamedQuery("Dienst.findDienstenWithBedrijf", Dienst.class)
                 .setParameter("bedrijf", bedrijf)
                .getResultList();
        } catch (NoResultException ex) {
            throw new EntityNotFoundException();
        } 
	}
}
