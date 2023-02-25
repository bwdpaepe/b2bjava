package repository;


// Een Data Transfer Object klasse om een immutable object van Medewerker te kunnen 
// returnen naar de GUI
// ENKEL FINAL ATTRIBUTEN EN NOOIT LOGICA OPNEMEN
public class MedewerkerDTO
{
	private final String voornaam;
	private final String familienaam;
	private final String email;
	private final String functie;
	private final int personeelsNr;

	public MedewerkerDTO(String voornaam, String familienaam, String email, int personeelsNr, String functie)
	{
		this.voornaam = voornaam;
		this.familienaam = familienaam;
		this.email = email;
		this.functie = functie;
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
		return functie;
	}
	
	public int getPersoneelsNr() {
		return personeelsNr;
	}
}
