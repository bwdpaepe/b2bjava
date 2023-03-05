package testen;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import domein.Bedrijf;
import repository.GenericDao;
import service.BedrijfService;

@ExtendWith(MockitoExtension.class)
public class BedrijfServiceTest {
	
	@Mock
	private GenericDao<Bedrijf> bedrijfRepoMock;
	
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
	
	@Test
	void maakBedrijfTest() {
		Bedrijf bedrijf  = new Bedrijf(NAAM, STRAAT, HUISNUMMER,POSTCODE,STAD,LAND,TELEFOONNUMMER,LOGO_FILENAME);
		Mockito.doNothing().when(bedrijfRepoMock).insert(bedrijf);
		
		bedrijfService.maakBedrijf(NAAM, STRAAT, HUISNUMMER, POSTCODE, STAD, LAND, TELEFOONNUMMER, LOGO_FILENAME);
		
		Mockito.verify(bedrijfRepoMock).insert(bedrijf);
	}
	
	@Test
	void getBedrijfByIdTest() {
		Bedrijf bedrijf  = new Bedrijf(NAAM, STRAAT, HUISNUMMER,POSTCODE,STAD,LAND,TELEFOONNUMMER,LOGO_FILENAME);
		
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

}
