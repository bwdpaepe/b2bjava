package repository;

import domein.Medewerker;

// Een Data Transfer Object klasse om een immutable object van Medewerker te kunnen 
// returnen naar de GUI
// ENKEL FINAL ATTRIBUTEN EN NOOIT LOGICA OPNEMEN
public class MedewerkerDTO extends UserDTO
{
	private final int personeelsNr;
	private final String functie;

	public MedewerkerDTO(Medewerker mw)
	{
		super(mw);
		this.personeelsNr = mw.getPersoneelsNr();
		this.functie = mw.getFunctie();
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
	public String toString() {
		return "MedewerkerDTO [personeelsNr=" + personeelsNr + ", functie=" + functie + ", voornaam=" + voornaam
				+ ", familienaam=" + familienaam + ", email=" + email + ", telefoonnummer=" + telefoonnummer
				+ ", adres=" + adres + "]";
	}
	
	


}