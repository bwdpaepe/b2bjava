package testen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domein.Bedrijf;
import repository.KlantLijstEntryDTO;

class KlantLijstEntryDTOTest
{
	private static final String NAAM = "Bedrijf A";
	private static final String STRAAT = "Straat A";
	private static final String HUISNUMMER = "A1";
	private static final String POSTCODE = "1234A";
	private static final String STAD = " stad A";
	private static final String LAND = "land A";
	private static final String TELEFOONNUMMER = "0123456789";
	private static final String LOGO_FILENAME = "logog_bedrijf_A";
	
	private static final Bedrijf KLANT = new Bedrijf(NAAM, STRAAT, HUISNUMMER, POSTCODE, STAD, LAND, TELEFOONNUMMER, LOGO_FILENAME);
	private static final int AANTAL = 9;
	
	private static final Object[] OBJ = new Object[]{ KLANT, AANTAL };
	
	private KlantLijstEntryDTO dto;
	
	@BeforeEach
	void before()
	{
		dto = new KlantLijstEntryDTO(OBJ);
	}
	
	@Test
	void maakKlantLijstEntryDTO()
	{
		Assertions.assertDoesNotThrow(() -> new KlantLijstEntryDTO(OBJ));
		
		Assertions.assertEquals(KLANT.getID(), dto.getKlantId());
		Assertions.assertEquals(KLANT.getNaam(), dto.getKlantNaam());
		Assertions.assertEquals(OBJ[1], dto.getAantalOpenBestellingen());
		
		
	}
	
	
	// GEEN LOGICA IN EEN DTO, dus geen verdere testen

}
