package repository;

import java.util.List;
import java.util.Set;

import domein.Dienst;
import domein.Transportdienst;

public class TransportdienstDTO extends DienstDTO {

	private final int barcodeLengte;
	private final boolean isBarcodeEnkelCijfers;
	private final String barcodePrefix;
	private final String verificatieCodeString;

	public TransportdienstDTO(Transportdienst td) {
		super(td);
		this.barcodeLengte = td.getBarcodeLengte();
		this.isBarcodeEnkelCijfers = td.isBarcodeEnkelCijfers();
		this.barcodePrefix = td.getBarcodePrefix();
		this.verificatieCodeString = td.getVerificatieCode();
	}

	public TransportdienstDTO(long id, String naam, boolean isActief, Set<ContactpersoonDTO> contactpersonen,
			int barcodeLengte, boolean isBarcodeEnkelCijfers, String barcodePrefix, String verificatieCodeString) {
		super(id, naam, isActief, contactpersonen);
		this.barcodeLengte = barcodeLengte;
		this.isBarcodeEnkelCijfers = isBarcodeEnkelCijfers;
		this.barcodePrefix = barcodePrefix;
		this.verificatieCodeString = verificatieCodeString;
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
		return "TransportdienstDTO [barcodeLengte=" + barcodeLengte + ", isBarcodeEnkelCijfers=" + isBarcodeEnkelCijfers
				+ ", barcodePrefix=" + barcodePrefix + ", verificatieCodeString=" + verificatieCodeString + ", id=" + id
				+ ", naam=" + naam + ", isActief=" + isActief + ", contactpersonen=" + contactpersonen + "]";
	}

}
