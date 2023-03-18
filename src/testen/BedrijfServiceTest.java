package testen;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import domein.Bedrijf;
import domein.BestellingStatus;
import domein.KlantEnAantalBestellingen;
import repository.BedrijfDao;
import repository.KlantLijstEntryDTO;
import service.BedrijfService;

@ExtendWith(MockitoExtension.class)
public class BedrijfServiceTest
{

	@Mock
	private BedrijfDao bedrijfRepoMock;

	@InjectMocks
	private BedrijfService bedrijfService;

	private static final String NAAM = "Bedrijf A";
	private static final String STRAAT = "Straat A";
	private static final String HUISNUMMER = "A1";
	private static final String POSTCODE = "1234A";
	private static final String STAD = " stad A";
	private static final String LAND = "land A";
	private static final String TELEFOONNUMMER = "0123456789";
	private static final String LOGO_FILENAME = "logog_bedrijf_A";

	private static final Long BEDRIJFSID = 1l;

	@Test
	void maakBedrijfTest()
	{
		Bedrijf bedrijf = new Bedrijf(NAAM, STRAAT, HUISNUMMER, POSTCODE, STAD, LAND, TELEFOONNUMMER, LOGO_FILENAME);
		Mockito.doNothing().when(bedrijfRepoMock).insert(bedrijf);

		bedrijfService.maakBedrijf(NAAM, STRAAT, HUISNUMMER, POSTCODE, STAD, LAND, TELEFOONNUMMER, LOGO_FILENAME);

		Mockito.verify(bedrijfRepoMock).insert(bedrijf);
	}

	@Test
	void getBedrijfByIdTest()
	{
		Bedrijf bedrijf = new Bedrijf(NAAM, STRAAT, HUISNUMMER, POSTCODE, STAD, LAND, TELEFOONNUMMER, LOGO_FILENAME);

		Mockito.when(bedrijfRepoMock.get(Long.valueOf(0))).thenReturn(bedrijf);

		Bedrijf bedrijf2 = bedrijfService.getBedrijfById(0);

		Assertions.assertEquals(bedrijf.getNaam(), bedrijf2.getNaam());
		Assertions.assertEquals(bedrijf.getStraat(), bedrijf2.getStraat());
		Assertions.assertEquals(bedrijf.getHuisnummer(), bedrijf2.getHuisnummer());
		Assertions.assertEquals(bedrijf.getPostcode(), bedrijf2.getPostcode());
		Assertions.assertEquals(bedrijf.getStad(), bedrijf2.getStad());
		Assertions.assertEquals(bedrijf.getLand(), bedrijf2.getLand());
		Assertions.assertEquals(bedrijf.getTelefoonnummer(), bedrijf2.getTelefoonnummer());
		Assertions.assertEquals(bedrijf.getLogo_filename(), bedrijf2.getLogo_filename());

		Mockito.verify(bedrijfRepoMock).get(Long.valueOf(0));
	}

	private static Stream<Arguments> provideListOfObjectArrays()
	{
		return Stream.of(Arguments.of(new KlantEnAantalBestellingen(new Bedrijf("Bedrijf 1", STRAAT, HUISNUMMER, POSTCODE, STAD, LAND, TELEFOONNUMMER, LOGO_FILENAME), 2L ),
				new KlantEnAantalBestellingen(new Bedrijf("Bedrijf 2", STRAAT, HUISNUMMER, POSTCODE, STAD, LAND, TELEFOONNUMMER, LOGO_FILENAME),
						5L),
				new KlantEnAantalBestellingen(new Bedrijf("Bedrijf 3", STRAAT, HUISNUMMER, POSTCODE, STAD, LAND, TELEFOONNUMMER, LOGO_FILENAME),
						1L)));
	}

	@ParameterizedTest
	@MethodSource("provideListOfObjectArrays")
	public void testGetListOfClientNamesWithNumberOfOpenOrders(KlantEnAantalBestellingen obj1, KlantEnAantalBestellingen obj2, KlantEnAantalBestellingen obj3)
	{
		List<KlantEnAantalBestellingen> objectList = new ArrayList<>();
		objectList.add(obj1);
		objectList.add(obj2);
		objectList.add(obj3);

		// mock bedrijfRepo.findCustomersWithOrdersWithSpecificStatus()
		Mockito.when(bedrijfRepoMock.findCustomersWithOrdersWithSpecificStatus(BEDRIJFSID, BestellingStatus.GEPLAATST))
				.thenReturn(objectList);

		// expected results
		KlantLijstEntryDTO dto1 = new KlantLijstEntryDTO(obj1);
		KlantLijstEntryDTO dto2 = new KlantLijstEntryDTO(obj2);
		KlantLijstEntryDTO dto3 = new KlantLijstEntryDTO(obj3);
		List<KlantLijstEntryDTO> expectedList = List.of(dto1, dto2, dto3);

		// act
		List<KlantLijstEntryDTO> actualList = bedrijfService.getListOfClientNamesWithNumberOfOpenOrders(BEDRIJFSID);

		// assert
		for(int i = 0; i < expectedList.size(); i++) {
			Assertions.assertEquals(expectedList.get(i).getKlantId(), actualList.get(i).getKlantId());
			Assertions.assertEquals(expectedList.get(i).getKlantNaam(), actualList.get(i).getKlantNaam());
			Assertions.assertEquals(expectedList.get(i).getAantalOpenBestellingen().intValue(), actualList.get(i).getAantalOpenBestellingen().intValue());
		}
		
		Mockito.verify(bedrijfRepoMock).findCustomersWithOrdersWithSpecificStatus(BEDRIJFSID, BestellingStatus.GEPLAATST);
	}

}
