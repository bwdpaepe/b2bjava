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

	@FXML
	private Button btnToevoegen;

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
		buildGuiAanpasTab();

		tvTransportdiensten.setRowFactory(tv -> {
			TableRow<TransportdienstDTO> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {

					this.selectedTransportdienstDTO = row.getItem();
					// TODO methode toevoegen om deze in de raadpleeg of aanpas tab te zetten
					buildGuiRaadpleegTab();
					buildGuiAanpasTab();

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
		lblBarcodeEnkelCijfers.setText(String.valueOf(selectedTransportdienstDTO.isBarcodeEnkelCijfers()));
		lblStatus.setText(String.valueOf(selectedTransportdienstDTO.getIsActief()));
		contactpersoonVoornaamKolom.setCellValueFactory(cellData -> cellData.getValue().getVoornaamProperty());
		contactpersoonFamilienaamKolom.setCellValueFactory(cellData -> cellData.getValue().getFamilienaamProperty());
		contactpersoonEmailadresKolom.setCellValueFactory(cellData -> cellData.getValue().getEmailadresProperty());
		contactpersoonTelefoonnummerKolom
				.setCellValueFactory(cellData -> cellData.getValue().getTelefoonnummerProperty());

		this.tvContactpersonen.setItems(contactpersonen);
	}

	private void buildGuiToevoegTab() {
		SpinnerValueFactory<Integer> factory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100);
		spinnerLengteBarcode.setValueFactory(factory);
		cbVerificatie.getItems().add("Orderid");
		cbVerificatie.getItems().add("Postcode");

	}

	private void buildGuiAanpasTab() {
		// TODO Auto-generated method stub

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
			// TODO degelijk errorbericht aanmaken
			melding.setAlertType(AlertType.ERROR);
			melding.setContentText(e.getMessage());
			melding.show();
		}

		transportdiensten = FXCollections.observableArrayList(dc.getTransportdienstenDTO());
		tvTransportdiensten.setItems(transportdiensten);
	}

}
