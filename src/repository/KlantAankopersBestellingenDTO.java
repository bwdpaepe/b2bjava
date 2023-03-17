package repository;

import java.util.List;
import java.util.stream.Collectors;

import domein.Bedrijf;
import domein.Bestelling;
import domein.User;

public class KlantAankopersBestellingenDTO
{

	private final Long klantId;
	private final String klantNaam;
	private final List<AankoperDetailsDTO> aankopers;
	private final List<BestellingDetailsDTO> bestellingen;

	public KlantAankopersBestellingenDTO(Bedrijf klant, List<User> aankopers, List<Bestelling> bestellingen)
	{
		this.klantId = klant.getID();
		this.klantNaam = klant.getNaam();
		this.aankopers = aankopers.stream().map(el -> new AankoperDetailsDTO(el)).collect(Collectors.toUnmodifiableList());
		this.bestellingen = bestellingen.stream().map(el -> new BestellingDetailsDTO(el)).collect(Collectors.toUnmodifiableList());
	}

	public Long getKlantId()
	{
		return klantId;
	}

	public String getKlantNaam()
	{
		return klantNaam;
	}

	public List<AankoperDetailsDTO> getAankopers()
	{
		return aankopers;
	}

	public List<BestellingDetailsDTO> getBestellingen()
	{
		return bestellingen;
	}

	@Override
	public String toString()
	{
		return "KlantAankopersBestellingenDTO [klantId=" + klantId + ", klantNaam=" + klantNaam + ", aankopers="
				+ aankopers + ", bestellingen=" + bestellingen + "]";
	}

}
