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
import javax.persistence.Table;

@Entity
@Table(name = "Personen")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "soort")
public abstract class Persoon implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column(name = "Voornaam")
	private String voornaam;
	@Column(name = "Familienaam")
	private String familienaam;
	@Column(name = "Email_adres", unique = true)
	private String emailAdress;
	@Column(name = "telefoonnummer")
	private String telefoonnummer;
	
	// lege Constructor voor JPA
	protected Persoon() {
		
	}
	
	public Persoon(String voornaam, String familienaam, String email, String telefoonnummer) {
		this.voornaam = voornaam;
		this.familienaam =  familienaam;
		this.emailAdress = email;
		this.telefoonnummer = telefoonnummer;
	}

	@Override
	public int hashCode() {
		return Objects.hash(emailAdress);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persoon other = (Persoon) obj;
		return Objects.equals(emailAdress, other.emailAdress);
	}

	
	
	

}
