package domein;

import repository.GenericDaoJpa;
import repository.UserDTO;
import repository.UserDao;
import repository.UserDaoJpa;
import service.UserService;

public class DomeinController
{
	private UserDTO userDTO;
	private UserDao userRepo;
	private UserService userService; // service klasse om o.a aanmelden uit te werken

	public DomeinController()
	{
		setUserRepo(new UserDaoJpa());
		setUserService(new UserService(userRepo));
	}

	private final void setUserService(UserService userService)
	{
		this.userService = userService;
	}

	public UserDTO getUser()
	{
		return userDTO;
	}

	private void setUser(UserDTO userDTO)
	{
		this.userDTO = userDTO;
	}

	public UserDao getUserRepo()
	{
		return userRepo;
	}

	public final void setUserRepo(UserDao userRepo)
	{
		this.userRepo = userRepo;
	}

	// return een Data Transfer Object van User naar de GUI
	public UserDTO aanmelden(String emailAdress, String password)
	{
		return userService.aanmelden(emailAdress, password);
	}

	public void maakMedewerker(String voornaam, String familienaam, String emailadres, String password, String adres, String telefoonnumer, 
			String functie, int personeelsNr)
	{
		userService.maakMedewerker(voornaam, familienaam, emailadres, password, adres, telefoonnumer, functie, personeelsNr);

	}
	
	public void updateMedewerker(String e, String r) {
		userService.updateMedewerker(e, r);
	}

	public void close()
	{
		GenericDaoJpa.closePersistency();
	}

}
