package testen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import domein.Bedrijf;
import domein.Transportdienst;

class TransportdienstTest {

	private Transportdienst td;
	private static final String NAAM = "Dienst1";
	private static final Bedrijf BEDRIJF = new Bedrijf();

	@BeforeEach
	void before() {
		td = new Transportdienst(NAAM, BEDRIJF);
	}

	@Test
	void maakTransportdienst_geldigeWaardes() {
		Assertions.assertEquals(NAAM, td.getNaam());
		Assertions.assertEquals(true, td.isActief());
	}

	/* DEZE FOUT KOMT VIA DE DATABANK WANT UNIQUE CONSTRAINT, NIET TE TESTEN IN DEZE KLASSE ZELF
	 * @Test void maakTransportdienstMetBestaandeNaam() {
	 * Assertions.assertThrows(IllegalArgumentException.class, () -> new
	 * Transportdienst(NAAM)); }
	 */

	/*
	 * @Test void updateStatus_transportdienst() { //setActief nu nog protected dus
	 * test kan momenteel niet runnen td.setActief(false);
	 * Assertions.assertEquals(false, td.isActief()); }
	 */

	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings = { "", " ", "\n" })
	void ongeldigeTransportdienstNaam_throwError(String transportdienstNaam) {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Transportdienst(transportdienstNaam, BEDRIJF));
	}

}
