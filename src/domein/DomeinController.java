package domein;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import repository.BestellingDTO;
import repository.DatabaseSeeding;
import repository.GenericDaoJpa;
import repository.KlantLijstEntryDTO;
import repository.TransportdienstDTO;
import repository.UserDTO;
import service.BedrijfService;
import service.BestellingService;
import service.DienstService;
import service.UserService;

public class DomeinController {
	private UserDTO ingelogdeUser;
	private UserService userService; // service klasse om o.a aanmelden uit te werken
	private DienstService dienstService;
	private BedrijfService bedrijfService;
	private BestellingService bestellingService;

	public DomeinController() {

		setUserService(new UserService());
		setDienstService(new DienstService());
		setBedrijfService(new BedrijfService());
		setBestellingService(new BestellingService());
	}

	public DomeinController(Boolean doSeeding) {
		this();
		if (doSeeding) {
			DatabaseSeeding.startDatabaseSeed(this);
		}
	}

	// USER OPERATIONS
	// --------------------------------------------------------------------------------------------------------------------------------------

	private final void setUserService(UserService userService) {
		this.userService = userService;
	}

	public UserDTO getIngelogdeUser() {
		return this.ingelogdeUser;
	}

	private void setIngelogdeUser(UserDTO userDTO) {
		this.ingelogdeUser = userDTO;
	}

	public UserDTO aanmelden(String emailAdress, String password) {
		UserDTO user = userService.aanmelden(emailAdress, password);
		setIngelogdeUser(user);
		return user;
	}

	public void maakMedewerker(String voornaam, String familienaam, String emailadres, String password, String adres,
			String telefoonnumer, String functie, int personeelsNr, int bedrijfsId) {
		Bedrijf bedrijf = bedrijfService.getBedrijfById(bedrijfsId);
		userService.maakMedewerker(voornaam, familienaam, emailadres, password, adres, telefoonnumer, personeelsNr,
				functie, bedrijf);
	}

	public void updateMedewerker(int id, String rol) {
		userService.updateMedewerker(id, rol);
	}

	// TRANSPORTDIENST OPERATIONS
	// --------------------------------------------------------------------------------------------------------------------------

	private final void setDienstService(DienstService dienstService) {
		this.dienstService = dienstService;
	}

	public TransportdienstDTO getTransportdienst(long dienstId) {
		Transportdienst td = dienstService.getTransportdienstByID(dienstId);
		TransportdienstDTO tdDTO = new TransportdienstDTO(td);
		return tdDTO;

	}

	// dit geeft ruwe transportdiensten terug, MAG NIET
//	public List<Transportdienst> getTransportdiensten() {
//		return dienstService.getTransportdiensten();
//	}
	// Zelfde methode als hierboven maar geeft DTO objecten
	public List<TransportdienstDTO> getTransportdienstenDTO() {
		//TODO eens aanmelden gelinkt is moet dit weg
		UserDTO user = userService.aanmelden("emailail1@test.com", "paswoord");
		setIngelogdeUser(user);
		
		List<Transportdienst> tdList = dienstService.getTransportdiensten(ingelogdeUser.getBedrijf().getId());
		List<TransportdienstDTO> tdListDTO = new ArrayList<>();
		for (Transportdienst td : tdList) {
			TransportdienstDTO tdDTO = new TransportdienstDTO(td);
			tdListDTO.add(tdDTO);
		}
		return tdListDTO;
	}

	public void maakTransportdienst(String naam, int barcodeLengte, boolean isBarcodeEnkelCijfers, String barcodePrefix,
			String verificatiecode, String contactVoornaam, String contactFamilienaam, String contactTelefoon,
			String contactEmailadres, int bedrijfsId) {
		Bedrijf bedrijf = bedrijfService.getBedrijfById(bedrijfsId);
		dienstService.maakTransportdienst(naam, barcodeLengte, isBarcodeEnkelCijfers, barcodePrefix, verificatiecode,
				contactVoornaam, contactFamilienaam, contactTelefoon, contactEmailadres, bedrijf);
	}

