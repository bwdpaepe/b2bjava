package gui;

import domein.DomeinController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import repository.ContactpersoonDTO;
import repository.TransportdienstDTO;

public class TransportdienstenController extends Pane {

	private DomeinController dc;
	private ObservableList<TransportdienstDTO> transportdiensten;
	private ObservableList<ContactpersoonDTO> contactpersonen;
	private TransportdienstDTO selectedTransportdienstDTO;

	@FXML
	private TableView<TransportdienstDTO> tvTransportdiensten;

	@FXML
	private TableColumn<TransportdienstDTO, String> transportdienstNaamKolom;

	@FXML
	private TableColumn<TransportdienstDTO, Boolean> transportdienstStatusKolom;

	@FXML
	private TableView<ContactpersoonDTO> tvContactpersonen;

	@FXML
	private TableColumn<ContactpersoonDTO, String> contactpersoonVoornaamKolom;

	@FXML
	private TableColumn<ContactpersoonDTO, String> contactpersoonFamilienaamKolom;

	@FXML
	private TableColumn<ContactpersoonDTO, String> contactpersoonEmailadresKolom;

	@FXML
	private TableColumn<ContactpersoonDTO, String> contactpersoonTelefoonnummerKolom;

	@FXML
	private Label lblNaam;

	@FXML
	private Label lblPrefix;

	@FXML
	private Label lblBarcodeLengte;

	@FXML
	private Label lblBarcodeEnkelCijfers;

	@FXML
	private Label lblVerificatiecode;

	@FXML
	private Label lblStatus;

	public TransportdienstenController() {

	}

	public void setParams(DomeinController dc) {
		this.dc = dc;
		this.transportdiensten = FXCollections.observableArrayList(dc.getTransportdienstenDTO());
		this.selectedTransportdienstDTO = transportdiensten.get(0);
		this.contactpersonen = FXCollections.observableArrayList(selectedTransportdienstDTO.getContactpersonen());
		buildGui();
	}

	private void buildGui() {

		transportdienstNaamKolom.setCellValueFactory(cellData -> cellData.getValue().getNaamProperty());
		transportdienstStatusKolom.setCellValueFactory(cellData -> cellData.getValue().getIsActiefProperty());
		tvTransportdiensten.setItems(transportdiensten);
		buildGuiRaadpleegTab();

		tvTransportdiensten.setRowFactory(tv -> {
			TableRow<TransportdienstDTO> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {

					this.selectedTransportdienstDTO = row.getItem();
					// TODO methode toevoegen om deze in de raadpleeg of aanpas tab te zetten
					buildGuiRaadpleegTab();

				}
			});
			return row;
		});
	}

	private void buildGuiRaadpleegTab() {
		lblNaam.setText(selectedTransportdienstDTO.getNaam());
		lblBarcodeLengte.setText(String.valueOf(selectedTransportdienstDTO.getBarcodeLengte()));
		lblPrefix.setText(selectedTransportdienstDTO.getBarcodePrefix());
		lblVerificatiecode.setText(selectedTransportdienstDTO.getVerificatieCodeString());
		// lblBarcodeEnkelCijfers.setText(selectedTransportdienstDTO.get);
		lblStatus.setText(String.valueOf(selectedTransportdienstDTO.getIsActief()));
		contactpersoonVoornaamKolom.setCellValueFactory(cellData -> cellData.getValue().getVoornaamProperty());
		contactpersoonFamilienaamKolom.setCellValueFactory(cellData -> cellData.getValue().getFamilienaamProperty());
		contactpersoonEmailadresKolom.setCellValueFactory(cellData -> cellData.getValue().getEmailadresProperty());
		contactpersoonTelefoonnummerKolom
				.setCellValueFactory(cellData -> cellData.getValue().getTelefoonnummerProperty());

		this.tvContactpersonen.setItems(contactpersonen);
	}

}
