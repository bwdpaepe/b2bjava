package main;

import domein.DomeinController;
import gui.AanmeldenController;
import gui.MasterController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class StartupGUI extends Application {

	private DomeinController dc;

	@Override
	public void start(Stage primaryStage) {
		try {
			run();

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Aanmelden.fxml"));
			AanmeldenController ac = new AanmeldenController(dc);
			loader.setController(ac);
			Parent root = loader.load();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
			
			/* FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Master.fxml"));
			BorderPane bp = loader.<BorderPane>load();
			MasterController mc = loader.getController();
			mc.setParams(dc);
			Scene scene = new Scene(bp);
			primaryStage.setScene(scene);
			primaryStage.show(); */

			

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

		launch(args);

	}

	private void run() {	
		dc = new DomeinController(true); // boolean om al dan niet database te seeden
	}
}
