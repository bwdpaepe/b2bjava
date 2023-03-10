package gui;

import java.util.List;

import domein.DomeinController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import repository.TransportdienstDTO;

public class TransportdienstenController extends Pane {

	private DomeinController dc;
	private ObservableList<TransportdienstDTO> transportdiensten;

	@FXML
	private TableView<TransportdienstDTO> tvTransportdiensten;

	@FXML
	private TableColumn<TransportdienstDTO, String> transportdienstNaamKolom;

	@FXML
	private TableColumn<TransportdienstDTO, Boolean> transportdienstStatusKolom;

	public TransportdienstenController() {

	}

	public void buttonOne() {
		System.out.println("Transportdiensten");
	}

	public void setParams(DomeinController dc) {
		this.dc = dc;
		this.transportdiensten = FXCollections.observableArrayList(dc.getTransportdienstenDTO());
		buildGui();
	}

	private void buildGui() {

		transportdienstNaamKolom.setCellValueFactory(cellData -> cellData.getValue().getNaamProperty());
		transportdienstStatusKolom.setCellValueFactory(cellData -> cellData.getValue().getIsActiefProperty());
		tvTransportdiensten.setItems(transportdiensten);
	}

}
