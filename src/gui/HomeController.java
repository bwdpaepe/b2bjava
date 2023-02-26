package gui;

import domein.DomeinController;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HomeController {
	
	// nog toolbar aan te maken zodat deze over alle schermen heen kan hergebruikt worden
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	private final DomeinController dc;
	
	public HomeController(DomeinController dc) {
		this.dc = dc;
	}
}
