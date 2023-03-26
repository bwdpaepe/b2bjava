package repository;

import domein.Medewerker;

public class MedewerkerListEntryDTO
{
	private final Long id;
	private final String voornaam;
	private final String familienaam;
	private final String functie;
	private final boolean isActief;

	public MedewerkerListEntryDTO(Medewerker mw)
	{
		this.id = mw.getId();
		this.voornaam = mw.getVoornaam();
		this.familienaam = mw.getFamilienaam();
		this.functie = mw.getFunctie();
		this.isActief = mw.getIsActief();
	}

	public Long getId()
	{
		return id;
	}

	public String getVoornaam()
	{
		return voornaam;
	}

	public String getFamilienaam()
	{
		return familienaam;
	}

	public String getFunctie()
	{
		return functie;
	}

	public boolean isActief()
	{
		return isActief;
	}

	@Override
	public String toString()
	{
		return "MedewerkerListEntryDTO [id=" + id + ", voornaam=" + voornaam + ", familienaam=" + familienaam
				+ ", functie=" + functie + ", isActief=" + isActief + "]";
	}
}