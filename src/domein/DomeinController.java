package domein;

import repository.GenericDaoJpa;
import repository.UserDTO;
import service.DienstService;
import service.TrackTraceFormatService;
import service.UserService;

public class DomeinController
{
	private UserDTO ingelogdeUser;
	private UserService userService; // service klasse om o.a aanmelden uit te werken
	private DienstService dienstService;
	private TrackTraceFormatService ttfService;

	public DomeinController()
	{

		setUserService(new UserService());
		setDienstService(new DienstService());
		setTtfService(new TrackTraceFormatService());
	}

	private final void setUserService(UserService userService)
	{
		this.userService = userService;
	}

	public UserDTO getIngelogdeUser()
	{
		return this.ingelogdeUser;
	}

	private void setIngelogdeUser(UserDTO userDTO)
	{
		this.ingelogdeUser = userDTO;
	}

	private final void setDienstService(DienstService dienstService) {
		this.dienstService = dienstService;
	}
	
	private final void setTtfService(TrackTraceFormatService ttfService) {
		this.ttfService = ttfService;
	}


	// return een Data Transfer Object van User naar de GUI
	public UserDTO aanmelden(String emailAdress, String password)
	{
		UserDTO user = userService.aanmelden(emailAdress, password);
		setIngelogdeUser(user);
		return user;
	}
	

	public void maakMedewerker(String voornaam, String familienaam, String emailadres, String password, String adres, String telefoonnumer, 
			String functie, int personeelsNr)
	{
		userService.maakMedewerker(voornaam, familienaam, emailadres, password, adres, telefoonnumer, functie, personeelsNr);

	}
	
	public void updateMedewerker(String e, String r) {
		userService.updateMedewerker(e, r);
	}
	
	public void maakTransportdienst (String naam, int barcodeLengte, boolean isBarcodeEnkelCijfers, String barcodePrefix, String verificatiecode, String contactVoornaam, String contactFamilienaam, String contactTelefoon, String contactEmailadres) {
		dienstService.maakTransportdienst(naam, barcodeLengte, isBarcodeEnkelCijfers, barcodePrefix, verificatiecode, contactVoornaam, contactFamilienaam, contactTelefoon, contactEmailadres);
	}

	public void close()
	{
		GenericDaoJpa.closePersistency();
	}

}
