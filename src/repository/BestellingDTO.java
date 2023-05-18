package repository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import domein.Bestelling;

public class BestellingDTO {

	protected final long id;
	protected final String orderID;
	protected final Date datumGeplaatst;
	protected final String status;
	protected final long leverancierID;
	protected final long klantID;
	protected final Long transportdienstID;
	protected final String transportdienstNaam;
	protected final String klantNaam;
	protected final String leveradresStraat;
	protected final String leveradresNummer;
	protected final String leveradresPostcode;
	protected final String leveradresStad;
	protected final String leveradresLand;
	protected final String trackAndTraceCode;
	protected final MedewerkerDTO aankoper;
	protected final DoosDTO doos;
	protected final List<BesteldProductDTO> besteldeProducten;
	protected final double totaalbedrag;

	public BestellingDTO(Bestelling bestelling) {

		this.id = bestelling.getId();
		this.orderID = bestelling.getOrderID();
		this.datumGeplaatst = bestelling.getDatumGeplaatst();
		this.status = bestelling.getStatus();
		this.leverancierID = bestelling.getLeverancier().getID();
		this.klantNaam = bestelling.getKlant().getNaam();
		if (bestelling.getTransportdienst() != null) {
		    this.transportdienstNaam = bestelling.getTransportdienst().getNaam();
		    this.transportdienstID = bestelling.getTransportdienst().getId();
		} else {
		    this.transportdienstNaam = null;
		    this.transportdienstID = null;
		}
		this.klantID = bestelling.getKlant().getID();
		this.leveradresStraat = bestelling.getLeveradresStraat();
		this.leveradresNummer = bestelling.getLeveradresNummer();
		this.leveradresPostcode = bestelling.getLeveradresPostcode();
		this.leveradresStad = bestelling.getLeveradresStad();
		this.leveradresLand = bestelling.getLeveradresLand();
		this.trackAndTraceCode = bestelling.getTrackAndTraceCode();
		this.aankoper = new MedewerkerDTO(bestelling.getAankoper());
		this.doos = new DoosDTO(bestelling.getDoos());
		this.besteldeProducten = bestelling.getBesteldeProducten().stream().map(e -> new BesteldProductDTO(e)).collect(Collectors.toUnmodifiableList());
		this.totaalbedrag = bestelling.getBesteldeProducten().stream().mapToDouble(bp -> bp.getAantal() * bp.getEenheidsprijs()).sum() + bestelling.getDoos().getPrijs();
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
	public Long getTransportdienstID() {
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
	
	public String getTrackAndTraceCode() {
		return trackAndTraceCode;
	}
	
	public MedewerkerDTO getAankoper() {
		return aankoper;
	}
	
	public DoosDTO getDoos() {
		return doos;
	}
	public List<BesteldProductDTO> getBesteldeProducten() {
		return besteldeProducten;
	}
	public double getTotaalbedrag() {
		return totaalbedrag;
	}
	
	@Override
	public String toString()
	{
		return "BestellingDTO [id=" + id + ", orderID=" + orderID + ", datumGeplaatst=" + datumGeplaatst + ", status="
				+ status + ", leverancierID=" + leverancierID + ", klantID=" + klantID + ", transportdienstID="
				+ transportdienstID + ", transportdienstNaam=" + transportdienstNaam + ", klantNaam=" + klantNaam
				+ ", leveradresStraat=" + leveradresStraat + ", leveradresNummer=" + leveradresNummer
				+ ", leveradresPostcode=" + leveradresPostcode + ", leveradresStad=" + leveradresStad
				+ ", leveradresLand=" + leveradresLand + ", aankoper=" + aankoper + ", doos=" + doos + "]";
	}
	

	
	
	


}
