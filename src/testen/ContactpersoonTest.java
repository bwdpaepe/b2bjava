package testen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domein.Contactpersoon;

class ContactpersoonTest {

	private Contactpersoon cp;
	private static final String VOORNAAM = "Jan";
	private static final String ACHTERNAAM = "Test";
	private static final String EMAIL = "mw1test@test.co";
	private static final String TELEFOONNUMMER = "0412358975"; 

	@BeforeEach
	void before() {
		cp = new Contactpersoon(VOORNAAM, ACHTERNAAM, EMAIL, TELEFOONNUMMER);

	}

	@Test
	void maakContacpersoon_geldigeWaarden() {
		Assertions.assertDoesNotThrow(() -> new Contactpersoon(VOORNAAM, ACHTERNAAM, EMAIL, TELEFOONNUMMER));
		Assertions.assertEquals(VOORNAAM, cp.getVoornaam());
		Assertions.assertEquals(ACHTERNAAM, cp.getFamilienaam());
		Assertions.assertEquals(EMAIL, cp.getEmailAdress());
		Assertions.assertEquals(TELEFOONNUMMER, cp.getTelefoonnummer());
		
	}
	
	//TODO verder testen schrijven
}
