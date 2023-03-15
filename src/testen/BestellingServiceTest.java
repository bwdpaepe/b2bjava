package testen;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import domein.Bedrijf;
import domein.Bestelling;
import domein.Contactpersoon;
import domein.Doos;
import domein.Medewerker;
import domein.Product;
import domein.TrackTraceFormat;
import domein.Transportdienst;
import repository.BestellingDao;
import service.BedrijfService;
import service.BestellingService;
import service.DienstService;
import service.DoosService;
import service.ProductService;
import service.UserService;

@ExtendWith(MockitoExtension.class)
class BestellingServiceTest {

	@Mock
	private BestellingDao bestellingRepoMock;

	@Mock
	private ProductService productServiceMock;

	@Mock
	private BedrijfService bedrijfServiceMock;

	@Mock
	private DienstService dienstServiceMock;

	@Mock
	private UserService userServiceMock;

	@Mock
	private DoosService doosSerivceMock;

	@InjectMocks
	private BestellingService bestellingService;

	private Bestelling bestelling;

	private static final String ORDER_ID = "1";
	private static final long BESTELLING_ID = 1;
	private static final String STATUS = "geplaatst";
	private static final Date DATE = new Date();
	private static final long LEVERANCIER_ID = 1;
	private static final long KLANT_ID = 2;
	private static final long TRANSPORT_ID = 1;
	private static final long AANKOPER_ID = 1;
	private static final String LEVERADRES_STRAAT = "Straat A";
	private static final String LEVERADRES_HUISNUMMER = "A1";
	private static final String LEVERADRES_POSTCODE = "1234A";
	private static final String LEVERADRES_STAD = " stad A";
	private static final String LEVERADRES_LAND = "land A";
	private static final long DOOS_ID = 1;

	// Leverancier gegevens
	private static final String NAAMBEDRIJF_LEVERANCIER = "Bedrijf A";
	private static final String STRAAT_LEVERANCIER = "Straat A";
	private static final String HUISNUMMER_LEVERANCIER = "A1";
	private static final String POSTCODE_LEVERANCIER = "1234A";
	private static final String STAD_LEVERANCIER = " stad A";
	private static final String LAND_LEVERANCIER = "land A";
	private static final String TELEFOONNUMMER_LEVERANCIER = "0123456789";
	private static final String LOGO_FILENAME_LEVERANCIER = "logog_bedrijf_A";
	private final Bedrijf LEVERANCIER = new Bedrijf(NAAMBEDRIJF_LEVERANCIER, STRAAT_LEVERANCIER, HUISNUMMER_LEVERANCIER,
			POSTCODE_LEVERANCIER, STAD_LEVERANCIER, LAND_LEVERANCIER, TELEFOONNUMMER_LEVERANCIER,
			LOGO_FILENAME_LEVERANCIER);

	// Klant gegevens
	private static final String NAAMBEDRIJF_KLANT = "Bedrijf A";
	private static final String STRAAT_KLANT = "Straat A";
	private static final String HUISNUMMER_KLANT = "A1";
	private static final String POSTCODE_KLANT = "1234A";
	private static final String STAD_KLANT = " stad A";
	private static final String LAND_KLANT = "land A";
	private static final String TELEFOONNUMMER_KLANT = "0123456789";
	private static final String LOGO_FILENAME_KLANT = "logog_bedrijf_A";
	private final Bedrijf KLANT = new Bedrijf(NAAMBEDRIJF_KLANT, STRAAT_KLANT, HUISNUMMER_KLANT, POSTCODE_KLANT,
			STAD_KLANT, LAND_KLANT, TELEFOONNUMMER_KLANT, LOGO_FILENAME_KLANT);

	private final String NAAMTRANSPORTDIENT = "Transportdienst_A";

	private final int BARCODELENGTE = 10;
	private final boolean ISBARCODEENKELCIJFERS = true;
	private final String BARCODEPREFIX = "123";
	private final String VERIFICATIECODE = "POSTCODE";
	private final TrackTraceFormat TTF = new TrackTraceFormat(BARCODELENGTE, ISBARCODEENKELCIJFERS, BARCODEPREFIX,
			VERIFICATIECODE);

	private final String CONTACTVOORNAAM = "TRANS";
	private final String CONTACTFAMILIENAAM = "PORT";
	private final String CONTACTTELEFOON = "1234567890";
	private final String CONTACTEMAILADRES = "trans.port@example.com";
	private final Contactpersoon CONTACTPERSOON = new Contactpersoon(CONTACTVOORNAAM, CONTACTFAMILIENAAM,
			CONTACTEMAILADRES, CONTACTTELEFOON);

	private static final String NAAMBEDRIJF = "Bedrijf A";
	private static final String STRAAT = "Straat A";
	private static final String HUISNUMMER = "A1";
	private static final String POSTCODE = "1234A";
	private static final String STAD = " stad A";
	private static final String LAND = "land A";
	private static final String TELEFOONNUMMER = "0123456789";
	private static final String LOGO_FILENAME = "logog_bedrijf_A";
	private final Bedrijf TRANSPORTDIENST_BEDRIJF = new Bedrijf(NAAMBEDRIJF, STRAAT, HUISNUMMER, POSTCODE, STAD, LAND,
			TELEFOONNUMMER, LOGO_FILENAME);

	// Transportdienst
	private final Transportdienst TRANSPORTDIENST = new Transportdienst(NAAMTRANSPORTDIENT, TRANSPORTDIENST_BEDRIJF,
			CONTACTPERSOON, TTF);

