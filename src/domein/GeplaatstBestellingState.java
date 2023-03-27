package domein;

import java.util.Date;

import javax.naming.SizeLimitExceededException;

import util.Tools;

public class GeplaatstBestellingState extends BestellingState {

	public GeplaatstBestellingState(Bestelling bestelling) {
		super(bestelling);
	}

	@Override
	public void verwerkBestelling(Transportdienst transportdienst) throws SizeLimitExceededException {
		bestelling.setStatus("verwerkt");
		// transportdienst
		bestelling.setTransportdienst(transportdienst);
		TrackTraceFormat ttf = bestelling.getTransportdienst().getTrackTraceFormat();
		// gebruik het ID van de bestelling om de code uniek te maken
		String bestellingID = String.valueOf(bestelling.getId());
				
		String trackAndTraceCode = bestelling.getTrackAndTraceCode();
		String generatedCode;
		do {
			generatedCode = Tools.generateTrackAndTraceCode(ttf, bestellingID);	
				
		} while (generatedCode.equals(trackAndTraceCode));
		
		bestelling.setTrackAndTraceCode(generatedCode);
		Notificatie notificatie = new Notificatie(new Date(), true, bestelling.getAankoper(), bestelling);
		bestelling.setNotificatie(notificatie);

		bestelling.toState(new VerwerktBestellingState(bestelling));
	}

}
