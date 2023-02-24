package domein;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
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


@Entity
@Table(name = "medewerkers")
@NamedQueries({
    @NamedQuery(name = "Medewerker.findByEmailAdress",
                         query = "select m from Medewerker m where m.emailAdress = :emailAdress")            
})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "soort")
public abstract class Medewerker implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "personeelsNr", unique = true)
	private int personeelsNr;
	
	

	@Column(name = "Voornaam")
    private String voornaam;
	@Column(name = "Familienaam")
    private String familienaam;
	@Column(name = "Email_adres", unique = true)
    private String emailAdress;
	@Column(name = "Hashed_paswoord")
    private String hashedPW;

	@Transient
	String salt = BCrypt.gensalt(12);
    
    public Medewerker(String voornaam, String familienaam, String email, String password, int personeelsNr) {

		setVoornaam(voornaam);
		setFamilienaam(familienaam);
		setEmail(email);
		setHashedPW(password);
		setPersoneelsNr(personeelsNr);;
	}

	public Medewerker() {
    	
    }
	
    @Override
	public int hashCode() {
		return Objects.hash(personeelsNr);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Medewerker other = (Medewerker) obj;
		return personeelsNr == other.personeelsNr;
	}

	public int getMederwerkersNR() {
		return personeelsNr;
	}

	public final void setMederwerkersNR(int mederwerkersNR) {
		this.personeelsNr = mederwerkersNR;
	}

	public String getVoornaam() {
		return voornaam;
	}

	public final void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}

	public String getFamilienaam() {
		return familienaam;
	}

	public final void setFamilienaam(String familienaam) {
		this.familienaam = familienaam;
	}

	public String getEmail() {
		return emailAdress;
	}

	public final void setEmail(String email) {
		
		this.emailAdress = email;
	}

	public String getHashedPW() {
		return hashedPW;
	}

	public final void setHashedPW(String password) {
		this.hashedPW = BCrypt.hashpw(password, salt);
	}	
	
	
	public int getPersoneelsNr() {
		return personeelsNr;
	}

	public final void setPersoneelsNr(int personeelsNR) {
		this.personeelsNr = personeelsNR;
	}

	public abstract String getRol() ;

	public abstract void setRol() ;


	
	
	
}


    










