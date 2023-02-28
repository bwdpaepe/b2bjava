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
@Table(name = "Gebruikers")
@NamedQueries(
	{ @NamedQuery(name = "User.findByEmailAdress", query = "select u from User u where u.emailAdress = :emailAdress") })
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "soort")
public abstract class User implements Serializable
{

	private static final long serialVersionUID = 1L;

	@Transient
	public static final int MIN_PW_LENGTH = 8; // TODO afspreken met team

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "Voornaam")
	private String voornaam;
	@Column(name = "Familienaam")
	private String familienaam;
	@Column(name = "Email_adres", unique = true)
	private String emailAdress;
	@Column(name = "Hashed_paswoord")
	private String hashedPW;
	@Column(name = "telefoonnummer")
	private String telefoonnummer;
	@Column(name = "adres")
	private String adres;

	@Transient
	private String salt = BCrypt.gensalt(12);

	public User(String voornaam, String familienaam, String email, String password, String telefoonnumer, String adres)
	{
		setVoornaam(voornaam);
		setFamilienaam(familienaam);
		setEmail(email);
		setHashedPW(password);
		setAdres(adres);
		setTelefoonnummer(telefoonnumer);
	}

	// lege Constructor voor JPA
	public User()
	{

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
		Medewerker other = (Medewerker) obj;
		return emailAdress == other.getEmail();
	}

	public String getVoornaam()
	{
		return voornaam;
	}

	public final void setVoornaam(String voornaam)
	{
		if (voornaam == null || voornaam.isBlank())
		{
			throw new IllegalArgumentException("Voornaam is verplicht");
		}
		this.voornaam = voornaam;
	}

	public String getFamilienaam()
	{
		return familienaam;
	}

	public final void setFamilienaam(String familienaam)
	{
		if (familienaam == null || familienaam.isBlank())
		{
			throw new IllegalArgumentException("Familienaam is verplicht");
		}
		this.familienaam = familienaam;
	}

	public String getEmail()
	{
		return emailAdress;
	}

	public final void setEmail(String email)
	{
		if (email == null || email.isBlank())
		{
			throw new IllegalArgumentException("E-mailadres is verplicht");
		}
		// https://howtodoinjava.com/java/regex/java-regex-validate-email-address/
		if (!email.matches(
				"^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$"))
		{
			throw new IllegalArgumentException("Ongeldig e-mailadres formaat");
		}
		this.emailAdress = email;
	}

	public String getHashedPW()
	{
		return hashedPW;
	}

	public final void setHashedPW(String password)
	{
		// error als geen wachtwoord, te kort, of bevat spatie
		if (password == null || password.length() < MIN_PW_LENGTH || password.matches(".*\\s.*"))
		{
			throw new IllegalArgumentException("Wachtwoord is ongeldig");
		}
		this.hashedPW = BCrypt.hashpw(password, salt);
	}

	public String getTelefoonnummer()
	{
		return telefoonnummer;
	}

	public final void setTelefoonnummer(String telefoonnummer)
	{
		// error als geen telefoonnummer, of niet voldoet aan de regex: eventueel
		// beginnen met een '+', en verder enkel cijfers (minstens 6)
		if (telefoonnummer == null || !telefoonnummer.matches("^\\+?\\d{6,}$"))
		{
			throw new IllegalArgumentException("Telefoonnummer is ongeldig");
		}
		this.telefoonnummer = telefoonnummer;
	}

	public String getAdres()
	{
		return adres;
	}

	public final void setAdres(String adres)
	{
		if (adres == null || adres.isBlank())
		{
			throw new IllegalArgumentException("Adres is verplicht");
		}
		this.adres = adres;
	}

}