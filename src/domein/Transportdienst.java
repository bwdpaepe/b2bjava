package domein;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Transportdienst")
public class Transportdienst extends Dienst
{

	private static final long serialVersionUID = 1L;
	
//	@ManyToOne
//	private TrackTraceFormat barcodeFormaat;

	// lege Constructor voor JPA
	protected Transportdienst()
	{
		super();
	}

	protected Transportdienst(String naam)
	{
		super(naam, true);  // By default: transportdienst is actief bij aanmaak (zie UC)
	}

//	public TrackTraceFormat getBarcodeFormaat()
//	{
//		return barcodeFormaat;
//	}

}
