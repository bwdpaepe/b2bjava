package domein;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Contactpersoon")
public class Contactpersoon extends Persoon {


	private static final long serialVersionUID = 1L;

		// lege Constructor voor JPA
		protected Contactpersoon() {
			
		}

		public Contactpersoon(String voornaam, String familienaam, String email, String telefoonnummer)
		{
			super(voornaam, familienaam, email, telefoonnummer);
		}
		
		
}
