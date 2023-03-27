package gui;

import domein.DomeinController;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
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
import javafx.scene.layout.Pane;
import repository.ContactpersoonDTO;
import repository.MedewerkerDTO;
import repository.PersoonDTO;
import repository.TransportdienstDTO;
import service.ValidationService;

public class TransportdienstenController extends Pane {

	private DomeinController dc;
	private MedewerkerDTO ingelogdeUser;
	private ObservableList<TransportdienstDTO> transportdiensten;
	private ObservableList<ContactpersoonDTO> contactpersonen;
	private TransportdienstDTO selectedTransportdienstDTO;
	private Alert melding = new Alert(AlertType.NONE);
	private ToggleGroup tgRaadpleegTab = new ToggleGroup();
	private ToggleGroup tgToevoegTab = new ToggleGroup();

	@FXML
	private TableView<TransportdienstDTO> tvTransportdiensten;

	@FXML
	private TableColumn<TransportdienstDTO, String> transportdienstNaamKolom;

	@FXML
	private TableColumn<TransportdienstDTO, String> transportdienstStatusKolom;

	@FXML
	private TextField txtTransportdienstZoeken;

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
	private Label lblVerificatiecode;

	@FXML
	private TextField txtNaamRaadpleegTab;

	@FXML
	private TextField txtPrefixRaadpleegTab;

	@FXML
	private TextField txtBarcodeLengteRaadpleegTab;

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
	private Button btnVerwijderContactPersoon;

	@FXML
	private Button btnToevoegenContactPersoon;

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
	private TextField txtLengteBarcodeToevoegTab;

	@FXML
	private CheckBox cbCijfers;

	@FXML
	private RadioButton rbOrderIdToevoegTab;

	@FXML
	private RadioButton rbPostcodeToevoegTab;

