package repository;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import domein.Bedrijf;
import domein.Bestelling;


public interface BestellingDao extends GenericDao<Bestelling> {
	
	public List<Bestelling> getBestellingenByLeverancierID(Bedrijf leverancier) throws EntityNotFoundException;

	public List<Bestelling> getBestellingInfoBijLeverancierVanKlant(Bedrijf leverancier, Bedrijf klant);

}
