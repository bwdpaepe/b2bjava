package repository;

import domein.User;

// Data Transfer Object PATTERN (DTO PATTERN)
// ENKEL FINAL ATTRIBUTEN EN NOOIT LOGICA OPNEMEN
public abstract class UserDTO
{
	protected final String voornaam;
	protected final String familienaam;
	protected final String email;
	protected final String telefoonnummer;
	protected final String adres;

	public UserDTO(User user)
	{
		this.voornaam = user.getVoornaam();
		this.familienaam = user.getFamilienaam();
		this.email = user.getEmail();
		this.telefoonnummer = user.getTelefoonnummer();
		this.adres = user.getAdres();
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