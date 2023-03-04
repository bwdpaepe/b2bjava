package repository;

import java.util.Set;

//Data Transfer Object PATTERN (DTO PATTERN)
//ENKEL FINAL ATTRIBUTEN EN NOOIT LOGICA OPNEMEN
public abstract class DienstDTO {
	
	private final Set<PersoonDTO> personen;
	private final String naam;
	private final boolean isActief;
	
	public DienstDTO(String naam, boolean isActief, Set<PersoonDTO> personen) {
		this.naam = naam;
		this.isActief = isActief;
		this.personen = personen;
	}

	public Set<PersoonDTO> getPersonen() {
		return personen;
	}

	public String getNaam() {
		return naam;
	}

	public boolean isActief() {
		return isActief;
	}

	@Override
	public String toString() {
		return "personen=" + personen + ", naam=" + naam + ", isActief=" + isActief;
	}

}
