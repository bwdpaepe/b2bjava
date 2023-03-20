package domein;

import java.util.Date;

public class GeplaatstBestellingState extends BestellingState {

	public GeplaatstBestellingState(Bestelling bestelling) {
		super(bestelling);
	}

	@Override
	public void verwerkBestelling(Transportdienst transportdienst) {

		// TODO genereren van een TTC
		bestelling.setStatus("verwerkt");
		bestelling.setTransportdienst(transportdienst);
		Notificatie notificatie = new Notificatie(new Date(), false, bestelling.getAankoper(), bestelling);
		bestelling.setNotificatie(notificatie);
		// TODO
		bestelling.setTrackAndTraceCode("code genereren");
		bestelling.toState(new VerwerktBestellingState(bestelling));
	}

}
