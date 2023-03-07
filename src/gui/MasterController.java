package gui;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class MasterController extends Pane {

	public MasterController() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Master.fxml"));
		loader.setController(this);
		loader.setRoot(this);

		try {
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}
}
