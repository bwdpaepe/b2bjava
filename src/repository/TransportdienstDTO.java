package repository;

import java.util.List;

public class TransportdienstDTO extends DienstDTO {
	
	private final int barcodeLengte;
	private final boolean isBarcodeEnkelCijfers;
	private final String barcodePrefix;
	private final String verificatieCodeString;
	
	public TransportdienstDTO(long id, String naam, boolean isActief, List<String> voornaamLijst, List<String> familienaamLijst, List<String> emailAdressLijst, List<String> telefoonnummerLijst, int barcodeLengte, boolean isBarcodeEnkelCijfers, String barcodePrefix,
			String verificatieCode) {
		super(id, naam, isActief, voornaamLijst, familienaamLijst, emailAdressLijst, telefoonnummerLijst);
		this.barcodeLengte = barcodeLengte;
		this.isBarcodeEnkelCijfers = isBarcodeEnkelCijfers;
		this.barcodePrefix = barcodePrefix;
		this.verificatieCodeString = verificatieCode;
	}
	
	public int getBarcodeLengte() {
		return barcodeLengte;
	}

	public boolean isBarcodeEnkelCijfers() {
		return isBarcodeEnkelCijfers;
	}

	public String getBarcodePrefix() {
		return barcodePrefix;
	}

	public String getVerificatieCodeString() {
		return verificatieCodeString;
	}

	@Override
	public String toString() {
		return "TransportdienstDTO [id=" + id + ", naam=" + naam + ", isActief=" + isActief + ", voornaamLijst="
				+ voornaamLijst + ", familienaamLijst=" + familienaamLijst + ", emailAdressLijst=" + emailAdressLijst
				+ ", telefoonnummerLijst=" + telefoonnummerLijst + ", barcodeLengte=" + barcodeLengte
				+ ", isBarcodeEnkelCijfers=" + isBarcodeEnkelCijfers + ", barcodePrefix=" + barcodePrefix
				+ ", verificatieCodeString=" + verificatieCodeString + "]";
	}


	
	



	

	
		
	

}
