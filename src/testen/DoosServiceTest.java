package testen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import domein.Bedrijf;
import domein.Doos;
import repository.GenericDao;
import service.DoosService;

@ExtendWith(MockitoExtension.class)
public class DoosServiceTest {

	@Mock
	private GenericDao<Doos> doosRepoMock;

	@InjectMocks
	private DoosService doosService;

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

	// Doos gegevens
	private static String NAAM = "Doos A";
	private static double HOOGTE = 10.0;
	private static double BREEDTE = 10.0;
	private static double LENGTE = 10.0;
	private static String DOOS_TYPE_STRING = "standaard";
	private static double PRIJS = 5.0;
	private static long DOOS_ID = 1;
	private Doos doos;

	@BeforeEach
	void maakDoos() {
		doos = new Doos(NAAM, HOOGTE, BREEDTE, LENGTE, DOOS_TYPE_STRING, PRIJS, LEVERANCIER);
	}

	@Test
	void testGetDoosByIdCorrect() {
		Mockito.doReturn(doos).when(doosRepoMock).get(DOOS_ID);

		Doos doos2 = doosService.getDoosById(DOOS_ID);

		Assertions.assertEquals(doos.getBreedte(), doos2.getBreedte());
		Assertions.assertEquals(doos.getHoogte(), doos2.getHoogte());
		Assertions.assertEquals(doos.getLengte(), doos2.getLengte());
		Assertions.assertEquals(doos.getPrijs(), doos2.getPrijs());
		Assertions.assertEquals(doos.getNaam(), doos2.getNaam());
		Assertions.assertEquals(doos.getBedrijf(), doos2.getBedrijf());
		Assertions.assertEquals(doos.getDoosType(), doos2.getDoosType());

		Mockito.verify(doosRepoMock).get(DOOS_ID);
	}

}
