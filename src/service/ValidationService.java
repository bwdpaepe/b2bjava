package service;

import javax.persistence.Transient;

public class ValidationService
{
	@Transient
	public static final int MIN_PW_LENGTH = 8; // TODO afspreken met team

	public static final void controleerNaam(String naam)
	{
		if (naam == null || naam.isBlank())
		{
			throw new IllegalArgumentException("Voornaam is verplicht");
		}
	}

	public static final void controleerAdres(String adres)
	{
		if (adres == null || adres.isBlank())
		{
			throw new IllegalArgumentException("Adres is verplicht");
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

	public static final void controleerFunctieString(String functie) {
		if (functie == null || functie.isBlank())
		{
			throw new IllegalArgumentException("De functie van de Medewerker is ongeldig");
		}
	}
	
	public static final void controleerPersoneelsnr(int personeelsNr)
	{
		if (personeelsNr <= 0)
		{// eventueel nog andere checks toevoegen
			throw new IllegalArgumentException("Personeelnummer is ongeldig");
		}
	}


	public static final void controleerBarcodeLengte(int aantal)
	{
		if (aantal <= 0)
		{
			throw new IllegalArgumentException("TractTraceFormat aantal karakters is ongeldig");
		}
	}

	public static final void controleerBarcodePrefix(String prefix)
	{
		if (prefix == null || prefix.isBlank())
		{
			throw new IllegalArgumentException("TractTraceFormat Prefix is ongeldig");
		}
	}

	public static final void controleerTrackVerificatiecode(String code)
	{
		if (code == null || code.isBlank())
		{
			throw new IllegalArgumentException("De Track&Trace verificatiecode is ongeldig");
		}
	}

}
