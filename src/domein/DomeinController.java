package domein;

import repository.GenericDaoJpa;
import repository.UserDTO;
import repository.UserDao;
import repository.UserDaoJpa;
import service.UserService;

public class DomeinController
{
	private UserDTO ingelogdeUser;
	private UserService userService; // service klasse om o.a aanmelden uit te werken

	public DomeinController()
	{

		setUserService(new UserService());
	}

	private final void setUserService(UserService userService)
	{
		this.userService = userService;
	}

	public UserDTO getIngelogdeUser()
	{
		return this.ingelogdeUser;
	}

	private void setIngelogdeUser(UserDTO userDTO)
	{
		this.ingelogdeUser = userDTO;
	}



	// return een Data Transfer Object van User naar de GUI
	public UserDTO aanmelden(String emailAdress, String password)
	{
		UserDTO user = userService.aanmelden(emailAdress, password);
		setIngelogdeUser(user);
		return user;
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
