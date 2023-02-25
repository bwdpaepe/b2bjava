package domein;

import org.mindrot.jbcrypt.BCrypt;


import repository.UserDao;
import repository.UserDaoJpa;
import repository.MedewerkerDTO;

public class UserService
{
	private UserDao userRepo;

	public UserService(UserDao userRepo)
	{
		this.userRepo = userRepo;
	}

	public MedewerkerDTO aanmelden(String emailAdress, String password)
	{
		Medewerker mw = (Medewerker) userRepo.getMedewerkerByEmailAdress(emailAdress);

		if (!BCrypt.checkpw(password, mw.getHashedPW()))
		{ // mw == null is niet nodig, geeft al EntityNotFoundException in DAO klasse
			throw new IllegalArgumentException("Ongeldige inloggegevens");
		}

		return new MedewerkerDTO(mw.getVoornaam(), mw.getFamilienaam(), mw.getEmail(), mw.getPersoneelsNr(),
				mw.getFunctie());

	}

	public void maakMedewerker(String voornaam, String familienaam, String emailadres, String password, String functie,
			int personeelsNr)
	{
		UserDaoJpa.startTransaction();

		userRepo.insert(new Medewerker(voornaam, familienaam, emailadres, password, personeelsNr, functie));

		UserDaoJpa.commitTransaction();
	}

}
