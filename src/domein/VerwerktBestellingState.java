package domein;

import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;

public class VerwerktBestellingState extends BestellingState {

	public VerwerktBestellingState(Bestelling bestelling) {
		super(bestelling);
	}

	@Override
	public void wijzigBestelling(Transportdienst transportdienst) {
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
		// }
		// ToDo: else validate trackAndTraceCode
		// {
		// throw error if not valid
		// }

		// WE MOETEN VOLGENS MIJ GEEN NIEUWE NOTIFICATIE MAKEN HIER, STAAT NERGENS IN DE
		// UC
		// WE KUNNEN WEL DE BESTAANDE NOTIFICATIE AANPASSEN DOOR DE FLAG OP FALSE TE
		// ZETTEN
		// ZODAT DE AANKOPER ZIET DAT ER IETS GEWIJZIGD IS, OP DEZE MANIER:

		bestelling.getNotificatie().setBekenen(false);

		// Notificatie notificatie = new Notificatie(new Date(), false,
		// bestelling.getAankoper(), bestelling);
		// bestelling.setNotificatie(notificatie);
	}

	@Override
	public void wijzigTrackAndTraceCode() {
		TrackTraceFormat ttf = bestelling.getTransportdienst().getTrackTraceFormat();
		String trackAndTraceCode = bestelling.getTrackAndTraceCode();
		String generatedString;
		String generatedCode;
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
	}

}
