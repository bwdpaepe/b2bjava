package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MasterController extends Pane {
	

	private DomeinController dc;

	@FXML private BorderPane bp;	
	@FXML private Text titlePlaceholder;
	
	@FXML private ContextMenu profileContextMenu;
	@FXML private Button btnProfile;
	
	@FXML
	private void showContextMenu(ActionEvent event) {
	    profileContextMenu.show(btnProfile, Side.BOTTOM, 0, 0);
	}
	
	@FXML
	private Button medewerkerButton;
	@FXML
	private Button doosButton;
	@FXML
	private Button transportdienstButton;
        
	public void initialize(URL location, ResourceBundle resources) {
	    ContextMenu contextMenu = new ContextMenu();
	    MenuItem logOutItem = new MenuItem("Log out");
	    logOutItem.setOnAction(e -> handleLogout());
	    contextMenu.getItems().addAll(logOutItem);

	    btnProfile.setOnAction(this::showContextMenu);
	}
    
	@FXML
	private void handleLogout() {
	    try {
	        // Call the domain controller method
	        dc.afmelden();

	        // Load the Aanmelden screen
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Aanmelden.fxml"));
	        AanmeldenController ac = new AanmeldenController(dc);
	        loader.setController(ac);
	        Parent root = loader.load();

	        // Get the current stage and set the scene
	        Stage currentStage = (Stage) bp.getScene().getWindow();
	        currentStage.setScene(new Scene(root));
	        currentStage.show();

	    } catch (Exception e) {
	        // Handle any exceptions
	        e.printStackTrace();
	    }
	}
	
	private void updateMedewerkerButtonVisibility() {
	    if (dc.getFunctionOfLoggedInUser().toLowerCase().equals("admin")) {
	        medewerkerButton.setVisible(true);
	        doosButton.setVisible(true);
	        transportdienstButton.setVisible(true);
	    } else {
	        medewerkerButton.setVisible(false);
	        doosButton.setVisible(false);
	        transportdienstButton.setVisible(false);
	    }
	}
	
	public MasterController() {

	}
	
	public void loadMedewerker() {
		// TODO
	}
	
	public void loadTransportdienst() {
		Parent root = null;
		try {
			titlePlaceholder.setText("Transportdiensten");
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Transportdiensten.fxml"));
			root = loader.load();
			TransportdienstenController tc = loader.getController();
			tc.setParams(dc);
			bp.setCenter(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadDoos() {
		// TODO
	}
	
	public void loadKlanten() {
		Parent root = null;
		try {
			titlePlaceholder.setText("Klanten");
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Klanten.fxml"));
			root = loader.load();
			KlantenController kc = loader.getController();
			kc.setParams(dc);
			kc.loadKlanten();
			bp.setCenter(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadBestellingen() {
		Parent root = null;
		try {
			titlePlaceholder.setText("Bestellingen");
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Bestellingen.fxml"));
			root = loader.load();
			BestellingenController bc = loader.getController();
			bc.setParams(dc);
			bc.loadBestellingen();
			bc.addListenerRijSelecteren();
			// rechts tonen we een form om bestelling te wijzigen, dit is leeg bij init => disable velden
			bc.disableDetail();
			bp.setCenter(root);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	
	public void setParams(DomeinController dc) {
		this.dc = dc;
		updateMedewerkerButtonVisibility();
	}
}
