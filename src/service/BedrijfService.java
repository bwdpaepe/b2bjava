package service;

import java.util.List;
import java.util.stream.Collectors;

import domein.Bedrijf;
import repository.BedrijfDao;
import repository.BedrijfDaoJpa;
import repository.GenericDaoJpa;

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

		} catch (Exception e)
		{
			GenericDaoJpa.rollbackTransaction();
		}
		GenericDaoJpa.commitTransaction();

	}

	public Bedrijf getBedrijfById(long bedrijfsId)
	{
		return bedrijfRepo.get(bedrijfsId);
		
	}
	
	public List<String[]> getListOfClientNamesWithNumberOfOpenOrders(long bedrijfsId) {
		List<Object[]> lijst = bedrijfRepo.findCustomersWithOrdersWithSpecificStatus(bedrijfsId, "open");
				
		// return a unmodifiable List of String-arrays.
		// In the array: klantName at index 0, klantID at index1, number of open Orders at index 2
		return lijst.stream()
		        .map(obj -> new String[]{((Bedrijf) obj[0]).getNaam(), String. valueOf(((Bedrijf) obj[0]).getID()) , obj[1].toString()})
		        .collect(Collectors.toUnmodifiableList());
	}
}
