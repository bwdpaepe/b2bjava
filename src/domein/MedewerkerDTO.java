package domein;


// Een Data Transfer Object klasse om een immutable object van Medewerker te kunnen 
// returnen naar de GUI
// ENKEL FINAL ATTRIBUTEN EN NOOIT LOGICA OPNEMEN
public class MedewerkerDTO
{
	private final String voornaam;
	private final String familienaam;
	private final String email;
	private final String rol;
	private final int personeelsNr;

	public MedewerkerDTO(String voornaam, String familienaam, String email, String rol, int personeelsNr)
	{
		this.voornaam = voornaam;
		this.familienaam = familienaam;
		this.email = email;
		this.rol = rol;
		this.personeelsNr = personeelsNr;
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

	public String getRol()
	{
		return rol;
	}
	
	public int getPersoneelsNr() {
		return personeelsNr;
	}
}
