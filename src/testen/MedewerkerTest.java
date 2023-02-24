package testen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import domein.Medewerker;

class MedewerkerTest
{
	private Medewerker mw;
	private static final String VOORNAAM = "John";
	private static final String ACHTERNAAM = "Doe";
	private static final String EMAIL = "mw1.test@test.co.edu";
	private static final String WACHTWOORD = "DitIsEenGeldigWachtwoord!!!";
	private static final int PERSONEELSNR = 1;
	
	// TODO deze geeft nog fout door het ontwerp waarbij Medewerker abstracte klasse is
	@BeforeEach
	void before() {
		mw = new Medewerker();
	}
	
	// TODO deze geeft nog fout door het ontwerp waarbij Medewerker abstracte klasse is
	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings = {"", " ", "\n"})
	void setVoornaam_ongeldigeWaarde_throwtError(String voornaam) {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Medewerker(voornaam, ACHTERNAAM, EMAIL, WACHTWOORD, PERSONEELSNR));
	}

}
