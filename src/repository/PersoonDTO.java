package repository;

import domein.Persoon;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class PersoonDTO {

	protected final long id;
	protected final String voornaam;
	protected final String familienaam;
	protected final String emailAdres;
	protected final String telefoonnummer;

	public PersoonDTO(Persoon persoon) {

		this.id = persoon.getId();
		this.voornaam = persoon.getVoornaam();
		this.familienaam = persoon.getFamilienaam();
		this.emailAdres = persoon.getEmailAdress();
		this.telefoonnummer = persoon.getTelefoonnummer();

	}

	public long getId() {
		return id;
	}

	public String getVoornaam() {
		return voornaam;
	}

	public String getFamilienaam() {
		return familienaam;
	}

	public String getEmailAdres() {
		return emailAdres;
	}

	public String getTelefoonnummer() {
		return telefoonnummer;
	}

	@Override
	public String toString() {
		return "PersoonDTO [id=" + id + ", voornaam=" + voornaam + ", familienaam=" + familienaam + ", emailAdres="
				+ emailAdres + ", telefoonnummer=" + telefoonnummer + "]";
	}

}
