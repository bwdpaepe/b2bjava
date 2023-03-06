package service;

import java.util.Date;
import java.util.List;

import domein.Bedrijf;
import domein.Bestelling;
import domein.Transportdienst;
import repository.BestellingDao;
import repository.BestellingDaoJpa;
import repository.GenericDao;
import repository.GenericDaoJpa;

public class BestellingService {

	BedrijfService bedrijfService = new BedrijfService();
	DienstService dienstService = new DienstService();
	BestellingDao bestellingRepo;

	public BestellingService() {
		this.bestellingRepo = new BestellingDaoJpa();
	}

	public void maakBestelling(String orderID, String status, Date datum, long leverancierID, long klantID,
			long transportdienstID) {
		try {
			Bedrijf leverancier = bedrijfService.getBedrijfById(leverancierID);
			Bedrijf klant = bedrijfService.getBedrijfById(klantID);
			Transportdienst td = dienstService.getTransportdienstByID(transportdienstID);
			Bestelling bestelling = new Bestelling(orderID, datum, status, leverancier, klant, td);

			GenericDaoJpa.startTransaction();
			bestellingRepo.insert(bestelling);
			GenericDaoJpa.commitTransaction();

		} catch (Exception e) {
			GenericDaoJpa.rollbackTransaction();
			throw new IllegalArgumentException(e.getMessage());
		}

	}
	
	public List<Bestelling> getBestellingen(long leverancierID){
		
		Bedrijf leverancier = bedrijfService.getBedrijfById(leverancierID);
		
		List<Bestelling> bestellingen = bestellingRepo.getBestellingenByLeverancierID(leverancier);
		bestellingen.sort((b1,b2) -> {return b1.getDatumGeplaatst().compareTo(b2.getDatumGeplaatst());});
		return bestellingen;
	}

}
