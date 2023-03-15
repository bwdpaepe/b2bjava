package repository;

import java.util.HashSet;
import java.util.Set;

import domein.Contactpersoon;
import domein.Dienst;
import domein.Persoon;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

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

	public boolean getIsActief() {
		return isActief;
	}

	public Set<ContactpersoonDTO> getContactpersonen() {
		return contactpersonen;
	}

	private final Set<ContactpersoonDTO> setContactpersonen(Dienst dienst) {
		Set<ContactpersoonDTO> contactpersonen = new HashSet<>();
		for (Persoon cp : dienst.getPersonen()) {
			if (cp instanceof Contactpersoon) {
				contactpersonen.add(new ContactpersoonDTO((Contactpersoon) cp));
			}
		}
		return contactpersonen;
	}

	@Override
	public String toString() {
		return "DienstDTO [id=" + id + ", naam=" + naam + ", isActief=" + isActief + ", contactpersonen="
				+ contactpersonen + "]";
	}

}
