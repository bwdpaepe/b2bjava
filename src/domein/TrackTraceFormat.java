package domein;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import service.ValidationService;

@Entity
@Table(name = "TrackTraceFormats")
@NamedQueries(
		{ @NamedQuery(name = "TrackTraceFormat.findTtfByAll", query = "select ttf from TrackTraceFormat ttf where ttf.barcodeLengte = :barcodeLengte and ttf.isBarcodeEnkelCijfers = :isBarcodeEnkelCijfers and ttf.barcodePrefix = :barcodePrefix and ttf.verificatieCodeString = :verificatieCodeString") })
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

	public final void setBarcodeLengte(int barcodeLengte)
	{
		ValidationService.controleerBarcodeLengte(barcodeLengte);
		this.barcodeLengte = barcodeLengte;
	}

	public boolean isBarcodeEnkelCijfers()
	{
		return isBarcodeEnkelCijfers;
	}

	public final void setIsBarcodeEnkelCijfers(boolean isBarcodeEnkelCijfers)
	{
		this.isBarcodeEnkelCijfers = isBarcodeEnkelCijfers;
	}

	public String getBarcodePrefix()
	{
		return barcodePrefix;
	}

	public final void setBarcodePrefix(String barcodePrefix)
	{
		ValidationService.controleerBarcodePrefix(barcodePrefix);
		this.barcodePrefix = barcodePrefix;
	}

	public String getVerificatieCode()
	{
		return verificatieCodeString;
	}

	public final void setVerificatieCode(String verificatieCode)
	{

		ValidationService.controleerTrackVerificatiecode(verificatieCode);

		this.verificatieCodeString = switch (verificatieCode.toLowerCase())
			{
			case "postcode" -> Verificatiecode.POSTCODE.toString();
			case "orderid" -> Verificatiecode.ORDERID.toString();
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
