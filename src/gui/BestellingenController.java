package gui;

import java.text.DateFormat;

import domein.DomeinController;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import repository.BestellingDTO;
import repository.TransportdienstDTO;
import repository.KlantLijstEntryDTO;

public class BestellingenController extends Pane {
	private DomeinController dc;
	@FXML
	private TableView<BestellingDTO> tvBestellingen;
	@FXML
	TableColumn<BestellingDTO, String> orderColumn;
	@FXML
	TableColumn<BestellingDTO, String> statusColumn;
	@FXML
	TableColumn<BestellingDTO, String> klantColumn;
	@FXML
	TableColumn<BestellingDTO, String> transportdienstColumn;
	@FXML
	TableColumn<BestellingDTO, String> datumColumn;
	@FXML
	private Label lblDetailsBestelling;
	@FXML
	private Button btnWijzigBestelling;
	@FXML
	private Label txtDozenVoorVerpakking;
	@FXML
	private Label txtTransportDienst;
	@FXML
	private TextField txtNaamKlant;
	@FXML
	private TextField txtNaamAankoper;
	@FXML
	private TextField txtEmailAankoper;
	@FXML
	private TextField txtOrderId;
	@FXML
	private TextField txtDatumGeplaatst;
	@FXML
	private TextField txtLeveradres;
	@FXML
	private TextField txtStatus;
	@FXML
	private TextField txtTotaleOrderbedrag;
	@FXML
	private ComboBox<TransportdienstDTO> cmbTransportdienst;
	@FXML
	private TextField txtTrackTraceGegevens;
	//ToDo add ProductDTO
	@FXML
	private TableColumn productNaamColumn;
	@FXML
	private TableColumn productAantalColumn;
	@FXML
	private TableColumn productEenheidsprijsColumn;
	@FXML
	private TableColumn productTotalePrijsColumn;
	
	@FXML
	TextField tfBestellingZoeken;

	public BestellingenController() {

	}

	public void loadBestellingen() {

		//assign values to columns
		ObservableList<BestellingDTO> bestellingList = FXCollections.observableList(dc.getBestellingen());
		orderColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOrderID()));
		statusColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus()));
		klantColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getKlantNaam()));
		transportdienstColumn.setCellValueFactory(
				cellData -> new SimpleStringProperty(cellData.getValue().getTransportdienstNaam()));
		datumColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
				DateFormat.getDateInstance().format(cellData.getValue().getDatumGeplaatst())));

		
		
	    // Create a filtered list that wraps the original list
	    FilteredList<BestellingDTO> filteredList = new FilteredList<>(bestellingList, p -> true);

	    // Add a listener to the search field
	    tfBestellingZoeken.textProperty().addListener((observable, oldValue, newValue) -> {
	        filteredList.setPredicate(bestelling -> {
	            // If search field is empty, show all entries
	            if (newValue == null || newValue.isEmpty()) {
	                return true;
	            }
	            // Zoektekst naar lowercase
	            String lowerCaseFilter = newValue.toLowerCase();
	            // controleer of orderID de zoektekst bevat
	            if (bestelling.getKlantNaam().toLowerCase().contains(lowerCaseFilter)) {
	                return true;
	            }
	            return false;
	        });
	    });
	    
	    // Wrap the filtered list in a sorted list and add it to the table view
	    SortedList<BestellingDTO> sortedList = new SortedList<>(filteredList);
	    sortedList.comparatorProperty().bind(tvBestellingen.comparatorProperty());
	    tvBestellingen.setItems(sortedList);

	}

	public void setParams(DomeinController dc) {

		this.dc = dc;
	}

}
