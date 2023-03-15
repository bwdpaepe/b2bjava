package repository;

import domein.Contactpersoon;

public class ContactpersoonDTO extends PersoonDTO {
	
	

	public ContactpersoonDTO(Contactpersoon cp) {
		super(cp);

	}

	@Override
	public String toString() {
		return "ContactpersoonDTO [id=" + id + ", voornaam=" + voornaam + ", familienaam=" + familienaam
				+ ", emailAdres=" + emailAdres + ", telefoonnummer=" + telefoonnummer + "]";
	}
	

	
	

}
