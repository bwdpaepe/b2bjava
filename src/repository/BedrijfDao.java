package repository;


import java.util.List;

import javax.persistence.EntityNotFoundException;

import domein.Bedrijf;

public interface BedrijfDao extends GenericDao<Bedrijf>  {
        public List<Object[]> findCustomersWithOrdersWithSpecificStatus(Long leverancierId, String status) throws EntityNotFoundException;


}