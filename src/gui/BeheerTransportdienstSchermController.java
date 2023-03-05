package gui;

import java.io.IOException;
import java.util.List;

import domein.DomeinController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import repository.TransportdienstDTO;

public class BeheerTransportdienstSchermController extends Pane {

	@FXML
	private Label welkomNaam;

	private TableView<TransportdienstDTO> tableViewTransportdienst;
	private TableColumn<TransportdienstDTO, String> naamKolom;
	private TableColumn<TransportdienstDTO, Boolean> statusKolom;

	private DomeinController dc;

	public BeheerTransportdienstSchermController(DomeinController dc,
			List<TransportdienstDTO> transportdienstDTOLijst) {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("BeheerTransportdienstScherm.fxml"));
		loader.setController(this);
		loader.setRoot(this);
		this.dc = dc;

		buildGuid(transportdienstDTOLijst);
		
		this.getChildren().add(tableViewTransportdienst);

		try {
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void buildGuid(List<TransportdienstDTO> transportdienstDTOLijst) {

		ObservableList<TransportdienstDTO> list = FXCollections.observableArrayList(transportdienstDTOLijst);

		tableViewTransportdienst = new TableView<TransportdienstDTO>();
		tableViewTransportdienst.setLayoutX(500);
		tableViewTransportdienst.setLayoutY(500);
		naamKolom = new TableColumn<TransportdienstDTO, String>("Naam");
		naamKolom.setCellValueFactory(new PropertyValueFactory<TransportdienstDTO, String>("naam"));
		statusKolom = new TableColumn<TransportdienstDTO, Boolean>("Status");
		statusKolom.setCellValueFactory(new PropertyValueFactory<TransportdienstDTO, Boolean>("isActief"));

		tableViewTransportdienst.getColumns().add(naamKolom);
		tableViewTransportdienst.getColumns().add(statusKolom);

		

		tableViewTransportdienst.setItems(list);


	}

}
