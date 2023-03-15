package repository;


import java.util.List;

import javax.persistence.EntityNotFoundException;

import domein.Bedrijf;
import domein.BestellingStatus;

public interface BedrijfDao extends GenericDao<Bedrijf>  {
        public List<Object[]> findCustomersWithOrdersWithSpecificStatus(Long leverancierId, BestellingStatus status) throws EntityNotFoundException;


}