package gui;

import domein.DomeinController;
import repository.UserDTO;

public class GraphicalUi {
	private final DomeinController domeinController;
    public GraphicalUi(DomeinController dc) {
       domeinController = dc;
    }

    public void run() {
        doStandardJob();
       
    }

    private void doStandardJob() {

        try {
            domeinController.maakMedewerker("Joachim2", "Dauchot", "emailail1@test.com", "paswoord","Adres adres adres1", "047563541854", "admin",5);;
            domeinController.maakMedewerker("Dimitri_2", "Valckenier", "emailail2@test.test.com", "paswoord","Adres adres adres2","+47565442854", "admin", 1);;
            domeinController.maakMedewerker("Jorgen", "Scheerens", "emailail3@test.be", "paswoord","Adres adres adres3","047565442854", "admin", 2);;
            domeinController.maakMedewerker("Bart", "De Paepe", "emailail4.bart@test.com", "paswoord","Adres adres adres4","047565442854", "admin", 3);;
            domeinController.maakMedewerker("Ian", "Daelman", "emailail5@test.com", "paswoord","Adres adres adres5","047565442854", "admin", 4);;
            
            /*UserDTO u = domeinController.aanmelden( "emailail1@test.com", "paswoord");
            
            System.out.print(u);*/
            
            domeinController.updateMedewerker("emailail3@test.be", "magazijnier");
                   
        } catch (IllegalArgumentException ex) {
            System.out.println("Operatie mislukt " + ex.getMessage());
        }
    }
}