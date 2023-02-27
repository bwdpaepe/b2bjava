package domein;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("Transportdienst")
public class Transportdienst extends Dienst {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManyToOne
	private TrackTraceFormat barcodeFormaat;
	
	// lege Constructor voor JPA
	protected Transportdienst() {
		super();
	}
	
	protected Transportdienst(String naam, boolean isActief) {
		super(naam, isActief);
	}

	public TrackTraceFormat getBarcodeFormaat() {
		return barcodeFormaat;
	}
	
	

}
