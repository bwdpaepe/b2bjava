package gui;

import domein.DomeinController;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import repository.UserDTO;

public class HomeController {
	
	// nog toolbar aan te maken zodat deze over alle schermen heen kan hergebruikt worden
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	private final DomeinController dc;
	private final UserDTO user;
	
	@FXML
	private Label welkomNaam; // nog voor elkaar krijgen dat voornaam + naam mooi verschijnen op homepagina
	
	public HomeController(DomeinController dc, UserDTO user) {
		this.dc = dc;
		this.user = user;
		
	}
	
	public void setWelkomTekst() {
		this.welkomNaam.setText(String.format("Welkom %s%s", user.getVoornaam(), user.getFamilienaam()));
	}
}
