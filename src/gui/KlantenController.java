package gui;

import domein.DomeinController;
import javafx.scene.layout.Pane;


public class KlantenController extends Pane {
	private DomeinController dc;

	
	
	public KlantenController() {


	}	
	

	public void setParams(DomeinController dc) {
		
		this.dc = dc;
	}

}
