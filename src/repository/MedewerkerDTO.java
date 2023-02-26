package repository;

// Een Data Transfer Object klasse om een immutable object van Medewerker te kunnen 
// returnen naar de GUI
// ENKEL FINAL ATTRIBUTEN EN NOOIT LOGICA OPNEMEN
public class MedewerkerDTO extends UserDTO
{
	private final int personeelsNr;
	private final String functie;
	private final String adres;
	private final String telefoonnumer;

	public MedewerkerDTO(String voornaam, String familienaam, String email, String adres, String telefoonnumer,
			int personeelsNr, String functie)
	{
		super(voornaam, familienaam, email);
		this.personeelsNr = personeelsNr;
		this.functie = functie;
		this.adres = adres;
		this.telefoonnumer = telefoonnumer;
	}

	protected int getPersoneelsNr()
	{
		return personeelsNr;
	}

	protected String getFunctie()
	{
		return functie;
	}

	protected String getAdres()
	{
		return adres;
	}

	protected String getTelefoonnumer()
	{
		return telefoonnumer;
	}

	@Override
	public String toString() {
		return "MedewerkerDTO [personeelsNr=" + personeelsNr + ", functie=" + functie + ", adres=" + adres
				+ ", telefoonnumer=" + telefoonnumer + ", voornaam=" + voornaam + ", familienaam=" + familienaam
				+ ", email=" + email + "]";
	}
	
	
}