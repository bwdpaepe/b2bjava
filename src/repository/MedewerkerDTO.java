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
	private final String adres;
	private final String telefoonnumer;

	public MedewerkerDTO(String voornaam, String familienaam, String email, String adres, String telefoonnumer, int personeelsNr, String functie)
	{
		this.voornaam = voornaam;
		this.familienaam = familienaam;
		this.email = email;
		this.personeelsNr = personeelsNr;
		this.functie = functie;
		this.adres = adres;
		this.telefoonnumer = telefoonnumer;
	}

	public String getAdres() {
		return adres;
	}

	public String getTelefoonnumer() {
		return telefoonnumer;
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
				+ ", personeelsNr=" + personeelsNr + ", functie=" + functie + ", adres=" + adres + ", telefoonnumer="
				+ telefoonnumer + "]";
	}


	
	

}