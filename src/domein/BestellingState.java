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

}
