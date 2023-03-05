package domein;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import service.ValidationService;

@Entity
public class Bestelling {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private long orderID;
	private Date datumGeplaatst;
	private String status;

	// RELATIES

	@ManyToOne
	@Column(name = "Leverancier", nullable = false)
	private Bedrijf leverancier;
	
	@ManyToOne
	@Column(name = "Klant", nullable = false)
	private Bedrijf klant;
	
	@OneToOne
	@Column(name = "Transportdienst", nullable = false)
	private Transportdienst transportdienst;
	
	public Bestelling(long orderID, Date datum, String status, Bedrijf leverancier, Bedrijf klant, Transportdienst transportdienst) {
		setOrderID(orderID);
		setDatumGeplaatst();
		setLeverancier(leverancier);
		setKlant(klant);
		setTransportdienst(transportdienst);
		setStatus(status);
	}

	public long getId() {
		return id;
	}

	public long getOrderID() {
		return orderID;
	}

	public void setOrderID(long orderID) {
		ValidationService.controleerGroterDanNul(orderID);
		this.orderID = orderID;
	}

	public Date getDatumGeplaatst() {
		return datumGeplaatst;
	}

	public void setDatumGeplaatst() {
		
		//set op moment van aanmaken
		this.datumGeplaatst = new Date();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		ValidationService.controleerNietBlanco(status);
		this.status = status;
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
