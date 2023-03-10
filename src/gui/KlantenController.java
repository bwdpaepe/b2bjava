package gui;



import java.io.IOException;

import domein.DomeinController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import repository.KlantLijstEntryDTO;


public class KlantenController extends Pane {
	private DomeinController dc;

	@FXML
	private Label errorMessage;
	@FXML
	private Pane errorWindow;
	@FXML
	private Button closeButton;
	
	public KlantenController() {


	}	
	

	public void setParams(DomeinController dc) {
		
		this.dc = dc;
	}
	
	@FXML
	public void onEnter(ActionEvent ae) throws IOException {
		this.geKlantenList(ae);
	}

	private void geKlantenList(ActionEvent ae) throws IOException {
		try
		{
			ObservableList<KlantLijstEntryDTO> klantenList = FXCollections.observableList(dc.geefLijstVanKlantenMetAantalOpenstaandeBestellingen());
			
			System.out.println(klantenList);
		} catch (Exception e)
		{
			errorMessage.setText(e.getMessage());
			this.errorWindow.setVisible(true);
			this.closeButton.setVisible(true);
		}
	}
}