	// Aankoper gegevens
	private static final String VOORNAAM = "Joachim2";
	private static final String FAMILIENAAM = "Dauchot";
	private static final String EMAIL = "emailail1@test.com";
	private static final String WACHTWOORD = "paswoord";
	private static final String ADRES = "Adres adres adres1";
	private static final String TELEFOONNUMMER_AANKOPER = "047563541854";
	private static final int PERSONEELNUMMER = 5;
	private static final String FUNCTIE = "aankoper";
	private final Medewerker AANKOPER = new Medewerker(VOORNAAM, FAMILIENAAM, EMAIL, WACHTWOORD, ADRES,
			TELEFOONNUMMER_AANKOPER, PERSONEELNUMMER, FUNCTIE, KLANT);
	// Doos gegevens
	private static String NAAM = "Doos A";
	private static double HOOGTE = 10.0;
	private static double BREEDTE = 10.0;
	private static double LENGTE = 10.0;
	private static String DOOS_TYPE_STRING = "standaard";
	private static double PRIJS = 5.0;
	private final Doos DOOS = new Doos(NAAM, HOOGTE, BREEDTE, LENGTE, DOOS_TYPE_STRING, PRIJS, LEVERANCIER);

	// product
	private static final String NAAM_PRODUCT = "Prodcut A";
	private static final double EENHEIDSPRIJS = 1.0;
	private static final long PRODUCT_ID = 1;
	private final Product PRODUCT = new Product(NAAM_PRODUCT, EENHEIDSPRIJS, LEVERANCIER);

	// Besteld product
	private static final int AANTAL = 10;
	// private final BesteldProduct BESTELD_PRODUCT = new BesteldProduct(PRODUCT,
	// AANTAL, bestelling);

	@BeforeEach
	void maakBestelling() {
		bestelling = new Bestelling(ORDER_ID, DATE, STATUS, LEVERANCIER, KLANT, TRANSPORTDIENST, AANKOPER,
				LEVERADRES_STRAAT, LEVERADRES_POSTCODE, LEVERADRES_STAD, LEVERADRES_LAND, LEVERADRES_HUISNUMMER, DOOS);
	}

////	Test werkt nog niet te bekijken
//	@Test
//	void maakBestellingTest() {
//
//		Mockito.doNothing().when(bestellingRepoMock).insert(bestelling);
//
//		Mockito.doReturn(LEVERANCIER).when(bedrijfServiceMock).getBedrijfById(LEVERANCIER_ID);
//
//		Mockito.doReturn(KLANT).when(bedrijfServiceMock).getBedrijfById(KLANT_ID);
//
//		Mockito.doReturn(TRANSPORTDIENST).when(dienstServiceMock).getTransportdienstByID(TRANSPORT_ID);
//
//		Mockito.doReturn(AANKOPER).when(userServiceMock).getMedewerkerById(AANKOPER_ID);
//
//		Mockito.doReturn(DOOS).when(doosSerivceMock).getDoosById(DOOS_ID);
//
//		bestellingService.maakBestelling(ORDER_ID, STATUS, DATE, LEVERANCIER_ID, KLANT_ID, TRANSPORT_ID, AANKOPER_ID,
//				LEVERADRES_STRAAT, LEVERADRES_HUISNUMMER, LEVERADRES_POSTCODE, LEVERADRES_STAD, LEVERADRES_LAND,
//				DOOS_ID);
//
//		Mockito.verify(bestellingRepoMock).insert(bestelling);
//
//	}

	@Test
	void testGetBestellingen() {
		bestelling = new Bestelling(ORDER_ID, DATE, STATUS, LEVERANCIER, KLANT, TRANSPORTDIENST, AANKOPER,
				LEVERADRES_STRAAT, LEVERADRES_POSTCODE, LEVERADRES_STAD, LEVERADRES_LAND, LEVERADRES_HUISNUMMER, DOOS);

		Bestelling bestelling = new Bestelling(ORDER_ID, DATE, STATUS, LEVERANCIER, KLANT, TRANSPORTDIENST, AANKOPER,
				LEVERADRES_STRAAT, LEVERADRES_HUISNUMMER, LEVERADRES_POSTCODE, LEVERADRES_STAD, LEVERADRES_LAND, DOOS);
		Bestelling bestelling2 = new Bestelling(ORDER_ID, DATE, STATUS, LEVERANCIER, KLANT, TRANSPORTDIENST, AANKOPER,
				LEVERADRES_STRAAT, LEVERADRES_HUISNUMMER, LEVERADRES_POSTCODE, LEVERADRES_STAD, LEVERADRES_LAND, DOOS);
		List<Bestelling> bestellingList = new ArrayList<>();
		bestellingList.add(bestelling);
		bestellingList.add(bestelling2);

		Mockito.doReturn(LEVERANCIER).when(bedrijfServiceMock).getBedrijfById(LEVERANCIER_ID);
		Mockito.doReturn(bestellingList).when(bestellingRepoMock).getBestellingenByLeverancierID(LEVERANCIER);

		List<Bestelling> bestellingList2 = bestellingService.getBestellingen(LEVERANCIER_ID);

		Assertions.assertEquals(bestellingList, bestellingList2);

		Mockito.verify(bestellingRepoMock).getBestellingenByLeverancierID(LEVERANCIER);

	}

//	@Test
//	void testAddBesteldProductToBestelling() {
//	
//		Mockito.doReturn(bestelling).when(bestellingRepoMock).get(BESTELLING_ID);
//		Mockito.doReturn(PRODUCT).when(productServiceMock).getProductById(PRODUCT_ID);
//		Mockito.doReturn(bestelling).when(bestellingRepoMock).update(bestelling);
//
//		bestellingService.addBesteldProductToBestelling(BESTELLING_ID, PRODUCT_ID, AANTAL);
//
//		Mockito.verify(bestellingRepoMock).update(bestelling);
//
//	}

}
