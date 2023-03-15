package testen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domein.Bedrijf;

class BedrijfTest
{
	
	private static final String NAAM = "Bedrijf A";
	private static final String STRAAT = "Straat A";
	private static final String NUMMER = "A1";
	private static final String POSTCODE = "1234A";
	private static final String STAD = "stad A";
	private static final String LAND = "land A";
	private static final String TELEFOONNUMMER = "0477658975";
	private static final String LOGO_FILENAAM = "logo_bedrijf_A";

	
	private Bedrijf b;
	
	@BeforeEach
	void before( ) {
		b = new Bedrijf(NAAM, STRAAT, NUMMER, POSTCODE, STAD, LAND, TELEFOONNUMMER, LOGO_FILENAAM);
	}
	
	@Test
	void maakMedewerker_geldigeWaardes()
	{
		Assertions.assertDoesNotThrow(() -> new Bedrijf(NAAM, STRAAT, NUMMER, POSTCODE, STAD, LAND, TELEFOONNUMMER, LOGO_FILENAAM));
		
		Assertions.assertEquals(NAAM, b.getNaam());
		Assertions.assertEquals(STRAAT, b.getStraat());
		Assertions.assertEquals(NUMMER, b.getHuisnummer());
		Assertions.assertEquals(POSTCODE, b.getPostcode());
		Assertions.assertEquals(STAD, b.getStad());
		Assertions.assertEquals(LAND, b.getLand());
		Assertions.assertEquals(TELEFOONNUMMER, b.getTelefoonnummer());
		Assertions.assertEquals(LOGO_FILENAAM, b.getLogo_filename());
		
	}

	// TODO
	// nog bijkomende testen voor ongeldige waardes
	
	
}
