package domein;

import java.util.Date;

public class UitVoorLeveringBestellingState extends BestellingState {
	public UitVoorLeveringBestellingState(Bestelling bestelling) {
		super(bestelling);
	}
	
	public void leverBestelling() {
		bestelling.setStatus("geleverd");
		bestelling.getNotificatie().setCreationDate(new Date());
		bestelling.toState(new GeleverdBestellingState(bestelling));
	}

	

}
