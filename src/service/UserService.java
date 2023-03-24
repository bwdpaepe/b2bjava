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

	public UserService()
	{
		this.userRepo = new UserDaoJpa();
	}

	// voor mockito
	public UserService(UserDao userRepo)
	{
		this.userRepo = userRepo;
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
			String password, String adres, String telefoonnummer, String functie, Bedrijf bedrijf)
	{
		try
		{
			// enkel admin mag medewerker aanmaken
			/* TODO NOG BEKIJKEN HOE SEEDING DAN KAN
			 * if (!getFunctionOfLoggedInUser(ingelogdeUser).toLowerCase().equals("admin"))
			 * throw new
			 * IllegalArgumentException("Onvoldoende rechten om medewerker aan te maken");
			 */

			UserDaoJpa.startTransaction();

			int personeelsNr = userRepo.findMaxPersoneelNrFromBedrijf(bedrijf.getID()) + 1;
			userRepo.insert(new Medewerker(voornaam, familienaam, emailadres, password, adres, telefoonnummer,
					personeelsNr, functie, bedrijf));

			UserDaoJpa.commitTransaction();

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
			if (!getFunctionOfLoggedInUser(ingelogdeUser).toLowerCase().equals("admin"))
				throw new IllegalArgumentException("Onvoldoende rechten om medewerker te updaten");

			User user = userRepo.get(id);

			if (user instanceof Medewerker)
			{
				Medewerker mw = ((Medewerker) user); // om gemakkelijker de setters te gebruiken in lijnen hieronder

				mw.setVoornaam(voornaam);
				mw.setFamilienaam(familienaam);
				mw.setEmail(emailadres);
				mw.setAdres(adres);
				mw.setTelefoonnummer(telefoonnummer);
				mw.setFunctie(functie);
				mw.setIsActief(isActief);

				UserDaoJpa.startTransaction();
				userRepo.update(user);

				UserDaoJpa.commitTransaction();
			} else
			{
				throw new IllegalArgumentException("Fout bij updaten Medewerker met id " + id);
			}
		} catch (Exception e)
		{
			throw new IllegalArgumentException(e.getMessage());
		}

	}

	public Medewerker getMedewerkerById(long id)
	{
		User user = userRepo.get(id);

		if (user instanceof Medewerker)
		{
			return (Medewerker) user;
		}
		throw new IllegalArgumentException("Ongeldige id of usertype");
	}

	public List<MedewerkerListEntryDTO> findAllMedewerkersByBedrijfId(UserDTO ingelogdeUser)
	{
		try
		{
			// enkel admin mag alle medewerkers opvragen
			if (!getFunctionOfLoggedInUser(ingelogdeUser).toLowerCase().equals("admin"))
				throw new IllegalArgumentException("Onvoldoende rechten om alle medewerkers op te vragen");
			return userRepo.findAllMedewerkersByBedrijfId(ingelogdeUser.getBedrijf().getId());
		} catch (Exception e)
		{
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	public String getFunctionOfLoggedInUser(UserDTO ingelogdeUser)
	{
		if (ingelogdeUser instanceof MedewerkerDTO)
		{
			MedewerkerDTO medewerker = (MedewerkerDTO) ingelogdeUser;
			return medewerker.getFunctie();
		}
		return null;
	}
}
