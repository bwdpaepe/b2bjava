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
		String trackAndTraceCode = bestelling.getTrackAndTraceCode();

		// ToDo: if empty trackAndTraceCode
		// {
		// track & trace
		TrackTraceFormat ttf = transportdienst.getTrackTraceFormat();
		// gebruik het ID van de bestelling om de code uniek te maken
		// String id = String.valueOf(bestelling.getId());
		// https://mvnrepository.com/artifact/org.apache.commons/commons-lang3/3.12.0
		String generatedString;
		if (ttf.isBarcodeEnkelCijfers()) {
			// generatedString = RandomStringUtils.randomNumeric(ttf.getBarcodeLengte() -
			// id.length() - ttf.getBarcodePrefix().length());
			generatedString = RandomStringUtils.randomNumeric(ttf.getBarcodeLengte() - ttf.getBarcodePrefix().length());
		} else {
			// generatedString = RandomStringUtils.randomAlphanumeric(ttf.getBarcodeLengte()
			// - id.length() - ttf.getBarcodePrefix().length());
			generatedString = RandomStringUtils
					.randomAlphanumeric(ttf.getBarcodeLengte() - ttf.getBarcodePrefix().length());
		}

		// StringBuilder code = new
		// StringBuilder().append(ttf.getBarcodePrefix()).append(generatedString).append(id);
		StringBuilder code = new StringBuilder().append(ttf.getBarcodePrefix()).append(generatedString);
		bestelling.setTrackAndTraceCode(new String(code));
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

	}

}
