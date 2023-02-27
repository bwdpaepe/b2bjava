package domein;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TrackTraceFormats")
public class TrackTraceFormat implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private int barcodeLengte;
	private boolean barcodeEnkelCijfers;
	private String barcodePrefix;
	private Verificatiecode verificatieCode;
	
	// lege Constructor voor JPA
	protected TrackTraceFormat() {
		
	}
	
	public TrackTraceFormat(int barcodeLengte, boolean barcodeEnkelCijfers, String barcodePrefix, int geselecteerdeVerificatie) {
		this.barcodeLengte = barcodeLengte;
		this.barcodeEnkelCijfers = barcodeEnkelCijfers;
		this.barcodePrefix = barcodePrefix;
		this.verificatieCode = switch(geselecteerdeVerificatie) {
			case 0 -> Verificatiecode.ORDERID;
			case 1 -> Verificatiecode.POSTCODE;
			default -> throw new IllegalArgumentException("Unexpected value: " + geselecteerdeVerificatie);
		};
	}

	public int getBarcodeLengte() {
		return barcodeLengte;
	}

	private void setBarcodeLengte(int barcodeLengte) {
		this.barcodeLengte = barcodeLengte;
	}

	public boolean isBarcodeEnkelCijfers() {
		return barcodeEnkelCijfers;
	}

	private void setBarcodeEnkelCijfers(boolean barcodeEnkelCijfers) {
		this.barcodeEnkelCijfers = barcodeEnkelCijfers;
	}

	public String getBarcodePrefix() {
		return barcodePrefix;
	}

	private void setBarcodePrefix(String barcodePrefix) {
		this.barcodePrefix = barcodePrefix;
	}
	
	public Verificatiecode getVerificatieCode() {
		return verificatieCode;
	}

	private void setVerificatieCode(Verificatiecode verificatieCode) {
		this.verificatieCode = verificatieCode;
	}

	@Override
	public int hashCode() {
		return Objects.hash(barcodeEnkelCijfers, barcodeLengte, barcodePrefix, verificatieCode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TrackTraceFormat other = (TrackTraceFormat) obj;
		return barcodeEnkelCijfers == other.barcodeEnkelCijfers && barcodeLengte == other.barcodeLengte
				&& Objects.equals(barcodePrefix, other.barcodePrefix) && verificatieCode == other.verificatieCode;
	}
	
}
