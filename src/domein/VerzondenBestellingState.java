package domein;

import java.util.Date;

public class VerzondenBestellingState extends BestellingState {
	public VerzondenBestellingState(Bestelling bestelling) {
		super(bestelling);
	}
	
	public void uitvoorleveringBestelling() {
		bestelling.setStatus("uit_voor_levering");
		bestelling.getNotificatie().setCreationDate(new Date());
		bestelling.toState(new UitVoorLeveringBestellingState(bestelling));
	}

	

}
