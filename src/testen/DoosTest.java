package testen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import domein.Bedrijf;
import domein.Doos;
import domein.DoosType;

class DoosTest
{

	private static final String NAAM = "test";
	private static final Double GELDIG_GETAL = 1.0;
	private static final Boolean IS_ACTIEF = true;
	private static final String TYPE_STRING = "STANDAARD";
	private static final DoosType TYPE = DoosType.STANDAARD;
	private static final Bedrijf BEDRIJF = new Bedrijf();
	
	private int i = 0;
	
	private Doos doos;
	
	@BeforeEach
	void before() {
		doos = new Doos(NAAM, GELDIG_GETAL, GELDIG_GETAL, GELDIG_GETAL, TYPE_STRING, GELDIG_GETAL, BEDRIJF);
	}

	@Test
	void maakDoos_geldigeWaardes() {
		Assertions.assertDoesNotThrow(() -> new Doos(NAAM, GELDIG_GETAL, GELDIG_GETAL, GELDIG_GETAL, TYPE_STRING, GELDIG_GETAL, BEDRIJF));
		Assertions.assertEquals(NAAM, doos.getNaam());
		Assertions.assertEquals(GELDIG_GETAL, doos.getDimensie().getBreedte());
		Assertions.assertEquals(GELDIG_GETAL, doos.getDimensie().getLengte());
		Assertions.assertEquals(GELDIG_GETAL, doos.getDimensie().getHoogte());
		Assertions.assertEquals(GELDIG_GETAL, doos.getPrijs());
		Assertions.assertEquals(IS_ACTIEF, doos.isActief());
		Assertions.assertEquals(TYPE, doos.getDoosType());
	}
	
	

	@ParameterizedTest
	@ValueSource (doubles = {-2, -0.01, 0})
	void maakDoos_ongeldigeWaardes(double value) {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Doos(NAAM, value, GELDIG_GETAL, GELDIG_GETAL, TYPE_STRING, GELDIG_GETAL, BEDRIJF));
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Doos(NAAM, GELDIG_GETAL, value, GELDIG_GETAL, TYPE_STRING, GELDIG_GETAL, BEDRIJF));
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Doos(NAAM, GELDIG_GETAL, GELDIG_GETAL, value, TYPE_STRING, GELDIG_GETAL, BEDRIJF));
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Doos(NAAM, GELDIG_GETAL, GELDIG_GETAL, GELDIG_GETAL, TYPE_STRING, value, BEDRIJF));
	}
	
	@ParameterizedTest
	@NullAndEmptySource
	public void ongeldige_strings_throwsError(String value) {

		
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Doos(value, GELDIG_GETAL, GELDIG_GETAL, GELDIG_GETAL, TYPE_STRING, GELDIG_GETAL, BEDRIJF));
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Doos(NAAM, GELDIG_GETAL, GELDIG_GETAL, GELDIG_GETAL, value, GELDIG_GETAL, BEDRIJF));		
		
	}
	
	@Test
	void wijzigDoos_geldig() {
		doos.wijzigDoos("Test2", "Custom", 50f, false);
		
		Assertions.assertEquals("Test2", doos.getNaam());
		Assertions.assertEquals("CUSTOM", doos.getDoosType().toString());
		Assertions.assertEquals(50f, doos.getPrijs());
		Assertions.assertEquals(false, doos.isActief());
	}
	
	@ParameterizedTest
	@NullAndEmptySource
	void wijzigDoos_ongeldig(String value) {
		
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> doos.wijzigDoos(value, TYPE_STRING, GELDIG_GETAL, IS_ACTIEF));
		Assertions.assertThrows(IllegalArgumentException.class, () -> doos.wijzigDoos(NAAM, value, GELDIG_GETAL , IS_ACTIEF));
		
	}
	
	@ParameterizedTest
	@ValueSource (doubles = {-2, -0.01, 0})
	public void wijzigDoos_ongeldig2(double value) {
		Assertions.assertThrows(IllegalArgumentException.class, () -> doos.wijzigDoos(NAAM, TYPE_STRING, value, IS_ACTIEF));
		
		
	}
	

	
	
	
}
