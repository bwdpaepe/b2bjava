package domein;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue("A")
public class Admin extends Medewerker {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String rol;

	public Admin(String voornaam, String familienaam, String email, String hashedPW, int personeelsNR) {
		super(voornaam, familienaam, email, hashedPW, personeelsNR);
		setRol();

	}

	public Admin() {
		super();
	}

	@Override
	public String getRol() {

		return this.rol;
	}

	@Override
	public void setRol() {
		this.rol = "Admin";

	}

}
