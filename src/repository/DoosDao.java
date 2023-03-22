package repository;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import domein.Bedrijf;
import domein.Doos;

public interface DoosDao extends GenericDao<Doos> {
	
	public List<Doos> getDozenVanBedrijf(Bedrijf bedrijf) throws EntityNotFoundException;
	
}


