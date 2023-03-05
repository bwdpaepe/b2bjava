package repository;

import java.util.Date;

import domein.Bestelling;

public class BestellingDTO {

	protected final long id;
	protected final String orderID;
	protected final Date datumGeplaatst;
	protected final String status;
	protected final BedrijfDTO leverancier;
	protected final BedrijfDTO klant;
	protected final TransportdienstDTO transportdienst;
	public BestellingDTO(Bestelling bestelling) {

		this.id = bestelling.getId();
		this.orderID = bestelling.getOrderID();
		this.datumGeplaatst = bestelling.getDatumGeplaatst();
		this.status = bestelling.getStatus();
		this.leverancier = new BedrijfDTO(bestelling.getLeverancier());
		this.klant = new BedrijfDTO(bestelling.getKlant());
		this.transportdienst = new TransportdienstDTO(bestelling.getTransportdienst());
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
	public BedrijfDTO getLeverancier() {
		return leverancier;
	}
	public BedrijfDTO getKlant() {
		return klant;
	}
	public TransportdienstDTO getTransportdienst() {
		return transportdienst;
	}
	@Override
	public String toString() {
		return "BestellingDTO [id=" + id + ", orderID=" + orderID + ", datumGeplaatst=" + datumGeplaatst + ", status="
				+ status + ", leverancier=" + leverancier + ", klant=" + klant + ", transportdienst=" + transportdienst
				+ "]";
	}
	
	
	


}
