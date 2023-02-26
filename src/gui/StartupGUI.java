package gui;

import java.io.IOException;

import domein.DomeinController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartupGUI extends Application{
	
	private DomeinController dc = new DomeinController();

	@Override
	public void start(Stage primaryStage) throws IOException {
		try {
			run();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Aanmelden.fxml"));
			AanmeldenController ac = new AanmeldenController(dc);
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
	
	private void run() {
        new GraphicalUi(dc).run();
        
    }
}
