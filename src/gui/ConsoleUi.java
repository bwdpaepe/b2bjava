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
            domeinController.maakMedewerker("Joachim", "Dauchot", "emailail", "paswoord", "admin", 0);;
        } catch (IllegalArgumentException ex) {
            System.out.println("Operatie mislukt " + ex.getMessage());
        }
    }
    
}
