package service;

import java.util.List;
import java.util.stream.Collectors;

import domein.Bedrijf;
import domein.BestellingStatus;
import domein.Doos;
import repository.BedrijfDao;
import repository.BedrijfDaoJpa;
import repository.GenericDaoJpa;
import repository.KlantLijstEntryDTO;

public class BedrijfService
{
	private BedrijfDao bedrijfRepo;

	public BedrijfService()
	{
		this.bedrijfRepo = new BedrijfDaoJpa();
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
		List<Object[]> lijst = bedrijfRepo.findCustomersWithOrdersWithSpecificStatus(bedrijfsId, BestellingStatus.GEPLAATST);
		
		// return a unmodifiable List of KlantLijstEntryDTO objects
		return lijst.stream()
		        .map(obj -> new KlantLijstEntryDTO(obj))
		        .collect(Collectors.toUnmodifiableList());
	}
	
	public void maakDoos(long bedrijfsId, String naam, String doosTypeString, double hoogte, double breedte, double lengte, double prijs) {
		GenericDaoJpa.startTransaction();
		try {
	        Bedrijf bedrijf = bedrijfRepo.get(bedrijfsId);
	        Doos doos = new Doos(naam, hoogte, breedte, lengte, doosTypeString, prijs, bedrijf);
	        bedrijf.getDozen().add(doos);
	        bedrijfRepo.update(bedrijf);
	        GenericDaoJpa.commitTransaction();
		} catch (Exception e) {
			GenericDaoJpa.rollbackTransaction();
			System.err.println(e.getMessage());
			throw new IllegalArgumentException(e.getMessage());
		};
	}
}
