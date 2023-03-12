package gui;



import domein.DomeinController;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import repository.KlantLijstEntryDTO;


public class KlantenController extends Pane {
	private DomeinController dc;

	@FXML
	private Label errorMessage;
	@FXML
	private Pane errorWindow;
	@FXML
	private Button closeButton;
	
	@FXML
	private TableView<KlantLijstEntryDTO> tvKlantenLijst;
	@FXML
	TableColumn<KlantLijstEntryDTO, String> naamColumn;
	@FXML
	TableColumn<KlantLijstEntryDTO, Number> aantalBestellingenColumn;
	@FXML
	TextField tfKlantZoeken;
	
	public KlantenController() {

	}	
	
	public void setParams(DomeinController dc) {
		this.dc = dc;
	}
	
	public void loadKlanten() {
	    System.out.println("Klanten lijst loading");
	    ObservableList<KlantLijstEntryDTO> klantenList = FXCollections.observableList(dc.geefLijstVanKlantenMetAantalOpenstaandeBestellingen());

	    naamColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getKlantNaam()));
//	    aantalBestellingenColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAantalOpenBestellingen().toString()));
	    aantalBestellingenColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty((int) cellData.getValue().getAantalOpenBestellingen()));


	    // Create a filtered list that wraps the original list
	    FilteredList<KlantLijstEntryDTO> filteredList = new FilteredList<>(klantenList, p -> true);

	    // Add a listener to the search field
	    tfKlantZoeken.textProperty().addListener((observable, oldValue, newValue) -> {
	        filteredList.setPredicate(klant -> {
	            // If search field is empty, show all entries
	            if (newValue == null || newValue.isEmpty()) {
	                return true;
	            }
	            // Zoektekst naar lowercase
	            String lowerCaseFilter = newValue.toLowerCase();
	            // controleer of klantnaam de zoektekst bevat
	            if (klant.getKlantNaam().toLowerCase().contains(lowerCaseFilter)) {
	                return true;
	            }
	            return false;
	        });
	    });

	    // Wrap the filtered list in a sorted list and add it to the table view
	    SortedList<KlantLijstEntryDTO> sortedList = new SortedList<>(filteredList);
	    sortedList.comparatorProperty().bind(tvKlantenLijst.comparatorProperty());
	    tvKlantenLijst.setItems(sortedList);
	}
}
