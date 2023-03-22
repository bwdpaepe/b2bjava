package testen;

import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import domein.Bedrijf;
import domein.BesteldProduct;
import domein.Bestelling;
import domein.Doos;
import domein.Medewerker;
import domein.Product;
import domein.Transportdienst;

public class BesteldProductTest {
	
	private static final Product PRODUCT = new Product("Testproduct", 15.0, new Bedrijf("Testklant","teststraat", "15","2221", "testgemeente", "testland", "32135135", "teststring"));
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
	private static BesteldProduct bp;
	
	@BeforeAll
	static void beforeAll() {
		b = new Bestelling(ORDERID, DATUMGEPLAATST, LEVERANCIER, KLANT, TRANSPORTDIENST, AANKOPER, LEVERADRES_STRAAT, LEVERADRES_NUMMER, LEVERADRES_POSTCODE, LEVERADRES_STAD, LEVERADRES_LAND, DOOS );
		bp = new BesteldProduct(PRODUCT, 5, b);
	}
	
	@Test
	void test() {
		Assertions.assertDoesNotThrow(() -> new BesteldProduct(PRODUCT, 5, b));
		
		Assertions.assertEquals(PRODUCT, bp.getProduct());
		Assertions.assertEquals(5, bp.getAantal());
		Assertions.assertEquals(b, bp.getBestelling());
		Assertions.assertEquals(PRODUCT.getNaam(), bp.getNaam());
		Assertions.assertEquals(PRODUCT.getEenheidsprijs(), bp.getEenheidsprijs());
	}
	
	@ParameterizedTest
	@ValueSource (ints = {-2, 0})
	void test_ongeldig_aantal(int value) {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new BesteldProduct(PRODUCT, value, b));
	}
}
