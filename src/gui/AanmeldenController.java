package gui;


import java.io.IOException;

import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import repository.UserDTO;

public class AanmeldenController {
	
	private final DomeinController dc;
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
	private TextField gebruikersnaamTxtField;
	@FXML
	private TextField paswoordTxtField;
	@FXML
	private Button aanmeldenButton;
	@FXML
	private Button paswoordVergetenButton;
	@FXML
	private Label errorMessage;
	
	public AanmeldenController(DomeinController dc) {
		this.dc = dc;
	}
	
	public void aanmelden(ActionEvent event) throws IOException {
		try {
			UserDTO user = dc.aanmelden(gebruikersnaamTxtField.getText(), paswoordTxtField.getText());
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
			HomeController hc = new HomeController(this.dc, user);
			loader.setController(hc);
			root = loader.load();
			
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			stage.setResizable(false);
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IllegalArgumentException e) {
			errorMessage.setText(e.getMessage());
			errorMessage.setTextFill(Color.RED);
		}
	}
	
}
