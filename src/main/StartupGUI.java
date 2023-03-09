package main;

import domein.DomeinController;
import gui.AanmeldenController;
import gui.MasterController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class StartupGUI extends Application {

	private DomeinController dc = new DomeinController();

	@Override
	public void start(Stage primaryStage) {
		try {
			run();
<<<<<<< HEAD

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Master.fxml"));
			BorderPane bp = loader.<BorderPane>load();
			MasterController mc = loader.getController();
			mc.setParams(dc);
			Scene scene = new Scene(bp);
=======
			AanmeldenController ac = new AanmeldenController(dc);
			Scene scene = new Scene(ac);
>>>>>>> refs/heads/beheren_transportdienst_v2
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
		new DomeinController(false); // boolean om al dan niet database te seeden
	}
}
