package testen;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.junit.jupiter.MockitoExtension;

import domein.Contactpersoon;
import domein.Persoon;
import repository.GenericDao;
import service.PersoonService;

@ExtendWith(MockitoExtension.class)
class PersoonServiceTest {

	@Mock
	private GenericDao<Persoon> persoonRepoMock;

	@InjectMocks
	private PersoonService persoonService;

	private static final String VOORNAAM = "John";
	private static final String FAMILIENAAM = "Doe";
	private static final String EMAILADRES = "test@test.com";
	private static final String TELEFOONNUMMER = "+12345678";

	@Test
	void maakPersoonTest() {
		Persoon persoon = new Contactpersoon(VOORNAAM, FAMILIENAAM, EMAILADRES, TELEFOONNUMMER);

		Mockito.doNothing().when(persoonRepoMock).insert(persoon);

		persoonService.maakPersoon(VOORNAAM, FAMILIENAAM, FAMILIENAAM, EMAILADRES);

		Mockito.verify(persoonRepoMock).insert(persoon);
	}

	@Test
	void updatePersoonTest() {

		// TODO indien de return type verandert van void naar Persoon kan je de check
		// doen die nog in commentaar staat

		Persoon persoon = new Contactpersoon(VOORNAAM, FAMILIENAAM, EMAILADRES, TELEFOONNUMMER);
		Persoon updatePersoon = new Contactpersoon("Test", FAMILIENAAM, EMAILADRES, TELEFOONNUMMER);
		List<Persoon> personen = new ArrayList();
		personen.add(persoon);

		Mockito.when(persoonRepoMock.findAll()).thenReturn(personen);
		// Mockito.doNothing().when(persoonRepoMock).update(updatePersoon);

		Mockito.when(persoonRepoMock.update(updatePersoon)).thenReturn(updatePersoon);

		persoonService.updatePersoon(VOORNAAM, FAMILIENAAM, TELEFOONNUMMER, EMAILADRES);

		/*
		 * if(updatePersoon instanceof Contactpersoon) {
		 * Assertions.assertEquals(persoonItem.getVoornaam(), VOORNAAM);
		 * Assertions.assertEquals(persoonItem.getFamilienaam(), FAMILIENAAM);
		 * Assertions.assertEquals(persoonItem.getEmailAdress(), EMAILADRES);
		 * Assertions.assertEquals(persoonItem.getTelefoonnummer(), TELEFOONNUMMER); }
		 */

		Mockito.verify(persoonRepoMock).update(persoon);

	}

	@Test
	void verwijderPersoonTest() {

		Persoon persoon = new Contactpersoon(VOORNAAM, FAMILIENAAM, EMAILADRES, TELEFOONNUMMER);
		List<Persoon> personen = new ArrayList();
		personen.add(persoon);

		Mockito.when(persoonRepoMock.findAll()).thenReturn(personen);
		Mockito.doNothing().when(persoonRepoMock).delete(persoon);

		persoonService.verwijderPersoon(EMAILADRES);

		Mockito.verify(persoonRepoMock).delete(persoon);

	}
	
	//TODO Test schrijven voor updaten personen

	@Test
	void getPersoonListTest() {

		Persoon persoon = new Contactpersoon(VOORNAAM, FAMILIENAAM, EMAILADRES, TELEFOONNUMMER);
		List<Persoon> personen = new ArrayList();
		personen.add(persoon);

		Mockito.when(persoonRepoMock.findAll()).thenReturn(personen);

		List<Persoon> personenList = persoonService.getPersoonList();

		for (Persoon persoonItem : personenList) {
			if (persoonItem instanceof Contactpersoon) {
				Assertions.assertEquals(persoonItem.getVoornaam(), VOORNAAM);
				Assertions.assertEquals(persoonItem.getFamilienaam(), FAMILIENAAM);
				Assertions.assertEquals(persoonItem.getEmailAdress(), EMAILADRES);
				Assertions.assertEquals(persoonItem.getTelefoonnummer(), TELEFOONNUMMER);
			}

		}

		Mockito.verify(persoonRepoMock).findAll();

	}

}
