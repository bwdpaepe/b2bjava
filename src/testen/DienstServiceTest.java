package testen;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import domein.Bedrijf;
import domein.Contactpersoon;
import domein.Dienst;
import domein.TrackTraceFormat;
import domein.Transportdienst;
import repository.GenericDao;
import service.DienstService;

@ExtendWith(MockitoExtension.class)
class DienstServiceTest {
	
	@Mock
	private GenericDao<Dienst> dienstRepoMock;
	
	@Mock
	private Bedrijf bedrijf;
	
	@InjectMocks
	private DienstService dienstService;
	
	private final String NAAMTRANSPORTDIENT = "Transportdienst_A";
	
	private final int BARCODELENGTE = 10;
	private final boolean ISBARCODEENKELCIJFERS = true;
	private final String BARCODEPREFIX = "123";
	private final String VERIFICATIECODE = "POSTCODE";
	private final TrackTraceFormat TTF = new TrackTraceFormat(BARCODELENGTE, ISBARCODEENKELCIJFERS, BARCODEPREFIX, VERIFICATIECODE);
	
	private final String CONTACTVOORNAAM = "TRANS";
	private final String CONTACTFAMILIENAAM = "PORT";
	private final String CONTACTTELEFOON = "1234567890";
	private final String CONTACTEMAILADRES = "trans.port@example.com";
	private final Contactpersoon CONTACTPERSOON = new Contactpersoon(CONTACTVOORNAAM, CONTACTFAMILIENAAM, CONTACTEMAILADRES, CONTACTTELEFOON);
	
	private static final String NAAMBEDRIJF = "Bedrijf A";
	private static final String STRAAT = "Straat A";
	private static final String HUISNUMMER = "A1";
	private static final String POSTCODE = "1234A";
	private static final String STAD = " stad A";
	private static final String LAND = "land A";
	private static final String TELEFOONNUMMER = "0123456789";
	private static final String LOGO_FILENAME = "logog_bedrijf_A";
	private final Bedrijf BEDRIJF = new Bedrijf(NAAMBEDRIJF, STRAAT, HUISNUMMER,POSTCODE,STAD,LAND,TELEFOONNUMMER,LOGO_FILENAME);

	@Test
	void testMaakTransportdienst() {
		Transportdienst td  = new Transportdienst(NAAMTRANSPORTDIENT, BEDRIJF, CONTACTPERSOON, TTF);
		Mockito.doNothing().when(dienstRepoMock).insert(td);
		
		dienstService.maakTransportdienst(NAAMTRANSPORTDIENT, BARCODELENGTE, ISBARCODEENKELCIJFERS, BARCODEPREFIX, VERIFICATIECODE, CONTACTVOORNAAM, CONTACTFAMILIENAAM, CONTACTTELEFOON, CONTACTEMAILADRES, BEDRIJF);
		
		Mockito.verify(dienstRepoMock).insert(td);
	}

	@Test
	void testWijzigActivatieDienst() {
		Transportdienst td_actief  = new Transportdienst(NAAMTRANSPORTDIENT, BEDRIJF, CONTACTPERSOON, TTF);
		Transportdienst td_nietActief  = new Transportdienst(NAAMTRANSPORTDIENT, BEDRIJF, CONTACTPERSOON, TTF);
		td_nietActief.setActief(false);
		Mockito.when(dienstRepoMock.get(Long.valueOf(0))).thenReturn(td_actief);
		Mockito.when(dienstRepoMock.update(td_actief)).thenReturn(td_nietActief);
		
		dienstService.wijzigActivatieDienst(0, false);
		Assertions.assertFalse(dienstService.getTransportdienstByID(0).isActief());
		
		Mockito.verify(dienstRepoMock, Mockito.times(2)).get(Long.valueOf(0));
		Mockito.verify(dienstRepoMock).update(td_actief);
	}

	@Test
	void testGetTransportdienstByID() {
		Transportdienst td  = new Transportdienst(NAAMTRANSPORTDIENT, BEDRIJF, CONTACTPERSOON, TTF);
		Mockito.when(dienstRepoMock.get(Long.valueOf(0))).thenReturn(td);
		
		Transportdienst td2 = dienstService.getTransportdienstByID(0);
		Assertions.assertEquals(td.getNaam(), td2.getNaam());
		Assertions.assertEquals(td.isActief(), td2.isActief());
		Assertions.assertEquals(td.getBedrijf(), td2.getBedrijf());
		Assertions.assertEquals(td.getContactpersonen(), td2.getContactpersonen());
		Assertions.assertEquals(td.getTrackTraceFormat(), td2.getTrackTraceFormat());
		
		Mockito.verify(dienstRepoMock).get(Long.valueOf(0));
		
	}

	

}
