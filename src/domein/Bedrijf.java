package domein;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import service.ValidationService;

@Entity
@NamedQueries(
		{
			@NamedQuery(
				    name = "Bedrijf.findKlantenWithOpenOrdersByLeverancier",
				    query = "SELECT o.klant, COUNT(o) " +
				            "FROM Bestelling o " +
				            "WHERE o.leverancier.id = :bedrijfId AND o.status = :status " +
				            "GROUP BY o.klant"
				)
})
public class Bedrijf implements Serializable
{

	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy = "bedrijf")
	private Set<User> users = new HashSet<>();

	@OneToMany(mappedBy = "bedrijf")
	private Set<Dienst> diensten = new HashSet<>();

	@OneToMany(mappedBy = "klant")
	private List<Bestelling> outgoingOrders;

	@OneToMany(mappedBy = "leverancier")
	private List<Bestelling> incomingOrders;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(unique = true)
	private String naam;
	@Column
	private String straat;
	@Column
	private String huisnummer;
	@Column
	private String postcode;
	@Column
	private String stad;
	@Column
	private String land;
	@Column
	private String telefoonnummer;
	@Column
	private String logo_filename;

	public Bedrijf(String naam, String straat, String huisnummer, String postcode, String stad, String land,
			String telefoonnummer, String logo_filename)
	{
		setNaam(naam);
		setStraat(straat);
		setHuisnummer(huisnummer);
		setPostcode(postcode);
		setStad(stad);
		setLand(land);
		setTelefoonnummer(telefoonnummer);
		setLogo_filename(logo_filename);
	}

	// lege Constructor voor JPA
	public Bedrijf()
	{

	}

	@Override
	public int hashCode()
	{
		return Objects.hash(naam);
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bedrijf other = (Bedrijf) obj;
		return Objects.equals(naam, other.naam);
	}

	public String getNaam()
	{
		return naam;
	}

	public final void setNaam(String naam)
	{
		ValidationService.controleerNietBlanco(naam);
		this.naam = naam;
	}

	public String getStraat()
	{
		return straat;
	}

	public final void setStraat(String straat)
	{
		ValidationService.controleerNietBlanco(straat);
		this.straat = straat;
	}

	public String getHuisnummer()
	{
		return huisnummer;
	}

	public final void setHuisnummer(String huisnummer)
	{
		ValidationService.controleerNietBlanco(huisnummer);
		this.huisnummer = huisnummer;
	}

	public String getPostcode()
	{
		return postcode;
	}

	public final void setPostcode(String postcode)
	{
		ValidationService.controleerNietBlanco(postcode);
		this.postcode = postcode;
	}

	public String getStad()
	{
		return stad;
	}

	public final void setStad(String stad)
	{
		ValidationService.controleerNietBlanco(stad);
		this.stad = stad;
	}

	public String getLand()
	{
		return land;
	}

	public final void setLand(String land)
	{
		ValidationService.controleerNietBlanco(land);
		this.land = land;
	}

	public String getTelefoonnummer()
	{
		return telefoonnummer;
	}

	public final void setTelefoonnummer(String telefoonnummer)
	{
		ValidationService.controleerTelefoonnummer(telefoonnummer);
		this.telefoonnummer = telefoonnummer;
	}

	public Set<User> getUsers()
	{
		return this.users;
	}

	public Set<Dienst> getTransportdiensten()
	{
		return this.diensten;
	}

	public long getID()
	{
		return this.id;
	}

	public String getLogo_filename()
	{
		return logo_filename;
	}

	public final void setLogo_filename(String logo_filename)
	{
		// ValidationService.controleerNietBlanco(logo_filename);
		this.logo_filename = logo_filename;
	}

}
