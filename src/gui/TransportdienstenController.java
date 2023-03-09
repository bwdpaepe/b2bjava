package gui;

import domein.DomeinController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class TransportdienstenController extends Pane {
	
	private DomeinController dc;
	
	@FXML
	private Button testButton;
	

	public TransportdienstenController() {

	}
	
	
	public void buttonOne() {
		System.out.println("Transportdiensten");
	}
	
	public void setParams(DomeinController dc) {
		this.dc = dc;
	}
	


}
