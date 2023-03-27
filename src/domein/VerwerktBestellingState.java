package domein;

import java.util.Date;

import javax.naming.SizeLimitExceededException;

import util.Tools;

public class VerwerktBestellingState extends BestellingState {

	public VerwerktBestellingState(Bestelling bestelling) {
		super(bestelling);
	}

	@Override
	public void wijzigBestelling(Transportdienst transportdienst) throws SizeLimitExceededException {
		// transportdienst
		if(!transportdienst.equals(bestelling.getTransportdienst())) {
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
			
			bestelling.getNotificatie().setCreationDate(new Date());
		}
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
	
	public void verzendBestelling() {
		bestelling.setStatus("verzonden");
		bestelling.getNotificatie().setCreationDate(new Date());
		bestelling.toState(new VerzondenBestellingState(bestelling));
	}

}
