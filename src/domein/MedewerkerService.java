package domein;

import org.mindrot.jbcrypt.BCrypt;

import repository.MedewerkerDao;
import repository.MedewerkerDaoJpa;

public class MedewerkerService
{
	private MedewerkerDao medewerkerRepo;
	
	public MedewerkerService(MedewerkerDao medewerkerRepo) {
		this.medewerkerRepo = medewerkerRepo;
	}
	
	public MedewerkerDTO aanmelden(String emailAdress, String password) {
		Medewerker mw = medewerkerRepo.getMedewerkerByEmailAdress(emailAdress);
		
		if (!BCrypt.checkpw(password, mw.getHashedPW())) { // mw == null is niet nodig, geeft al EntityNotFoundException in DAO klasse
			throw new IllegalArgumentException("Ongeldige inloggegevens");
		}
		
		return new MedewerkerDTO(mw.getVoornaam(), mw.getFamilienaam(), mw.getEmail(), mw.getRol(), mw.getPersoneelsNr());
		
	}

	public void maakMedewerker(String voornaam, String familienaam, String emailadres, String password, String rol,
			int personeelsNr)
	{
		MedewerkerDaoJpa.startTransaction();

		switch (rol.toLowerCase())
			{
			case "magazijnier":
				medewerkerRepo.insert(new Magazijnier(voornaam, familienaam, emailadres, password, personeelsNr));

				break;
			case "admin":
				medewerkerRepo.insert(new Admin(voornaam, familienaam, emailadres, password, personeelsNr));

			default:
				break;
			}

		MedewerkerDaoJpa.commitTransaction();
	}
	
	
}
