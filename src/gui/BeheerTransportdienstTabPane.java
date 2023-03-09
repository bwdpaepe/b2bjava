package gui;

import domein.DomeinController;
import javafx.scene.control.TabPane;
import repository.TransportdienstDTO;

public class BeheerTransportdienstTabPane extends TabPane {

	private final BeheerTransportdienstToevoegTab toevoegTab;
	private final BeheerTransportdienstRaadpleegTab raadpleegTab;
	private DomeinController dc;

	public BeheerTransportdienstTabPane(DomeinController dc) {
		super();
		this.dc = dc;
		this.toevoegTab = new BeheerTransportdienstToevoegTab(dc);
		this.raadpleegTab = new BeheerTransportdienstRaadpleegTab(dc);
		buidlGui();

	}

	private void buidlGui() {
		this.setLayoutX(400);
		this.setLayoutY(170);
		this.prefHeightProperty().set(900);
		this.prefWidthProperty().set(1500);
		this.setStyle("-fx-border-style:solid; -fx-padding: 1;");
		this.toevoegTab.setClosable(false);
		this.raadpleegTab.setClosable(false);

		this.getTabs().addAll(this.toevoegTab, this.raadpleegTab);
	}

	public BeheerTransportdienstToevoegTab getToevoegTab() {
		return toevoegTab;
	}

	public BeheerTransportdienstRaadpleegTab getRaadpleegTab() {
		return raadpleegTab;
	}

	public void raadpleegTabGui(TransportdienstDTO selectedTransportdienstDTO) {
		this.getSelectionModel().select(raadpleegTab);
		raadpleegTab.raadpleegTabGui(selectedTransportdienstDTO);
		
		
	}
	
	

}
