package gui;

import java.io.IOException;

import domein.DomeinController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class MasterController extends Pane {
	

	private DomeinController dc;

	@FXML private BorderPane bp;	
	@FXML private Text titlePlaceholder;
	
	
	
	public MasterController() {


	}
	
	public void loadMedewerker() {

		
		
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
	}
	

}
