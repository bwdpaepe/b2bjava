package domein;

import java.util.List;

import repository.DienstDTO;
import repository.GenericDaoJpa;
import repository.UserDTO;
import service.DienstService;
import service.PersoonService;
import service.TrackTraceFormatService;
import service.UserService;

public class DomeinController
{
	private UserDTO ingelogdeUser;
	private UserService userService; // service klasse om o.a aanmelden uit te werken
	private DienstService dienstService;
	private TrackTraceFormatService ttfService;
	private PersoonService persoonService;

	public DomeinController()
	{

		setUserService(new UserService());
		setDienstService(new DienstService());
		setTtfService(new TrackTraceFormatService());
		setPersoonService(new PersoonService());
	}

	public UserDTO getIngelogdeUser()
	{
		return this.ingelogdeUser;
	}
	
	// todo hoe kent de gui het dienstId
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
	
	private final void setTtfService(TrackTraceFormatService ttfService) {
		this.ttfService = ttfService;
	}
	
	private final void setPersoonService(PersoonService persoonService) {
		this.persoonService = persoonService;
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
		userService.maakMedewerker(voornaam, familienaam, emailadres, password, adres, telefoonnumer, functie, personeelsNr, bedrijfsId);

	}
	
	public void updateMedewerker(String e, String r) {
		userService.updateMedewerker(e, r);
	}
	
	public void maakTransportdienst (String naam, int barcodeLengte, boolean isBarcodeEnkelCijfers, String barcodePrefix, String verificatiecode, List<String> contactVoornaamLijst, List<String> contactFamilienaamLijst, List<String> contactTelefoonLijst, List<String> contactEmailadresLijst) {
		dienstService.maakTransportdienst(naam, barcodeLengte, isBarcodeEnkelCijfers, barcodePrefix, verificatiecode, contactVoornaamLijst, contactFamilienaamLijst, contactTelefoonLijst, contactEmailadresLijst, this.ttfService, this.persoonService);
	}
	
	// ? What to do if the email of a contactpersoon is updated, currently we use the email to select the person to be updated
	public void updateTransportdienst(long dienstId, String naam, boolean isActief, int barcodeLengte, boolean isBarcodeEnkelCijfers, String barcodePrefix, String verificatiecode, List<String> contactVoornaamLijst, List<String> contactFamilienaamLijst, List<String> contactTelefoonLijst, List<String> contactEmailadresLijst) {
		dienstService.updateTransportdienst(dienstId, naam, isActief, barcodeLengte, isBarcodeEnkelCijfers, barcodePrefix, verificatiecode, contactVoornaamLijst, contactFamilienaamLijst, contactTelefoonLijst, contactEmailadresLijst, this.ttfService, this.persoonService);		
	}

	public void close()
	{
		GenericDaoJpa.closePersistency();
	}

}
