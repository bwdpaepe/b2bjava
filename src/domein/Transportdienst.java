package domein;

import java.util.HashSet;
import java.util.Set;

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
	public Transportdienst()
	{
		
	}

	public Transportdienst(String naam, Bedrijf bedrijf, Persoon persoon, TrackTraceFormat ttf)
	{
		super(naam, bedrijf, true, persoon);
		setTrackTraceFormat(ttf);// By default: transportdienst is actief bij aanmaak (zie UC)
	}

	public TrackTraceFormat getTrackTraceFormat()
	{
		return trackTraceFormat;
	}


	public void setTrackTraceFormat(TrackTraceFormat trackTraceFormat)
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
	

	public Set<Contactpersoon> getContactpersonen(){
		Set<Contactpersoon> contactpersonen = new HashSet<>();
		for (Persoon p : this.personen) {
			if (p instanceof Contactpersoon) {
				contactpersonen.add((Contactpersoon)p);
			}
		}
		return contactpersonen;
	}



}
