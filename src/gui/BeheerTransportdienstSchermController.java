package gui;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import repository.DienstDTO;
import repository.TransportdienstDTO;

public class BeheerTransportdienstSchermController extends Pane {

	@FXML
	private TableView<TransportdienstDTO> tableViewTransportdienst = new TableView<TransportdienstDTO>();

	private TableColumn<TransportdienstDTO, String> naamKolom = new TableColumn<TransportdienstDTO, String>("Naam");

	private TableColumn<TransportdienstDTO, String> statusKolom = new TableColumn<TransportdienstDTO, String>("Status");

	public BeheerTransportdienstSchermController() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("BeheerTransportdienstScherm.fxml"));
		loader.setController(this);
		loader.setRoot(this);

		buildGuid();
//
		try {
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void buildGuid() {
/*
		naamKolom.setCellValueFactory(new PropertyValueFactory<TransportdienstDTO, String>("naam"));

		tableViewTransportdienst.getColumns().add(naamKolom);

		// tableViewTransportdienst.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		TransportdienstDTO td = diensten.get(0);
		tableViewTransportdienst.getItems().add(td);
*/
	}

}
