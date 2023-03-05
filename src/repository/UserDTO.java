package repository;

// Data Transfer Object PATTERN (DTO PATTERN)
// ENKEL FINAL ATTRIBUTEN EN NOOIT LOGICA OPNEMEN
public abstract class UserDTO
{
	protected final String voornaam;
	protected final String familienaam;
	protected final String email;
	protected final String telefoonnummer;
	protected final String adres;

	public UserDTO(String voornaam, String familienaam, String email, String telefoonnummer, String adres)
	{
		this.voornaam = voornaam;
		this.familienaam = familienaam;
		this.email = email;
		this.telefoonnummer = telefoonnummer;
		this.adres = adres;
	}

	public String getVoornaam()
	{
		return voornaam;
	}

	public String getFamilienaam()
	{
		return familienaam;
	}

	public String getEmail()
	{
		return email;
	}

	public String getTelefoonnummer()
	{
		return telefoonnummer;
	}

	public String getAdres()
	{
		return adres;
	}

	@Override
	public String toString() {
		return "UserDTO [voornaam=" + voornaam + ", familienaam=" + familienaam + ", email=" + email
				+ ", telefoonnummer=" + telefoonnummer + ", adres=" + adres + "]";
	}

	


}