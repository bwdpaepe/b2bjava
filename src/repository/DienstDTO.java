package repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.mockito.internal.util.collections.HashCodeAndEqualsSafeSet;

import domein.Contactpersoon;
import domein.Dienst;
import domein.Persoon;

//Data Transfer Object PATTERN (DTO PATTERN)
//ENKEL FINAL ATTRIBUTEN EN NOOIT LOGICA OPNEMEN
public abstract class DienstDTO {
	
	protected final long id;
	protected final String naam;
	protected final boolean isActief;
	protected final Set<ContactpersoonDTO> contactpersonen;

	
	public DienstDTO(Dienst dienst) {
		this.id = dienst.getId();
		this.naam = dienst.getNaam();
		this.isActief = dienst.isActief();
		this.contactpersonen = setContactpersonen(dienst);

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
	
	private final Set<ContactpersoonDTO> setContactpersonen(Dienst dienst){
		Set<ContactpersoonDTO> contactpersonen = new HashSet<>();
		for (Persoon cp : dienst.getPersonen()) {
			if(cp instanceof Contactpersoon) {
				contactpersonen.add(new ContactpersoonDTO((Contactpersoon)cp));
			}
		}
		return contactpersonen;
	}



	

	

	

}
