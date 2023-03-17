package repository;

import java.util.List;

import domein.Bedrijf;
import domein.Bestelling;
import domein.User;

public class KlantAankopersBestellingenDTO
{

	private final Long klantId;
	private final String klantNaam;

	public KlantAankopersBestellingenDTO(Bedrijf klant, List<User> aankopers, List<Bestelling> bestellingen)
	{
		this.klantId = klant.getID();
		this.klantNaam = klant.getNaam();
	}

	public Long getKlantId()
	{
		return klantId;
	}

	public String getKlantNaam()
	{
		return klantNaam;
	}


}
