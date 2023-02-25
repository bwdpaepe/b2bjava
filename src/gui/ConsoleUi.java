package gui;

import domein.DomeinController;
import repository.MedewerkerDTO;

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
            domeinController.maakMedewerker("Joachim2", "Dauchot", "emailail1@test.com", "paswoord","047563541854","Adres adres adres1", "admin", 5);;
            domeinController.maakMedewerker("Dimitri", "Valckenier", "emailail2@test.test.com", "paswoord","047565442854","Adres adres adres2", "admin", 1);;
            domeinController.maakMedewerker("Jorgen", "Scheerens", "emailail3@test.be", "paswoord","04756546854","Adres adres adres3", "admin", 2);;
            domeinController.maakMedewerker("Bart", "De Paepe", "emailail4.bart@test.com", "paswoord","04541342854","Adres adres adres4", "admin", 3);;
            domeinController.maakMedewerker("Ian", "Daelman", "emailail5@test.com", "paswoord","04756342864654","Adres adres adres5", "admin", 4);;
            
            MedewerkerDTO u = domeinController.aanmelden( "emailail1@test.com", "paswoord");
            
            System.out.print(u);
            
            domeinController.updateMedewerker("emailail3@test.be", "magazijnier");
            


            
        } catch (IllegalArgumentException ex) {
            System.out.println("Operatie mislukt " + ex.getMessage());
        }
    }
    
}
