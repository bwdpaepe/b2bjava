package repository;

public class TrackTraceFormatDTO {
	
	private final int barcodeLengte;
	private final boolean isBarcodeEnkelCijfers;
	private final String barcodePrefix;
	private final String verificatieCodeString;
	
	public TrackTraceFormatDTO(int barcodeLengte, boolean isBarcodeEnkelCijfers, String barcodePrefix, String verificatieCodeString) {
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
		return "barcodeLengte=" + barcodeLengte + ", isBarcodeEnkelCijfers="
				+ isBarcodeEnkelCijfers + ", barcodePrefix=" + barcodePrefix + ", verificatieCodeString="
				+ verificatieCodeString;
	}
	
	

}
