package service;

import java.util.Date;
import java.util.List;

import domein.Bedrijf;
import domein.BesteldProduct;
import domein.Bestelling;
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
	BestellingDao bestellingRepo;

	public BestellingService() {
		this.bestellingRepo = new BestellingDaoJpa();
	}

	public void maakBestelling(String orderID, String status, Date datum, long leverancierID, long klantID,
			long transportdienstID, long aankoperId, String leveradresStraat, String leveradresNummer,String leveradresPostcode, String leveradresStad, 
			String leveradresLand) {
		try {
			Bedrijf leverancier = bedrijfService.getBedrijfById(leverancierID);
			Bedrijf klant = bedrijfService.getBedrijfById(klantID);
			Transportdienst td = dienstService.getTransportdienstByID(transportdienstID);
			Medewerker aankoper = userService.getMedewerkerById(aankoperId);
			Bestelling bestelling = new Bestelling(orderID, datum, status, leverancier, klant, td, aankoper, leveradresStraat,
					leveradresNummer, leveradresPostcode, leveradresStad, leveradresLand);

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
	
	public void addBesteldProductToBestelling (long bestellingId, long productId, int aantal) {
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
//			System.err.println(e.getMessage());
			throw new IllegalArgumentException(e.getMessage());
		}
	}

}
