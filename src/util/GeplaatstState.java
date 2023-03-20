package util;

import org.apache.commons.lang3.RandomStringUtils;

import domein.Bestelling;
import domein.TrackTraceFormat;
import domein.Transportdienst;

public class GeplaatstState extends BestellingState {

	public GeplaatstState(Bestelling bestelling) {
		super(bestelling);
	}

	@Override
	public void verwerkBestelling(Transportdienst transportdienst) {
		// transportdienst
		bestelling.setTransportdienst(transportdienst);
		
		// track & trace code
		// TTF
		TrackTraceFormat ttf = transportdienst.getTrackTraceFormat();
		String id = String.valueOf(bestelling.getId());
		// https://mvnrepository.com/artifact/org.apache.commons/commons-lang3/3.12.0
		String generatedString = RandomStringUtils.randomAlphanumeric(ttf.getBarcodeLengte() - id.length() - ttf.getBarcodePrefix().length());
		if(ttf.isBarcodeEnkelCijfers()) {
			generatedString = RandomStringUtils.randomNumeric(ttf.getBarcodeLengte() - id.length() - ttf.getBarcodePrefix().length());
		}
		
		StringBuilder code = new StringBuilder().append(ttf.getBarcodePrefix()).append(generatedString).append(id);
		
		bestelling.setTrackAndTraceCode(new String(code));
		
		
		
		// update state
		// todo : continue here -> create verwerktstate and so on ..
		bestelling.toState(new VerwerktState(bestelling));

	}
	
	

	

}
