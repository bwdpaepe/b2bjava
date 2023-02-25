package domein;

import repository.GenericDaoJpa;
import repository.MedewerkerDao;
import repository.MedewerkerDaoJpa;

public class DomeinController
{
	private Medewerker m;
	private MedewerkerDao medewerkerRepo;
	private MedewerkerService medewerkerService; // service klasse om o.a aanmelden uit te werken

	public DomeinController()
	{
		setMedewerkerRepo(new MedewerkerDaoJpa());
		setMedewerkerService(new MedewerkerService(medewerkerRepo));
	}

	private void setMedewerkerService(MedewerkerService medewerkerService)
	{
		this.medewerkerService = medewerkerService;
	}

	public Medewerker getM()
	{
		return m;
	}

	public void setM(Medewerker m)
	{
		// TODO
	}

	public MedewerkerDao getMedewerkerRepo()
	{
		return medewerkerRepo;
	}

	public void setMedewerkerRepo(MedewerkerDao medewerkerRepo)
	{
		this.medewerkerRepo = medewerkerRepo;
	}

	// return een Data Transfer Object van Medewerker naar de GUI
	public MedewerkerDTO aanmelden(String emailAdress, String password)
	{
		return medewerkerService.aanmelden(emailAdress, password);
	}

	public void maakMedewerker(String voornaam, String familienaam, String emailadres, String password, 
			String functie, int personeelsNr)
	{
		medewerkerService.maakMedewerker(voornaam, familienaam, emailadres, password, functie, personeelsNr);

	}

	public void close()
	{
		GenericDaoJpa.closePersistency();
	}

}
