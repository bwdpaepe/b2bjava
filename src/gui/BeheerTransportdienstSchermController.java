package gui;

import java.io.IOException;
import java.util.List;

import domein.DomeinController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import repository.TransportdienstDTO;
import repository.UserDTO;
import service.ValidationService;

public class BeheerTransportdienstSchermController extends Pane {

	@FXML
	private Label welkomNaam;

	private TableView<TransportdienstDTO> tableViewTransportdienst;
	private TableColumn<TransportdienstDTO, String> naamKolom;
	private TableColumn<TransportdienstDTO, Boolean> statusKolom;
	private TabPane tabPane;
	private GridPane gridPaneToevoegen;
	private GridPane gridPaneRaadplegen;
	private DomeinController dc;
	private ObservableList<TransportdienstDTO> list;

	public BeheerTransportdienstSchermController(DomeinController dc, List<TransportdienstDTO> transportdienstDTOLijst,
			UserDTO user) {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("BeheerTransportdienstScherm.fxml"));
		loader.setController(this);
		loader.setRoot(this);

		this.dc = dc;

		buildGuid(transportdienstDTOLijst, user);

		this.getChildren().addAll(tableViewTransportdienst, tabPane);

		try {
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	// TODO welkomnaam implementeren
	private void buildGuid(List<TransportdienstDTO> transportdienstDTOLijst, UserDTO user) {

		welkomNaam = new Label();

		// Linkerdeel scherm tableview met naam transportdienst en status
		// TODO status true/false naar actief/non-actief
		tableViewTransportdienst = new TableView<TransportdienstDTO>();
		naamKolom = new TableColumn<TransportdienstDTO, String>("Naam");
		statusKolom = new TableColumn<TransportdienstDTO, Boolean>("Status");

		tableViewTransportdienst.setLayoutX(85);
		tableViewTransportdienst.setLayoutY(170);

		tableViewTransportdienst.prefHeightProperty().set(900);

		// welkomNaam.setText(String.format("Welkom %s %s", user.getVoornaam(),
		// user.getFamilienaam()));

		list = FXCollections.observableArrayList(transportdienstDTOLijst);

		naamKolom.setCellValueFactory(new PropertyValueFactory<TransportdienstDTO, String>("naam"));
		statusKolom.setCellValueFactory(new PropertyValueFactory<TransportdienstDTO, Boolean>("isActief"));

		tableViewTransportdienst.getColumns().add(naamKolom);
		tableViewTransportdienst.getColumns().add(statusKolom);

		tableViewTransportdienst.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		tableViewTransportdienst.setItems(list);

		// Rechterdeel met tabpane
		tabPane = new TabPane();

		// Dimensies en styling instellen van de veschillende tabs
		tabPane.setLayoutX(400);
		tabPane.setLayoutY(170);

		tabPane.prefHeightProperty().set(900);
		tabPane.prefWidthProperty().set(1500);

		tabPane.setStyle("-fx-border-style:solid; -fx-padding: 1;");

		// Tab om transportdiensten toe te voegen
		Tab toevoegenTab = new Tab("Toevoegen");

		toevoegenTab.setClosable(false);

		// Opbouw tabblad toevoegen
		gridPaneToevoegen = new GridPane();
		Label lblTitel = new Label("TOEVOEGEN TRANSPORTDIENST");
		Label lblNaamTransportdienst = new Label("Naam transportdienst:");
		Label lblContactpersoonVoornaam = new Label("Contactpersoon voornaam:");
		Label lblContactpersoonFamilienaam = new Label("Contactpersoon familienaam:");
		Label lblTelefoonnummer = new Label("Telefoonnummer:");
		Label lblEmailadres = new Label("Emailadres: ");
		Label lblTrackAndTrace = new Label("TRACK AND TRACE CODE");
		Label lblVerificatiecode = new Label("Verificatiecode:");
		Label lblBarcodeLengteCode = new Label("Aantal karakters van de code:");
		Label lblBarcodePrefix = new Label("Track and Trace prefix: ");
		Label lblIsBarcodeEnkelCijfers = new Label("Bestaat de code enkel uit cijfers:");
		TextField txtNaamTransportdienst = new TextField();
		TextField txtContactpersoonVoornaam = new TextField();
		TextField txtContactpersoonFamilienaam = new TextField();
		TextField txtTelefoonnummer = new TextField();
		TextField txtEmailadres = new TextField();
		TextField txtBarcodePrefix = new TextField();
		ChoiceBox<String> cbVerificatiecode = new ChoiceBox<>();
		ChoiceBox<Integer> cbBarcodeLengte = new ChoiceBox<>();
		CheckBox cbIsBarcodeEnkelCijfers = new CheckBox();
		Button btnToevoegen = new Button("TOEVOEGEN TRANSPORTDIENST");

		// Styling gripane elementen
		btnToevoegen.setFont(Font.font("Arial", FontWeight.BOLD, 24));
		lblTitel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
		lblTrackAndTrace.setFont(Font.font("Arial", 24));

		// Opties toevoegen aan gridPane elementen
		cbVerificatiecode.getItems().add("Postcode");
		cbVerificatiecode.getItems().add("Orderid");

		for (int i = 1; i < 21; i++) {
			cbBarcodeLengte.getItems().add(i);
		}

		// Opbouw gridPane tabblad Toevoegen
		gridPaneToevoegen.setHgap(10);
		gridPaneToevoegen.setVgap(10);
		gridPaneToevoegen.setPadding(new Insets(10, 10, 10, 10));

		gridPaneToevoegen.add(lblTitel, 0, 0, 2, 1);
		gridPaneToevoegen.add(lblNaamTransportdienst, 0, 1, 1, 1);
		gridPaneToevoegen.add(txtNaamTransportdienst, 1, 1, 1, 1);
		gridPaneToevoegen.add(lblContactpersoonVoornaam, 0, 2, 1, 1);
		gridPaneToevoegen.add(txtContactpersoonVoornaam, 1, 2, 1, 1);
		gridPaneToevoegen.add(lblContactpersoonFamilienaam, 0, 3, 1, 1);
		gridPaneToevoegen.add(txtContactpersoonFamilienaam, 1, 3, 1, 1);
		gridPaneToevoegen.add(lblTelefoonnummer, 0, 4, 1, 1);
		gridPaneToevoegen.add(txtTelefoonnummer, 1, 4, 1, 1);
		gridPaneToevoegen.add(lblEmailadres, 0, 5, 1, 1);
		gridPaneToevoegen.add(txtEmailadres, 1, 5, 1, 1);
		gridPaneToevoegen.add(lblTrackAndTrace, 0, 6, 2, 1);
		gridPaneToevoegen.add(lblVerificatiecode, 0, 7, 1, 1);
		gridPaneToevoegen.add(cbVerificatiecode, 1, 7, 1, 1);
		gridPaneToevoegen.add(lblBarcodeLengteCode, 0, 8, 1, 1);
		gridPaneToevoegen.add(cbBarcodeLengte, 1, 8, 1, 1);
		gridPaneToevoegen.add(lblBarcodePrefix, 0, 9, 1, 1);
		gridPaneToevoegen.add(txtBarcodePrefix, 1, 9, 1, 1);
		gridPaneToevoegen.add(lblIsBarcodeEnkelCijfers, 0, 10, 1, 1);
		gridPaneToevoegen.add(cbIsBarcodeEnkelCijfers, 1, 10, 1, 1);
		gridPaneToevoegen.add(btnToevoegen, 0, 11, 2, 2);

		// Events tabblad toevoegen
		btnToevoegen.setOnAction(evt -> {

			try {
				String naamTransportdienst = txtNaamTransportdienst.getText();
				int barcodeLengte = (Integer) cbBarcodeLengte.getValue();
				boolean isBarcodeEnkelCijfers = cbIsBarcodeEnkelCijfers.isSelected();
				String barcodePrefix = txtBarcodePrefix.getText();
				String verificatiecode = (String) cbVerificatiecode.getValue();
				String contactVoornaam = txtContactpersoonVoornaam.getText();
				String contactFamilienaam = txtContactpersoonFamilienaam.getText();
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
				ValidationService.controleerTelefoonnummer(contactTelefoon);
				ValidationService.controleerEmail(contactEmailadres);

				// Aanmaken van een transportdienst
				dc.maakTransportdienst(naamTransportdienst, barcodeLengte, isBarcodeEnkelCijfers, barcodePrefix,
						verificatiecode, contactVoornaam, contactFamilienaam, contactTelefoon, contactEmailadres,
						bedrijfsId);

			} catch (IllegalArgumentException e) {
				// TODO degelijk errorbericht aanmaken
				System.out.println(e);
			}

			list = FXCollections.observableArrayList(dc.getTransportdienstenDTO());
			tableViewTransportdienst.setItems(list);
		});

		toevoegenTab.setContent(gridPaneToevoegen);

		// Tab om transportdiensten te raadplegen en aan te passen
		Tab raadpleegTab = new Tab("Raadplegen");

		raadpleegTab.setClosable(false);

		// Opbouw tabblad toevoegen
		gridPaneRaadplegen = new GridPane();
		Label lblTitelRaadpleegTab = new Label("RAADPLEGEN TRANSPORTDIENST");
		Label lblNaamTransportdienstRaadpleegTab = new Label("Naam transportdienst:");
		Label lblContactpersoonVoornaamRaadpleegTab = new Label("Contactpersoon voornaam:");
		Label lblContactpersoonFamilienaamRaadpleegTab = new Label("Contactpersoon familienaam:");
		Label lblTelefoonnummerRaadpleegTab = new Label("Telefoonnummer:");
		Label lblEmailadresRaadpleegTab = new Label("Emailadres: ");
		Label lblTrackAndTraceRaadpleegTab = new Label("TRACK AND TRACE CODE");
		Label lblVerificatiecodeRaadpleegTab = new Label("Verificatiecode:");
		Label lblBarcodeLengteCodeRaadpleegTab = new Label("Aantal karakters van de code:");
		Label lblBarcodePrefixRaadpleegTab = new Label("Track and Trace prefix: ");
		Label lblIsBarcodeEnkelCijfersRaadpleegTab = new Label("Bestaat de code enkel uit cijfers:");
		Label lblVerificatiecodeRaadpleegTabtd = new Label("HIER KOMT DE VERIFICATIECODE VAN DE GESELECTEERDE TD");
		Label lblBarcodePrefixRaadpleegTabtd = new Label("HIER KOMT DE BARCODE PREFIX VAN DE GESELECTEERDE TD");

		Label lblBarcodeLengteRaadpleegTabtd = new Label("HIER KOMT DE BARCODE LENGTE VAN DE GESELECTEERDE TD");
		Label lblIsBarcodeEnkelCijfersRaadpleegTabtd = new Label(
				"HIER KOMT DE BARCODE ENKEL CIJFERS VAN DE GESELECTEERDE TD");
		TextField txtNaamTransportdienstRaadpleegTab = new TextField();
		TextField txtContactpersoonVoornaamRaadpleegTab = new TextField();
		TextField txtContactpersoonFamilienaamRaadpleegTab = new TextField();
		TextField txtTelefoonnummerRaadpleegTab = new TextField();
		TextField txtEmailadresRaadpleegTab = new TextField();
		TextField txtBarcodePrefixRaadpleegTab = new TextField();
		ChoiceBox<String> cbVerificatiecodeRaadpleegTab = new ChoiceBox<>();
		ChoiceBox<Integer> cbBarcodeLengteRaadpleegTab = new ChoiceBox<>();
		CheckBox cbIsBarcodeEnkelCijfersRaadpleegTab = new CheckBox();
		Button btnAanpassen = new Button("AANPASSEN TRANSPORTDIENST");

		// Styling gripane elementen
		btnAanpassen.setFont(Font.font("Arial", FontWeight.BOLD, 24));
		lblTitelRaadpleegTab.setFont(Font.font("Arial", FontWeight.BOLD, 24));
		lblTrackAndTraceRaadpleegTab.setFont(Font.font("Arial", 24));

		// Opties toevoegen aan gridPane elementen
		cbVerificatiecodeRaadpleegTab.getItems().add("Postcode");
		cbVerificatiecodeRaadpleegTab.getItems().add("Orderid");

		for (int i = 1; i < 21; i++) {
			cbBarcodeLengteRaadpleegTab.getItems().add(i);
		}

		// Opbouw gridPane tabblad Toevoegen
		gridPaneRaadplegen.setHgap(10);
		gridPaneRaadplegen.setVgap(10);
		gridPaneRaadplegen.setPadding(new Insets(10, 10, 10, 10));

		gridPaneRaadplegen.add(lblTitelRaadpleegTab, 0, 0, 2, 1);
		gridPaneRaadplegen.add(lblNaamTransportdienstRaadpleegTab, 0, 1, 1, 1);
		gridPaneRaadplegen.add(txtNaamTransportdienstRaadpleegTab, 1, 1, 1, 1);
		gridPaneRaadplegen.add(lblContactpersoonVoornaamRaadpleegTab, 0, 2, 1, 1);
		gridPaneRaadplegen.add(txtContactpersoonVoornaamRaadpleegTab, 1, 2, 1, 1);
		gridPaneRaadplegen.add(lblContactpersoonFamilienaamRaadpleegTab, 0, 3, 1, 1);
		gridPaneRaadplegen.add(txtContactpersoonFamilienaamRaadpleegTab, 1, 3, 1, 1);
		gridPaneRaadplegen.add(lblTelefoonnummerRaadpleegTab, 0, 4, 1, 1);
		gridPaneRaadplegen.add(txtTelefoonnummerRaadpleegTab, 1, 4, 1, 1);
		gridPaneRaadplegen.add(lblEmailadresRaadpleegTab, 0, 5, 1, 1);
		gridPaneRaadplegen.add(txtEmailadresRaadpleegTab, 1, 5, 1, 1);
		gridPaneRaadplegen.add(lblTrackAndTraceRaadpleegTab, 0, 6, 2, 1);
		gridPaneRaadplegen.add(lblVerificatiecodeRaadpleegTab, 0, 7, 1, 1);
		gridPaneRaadplegen.add(lblVerificatiecodeRaadpleegTabtd, 1, 7, 1, 1);
		gridPaneRaadplegen.add(cbVerificatiecodeRaadpleegTab, 1, 7, 1, 1);
		gridPaneRaadplegen.add(lblBarcodeLengteCodeRaadpleegTab, 0, 8, 1, 1);
		gridPaneRaadplegen.add(cbBarcodeLengteRaadpleegTab, 1, 8, 1, 1);
		gridPaneRaadplegen.add(lblBarcodeLengteRaadpleegTabtd, 1, 8, 1, 1);
		gridPaneRaadplegen.add(lblBarcodePrefixRaadpleegTab, 0, 9, 1, 1);
		gridPaneRaadplegen.add(txtBarcodePrefixRaadpleegTab, 1, 9, 1, 1);
		gridPaneRaadplegen.add(lblBarcodePrefixRaadpleegTabtd, 1, 9, 1, 1);
		gridPaneRaadplegen.add(lblIsBarcodeEnkelCijfersRaadpleegTab, 0, 10, 1, 1);
		gridPaneRaadplegen.add(cbIsBarcodeEnkelCijfersRaadpleegTab, 1, 10, 1, 1);
		gridPaneRaadplegen.add(lblIsBarcodeEnkelCijfersRaadpleegTabtd, 1, 10, 1, 1);
		gridPaneRaadplegen.add(btnAanpassen, 0, 11, 2, 2);

		txtNaamTransportdienstRaadpleegTab.setEditable(false);
		txtContactpersoonVoornaamRaadpleegTab.setEditable(false);
		txtContactpersoonFamilienaamRaadpleegTab.setEditable(false);
		txtTelefoonnummerRaadpleegTab.setEditable(false);
		txtEmailadresRaadpleegTab.setEditable(false);

		// TODO deze 4 velden een label toevoegen met de waarde van de geselecteerde td,
		// bij drukken button aanpassen kan je deze zaken terug visible zetten
		cbVerificatiecodeRaadpleegTab.setVisible(false);
		txtBarcodePrefixRaadpleegTab.setVisible(false);
		cbBarcodeLengteRaadpleegTab.setVisible(false);
		cbIsBarcodeEnkelCijfersRaadpleegTab.setVisible(false);

		raadpleegTab.setContent(gridPaneRaadplegen);
		
		btnAanpassen.setOnAction(evt -> {
			//TODO drukken button moeten alle velden editable zijn
			//Placeholder moet nog een worden toegevoegd
			//Event van tablview moet nog gemaakt worden
		});

		tabPane.getTabs().addAll(toevoegenTab, raadpleegTab);

	}

}
