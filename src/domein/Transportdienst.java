package domein;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("Transportdienst")
public class Transportdienst extends Dienst
{

	private static final long serialVersionUID = 1L;
	
	@OneToOne
	private TrackTraceFormat tractTraceFormaat;
	
	// lege Constructor voor JPA
	protected Transportdienst()
	{
		super();
	}

	public Transportdienst(String naam)
	{
		super(naam, true);  // By default: transportdienst is actief bij aanmaak (zie UC)
	}

	public TrackTraceFormat getTractTraceFormaat()
	{
		return tractTraceFormaat;
	}

	public final void setTractTraceFormaat(TrackTraceFormat tractTraceFormaat)
	{
		this.tractTraceFormaat = tractTraceFormaat;
	}

	
	
//	public TrackTraceFormat getBarcodeFormaat()
//	{
//		return barcodeFormaat;
//	}

}
