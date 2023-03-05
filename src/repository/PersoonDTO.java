package repository;

public abstract class PersoonDTO {

	protected final long id;
	protected final String voornaam;
	protected final String familienaam;
	protected final String emailAdres;
	protected final String telefoonnummer;
	
	

	public PersoonDTO(long id, String voornaam, String familienaam, String emailadres, String telefoonnummer) {
		
		this.id = id;
		this.voornaam = voornaam;
		this.familienaam = familienaam;
		this.emailAdres = emailadres;
		this.telefoonnummer = telefoonnummer;
		// TODO Auto-generated constructor stub
	}



	public long getId() {
		return id;
	}



	public String getVoornaam() {
		return voornaam;
	}



	public String getFamilienaam() {
		return familienaam;
	}



	public String getEmailAdres() {
		return emailAdres;
	}



	public String getTelefoonnummer() {
		return telefoonnummer;
	}
	

}
