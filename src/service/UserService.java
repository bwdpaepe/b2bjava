package service;

import org.mindrot.jbcrypt.BCrypt;

import domein.Bedrijf;
import domein.Medewerker;
import domein.User;
import repository.MedewerkerDTO;
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
		
		
		User user = userRepo.getMedewerkerByEmailAdress(emailAdress);

		if (!BCrypt.checkpw(password, user.getHashedPW()))
		{ // mw == null is niet nodig, geeft al EntityNotFoundException in DAO klasse
			throw new IllegalArgumentException("Ongeldige inloggegevens");
		}

		if (user instanceof Medewerker)
		{
			return new MedewerkerDTO(user.getVoornaam(), user.getFamilienaam(), user.getEmail(), user.getAdres(),
					user.getTelefoonnummer(), ((Medewerker) user).getPersoneelsNr(), ((Medewerker) user).getFunctie());
		} else
		{
			throw new IllegalArgumentException("Ongeldig usertype");
		}
	}

	public void maakMedewerker(String voornaam, String familienaam, String emailadres, String password, String adres,
			String telefoonnummer, int personeelsNr, String functie, Bedrijf bedrijf)
	{
		UserDaoJpa.startTransaction();

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
}
