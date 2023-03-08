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
import domein.Contactpersoon;
import domein.TrackTraceFormat;
import domein.Transportdienst;
import repository.DienstDaoJpa;
import service.DienstService;

@ExtendWith(MockitoExtension.class)
class DienstServiceTest {
	
	@Mock
	private DienstDaoJpa dienstRepoMock;
	
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
	
	private Transportdienst td;

	@BeforeEach
	void maakTransportdienst() {
		td  = new Transportdienst(NAAMTRANSPORTDIENT, BEDRIJF, CONTACTPERSOON, TTF);
	}
	
	@Test
	void testMaakTransportdienst() {
		
		Mockito.doNothing().when(dienstRepoMock).insert(td);
		
		dienstService.maakTransportdienst(NAAMTRANSPORTDIENT, BARCODELENGTE, ISBARCODEENKELCIJFERS, BARCODEPREFIX, VERIFICATIECODE, CONTACTVOORNAAM, CONTACTFAMILIENAAM, CONTACTTELEFOON, CONTACTEMAILADRES, BEDRIJF);
		
		Mockito.verify(dienstRepoMock).insert(td);
	}

	@Test
	void testWijzigActivatieDienst() {
		Transportdienst td_nietActief  = new Transportdienst(NAAMTRANSPORTDIENT, BEDRIJF, CONTACTPERSOON, TTF);
		td_nietActief.setActief(false);
		Mockito.when(dienstRepoMock.get(Long.valueOf(0))).thenReturn(td);
		Mockito.when(dienstRepoMock.update(td)).thenReturn(td_nietActief);
		
		dienstService.wijzigActivatieDienst(0, false);
		Assertions.assertFalse(dienstService.getTransportdienstByID(0).isActief());
		
		Mockito.verify(dienstRepoMock, Mockito.times(2)).get(Long.valueOf(0));
		Mockito.verify(dienstRepoMock).update(td);
	}

	@Test
	void testGetTransportdienstByID() {
		Mockito.when(dienstRepoMock.get(Long.valueOf(0))).thenReturn(td);
		
		Transportdienst td2 = dienstService.getTransportdienstByID(0);
		Assertions.assertEquals(td.getNaam(), td2.getNaam());
		Assertions.assertEquals(td.isActief(), td2.isActief());
		Assertions.assertEquals(td.getBedrijf(), td2.getBedrijf());
		Assertions.assertEquals(td.getContactpersonen(), td2.getContactpersonen());
		Assertions.assertEquals(td.getTrackTraceFormat(), td2.getTrackTraceFormat());
		
		Mockito.verify(dienstRepoMock).get(Long.valueOf(0));
		
	}
	
	@Test
	void testAddContactpersoon() {
		final String CONTACTVOORNAAM2 = "TRANS2";
		final String CONTACTFAMILIENAAM2 = "PORT2";
		final String CONTACTTELEFOON2 = "2345678901";
		final String CONTACTEMAILADRES2 = "trans2.port2@example.com";
		Contactpersoon CONTACTPERSOON2 = new Contactpersoon(CONTACTVOORNAAM2, CONTACTFAMILIENAAM2, CONTACTEMAILADRES2, CONTACTTELEFOON2);
		Transportdienst td2  = new Transportdienst(NAAMTRANSPORTDIENT, BEDRIJF, CONTACTPERSOON, TTF);
		td2.addPerson(CONTACTPERSOON2);
		
		Mockito.when(dienstRepoMock.get(Long.valueOf(0))).thenReturn(td);
		Mockito.when(dienstRepoMock.update(td)).thenReturn(td2);
		
		Assertions.assertDoesNotThrow(() -> dienstService.addContactpersoon(CONTACTVOORNAAM2, CONTACTFAMILIENAAM2, CONTACTTELEFOON2, CONTACTEMAILADRES2, 0));
		
		Mockito.verify(dienstRepoMock).get(Long.valueOf(0));
		Mockito.verify(dienstRepoMock).update(td);
	}
	
	@Test
	void testEditContactpersoon() {
		final String CONTACTVOORNAAM2 = "TRANS2";
		final String CONTACTFAMILIENAAM2 = "PORT2";
		final String CONTACTTELEFOON2 = "2345678901";
		final String CONTACTEMAILADRES2 = "trans2.port2@example.com";
		Contactpersoon CONTACTPERSOON2 = new Contactpersoon(CONTACTVOORNAAM2, CONTACTFAMILIENAAM2, CONTACTEMAILADRES2, CONTACTTELEFOON2);
		Transportdienst td2  = new Transportdienst(NAAMTRANSPORTDIENT, BEDRIJF, CONTACTPERSOON2, TTF);
		Mockito.when(dienstRepoMock.get(Long.valueOf(0))).thenReturn(td);
		Mockito.when(dienstRepoMock.update(td)).thenReturn(td2);
		
		Assertions.assertDoesNotThrow(() -> dienstService.editContactpersoon(CONTACTVOORNAAM2, CONTACTFAMILIENAAM2, CONTACTTELEFOON2, CONTACTEMAILADRES2, 0, 0));
		
		Mockito.verify(dienstRepoMock).get(Long.valueOf(0));
		Mockito.verify(dienstRepoMock).update(td);
	}
	
	@Test
	void testRemoveContactpersoon() {
		Transportdienst td2  = new Transportdienst(NAAMTRANSPORTDIENT, BEDRIJF, CONTACTPERSOON, TTF);
		td2.removePerson(CONTACTPERSOON);
		Mockito.when(dienstRepoMock.get(Long.valueOf(0))).thenReturn(td);
		Mockito.when(dienstRepoMock.update(td)).thenReturn(td2);
		
		Assertions.assertDoesNotThrow(() -> dienstService.removeContactpersoon(0, 0));
		
		Mockito.verify(dienstRepoMock).get(Long.valueOf(0));
		Mockito.verify(dienstRepoMock).update(td);
	}
	
	@Test
	void updateTransportdienst() {
		final String NAAMTRANSPORTDIENT2 = "UPDATE_TRANSPORTDIENST";
		final int BARCODELENGTE2 = 11;
		final boolean ISBARCODEENKELCIJFERS2 = false;
		final String BARCODEPREFIX2 = "456";
		final String VERIFICATIECODE2 = "ORDERID";
		final TrackTraceFormat TTF2 = new TrackTraceFormat(BARCODELENGTE2, ISBARCODEENKELCIJFERS2, BARCODEPREFIX2, VERIFICATIECODE2);
		
		Transportdienst td_updated  = new Transportdienst(NAAMTRANSPORTDIENT2, BEDRIJF, CONTACTPERSOON, TTF2);
		Mockito.when(dienstRepoMock.get(Long.valueOf(0))).thenReturn(td);
		Mockito.when(dienstRepoMock.update(td)).thenReturn(td_updated);
		
		Assertions.assertDoesNotThrow(() -> dienstService.updateTransportdienst(NAAMTRANSPORTDIENT2, BARCODELENGTE2, ISBARCODEENKELCIJFERS2, BARCODEPREFIX2, VERIFICATIECODE2, 0));
		
		Mockito.verify(dienstRepoMock).get(Long.valueOf(0));
		Mockito.verify(dienstRepoMock).update(td);
	}

	

}
