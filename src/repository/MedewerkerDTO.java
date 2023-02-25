package repository;

import util.Functie;

// Een Data Transfer Object klasse om een immutable object van Medewerker te kunnen 
// returnen naar de GUI
// ENKEL FINAL ATTRIBUTEN EN NOOIT LOGICA OPNEMEN
public class MedewerkerDTO
{
	private final String voornaam;
	private final String familienaam;
	private final String email;
	private final int personeelsNr;
	private final String functie;

	public MedewerkerDTO(String voornaam, String familienaam, String email, int personeelsNr, String functie)
	{
		this.voornaam = voornaam;
		this.familienaam = familienaam;
		this.email = email;
		this.personeelsNr = personeelsNr;
		this.functie = functie;
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

	public int getPersoneelsNr() {
		return personeelsNr;
	}

	public String getFunctie()
	{
		return functie;
	}

	@Override
	public String toString() {
		return "MedewerkerDTO [voornaam=" + voornaam + ", familienaam=" + familienaam + ", email=" + email
				+ ", personeelsNr=" + personeelsNr + ", functie=" + functie + "]";
	}
	
	

}