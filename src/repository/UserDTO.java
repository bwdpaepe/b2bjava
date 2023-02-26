package repository;

// Data Transfer Object PATTERN (DTO PATTERN)
// ENKEL FINAL ATTRIBUTEN EN NOOIT LOGICA OPNEMEN
public abstract class UserDTO
{
	protected final String voornaam;
	protected final String familienaam;
	protected final String email;

	public UserDTO(String voornaam, String familienaam, String email)
	{
		this.voornaam = voornaam;
		this.familienaam = familienaam;
		this.email = email;
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
	
	@Override
	public abstract String toString();
}