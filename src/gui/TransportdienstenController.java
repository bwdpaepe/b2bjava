package gui;

import domein.DomeinController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import repository.ContactpersoonDTO;
import repository.TransportdienstDTO;
import service.ValidationService;

public class TransportdienstenController extends Pane {

	private DomeinController dc;
	private ObservableList<TransportdienstDTO> transportdiensten;
	private ObservableList<ContactpersoonDTO> contactpersonen;
	private TransportdienstDTO selectedTransportdienstDTO;
	private Alert melding = new Alert(AlertType.NONE);

	@FXML
	private TableView<TransportdienstDTO> tvTransportdiensten;

	@FXML
	private TableColumn<TransportdienstDTO, String> transportdienstNaamKolom;

	@FXML
	private TableColumn<TransportdienstDTO, Boolean> transportdienstStatusKolom;

	@FXML
	private TabPane tabPane;

	@FXML
	private Tab raadpleegTab;

	@FXML
	private Tab toevoegTab;

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

	// @FXML
	// private Label lblVerificatiecode;

	@FXML
	private TextField txtNaamRaadpleegTab;

	@FXML
	private TextField txtPrefixRaadpleegTab;

	@FXML
	private TextField txtBarCodeLengteRaadpleegTab;

	@FXML
	private CheckBox cbEnkelCijfersRaadpleegTab;

	@FXML
	private CheckBox cbStatusRaadpleegTab;

	@FXML
	private ChoiceBox<String> cbVerificatiecodeRaadpleegTab;

	@FXML
	private Button btnToevoegen;

	@FXML
	private Button btnUpdateTransportdienst;

	@FXML
	private Button btnSaveTransportdienst;

	@FXML
	private Button btnAbortUpdate;

	@FXML
	private TextField txtNaamTransportdienst;

	@FXML
	private TextField txtTelefoonnummer;

	@FXML
	private TextField txtEmailadres;

	@FXML
	private TextField txtFamilienaam;

	@FXML
	private TextField txtVoornaam;

	@FXML
	private Spinner<Integer> spinnerLengteBarcode;

	@FXML
	private CheckBox cbCijfers;

	@FXML
	private ChoiceBox<String> cbVerificatie;

	@FXML
	private TextField txtPrefix;

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
		buidGuiTableViewTransportdiensten();
		buildGuiToevoegTab();

	}

	private void buidGuiTableViewTransportdiensten() {
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
					tabPane.getSelectionModel().select(raadpleegTab);

				}
			});
			return row;
		});
	}

	private void buildGuiRaadpleegTab() {
		txtNaamRaadpleegTab.setText(selectedTransportdienstDTO.getNaam());
		txtBarCodeLengteRaadpleegTab.setText(String.valueOf(selectedTransportdienstDTO.getBarcodeLengte()));
		txtPrefixRaadpleegTab.setText(selectedTransportdienstDTO.getBarcodePrefix());
		cbEnkelCijfersRaadpleegTab.setSelected(selectedTransportdienstDTO.isBarcodeEnkelCijfers());
		// lblVerificatiecode.setText(selectedTransportdienstDTO.getVerificatieCodeString());
		cbStatusRaadpleegTab.setSelected(selectedTransportdienstDTO.getIsActief());
		contactpersoonVoornaamKolom.setCellValueFactory(cellData -> cellData.getValue().getVoornaamProperty());
		contactpersoonFamilienaamKolom.setCellValueFactory(cellData -> cellData.getValue().getFamilienaamProperty());
		contactpersoonEmailadresKolom.setCellValueFactory(cellData -> cellData.getValue().getEmailadresProperty());
		contactpersoonTelefoonnummerKolom
				.setCellValueFactory(cellData -> cellData.getValue().getTelefoonnummerProperty());

		this.tvContactpersonen.setItems(contactpersonen);

		btnAbortUpdate.setVisible(false);
		btnSaveTransportdienst.setVisible(false);
	}

	private void buildGuiToevoegTab() {
		SpinnerValueFactory<Integer> factory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100);
		spinnerLengteBarcode.setValueFactory(factory);
		cbVerificatie.getItems().add("Orderid");
		cbVerificatie.getItems().add("Postcode");

	}

	@FXML
	void addTransportdienst(ActionEvent event) {
		try {
			String naamTransportdienst = txtNaamTransportdienst.getText();
			int barcodeLengte = spinnerLengteBarcode.getValue();
			boolean isBarcodeEnkelCijfers = cbCijfers.isSelected();
			String barcodePrefix = txtPrefix.getText();
			String verificatiecode = (String) cbVerificatie.getValue();
			String contactVoornaam = txtVoornaam.getText();
			String contactFamilienaam = txtFamilienaam.getText();
			String contactTelefoon = txtTelefoonnummer.getText();
			String contactEmailadres = txtEmailadres.getText();

			// TODO implementeren bedrijfsId
			int bedrijfsId = 1;

			// Validatie input formulier
			ValidationService.controleerNietBlanco(naamTransportdienst);
			ValidationService.controleerGroterDanNul(barcodeLengte);
			ValidationService.controleerNietBlanco(barcodePrefix);
			ValidationService.controleerNietBlanco(contactVoornaam);
			ValidationService.controleerNietBlanco(contactFamilienaam);
			ValidationService.controleerEmail(contactEmailadres);
			ValidationService.controleerTelefoonnummer(contactTelefoon);

			// Aanmaken van een transportdienst
			dc.maakTransportdienst(naamTransportdienst, barcodeLengte, isBarcodeEnkelCijfers, barcodePrefix,
					verificatiecode, contactVoornaam, contactFamilienaam, contactTelefoon, contactEmailadres,
					bedrijfsId);

		} catch (IllegalArgumentException e) {
			melding.setAlertType(AlertType.ERROR);
			melding.setContentText(e.getMessage());
			melding.show();
		}

		transportdiensten = FXCollections.observableArrayList(dc.getTransportdienstenDTO());
		tvTransportdiensten.setItems(transportdiensten);
	}

	@FXML
	void abortUpdateTransportdienst(ActionEvent event) {

	}

	@FXML
	void saveTransportdienst(ActionEvent event) {

	}

	@FXML
	void updateTransportdienst(ActionEvent event) {

		btnAbortUpdate.setVisible(true);
		btnSaveTransportdienst.setVisible(true);

	}

}
