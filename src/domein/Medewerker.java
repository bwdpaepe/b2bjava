package domein;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "medewerkers")
public class Medewerker implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "mederwerksNR")
	private int mederwerkersNR;
	
	

	
    private String voornaam;
    private String familienaam;
    private String email;
    private String hashedPW;

    
    public Medewerker(String voornaam, String familienaam, String email, String hashedPW) {

		this.voornaam = voornaam;
		this.familienaam = familienaam;
		this.email = email;
		this.hashedPW = hashedPW;
	}

	protected Medewerker() {
    	
    }
    
    @Override
	public int hashCode() {
		return Objects.hash(mederwerkersNR);
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
		return mederwerkersNR == other.mederwerkersNR;
	}

	public int getMederwerkersNR() {
		return mederwerkersNR;
	}

	public void setMederwerkersNR(int mederwerkersNR) {
		this.mederwerkersNR = mederwerkersNR;
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
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHashedPW() {
		return hashedPW;
	}

	public void setHashedPW(String hashedPW) {
		this.hashedPW = hashedPW;
	}
	
	
	
}


    










