package service;

import java.util.Date;

import javax.persistence.Transient;

public class ValidationService
{
	@Transient
	public static final int MIN_PW_LENGTH = 8; // TODO afspreken met team

	public static final void controleerNietBlanco(Object waarde)
	{
		if (waarde == null)
		{
			throw new IllegalArgumentException("Veld is verplicht");
		}
		if(waarde instanceof String && ((String) waarde).isBlank()) {
			throw new IllegalArgumentException("Veld is verplicht");
		}
	}
	
	public static final void controleerGroterDanNul(int waarde) {
		if (waarde <= 0)
		{// eventueel nog andere checks toevoegen
			throw new IllegalArgumentException("Veld moet groter zijn dan nul");
		}
	}
	public static final void controleerGroterDanNul(long waarde) {
		if (waarde <= 0)
		{// eventueel nog andere checks toevoegen
			throw new IllegalArgumentException("Veld moet groter zijn dan nul");
		}
	}
	
	public static final void controleerGroterDanNul(double waarde) {
		if (waarde <= 0)
		{// eventueel nog andere checks toevoegen
			throw new IllegalArgumentException("Veld moet groter zijn dan nul");
		}
	}


	public static final void controleerEmail(String email)
	{
		if (email == null || email.isBlank())
		{
			throw new IllegalArgumentException("E-mailadres is verplicht");
		}
		// https://howtodoinjava.com/java/regex/java-regex-validate-email-address/
		if (!email.matches(
				"^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$"))
		{
			throw new IllegalArgumentException("Ongeldig e-mailadres formaat");
		}
	}

	public static final void controleerWachtwoord(String password)
	{
		// error als geen wachtwoord, te kort, of bevat spatie
		if (password == null || password.length() < MIN_PW_LENGTH || password.matches(".*\\s.*"))
		{
			throw new IllegalArgumentException("Wachtwoord is ongeldig");
		}
	}

	public static final void controleerTelefoonnummer(String telefoonnummer)
	{
		// error als geen telefoonnummer, of niet voldoet aan de regex: eventueel
		// beginnen met een '+', en verder enkel cijfers (minstens 6)
		if (telefoonnummer == null || !telefoonnummer.matches("^\\+?\\d{6,}$"))
		{
			throw new IllegalArgumentException("Telefoonnummer is ongeldig");
		}
	}
	
	//uiteindelijk nog niet nodig, maar laat het staan voor eventueel later gebruik
	public static final void controleerDatumNietInVerleden(Date datum) {
		if (new Date().compareTo(datum) > 0) {
			throw new IllegalArgumentException("datum kan niet in het verleden liggen");
		}
	}


	





}
