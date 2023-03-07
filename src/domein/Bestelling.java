package domein;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

import service.ValidationService;

@Entity
@NamedQueries(
		{ @NamedQuery(name = "Bestelling.getBestellingenByLeverancierID", query = "select b from Bestelling b where b.leverancier = :leverancier") })
public class Bestelling {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String orderID;
	private Date datumGeplaatst;
	private BestellingStatus status;

	// RELATIES

	@ManyToOne
	@JoinColumn(name = "Leverancier", nullable = false)
	private Bedrijf leverancier;
	
	@ManyToOne
	@JoinColumn(name = "Klant", nullable = false)
	private Bedrijf klant;
	
	@ManyToOne
	@JoinColumn(name = "Transportdienst", nullable = true)
	private Transportdienst transportdienst;
	
	protected Bestelling() {
		
	};
	
	public Bestelling(String orderID, Date datum_geplaatst,  String statusString, Bedrijf leverancier, Bedrijf klant, Transportdienst transportdienst) {
		setOrderID(orderID);
		setDatumGeplaatst(datum_geplaatst);
		setLeverancier(leverancier);
		setKlant(klant);
		setTransportdienst(transportdienst);
		setStatus(statusString);
	}

	public long getId() {
		return id;
	}

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		ValidationService.controleerNietBlanco(orderID);
		this.orderID = orderID;
	}

	public Date getDatumGeplaatst() {
		return datumGeplaatst;
	}

	public void setDatumGeplaatst(Date datum) {
		ValidationService.controleerNietBlanco(datum);

		this.datumGeplaatst = datum;
	}

	public String getStatus() {
		return status.toString().toLowerCase();
	}

	public void setStatus(String statusString) {
		ValidationService.controleerNietBlanco(statusString);
		this.status = switch (statusString.toLowerCase())
			{
			case "geplaatst" -> BestellingStatus.GEPLAATST;
			case "verwerkt" -> BestellingStatus.VERWERKT;
			default -> throw new IllegalArgumentException("Ongeldige status van Bestelling: " + statusString);
			};
	}


	public Bedrijf getLeverancier() {
		return leverancier;
	}

	public void setLeverancier(Bedrijf leverancier) {
		ValidationService.controleerNietBlanco(leverancier);
		this.leverancier = leverancier;
	}
	
	public Bedrijf getKlant() {
		return klant;
	}

		
	public void setKlant(Bedrijf klant) {
		ValidationService.controleerNietBlanco(klant);
		this.klant = klant;
	}

	public Transportdienst getTransportdienst() {
		return transportdienst;
	}

	public void setTransportdienst(Transportdienst transportdienst) {
		ValidationService.controleerNietBlanco(transportdienst);
		this.transportdienst = transportdienst;
	}



}
