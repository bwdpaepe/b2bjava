package gui;

import domein.DomeinController;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import repository.UserDTO;

public class HomeController {
	
	// nog toolbar aan te maken zodat deze over alle schermen heen kan hergebruikt worden
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	private final DomeinController dc;
	private final UserDTO user;
	
	public HomeController(DomeinController dc, UserDTO user) {
		this.dc = dc;
		this.user = user;
	}
}
