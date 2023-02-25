package gui;

import domein.DomeinController;

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
            domeinController.maakMedewerker("Joachim", "Dauchot", "emailail1@test.com", "paswoord", "MagaZijNieR", 5);;
            domeinController.maakMedewerker("Dimitri", "Valckenier", "emailail2@test.test.com", "paswoord", "admin", 1);;
            domeinController.maakMedewerker("Jorgen", "Scheerens", "emailail3@test.be", "paswoord", "admin", 2);;
            domeinController.maakMedewerker("Bart", "De Paepe", "emailail4.bart@test.com", "paswoord", "admin", 3);;
            domeinController.maakMedewerker("Ian", "Daelman", "emailail5@test.com", "paswoord", "admin", 4);;

            
        } catch (IllegalArgumentException ex) {
            System.out.println("Operatie mislukt " + ex.getMessage());
        }
    }
    
}
