package gui;

import java.util.Arrays;

import domein.DomeinController;
import repository.DienstDTO;
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
           	
        	domeinController.maakBedrijf("Bedrijf A", "Straat A", "A1", "1234A", "stad A", "land A", "0123456789", "logo_bedrijf_A");
        	domeinController.maakBedrijf("Bedrijf B", "Straat B", "B2", "4321B", "stad B", "land B", "9876543210", "logo_bedrijf_B");
        	domeinController.maakBedrijf("Bedrijf C", "Straat C", "C3", "9876C", "stad C", "land C", "1234567", "logo_bedrijf_C");
        	
        	// Medewerkers worden aangemaakt voor 1 van de bovenstaande bedrijven
            domeinController.maakMedewerker("Joachim2", "Dauchot", "emailail1@test.com", "paswoord","Adres adres adres1", "047563541854", "admin",5, 1);;
            domeinController.maakMedewerker("Dimitri_2", "Valckenier", "emailail2@test.test.com", "paswoord","Adres adres adres2","+47565442854", "admin", 1, 1);;
            domeinController.maakMedewerker("Jorgen", "Scheerens", "emailail3@test.be", "paswoord","Adres adres adres3","047565442854", "admin", 2, 1);;
            domeinController.maakMedewerker("Bart", "De Paepe", "emailail4.bart@test.com", "paswoord","Adres adres adres4","047565442854", "admin", 3, 2);;
            domeinController.maakMedewerker("Ian", "Daelman", "emailail5@test.com", "paswoord","Adres adres adres5","047565442854", "admin", 4, 3);;
            
            UserDTO u = domeinController.aanmelden( "emailail1@test.com", "paswoord");
            
            System.out.println(u);
            
            domeinController.updateMedewerker("emailail3@test.be", "magazijnier");
                   
            
            domeinController.maakTransportdienst("trans1", 5, false, "trans1_prefix", "postcode", Arrays.asList("cVN"), Arrays.asList("cFN"), Arrays.asList("01234567"), Arrays.asList("contact1@test.com"));
            domeinController.wijzigActivatieDienst(1, false);
            
            DienstDTO d = domeinController.getDienst(1);
            
            System.out.println(d);
            
        } catch (IllegalArgumentException ex) {
            System.out.println("Operatie mislukt " + ex.getMessage());
        }
    }
}
