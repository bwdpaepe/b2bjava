package util;

import domein.Bestelling;
import domein.Transportdienst;

public abstract class BestellingState {
	protected final Bestelling bestelling;
	
	public BestellingState(Bestelling bestelling) {
		this.bestelling = bestelling;
	}
	
	public void verwerkBestelling(Transportdienst transportdienst) {
		//nothing to do
	}
	public void wijzigBestelling() {
		//nothing to do
	}
	public void stuurNotificatie() {
		// nothing to do
	}
}
