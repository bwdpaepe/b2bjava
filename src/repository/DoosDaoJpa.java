package repository;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;

import domein.Bedrijf;

import domein.Doos;

public class DoosDaoJpa extends GenericDaoJpa<Doos> implements DoosDao {

	public DoosDaoJpa() {
		super(Doos.class);
	}

	@Override
	public List<Doos> getDozenVanBedrijf(Bedrijf bedrijf) throws EntityNotFoundException {
		try {
			return em.createNamedQuery("Doos.getDozenVanBedrijf", Doos.class).setParameter("bedrijf", bedrijf)
					.getResultList();
		} catch (NoResultException ex) {
			throw new EntityNotFoundException("Dit bedrijf heeft nog geen dozen");
		}
	}

}
