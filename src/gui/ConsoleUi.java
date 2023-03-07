package gui;

import java.util.Date;
import java.util.List;

import domein.DomeinController;
import repository.BestellingDTO;
import repository.TransportdienstDTO;
import repository.UserDTO;

//TESTKLASSE GUI

public class ConsoleUi {
    private final DomeinController domeinController;
    public ConsoleUi(DomeinController dc) {
       domeinController = dc;
    }

    public void run() {
        doStandardJob();
        domeinController.close();
    }

    private void doStandardJob() {

        try {
           	
        	//bedrijven
        	domeinController.maakBedrijf("Bedrijf A", "Straat A", "A1", "1234A", "stad A", "land A", "0123456789", "logo_bedrijf_A");
        	domeinController.maakBedrijf("Bedrijf B", "Straat B", "B2", "4321B", "stad B", "land B", "9876543210", "logo_bedrijf_B");
        	domeinController.maakBedrijf("Bedrijf C", "Straat C", "C3", "9876C", "stad C", "land C", "1234567", "logo_bedrijf_C");
        	domeinController.maakBedrijf("Bedrijf D", "Straat D", "D4", "9876D", "stad D", "land D", "1234567", "logo_bedrijf_D");
        	domeinController.maakBedrijf("Bedrijf E", "Straat E", "E5", "9876E", "stad E", "land E", "1234567", "logo_bedrijf_E");
        	
        	
        	
        	// Medewerkers worden aangemaakt voor 1 van de bovenstaande bedrijven
            domeinController.maakMedewerker("Joachim2", "Dauchot", "emailail1@test.com", "paswoord","Adres adres adres1", "047563541854", "admin",5, 1);
            domeinController.maakMedewerker("Dimitri_2", "Valckenier", "emailail2@test.test.com", "paswoord","Adres adres adres2","+47565442854", "admin", 1, 1);
            domeinController.maakMedewerker("Jorgen", "Scheerens", "emailail3@test.be", "paswoord","Adres adres adres3","047565442854", "admin", 2, 1);
            domeinController.maakMedewerker("Bart", "De Paepe", "emailail4.bart@test.com", "paswoord","Adres adres adres4","047565442854", "admin", 3, 2);
            domeinController.maakMedewerker("Ian", "Daelman", "emailail5@test.com", "paswoord","Adres adres adres5","047565442854", "admin", 4, 3);
            
            UserDTO u = domeinController.aanmelden( "emailail1@test.com", "paswoord");            
            System.out.println(u);
            
            
            //transportdienst
            domeinController.maakTransportdienst("TD1", 4, true, "2", "POSTCODE", "jos", "josinson", "0478559874", "email@test.fr", 1);            
            TransportdienstDTO tdDTO = domeinController.getTransportdienst(1);
            System.out.println(tdDTO);
            
            //Bestellingen
            domeinController.maakBestelling("ORder1", "VErweRkT", new Date(2022,10,10), 1, 2, 1);
            domeinController.maakBestelling("ORder2", "geplaatst", new Date(2021,10,10), 1, 3, 1);
            domeinController.maakBestelling("ORder3", "GEPLAATST", new Date(2020,10,10), 1, 2, 1);
            domeinController.maakBestelling("ORder4", "verwerkt", new Date(2023,10,10), 1, 3, 1);
            domeinController.maakBestelling("ORder5", "GEPLAATST", new Date(2018,5,12), 1, 2, 1);
            domeinController.maakBestelling("ORder6", "VErweRkT", new Date(2018,5,12), 1, 5, 1);
            List<BestellingDTO> bestellingen = domeinController.getBestellingen();
            for (BestellingDTO bestellingDTO : bestellingen) {
            	System.out.println(bestellingDTO + "/n");
				
			}
            
            
            List<String[]> klantenVanLeverancier =  domeinController.geefLijstVanKlantenMetAantalOpenstaandeBestellingen();
            System.out.println("\nklanten van leverancier " + domeinController.getIngelogdeUser().getBedrijf().getNaam() + " met bedrijfsId " + String.valueOf(domeinController.getIngelogdeUser().getBedrijf().getId()));
            for (String[] strings : klantenVanLeverancier)
			{
				System.out.printf("  - Id klant: %s, Naam klant: %s, aantal open bestellingen, %s%n", strings[1], strings[0], strings[2]);
			}
            
            domeinController.updateMedewerker(3, "magazijnier");                  
            
            
        } catch (IllegalArgumentException ex) {
            System.out.println("Operatie mislukt " + ex.getMessage());
        }
    }
}
