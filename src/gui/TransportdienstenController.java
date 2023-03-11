package gui;

import domein.DomeinController;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import repository.ContactpersoonDTO;
import repository.PersoonDTO;
import repository.TransportdienstDTO;
import service.ValidationService;

public class TransportdienstenController extends Pane {

	private DomeinController dc;
	private ObservableList<TransportdienstDTO> transportdiensten;
	private ObservableList<ContactpersoonDTO> contactpersonen;
	private TransportdienstDTO selectedTransportdienstDTO;
	private Alert melding = new Alert(AlertType.NONE);
	private SpinnerValueFactory<Integer> factory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100);
	private ToggleGroup tgRaadpleegTab = new ToggleGroup();
	private ToggleGroup tgToevoegTab = new ToggleGroup();

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
	private TableColumn<PersoonDTO, Long> contactpersoonIdKolom;

	@FXML
	private TableColumn<ContactpersoonDTO, String> contactpersoonVoornaamKolom;

	@FXML
	private TableColumn<ContactpersoonDTO, String> contactpersoonFamilienaamKolom;

	@FXML
	private TableColumn<ContactpersoonDTO, String> contactpersoonEmailadresKolom;

	@FXML
	private TableColumn<ContactpersoonDTO, String> contactpersoonTelefoonnummerKolom;

	@FXML
	private RadioButton rbOrderIdRaadpleegTab;

	@FXML
	private RadioButton rbPostcodeRaadpleegTab;

	@FXML
	private Spinner<Integer> spinBarcodeLengteRaadpleegTab;

	@FXML
	private Label lblVerificatiecode;

	@FXML
	private TextField txtNaamRaadpleegTab;

	@FXML
	private TextField txtPrefixRaadpleegTab;

	@FXML
	private CheckBox cbEnkelCijfersRaadpleegTab;

	@FXML
	private CheckBox cbStatusRaadpleegTab;

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
	private RadioButton rbOrderIdToevoegTab;

	@FXML
	private RadioButton rbPostcodeToevoegTab;

	@FXML
	private TextField txtPrefix;

	@FXML
	private HBox hBoxContactPersonen1;

	@FXML
	private HBox hBoxContactPersonen2;

	@FXML
	private TextField txtVoornaamToevoegen;

	@FXML
	private TextField txtFamilienaamToevoegen;

	@FXML
	private TextField txtEmailadresToevoegen;

	@FXML
	private TextField txtTelefoonnummerToevoegen;

	public TransportdienstenController() {

	}

	public void setParams(DomeinController dc) {
		this.dc = dc;
		rbOrderIdRaadpleegTab.setToggleGroup(tgRaadpleegTab);
		rbPostcodeRaadpleegTab.setToggleGroup(tgRaadpleegTab);
		this.transportdiensten = FXCollections.observableArrayList(dc.getTransportdienstenDTO());
		this.selectedTransportdienstDTO = transportdiensten.get(0);
		this.contactpersonen = FXCollections.observableArrayList(selectedTransportdienstDTO.getContactpersonen());
		buildGui();
	}

	private void buildGui() {
		this.transportdiensten = FXCollections.observableArrayList(dc.getTransportdienstenDTO());
		this.contactpersonen = FXCollections.observableArrayList(selectedTransportdienstDTO.getContactpersonen());
		buildGuiTableViewTransportdiensten();
		buildGuiToevoegTab();

	}

	private void buildGuiTableViewTransportdiensten() {
		transportdienstNaamKolom
				.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNaam()));
		transportdienstStatusKolom
				.setCellValueFactory(cellData -> new SimpleBooleanProperty(cellData.getValue().getIsActief()));
		tvTransportdiensten.setItems(transportdiensten);
		buildGuiRaadpleegTab();

		tvTransportdiensten.setRowFactory(tv -> {
			TableRow<TransportdienstDTO> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1) {

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
		boolean postcodeSelected = selectedTransportdienstDTO.getVerificatieCodeString() == "POSTCODE" ? true : false;
		rbOrderIdRaadpleegTab.setSelected(!postcodeSelected);
		rbPostcodeRaadpleegTab.setSelected(postcodeSelected);
		txtNaamRaadpleegTab.setText(selectedTransportdienstDTO.getNaam());
		txtPrefixRaadpleegTab.setText(selectedTransportdienstDTO.getBarcodePrefix());
		cbEnkelCijfersRaadpleegTab.setSelected(selectedTransportdienstDTO.isBarcodeEnkelCijfers());
		cbStatusRaadpleegTab.setSelected(selectedTransportdienstDTO.getIsActief());
		spinBarcodeLengteRaadpleegTab.setValueFactory(factory);
		spinBarcodeLengteRaadpleegTab.getValueFactory().setValue(selectedTransportdienstDTO.getBarcodeLengte());

		contactpersoonIdKolom
				.setCellValueFactory(cellData -> new SimpleLongProperty(cellData.getValue().getId()).asObject());

		contactpersoonVoornaamKolom
				.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getVoornaam()));
		contactpersoonVoornaamKolom.setCellFactory(TextFieldTableCell.<ContactpersoonDTO>forTableColumn());
		contactpersoonVoornaamKolom.setOnEditCommit((CellEditEvent<ContactpersoonDTO, String> t) -> {
			ContactpersoonDTO c = ((ContactpersoonDTO) t.getTableView().getItems().get(t.getTablePosition().getRow()));
			editContactPersoon(t.getNewValue(), c.getFamilienaam(), c.getTelefoonnummer(), c.getEmailAdres(), c.getId(),
					selectedTransportdienstDTO.getId());
		});

		contactpersoonFamilienaamKolom
				.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFamilienaam()));
		contactpersoonFamilienaamKolom.setCellFactory(TextFieldTableCell.<ContactpersoonDTO>forTableColumn());
		contactpersoonFamilienaamKolom.setOnEditCommit((CellEditEvent<ContactpersoonDTO, String> t) -> {
			ContactpersoonDTO c = ((ContactpersoonDTO) t.getTableView().getItems().get(t.getTablePosition().getRow()));
			editContactPersoon(c.getVoornaam(), t.getNewValue(), c.getTelefoonnummer(), c.getEmailAdres(), c.getId(),
					selectedTransportdienstDTO.getId());

		});

		contactpersoonTelefoonnummerKolom
				.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTelefoonnummer()));
		contactpersoonTelefoonnummerKolom.setCellFactory(TextFieldTableCell.<ContactpersoonDTO>forTableColumn());
		contactpersoonTelefoonnummerKolom.setOnEditCommit((CellEditEvent<ContactpersoonDTO, String> t) -> {
			ContactpersoonDTO c = ((ContactpersoonDTO) t.getTableView().getItems().get(t.getTablePosition().getRow()));
			editContactPersoon(c.getVoornaam(), c.getFamilienaam(), t.getNewValue(), c.getEmailAdres(), c.getId(),
					selectedTransportdienstDTO.getId());
		});

		contactpersoonEmailadresKolom
				.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmailAdres()));
		contactpersoonEmailadresKolom.setCellFactory(TextFieldTableCell.<ContactpersoonDTO>forTableColumn());
		contactpersoonEmailadresKolom.setOnEditCommit((CellEditEvent<ContactpersoonDTO, String> t) -> {
			ContactpersoonDTO c = ((ContactpersoonDTO) t.getTableView().getItems().get(t.getTablePosition().getRow()));
			editContactPersoon(c.getVoornaam(), c.getFamilienaam(), c.getTelefoonnummer(), t.getNewValue(), c.getId(),
					selectedTransportdienstDTO.getId());
		});

		this.contactpersonen = FXCollections.observableArrayList(selectedTransportdienstDTO.getContactpersonen());
		this.tvContactpersonen.setItems(contactpersonen);

		disableButtonsGui();
	}

	private void buildGuiToevoegTab() {
		spinnerLengteBarcode.setValueFactory(factory);
		rbOrderIdToevoegTab.setToggleGroup(tgToevoegTab);
		rbPostcodeToevoegTab.setToggleGroup(tgToevoegTab);

	}

	@FXML
	void addTransportdienst(ActionEvent event) {
		try {
			String naamTransportdienst = txtNaamTransportdienst.getText();
			int barcodeLengte = spinnerLengteBarcode.getValue();
			boolean isBarcodeEnkelCijfers = cbCijfers.isSelected();
			String barcodePrefix = txtPrefix.getText();
			String verificatiecode = rbOrderIdToevoegTab.isSelected() ? "Orderid" : "Postcode";
			String contactVoornaam = txtVoornaam.getText();
			String contactFamilienaam = txtFamilienaam.getText();
			String contactTelefoon = txtTelefoonnummer.getText();
			String contactEmailadres = txtEmailadres.getText();

			// TODO implementeren bedrijfsId
			int bedrijfsId = 1; // !!!!!!!!!!!!!!!!!!!!!!!!!

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
		buildGuiRaadpleegTab();
		disableButtonsGui();
	}

	@FXML
	void saveUpdateTransportdienst(ActionEvent event) {
		try {

			String naamTransportdienst = txtNaamRaadpleegTab.getText();
			int barcodeLengte = spinBarcodeLengteRaadpleegTab.getValue();
			boolean isBarcodeEnkelCijfers = cbEnkelCijfersRaadpleegTab.isSelected();
			boolean isStatusActief = cbStatusRaadpleegTab.isSelected();
			String barcodePrefix = txtPrefixRaadpleegTab.getText();
			String verificatiecode = rbOrderIdRaadpleegTab.isSelected() ? "Orderid" : "Postcode";
			long dienstId = selectedTransportdienstDTO.getId();

			dc.updateTransportdienst(naamTransportdienst, barcodeLengte, isBarcodeEnkelCijfers, barcodePrefix,
					verificatiecode, dienstId);
			dc.wijzigActivatieDienst(dienstId, isStatusActief);
			this.transportdiensten = FXCollections.observableArrayList(dc.getTransportdienstenDTO());
			this.selectedTransportdienstDTO = dc.getTransportdienst(dienstId);

		} catch (IllegalArgumentException e) {
			melding.setAlertType(AlertType.ERROR);
			melding.setContentText(e.getMessage());
			melding.show();
		}

		buildGui();
	}

	@FXML
	void updateTransportdienst(ActionEvent event) {
		enableButtonsGui();

	}

	private void editContactPersoon(String voornaam, String familienaam, String telefoonnummer, String email, long id,
			long transportId) {
		try {
			dc.editContactpersoon(voornaam, familienaam, telefoonnummer, email, id, transportId);
			tvContactpersonen
					.setItems(FXCollections.observableArrayList(selectedTransportdienstDTO.getContactpersonen()));
			this.selectedTransportdienstDTO = dc.getTransportdienst(selectedTransportdienstDTO.getId());
		} catch (IllegalArgumentException e) {
			melding.setAlertType(AlertType.ERROR);
			melding.setContentText(e.getMessage());
			melding.show();
		}

	}

	@FXML
	void toevoegenContactpersoon(ActionEvent event) {

		try {
			dc.addContactpersoon(txtVoornaamToevoegen.getText(), txtFamilienaamToevoegen.getText(),
					txtTelefoonnummerToevoegen.getText(), txtEmailadresToevoegen.getText(),
					selectedTransportdienstDTO.getId());
			this.selectedTransportdienstDTO = dc.getTransportdienst(selectedTransportdienstDTO.getId());
			tvContactpersonen
					.setItems(FXCollections.observableArrayList(selectedTransportdienstDTO.getContactpersonen()));
			txtVoornaamToevoegen.clear();
			txtFamilienaamToevoegen.clear();
			txtEmailadresToevoegen.clear();
			txtTelefoonnummerToevoegen.clear();
			txtVoornaamToevoegen.requestFocus();
		} catch (IllegalArgumentException e) {
			melding.setAlertType(AlertType.ERROR);
			melding.setContentText(e.getMessage());
			melding.show();
		}

	}

	@FXML
	void verwijderenContactpersoon(ActionEvent event) {
		int rij = tvContactpersonen.getSelectionModel().getSelectedIndex();
		ContactpersoonDTO c = tvContactpersonen.getItems().get(rij);
		dc.removeContactpersoon(c.getId(), selectedTransportdienstDTO.getId());
		this.selectedTransportdienstDTO = dc.getTransportdienst(selectedTransportdienstDTO.getId());
		tvContactpersonen
				.setItems(FXCollections.observableArrayList(selectedTransportdienstDTO.getContactpersonen()));
		

	}

	private void disableButtonsGui() {
		txtNaamRaadpleegTab.setEditable(false);
		txtPrefixRaadpleegTab.setEditable(false);
		cbEnkelCijfersRaadpleegTab.setDisable(true);
		cbStatusRaadpleegTab.setDisable(true);
		spinBarcodeLengteRaadpleegTab.setDisable(true);
		rbOrderIdRaadpleegTab.setDisable(true);
		rbPostcodeRaadpleegTab.setDisable(true);
		btnAbortUpdate.setVisible(false);
		btnSaveTransportdienst.setVisible(false);
		tvContactpersonen.setEditable(false);
		hBoxContactPersonen1.setVisible(false);
		hBoxContactPersonen2.setVisible(false);
	}

	private void enableButtonsGui() {
		txtNaamRaadpleegTab.setEditable(true);
		txtPrefixRaadpleegTab.setEditable(true);
		cbEnkelCijfersRaadpleegTab.setDisable(false);
		cbStatusRaadpleegTab.setDisable(false);
		spinBarcodeLengteRaadpleegTab.setDisable(false);
		rbOrderIdRaadpleegTab.setDisable(false);
		rbPostcodeRaadpleegTab.setDisable(false);
		btnAbortUpdate.setVisible(true);
		btnSaveTransportdienst.setVisible(true);
		tvContactpersonen.setEditable(true);
		hBoxContactPersonen1.setVisible(true);
		hBoxContactPersonen2.setVisible(true);
	}

}
