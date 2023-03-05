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

import service.ValidationService;

@Entity
@Table(name = "Personen")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "soort")
public abstract class Persoon implements Serializable
{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	protected Persoon()
	{

	}

	public Persoon(String voornaam, String familienaam, String telefoonnummer, String email)
	{
		setVoornaam(voornaam);
		setFamilienaam(familienaam);
		setEmailAdress(email);
		setTelefoonnummer(telefoonnummer);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(emailAdress);
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persoon other = (Persoon) obj;
		return Objects.equals(emailAdress, other.emailAdress);
	}

	public long getId()
	{
		return id;
	}

	public String getVoornaam()
	{
		return voornaam;
	}

	public final void setVoornaam(String voornaam)
	{
		ValidationService.controleerNietBlanco(voornaam);
		this.voornaam = voornaam;
	}

	public String getFamilienaam()
	{
		return familienaam;
	}

	public final void setFamilienaam(String familienaam)
	{
		ValidationService.controleerNietBlanco(familienaam);
		this.familienaam = familienaam;
	}

	public String getEmailAdress()
	{
		return emailAdress;
	}

	public final void setEmailAdress(String emailAdress)
	{
		ValidationService.controleerEmail(emailAdress);
		this.emailAdress = emailAdress;
	}

	public String getTelefoonnummer()
	{
		return telefoonnummer;
	}

	public void setTelefoonnummer(String telefoonnummer)
	{
		ValidationService.controleerTelefoonnummer(telefoonnummer);
		this.telefoonnummer = telefoonnummer;
	}

}
