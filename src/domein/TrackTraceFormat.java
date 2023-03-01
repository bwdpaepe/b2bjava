package domein;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import service.ValidationService;

@Entity
@Table(name = "TrackTraceFormats")
public class TrackTraceFormat implements Serializable
{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private int barcodeLengte;
	private boolean isBarcodeEnkelCijfers;
	private String barcodePrefix;
	private String verificatieCodeString;

	// lege Constructor voor JPA
	protected TrackTraceFormat()
	{

	}

	public TrackTraceFormat(int barcodeLengte, boolean isBarcodeEnkelCijfers, String barcodePrefix,
			String verificatieCode)
	{
		setBarcodeLengte(barcodeLengte);
		setIsBarcodeEnkelCijfers(isBarcodeEnkelCijfers);
		setBarcodePrefix(barcodePrefix);
		setVerificatieCode(verificatieCode);
	}

	public int getBarcodeLengte()
	{
		return barcodeLengte;
	}

	private final void setBarcodeLengte(int barcodeLengte)
	{
		ValidationService.controleerBarcodeLengte(barcodeLengte);
		this.barcodeLengte = barcodeLengte;
	}

	public boolean isBarcodeEnkelCijfers()
	{
		return isBarcodeEnkelCijfers;
	}

	private final void setIsBarcodeEnkelCijfers(boolean isBarcodeEnkelCijfers)
	{
		this.isBarcodeEnkelCijfers = isBarcodeEnkelCijfers;
	}

	public String getBarcodePrefix()
	{
		return barcodePrefix;
	}

	private final void setBarcodePrefix(String barcodePrefix)
	{
		ValidationService.controleerBarcodePrefix(barcodePrefix);
		this.barcodePrefix = barcodePrefix;
	}

	public String getVerificatieCode()
	{
		return verificatieCodeString;
	}

	private final void setVerificatieCode(String verificatieCode)
	{

		ValidationService.controleerTrackVerificatiecode(verificatieCode);

		this.verificatieCodeString = switch (verificatieCode.toLowerCase())
			{
			case "postcode" -> Verificatiecode.ORDERID.toString();
			case "orderid" -> Verificatiecode.POSTCODE.toString();
			default -> throw new IllegalArgumentException("Verificatiecode is ongeldig: " + verificatieCode);
			};
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(isBarcodeEnkelCijfers, barcodeLengte, barcodePrefix, verificatieCodeString);
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TrackTraceFormat other = (TrackTraceFormat) obj;
		return isBarcodeEnkelCijfers == other.isBarcodeEnkelCijfers && barcodeLengte == other.barcodeLengte
				&& Objects.equals(barcodePrefix, other.barcodePrefix)
				&& verificatieCodeString == other.verificatieCodeString;
	}

}
