package testen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
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
	
	
	// TODO verdere testen uitwerken met ongeldige waardes
	@ParameterizedTest
	@ValueSource (doubles = {-2, -0.01, 0})
	void maakDoos_ongeldigeWaardes(double value) {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Doos(NAAM, value, GELDIG_GETAL, GELDIG_GETAL, TYPE_STRING, GELDIG_GETAL, BEDRIJF));
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Doos(NAAM, GELDIG_GETAL, value, GELDIG_GETAL, TYPE_STRING, GELDIG_GETAL, BEDRIJF));
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Doos(NAAM, GELDIG_GETAL, GELDIG_GETAL, value, TYPE_STRING, GELDIG_GETAL, BEDRIJF));
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Doos(NAAM, GELDIG_GETAL, GELDIG_GETAL, GELDIG_GETAL, TYPE_STRING, value, BEDRIJF));
	}
}
