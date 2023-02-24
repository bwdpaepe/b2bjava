package domein;

import org.mindrot.jbcrypt.BCrypt;

import repository.MedewerkerDao;

public class MedewerkerService
{
	private MedewerkerDao mwDao;
	
	public MedewerkerService(MedewerkerDao mwDao) {
		this.mwDao = mwDao;
	}
	
	public MedewerkerDTO aanmelden(String emailAdress, String password) {
		Medewerker mw = mwDao.getMedewerkerByEmailAdress(emailAdress);
		
		if (!BCrypt.checkpw(password, mw.getHashedPW())) { // mw == null is niet nodig, geeft al EntityNotFoundException in DAO klasse
			throw new IllegalArgumentException("Ongeldige inloggegevens");
		}
		
		return new MedewerkerDTO(mw.getVoornaam(), mw.getFamilienaam(), mw.getEmail(), mw.getRol(), mw.getPersoneelsNr());
		
	}
}
