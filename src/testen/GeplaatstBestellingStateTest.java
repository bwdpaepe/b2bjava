package testen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import domein.Bedrijf;
import domein.Bestelling;
import domein.Contactpersoon;
import domein.GeplaatstBestellingState;
import domein.TrackTraceFormat;
import domein.Transportdienst;

@ExtendWith(MockitoExtension.class)
class GeplaatstBestellingStateTest {
	
	@Mock 
	private Bestelling bestellingMock;
	
	@InjectMocks
	private GeplaatstBestellingState geplaatstBestellingState;

	private final String NAAMTRANSPORTDIENT = "Transportdienst_A";
	
	private final int BARCODELENGTE = 10;
	private final boolean ISBARCODEENKELCIJFERS = true;
	private final String BARCODEPREFIX = "123";
	private final String VERIFICATIECODE = "POSTCODE";
	private final TrackTraceFormat TTF = new TrackTraceFormat(BARCODELENGTE, ISBARCODEENKELCIJFERS, BARCODEPREFIX, VERIFICATIECODE);
	
	private final String CONTACTVOORNAAM = "TRANS";
	private final String CONTACTFAMILIENAAM = "PORT";
	private final String CONTACTTELEFOON = "1234567890";
	private final String CONTACTEMAILADRES = "trans.port@example.com";
	private final Contactpersoon CONTACTPERSOON = new Contactpersoon(CONTACTVOORNAAM, CONTACTFAMILIENAAM, CONTACTEMAILADRES, CONTACTTELEFOON);
	
	private static final String NAAMBEDRIJF = "Bedrijf A";
	private static final String STRAAT = "Straat A";
	private static final String HUISNUMMER = "A1";
	private static final String POSTCODE = "1234A";
	private static final String STAD = " stad A";
	private static final String LAND = "land A";
	private static final String TELEFOONNUMMER = "0123456789";
	private static final String LOGO_FILENAME = "logog_bedrijf_A";
	private final Bedrijf BEDRIJF = new Bedrijf(NAAMBEDRIJF, STRAAT, HUISNUMMER,POSTCODE,STAD,LAND,TELEFOONNUMMER,LOGO_FILENAME);
	
	private Transportdienst td;

	@BeforeEach
	void maakTransportdienst() {
		td  = new Transportdienst(NAAMTRANSPORTDIENT, BEDRIJF, CONTACTPERSOON, TTF);
	}
	
	@Test
	void testVerwerkBestelling() {
		//ToDo implement test
		Assertions.fail("Not yet implemented");
	}

}
