package domein;

import repository.GenericDaoJpa;
import repository.MedewerkerDao;
import repository.MedewerkerDaoJpa;

public class DomeinController {
	private Medewerker m;
	private MedewerkerDao medewerkerRepo;

	public DomeinController() {
		setMedewerkerRepo(new MedewerkerDaoJpa());
	}

	public Medewerker getM() {
		return m;
	}

	public void setM(Medewerker m) {
		// TODO
	}

	public MedewerkerDao getMedewerkerRepo() {
		return medewerkerRepo;
	}

	public void setMedewerkerRepo(MedewerkerDao medewerkerRepo) {
		this.medewerkerRepo = medewerkerRepo;
	}

	public Medewerker aanmelden(String emailAdress, String paswoord) {
		// TODO
		return new Magazijnier("", "", "", "", 0); // NOG AAN TE PAKKEN, dit is placeholder om geen errors te zien

	}

	public void maakMedewerker(String voornaam, String familienaam, String emailadres, String paswoord, String rol,
			int personeelsNR) {
		GenericDaoJpa.startTransaction();

		switch (rol.toLowerCase()) {
		case "magazijnier":
			medewerkerRepo.insert(new Magazijnier(voornaam, familienaam, emailadres, paswoord, personeelsNR));

			break;
		case "admin":
			medewerkerRepo.insert(new Admin(voornaam, familienaam, emailadres, paswoord, personeelsNR));

		default:
			break;
		}

		GenericDaoJpa.commitTransaction();

	}
	
	public void close() {
		GenericDaoJpa.closePersistency();
	}

}
