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
            domeinController.maakMedewerker("Joachim", "Dauchot", "emailail1", "paswoord", "MagaZijNieR", 0);;
            domeinController.maakMedewerker("Joachim", "Dauchot", "emailail2", "paswoord", "admin", 1);;
            domeinController.maakMedewerker("Joachim", "Dauchot", "emailail3", "paswoord", "admin", 2);;
            domeinController.maakMedewerker("Joachim", "Dauchot", "emailail4", "paswoord", "admin", 3);;
            domeinController.maakMedewerker("Joachim", "Dauchot", "emailail5", "paswoord", "admin", 4);;
        } catch (IllegalArgumentException ex) {
            System.out.println("Operatie mislukt " + ex.getMessage());
        }
    }
    
}
