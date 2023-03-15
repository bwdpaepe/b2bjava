package testen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import domein.Bedrijf;
import domein.Product;

public class ProductTest {
	
	private static final Bedrijf LEVERANCIER = new Bedrijf("Testleverancier","teststraat", "15","2221", "testgemeente", "testland", "32135135", "teststring");
	private static final String NAAM = "Testproduct";
	private static final double EENHEIDSPRIJS = 5.0;
	private static Product p;
	
	@BeforeAll
	static void beforeAll() {
		p = new Product(NAAM, EENHEIDSPRIJS, LEVERANCIER);
	}
	
	@Test
	void test() {
		Assertions.assertDoesNotThrow(() -> new Product("testproduct", 12.5, LEVERANCIER));
		
		Assertions.assertEquals(NAAM, p.getNaam());
		Assertions.assertEquals(EENHEIDSPRIJS, p.getEenheidsprijs());
	}
	
	 @ParameterizedTest
	 @NullAndEmptySource
	 void test_ongeldige_naam(String naam) {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Product(naam, 5.0, LEVERANCIER));
	 }
	 
	 @ParameterizedTest
	 @ValueSource (doubles = {-20.0, -0.01, 0})
	 void test_ongeldige_eenheidsprijs(double value) {
		 Assertions.assertThrows(IllegalArgumentException.class,() -> new Product(NAAM, value, LEVERANCIER));
	 }
}
