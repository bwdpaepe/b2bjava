package repository;

// Een Data Transfer Object klasse om een immutable object van Medewerker te kunnen 
// returnen naar de GUI
// ENKEL FINAL ATTRIBUTEN EN NOOIT LOGICA OPNEMEN
public class MedewerkerDTO extends UserDTO
{
	private final int personeelsNr;
	private final String functie;

	public MedewerkerDTO(String voornaam, String familienaam, String email, String adres, String telefoonnummer,
			int personeelsNr, String functie)
	{
		super(voornaam, familienaam, email, telefoonnummer, adres);
		this.personeelsNr = personeelsNr;
		this.functie = functie;
	}

	public int getPersoneelsNr()
	{
		return personeelsNr;
	}

	public String getFunctie()
	{
		return functie;
	}

	@Override
	public final String toString()
	{
		return "MedewerkerDTO [personeelsNr=" + personeelsNr + ", functie=" + functie + ", " + super.toString() + "]";
	}
}