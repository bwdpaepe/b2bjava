package testen;

import domein.Bedrijf;
import domein.Bestelling;
import domein.Transportdienst;
import java.util.Date;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class BestellingTest {



	private static final String ORDERID = "ORDER_111";
	private static final Date DATUMGEPLAATST = new Date();
	private static final String STATUS = "OPEN";
	private static final Bedrijf LEVERANCIER = new Bedrijf();
	private static final Bedrijf KLANT = new Bedrijf();
	private static final Transportdienst TRANSPORTDIENST = new Transportdienst();
	
	private Bestelling b;
	
	@BeforeAll
	void before() {
		b = new Bestelling(ORDERID, DATUMGEPLAATST, STATUS, LEVERANCIER, KLANT, TRANSPORTDIENST);
	}
	@Test
	void test() {
		Assertions.assertDoesNotThrow(() -> new Bestelling(ORDERID, DATUMGEPLAATST, STATUS, LEVERANCIER, KLANT, TRANSPORTDIENST));
	}

}
