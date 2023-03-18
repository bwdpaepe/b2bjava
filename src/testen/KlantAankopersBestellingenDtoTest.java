package testen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domein.Bedrijf;
import domein.Bestelling;
import domein.BestellingStatus;
import domein.Medewerker;
import domein.User;
import repository.KlantAankopersBestellingenDTO;

public class KlantAankopersBestellingenDtoTest
{
	private static final Long KLANT_ID = 999L;
	private static final String KLANTNAAM = "klantnaam";
	private static final String STRAAT = "straat";
	private static final String HUISNUMMER = "12 bus 3";
	private static final String POSTCODE = "ab 9845";
	private static final String STAD = "testStad";
	private static final String LAND = "testLand";
	private static final String TELEFOONNUMMER = "+123456";
	private static final String LOGO_FILENAME = "bedrijf_klantnaam";
	private static final Bedrijf KLANT = new Bedrijf(KLANTNAAM, STRAAT, HUISNUMMER, POSTCODE, STAD, LAND, TELEFOONNUMMER, LOGO_FILENAME);
	
	private static final User AANKOPER_1 = new Medewerker("A1.", "De Aankoper", "aankoperA1@test.com", "paswoord", "Adres adres adres5", "047565442854", 6, "aankoper", new Bedrijf());
	private static final User AANKOPER_2 = new Medewerker("D1.", "De Aankoper", "aankoperD1@test.com", "paswoord", "Adres adres adres4", "047565442854", 7, "aankoper", new Bedrijf());
	private static final List<User> AANKOPERS = new ArrayList<>(Arrays.asList(AANKOPER_1, AANKOPER_2));
	
	private static final String ORDERID_1 = "order1";
	private static final String ORDERID_2 = "order 2";
	private static final String ORDERID_3 = "Order 3";
	@SuppressWarnings("deprecation")
	private static final Date DATE_1 = new Date(2022 - 1900, 9, 10);
	@SuppressWarnings("deprecation")
	private static final Date DATE_2 = new Date(2023 - 1900, 1, 18);
	@SuppressWarnings("deprecation")
	private static final Date DATE_3 = new Date(2023 - 1900, 2, 22);
	private static final String STATUS_1 = BestellingStatus.GEPLAATST.toString();
	private static final String STATUS_2 = BestellingStatus.GEPLAATST.toString();
	private static final String STATUS_3 = BestellingStatus.VERWERKT.toString();
	private static final Bestelling BESTELLING_1 = new Bestelling(ORDERID_1, DATE_1, STATUS_1, new Bedrijf(), KLANT, null, (Medewerker) AANKOPER_1, STRAAT, HUISNUMMER, POSTCODE, STAD, LAND, null);
	private static final Bestelling BESTELLING_2 = new Bestelling(ORDERID_2, DATE_2, STATUS_2, new Bedrijf(), KLANT, null, (Medewerker) AANKOPER_2, STRAAT, HUISNUMMER, POSTCODE, STAD, LAND, null);
	private static final Bestelling BESTELLING_3 = new Bestelling(ORDERID_3, DATE_3, STATUS_3, new Bedrijf(), KLANT, null, (Medewerker) AANKOPER_2, STRAAT, HUISNUMMER, POSTCODE, STAD, LAND, null);
	private static final List<Bestelling> BESTELLINGEN = new ArrayList<>(Arrays.asList(BESTELLING_1, BESTELLING_2, BESTELLING_3));
	
	private KlantAankopersBestellingenDTO kab;
	
	@BeforeEach
	void before() {
		kab = new KlantAankopersBestellingenDTO(KLANT, AANKOPERS, BESTELLINGEN);
	}
	
	@Test
	void maakKlantAankopersBestellingenDTO() {
		Assertions.assertDoesNotThrow(() -> new KlantAankopersBestellingenDTO(KLANT, AANKOPERS, BESTELLINGEN));
		
		Assertions.assertEquals(KLANT.getID(), kab.getKlantId());
		Assertions.assertEquals(KLANT.getNaam(), kab.getKlantNaam());
		Assertions.assertEquals(KLANT.getStraat(), kab.getStraat());
		Assertions.assertEquals(KLANT.getHuisnummer(), kab.getHuisnummer());
		Assertions.assertEquals(KLANT.getPostcode(), kab.getPostcode());
		Assertions.assertEquals(KLANT.getStad(), kab.getStad());
		Assertions.assertEquals(KLANT.getLand(), kab.getLand());
		Assertions.assertEquals(KLANT.getTelefoonnummer(), kab.getTelefoonnummer());
		Assertions.assertEquals(KLANT.getLogo_filename(), kab.getLogo_filename());
		
		Assertions.assertEquals(AANKOPERS.size(), kab.getAankopers().size());
		
		Assertions.assertEquals(AANKOPER_1.getVoornaam(), kab.getAankopers().get(0).getVoornaam());
		Assertions.assertEquals(AANKOPER_1.getFamilienaam(), kab.getAankopers().get(0).getFamilienaam());
		Assertions.assertEquals(AANKOPER_1.getEmail(), kab.getAankopers().get(0).getEmailadres());
		
		Assertions.assertEquals(AANKOPER_2.getVoornaam(), kab.getAankopers().get(1).getVoornaam());
		Assertions.assertEquals(AANKOPER_2.getFamilienaam(), kab.getAankopers().get(1).getFamilienaam());
		Assertions.assertEquals(AANKOPER_2.getEmail(), kab.getAankopers().get(1).getEmailadres());
		
		Assertions.assertEquals(BESTELLINGEN.size(), kab.getBestellingen().size());
		
		Assertions.assertEquals(BESTELLING_1.getOrderID(), kab.getBestellingen().get(0).getOrderId());
		Assertions.assertEquals(BESTELLING_1.getDatumGeplaatst(), kab.getBestellingen().get(0).getDatumGeplaatst());
		Assertions.assertEquals(BESTELLING_1.getStatus(), kab.getBestellingen().get(0).getStatus());

		Assertions.assertEquals(BESTELLING_2.getOrderID(), kab.getBestellingen().get(1).getOrderId());
		Assertions.assertEquals(BESTELLING_2.getDatumGeplaatst(), kab.getBestellingen().get(1).getDatumGeplaatst());
		Assertions.assertEquals(BESTELLING_2.getStatus(), kab.getBestellingen().get(1).getStatus());

		Assertions.assertEquals(BESTELLING_3.getOrderID(), kab.getBestellingen().get(2).getOrderId());
		Assertions.assertEquals(BESTELLING_3.getDatumGeplaatst(), kab.getBestellingen().get(2).getDatumGeplaatst());
		Assertions.assertEquals(BESTELLING_3.getStatus(), kab.getBestellingen().get(2).getStatus());
	}
}
