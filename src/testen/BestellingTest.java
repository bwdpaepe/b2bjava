package testen;

import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import domein.Bedrijf;
import domein.Bestelling;
import domein.Doos;
import domein.Medewerker;
import domein.Transportdienst;

class BestellingTest {



	private static final String ORDERID = "ORDER_111";
	private static final Date DATUMGEPLAATST = new Date(2022,10,10);
	private static final String STATUS = "geplaatst";
	private static final Bedrijf LEVERANCIER = new Bedrijf("Testleverancier","teststraat", "15","2221", "testgemeente", "testland", "32135135", "teststring");
	private static final Bedrijf KLANT = new Bedrijf("Testklant","teststraat", "15","2221", "testgemeente", "testland", "32135135", "teststring");
	private static final Transportdienst TRANSPORTDIENST = new Transportdienst();
	
	private static final Medewerker AANKOPER = new Medewerker("voornaam", "familienaam", "emailadres@test.cm", "password", "adres", "012345678", 1,
			"aankoper", KLANT); 
	
	private static final String LEVERADRES_STRAAT = "leveradresStraat";
	private static final String LEVERADRES_NUMMER = "leveradresNummer";
	private static final String LEVERADRES_POSTCODE = "leveradresPostcode";
	private static final String LEVERADRES_STAD = "leveradresStad";
	private static final String LEVERADRES_LAND  = "leveradresLand";
	
	private static final Doos DOOS = new Doos();
	
	private static Bestelling b;
	
	@BeforeAll
	static void beforeAll() {
		b = new Bestelling(ORDERID, DATUMGEPLAATST, STATUS, LEVERANCIER, KLANT, TRANSPORTDIENST, AANKOPER, LEVERADRES_STRAAT, LEVERADRES_NUMMER, LEVERADRES_POSTCODE, LEVERADRES_STAD, LEVERADRES_LAND, DOOS );
	}
	@Test
	public void test() {
		Assertions.assertDoesNotThrow(() -> new Bestelling(ORDERID, DATUMGEPLAATST, STATUS, LEVERANCIER, KLANT, TRANSPORTDIENST, AANKOPER, LEVERADRES_STRAAT, LEVERADRES_NUMMER, LEVERADRES_POSTCODE, LEVERADRES_STAD, LEVERADRES_LAND, DOOS ));
		
		
		Assertions.assertEquals(ORDERID, b.getOrderID());
		Assertions.assertEquals(new Date(2022,10,10), b.getDatumGeplaatst());
		Assertions.assertEquals(STATUS, b.getStatus());
		Assertions.assertEquals(LEVERANCIER, b.getLeverancier());
		Assertions.assertEquals(KLANT, b.getKlant());
		Assertions.assertEquals(TRANSPORTDIENST, b.getTransportdienst());
		Assertions.assertEquals(AANKOPER, b.getAankoper());
		Assertions.assertEquals(LEVERADRES_STRAAT, b.getLeveradresStraat());
		Assertions.assertEquals(LEVERADRES_NUMMER, b.getLeveradresNummer());
		Assertions.assertEquals(LEVERADRES_POSTCODE, b.getLeveradresPostcode());
		Assertions.assertEquals(LEVERADRES_STAD, b.getLeveradresStad());
		Assertions.assertEquals(LEVERADRES_LAND, b.getLeveradresLand());
		
	}
	
	
	@ParameterizedTest
	@NullAndEmptySource
	public void ongeldige_strings_throwsError(String value) {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Bestelling(value, DATUMGEPLAATST, STATUS, LEVERANCIER, KLANT, TRANSPORTDIENST, AANKOPER, LEVERADRES_STRAAT, LEVERADRES_NUMMER, LEVERADRES_POSTCODE, LEVERADRES_STAD, LEVERADRES_LAND, DOOS));
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Bestelling(ORDERID, DATUMGEPLAATST, value, LEVERANCIER, KLANT, TRANSPORTDIENST, AANKOPER, LEVERADRES_STRAAT, LEVERADRES_NUMMER, LEVERADRES_POSTCODE, LEVERADRES_STAD, LEVERADRES_LAND, DOOS));
		Assertions.assertThrows(IllegalArgumentException.class, () ->  new Bestelling(ORDERID, DATUMGEPLAATST, STATUS, LEVERANCIER, KLANT, TRANSPORTDIENST, AANKOPER, value, LEVERADRES_NUMMER, LEVERADRES_POSTCODE, LEVERADRES_STAD, LEVERADRES_LAND, DOOS));
		Assertions.assertThrows(IllegalArgumentException.class, () ->  new Bestelling(ORDERID, DATUMGEPLAATST, STATUS, LEVERANCIER, KLANT, TRANSPORTDIENST, AANKOPER, LEVERADRES_STRAAT, value, LEVERADRES_POSTCODE, LEVERADRES_STAD, LEVERADRES_LAND, DOOS));
		Assertions.assertThrows(IllegalArgumentException.class, () ->  new Bestelling(ORDERID, DATUMGEPLAATST, STATUS, LEVERANCIER, KLANT, TRANSPORTDIENST, AANKOPER, LEVERADRES_STRAAT, LEVERADRES_NUMMER, value, LEVERADRES_STAD, LEVERADRES_LAND, DOOS));
		Assertions.assertThrows(IllegalArgumentException.class, () ->  new Bestelling(ORDERID, DATUMGEPLAATST, STATUS, LEVERANCIER, KLANT, TRANSPORTDIENST, AANKOPER, LEVERADRES_STRAAT, LEVERADRES_NUMMER, LEVERADRES_POSTCODE, value, LEVERADRES_LAND, DOOS));
		Assertions.assertThrows(IllegalArgumentException.class, () ->  new Bestelling(ORDERID, DATUMGEPLAATST, STATUS, LEVERANCIER, KLANT, TRANSPORTDIENST, AANKOPER, LEVERADRES_STRAAT, LEVERADRES_NUMMER, LEVERADRES_POSTCODE, LEVERADRES_STAD, value, DOOS));
	}
	
	@Test
	public void ongeldig_bedrijf_throwsError() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Bestelling(ORDERID, DATUMGEPLAATST, STATUS, LEVERANCIER, KLANT, null, AANKOPER, LEVERADRES_STRAAT, LEVERADRES_NUMMER, LEVERADRES_POSTCODE, LEVERADRES_STAD, LEVERADRES_LAND, DOOS));
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Bestelling(ORDERID, DATUMGEPLAATST, STATUS, LEVERANCIER, null, TRANSPORTDIENST, AANKOPER, LEVERADRES_STRAAT, LEVERADRES_NUMMER, LEVERADRES_POSTCODE, LEVERADRES_STAD, LEVERADRES_LAND, DOOS));
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Bestelling(ORDERID, DATUMGEPLAATST, STATUS, null, KLANT, TRANSPORTDIENST, AANKOPER, LEVERADRES_STRAAT, LEVERADRES_NUMMER, LEVERADRES_POSTCODE, LEVERADRES_STAD, LEVERADRES_LAND, DOOS));
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Bestelling(ORDERID, null, STATUS, LEVERANCIER, KLANT, TRANSPORTDIENST, AANKOPER, LEVERADRES_STRAAT, LEVERADRES_NUMMER, LEVERADRES_POSTCODE, LEVERADRES_STAD, LEVERADRES_LAND, DOOS));

	}
	
	
}
