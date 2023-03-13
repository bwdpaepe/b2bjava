package repository;

import java.util.Date;
import java.util.Random;

import domein.DomeinController;

public class DatabaseSeeding
{
	public static final void startDatabaseSeed(DomeinController dc)
	{
		DomeinController domeinController = dc;

		try
		{

			// bedrijven
			domeinController.maakBedrijf("Bedrijf Alfa", "Straat A", "A1", "1234A", "stad A", "land A", "0123456789",
					"logo_bedrijf_A");
			domeinController.maakBedrijf("Bedrijf Beta", "Straat B", "B2", "4321B", "stad B", "land B", "9876543210",
					"logo_bedrijf_B");
			domeinController.maakBedrijf("Bedrijf Charlie", "Straat C", "C3", "9876C", "stad C", "land C", "1234567",
					"logo_bedrijf_C");
			domeinController.maakBedrijf("Bedrijf Delta", "Straat D", "D4", "9876D", "stad D", "land D", "1234567",
					"logo_bedrijf_D");
			domeinController.maakBedrijf("Bedrijf Echo", "Straat E", "E5", "9876E", "stad E", "land E", "1234567",
					"logo_bedrijf_E");

			// Dozen
			for (int i = 1; i <= 5; i++) {
			    for (int j = 1; j <= 10; j++) {
			        String naam = "doos_" + j;
			        double hoogte = Math.round((Math.random() * 10 + 1) * 100.0) / 100.0;
			        double breedte = Math.round((Math.random() * 10 + 1) * 100.0) / 100.0;
			        double lengte = Math.round((Math.random() * 10 + 1) * 100.0) / 100.0;
			        double prijs = Math.round((Math.random() * 100 + 1) * 100.0) / 100.0;
			        String doosType = "standaard";
			        if (j % 2 == 0) {
			            doosType = "custom";
			        }
			        domeinController.maakDoos(i, naam, doosType, hoogte, breedte, lengte, prijs);
			    }
			}
			
			// Producten
			for (int i = 1; i <= 5; i++) {
			    for (int j = 1; j <= 20; j++) {
			        String naam = "product_" + j;
			        double prijs = Math.round((Math.random() * 100 + 1) * 100.0) / 100.0;
			        domeinController.maakProduct(i, naam, prijs);
			    }
			}
			
			// Medewerkers
			domeinController.maakMedewerker("Joachim2", "Dauchot", "emailail1@test.com", "paswoord",
					"Adres adres adres1", "047563541854", "admin", 5, 1);
			domeinController.maakMedewerker("Dimitri_2", "Valckenier", "emailail2@test.test.com", "paswoord",
					"Adres adres adres2", "+47565442854", "admin", 1, 1);
			domeinController.maakMedewerker("Jorgen", "Scheerens", "emailail3@test.be", "paswoord",
					"Adres adres adres3", "047565442854", "admin", 2, 1);
			domeinController.maakMedewerker("Bart", "De Paepe", "emailail4.bart@test.com", "paswoord",
					"Adres adres adres4", "047565442854", "admin", 3, 2);
			domeinController.maakMedewerker("Ian", "Daelman", "emailail5@test.com", "paswoord", "Adres adres adres5",
					"047565442854", "admin", 4, 3);
			domeinController.maakMedewerker("A.", "De Aankoper", "aankoper@test.com", "paswoord", "Adres adres adres5", "047565442854", "aankoper", 6, 1);

			// transportdienst
			domeinController.maakTransportdienst("Post NL", 4, true, "2", "POSTCODE", "jos", "josinson", "0478559874",
					"email@test.fr", 1);
			domeinController.maakTransportdienst("DHL", 2, true, "1", "POSTCODE", "jos", "josinson", "0478559874",
					"email@test.hk", 3);
			domeinController.maakTransportdienst("GLS", 8, true, "6", "POSTCODE", "jos", "josinson", "0478559874",
					"email@test.uk", 2);			
			

			// Bestellingen
			
			Random random = new Random();
			for (int i = 1; i <= 100; i++) {
			    String orderId = "Order" + i;
			    String status = random.nextBoolean() ? "geplaatst" : "verwerkt";
			    Date datum = new Date();
			    long leverancierID = random.nextLong(5) + 1;
			    long klantID = random.nextLong(5) + 1;
			    while (klantID == leverancierID) {
			    	klantID = random.nextLong(5) + 1;
			    }
			    domeinController.maakBestelling(orderId, status, datum, leverancierID, klantID, 1, 6, "leveradresStraat", "leveradresNummer", "leveradresPostcode", "leveradresStad", "leveradresLand");
			}
			/*
			domeinController.maakBestelling("Order1", "VErweRkT", new Date(), 1, 2, 1);
			domeinController.maakBestelling("Order2", "geplaatst", new Date(), 1, 3, 2);
			domeinController.maakBestelling("Order3", "GEPLAATST", new Date(), 1, 2, 3);
			domeinController.maakBestelling("Order4", "verwerkt", new Date(), 2, 3, 2);
			domeinController.maakBestelling("Order5", "GEPLAATST", new Date(), 2, 2, 1);
			domeinController.maakBestelling("Order6", "VErweRkT", new Date(), 2, 5, 3);
			domeinController.maakBestelling("Order7", "geplaatst", new Date(), 3, 4, 1);
			domeinController.maakBestelling("Order8", "GEPLAATST", new Date(), 1, 3, 3);
			domeinController.maakBestelling("Order1", "VErweRkT", new Date(), 1, 2, 1);
			domeinController.maakBestelling("Order2", "geplaatst", new Date(), 1, 3, 2);
			domeinController.maakBestelling("Order3", "GEPLAATST", new Date(), 1, 2, 3);
			domeinController.maakBestelling("Order4", "verwerkt", new Date(), 2, 3, 2);
			domeinController.maakBestelling("Order5", "GEPLAATST", new Date(), 2, 2, 1);
			domeinController.maakBestelling("Order6", "VErweRkT", new Date(), 2, 5, 3);
			domeinController.maakBestelling("Order7", "geplaatst", new Date(), 3, 4, 1);
			domeinController.maakBestelling("Order8", "GEPLAATST", new Date(), 1, 3, 3);
			domeinController.maakBestelling("Order1", "VErweRkT", new Date(), 1, 2, 1);
			domeinController.maakBestelling("Order2", "geplaatst", new Date(), 1, 3, 2);
			domeinController.maakBestelling("Order3", "GEPLAATST", new Date(), 1, 2, 3);
			domeinController.maakBestelling("Order4", "verwerkt", new Date(), 2, 3, 2);
			domeinController.maakBestelling("Order5", "GEPLAATST", new Date(), 2, 2, 1);
			domeinController.maakBestelling("Order6", "VErweRkT", new Date(), 2, 5, 3);
			domeinController.maakBestelling("Order7", "geplaatst", new Date(), 3, 4, 1);
			domeinController.maakBestelling("Order8", "GEPLAATST", new Date(), 1, 3, 3);
			domeinController.maakBestelling("Order1", "VErweRkT", new Date(), 1, 2, 1);
			domeinController.maakBestelling("Order2", "geplaatst", new Date(), 1, 3, 2);
			domeinController.maakBestelling("Order3", "GEPLAATST", new Date(), 1, 2, 3);
			domeinController.maakBestelling("Order4", "verwerkt", new Date(), 2, 3, 2);
			domeinController.maakBestelling("Order5", "GEPLAATST", new Date(), 2, 2, 1);
			domeinController.maakBestelling("Order6", "VErweRkT", new Date(), 2, 5, 3);
			domeinController.maakBestelling("Order7", "geplaatst", new Date(), 3, 4, 1);
			domeinController.maakBestelling("Order8", "GEPLAATST", new Date(), 1, 3, 3);
			domeinController.maakBestelling("Order1", "VErweRkT", new Date(), 1, 2, 1);
			domeinController.maakBestelling("Order2", "geplaatst", new Date(), 1, 3, 2);
			domeinController.maakBestelling("Order3", "GEPLAATST", new Date(), 1, 2, 3);
			domeinController.maakBestelling("Order4", "verwerkt", new Date(), 2, 3, 2);
			domeinController.maakBestelling("Order5", "GEPLAATST", new Date(), 2, 2, 1);
			domeinController.maakBestelling("Order6", "VErweRkT", new Date(), 2, 5, 3);
			domeinController.maakBestelling("Order7", "geplaatst", new Date(), 3, 4, 1);
			domeinController.maakBestelling("Order8", "GEPLAATST", new Date(), 1, 3, 3);
			domeinController.maakBestelling("Order1", "VErweRkT", new Date(), 1, 2, 1);
			domeinController.maakBestelling("Order2", "geplaatst", new Date(), 1, 3, 2);
			domeinController.maakBestelling("Order3", "GEPLAATST", new Date(), 1, 2, 3);
			domeinController.maakBestelling("Order4", "verwerkt", new Date(), 2, 3, 2);
			domeinController.maakBestelling("Order5", "GEPLAATST", new Date(), 2, 2, 1);
			domeinController.maakBestelling("Order6", "VErweRkT", new Date(), 2, 5, 3);
			domeinController.maakBestelling("Order7", "geplaatst", new Date(), 3, 4, 1);
			domeinController.maakBestelling("Order8", "GEPLAATST", new Date(), 1, 3, 3);
			domeinController.maakBestelling("Order1", "VErweRkT", new Date(), 1, 2, 1);
			domeinController.maakBestelling("Order2", "geplaatst", new Date(), 1, 3, 2);
			domeinController.maakBestelling("Order3", "GEPLAATST", new Date(), 1, 2, 3);
			domeinController.maakBestelling("Order4", "verwerkt", new Date(), 2, 3, 2);
			domeinController.maakBestelling("Order5", "GEPLAATST", new Date(), 2, 2, 1);
			domeinController.maakBestelling("Order6", "VErweRkT", new Date(), 2, 5, 3);
			domeinController.maakBestelling("Order7", "geplaatst", new Date(), 3, 4, 1);
			domeinController.maakBestelling("Order8", "GEPLAATST", new Date(), 1, 3, 3);
			domeinController.maakBestelling("Order1", "VErweRkT", new Date(), 1, 2, 1);
			domeinController.maakBestelling("Order2", "geplaatst", new Date(), 1, 3, 2);
			domeinController.maakBestelling("Order3", "GEPLAATST", new Date(), 1, 2, 3);
			domeinController.maakBestelling("Order4", "verwerkt", new Date(), 2, 3, 2);
			domeinController.maakBestelling("Order5", "GEPLAATST", new Date(), 2, 2, 1);
			domeinController.maakBestelling("Order6", "VErweRkT", new Date(), 2, 5, 3);
			domeinController.maakBestelling("Order7", "geplaatst", new Date(), 3, 4, 1);
			domeinController.maakBestelling("Order8", "GEPLAATST", new Date(), 1, 3, 3);
			domeinController.maakBestelling("Order1", "VErweRkT", new Date(), 1, 2, 1);
			domeinController.maakBestelling("Order2", "geplaatst", new Date(), 1, 3, 2);
			domeinController.maakBestelling("Order3", "GEPLAATST", new Date(), 1, 2, 3);
			domeinController.maakBestelling("Order4", "verwerkt", new Date(), 2, 3, 2);
			domeinController.maakBestelling("Order5", "GEPLAATST", new Date(), 2, 2, 1);
			domeinController.maakBestelling("Order6", "VErweRkT", new Date(), 2, 5, 3);
			domeinController.maakBestelling("Order7", "geplaatst", new Date(), 3, 4, 1);
			domeinController.maakBestelling("Order8", "GEPLAATST", new Date(), 1, 3, 3);
			domeinController.maakBestelling("Order1", "VErweRkT", new Date(), 1, 2, 1);
			domeinController.maakBestelling("Order2", "geplaatst", new Date(), 1, 3, 2);
			domeinController.maakBestelling("Order3", "GEPLAATST", new Date(), 1, 2, 3);
			domeinController.maakBestelling("Order4", "verwerkt", new Date(), 2, 3, 2);
			domeinController.maakBestelling("Order5", "GEPLAATST", new Date(), 2, 2, 1);
			domeinController.maakBestelling("Order6", "VErweRkT", new Date(), 2, 5, 3);
			domeinController.maakBestelling("Order7", "geplaatst", new Date(), 3, 4, 1);
			domeinController.maakBestelling("Order8", "GEPLAATST", new Date(), 1, 3, 3);
			
			domeinController.maakBestelling("Order6", "VErweRkT", new Date(), 1, 5, 3);
			domeinController.maakBestelling("Order6", "VErweRkT", new Date(), 1, 5, 3);
			*/
			

			System.out.print("Database Seeded");


		} catch (IllegalArgumentException ex)
		{
			System.out.println("Operatie mislukt " + ex.getMessage());
		}

	}
}
