package service;

import domein.Bedrijf;
import repository.GenericDao;
import repository.GenericDaoJpa;

public class BedrijfService
{
	private GenericDao<Bedrijf> bedrijfRepo;

	public BedrijfService()
	{
		this.bedrijfRepo = new GenericDaoJpa<>(Bedrijf.class);
	}

	// nodig voor mockito
	public BedrijfService(GenericDao<Bedrijf> bedrijfRepo)
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

	public Bedrijf getBedrijfById(int bedrijfsId)
	{
		return bedrijfRepo.get(Long.valueOf(bedrijfsId));
		
	}
}
