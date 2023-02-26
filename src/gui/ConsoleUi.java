package gui;

import java.io.IOException;

import domein.DomeinController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import repository.UserDTO;

//TESTKLASSE GUI

public class ConsoleUi extends Application {
    private final DomeinController domeinController = new DomeinController();
    /*public ConsoleUi(DomeinController dc) {
       domeinController = dc;
    }*/

    public void run() {
        doStandardJob();
        domeinController.close();
    }

    private void doStandardJob() {

        try {
            domeinController.maakMedewerker("Joachim2", "Dauchot", "emailail1@test.com", "paswoord","Adres adres adres1", "047563541854", "admin",5);;
            domeinController.maakMedewerker("Dimitri_2", "Valckenier", "emailail2@test.test.com", "paswoord","Adres adres adres2","+47565442854", "admin", 1);;
            domeinController.maakMedewerker("Jorgen", "Scheerens", "emailail3@test.be", "paswoord","Adres adres adres3","047565442854", "admin", 2);;
            domeinController.maakMedewerker("Bart", "De Paepe", "emailail4.bart@test.com", "paswoord","Adres adres adres4","047565442854", "admin", 3);;
            domeinController.maakMedewerker("Ian", "Daelman", "emailail5@test.com", "paswoord","Adres adres adres5","047565442854", "admin", 4);;
            
            UserDTO u = domeinController.aanmelden( "emailail1@test.com", "paswoord");
            
            System.out.print(u);
            
            domeinController.updateMedewerker("emailail3@test.be", "magazijnier");
            


            
        } catch (IllegalArgumentException ex) {
            System.out.println("Operatie mislukt " + ex.getMessage());
        }
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.run();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Aanmelden.fxml"));
			AanmeldenController ac = new AanmeldenController(this.domeinController);
			loader.setController(ac);
			Parent root = loader.load();
			Scene scene = new Scene (root);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
    
}
