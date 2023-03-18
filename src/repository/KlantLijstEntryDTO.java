package repository;

import domein.KlantEnAantalBestellingen;

public class KlantLijstEntryDTO
{

	private final Long klantId;
	private final String klantNaam;
	private final int aantalOpenBestellingen;
	
	public KlantLijstEntryDTO(KlantEnAantalBestellingen obj)
	{
		this.klantId = obj.getBedrijf().getID();
		this.klantNaam = obj.getBedrijf().getNaam();
		this.aantalOpenBestellingen =  obj.getAantalOpenBestellingen().intValue();
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
