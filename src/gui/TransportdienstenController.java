package gui;

import java.util.List;

import domein.DomeinController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import repository.TransportdienstDTO;

public class TransportdienstenController extends Pane {

	private DomeinController dc;
	private ObservableList<TransportdienstDTO> transportdiensten;
	private TransportdienstDTO selectedTransportdienstDTO;

	@FXML
	private TableView<TransportdienstDTO> tvTransportdiensten;

	@FXML
	private TableColumn<TransportdienstDTO, String> transportdienstNaamKolom;

	@FXML
	private TableColumn<TransportdienstDTO, Boolean> transportdienstStatusKolom;

	@FXML
	private TableColumn<TransportdienstDTO, String> contactpersoonVoornaamKolom;

	@FXML
	private TableColumn<TransportdienstDTO, String> contactpersoonFamilienaamKolom;

	@FXML
	private TableColumn<TransportdienstDTO, String> contactpersoonEmailadresKolom;

	@FXML
	private TableColumn<TransportdienstDTO, String> contactpersoonTelefoonnummerKolom;

	public TransportdienstenController() {

	}

	public void buttonOne() {
		System.out.println("Transportdiensten");
	}

	public void setParams(DomeinController dc) {
		this.dc = dc;
		this.transportdiensten = FXCollections.observableArrayList(dc.getTransportdienstenDTO());
		this.selectedTransportdienstDTO = transportdiensten.get(0);
		buildGui();
	}

	private void buildGui() {

		transportdienstNaamKolom.setCellValueFactory(cellData -> cellData.getValue().getNaamProperty());
		transportdienstStatusKolom.setCellValueFactory(cellData -> cellData.getValue().getIsActiefProperty());
		tvTransportdiensten.setItems(transportdiensten);

		tvTransportdiensten.setRowFactory(tv -> {
			TableRow<TransportdienstDTO> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {

					TransportdienstDTO selectedTransportdienstDTO = row.getItem();
					// TODO methode toevoegen om deze in de raadpleeg of aanpas tab te zetten
					/*contactpersoonVoornaamKolom.setCellValueFactory(cellData -> cellData.getValue().getNaamProperty());
					contactpersoonFamilienaamKolom.setCellValueFactory(cellData -> cellData.getValue().getIsActiefProperty());
					contactpersoonEmailadresKolom.setCellValueFactory(cellData -> cellData.getValue().getNaamProperty());
					contactpersoonTelefoonnummerKolom.setCellValueFactory(cellData -> cellData.getValue().getIsActiefProperty());*/

				}
			});
			return row;
		});
	}

}
