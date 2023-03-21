package service;

import java.util.Date;
import java.util.List;

import domein.Bedrijf;
import domein.BesteldProduct;
import domein.Bestelling;
import domein.BestellingStatus;
import domein.Doos;
import domein.Medewerker;
import domein.Product;
import domein.Transportdienst;
import repository.BestellingDao;
import repository.BestellingDaoJpa;
import repository.GenericDaoJpa;

public class BestellingService {

	BedrijfService bedrijfService = new BedrijfService();
	DienstService dienstService = new DienstService();
	UserService userService = new UserService();
	ProductService productService = new ProductService();
	DoosService doosService = new DoosService();
	NotificatieService notificatieService = new NotificatieService();
	BestellingDao bestellingRepo;

	public BestellingService() {
		this.bestellingRepo = new BestellingDaoJpa();
	}

	public void maakBestelling(String orderID, String status, Date datum, long leverancierID, long klantID,
			long transportdienstID, long aankoperId, String leveradresStraat, String leveradresNummer,
			String leveradresPostcode, String leveradresStad, String leveradresLand, long doosId) {
		try {
			Bedrijf leverancier = bedrijfService.getBedrijfById(leverancierID);
			Bedrijf klant = bedrijfService.getBedrijfById(klantID);
			Transportdienst td = dienstService.getTransportdienstByID(transportdienstID);
			Medewerker aankoper = userService.getMedewerkerById(aankoperId);
			Doos doos = doosService.getDoosById(doosId);
			Bestelling bestelling = new Bestelling(orderID, datum, status, leverancier, klant, td, aankoper,
					leveradresStraat, leveradresNummer, leveradresPostcode, leveradresStad, leveradresLand, doos);

			GenericDaoJpa.startTransaction();
			bestellingRepo.insert(bestelling);
			GenericDaoJpa.commitTransaction();

		} catch (Exception e) {
			GenericDaoJpa.rollbackTransaction();
			throw new IllegalArgumentException(e.getMessage());
		}

	}

	public Bestelling getBestelling(long bestellingID) {

		Bestelling bestelling = bestellingRepo.get(bestellingID);
		return bestelling;
	}

	public List<Bestelling> getBestellingen(long leverancierID) {

		Bedrijf leverancier = bedrijfService.getBedrijfById(leverancierID);

		List<Bestelling> bestellingen = bestellingRepo.getBestellingenByLeverancierID(leverancier);
		bestellingen.sort((b1, b2) -> {
			return b1.getDatumGeplaatst().compareTo(b2.getDatumGeplaatst());
		});
		return bestellingen;
	}

	public void addBesteldProductToBestelling(long bestellingId, long productId, int aantal) {
		try {
			GenericDaoJpa.startTransaction();
			Bestelling bestelling = bestellingRepo.get(bestellingId);
			Product product = productService.getProductById(productId);
			BesteldProduct besteldProduct = new BesteldProduct(product, aantal, bestelling);
			bestelling.addBesteldProductToBestelling(besteldProduct);
			bestellingRepo.update(bestelling);
			GenericDaoJpa.commitTransaction();
		} catch (Exception e) {
			GenericDaoJpa.rollbackTransaction();
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	public void wijzigBestelling(long bestellingId, long transportdienstId) {
		try {
			GenericDaoJpa.startTransaction();
			Bestelling bestelling = bestellingRepo.get(bestellingId);
			Transportdienst transportdienst = dienstService.getTransportdienstByID(transportdienstId);
			bestelling.wijzigBestelling(transportdienst);
			bestellingRepo.update(bestelling);
			GenericDaoJpa.commitTransaction();
		} catch (Exception e) {
			GenericDaoJpa.rollbackTransaction();
			throw new IllegalArgumentException(e.getMessage());
		}
	}
	
	public void verwerkBestelling(long bestellingId, long transportdienstId) {
		try {
			GenericDaoJpa.startTransaction();
			Bestelling bestelling = bestellingRepo.get(bestellingId);
			Transportdienst transportdienst = dienstService.getTransportdienstByID(transportdienstId);
			bestelling.verwerkBestelling(transportdienst);
			bestellingRepo.update(bestelling);
			GenericDaoJpa.commitTransaction();
		} catch (Exception e) {
			GenericDaoJpa.rollbackTransaction();
			throw new IllegalArgumentException(e.getMessage());
		}
	}
	
	public void wijzigTrackAndTraceCode(long bestellingId) {
		try {
			GenericDaoJpa.startTransaction();
			Bestelling bestelling = bestellingRepo.get(bestellingId);
			bestelling.wijzigTrackAndTraceCode();
			bestellingRepo.update(bestelling);
			GenericDaoJpa.commitTransaction();
		} catch (Exception e) {
			GenericDaoJpa.rollbackTransaction();
			throw new IllegalArgumentException(e.getMessage());
		}
	}

}
