package testen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import domein.Medewerker;

class MedewerkerTest
{
	private Medewerker mw;
	private static final String VOORNAAM = "John";
	private static final String ACHTERNAAM = "Doe";
	private static final String EMAIL = "mw1test@test.co";
	private static final String WACHTWOORD = "DitIsEenGeldigWachtwoord!!!";
	private static final String TELEFOONNUMMER = "0477658975";
	private static final String ADRES = "testDorp 9875 testStraat 55";
	private static final int PERSONEELSNR = 1;
	private static final String FUNCTIE = "aDmin"; // bewust afwisseling hoofdletter en kleine letters

	@BeforeEach
	void before()
	{
		mw = new Medewerker(VOORNAAM, ACHTERNAAM, EMAIL, WACHTWOORD, ADRES, TELEFOONNUMMER, PERSONEELSNR, FUNCTIE);
	}

	@Test
	void maakMedewerker_geldigeWaardes()
	{
		Assertions.assertDoesNotThrow(() -> new Medewerker(VOORNAAM, ACHTERNAAM, EMAIL, WACHTWOORD, ADRES,
				TELEFOONNUMMER, PERSONEELSNR, FUNCTIE));
		Assertions.assertEquals(VOORNAAM, mw.getVoornaam());
		Assertions.assertEquals(ACHTERNAAM, mw.getFamilienaam());
		Assertions.assertEquals(EMAIL, mw.getEmail());
		Assertions.assertEquals(PERSONEELSNR, mw.getPersoneelsNr());

		Assertions.assertEquals(FUNCTIE.toString().toLowerCase(), mw.getFunctie().toString().toLowerCase());

		// test dat hashedPw NIET(!) overeenkomt met origineel wachtwoord.
		// Bcrypt genereert telkens verschillende hash, dus niet mogelijk exacte waarde te testen
		Assertions.assertNotNull(mw.getHashedPW());
		Assertions.assertNotEquals(WACHTWOORD, mw.getHashedPW());
	}

	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings =
		{ "", " ", "\n" })
	void ongeldigeNaam_throwtError(String naam)
	{
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Medewerker(naam, ACHTERNAAM, EMAIL,
				WACHTWOORD, ADRES, TELEFOONNUMMER, PERSONEELSNR, FUNCTIE));
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> new Medewerker(VOORNAAM, naam, EMAIL, WACHTWOORD, ADRES, TELEFOONNUMMER, PERSONEELSNR, FUNCTIE));
	}

	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings =
		{ "", " ", "\n", "test.test", "@test.com", "@test", "test @test.com", "test@ test.com", "test@com",
				"test@test.", "test@test..com", "test@com.", "test@.com.", "test@täst.com" })
	void ongeldigeEmail_throwtError(String email)
	{
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Medewerker(VOORNAAM, ACHTERNAAM, email,
				WACHTWOORD, ADRES, TELEFOONNUMMER, PERSONEELSNR, FUNCTIE));
	}

	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings =
		{ "", " ", "\n", "test test", "  @test.com", "test" }) //leeg, te kort, of met spaties
	void ongeldigWachtwoord_throwtError(String wachtwoord)
	{
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Medewerker(VOORNAAM, ACHTERNAAM, EMAIL,
				wachtwoord, ADRES, TELEFOONNUMMER, PERSONEELSNR, FUNCTIE));
	}

	@ParameterizedTest
	@ValueSource(ints =
		{ Integer.MIN_VALUE, -5, 0 })  // als <= 0
	void ongeldigPersoneelsnr_throwtError(int nr)
	{
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> new Medewerker(VOORNAAM, ACHTERNAAM, EMAIL, WACHTWOORD, ADRES, TELEFOONNUMMER, nr, FUNCTIE));
	}

	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings =
		{ "", " ", "\n", "ietsAnders", "magazijn", "adm", "magazijniers", "admins" })
	void ongeldigeFunctie_throwtError(String functie)
	{
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Medewerker(VOORNAAM, ACHTERNAAM, EMAIL,
				WACHTWOORD, ADRES, TELEFOONNUMMER, PERSONEELSNR, functie));
	}

	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings =
		{ "", " ", "\n" })
	void ongeldigAdres_throwtError(String adres)
	{
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Medewerker(VOORNAAM, ACHTERNAAM, EMAIL,
				WACHTWOORD, adres, TELEFOONNUMMER, PERSONEELSNR, FUNCTIE));
	}

}
