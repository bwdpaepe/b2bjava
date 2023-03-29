package gui;



import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import domein.DomeinController;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.StringConverter;
import repository.AankoperDetailsDTO;
import repository.BestellingDetailsDTO;
import repository.KlantAankopersBestellingenDTO;
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
	
	@FXML
	private Label labelKlantDetailsNaam;
	@FXML
	private Label klantDetailLabelKlantStraatNummer;
	@FXML
	private Label klantDetailLabelKlantPostcodeStad;
	@FXML
	private Label klantDetailLabelKlantLand;
	@FXML
	private Label klantDetailLabelKlantTelefoonnr;
	
	@FXML
	private ImageView ivLogoKlant;
	
	@FXML
	private TableView<AankoperDetailsDTO> tvKlantDetailAankopers;
	@FXML
	TableColumn<AankoperDetailsDTO, String> naamAankoperColumn;
	@FXML
	TableColumn<AankoperDetailsDTO, String> emailAankoperColumn;
	
	@FXML
	private TableView<BestellingDetailsDTO> tvKlantDetailBestellingen;
	@FXML
	TableColumn<BestellingDetailsDTO, String> orderIdColumn;
	@FXML
	TableColumn<BestellingDetailsDTO, Date> orderDatumColumn;
	@FXML
	TableColumn<BestellingDetailsDTO, String> orderStatusColumn;

	
	public KlantenController() {

	}	
	
	public void setParams(DomeinController dc) {
		this.dc = dc;
	}
	
	public void loadKlanten() {
	    //System.out.println("Klanten lijst loading");
	    ObservableList<KlantLijstEntryDTO> klantenList = FXCollections.observableList(dc.geefLijstVanKlantenMetAantalOpenstaandeBestellingen());

	    naamColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getKlantNaam()));
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
	    
	    // Listener om de details van een geselecteerde klant op te halen
	    tvKlantenLijst.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
	        loadSelectedKlantDetails(newValue);
	    });
	}
	
	private void loadSelectedKlantDetails(KlantLijstEntryDTO selectedKlant) {
		//System.out.println("Details aan het ophalen van klant: " + selectedKlant.getKlantNaam());
	    if (selectedKlant != null) {
	        KlantAankopersBestellingenDTO klantDetails = dc.geefDetailsVanKlant(selectedKlant.getKlantId());
	        //System.out.println(klantDetails);
	        
	        // Hier verdere methodes aanmaken/aanroepen om de GUI up te daten
	        setLabelsInBorderpane(klantDetails);
	        setBedrijfLogo(klantDetails.getLogo_filename());
	        setTableViewKlantAankopers(klantDetails.getAankopers());
	        setTableViewKlantDetailBestellingen(klantDetails.getBestellingen());
	    }
	}

	private void setLabelsInBorderpane(KlantAankopersBestellingenDTO klantDetails)
	{
		labelKlantDetailsNaam.setText(klantDetails.getKlantNaam().toUpperCase());
		klantDetailLabelKlantStraatNummer.setText(klantDetails.getStraat() + " " + klantDetails.getHuisnummer());
        klantDetailLabelKlantPostcodeStad.setText(klantDetails.getPostcode() + " " + klantDetails.getStad());
        klantDetailLabelKlantLand.setText(klantDetails.getLand());
        klantDetailLabelKlantTelefoonnr.setText(klantDetails.getTelefoonnummer());
	}
	
	private void setBedrijfLogo(String logo_filename) {
	    if (logo_filename != null) {
	        try {
	            InputStream logoStream = getClass().getResourceAsStream("/gui_images/bedrijflogos/" + logo_filename);
	            if (logoStream != null) {
	                Image logo = new Image(logoStream);
	                ivLogoKlant.setImage(logo);
	            } else {
	                ivLogoKlant.setImage(null);
	            }
	        } catch (Exception e) {
	            System.err.println("Error loading logo image: " + e.getMessage());
	            ivLogoKlant.setImage(null);
	        }
	    } else {
	        ivLogoKlant.setImage(null);
	    }
	}
	
	private void setTableViewKlantAankopers(List<AankoperDetailsDTO> aankopers)
	{
		ObservableList<AankoperDetailsDTO> aankopersList = FXCollections.observableList(aankopers);
		
		naamAankoperColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getVoornaam() + " " + cellData.getValue().getFamilienaam()));
		emailAankoperColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmailadres()));
		
		SortedList<AankoperDetailsDTO> sortedList = new SortedList<>(aankopersList);
		sortedList.comparatorProperty().bind(tvKlantDetailAankopers.comparatorProperty());
		tvKlantDetailAankopers.setItems(sortedList);
	}
	
	private void setTableViewKlantDetailBestellingen(List<BestellingDetailsDTO> bestellingen)
	{
		ObservableList<BestellingDetailsDTO> bestellingenList = FXCollections.observableList(bestellingen);
		
		// Order ID data toevoegen in kolom
		orderIdColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOrderId()));

		// Datum van de Order eerst omzetten naar geschikt formaat alvorens toe te voegen aan kolom
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		orderDatumColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<Date>(cellData.getValue().getDatumGeplaatst()));
		orderDatumColumn.setCellFactory(col -> new TextFieldTableCell<BestellingDetailsDTO, Date>(new StringConverter<Date>()
		{
			@Override
			public String toString(Date date)
			{
				return dateFormat.format(date);
			}

			@Override
			public Date fromString(String string)
			{
				throw new UnsupportedOperationException("Not supported.");
			}	
		}));
		
		// Status van bestelling toevoegen in kolom
		orderStatusColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus()));
		
		SortedList<BestellingDetailsDTO> sortedList = new SortedList<>(bestellingenList);
		sortedList.comparatorProperty().bind(tvKlantDetailBestellingen.comparatorProperty());
		tvKlantDetailBestellingen.setItems(sortedList);
	}	
}