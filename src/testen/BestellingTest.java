package testen;

import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import domein.Bedrijf;
import domein.Bestelling;
import domein.Contactpersoon;
import domein.Doos;
import domein.Medewerker;
import domein.TrackTraceFormat;
import domein.Transportdienst;

class BestellingTest {

	private final static String NAAMTRANSPORTDIENT = "Transportdienst_A";
	private final static String NAAMTRANSPORTDIENT2 = "Transportdienst_B";
	private final static int BARCODELENGTE = 10;
	private final static boolean ISBARCODEENKELCIJFERS = true;
	private final static String BARCODEPREFIX = "123";
	private final static String VERIFICATIECODE = "POSTCODE";
	private final static TrackTraceFormat TTF = new TrackTraceFormat(BARCODELENGTE, ISBARCODEENKELCIJFERS,
			BARCODEPREFIX, VERIFICATIECODE);

	private final static String CONTACTVOORNAAM = "TRANS";
	private final static String CONTACTFAMILIENAAM = "PORT";
	private final static String CONTACTTELEFOON = "1234567890";
	private final static String CONTACTEMAILADRES = "trans.port@example.com";
	private final static Contactpersoon CONTACTPERSOON = new Contactpersoon(CONTACTVOORNAAM, CONTACTFAMILIENAAM,
			CONTACTEMAILADRES, CONTACTTELEFOON);

	private static final String ORDERID = "ORDER_111";
	private static final Date DATUMGEPLAATST = new Date(2022, 10, 10);
	private static final String STATUS_GEPLAATST = "geplaatst";
	private static final String STATUS_VERWERKT = "verwerkt";
	private static final Bedrijf LEVERANCIER = new Bedrijf("Testleverancier", "teststraat", "15", "2221",
			"testgemeente", "testland", "32135135", "teststring");
	private static final Bedrijf KLANT = new Bedrijf("Testklant", "teststraat", "15", "2221", "testgemeente",
			"testland", "32135135", "teststring");
	private static final Transportdienst TRANSPORTDIENST = new Transportdienst(NAAMTRANSPORTDIENT, LEVERANCIER,
			CONTACTPERSOON, TTF);
	private static final Transportdienst TRANSPORTDIENST2 = new Transportdienst(NAAMTRANSPORTDIENT2, LEVERANCIER,
			CONTACTPERSOON, TTF);

	private static final Medewerker AANKOPER = new Medewerker("voornaam", "familienaam", "emailadres@test.cm",
			"password", "adres", "012345678", 1, "aankoper", KLANT);

	private static final String LEVERADRES_STRAAT = "leveradresStraat";
	private static final String LEVERADRES_NUMMER = "leveradresNummer";
	private static final String LEVERADRES_POSTCODE = "leveradresPostcode";
	private static final String LEVERADRES_STAD = "leveradresStad";
	private static final String LEVERADRES_LAND = "leveradresLand";

	private static final Doos DOOS = new Doos();

	private static Bestelling bGeplaatst;
	private static Bestelling bVerwerkt;

	@BeforeAll
	static void beforeAll() {
		bGeplaatst = new Bestelling(ORDERID, DATUMGEPLAATST, STATUS_GEPLAATST, LEVERANCIER, KLANT, TRANSPORTDIENST,
				AANKOPER, LEVERADRES_STRAAT, LEVERADRES_NUMMER, LEVERADRES_POSTCODE, LEVERADRES_STAD, LEVERADRES_LAND,
				DOOS);
		bVerwerkt = new Bestelling(ORDERID, DATUMGEPLAATST, STATUS_GEPLAATST, LEVERANCIER, KLANT, TRANSPORTDIENST,
				AANKOPER, LEVERADRES_STRAAT, LEVERADRES_NUMMER, LEVERADRES_POSTCODE, LEVERADRES_STAD, LEVERADRES_LAND,
				DOOS);
		bVerwerkt.verwerkBestelling(TRANSPORTDIENST);
	}

