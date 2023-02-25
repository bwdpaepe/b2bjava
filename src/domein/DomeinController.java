package domein;

import repository.GenericDaoJpa;

import repository.UserDao;
import repository.UserDaoJpa;
import repository.MedewerkerDTO;

public class DomeinController
{
	private Medewerker m;
	private UserDao userRepo;
	private UserService userService; // service klasse om o.a aanmelden uit te werken

	public DomeinController()
	{
		setUserRepo(new UserDaoJpa());
		setMedewerkerService(new UserService(userRepo));
	}

	private void setMedewerkerService(UserService medewerkerService)
	{
		this.userService = medewerkerService;
	}

	public Medewerker getM()
	{
		return m;
	}

	public void setM(Medewerker m)
	{
		// TODO
	}

	public UserDao getMedewerkerRepo()
	{
		return userRepo;
	}

	public void setUserRepo(UserDao userRepo)
	{
		this.userRepo = userRepo;
	}

	// return een Data Transfer Object van Medewerker naar de GUI
	public MedewerkerDTO aanmelden(String emailAdress, String password)
	{
		return userService.aanmelden(emailAdress, password);
	}

	public void maakMedewerker(String voornaam, String familienaam, String emailadres, String password, 
			String functie, int personeelsNr)
	{
		userService.maakMedewerker(voornaam, familienaam, emailadres, password, functie, personeelsNr);

	}

	public void close()
	{
		GenericDaoJpa.closePersistency();
	}

}
