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

public class BeheerTransportdienstSchermController extends Pane {

	@FXML
	private Label welkomNaam;

	private TableView<TransportdienstDTO> tableViewTransportdienst;
	private TableColumn<TransportdienstDTO, String> naamKolom;
	private TableColumn<TransportdienstDTO, Boolean> statusKolom;
	private TabPane tabPane;
	private GridPane gridPane;

	private DomeinController dc;

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
		// Initialisatie van de verschillende elementen op het scherm
		welkomNaam = new Label();
		tableViewTransportdienst = new TableView<TransportdienstDTO>();
		naamKolom = new TableColumn<TransportdienstDTO, String>("Naam");
		statusKolom = new TableColumn<TransportdienstDTO, Boolean>("Status");
		tabPane = new TabPane();
		gridPane = new GridPane();
		Tab toevoegenTab = new Tab("Toevoegen");
		Tab raadpleegTab = new Tab("Raadplegen");

		Label lblTitel = new Label("TOEVOEGEN TRANSPORTDIENST");
		Label lblNaamTransportdienst = new Label("Naam transportdienst:");
		Label lblContactpersoon = new Label("Contactpersoon:");
		Label lblTelefoonnummer = new Label("Telefoonnummer:");
		Label lblEmailadres = new Label("Emailadres: ");
		Label lblTrackAndTrace = new Label("TRACK AND TRACE CODE");
		Label lblVerificatiecode = new Label("Verificatiecode:");
		Label lblLengteCode = new Label("Aantal karakters van de code:");
		Label lblPrefix = new Label("Track and Trace prefix: ");
		Label lblEnkelCijfers = new Label("Bestaat de code enkel uit cijfers:");
		TextField txtNaamTransportdienst = new TextField();
		TextField txtContactpersoon = new TextField();
		TextField txtTelefoonnummer = new TextField();
		TextField txtEmailadres = new TextField();
		ChoiceBox<String> cbVerificatiecode = new ChoiceBox<>();
		ChoiceBox<Integer> cbLengteCode = new ChoiceBox<>();
		TextField txtPrefix = new TextField();
		CheckBox cbEnkelCijfers = new CheckBox();
		Button btnToevoegen = new Button("TOEVOEGEN TRANSPORTDIENST");

		// Styling gripane elementen
		btnToevoegen.setFont(Font.font("Arial", FontWeight.BOLD, 24));
		lblTitel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
		lblTrackAndTrace.setFont(Font.font("Arial", 24));

		// Opties toevoegen aan gridPane elementen
		cbVerificatiecode.getItems().add("Postcode");
		cbVerificatiecode.getItems().add("Orderid");

		for (int i = 1; i < 21; i++) {
			cbLengteCode.getItems().add(i);
		}
		
		//gridPane action events
		
		btnToevoegen.setOnAction(evt ->{
			System.out.println("Button test");
			
			//TODO verder dit event afhandelen
		});

		// Opbouw gridPane
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.setPadding(new Insets(10, 10, 10, 10));

		gridPane.add(lblTitel, 0, 0, 2, 1);
		gridPane.add(lblNaamTransportdienst, 0, 1, 1, 1);
		gridPane.add(txtNaamTransportdienst, 1, 1, 1, 1);
		gridPane.add(lblContactpersoon, 0, 2, 1, 1);
		gridPane.add(txtContactpersoon, 1, 2, 1, 1);
		gridPane.add(lblTelefoonnummer, 0, 3, 1, 1);
		gridPane.add(txtTelefoonnummer, 1, 3, 1, 1);
		gridPane.add(lblEmailadres, 0, 4, 1, 1);
		gridPane.add(txtEmailadres, 1, 4, 1, 1);
		gridPane.add(lblTrackAndTrace, 0, 5, 2, 1);
		gridPane.add(lblVerificatiecode, 0, 6, 1, 1);
		gridPane.add(cbVerificatiecode, 1, 6, 1, 1);
		gridPane.add(lblLengteCode, 0, 7, 1, 1);
		gridPane.add(cbLengteCode, 1, 7, 1, 1);
		gridPane.add(lblPrefix, 0, 8, 1, 1);
		gridPane.add(txtPrefix, 1, 8, 1, 1);
		gridPane.add(lblEnkelCijfers, 0, 9, 1, 1);
		gridPane.add(cbEnkelCijfers, 1, 9, 1, 1);
		gridPane.add(btnToevoegen, 0, 10, 2, 2);
		
		//Events gridpane

		toevoegenTab.setClosable(false);
		raadpleegTab.setClosable(false);

		toevoegenTab.setContent(gridPane);

		tabPane.getTabs().addAll(toevoegenTab, raadpleegTab);

		// Dimensies en styling instellen van de veschillende tabs
		tabPane.setLayoutX(400);
		tabPane.setLayoutY(170);

		tabPane.prefHeightProperty().set(900);
		tabPane.prefWidthProperty().set(1500);

		tabPane.setStyle("-fx-border-style:solid; -fx-padding: 1;");

		tableViewTransportdienst.setLayoutX(85);
		tableViewTransportdienst.setLayoutY(170);

		tableViewTransportdienst.prefHeightProperty().set(900);

		//

		welkomNaam.setText(String.format("Welkom %s %s", user.getVoornaam(), user.getFamilienaam()));

		ObservableList<TransportdienstDTO> list = FXCollections.observableArrayList(transportdienstDTOLijst);

		naamKolom.setCellValueFactory(new PropertyValueFactory<TransportdienstDTO, String>("naam"));
		statusKolom.setCellValueFactory(new PropertyValueFactory<TransportdienstDTO, Boolean>("isActief"));

		tableViewTransportdienst.getColumns().add(naamKolom);
		tableViewTransportdienst.getColumns().add(statusKolom);

		tableViewTransportdienst.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		tableViewTransportdienst.setItems(list);

	}

}
