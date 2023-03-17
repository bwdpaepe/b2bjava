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
	private final String straat;
	private final String huisnummer;
	private final String postcode;
	private final String stad;
	private final String land;
	private final String telefoonnummer;
	private final String logo_filename;
	private final List<AankoperDetailsDTO> aankopers;
	private final List<BestellingDetailsDTO> bestellingen;

	public KlantAankopersBestellingenDTO(Bedrijf klant, List<User> aankopers, List<Bestelling> bestellingen)
	{
		this.klantId = klant.getID();
		this.klantNaam = klant.getNaam();
		this.straat = klant.getStraat();
		this.huisnummer = klant.getHuisnummer();
		this.postcode = klant.getPostcode();
		this.stad = klant.getStad();
		this.land = klant.getLand();
		this.telefoonnummer = klant.getTelefoonnummer();
		this.logo_filename = klant.getLogo_filename();
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
	
	public String getStraat()
	{
		return straat;
	}

	public String getHuisnummer()
	{
		return huisnummer;
	}

	public String getPostcode()
	{
		return postcode;
	}

	public String getStad()
	{
		return stad;
	}

	public String getLand()
	{
		return land;
	}

	public String getTelefoonnummer()
	{
		return telefoonnummer;
	}

	public String getLogo_filename()
	{
		return logo_filename;
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
		return "KlantAankopersBestellingenDTO [klantId=" + klantId + ", klantNaam=" + klantNaam + ", straat=" + straat
				+ ", huisnummer=" + huisnummer + ", postcode=" + postcode + ", stad=" + stad + ", land=" + land
				+ ", telefoonnummer=" + telefoonnummer + ", logo_filename=" + logo_filename + ", aankopers=" + aankopers
				+ ", bestellingen=" + bestellingen + "]";
	}

}
