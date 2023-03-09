package gui;

import domein.DomeinController;
import javafx.scene.layout.Pane;

public class BestellingenController extends Pane {
	private DomeinController dc;
	
	
	public BestellingenController() {
		// TODO Auto-generated constructor stub
	}
	
	
	public void buttonTwo() {
		System.out.println("Bestellingen");
	}
	
	public void setParams(DomeinController dc) {
		
		this.dc = dc;
	}

}
