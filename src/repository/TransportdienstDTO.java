package repository;

import java.util.List;
import java.util.Set;

public class TransportdienstDTO extends DienstDTO {
	
	private final int barcodeLengte;
	private final boolean isBarcodeEnkelCijfers;
	private final String barcodePrefix;
	private final String verificatieCodeString;
	
	public TransportdienstDTO(long id, String naam, boolean isActief , Set<ContactpersoonDTO> contactpersonen, int barcodeLengte, boolean isBarcodeEnkelCijfers, String barcodePrefix,
			String verificatieCode) {
		super(id, naam, isActief, contactpersonen);
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


	
	



	

	
		
	

}
