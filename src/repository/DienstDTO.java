package repository;

import java.util.List;

//Data Transfer Object PATTERN (DTO PATTERN)
//ENKEL FINAL ATTRIBUTEN EN NOOIT LOGICA OPNEMEN
public abstract class DienstDTO {
	
	protected final long id;
	protected final String naam;
	protected final boolean isActief;
	protected final List<String> voornaamLijst;
	protected final List<String> familienaamLijst;
	protected final List<String> emailAdressLijst;
	protected final List<String> telefoonnummerLijst;
	
	public DienstDTO(long id, String naam, boolean isActief, List<String> voornaamLijst, List<String> familienaamLijst, List<String> emailAdressLijst, List<String> telefoonnummerLijst) {
		this.id = id;
		this.naam = naam;
		this.isActief = isActief;
		this.voornaamLijst = voornaamLijst;
		this.familienaamLijst = familienaamLijst;
		this.emailAdressLijst = emailAdressLijst;
		this.telefoonnummerLijst = telefoonnummerLijst;
	}

	public long getId() {
		return id;
	}

	public String getNaam() {
		return naam;
	}

	public boolean getIsActief() { //de get is nodig om de data in de tableView te krijgen
		return isActief;
	}

	public List<String> getVoornaamLijst() {
		return voornaamLijst;
	}

	public List<String> getFamilienaamLijst() {
		return familienaamLijst;
	}

	public List<String> getEmailAdressLijst() {
		return emailAdressLijst;
	}

	public List<String> getTelefoonnummerLijst() {
		return telefoonnummerLijst;
	}

	@Override
	public String toString() {
		return "DienstDTO [id=" + id + ", naam=" + naam + ", isActief=" + isActief + ", voornaamLijst=" + voornaamLijst
				+ ", familienaamLijst=" + familienaamLijst + ", emailAdressLijst=" + emailAdressLijst
				+ ", telefoonnummerLijst=" + telefoonnummerLijst + "]";
	}

	

	

	

}
