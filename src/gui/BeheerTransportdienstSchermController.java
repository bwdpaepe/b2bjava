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

public class BeheerTransportdienstSchermController extends Pane {

	@FXML
	private TableView<DienstDTO> tableViewTransportdienst = new TableView<DienstDTO>();

	@FXML
	private TableColumn<DienstDTO, String> naamKolom = new TableColumn<DienstDTO, String>("Naam");

	@FXML
	private TableColumn<DienstDTO, String> statusKolom = new TableColumn<DienstDTO, String>("Status");
	
	

	public BeheerTransportdienstSchermController(List<DienstDTO> diensten) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("BeheerTransportdienstScherm.fxml"));
		loader.setController(this);
		loader.setRoot(this);

		buildGuid(diensten);

		try {
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void buildGuid(List<DienstDTO> diensten) {

		naamKolom.setCellValueFactory(new PropertyValueFactory<DienstDTO, String>("naam"));

		tableViewTransportdienst.getColumns().add(naamKolom);
		
		tableViewTransportdienst.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		for (DienstDTO dienst : diensten) {
			tableViewTransportdienst.getItems().add(dienst);
		}

	}

}
