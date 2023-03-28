package domein;

import java.util.Date;

public class VerzondenBestellingState extends BestellingState {
	public VerzondenBestellingState(Bestelling bestelling) {
		super(bestelling);
	}
	
	@Override
	public void uitvoorleveringBestelling() {
		bestelling.setStatus("uit_voor_levering");
		bestelling.getNotificatie().setCreationDate(new Date());
		bestelling.getNotificatie().setBekenen(false);
		bestelling.toState(new UitVoorLeveringBestellingState(bestelling));
	}

	

}
