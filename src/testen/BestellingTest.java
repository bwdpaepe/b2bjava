package testen;

import domein.Bedrijf;
import domein.Bestelling;
import domein.Transportdienst;
import java.util.Date;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class BestellingTest {



	private static final String ORDERID = "ORDER_111";
	private static final Date DATUMGEPLAATST = new Date(2022,10,10);
	private static final String STATUS = "OPEN";
	private static final Bedrijf LEVERANCIER = new Bedrijf();
	private static final Bedrijf KLANT = new Bedrijf();
	private static final Transportdienst TRANSPORTDIENST = new Transportdienst();
	
	private static Bestelling b;
	
	@BeforeAll
	static void beforeAll() {
		b = new Bestelling(ORDERID, DATUMGEPLAATST, STATUS, LEVERANCIER, KLANT, TRANSPORTDIENST);
	}
	@Test
	void test() {
		Assertions.assertDoesNotThrow(() -> new Bestelling(ORDERID, DATUMGEPLAATST, STATUS, LEVERANCIER, KLANT, TRANSPORTDIENST));
		
		
		Assertions.assertEquals(ORDERID, b.getOrderID());
		Assertions.assertEquals(new Date(2022,10,10), b.getDatumGeplaatst());
		Assertions.assertEquals(STATUS, b.getStatus());
		Assertions.assertEquals(LEVERANCIER, b.getLeverancier());
		Assertions.assertEquals(KLANT, b.getKlant());
		Assertions.assertEquals(TRANSPORTDIENST, b.getTransportdienst());
	}
	
	
	@ParameterizedTest
	@NullAndEmptySource
	void ongeldige_strings_throwsError(String value) {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Bestelling(value, DATUMGEPLAATST, STATUS, LEVERANCIER, KLANT, TRANSPORTDIENST));
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Bestelling(ORDERID, DATUMGEPLAATST, value, LEVERANCIER, KLANT, TRANSPORTDIENST));
	}
	
	@Test
	void ongeldig_bedrijf_throwsError() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Bestelling(ORDERID, DATUMGEPLAATST, STATUS, LEVERANCIER, KLANT, null));
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Bestelling(ORDERID, DATUMGEPLAATST, STATUS, LEVERANCIER, null, TRANSPORTDIENST));
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Bestelling(ORDERID, DATUMGEPLAATST, STATUS, null, KLANT, TRANSPORTDIENST));
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Bestelling(ORDERID, null, STATUS, LEVERANCIER, KLANT, TRANSPORTDIENST));

	}
	
	
	

}