	@FXML
	private TextField txtPrefix;

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
		this.ingelogdeUser = (MedewerkerDTO) dc.getIngelogdeUser();
		rbOrderIdRaadpleegTab.setToggleGroup(tgRaadpleegTab);
		rbPostcodeRaadpleegTab.setToggleGroup(tgRaadpleegTab);
		this.transportdiensten = FXCollections.observableArrayList(dc.getTransportdienstenDTO());
		this.selectedTransportdienstDTO = transportdiensten.get(0);
		this.contactpersonen = FXCollections.observableArrayList(selectedTransportdienstDTO.getContactpersonen());
		buildGui();
	}

	private void buildGui() {
		buildGuiTableViewTransportdiensten();
		buildGuiRaadpleegTab();
		buildGuiToevoegTab();

	}

	private void buildGuiTableViewTransportdiensten() {
		transportdienstNaamKolom
				.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNaam()));
		transportdienstStatusKolom.setCellValueFactory(cellData -> new SimpleStringProperty(
				getStatusTransportdienstString(cellData.getValue().getIsActief())));

		FilteredList<TransportdienstDTO> filteredList = new FilteredList<>(transportdiensten, p -> true);

		txtTransportdienstZoeken.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredList.setPredicate(transportdienst -> {
				// If search field is empty, show all entries
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				// Zoektekst naar lowercase
				String lowerCaseFilter = newValue.toLowerCase();

				if (transportdienst.getNaam().toLowerCase().contains(lowerCaseFilter)) {
					return true;
				}
				return false;
			});

		});

		// Wrap the filtered list in a sorted list and add it to the table view
		SortedList<TransportdienstDTO> sortedList = new SortedList<>(filteredList);
		sortedList.comparatorProperty().bind(tvTransportdiensten.comparatorProperty());
		tvTransportdiensten.setItems(sortedList);

		// Selecteer een rij die in het rechtercomponent zichtbaar wordt
		tvTransportdiensten.setRowFactory(tv -> {
			TableRow<TransportdienstDTO> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() >= 1) {

					this.selectedTransportdienstDTO = row.getItem();
					tabPane.getSelectionModel().select(raadpleegTab);
					buildGuiRaadpleegTab();

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
		txtBarcodeLengteRaadpleegTab.setText(String.valueOf(selectedTransportdienstDTO.getBarcodeLengte()));

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
			try {
				ValidationService.controleerUniekEmailadres(selectedTransportdienstDTO.getContactpersonen(),
						t.getNewValue());
				editContactPersoon(c.getVoornaam(), c.getFamilienaam(), c.getTelefoonnummer(), t.getNewValue(),
						c.getId(), selectedTransportdienstDTO.getId());
			} catch (IllegalArgumentException e) {
				toonMelding(AlertType.ERROR, e.getMessage());
			}
		});

		this.contactpersonen = FXCollections.observableArrayList(selectedTransportdienstDTO.getContactpersonen());
		this.tvContactpersonen.setItems(contactpersonen);

		disableGui();
	}

	private void buildGuiToevoegTab() {
		rbOrderIdToevoegTab.setToggleGroup(tgToevoegTab);
		rbPostcodeToevoegTab.setToggleGroup(tgToevoegTab);
	}

	@FXML
	void addTransportdienst(ActionEvent event) {
		try {
			String naamTransportdienst = txtNaamTransportdienst.getText();
			String barcodeLengte = txtLengteBarcodeToevoegTab.getText();
			boolean isBarcodeEnkelCijfers = cbCijfers.isSelected();
			String barcodePrefix = txtPrefix.getText();
			String verificatiecode = rbOrderIdToevoegTab.isSelected() ? "Orderid" : "Postcode";
			String contactVoornaam = txtVoornaam.getText();
			String contactFamilienaam = txtFamilienaam.getText();
			String contactTelefoon = txtTelefoonnummer.getText();
			String contactEmailadres = txtEmailadres.getText();
			long bedrijfsId = ingelogdeUser.getBedrijf().getId();

			// Validatie input formulier
			ValidationService.controleerNietBlanco(naamTransportdienst);
			ValidationService.controleerNumerischeWaardeBarcodeLengte(barcodeLengte);
			ValidationService.controleerGroterDanNul(Integer.parseInt(barcodeLengte));
			ValidationService.controleerTTFTotaleLengte(Integer.parseInt(barcodeLengte));
			ValidationService.controleerNietBlanco(barcodePrefix);
			ValidationService.controleerTTFPrefixLengte(Integer.parseInt(barcodeLengte), barcodePrefix);
			ValidationService.controleerNietBlanco(contactVoornaam);
			ValidationService.controleerNietBlanco(contactFamilienaam);
			ValidationService.controleerEmail(contactEmailadres);
			ValidationService.controleerTelefoonnummer(contactTelefoon);

			if (isBarcodeEnkelCijfers) {
				ValidationService.controleerNumerischeWaardeBarcodePrefix(barcodePrefix);
			}

			// Aanmaken van een transportdienst
			dc.maakTransportdienst(naamTransportdienst, Integer.parseInt(barcodeLengte), isBarcodeEnkelCijfers,
					barcodePrefix, verificatiecode, contactVoornaam, contactFamilienaam, contactTelefoon,
					contactEmailadres, bedrijfsId);

			toonMelding(AlertType.INFORMATION, "De transportdienst is aangemaakt");

		} catch (IllegalArgumentException e) {
			toonMelding(AlertType.ERROR, e.getMessage());
		}

		transportdiensten = FXCollections.observableArrayList(dc.getTransportdienstenDTO());
		tvTransportdiensten.setItems(transportdiensten);
		txtNaamTransportdienst.clear();
		txtLengteBarcodeToevoegTab.clear();
		cbCijfers.setSelected(false);
		rbOrderIdToevoegTab.setSelected(false);
		rbPostcodeToevoegTab.setSelected(false);
		txtPrefix.clear();
		txtVoornaam.clear();
		txtFamilienaam.clear();
		txtTelefoonnummer.clear();
		txtEmailadres.clear();
	}

	@FXML
	void saveUpdateTransportdienst(ActionEvent event) {
		try {

			String naamTransportdienst = txtNaamRaadpleegTab.getText();
			String barcodeLengte = txtBarcodeLengteRaadpleegTab.getText();
			boolean isBarcodeEnkelCijfers = cbEnkelCijfersRaadpleegTab.isSelected();
			boolean isStatusActief = cbStatusRaadpleegTab.isSelected();
			String barcodePrefix = txtPrefixRaadpleegTab.getText();
			String verificatiecode = rbOrderIdRaadpleegTab.isSelected() ? "Orderid" : "Postcode";
			long dienstId = selectedTransportdienstDTO.getId();

			// Validatie input formulier
			ValidationService.controleerNietBlanco(naamTransportdienst);
			ValidationService.controleerNumerischeWaardeBarcodeLengte(barcodeLengte);
			ValidationService.controleerGroterDanNul(Integer.valueOf(barcodeLengte));
			ValidationService.controleerTTFTotaleLengte(Integer.valueOf(barcodeLengte));
			ValidationService.controleerNietBlanco(barcodePrefix);
			ValidationService.controleerTTFPrefixLengte(Integer.valueOf(barcodeLengte), barcodePrefix);

			if (isBarcodeEnkelCijfers) {
				ValidationService.controleerNumerischeWaardeBarcodePrefix(barcodePrefix);
			}

			dc.updateTransportdienst(naamTransportdienst, Integer.parseInt(barcodeLengte), isBarcodeEnkelCijfers,
					barcodePrefix, verificatiecode, dienstId);
			dc.wijzigActivatieDienst(dienstId, isStatusActief);
			this.transportdiensten = FXCollections.observableArrayList(dc.getTransportdienstenDTO());
			this.selectedTransportdienstDTO = dc.getTransportdienst(dienstId);
			toonMelding(AlertType.INFORMATION, "De wijzigingen zijn opgeslaan");

		} catch (IllegalArgumentException e) {
			toonMelding(AlertType.ERROR, e.getMessage());
		}

		buildGui();
	}

	@FXML
	void updateTransportdienst(ActionEvent event) {
		editableGui();

	}

	private void editContactPersoon(String voornaam, String familienaam, String telefoonnummer, String email, long id,
			long transportId) {
		try {
			dc.editContactpersoon(voornaam, familienaam, telefoonnummer, email, id, transportId);
			selectedTransportdienstDTO = dc.getTransportdienst(selectedTransportdienstDTO.getId());
			tvContactpersonen
					.setItems(FXCollections.observableArrayList(selectedTransportdienstDTO.getContactpersonen()));
		} catch (IllegalArgumentException e) {
			toonMelding(AlertType.ERROR, e.getMessage());
			selectedTransportdienstDTO = dc.getTransportdienst(selectedTransportdienstDTO.getId());
			tvContactpersonen
					.setItems(FXCollections.observableArrayList(selectedTransportdienstDTO.getContactpersonen()));
		}
		buildGui();
		editableGui();

	}

	@FXML
	void toevoegenContactpersoon(ActionEvent event) {

		try {

			ValidationService.controleerUniekEmailadres(selectedTransportdienstDTO.getContactpersonen(),
					txtEmailadresToevoegen.getText());
			ValidationService.controleerNietBlanco(txtVoornaamToevoegen.getText());
			ValidationService.controleerNietBlanco(txtFamilienaamToevoegen.getText());
			ValidationService.controleerEmail(txtEmailadresToevoegen.getText());
			ValidationService.controleerTelefoonnummer(txtTelefoonnummerToevoegen.getText());

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

		} catch (Exception e) {
			toonMelding(AlertType.ERROR, e.getMessage());
		}
		buildGui();
		editableGui();

	}

	@FXML
	void verwijderenContactpersoon(ActionEvent event) {
		try {

			if (tvContactpersonen.getItems().size() == 1) {
				throw new IllegalArgumentException("Er moet minstens 1 contactpersoon beschikbaar zijn");
			}

			int rij = tvContactpersonen.getSelectionModel().getSelectedIndex();
			ContactpersoonDTO c = tvContactpersonen.getItems().get(rij);
			dc.removeContactpersoon(c.getId(), selectedTransportdienstDTO.getId());
			this.selectedTransportdienstDTO = dc.getTransportdienst(selectedTransportdienstDTO.getId());
			tvContactpersonen
					.setItems(FXCollections.observableArrayList(selectedTransportdienstDTO.getContactpersonen()));
		} catch (IllegalArgumentException e) {
			toonMelding(AlertType.ERROR, e.getMessage());
		} catch (IndexOutOfBoundsException e) {
			toonMelding(AlertType.ERROR, "Selecteer een contactpersoon");
		}
		buildGui();
		editableGui();

	}

	private void disableGui() {
		txtBarcodeLengteRaadpleegTab.setEditable(false);
		txtTransportdienstZoeken.setEditable(false);
		txtNaamRaadpleegTab.setEditable(false);
		txtPrefixRaadpleegTab.setEditable(false);
		cbEnkelCijfersRaadpleegTab.setDisable(true);
		cbStatusRaadpleegTab.setDisable(true);
		rbOrderIdRaadpleegTab.setDisable(true);
		rbPostcodeRaadpleegTab.setDisable(true);
		btnSaveTransportdienst.setVisible(false);
		btnToevoegenContactPersoon.setVisible(false);
		btnVerwijderContactPersoon.setVisible(false);
		btnUpdateTransportdienst.setDisable(false);
		tvContactpersonen.setEditable(false);
		tvTransportdiensten.setDisable(false);
		toevoegTab.setDisable(false);
	}

	private void editableGui() {
		txtBarcodeLengteRaadpleegTab.setEditable(true);
		txtTransportdienstZoeken.setEditable(true);
		txtNaamRaadpleegTab.setEditable(true);
		txtPrefixRaadpleegTab.setEditable(true);
		cbEnkelCijfersRaadpleegTab.setDisable(false);
		cbStatusRaadpleegTab.setDisable(false);
		rbOrderIdRaadpleegTab.setDisable(false);
		rbPostcodeRaadpleegTab.setDisable(false);
		btnSaveTransportdienst.setVisible(true);
		btnToevoegenContactPersoon.setVisible(true);
		btnVerwijderContactPersoon.setVisible(true);
		btnUpdateTransportdienst.setDisable(true);
		tvContactpersonen.setEditable(true);
		tvTransportdiensten.setDisable(true);
		toevoegTab.setDisable(true);
	}

	private String getStatusTransportdienstString(boolean isActief) {
		return isActief == true ? "Actief" : "Non-actief";
	}

	private void toonMelding(AlertType type, String boodschap) {
		melding.setAlertType(type);
		melding.setContentText(boodschap);
		melding.show();
	}

}