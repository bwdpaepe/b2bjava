package testen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domein.Bedrijf;
import domein.Medewerker;
import repository.MedewerkerListEntryDTO;

class MedewerkerListEntryDTOTest
{

	
	private static final String VOORNAAM = "John";
	private static final String ACHTERNAAM = "Doe";
	private static final String EMAIL = "mw1test@test.co";
	private static final String WACHTWOORD = "DitIsEenGeldigWachtwoord!!!";
	private static final String TELEFOONNUMMER = "0477658975";
	private static final String ADRES = "testDorp 9875 testStraat 55";
	private static final int PERSONEELSNR = 1;
	private static final String FUNCTIE = "aDmin";
	private static final Bedrijf BEDRIJF = new Bedrijf("Bedrijf A", "Straat A", "A1", "1234A", "stad A", "land A", "0123456789", "logo_bedrijf_A");
	
	private Medewerker mw = new Medewerker(VOORNAAM, ACHTERNAAM, EMAIL, WACHTWOORD, ADRES, TELEFOONNUMMER, PERSONEELSNR, FUNCTIE, BEDRIJF);
	
	private MedewerkerListEntryDTO mwDto;
	
	@BeforeEach
	void before()
	{
		mwDto = new MedewerkerListEntryDTO(mw);
	}


	@Test
	void maakMedewerkerListEntryDTO()
	{
		Assertions.assertDoesNotThrow(() -> new MedewerkerListEntryDTO(mw));
		
		Assertions.assertEquals(mwDto.getId(), mw.getId());
		Assertions.assertEquals(mwDto.getVoornaam(), mw.getVoornaam());
		Assertions.assertEquals(mwDto.getFamilienaam(), mw.getFamilienaam());
		Assertions.assertEquals(mwDto.getFunctie(), mw.getFunctie());
		Assertions.assertEquals(mwDto.isActief(), mw.getIsActief());
	}
	
	// GEEN LOGICA IN EEN DTO, dus geen verdere testen
	
}
