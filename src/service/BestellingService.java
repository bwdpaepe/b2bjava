package service;

import java.util.Date;
import java.util.List;

import domein.Bedrijf;
import domein.Bestelling;
import domein.Transportdienst;
import repository.GenericDao;
import repository.GenericDaoJpa;

public class BestellingService {

	BedrijfService bedrijfService = new BedrijfService();
	DienstService dienstService = new DienstService();
	GenericDao<Bestelling> bestellingRepo;

	public BestellingService() {
		this.bestellingRepo = new GenericDaoJpa<>(Bestelling.class);
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
			throw new IllegalArgumentException("Er ging iets mis bij het aanmaken van je Bestelling");
		}

	}
	
	public List<Bestelling> getBestellingen(){
		
		List<Bestelling> bestellingen = bestellingRepo.findAll();
		return bestellingen;
	}

}
