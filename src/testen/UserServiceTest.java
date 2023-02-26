package testen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import domein.Medewerker;
import domein.User;
import repository.MedewerkerDTO;
import repository.UserDTO;
import repository.UserDao;
import service.UserService;

@ExtendWith(MockitoExtension.class)
class UserServiceTest
{

	@Mock
	private UserDao userRepoMock;

	@InjectMocks
	private UserService userService;

	private static final String VOORNAAM = "John";
	private static final String FAMILIENAAM = "Doe";
	private static final String EMAILADRES = "test@test.com";
	private static final String WACHTWOORD = "DitIsEenGeldigWW!!!";
	private static final String ADRES = "TestStraat 1, 1234 Demo";
	private static final String TELEFOONNUMMER = "+1123456";
	private static final String FUNCTIE = "Admin";
	private static final String FUNCTIE_NIEUW = "Magazijnier";
	private static final int PERSONEELNR = 12345;

	@Test
	void aanmelden()
	{
		User user = new Medewerker("John", "Doe", EMAILADRES, WACHTWOORD, "adres", "0123456789", 1, "admin");

		Mockito.when(userRepoMock.getMedewerkerByEmailAdress(EMAILADRES)).thenReturn(user);

		UserDTO userDTO = userService.aanmelden(EMAILADRES, WACHTWOORD);

		Assertions.assertEquals(user.getVoornaam(), userDTO.getVoornaam());
		Assertions.assertEquals(user.getFamilienaam(), userDTO.getFamilienaam());
		Assertions.assertEquals(user.getEmail(), userDTO.getEmail());
		Assertions.assertEquals(user.getAdres(), userDTO.getAdres());
		Assertions.assertEquals(user.getTelefoonnummer(), userDTO.getTelefoonnummer());
		if (user instanceof Medewerker)
		{
			Assertions.assertEquals(((Medewerker) user).getPersoneelsNr(), ((MedewerkerDTO) userDTO).getPersoneelsNr());
			Assertions.assertEquals(((Medewerker) user).getFunctie(), ((MedewerkerDTO) userDTO).getFunctie());
		}

		Mockito.verify(userRepoMock).getMedewerkerByEmailAdress(EMAILADRES);
	}

	// TODO nog uitzoeken hoe connectie met databank kan voorkomen worden door UserDaoJpa.startTransaction()
//	@Test
//	public void testMaakMedewerker()
//	{
//
//		Medewerker mw = new Medewerker(VOORNAAM, FAMILIENAAM, EMAILADRES, WACHTWOORD, ADRES, TELEFOONNUMMER,
//				PERSONEELNR, FUNCTIE);
//
//		userService.maakMedewerker(VOORNAAM, FAMILIENAAM, EMAILADRES, WACHTWOORD, ADRES, TELEFOONNUMMER, FUNCTIE,
//				PERSONEELNR);
//
//		Mockito.verify(userRepoMock, Mockito.times(1)).insert(mw);
//	}

	// TODO nog uitzoeken hoe connectie met databank kan voorkomen worden door UserDaoJpa.startTransaction()
//	@Test
//	void updateMedewerkerTest()
//	{
//		Medewerker mw = new Medewerker("John", "Doe", EMAILADRES, WACHTWOORD, "adres", "0123456789", 1, "admin");
//		Mockito.when(userRepoMock.getMedewerkerByEmailAdress(EMAILADRES)).thenReturn(mw);
//		
//		userService.updateMedewerker(EMAILADRES, FUNCTIE_NIEUW);
//		
//		Assertions.assertEquals(FUNCTIE_NIEUW.toLowerCase(), mw.getFunctie().toLowerCase());
//		
//		Mockito.verify(userRepoMock).update(mw);
//
//	}
}
