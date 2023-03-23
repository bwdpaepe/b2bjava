package repository;

import domein.User;

// Data Transfer Object PATTERN (DTO PATTERN)
// ENKEL FINAL ATTRIBUTEN EN NOOIT LOGICA OPNEMEN
public abstract class UserDTO
{
	protected final long id;
	protected final String voornaam;
	protected final String familienaam;
	protected final String email;
	protected final String telefoonnummer;
	protected final String adres;
	protected final BedrijfDTO bedrijf;
	protected final Boolean isActief;

	public UserDTO(User user)
	{
		this.id = user.getId();
		this.voornaam = user.getVoornaam();
		this.familienaam = user.getFamilienaam();
		this.email = user.getEmail();
		this.telefoonnummer = user.getTelefoonnummer();
		this.adres = user.getAdres();
		this.bedrijf = new BedrijfDTO(user.getBedrijf());
		this.isActief = user.getIsActief();
	}
	
	public long getID() {
		return this.id;
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
	
	public BedrijfDTO getBedrijf() {
		return bedrijf;
	}
	
	public Boolean getIsActief()
	{
		return isActief;
	}

	@Override
	public String toString()
	{
		return "UserDTO [id=" + id + ", voornaam=" + voornaam + ", familienaam=" + familienaam + ", email=" + email
				+ ", telefoonnummer=" + telefoonnummer + ", adres=" + adres + ", bedrijf=" + bedrijf + ", isActief="
				+ isActief + "]";
	}

}