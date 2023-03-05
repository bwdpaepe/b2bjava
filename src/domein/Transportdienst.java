package domein;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("Transportdienst")
public class Transportdienst extends Dienst
{

	private static final long serialVersionUID = 1L;
	
	@OneToOne(cascade = CascadeType.ALL)
	private TrackTraceFormat trackTraceFormat;
	
	// lege Constructor voor JPA
	protected Transportdienst()
	{
		
	}

	public Transportdienst(String naam, Bedrijf bedrijf)
	{
		super(naam, bedrijf, true);  // By default: transportdienst is actief bij aanmaak (zie UC)
	}

	public TrackTraceFormat getTrackTraceFormat()
	{
		return trackTraceFormat;
	}

	@Override
	public final void setTrackTraceFormat(TrackTraceFormat trackTraceFormat)
	{
		this.trackTraceFormat = trackTraceFormat;
	}

	
	
	public int getBarcodeLengte()
	{
		return trackTraceFormat.getBarcodeLengte();
	}
	
	public boolean isBarcodeEnkelCijfers()
	{
		return trackTraceFormat.isBarcodeEnkelCijfers();
	}
	
	public String getBarcodePrefix()
	{
		return trackTraceFormat.getBarcodePrefix();
	}

	public String getVerificatieCode()
	{
		return trackTraceFormat.getVerificatieCode();
	}

}
