package gui;


import java.io.IOException;

import javax.persistence.EntityNotFoundException;

import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
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
	private PasswordField paswoordTxtField;
	@FXML
	private Button aanmeldenButton;
	@FXML
	private Button paswoordVergetenButton;
	@FXML
	private Label errorMessage;
	@FXML
	private Pane errorWindow;
	@FXML
	private Button closeButton;
	
	
	public AanmeldenController(DomeinController dc) {
		this.dc = dc;
	}
	
	@FXML
	public void onEnter(ActionEvent ae) throws IOException {
		this.aanmelden(ae);
	}
	
	@FXML
	public void closeError(ActionEvent ae) {
		this.errorWindow.setVisible(false);
		
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
			hc.setWelkomTekst();
			stage.show();
		} catch (IllegalArgumentException e) {
			errorMessage.setText(e.getMessage());
			this.errorWindow.setVisible(true);
			this.closeButton.setVisible(true);
			
			
			
		} catch (EntityNotFoundException e) {
			errorMessage.setText(e.getMessage());
			this.errorWindow.setVisible(true);
			this.closeButton.setVisible(true);
			
		}
	}
	
}
