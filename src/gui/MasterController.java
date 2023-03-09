package gui;

import java.io.IOException;
import java.security.DomainCombiner;

import domein.DomeinController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class MasterController extends Pane {
	

	private DomeinController dc;

	@FXML
	private BorderPane bp;
	
	private Label label;
	
	
	public MasterController() {


	}
	
	public void loadMedewerker() {

		
		
	}
	
	public void loadTransportdienst() {
		Parent root = null;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Transportdiensten.fxml"));
			root = loader.load();
			TransportdienstenController tc = loader.getController();
			tc.setParams(dc);
			bp.setCenter(root);

			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	
	public void loadDoos() {
		
	}
	
	public void loadKlanten() {
		
	}
	
	public void loadBestellingen() {
		Parent root = null;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Bestellingen.fxml"));
			root = loader.load();
			BestellingenController bc = loader.getController();
			bc.setParams(dc);				
			bp.setCenter(root);

			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setParams(DomeinController dc) {
		this.dc = dc;
	}
	

}
