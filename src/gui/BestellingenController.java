package gui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import repository.BesteldProductDTO;
import repository.BestellingDTO;
import repository.DoosDTO;
import repository.TransportdienstDTO;
import repository.KlantLijstEntryDTO;
import repository.MedewerkerDTO;

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
	private TextField txtDozenVoorVerpakking;
	/*@FXML
	private Label txtTransportDienst;*/
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
	@FXML
	private TableView<BesteldProductDTO> tvBesteldeProducten;
	@FXML
	TableColumn<BesteldProductDTO, String> productNaamColumn;
	@FXML
	TableColumn<BesteldProductDTO, String> productAantalColumn;
	@FXML
	TableColumn<BesteldProductDTO, String> productEenheidsprijsColumn;
	@FXML
	TableColumn<BesteldProductDTO, String> productTotalePrijsColumn;
	
	@FXML
	TextField tfBestellingZoeken;
	
	private final String VERWERKT = "VERWERKT";
	
	private BestellingDTO selectedBestellingDTO;

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
	
	// Event Listener on Button[#btnWijzigBestelling].onAction
	@FXML
	public void wijzigBestelling(ActionEvent event) {
		long geselecteerdeBestellingDTOId = selectedBestellingDTO.getId();
		TransportdienstDTO geselecteerdeTransportdienstDTO = cmbTransportdienst.getValue();
		long geselecteerdeTransportdienstDTOId = geselecteerdeTransportdienstDTO.getId();
		
		dc.wijzigBestelling(geselecteerdeBestellingDTOId, geselecteerdeTransportdienstDTOId);
		
		//***toon gewijzigd BestellingDTO op het scherm***
		BestellingDTO gewijzigdeDetailBestellingDTO = dc.getBestelling(geselecteerdeBestellingDTOId);
		maakVisueelDetailBestelling(gewijzigdeDetailBestellingDTO);
		
		
	}
	
	public void disableDetail() {
		txtDozenVoorVerpakking.setDisable(true);
		txtNaamKlant.setDisable(true);
		txtNaamAankoper.setDisable(true);
		txtEmailAankoper.setDisable(true);
		txtOrderId.setDisable(true);
		txtDatumGeplaatst.setDisable(true);
		txtLeveradres.setDisable(true);
		txtStatus.setDisable(true);
		txtTotaleOrderbedrag.setDisable(true);
		cmbTransportdienst.setDisable(true);
		txtTrackTraceGegevens.setDisable(true);
		btnWijzigBestelling.setDisable(true);
	}
	
	public void addListenerRijSelecteren() {
		// Een rij selecteren
		tvBestellingen.getSelectionModel().selectedItemProperty().
			addListener((observableValue, oldBestellingDTO, newBestellingDTO) -> {
				if (newBestellingDTO != null) {
					//***set selected BestellingDTO***
					selectedBestellingDTO = newBestellingDTO;
					
					//***vul het detail op***
					maakVisueelDetailBestelling(newBestellingDTO);
					
					//***Overzicht bestelde producten van deze bestelling***
					loadBesteldeProducten(newBestellingDTO);
				}
			});
	}
	
	private void maakVisueelDetailBestelling(BestellingDTO bestellingDTO) {
		lblDetailsBestelling.setText(String.format("Detail bestelling: %s", bestellingDTO.getOrderID()));
		txtDozenVoorVerpakking.setText(maakVisueelDoosType(bestellingDTO));
		txtDozenVoorVerpakking.setDisable(true);
		txtNaamKlant.setText(bestellingDTO.getKlantNaam());
		txtNaamKlant.setDisable(true);
		txtNaamAankoper.setText(maakVisueelNaamAankoper(bestellingDTO));
		txtNaamAankoper.setDisable(true);
		txtEmailAankoper.setText(maakVisueelEmailAankoper(bestellingDTO));
		txtEmailAankoper.setDisable(true);
		txtOrderId.setText(bestellingDTO.getOrderID());
		txtOrderId.setDisable(true);
		//Date date = newBestellingDTO.getDatumGeplaatst();  
		//DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");  
		//String strDate = dateFormat.format(date);  
		txtDatumGeplaatst.setText(DateFormat.getDateInstance().format(bestellingDTO.getDatumGeplaatst()));
		txtDatumGeplaatst.setDisable(true);
		txtLeveradres.setText(maakVisueelLeveradres(bestellingDTO));
		txtLeveradres.setDisable(true);
		txtStatus.setText(bestellingDTO.getStatus());
		txtStatus.setDisable(true);
		txtTotaleOrderbedrag.setText(String.format("%.2f€", bestellingDTO.getTotaalbedrag()));
		txtTotaleOrderbedrag.setDisable(true);
		// transportdiensten van bedrijf van actueel ingelogde gebruiker
		ObservableList<TransportdienstDTO> transportdienstDTOList = FXCollections.observableList(dc.getTransportdienstenDTO());
		TransportdienstDTO geselecteerdeTransportdienstDTO = dc.getTransportdienst(bestellingDTO.getTransportdienstID());
		// ToDo: toon enkel naam transportdienst ipv volledig object
		cmbTransportdienst.setItems(transportdienstDTOList);
		cmbTransportdienst.setValue(geselecteerdeTransportdienstDTO);
		if(bestellingDTO.getStatus().toUpperCase().equals(VERWERKT)) {
			cmbTransportdienst.setDisable(false);
			btnWijzigBestelling.setDisable(false);
		}
		else {
			cmbTransportdienst.setDisable(true);
			btnWijzigBestelling.setDisable(true);
		}
		txtTrackTraceGegevens.setText("ToDo");
		if(bestellingDTO.getStatus().toUpperCase().equals(VERWERKT)) {
			txtTrackTraceGegevens.setDisable(false);
			btnWijzigBestelling.setDisable(false);
		}
		else {
			txtTrackTraceGegevens.setDisable(true);
			btnWijzigBestelling.setDisable(true);
		}
	}
	
	private String maakVisueelNaamAankoper(BestellingDTO bestellingDTO) {
		MedewerkerDTO medewerkerDTO = bestellingDTO.getAankoper();
		return (medewerkerDTO.getVoornaam() + " " + medewerkerDTO.getFamilienaam());
	}
	
	private String maakVisueelEmailAankoper(BestellingDTO bestellingDTO) {
		MedewerkerDTO medewerkerDTO = bestellingDTO.getAankoper();
		return (medewerkerDTO.getEmail());
	}
	
	private String maakVisueelLeveradres(BestellingDTO bestellingDTO) {
		return (bestellingDTO.getLeveradresStraat() + " " 
				+ bestellingDTO.getLeveradresNummer() + " | "
				+ bestellingDTO.getLeveradresPostcode() + " "
				+bestellingDTO.getLeveradresStad() + " | "
				+bestellingDTO.getLeveradresLand());
	}
	
	private String maakVisueelDoosType(BestellingDTO bestellingDTO) {
		DoosDTO doosDTO = bestellingDTO.getDoos();
		return (doosDTO.getDoosType());
	}
	
	//ToDo we willen enkel de naam van de transportdienst weergeven in de combobox
	private String maakVisueelTransportdienst(TransportdienstDTO transportdienstDTO) {
		return transportdienstDTO.getNaam();
	}
	
	private void loadBesteldeProducten(BestellingDTO bestellingDTO) {
		ObservableList<BesteldProductDTO> besteldProductDTOLijst = FXCollections.observableList(bestellingDTO.getBesteldeProducten());
		productNaamColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNaam()));
		productAantalColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.format("%d", cellData.getValue().getAantal())));
		productEenheidsprijsColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.format("%.2f€",cellData.getValue().getEenheidsprijs())));
		productTotalePrijsColumn.setCellValueFactory(
				cellData -> new SimpleStringProperty(String.format("%.2f€", cellData.getValue().getSubtotaal())));
		
		tvBesteldeProducten.setItems(besteldProductDTOLijst);
		
	}

}