	@Test
	public void test() {
		Assertions.assertDoesNotThrow(() -> new Bestelling(ORDERID, DATUMGEPLAATST, STATUS_GEPLAATST, LEVERANCIER,
				KLANT, TRANSPORTDIENST, AANKOPER, LEVERADRES_STRAAT, LEVERADRES_NUMMER, LEVERADRES_POSTCODE,
				LEVERADRES_STAD, LEVERADRES_LAND, DOOS));

		Assertions.assertEquals(ORDERID, bGeplaatst.getOrderID());
		Assertions.assertEquals(new Date(2022, 10, 10), bGeplaatst.getDatumGeplaatst());
		Assertions.assertEquals(STATUS_GEPLAATST, bGeplaatst.getStatus());
		Assertions.assertEquals(LEVERANCIER, bGeplaatst.getLeverancier());
		Assertions.assertEquals(KLANT, bGeplaatst.getKlant());
		Assertions.assertEquals(TRANSPORTDIENST, bGeplaatst.getTransportdienst());
		Assertions.assertEquals(AANKOPER, bGeplaatst.getAankoper());
		Assertions.assertEquals(LEVERADRES_STRAAT, bGeplaatst.getLeveradresStraat());
		Assertions.assertEquals(LEVERADRES_NUMMER, bGeplaatst.getLeveradresNummer());
		Assertions.assertEquals(LEVERADRES_POSTCODE, bGeplaatst.getLeveradresPostcode());
		Assertions.assertEquals(LEVERADRES_STAD, bGeplaatst.getLeveradresStad());
		Assertions.assertEquals(LEVERADRES_LAND, bGeplaatst.getLeveradresLand());
		Assertions.assertEquals(bGeplaatst.getTrackAndTraceCode(), null);

	}

