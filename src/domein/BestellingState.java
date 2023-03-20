package domein;

public abstract class BestellingState {

	protected final Bestelling bestelling;

	public BestellingState(Bestelling bestelling) {
		this.bestelling = bestelling;
	}

	public void verwerkBestelling() {
		throw new IllegalArgumentException(
				"Een bestelling kan enkel verwerkt worden wanneer deze de status geplaatst heeft");
	}

	public void wijzigTrackAndTraceCodeBestelling() {
		throw new IllegalArgumentException("Een bestelling kan enkel gewijzigd worden wanneer deze verwerkt is");
	}

}
