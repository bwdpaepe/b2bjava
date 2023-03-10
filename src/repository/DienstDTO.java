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
	protected final SimpleStringProperty naam = new SimpleStringProperty();
	protected final SimpleBooleanProperty isActief = new SimpleBooleanProperty();
	protected final Set<ContactpersoonDTO> contactpersonen;

	public DienstDTO(Dienst dienst) {
		this.id = dienst.getId();
		setNaam(dienst.getNaam());
		setIsActief(dienst.isActief());
		this.contactpersonen = setContactpersonen(dienst);

	}

	public DienstDTO(long id, String naam, boolean isActief, Set<ContactpersoonDTO> contactpersonen) {
		this.id = id;
		setNaam(naam);
		setIsActief(isActief);
		this.contactpersonen = contactpersonen;
	}

	public long getId() {
		return id;
	}

	private void setNaam(String naam) {
		this.naam.set(naam);
	}

	public String getNaam() {
		return naam.get();
	}

	public StringProperty getNaamProperty() {
		return naam;
	}

	private void setIsActief(boolean isActief) {
		this.isActief.set(isActief);
	}

	public boolean getIsActief() {
		return isActief.get();
	}

	public BooleanProperty getIsActiefProperty() { // de get is nodig om de data in de tableView te krijgen
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
