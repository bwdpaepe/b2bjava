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
	protected final String leveradresStraat;
	protected final String leveradresNummer;
	protected final String leveradresPostcode;
	protected final String leveradresStad;
	protected final String leveradresLand;
	protected final MedewerkerDTO aankoper;
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
		this.leveradresStraat = bestelling.getLeveradresStraat();
		this.leveradresNummer = bestelling.getLeveradresNummer();
		this.leveradresPostcode = bestelling.getLeveradresPostcode();
		this.leveradresStad = bestelling.getLeveradresStad();
		this.leveradresLand = bestelling.getLeveradresLand();
		this.aankoper = new MedewerkerDTO(bestelling.getAankoper());
		
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
	
	public String getLeveradresStraat() {
		return leveradresStraat;
	}
	
	public String getLeveradresNummer() {
		return leveradresNummer;
	}
	
	public String getLeveradresPostcode() {
		return leveradresPostcode;
	}
	
	public String getLeveradresStad() {
		return leveradresStad;
	}
	
	public String getLeveradresLand() {
		return leveradresLand;
	}
	
	public MedewerkerDTO getAankoper() {
		return aankoper;
	}
	@Override
	public String toString() {
		return "BestellingDTO [id=" + id + ", orderID=" + orderID + ", datumGeplaatst=" + datumGeplaatst + ", status="
				+ status + ", leverancierID=" + leverancierID + ", klantID=" + klantID + ", transportdienstID="
				+ transportdienstID + "]";
	}

	
	
	


}
