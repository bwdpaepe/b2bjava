package gui;

import java.text.DateFormat;

import domein.DomeinController;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import repository.BestellingDTO;
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
