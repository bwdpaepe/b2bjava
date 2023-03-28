package domein;

import javax.naming.SizeLimitExceededException;

public abstract class BestellingState {

	protected final Bestelling bestelling;

	public BestellingState(Bestelling bestelling) {
		this.bestelling = bestelling;
	}

	public void verwerkBestelling(Transportdienst transportdienst) throws SizeLimitExceededException {
		throw new IllegalArgumentException(
				"Een bestelling kan enkel verwerkt worden wanneer deze de status geplaatst heeft");
	}

	public void wijzigBestelling(Transportdienst transportdienst) throws SizeLimitExceededException {
		throw new IllegalArgumentException("Een bestelling kan enkel gewijzigd worden wanneer deze verwerkt is");
	}

	public void wijzigTrackAndTraceCode() throws SizeLimitExceededException {
		throw new IllegalArgumentException(
				"Een track and trace code kan enkel gewijzigd worden wanneer deze verwerkt is");
	}
	
	public void verzendBestelling() {
		throw new IllegalArgumentException(
				"Een bestelling kan enkel verzonden worden wanneer deze de status verwerkt heeft");
	}
	
	public void uitvoorleveringBestelling() {
		throw new IllegalArgumentException(
				"Een bestelling kan enkel uit voor levering zijn wanneer deze de status verzonden heeft");
	}
	
	public void leverBestelling() {
		throw new IllegalArgumentException(
				"Een bestelling kan enkel afgeleverd zijn wanneer deze de status uit voor levering heeft");
	}

}