	public void wijzigActivatieDienst(long dienstId, boolean isActief) {
		dienstService.wijzigActivatieDienst(dienstId, isActief);
	}

	public void addContactpersoon(String contactVoornaam, String contactFamilienaam, String contactTelefoon,
			String contactEmailadres, long transportdienstId) {
		dienstService.addContactpersoon(contactVoornaam, contactFamilienaam, contactTelefoon, contactEmailadres,
				transportdienstId);
	}

	public void editContactpersoon(String contactVoornaam, String contactFamilienaam, String contactTelefoon,
			String contactEmailadres, long contactpersoonId, long transportdienstId) {
		dienstService.editContactpersoon(contactVoornaam, contactFamilienaam, contactTelefoon, contactEmailadres,
				contactpersoonId, transportdienstId);
	}

	public void removeContactpersoon(long contactpersoonId, long transportdienstId) {
		dienstService.removeContactpersoon(contactpersoonId, transportdienstId);
	}

	public void updateTransportdienst(String naam, int barcodeLengte, boolean isBarcodeEnkelCijfers,
			String barcodePrefix, String verificatiecode, long transportdienstId) {
		dienstService.updateTransportdienst(naam, barcodeLengte, isBarcodeEnkelCijfers, barcodePrefix, verificatiecode,
				transportdienstId);
	}

	// BEDRIJF OPERATIONS
	// ------------------------------------------------------------------------------------------------------------------------------------------

	public void maakBedrijf(String naam, String straat, String huisnummer, String postcode, String stad, String land,
			String telefoonnummer, String logo_filename) {
		bedrijfService.maakBedrijf(naam, straat, huisnummer, postcode, stad, land, telefoonnummer, logo_filename);
	}

	private final void setBedrijfService(BedrijfService bedrijfService) {
		this.bedrijfService = bedrijfService;
	}

	public final List<KlantLijstEntryDTO> geefLijstVanKlantenMetAantalOpenstaandeBestellingen() {
		return bedrijfService.getListOfClientNamesWithNumberOfOpenOrders(ingelogdeUser.getBedrijf().getId());
	}

	// BESTELLING OPERATIONS
	// --------------------------------------------------------------------------------------------------------------------------------------------------

	public void setBestellingService(BestellingService bs) {
		this.bestellingService = bs;
	}

	public void maakBestelling(String OrderId, String status, Date datum, long leverancierID, long klantID,
			long transportdienstID, long aankoperId, String leveradresStraat, String leveradresNummer,String leveradresPostcode, String leveradresStad, 
			String leveradresLand) {
		bestellingService.maakBestelling(OrderId, status, datum, leverancierID, klantID, transportdienstID, aankoperId, leveradresStraat, leveradresNummer, leveradresPostcode, leveradresStad, leveradresLand);
	}

	// dit moet nog gefixed worden om enkel de bestellingen te krijgen van het
	// bedrijf van de aanvrager
	public List<BestellingDTO> getBestellingen() {
		List<Bestelling> bestellingen = bestellingService.getBestellingen(ingelogdeUser.getBedrijf().getId());
		List<BestellingDTO> bestellingenDTO = bestellingen.stream().map(b -> new BestellingDTO(b)).toList();

		return Collections.unmodifiableList(bestellingenDTO);

	}
	
	// DOOS OPERATIONS
	// --------------------------------------------------------------------------------------------------------------------------------------------------
	public void maakDoos(long bedrijfsId, String naam, String doosTypeString, double hoogte, double breedte, double lengte, double prijs ) {
		bedrijfService.maakDoos(bedrijfsId, naam, doosTypeString, hoogte, breedte, lengte, prijs);
	}
	
	// PRODUCT OPERATIONS
	// --------------------------------------------------------------------------------------------------------------------------------------------------
	public void maakProduct(long leveranciersId, String naam, double eenheidsprijs) {
		bedrijfService.maakProduct(leveranciersId, naam, eenheidsprijs);
	}

	// GLOBAL OPERATIONS
	// --------------------------------------------------------------------------------------------------------------------------------------------------

	public void close() {
		GenericDaoJpa.closePersistency();
	}

}
