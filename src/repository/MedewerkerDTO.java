package repository;

import domein.Medewerker;

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
	public String toString()
	{
		return "MedewerkerDTO [personeelsNr=" + personeelsNr + ", functie=" + functie + ", id=" + id + ", voornaam="
				+ voornaam + ", familienaam=" + familienaam + ", email=" + email + ", telefoonnummer=" + telefoonnummer
				+ ", adres=" + adres + ", bedrijf=" + bedrijf + ", isActief=" + isActief + "]";
	}
}