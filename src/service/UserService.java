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
	private BedrijfService bedrijfService;

	public UserService()
	{
		this.userRepo = new UserDaoJpa();
		this.bedrijfService = new BedrijfService();
	}

	// voor mockito
	public UserService(UserDao userRepo, BedrijfService bedrijfService)
	{
		this.userRepo = userRepo;
		this.bedrijfService = bedrijfService;
	}

	public UserDTO aanmelden(String emailAdress, String password)
	{
		try
		{
			User user = userRepo.getMedewerkerByEmailAdress(emailAdress);

			if (!BCrypt.checkpw(password, user.getHashedPW()))
			{ // mw == null is niet nodig, geeft al EntityNotFoundException in DAO klasse
				throw new IllegalArgumentException("Ongeldige inloggegevens");
			}

			if (!user.getIsActief()) throw new IllegalArgumentException("De gebruiker is niet actief");
			
			if (user instanceof Medewerker && ((Medewerker) user).getFunctie() != "Aankoper")
			{
				return new MedewerkerDTO((Medewerker) user);
			} else
			{
				throw new IllegalArgumentException("Ongeldig usertype");
			}
		} catch (Exception e)
		{
			throw new IllegalArgumentException("Ongeldige inloggegevens");
		}
	}

	public void maakMedewerker(UserDTO ingelogdeUser, String voornaam, String familienaam, String emailadres,
			String password, String adres, String telefoonnummer, String functie)
	{
		try {
			// enkel admin mag medewerker aanmaken
			checkIfUserIsAdmin(ingelogdeUser);

			int personeelsNr = userRepo.findMaxPersoneelNrFromBedrijf(ingelogdeUser.getBedrijf().getId()) + 1;
			Bedrijf bedrijf = bedrijfService.getBedrijfById(ingelogdeUser.getBedrijf().getId());
			
			try {
				UserDaoJpa.startTransaction();
				userRepo.insert(new Medewerker(voornaam, familienaam, emailadres, password, adres, telefoonnummer,
						personeelsNr, functie, bedrijf));
				UserDaoJpa.commitTransaction();
			} catch (Exception e) {
				UserDaoJpa.rollbackTransaction();
				throw new IllegalArgumentException(e.getMessage());
			}
		} catch (Exception e) {
 			throw new IllegalArgumentException(e.getMessage());
		}
	}

	// TODO deze methode enkel nodig in database seeder, nadien verwijderen
	public void maakMedewerkerDatabaseSeeder(String voornaam, String familienaam, String emailadres, String password,
			String adres, String telefoonnummer, String functie, Long bedrijfId)
	{
		try
		{
			int personeelsNr = userRepo.findMaxPersoneelNrFromBedrijf(bedrijfId) + 1;
			Bedrijf bedrijf = bedrijfService.getBedrijfById(bedrijfId);

			try
			{
				UserDaoJpa.startTransaction();
				userRepo.insert(new Medewerker(voornaam, familienaam, emailadres, password, adres, telefoonnummer,
						personeelsNr, functie, bedrijf));
				UserDaoJpa.commitTransaction();
			} catch (Exception e)
			{
				UserDaoJpa.rollbackTransaction();
				throw new IllegalArgumentException(e.getMessage());
			}
		} catch (Exception e)
		{
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	public void updateMedewerker(UserDTO ingelogdeUser, long id, String voornaam, String familienaam, String emailadres,
			String adres, String telefoonnummer, String functie, Boolean isActief)
	{
		try
		{
			// enkel admin mag medewerker updaten
			checkIfUserIsAdmin(ingelogdeUser);
			
			User user = userRepo.get(id);

			if (!(user instanceof Medewerker))
				throw new IllegalArgumentException("User met id " + id + " is geen medewerker");

			Medewerker mw = ((Medewerker) user); // om gemakkelijker de setters te gebruiken in lijnen hieronder

			mw.setVoornaam(voornaam);
			mw.setFamilienaam(familienaam);
			mw.setEmail(emailadres);
			mw.setAdres(adres);
			mw.setTelefoonnummer(telefoonnummer);
			mw.setFunctie(functie);
			mw.setIsActief(isActief);
			try {
				UserDaoJpa.startTransaction();
				userRepo.update(user);
				UserDaoJpa.commitTransaction();
			} catch (Exception e) {
				UserDaoJpa.rollbackTransaction();
				throw new IllegalArgumentException("Fout bij updaten Medewerker met id " + id + ": " + e.getMessage());
			}
		} catch (Exception e) {
			//e.printStackTrace();
			throw new IllegalArgumentException(e.getMessage());
		}
	}

 
	public Medewerker getMedewerkerById(long id)
	{
		User user = userRepo.get(id);

		if (user instanceof Medewerker) {
			return (Medewerker) user;
		}
		throw new IllegalArgumentException("Ongeldige id of usertype");
	}

	public List<MedewerkerListEntryDTO> findAllMedewerkersByBedrijfId(UserDTO ingelogdeUser)
	{
		try {
			// enkel admin mag alle medewerkers opvragen
			checkIfUserIsAdmin(ingelogdeUser);

			return userRepo.findAllMedewerkersByBedrijfId(ingelogdeUser.getBedrijf().getId());

		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	public String getFunctionOfLoggedInUser(UserDTO ingelogdeUser)
	{
		if (ingelogdeUser instanceof MedewerkerDTO) {
			MedewerkerDTO medewerker = (MedewerkerDTO) ingelogdeUser;
			return medewerker.getFunctie();
		}
		return null;
	}
	
	private void checkIfUserIsAdmin(UserDTO ingelogdeUser)
	{
		if (!getFunctionOfLoggedInUser(ingelogdeUser).toLowerCase().equals("admin"))
			throw new IllegalArgumentException("Onvoldoende rechten voor deze bewerking");
	}
}
