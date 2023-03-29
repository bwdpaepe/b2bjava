package util;

import javax.naming.SizeLimitExceededException;

import org.apache.commons.lang3.RandomStringUtils;

import domein.TrackTraceFormat;

public class Tools {
	public static String generateTrackAndTraceCode(TrackTraceFormat ttf, String bestellingID) throws SizeLimitExceededException {
		String generatedString;
		String generatedCode;
		
		// gebruik het ID van de bestelling om de code uniek te maken
		// https://mvnrepository.com/artifact/org.apache.commons/commons-lang3/3.12.0
		
		if(ttf.getBarcodeLengte() - ttf.getBarcodePrefix().length() - bestellingID.length() < 0) {
			throw new SizeLimitExceededException("De lengte van het prefix + de lengte van het ID zijn groter dan de totale lengte van de code.");
		}
				
		if (ttf.isBarcodeEnkelCijfers()) {
			generatedString = RandomStringUtils
					.randomNumeric(ttf.getBarcodeLengte() - ttf.getBarcodePrefix().length() - bestellingID.length());
		} else {
			generatedString = RandomStringUtils
					.randomAlphanumeric(ttf.getBarcodeLengte() - ttf.getBarcodePrefix().length() - bestellingID.length());
		}
		StringBuilder code = new StringBuilder().append(ttf.getBarcodePrefix()).append(generatedString).append(bestellingID);
		generatedCode = new String(code);
		return generatedCode;
	}

}
