package testen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import domein.TrackTraceFormat;

class TrackTraceFormatTest {

	private TrackTraceFormat ttf;
	private static final int BARCODE_LENGTE = 5;
	private static final boolean IS_BARCODE_ENKEL_CIJFERS = true;
	private static final String BARCODE_PREFIX = "TEST";
	private static final String VERIFICATIE_CODE = "postcode";

	@BeforeEach
	void before() {
		ttf = new TrackTraceFormat(BARCODE_LENGTE, IS_BARCODE_ENKEL_CIJFERS, BARCODE_PREFIX, VERIFICATIE_CODE);
	}

	@Test
	void maakTrackTraceFormat_geldigeWaarden() {
		Assertions.assertDoesNotThrow(() -> new TrackTraceFormat(BARCODE_LENGTE, IS_BARCODE_ENKEL_CIJFERS,
				BARCODE_PREFIX, VERIFICATIE_CODE));
		Assertions.assertEquals(BARCODE_LENGTE, ttf.getBarcodeLengte());
		Assertions.assertEquals(IS_BARCODE_ENKEL_CIJFERS, ttf.isBarcodeEnkelCijfers());
		Assertions.assertEquals(BARCODE_PREFIX, ttf.getBarcodePrefix());
		Assertions.assertEquals(VERIFICATIE_CODE, ttf.getVerificatieCode());
	}
	
	
	@ParameterizedTest
	@ValueSource(ints = { -1, 0})
	void ongeldigeBarcodeLengte_throwError(int barcodeLengte) {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new TrackTraceFormat(barcodeLengte, IS_BARCODE_ENKEL_CIJFERS, BARCODE_PREFIX, VERIFICATIE_CODE));
	}
	
	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings = { "", " ", "\n" })
	void ongeldigeBarcodePrefix_throwError(String barcodePrefix) {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new TrackTraceFormat(BARCODE_LENGTE, IS_BARCODE_ENKEL_CIJFERS, barcodePrefix, VERIFICATIE_CODE));
	}
	
	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings = { "", " ", "\n", "post code", "order id" })
	void ongeldigeVerificatieCodeString_throwError(String verificatieCode) {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new TrackTraceFormat(BARCODE_LENGTE, IS_BARCODE_ENKEL_CIJFERS, BARCODE_PREFIX, verificatieCode));
	}

}
