package testen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import domein.Bedrijf;
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
	private static final Bedrijf BEDRIJF = new Bedrijf("Bedrijf A", "Straat A", "A1", "1234A", "stad A", "land A", "0123456789", "logo_bedrijf_A");

	private static final UserDTO adminUser = new MedewerkerDTO(new Medewerker(VOORNAAM, FAMILIENAAM, EMAILADRES, WACHTWOORD, ADRES, TELEFOONNUMMER, PERSONEELNR, "admin", BEDRIJF));
	
	@Test
	void aanmelden()
	{
		User user = new Medewerker(VOORNAAM, FAMILIENAAM, EMAILADRES, WACHTWOORD, ADRES, TELEFOONNUMMER, PERSONEELNR, FUNCTIE, BEDRIJF);

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

	
	@Test
	void testUpdateMedewerker() {
	    
	    long id = 1L;
	    String updatedVoornaam = "UpdatedFirstName";
	    String updatedFamilienaam = "UpdatedLastName";
	    String updatedEmailadres = "updated@test.com";
	    String updatedAdres = "Updated Street 1, 1234 Demo";
	    String updatedTelefoonnummer = "+1987654321";
	    String updatedFunctie = FUNCTIE_NIEUW;
	    Boolean updatedIsActief = false;

	    Medewerker mw = new Medewerker(VOORNAAM, FAMILIENAAM, EMAILADRES, WACHTWOORD, ADRES, TELEFOONNUMMER, PERSONEELNR, FUNCTIE, BEDRIJF);

	    Mockito.when(userRepoMock.get(Mockito.anyLong())).thenReturn(mw);

	    userService.updateMedewerker(adminUser, id, updatedVoornaam, updatedFamilienaam, updatedEmailadres,
	            updatedAdres, updatedTelefoonnummer, updatedFunctie, updatedIsActief);

	    Assertions.assertEquals(updatedVoornaam, mw.getVoornaam());
	    Assertions.assertEquals(updatedFamilienaam, mw.getFamilienaam());
	    Assertions.assertEquals(updatedEmailadres, mw.getEmail());
	    Assertions.assertEquals(updatedAdres, mw.getAdres());
	    Assertions.assertEquals(updatedTelefoonnummer, mw.getTelefoonnummer());
	    Assertions.assertEquals(updatedFunctie.toLowerCase(), mw.getFunctie().toLowerCase());
	    Assertions.assertEquals(updatedIsActief, mw.getIsActief());

	    Mockito.verify(userRepoMock).get(id);
	    Mockito.verify(userRepoMock).update(mw);
	}
	
	// TODO nog uitzoeken hoe connectie met databank kan voorkomen worden door UserDaoJpa.startTransaction()
	
	/*
	 * @Test public void testMaakMedewerker() {
	 * 
	 * Medewerker mw = new Medewerker(VOORNAAM, FAMILIENAAM, EMAILADRES, WACHTWOORD,
	 * ADRES, TELEFOONNUMMER,PERSONEELNR, FUNCTIE, BEDRIJF);
	 * 
	 * Mockito.doNothing().when(userRepoMock).insert(mw);
	 * 
	 * userService.maakMedewerker(adminUser, VOORNAAM, FAMILIENAAM, EMAILADRES,
	 * WACHTWOORD, ADRES, TELEFOONNUMMER, FUNCTIE);
	 * 
	 * Mockito.verify(userRepoMock, Mockito.times(1)).insert(mw); }
	 */
	 

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
