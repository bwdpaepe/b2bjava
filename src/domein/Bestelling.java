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
	@Column(name = "Bedrijf", nullable = false)
	private Bedrijf bedrijf;
	
	@OneToOne
	@Column(name = "Transportdienst", nullable = false)
	private Transportdienst transportdienst;
	
	public Bestelling(long orderID, Date datum, String status, Bedrijf bedrijf, Transportdienst transportdienst) {
		setOrderID(orderID);
		setDatumGeplaatst();
		setBedrijf(bedrijf);
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

	public Bedrijf getBedrijf() {
		return bedrijf;
	}

	public void setBedrijf(Bedrijf bedrijf) {
		ValidationService.controleerNietBlanco(bedrijf);
		this.bedrijf = bedrijf;
	}

	public Transportdienst getTransportdienst() {
		return transportdienst;
	}

	public void setTransportdienst(Transportdienst transportdienst) {
		ValidationService.controleerNietBlanco(transportdienst);
		this.transportdienst = transportdienst;
	}



}
