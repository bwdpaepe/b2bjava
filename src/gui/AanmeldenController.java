package gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import repository.DienstDTO;
import repository.TransportdienstDTO;
import repository.UserDTO;

public class AanmeldenController extends Pane {

	private final DomeinController dc;
	// private Stage stage;
	// private Scene scene;
	// private Parent root;

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
	
	//TODO verwijderen na ontwikkeling
	@FXML
	private Button aanmeldenZonderInlogButton;

	public AanmeldenController(DomeinController dc) {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("Aanmelden.fxml"));
		loader.setController(this);
		loader.setRoot(this);
		this.dc = dc;

		try {
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
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

			// FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
			// HomeController hc = new HomeController(this.dc, user);
			// loader.setController(hc);
			// root = loader.load();
			// stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

			// stage.setResizable(false);

			// Mock data om gui te testen   <<=== maken in GraphicalUI en ophalen via DC

			List<TransportdienstDTO> diensten = new ArrayList<>();
			
			

			
			BeheerTransportdienstSchermController root = new BeheerTransportdienstSchermController(dc, diensten, user);
			Scene scene = new Scene(root);
			Stage stage = (Stage) this.getScene().getWindow();
			stage.setScene(scene);

			// hc.setWelkomTekst();
			// stage.show();
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
	
	public void aanmeldenZonderLoginGegevens(ActionEvent event) {
		try {
			UserDTO user = dc.aanmelden("emailail1@test.com", "paswoord");

			// stage.setResizable(false);

			// Mock data om gui te testen <<=== SAME HERE

			List<TransportdienstDTO> diensten = new ArrayList<>();

			
			BeheerTransportdienstSchermController root = new BeheerTransportdienstSchermController(dc, diensten, user);
			Scene scene = new Scene(root);
			Stage stage = (Stage) this.getScene().getWindow();
			stage.setScene(scene);
			
		}catch (IllegalArgumentException e) {
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
