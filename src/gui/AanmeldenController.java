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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AanmeldenController extends Pane {

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

	// TODO verwijderen na ontwikkeling
	@FXML
	private Button aanmeldenZonderInlogButton;

	public AanmeldenController(DomeinController dc) {

		this.dc = dc;
		
	}

	public void onEnter(ActionEvent ae) throws IOException {
		this.aanmelden(ae);
	}

	public void closeError(ActionEvent ae) {
		this.errorWindow.setVisible(false);

	}

	@FXML
	public void aanmelden(ActionEvent event) throws IOException {
		try {
			dc.aanmelden(gebruikersnaamTxtField.getText(), paswoordTxtField.getText());

			FXMLLoader loader = new FXMLLoader(getClass().getResource("Master.fxml"));
			Parent root = loader.load();
			MasterController mc = loader.getController();
			mc.setParams(dc);			
			scene = new Scene(root);
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.show();
			mc.loadBestellingen();

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

	@FXML
	public void aanmeldenZonderLoginGegevens(ActionEvent event) throws IOException {
		try {
			dc.aanmelden("emailail1@test.com", "paswoord");
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Master.fxml"));
			Parent root = loader.load();
			MasterController mc = loader.getController();
			mc.setParams(dc);		
			scene = new Scene(root);
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.show();
			mc.loadBestellingen();

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
