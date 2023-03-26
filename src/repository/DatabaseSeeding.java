package repository;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.naming.SizeLimitExceededException;

import domein.DomeinController;
import service.UserService;

public class DatabaseSeeding
{
	private static final int AANTAL_BEDRIJVEN = 5;
	private static final int AANTAL_BESTELLINGEN = 100;
	private static final int  AANTAL_PRODUCTEN_PER_LEVERANCIER = 20;
	final private static int TRACKTRACECODELENGTE = 30;
	
	public static final void startDatabaseSeed(DomeinController dc)
	{
		System.out.println("Database seeding started, please wait...");
		
		DomeinController domeinController = dc;
		UserService us = new UserService();

		try
		{

			// bedrijven
			domeinController.maakBedrijf("Bedrijf A", "Straat A", "A1", "1234A", "stad A", "land A", "0123456789",
					"1.jpg");
			domeinController.maakBedrijf("Bedrijf B", "Straat B", "B2", "4321B", "stad B", "land B", "9876543210",
					"2.jpg");
			domeinController.maakBedrijf("Bedrijf C", "Straat C", "C3", "9876C", "stad C", "land C", "1234567",
					"3.jpg");
			domeinController.maakBedrijf("Bedrijf D", "Straat D", "D4", "9876D", "stad D", "land D", "1234567",
					"4.jpg");
			domeinController.maakBedrijf("Bedrijf E", "Straat E", "E5", "9876E", "stad E", "land E", "1234567",
					"5.jpg");
			
			// Medewerkers
			us.maakMedewerkerDatabaseSeeder("Joachim2", "Dauchot", "emailail1@test.com", "paswoord",
					"Adres adres adres1", "047563541854", "admin", 1L);
			us.maakMedewerkerDatabaseSeeder("Dimitri_2", "Valckenier", "emailail2@test.test.com", "paswoord",
					"Adres adres adres2", "+47565442854", "admin",  1L);
			us.maakMedewerkerDatabaseSeeder("Jorgen", "Scheerens", "emailail3@test.be", "paswoord",
					"Adres adres adres3", "047565442854", "admin",  1L);
			us.maakMedewerkerDatabaseSeeder("Bart", "De Paepe", "emailail4.bart@test.com", "paswoord",
					"Adres adres adres4", "047565442854", "admin",  2L);
			us.maakMedewerkerDatabaseSeeder("Ian", "Daelman", "emailail5@test.com", "paswoord", "Adres adres adres5",
					"047565442854", "admin",  3L);
			us.maakMedewerkerDatabaseSeeder("A1.", "De Aankoper", "aankoperA1@test.com", "paswoord", "Adres adres adres5", "047565442854", "aankoper",  1L);
			us.maakMedewerkerDatabaseSeeder("B1.", "De Aankoper", "aankoperB1@test.com", "paswoord", "Adres adres adres2", "047565442852", "aankoper",  2L);
			us.maakMedewerkerDatabaseSeeder("C1.", "De Aankoper", "aankoperC1@test.com", "paswoord", "Adres adres adres3", "047565442853", "aankoper",  3L);
			us.maakMedewerkerDatabaseSeeder("D1.", "De Aankoper", "aankoperD1@test.com", "paswoord", "Adres adres adres4", "047565442854", "aankoper",  4L);
			us.maakMedewerkerDatabaseSeeder("E1.", "De Aankoper", "aankoperE1@test.com", "paswoord", "Adres adres adres5", "047565442855", "aankoper",  5L);
			us.maakMedewerkerDatabaseSeeder("M1_A.", "De Magazijnier", "mag1@test.com", "paswoord", "Adres adres adres5", "047565442854", "magazijnier",  1L);
			us.maakMedewerkerDatabaseSeeder("B2.", "De Aankoper", "aankoper2@test.com", "paswoord", "Adres adres adres2", "047565442852", "aankoper",  2L);
			us.maakMedewerkerDatabaseSeeder("C2.", "De Aankoper", "aankoper3@test.com", "paswoord", "Adres adres adres3", "047565442853", "aankoper",  3L);
			us.maakMedewerkerDatabaseSeeder("D2.", "De Aankoper", "aankoper4@test.com", "paswoord", "Adres adres adres4", "047565442854", "aankoper",  4L);
			us.maakMedewerkerDatabaseSeeder("E2.", "De Aankoper", "aankoper5@test.com", "paswoord", "Adres adres adres5", "047565442855", "aankoper",  5L);
			us.maakMedewerkerDatabaseSeeder("B3.", "De Aankoper", "aankoperB3@test.com", "paswoord", "Adres adres adres2", "047565442852", "aankoper",  2L);
			us.maakMedewerkerDatabaseSeeder("C3.", "De Aankoper", "aankoperC3@test.com", "paswoord", "Adres adres adres3", "047565442853", "aankoper",  3L);
			us.maakMedewerkerDatabaseSeeder("D3.", "De Aankoper", "aankoperD3@test.com", "paswoord", "Adres adres adres4", "047565442854", "aankoper",  4L);
			us.maakMedewerkerDatabaseSeeder("M1_B.", "De Magazijnier", "mag2@test.com", "paswoord", "Adres adres adres5", "047565442854", "magazijnier",  2L);
			
			//aanmelden nodig voor ingelogdeUser related operations
			domeinController.aanmelden("emailail1@test.com", "paswoord");

			// Dozen
			for (int i = 1; i <= AANTAL_BEDRIJVEN; i++) {
			    for (int j = 1; j <= 10; j++) {
			        String naam = "doos_" + j + "_" + i;
			        double hoogte = Math.round((Math.random() * 10 + 1) * 100.0) / 100.0;
			        double breedte = Math.round((Math.random() * 10 + 1) * 100.0) / 100.0;
			        double lengte = Math.round((Math.random() * 10 + 1) * 100.0) / 100.0;
			        double prijs = Math.round((Math.random() * 100 + 1) * 100.0) / 100.0;
			        String doosType = "standaard";
			        if (j % 2 == 0) {
			            doosType = "custom";
			        }
			        domeinController.maakDoos(naam, doosType, hoogte, breedte, lengte, prijs);
			    }
			}

			
			// Producten
			for (int i = 1; i <= AANTAL_BEDRIJVEN; i++) {
			    for (int j = 1; j <= AANTAL_PRODUCTEN_PER_LEVERANCIER; j++) {
			        String naam = "product_" + j;
			        double prijs = Math.round((Math.random() * 10000 + 1)) / 100.0;
			        domeinController.maakProduct(i, naam, prijs);
			    }
			}
			
			// transportdienst
			domeinController.maakTransportdienst("Post FR", TRACKTRACECODELENGTE, true, "1", "POSTCODE", "jos", "josinson", "0478559871",
					"email@test.fr", 1);
			domeinController.maakTransportdienst("Post BE", TRACKTRACECODELENGTE, true, "2", "ORDERID", "jos", "josinson", "0478559872",
					"email@test.be", 1);
			domeinController.maakTransportdienst("Post DE", TRACKTRACECODELENGTE, true, "3", "POSTCODE", "jos", "josinson", "0478559873",
					"email@test.de", 1);
			domeinController.maakTransportdienst("Post NL", TRACKTRACECODELENGTE, true, "4", "POSTCODE", "jos", "josinson", "0478559874",
					"email@test.nl", 1);
			domeinController.maakTransportdienst("DHL", TRACKTRACECODELENGTE, true, "1", "POSTCODE", "jos", "josinson", "0478559874",
					"email@test.hk", 3);
			domeinController.maakTransportdienst("GLS", TRACKTRACECODELENGTE, true, "6", "POSTCODE", "jos", "josinson", "0478559874",
					"email@test.uk", 2);
			domeinController.maakTransportdienst("Post MM", TRACKTRACECODELENGTE, true, "2", "POSTCODE", "jos", "josinson", "0478559874",
					"email2@test.fr", 2);
			domeinController.maakTransportdienst("BHV", TRACKTRACECODELENGTE, true, "1", "POSTCODE", "jos", "josinson", "0478559874",
					"email2@test.hk", 1);
			domeinController.maakTransportdienst("ZOEF", TRACKTRACECODELENGTE, true, "6", "POSTCODE", "jos", "josinson", "0478559874",
					"email2@test.uk", 3);
			
			// contactpersonen transportdienst
			domeinController.addContactpersoon("Jan", "Metdepet", "0478559874", "test@jan.com", 1);
			domeinController.addContactpersoon("Karel", "Metdepet", "0478559874", "test@metdepet.com", 1);
			domeinController.addContactpersoon("Joseline", "Metdepet", "0478559874", "test@test.com", 1);
			domeinController.addContactpersoon("Franky", "Metdepet", "0478559874", "test@td.com", 1);
			

			// Bestellingen
			Random random = new Random();
			for (int i = 1; i <= AANTAL_BESTELLINGEN; i++) {
			    String orderId = "Order" + i;
			    Date datum = new Date();
			    long leverancierID = random.nextLong(5) + 1;
			    long klantID = random.nextLong(5) + 1;
			    while (klantID == leverancierID) {
			    	klantID = random.nextLong(5) + 1;
			    }
			    long doosId = random.nextLong(10) + 1;
			    domeinController.maakBestelling(orderId, datum, leverancierID, klantID, 1, 6, "leveradresStraat", "leveradresNummer", "leveradresPostcode", "leveradresStad", "leveradresLand", doosId);
			}
			
			// add BesteldProduct to bestelling
			// DomeinController.addProductenToBestelling(long bestellingId, long longProductId, int aantal)
			for (int i = 1; i <= AANTAL_BESTELLINGEN; i++) {
			    int aantalProductenPerBestelling = (int) (Math.random() * 5) + 2; // random number of products per bestelling between 2 and 5
			    for (int j = 1; j < aantalProductenPerBestelling; j++) {
			        int quantity = (int) (Math.random() * 100) + 1; // random quantity between 1 and 100
			        	domeinController.addProductenToBestelling(i, j, quantity); 
			    }
			}
			
			System.out.println("Database Seeded");
			
			dc.aanmelden("emailail1@test.com", "paswoord");
			System.out.println("user aangemeld\n");
			
			System.out.println("Voor update user 2:");
			System.out.println(dc.findMedewerkerById(2L));
			dc.updateMedewerker(2l, "test_vn", "test_an", "testemail@test.com", "test adres", "123456789", "aankoper", false);
			System.out.println("Na update user 2:");
			System.out.println(dc.findMedewerkerById(2L) + "\n");
			
			KlantAankopersBestellingenDTO kab = dc.geefDetailsVanKlant(2);
			System.out.println(kab);
			
			List<MedewerkerListEntryDTO> mwList = dc.findAllMedewerkersByBedrijfId();
			System.out.println(mwList);
			//System.out.println(mwList.size());
			
			//Aantal geplaatste bestellingen verwerken 
			List<BestellingDTO> bestellingen = dc.getBestellingen();
			for(BestellingDTO bestelling: bestellingen) {
				if(random.nextBoolean()) {
					try {
						dc.verwerkBestelling(bestelling.getId(), 1L);
					} catch (SizeLimitExceededException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}



		} catch (IllegalArgumentException ex)
		{
			System.out.println("Operatie mislukt " + ex.getMessage());
		}

	}
}
