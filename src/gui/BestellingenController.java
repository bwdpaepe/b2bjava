package gui;

import domein.DomeinController;
import javafx.scene.layout.Pane;

public class BestellingenController extends Pane {
	private DomeinController dc;
	
	
	public BestellingenController() {

	}
	
	
	public void buttonTwo() {
		System.out.println("Bestellingen");
	}
	
	public void setParams(DomeinController dc) {
		
		this.dc = dc;
	}

}
