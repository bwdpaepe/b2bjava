package domein;

import java.util.Date;

import javax.naming.SizeLimitExceededException;

import org.apache.commons.lang3.RandomStringUtils;

import util.Tools;

public class VerwerktBestellingState extends BestellingState {

	public VerwerktBestellingState(Bestelling bestelling) {
		super(bestelling);
	}

	@Override
	public void wijzigBestelling(Transportdienst transportdienst) throws SizeLimitExceededException {
		// transportdienst
		bestelling.setTransportdienst(transportdienst);
		TrackTraceFormat ttf = bestelling.getTransportdienst().getTrackTraceFormat();
		String trackAndTraceCode = bestelling.getTrackAndTraceCode();
		String generatedCode;
		// gebruik het ID van de bestelling om de code uniek te maken
		String bestellingID = String.valueOf(bestelling.getId());
		do {
			generatedCode = Tools.generateTrackAndTraceCode(ttf, bestellingID);	
				
		} while (generatedCode.equals(trackAndTraceCode));
		
		bestelling.setTrackAndTraceCode(generatedCode);
		// WE MOETEN VOLGENS MIJ GEEN NIEUWE NOTIFICATIE MAKEN HIER, STAAT NERGENS IN DE
		// UC
		// WE KUNNEN WEL DE BESTAANDE NOTIFICATIE AANPASSEN DOOR DE FLAG OP FALSE TE
		// ZETTEN
		// ZODAT DE AANKOPER ZIET DAT ER IETS GEWIJZIGD IS, OP DEZE MANIER:

		bestelling.getNotificatie().setBekenen(false);

		// bestelling.setNotificatie(notificatie);
	}

	@Override
	public void wijzigTrackAndTraceCode() throws SizeLimitExceededException {
		TrackTraceFormat ttf = bestelling.getTransportdienst().getTrackTraceFormat();
		String trackAndTraceCode = bestelling.getTrackAndTraceCode();
		String generatedCode;
		// gebruik het ID van de bestelling om de code uniek te maken
		String bestellingID = String.valueOf(bestelling.getId());
		do {
			generatedCode = Tools.generateTrackAndTraceCode(ttf, bestellingID);
				
		} while (generatedCode.equals(trackAndTraceCode));
		bestelling.setTrackAndTraceCode(generatedCode);
		
	}

}
