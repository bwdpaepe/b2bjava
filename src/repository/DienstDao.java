package repository;

import java.util.List;
import javax.persistence.EntityNotFoundException;

import domein.Bedrijf;
import domein.Dienst;


public interface DienstDao extends GenericDao<Dienst> {
	public List<Dienst> findDienstenWithBedrijf(Bedrijf bedrijf) throws EntityNotFoundException;
}


