package gui;

import java.io.IOException;
import java.util.List;

import domein.DomeinController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import repository.TransportdienstDTO;
import repository.UserDTO;

public class BeheerTransportdienstSchermController extends Pane {

	@FXML
	private Label welkomNaam;
	

	private TableView<TransportdienstDTO> tableViewTransportdienst;
	private TableColumn<TransportdienstDTO, String> naamKolom;
	private TableColumn<TransportdienstDTO, Boolean> statusKolom;
	private TabPane tabPane;

	private DomeinController dc;

	public BeheerTransportdienstSchermController(DomeinController dc,
			List<TransportdienstDTO> transportdienstDTOLijst, UserDTO user) {

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

	//TODO welkomnaam implementeren
	private void buildGuid(List<TransportdienstDTO> transportdienstDTOLijst, UserDTO user) {
		
		welkomNaam = new Label();
		tableViewTransportdienst = new TableView<TransportdienstDTO>();
		naamKolom = new TableColumn<TransportdienstDTO, String>("Naam");
		statusKolom = new TableColumn<TransportdienstDTO, Boolean>("Status");
		tabPane = new TabPane();
		Tab toevoegenTab = new Tab("Toevoegen");
		Tab raadpleegTab = new Tab("Raadplegen");
		
		tabPane.getTabs().addAll(toevoegenTab, raadpleegTab);
		
		tabPane.setLayoutX(400);
		tabPane.setLayoutY(170);
		
		tabPane.prefHeightProperty().set(900);
		tabPane.prefWidthProperty().set(1500);
		
		welkomNaam.setText(String.format("Welkom %s %s", user.getVoornaam(), user.getFamilienaam()));

		ObservableList<TransportdienstDTO> list = FXCollections.observableArrayList(transportdienstDTOLijst);

		
		tableViewTransportdienst.setLayoutX(85);
		tableViewTransportdienst.setLayoutY(170);
		
		tableViewTransportdienst.prefHeightProperty().set(900);
		
		naamKolom.setCellValueFactory(new PropertyValueFactory<TransportdienstDTO, String>("naam"));
		statusKolom.setCellValueFactory(new PropertyValueFactory<TransportdienstDTO, Boolean>("isActief"));

		tableViewTransportdienst.getColumns().add(naamKolom);
		tableViewTransportdienst.getColumns().add(statusKolom);

		tableViewTransportdienst.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		tableViewTransportdienst.setItems(list);


	}

}
