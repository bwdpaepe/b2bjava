package domein;

public class VerwerktBestellingState extends BestellingState{

	public VerwerktBestellingState(Bestelling bestelling) {
		super(bestelling);
	}
	
	public void wijzigTrackAndTraceCodeBestelling() {
		bestelling.setTrackAndTraceCode("todo");
	}

}
