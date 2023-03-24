package service;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import domein.Bedrijf;
import domein.Medewerker;
import domein.User;
import repository.MedewerkerDTO;
import repository.MedewerkerListEntryDTO;
import repository.UserDTO;
import repository.UserDao;
import repository.UserDaoJpa;

public class UserService
{
	private UserDao userRepo;
	
	public UserService() {
		this.userRepo = new UserDaoJpa();
	}

	//voor mockito
	public UserService(UserDao userRepo)
	{
		this.userRepo = userRepo;
	}

	public UserDTO aanmelden(String emailAdress, String password)
	{
		
		try {
		User user = userRepo.getMedewerkerByEmailAdress(emailAdress);

		if (!BCrypt.checkpw(password, user.getHashedPW()))
		{ // mw == null is niet nodig, geeft al EntityNotFoundException in DAO klasse
			throw new IllegalArgumentException("Ongeldige inloggegevens");
		}

		if (user instanceof Medewerker && ((Medewerker) user).getFunctie() != "Aankoper")
		{
			return new MedewerkerDTO((Medewerker)user);
		} else
		{
			throw new IllegalArgumentException("Ongeldig usertype");
		}
		} catch (Exception e) {
			throw new IllegalArgumentException("Ongeldige inloggegevens");
		}
	}

	public void maakMedewerker(String voornaam, String familienaam, String emailadres, String password, String adres,
			String telefoonnummer, String functie, Bedrijf bedrijf)
	{
		UserDaoJpa.startTransaction();
		
		int personeelsNr = userRepo.findMaxPersoneelNrFromBedrijf(bedrijf.getID()) + 1;
		userRepo.insert(new Medewerker(voornaam, familienaam, emailadres, password, adres, telefoonnummer, personeelsNr,
				functie, bedrijf));

		UserDaoJpa.commitTransaction();
	}

	public void updateMedewerker(int id, String nieuweRol)
	{
		User user = userRepo.get(Long.valueOf(id));

		if (user instanceof Medewerker)
		{
			((Medewerker) user).setFunctie(nieuweRol);
			UserDaoJpa.startTransaction();
			userRepo.update(user);

			UserDaoJpa.commitTransaction();
		} else
		{
			throw new IllegalArgumentException("Ongeldig usertype");
		}

	}
	
	public Medewerker getMedewerkerById(long id) {
		User user =  userRepo.get(id);
		
		if(user instanceof Medewerker) {
			return (Medewerker) user;
		}
		throw new IllegalArgumentException("Ongeldige id of usertype");
	}
	
	public List<MedewerkerListEntryDTO> findAllMedewerkersByBedrijfId(long bedrijfId) {
		try {
			return userRepo.findAllMedewerkersByBedrijfId(bedrijfId);
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}
}
