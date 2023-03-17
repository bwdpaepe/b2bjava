package repository;

import java.util.List;

import domein.AankoperDetails;
import domein.Bedrijf;
import domein.BestellingDetails;

public class KlantAankopersBestellingenDTO
{

	private final Long klantId;
	private final String klantNaam;

	public KlantAankopersBestellingenDTO(Bedrijf klant, List<AankoperDetails> aankopersVanKlant, List<BestellingDetails> bestellingVanKlant)
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
