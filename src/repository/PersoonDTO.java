package repository;

public abstract class PersoonDTO {
	
	private final String voornaam;
	private final String familienaam;
	private final String emailAdress;
	private final String telefoonnummer;
	
	public PersoonDTO(String voornaam, String familienaam, String email, String telefoonnummer) {
		this.voornaam = voornaam;
		this.familienaam = familienaam;
		this.emailAdress = email;
		this.telefoonnummer = telefoonnummer;
	}

	public String getVoornaam() {
		return voornaam;
	}

	public String getFamilienaam() {
		return familienaam;
	}

	public String getEmailAdress() {
		return emailAdress;
	}

	public String getTelefoonnummer() {
		return telefoonnummer;
	}

	@Override
	public String toString() {
		return "voornaam=" + voornaam + ", familienaam=" + familienaam + ", emailAdress=" + emailAdress
				+ ", telefoonnummer=" + telefoonnummer;
	}
	
	

}
