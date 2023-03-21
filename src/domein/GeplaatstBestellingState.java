package domein;

import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;

public class GeplaatstBestellingState extends BestellingState {

	public GeplaatstBestellingState(Bestelling bestelling) {
		super(bestelling);
	}

	@Override
	public void verwerkBestelling(Transportdienst transportdienst) {

		bestelling.setStatus("verwerkt");
		// transportdienst
		bestelling.setTransportdienst(transportdienst);
		TrackTraceFormat ttf = bestelling.getTransportdienst().getTrackTraceFormat();
		String trackAndTraceCode = bestelling.getTrackAndTraceCode();
		String generatedString;
		String generatedCode;
		// gebruik het ID van de bestelling om de code uniek te maken
		// String id = String.valueOf(bestelling.getId());
		// https://mvnrepository.com/artifact/org.apache.commons/commons-lang3/3.12.0
		do {
			if (ttf.isBarcodeEnkelCijfers()) {
				generatedString = RandomStringUtils
						.randomNumeric(ttf.getBarcodeLengte() - ttf.getBarcodePrefix().length());
			} else {
				generatedString = RandomStringUtils
						.randomAlphanumeric(ttf.getBarcodeLengte() - ttf.getBarcodePrefix().length());
			}
			StringBuilder code = new StringBuilder().append(ttf.getBarcodePrefix()).append(generatedString);
			generatedCode = new String(code);
		} while (generatedCode.equals(trackAndTraceCode));
		bestelling.setTrackAndTraceCode(generatedCode);

		Notificatie notificatie = new Notificatie(new Date(), false, bestelling.getAankoper(), bestelling);
		bestelling.setNotificatie(notificatie);

		bestelling.toState(new VerwerktBestellingState(bestelling));
	}

}
