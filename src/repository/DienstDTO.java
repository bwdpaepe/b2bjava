package repository;

import java.util.List;
import java.util.Set;

import domein.Contactpersoon;

//Data Transfer Object PATTERN (DTO PATTERN)
//ENKEL FINAL ATTRIBUTEN EN NOOIT LOGICA OPNEMEN
public abstract class DienstDTO {
	
	protected final long id;
	protected final String naam;
	protected final boolean isActief;
	protected final Set<ContactpersoonDTO> contactpersonen;

	
	public DienstDTO(long id, String naam, boolean isActief, Set<ContactpersoonDTO> contactpersonen) {
		this.id = id;
		this.naam = naam;
		this.isActief = isActief;
		this.contactpersonen = contactpersonen;

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
	
	public Set<ContactpersoonDTO> getContactpersonen(){
		return contactpersonen;
	}



	

	

	

}
