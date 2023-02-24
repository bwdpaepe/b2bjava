package domein;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "medewerkers")
public class Magazijnier extends Medewerker {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String rol;


	
	public Magazijnier(String voornaam, String familienaam, String email, String hashedPW, int personeelsNR) {
		super(voornaam, familienaam, email, hashedPW, personeelsNR);
		setRol();

	}
	
	public Magazijnier() {
		super();
	}
	@Override
	public String getRol() {

		return this.rol;
	}

	@Override
	public void setRol() {
		this.rol = "Magazijnier";
		
	}


}
