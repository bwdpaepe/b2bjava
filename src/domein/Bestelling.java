package domein;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import service.ValidationService;
import util.BestellingState;
import util.GeplaatstState;

@Entity
@NamedQueries(
		{ 
			@NamedQuery(name = "Bestelling.getBestellingenByLeverancierID", query = "select b from Bestelling b where b.leverancier = :leverancier") ,
			@NamedQuery(name = "Bestelling.getBestellingenVanKlantBijLeverancier", query = "select b from Bestelling b where b.leverancier = :leverancier and b.klant = :klant") }
		)
public class Bestelling {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String orderID;
	private Date datumGeplaatst;
	private BestellingStatus status;
	private String klantNaam;
	
	private String leveradresStraat;
	private String leveradresNummer;
	private String leveradresPostcode;
	private String leveradresLand;
	private String leveradresStad;
	private String trackAndTraceCode;

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
	
	@ManyToOne
	@JoinColumn(name = "Medewerker", nullable = false)
	private Medewerker aankoper;
	
	@OneToMany(mappedBy = "bestelling", cascade = CascadeType.ALL)
	private List<BesteldProduct> besteldeProducten;
	
	@ManyToOne
	@JoinColumn(name = "Doos", nullable = false)
	private Doos doos;
	
	// STATE PATTERN
	private BestellingState currentState;
	
	protected Bestelling() {
		
	};
	
	public Bestelling(String orderID, Date datum_geplaatst,  String statusString, 
			Bedrijf leverancier, Bedrijf klant, Transportdienst transportdienst, Medewerker aankoper,
			String leveradresStraat, String leveradresNummer,String leveradresPostcode, String leveradresStad, 
			String leveradresLand, Doos doos) {
		setOrderID(orderID);
		setDatumGeplaatst(datum_geplaatst);
		setLeverancier(leverancier);
		setKlant(klant);
		setKlantNaam(klant.getNaam());
		setTransportdienst(transportdienst);
		setStatus(statusString);
		setAankoper(aankoper);
		setLeveradresLand(leveradresLand);
		setLeveradresStraat(leveradresStraat);
		setLeveradresNummer(leveradresNummer);
		setLeveradresPostcode(leveradresPostcode);
		setLeveradresStad(leveradresStad);
		setDoos(doos);
		
		toState(new GeplaatstState(this));
	}
	
	
	public Doos getDoos()
	{
		return doos;
	}

	private void setDoos(Doos doos)
	{
		//ValidationService.controleerNietBlanco(doos);  // MAG WEL BLANCO ZIJN
		this.doos = doos;
	}

	public List<BesteldProduct> getBesteldeProducten()
	{
		return besteldeProducten;
	}

	public final void addBesteldProductToBestelling(BesteldProduct bp) {
		besteldeProducten.add(bp);
	}

	public String getLeveradresStad() {
		return leveradresStad;
	}

	public final void setLeveradresStad(String leveradresStad) {
		ValidationService.controleerNietBlanco(leveradresStad);
		this.leveradresStad = leveradresStad;
	}

	public Medewerker getAankoper() {
		return aankoper;
	}

	public final void setAankoper(Medewerker aankoper) {
		if(aankoper.getFunctie().toString().equalsIgnoreCase("aankoper")) {
		this.aankoper = aankoper; 
		} else {
		throw new IllegalArgumentException("Medewerker is geen aankoper");
		}
	}

	public long getId() {
		return id;
	}

	public String getOrderID() {
		return orderID;
	}

	public final void setOrderID(String orderID) {
		ValidationService.controleerNietBlanco(orderID);
		this.orderID = orderID;
	}

	public Date getDatumGeplaatst() {
		return datumGeplaatst;
	}

	public final void setDatumGeplaatst(Date datum) {
		ValidationService.controleerNietBlanco(datum);

		this.datumGeplaatst = datum;
	}

	public String getStatus() {
		return status.toString().toLowerCase();
	}

	public final void setStatus(String statusString) {
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

	public final void setLeverancier(Bedrijf leverancier) {
		ValidationService.controleerNietBlanco(leverancier);
		this.leverancier = leverancier;
	}
	
	public Bedrijf getKlant() {
		return klant;
	}

		
	public final void setKlant(Bedrijf klant) {
		ValidationService.controleerNietBlanco(klant);
		this.klant = klant;
	}

	public Transportdienst getTransportdienst() {
		return transportdienst;
	}

	public final void setTransportdienst(Transportdienst transportdienst) {
		//ValidationService.controleerNietBlanco(transportdienst);    // MAG WEL BLANCO ZIJN
		this.transportdienst = transportdienst;
	}

	public String getKlantNaam() {
		return klantNaam;
	}

	public void setKlantNaam(String klantNaam) {
		ValidationService.controleerNietBlanco(klantNaam);
		this.klantNaam = klantNaam;
	}


	public String getLeveradresStraat() {
		return leveradresStraat;
	}

	public void setLeveradresStraat(String leveradresStraat) {
		ValidationService.controleerNietBlanco(leveradresStraat);
		this.leveradresStraat = leveradresStraat;
	}

	public String getLeveradresNummer() {
		return leveradresNummer;
	}

	public void setLeveradresNummer(String leveradresNummer) {
		ValidationService.controleerNietBlanco(leveradresNummer);
		this.leveradresNummer = leveradresNummer;
	}

	public String getLeveradresPostcode() {
		return leveradresPostcode;
	}

	public void setLeveradresPostcode(String leveradresPostcode) {
		ValidationService.controleerNietBlanco(leveradresPostcode);
		this.leveradresPostcode = leveradresPostcode;
	}

	public String getLeveradresLand() {
		return leveradresLand;
	}

	public void setLeveradresLand(String leveradresLand) {
		ValidationService.controleerNietBlanco(leveradresLand);
		this.leveradresLand = leveradresLand;
	}

	public String getTrackAndTraceCode() {
		return trackAndTraceCode;
	}

	public void setTrackAndTraceCode(String trackAndTraceCode) {
		this.trackAndTraceCode = trackAndTraceCode;
	}
	
	protected void toState(BestellingState state) {
		currentState = state;
	}
	


}
