package service;

import java.util.Date;
import java.util.List;

import javax.naming.SizeLimitExceededException;

import domein.Bedrijf;
import domein.BesteldProduct;
import domein.Bestelling;
import domein.BestellingStatus;
import domein.Doos;
import domein.GeleverdBestellingState;
import domein.GeplaatstBestellingState;
import domein.Medewerker;
import domein.Product;
import domein.Transportdienst;
import domein.UitVoorLeveringBestellingState;
import domein.VerwerktBestellingState;
import domein.VerzondenBestellingState;
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

	public void maakBestelling(String orderID, Date datum, long leverancierID, long klantID,
			long transportdienstID, long aankoperId, String leveradresStraat, String leveradresNummer,
			String leveradresPostcode, String leveradresStad, String leveradresLand, long doosId) {
		try {
			Bedrijf leverancier = bedrijfService.getBedrijfById(leverancierID);
			Bedrijf klant = bedrijfService.getBedrijfById(klantID);
			Transportdienst td = dienstService.getTransportdienstByID(transportdienstID);
			Medewerker aankoper = userService.getMedewerkerById(aankoperId);
			Doos doos = doosService.getDoosById(doosId);
			Bestelling bestelling = new Bestelling(orderID, datum, leverancier, klant, td, aankoper,
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

		Bestelling bestelling = getBestellingIncludingCurrentState(bestellingID);
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

	private Bestelling getBestellingIncludingCurrentState(Long bestellingId) {
		
		Bestelling bestelling = bestellingRepo.get(bestellingId);
		
	    BestellingStatus status;
	    try {
	        status = BestellingStatus.valueOf(bestelling.getStatus().toUpperCase());
	    } catch (IllegalArgumentException e) {
	        throw new IllegalArgumentException("Ongeldige bestellingstatus: " + bestelling);
	    }

	    switch (status) {
	        case GEPLAATST ->
	            bestelling.toState(new GeplaatstBestellingState(bestelling));
	        case VERWERKT ->
            	bestelling.toState(new VerwerktBestellingState(bestelling));
	        case VERZONDEN ->
        		bestelling.toState(new VerzondenBestellingState(bestelling));
	        case UIT_VOOR_LEVERING ->
    			bestelling.toState(new UitVoorLeveringBestellingState(bestelling));
	        case GELEVERD ->
    			bestelling.toState(new GeleverdBestellingState(bestelling));
	        default -> 
	            throw new IllegalArgumentException("Ongeldige bestellingstatus: " + bestelling);
	    }
	    
	    return bestelling;
	}
	
	public void addBesteldProductToBestelling(long bestellingId, long productId, int aantal) {
		try {
			GenericDaoJpa.startTransaction();
			Bestelling bestelling = getBestellingIncludingCurrentState(bestellingId);
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

	public void wijzigBestelling(long bestellingId, long transportdienstId) throws SizeLimitExceededException {
		try {
			GenericDaoJpa.startTransaction();
			Bestelling bestelling = getBestellingIncludingCurrentState(bestellingId);
			Transportdienst transportdienst = dienstService.getTransportdienstByID(transportdienstId);
			bestelling.wijzigBestelling(transportdienst);
			bestellingRepo.update(bestelling);
			GenericDaoJpa.commitTransaction();
		} catch (SizeLimitExceededException e) {
			throw new SizeLimitExceededException(e.getMessage());
		}
		
		   catch (Exception e) {
			GenericDaoJpa.rollbackTransaction();
			throw new IllegalArgumentException(e.getMessage());
		}
	}
	
	public void verwerkBestelling(long bestellingId, long transportdienstId) throws SizeLimitExceededException {
		try {
			GenericDaoJpa.startTransaction();
			Bestelling bestelling = getBestellingIncludingCurrentState(bestellingId);
			Transportdienst transportdienst = dienstService.getTransportdienstByID(transportdienstId);
			bestelling.verwerkBestelling(transportdienst);
			bestellingRepo.update(bestelling);
			GenericDaoJpa.commitTransaction();
		} catch (SizeLimitExceededException e) {
			throw new SizeLimitExceededException(e.getMessage());
		}
		
		   catch (Exception e) {
			GenericDaoJpa.rollbackTransaction();
			throw new IllegalArgumentException(e.getMessage());
		}
	}
	
	public void verzendBestelling(long bestellingId) {
		try {
			GenericDaoJpa.startTransaction();
			Bestelling bestelling = getBestellingIncludingCurrentState(bestellingId);
			bestelling.verzendBestelling();
			bestellingRepo.update(bestelling);
			GenericDaoJpa.commitTransaction();
		} catch (Exception e) {
			GenericDaoJpa.rollbackTransaction();
			throw new IllegalArgumentException(e.getMessage());
		}
	}
	
	public void uitBestelling(long bestellingId) {
		try {
			GenericDaoJpa.startTransaction();
			Bestelling bestelling = getBestellingIncludingCurrentState(bestellingId);
			bestelling.uitBestelling();
			bestellingRepo.update(bestelling);
			GenericDaoJpa.commitTransaction();
		} catch (Exception e) {
			GenericDaoJpa.rollbackTransaction();
			throw new IllegalArgumentException(e.getMessage());
		}
	}
	
	public void leverBestelling(long bestellingId) {
		try {
			GenericDaoJpa.startTransaction();
			Bestelling bestelling = getBestellingIncludingCurrentState(bestellingId);
			bestelling.leverBestelling();
			bestellingRepo.update(bestelling);
			GenericDaoJpa.commitTransaction();
		} catch (Exception e) {
			GenericDaoJpa.rollbackTransaction();
			throw new IllegalArgumentException(e.getMessage());
		}
	}
	
	public void wijzigTrackAndTraceCode(long bestellingId) throws SizeLimitExceededException {
		try {
			GenericDaoJpa.startTransaction();
			Bestelling bestelling = getBestellingIncludingCurrentState(bestellingId);
			bestelling.wijzigTrackAndTraceCode();
			bestellingRepo.update(bestelling);
			GenericDaoJpa.commitTransaction();
		} catch (SizeLimitExceededException e) {
			throw new SizeLimitExceededException(e.getMessage());
		} catch (Exception e) {
			GenericDaoJpa.rollbackTransaction();
			throw new IllegalArgumentException(e.getMessage());
		}
	}

}
