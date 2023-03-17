package repository;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import domein.Bedrijf;
import domein.Bestelling;
import domein.BestellingDetails;

public interface BestellingDao extends GenericDao<Bestelling> {
	
	public List<Bestelling> getBestellingenByLeverancierID(Bedrijf leverancier) throws EntityNotFoundException;

	public List<BestellingDetails> getBestellingInfoBijLeverancierVanKlant(long leverancierId, long klantId);

}
