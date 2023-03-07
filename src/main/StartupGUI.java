package main;

import domein.DomeinController;
import gui.AanmeldenController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartupGUI extends Application {

	private DomeinController dc = new DomeinController();

	@Override
	public void start(Stage primaryStage) {
		try {
			run();
			AanmeldenController ac = new AanmeldenController(dc);
			Scene scene = new Scene(ac);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

		launch(args);

	}

	private void run() {	
		new DomeinController(true); // boolean om al dan niet database te seeden
	}
}
