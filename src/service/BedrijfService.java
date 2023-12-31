package service;

import java.util.List;
import java.util.stream.Collectors;

import domein.Bedrijf;
import domein.Bestelling;
import domein.BestellingStatus;
import domein.Doos;
import domein.KlantEnAantalBestellingen;
import domein.Product;
import domein.User;
import repository.BedrijfDao;
import repository.BedrijfDaoJpa;
import repository.BestellingDao;
import repository.BestellingDaoJpa;
import repository.GenericDaoJpa;
import repository.KlantAankopersBestellingenDTO;
import repository.KlantLijstEntryDTO;
import repository.UserDao;
import repository.UserDaoJpa;

public class BedrijfService
{
	private BedrijfDao bedrijfRepo;
	private UserDao userRepo;
	private BestellingDao bestellingRepo;

	public BedrijfService()
	{
		this.bedrijfRepo = new BedrijfDaoJpa();
		this.userRepo = new UserDaoJpa();
		this.bestellingRepo = new BestellingDaoJpa();
	}

	// nodig voor mockito
	public BedrijfService(BedrijfDao bedrijfRepo)
	{
		this.bedrijfRepo = bedrijfRepo;
	}

	public void maakBedrijf(String naam, String straat, String huisnummer, String postcode, String stad, String land,
			String telefoonnummer, String logo_filename)
	{

		GenericDaoJpa.startTransaction();
		try
		{
			bedrijfRepo
					.insert(new Bedrijf(naam, straat, huisnummer, postcode, stad, land, telefoonnummer, logo_filename));
			GenericDaoJpa.commitTransaction();
		} catch (Exception e)
		{
			GenericDaoJpa.rollbackTransaction();
			System.err.println(e.getMessage());
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	public Bedrijf getBedrijfById(long bedrijfsId)
	{
		return bedrijfRepo.get(bedrijfsId);
		
	}
	
	public List<KlantLijstEntryDTO> getListOfClientNamesWithNumberOfOpenOrders(long bedrijfsId) {
		List<KlantEnAantalBestellingen> lijst = bedrijfRepo.findCustomersWithOrdersWithSpecificStatus(bedrijfsId, BestellingStatus.GEPLAATST);
		
		// return a unmodifiable List of KlantLijstEntryDTO objects
		return lijst.stream()
		        .map(obj -> new KlantLijstEntryDTO(obj))
		        .collect(Collectors.toUnmodifiableList());
	}
	

	
	public void maakProduct(long leveranciersId, String naam, double eenheidsprijs) {
		GenericDaoJpa.startTransaction();
		try {
	        Bedrijf leverancier = bedrijfRepo.get(leveranciersId);
	        Product product = new Product(naam, eenheidsprijs, leverancier);
	        leverancier.getProductenTeKoop().add(product);
	        bedrijfRepo.update(leverancier);
	        GenericDaoJpa.commitTransaction();
		} catch (Exception e) {
			GenericDaoJpa.rollbackTransaction();
//			System.err.println(e.getMessage());
			throw new IllegalArgumentException(e.getMessage());
		};
	}

	public KlantAankopersBestellingenDTO getDetailsOfClient(long leverancierId, long klantId)
	{
		Bedrijf klant = bedrijfRepo.get(klantId);
		Bedrijf leverancier = bedrijfRepo.get(leverancierId);
		List<Bestelling> bestellingen = bestellingRepo.getBestellingInfoBijLeverancierVanKlant(leverancier, klant);
		List<User> aankopers = userRepo.getAankopersFromCompany(klantId);
		
		return new KlantAankopersBestellingenDTO(klant, aankopers, bestellingen);
	}
}
