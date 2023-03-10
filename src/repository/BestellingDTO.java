package repository;

import java.util.Date;

import domein.Bestelling;

public class BestellingDTO {

	protected final long id;
	protected final String orderID;
	protected final Date datumGeplaatst;
	protected final String status;
	protected final long leverancierID;
	protected final long klantID;
	protected final long transportdienstID;
	protected final String transportdienstNaam;
	protected final String klantNaam;
	public BestellingDTO(Bestelling bestelling) {

		this.id = bestelling.getId();
		this.orderID = bestelling.getOrderID();
		this.datumGeplaatst = bestelling.getDatumGeplaatst();
		this.status = bestelling.getStatus();
		this.leverancierID = bestelling.getLeverancier().getID();
		this.klantNaam = bestelling.getKlant().getNaam();
		this.transportdienstNaam = bestelling.getTransportdienst().getNaam();
		this.transportdienstID = bestelling.getTransportdienst().getId();
		this.klantID = bestelling.getKlant().getID();
	}
	public long getId() {
		return id;
	}
	public String getOrderID() {
		return orderID;
	}
	public Date getDatumGeplaatst() {
		return datumGeplaatst;
	}
	public String getStatus() {
		return status;
	}
	
	public long getLeverancierID() {
		return leverancierID;
	}
	public long getKlantID() {
		return klantID;
	}
	public long getTransportdienstID() {
		return transportdienstID;
	}
	public String getTransportdienstNaam() {
		return transportdienstNaam;
	}
	public String getKlantNaam() {
		return klantNaam;
	}
	@Override
	public String toString() {
		return "BestellingDTO [id=" + id + ", orderID=" + orderID + ", datumGeplaatst=" + datumGeplaatst + ", status="
				+ status + ", leverancierID=" + leverancierID + ", klantID=" + klantID + ", transportdienstID="
				+ transportdienstID + "]";
	}

	
	
	


}
