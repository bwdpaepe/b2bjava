package repository;

import domein.Persoon;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class PersoonDTO {

	protected final long id;
	/*
	 * protected final String voornaam; protected final String familienaam;
	 * protected final String emailAdres; protected final String telefoonnummer;
	 */
	protected final SimpleStringProperty voornaam = new SimpleStringProperty();
	protected final SimpleStringProperty familienaam = new SimpleStringProperty();
	protected final SimpleStringProperty emailAdres = new SimpleStringProperty();
	protected final SimpleStringProperty telefoonnummer = new SimpleStringProperty();

	public PersoonDTO(Persoon persoon) {

		this.id = persoon.getId();
		setVoornaam(persoon.getVoornaam());
		setFamilienaam(persoon.getFamilienaam());
		setEmailAdres(persoon.getEmailAdress());
		setTelefoonnummer(persoon.getTelefoonnummer());

	}

	public long getId() {
		return id;
	}

	private void setVoornaam(String voornaam) {
		this.voornaam.set(voornaam);
	}

	public String getVoornaam() {
		return voornaam.get();
	}

	public StringProperty getVoornaamProperty() {
		return voornaam;
	}

	private void setFamilienaam(String familienaam) {
		this.familienaam.set(familienaam);
	}

	public String getFamilienaam() {
		return familienaam.get();
	}

	public StringProperty getFamilienaamProperty() {
		return familienaam;
	}

	private void setEmailAdres(String emailAdres) {
		this.emailAdres.set(emailAdres);
	}

	public String getEmailAdres() {
		return emailAdres.get();
	}

	public StringProperty getEmailadresProperty() {
		return emailAdres;
	}

	private void setTelefoonnummer(String telefoonnummer) {
		this.telefoonnummer.set(telefoonnummer);
	}

	public String getTelefoonnummer() {
		return telefoonnummer.get();
	}

	public StringProperty getTelefoonnummerProperty() {
		return telefoonnummer;
	}

	@Override
	public String toString() {
		return "PersoonDTO [id=" + id + ", voornaam=" + voornaam + ", familienaam=" + familienaam + ", emailAdres="
				+ emailAdres + ", telefoonnummer=" + telefoonnummer + "]";
	}

}
