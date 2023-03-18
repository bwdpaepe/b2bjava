package repository;


import java.util.List;

import javax.persistence.EntityNotFoundException;

import domein.Bedrijf;
import domein.BestellingStatus;
import domein.KlantEnAantalBestellingen;

public interface BedrijfDao extends GenericDao<Bedrijf>  {
        public List<KlantEnAantalBestellingen> findCustomersWithOrdersWithSpecificStatus(Long leverancierId, BestellingStatus status) throws EntityNotFoundException;


}