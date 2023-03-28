package domein;

import java.util.Date;

public class UitVoorLeveringBestellingState extends BestellingState {
	public UitVoorLeveringBestellingState(Bestelling bestelling) {
		super(bestelling);
	}
	
	@Override
	public void leverBestelling() {
		bestelling.setStatus("geleverd");
		bestelling.getNotificatie().setCreationDate(new Date());
		bestelling.getNotificatie().setBekenen(false);
		bestelling.toState(new GeleverdBestellingState(bestelling));
	}

	

}
