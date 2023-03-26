package service;

import java.util.Date;
import java.util.Set;



import repository.ContactpersoonDTO;

public class ValidationService {
	final private static int TRACKTRACECODELENGTE = 30;
	
	public static final int MIN_PW_LENGTH = 8; 

	public static final void controleerNietBlanco(Object waarde) {
		if (waarde == null) {
			throw new IllegalArgumentException("Veld is verplicht");
		}
		if (waarde instanceof String && (((String) waarde).isBlank() || ((String)waarde).isEmpty())) {
			throw new IllegalArgumentException("Veld is verplicht");
		}
	}

	public static final <T extends Number> void controleerGroterDanNul(T waarde) {
	    if (waarde.doubleValue() <= 0) {
	        throw new IllegalArgumentException("Veld moet groter zijn dan nul");
	    }
	}
	
	public static final void controleerEmail(String email) {
		if (email == null || email.isBlank()) {
			throw new IllegalArgumentException("E-mailadres is verplicht");
		}
		// https://howtodoinjava.com/java/regex/java-regex-validate-email-address/
		if (!email.matches(
				"^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")) {
			throw new IllegalArgumentException("Ongeldig e-mailadres formaat");
		}
	}

	public static final void controleerWachtwoord(String password) {
		// error als geen wachtwoord, te kort, of bevat spatie
		if (password == null || password.length() < MIN_PW_LENGTH || password.matches(".*\\s.*")) {
			throw new IllegalArgumentException("Wachtwoord is ongeldig");
		}
	}

	public static final void controleerTelefoonnummer(String telefoonnummer) {
		// error als geen telefoonnummer, of niet voldoet aan de regex: eventueel
		// beginnen met een '+', en verder enkel cijfers (minstens 6)
		if (telefoonnummer == null || !telefoonnummer.matches("^\\+?\\d{6,}$")) {
			throw new IllegalArgumentException("Telefoonnummer is ongeldig");
		}
	}

	// uiteindelijk nog niet nodig, maar laat het staan voor eventueel later gebruik
	public static final void controleerDatumNietInVerleden(Date datum) {
		if (new Date().compareTo(datum) > 0) {
			throw new IllegalArgumentException("datum kan niet in het verleden liggen");
		}
	}

	public static final void controleerUniekEmailadres(Set<ContactpersoonDTO> contactpersonen, String emailadres) {
		for (ContactpersoonDTO c : contactpersonen) {
			if (c.getEmailAdres().equals(emailadres)) {
				throw new IllegalArgumentException("Iedere contactpersoon moet een uniek emailadres hebben");
			}
		}
	}
	

	
	public static final void controleerTTFPrefixLengte(int TTFlengte, String TTFPrefix) {
		if(TTFPrefix.length() >= TTFlengte) {
			throw new IllegalArgumentException("Prefix kan niet groter of gelijk zijn dan totale lengte code");
		}
	}
	
	public static final void controleerTTFTotaleLengte(int totaleLengte) {
		if(totaleLengte < TRACKTRACECODELENGTE) {
			throw new IllegalArgumentException(String.format("%s%d", "Totale lengte code kan niet kleiner zijn dan ", TRACKTRACECODELENGTE));
		}
	}

}
