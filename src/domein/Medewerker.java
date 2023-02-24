package domein;

import java.io.Serializable;
import java.math.BigDecimal;
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


@Entity
@Table(name = "medewerkers")
@NamedQueries({
    @NamedQuery(name = "Medewerker.findByName",
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
	
	@Column(name = "personeelsNR")
	private int personeelsNR;
	
	

	@Column(name = "Voornaam")
    private String voornaam;
	@Column(name = "Familienaam")
    private String familienaam;
	@Column(name = "Email_adres")
    private String emailAdress;
	@Column(name = "Hashed_paswoord")
    private String hashedPW;


    
    public Medewerker(String voornaam, String familienaam, String email, String hashedPW, int personeelsNR) {

		this.voornaam = voornaam;
		this.familienaam = familienaam;
		this.emailAdress = email;
		this.hashedPW = hashedPW;
		this.personeelsNR = personeelsNR;
	}

	public Medewerker() {
    	
    }
    
    @Override
	public int hashCode() {
		return Objects.hash(personeelsNR);
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
		return personeelsNR == other.personeelsNR;
	}

	public int getMederwerkersNR() {
		return personeelsNR;
	}

	public void setMederwerkersNR(int mederwerkersNR) {
		this.personeelsNR = mederwerkersNR;
	}

	public String getVoornaam() {
		return voornaam;
	}

	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}

	public String getFamilienaam() {
		return familienaam;
	}

	public void setFamilienaam(String familienaam) {
		this.familienaam = familienaam;
	}

	public String getEmail() {
		return emailAdress;
	}

	public void setEmail(String email) {
		this.emailAdress = email;
	}

	public String getHashedPW() {
		return hashedPW;
	}

	public void setHashedPW(String hashedPW) {
		this.hashedPW = hashedPW;
	}	
	
	
	public int getPersoneelsNR() {
		return personeelsNR;
	}

	public void setPersoneelsNR(int personeelsNR) {
		this.personeelsNR = personeelsNR;
	}

	public abstract String getRol() ;

	public abstract void setRol() ;
	
	
	
}


    










