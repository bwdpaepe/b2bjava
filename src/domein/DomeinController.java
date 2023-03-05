package domein;

import java.util.List;

import repository.DienstDTO;
import repository.GenericDaoJpa;
import repository.UserDTO;
import service.BedrijfService;
import service.DienstService;
import service.UserService;

public class DomeinController
{
	private UserDTO ingelogdeUser;
	private UserService userService; // service klasse om o.a aanmelden uit te werken
	private DienstService dienstService;
	private BedrijfService bedrijfService;

	public DomeinController()
	{

		setUserService(new UserService());
		setDienstService(new DienstService());
		setBedrijfService(new BedrijfService());
	}

	public UserDTO getIngelogdeUser()
	{
		return this.ingelogdeUser;
	}
	
	public DienstDTO getDienst(long dienstId) {
		return(dienstService.getDienst(dienstId));
		
	}
	
	public List<DienstDTO> getDiensten() {
		return(dienstService.getDiensten());
	}

	private void setIngelogdeUser(UserDTO userDTO)
	{
		this.ingelogdeUser = userDTO;
	}

	private final void setUserService(UserService userService)
	{
		this.userService = userService;
	}

	private final void setDienstService(DienstService dienstService) {
		this.dienstService = dienstService;
	}
	
	private final void setBedrijfService(BedrijfService bedrijfService) {
		this.bedrijfService = bedrijfService;
	}

	// return een Data Transfer Object van User naar de GUI
	public UserDTO aanmelden(String emailAdress, String password)
	{
		UserDTO user = userService.aanmelden(emailAdress, password);
		setIngelogdeUser(user);
		return user;
	}
	

	public void maakMedewerker(String voornaam, String familienaam, String emailadres, String password, String adres, String telefoonnumer, 
			String functie, int personeelsNr, int bedrijfsId)
	{
		Bedrijf bedrijf = bedrijfService.getBedrijfById(bedrijfsId);
		userService.maakMedewerker(voornaam, familienaam, emailadres, password, adres, telefoonnumer,  personeelsNr, functie, bedrijf);
	}
	
	public void updateMedewerker(String e, String r) {
		userService.updateMedewerker(e, r);
	}
	
	public void maakTransportdienst (String naam, int barcodeLengte, boolean isBarcodeEnkelCijfers, String barcodePrefix, String verificatiecode, List<String> contactVoornaamLijst, List<String> contactFamilienaamLijst, List<String> contactTelefoonLijst, List<String> contactEmailadresLijst) {
		dienstService.maakTransportdienst(naam, barcodeLengte, isBarcodeEnkelCijfers, barcodePrefix, verificatiecode, contactVoornaamLijst, contactFamilienaamLijst, contactTelefoonLijst, contactEmailadresLijst);
	}
	
	public void wijzigActivatieDienst(long dienstId, boolean isActief) {
		dienstService.wijzigActivatieDienst(dienstId, isActief);
	}
	
	public void maakBedrijf(String naam, String straat, String huisnummer, String postcode, String stad, String land,
			String telefoonnummer, String logo_filename) {
		bedrijfService.maakBedrijf(naam, straat, huisnummer, postcode, stad, land, telefoonnummer, logo_filename);
	}

	public void close()
	{
		GenericDaoJpa.closePersistency();
	}

}
