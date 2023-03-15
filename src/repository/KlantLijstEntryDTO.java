package repository;

import domein.Bedrijf;

public class KlantLijstEntryDTO
{

	private final Long klantId;
	private final String klantNaam;
	private final int aantalOpenBestellingen;
	
	public KlantLijstEntryDTO(Object[] obj)
	{
		this.klantId = ((Bedrijf) obj[0]).getID();
		this.klantNaam = ((Bedrijf) obj[0]).getNaam();
		this.aantalOpenBestellingen =  ((Number) obj[1]).intValue();
	}

	public Long getKlantId()
	{
		return klantId;
	}

	public String getKlantNaam()
	{
		return klantNaam;
	}

	public Number getAantalOpenBestellingen()
	{
		return aantalOpenBestellingen;
	}

	@Override
	public String toString()
	{
		return "KlantLijstEntryDTO [klantId=" + klantId + ", klantNaam=" + klantNaam + ", aantalOpenBestellingen="
				+ aantalOpenBestellingen + "]";
	}	
}