	@ParameterizedTest
	@NullAndEmptySource
	public void ongeldige_strings_throwsError(String value) {
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> new Bestelling(value, DATUMGEPLAATST, STATUS_GEPLAATST, LEVERANCIER, KLANT, TRANSPORTDIENST,
						AANKOPER, LEVERADRES_STRAAT, LEVERADRES_NUMMER, LEVERADRES_POSTCODE, LEVERADRES_STAD,
						LEVERADRES_LAND, DOOS));
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> new Bestelling(ORDERID, DATUMGEPLAATST, value, LEVERANCIER, KLANT, TRANSPORTDIENST, AANKOPER,
						LEVERADRES_STRAAT, LEVERADRES_NUMMER, LEVERADRES_POSTCODE, LEVERADRES_STAD, LEVERADRES_LAND,
						DOOS));
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> new Bestelling(ORDERID, DATUMGEPLAATST, STATUS_GEPLAATST, LEVERANCIER, KLANT, TRANSPORTDIENST,
						AANKOPER, value, LEVERADRES_NUMMER, LEVERADRES_POSTCODE, LEVERADRES_STAD, LEVERADRES_LAND,
						DOOS));
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> new Bestelling(ORDERID, DATUMGEPLAATST, STATUS_GEPLAATST, LEVERANCIER, KLANT, TRANSPORTDIENST,
						AANKOPER, LEVERADRES_STRAAT, value, LEVERADRES_POSTCODE, LEVERADRES_STAD, LEVERADRES_LAND,
						DOOS));
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> new Bestelling(ORDERID, DATUMGEPLAATST, STATUS_GEPLAATST, LEVERANCIER, KLANT, TRANSPORTDIENST,
						AANKOPER, LEVERADRES_STRAAT, LEVERADRES_NUMMER, value, LEVERADRES_STAD, LEVERADRES_LAND, DOOS));
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> new Bestelling(ORDERID, DATUMGEPLAATST, STATUS_GEPLAATST, LEVERANCIER, KLANT, TRANSPORTDIENST,
						AANKOPER, LEVERADRES_STRAAT, LEVERADRES_NUMMER, LEVERADRES_POSTCODE, value, LEVERADRES_LAND,
						DOOS));
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> new Bestelling(ORDERID, DATUMGEPLAATST, STATUS_GEPLAATST, LEVERANCIER, KLANT, TRANSPORTDIENST,
						AANKOPER, LEVERADRES_STRAAT, LEVERADRES_NUMMER, LEVERADRES_POSTCODE, LEVERADRES_STAD, value,
						DOOS));
	}

	@Test
	public void ongeldig_bestelling_throwsError() {
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> new Bestelling(ORDERID, DATUMGEPLAATST, STATUS_GEPLAATST, LEVERANCIER, null, TRANSPORTDIENST,
						AANKOPER, LEVERADRES_STRAAT, LEVERADRES_NUMMER, LEVERADRES_POSTCODE, LEVERADRES_STAD,
						LEVERADRES_LAND, DOOS));
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> new Bestelling(ORDERID, DATUMGEPLAATST, STATUS_GEPLAATST, null, KLANT, TRANSPORTDIENST, AANKOPER,
						LEVERADRES_STRAAT, LEVERADRES_NUMMER, LEVERADRES_POSTCODE, LEVERADRES_STAD, LEVERADRES_LAND,
						DOOS));
	}

	@Test
	void geldige_acties_bestelling() {
		Assertions.assertDoesNotThrow(()-> bGeplaatst.verwerkBestelling(TRANSPORTDIENST));
		Assertions.assertDoesNotThrow(()-> bVerwerkt.wijzigBestelling(TRANSPORTDIENST));
		Assertions.assertDoesNotThrow(()-> bVerwerkt.wijzigTrackAndTraceCode());
		
	}
	
	@Test
	void ongeldige_acties_bestelling() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> bGeplaatst.wijzigBestelling(TRANSPORTDIENST));
		Assertions.assertThrows(IllegalArgumentException.class, () -> bGeplaatst.wijzigTrackAndTraceCode());
		Assertions.assertThrows(IllegalArgumentException.class, () -> bVerwerkt.verwerkBestelling(TRANSPORTDIENST));
	}
	
	@Test
	void wijzig_track_and_trace_code_correct() {
		String code1 = bVerwerkt.getTrackAndTraceCode();
		bVerwerkt.wijzigTrackAndTraceCode();
		String code2 = bVerwerkt.getTrackAndTraceCode();
		
		Assertions.assertNotEquals(code1, code2);
	}
	
	@Test
	void wijzig_bestelling_correct() {
		String code1 = bVerwerkt.getTrackAndTraceCode();
		Transportdienst TRANSPORTDIENST1 = bVerwerkt.getTransportdienst();
		bVerwerkt.wijzigBestelling(TRANSPORTDIENST2);
		String code2 = bVerwerkt.getTrackAndTraceCode();
		
		Assertions.assertNotEquals(code1, code2);
		Assertions.assertEquals(TRANSPORTDIENST2, bVerwerkt.getTransportdienst());
		Assertions.assertNotEquals(TRANSPORTDIENST1, TRANSPORTDIENST2);
	}
	
	@Test
	void verwerk_bestelling_correct() {
		Bestelling bestelling = new Bestelling(ORDERID, DATUMGEPLAATST, STATUS_GEPLAATST, LEVERANCIER, KLANT, TRANSPORTDIENST,
				AANKOPER, LEVERADRES_STRAAT, LEVERADRES_NUMMER, LEVERADRES_POSTCODE, LEVERADRES_STAD, LEVERADRES_LAND,
				DOOS);;
		
		bestelling.verwerkBestelling(TRANSPORTDIENST);
		
		Assertions.assertEquals(TRANSPORTDIENST, bestelling.getTransportdienst());
		Assertions.assertEquals("verwerkt", bestelling.getStatus());
		Assertions.assertNotNull(bestelling.getTrackAndTraceCode());
		Assertions.assertNotNull(bestelling.getNotificatie());
		
	}

}
