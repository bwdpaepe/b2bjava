package domein;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.mindrot.jbcrypt.BCrypt;

import util.AdminFunctie;
import util.Functie;
import util.MagazijnierFunctie;

@Entity
@DiscriminatorValue("Medewerker")
public class Medewerker extends User
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Column(name= "Functie")
	private String functieString;
	
	
	@Transient
	private Functie functie;


	public Medewerker(String voornaam, String familienaam, String email, String password, int personeelsNr, String functie)
	{

		super(voornaam,familienaam,email,password,personeelsNr);
		setFunctie(functie);
	}

	// Lege constructor nodig voor JPA
	public Medewerker()
	{
		super();
	}


	public String getFunctie()
	{
		return this.functieString;
	}

	public final void setFunctie(String functie)
	{
		if(functie == null || functie.isEmpty() || functie.isBlank()) {
			throw new IllegalArgumentException("This function is not valid, please choose Admin or Magazijnier");
		}
		
		switch (functie.toLowerCase()) {
		case "magazijnier": this.functie = new MagazijnierFunctie();			
			
			break;
		case "admin": this.functie = new AdminFunctie();
		
		break;

		default: throw new IllegalArgumentException("This function is not valid, please choose Admin or Magazijnier");
			
		}
		
		this.functieString = this.functie.toString();
	}

}

