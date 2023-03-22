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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
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
	private Label lblDozenVoorVerpakking;
	/*@FXML
	private Label txtTransportDienst;*/
	@FXML
	private Label lblNaamKlant;
	@FXML
	private Label lblNaamAankoper;
	@FXML
	private Label lblEmailAankoper;
	@FXML
	private Label lblOrderId;
	@FXML
	private Label lblDatumGeplaatst;
	@FXML
	private Label lblLeveradres;
	@FXML
	private Label lblStatus;
	@FXML
	private Label lblTotaleOrderbedrag;
	@FXML
	private ComboBox<TransportdienstDTO> cmbTransportdienst;
	@FXML
	private Label lblTrackTraceGegevens;
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
		
		//WE KUNNEN DE TTC HALEN UIT DE BESTELLING, WE MOETEN DIT NIET MEEGEVEN ALS PARAMETER
		String trackAndTraceCodeBestelling = lblTrackTraceGegevens.getText();
		
		dc.wijzigBestelling(geselecteerdeBestellingDTOId, geselecteerdeTransportdienstDTOId);
		
		//***toon gewijzigd BestellingDTO op het scherm***
		BestellingDTO gewijzigdeDetailBestellingDTO = dc.getBestelling(geselecteerdeBestellingDTOId);
		maakVisueelDetailBestelling(gewijzigdeDetailBestellingDTO);
		//***laadt de lijst met bestellingen***
		loadBestellingen();
		
		
	}
	
	public void disableDetail() {
		/*lblDozenVoorVerpakking.setDisable(true);
		lblNaamKlant.setDisable(true);
		lblNaamAankoper.setDisable(true);
		lblEmailAankoper.setDisable(true);
		lblOrderId.setDisable(true);
		lblDatumGeplaatst.setDisable(true);
		lblLeveradres.setDisable(true);
		lblStatus.setDisable(true);
		lblTotaleOrderbedrag.setDisable(true);
		*/
		cmbTransportdienst.setDisable(true);
		//cmbTransportdienst.setStyle("-fx-opacity: 1.0;-fx-background-color: #EC4842;-fx-text-fill: #000000;");
		lblTrackTraceGegevens.setDisable(true);
		lblTrackTraceGegevens.setStyle("-fx-opacity: 1.0;");
		btnWijzigBestelling.setDisable(true);
		btnWijzigBestelling.setStyle("-fx-opacity: 1.0;");
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
		lblDozenVoorVerpakking.setText(maakVisueelDoosType(bestellingDTO));
		//lblDozenVoorVerpakking.setDisable(true);
		lblNaamKlant.setText(bestellingDTO.getKlantNaam());
		//lblNaamKlant.setDisable(true);
		lblNaamAankoper.setText(maakVisueelNaamAankoper(bestellingDTO));
		//lblNaamAankoper.setDisable(true);
		lblEmailAankoper.setText(maakVisueelEmailAankoper(bestellingDTO));
		//lblEmailAankoper.setDisable(true);
		lblOrderId.setText(bestellingDTO.getOrderID());
		//lblOrderId.setDisable(true);
		//Date date = newBestellingDTO.getDatumGeplaatst();  
		//DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");  
		//String strDate = dateFormat.format(date);  
		lblDatumGeplaatst.setText(DateFormat.getDateInstance().format(bestellingDTO.getDatumGeplaatst()));
		//lblDatumGeplaatst.setDisable(true);
		lblLeveradres.setText(maakVisueelLeveradres(bestellingDTO));
		//lblLeveradres.setDisable(true);
		lblStatus.setText(bestellingDTO.getStatus());
		//lblStatus.setDisable(true);
		lblTotaleOrderbedrag.setText(String.format("%.2f€", bestellingDTO.getTotaalbedrag()));
		//lblTotaleOrderbedrag.setDisable(true);
		// transportdiensten van bedrijf van actueel ingelogde gebruiker
		ObservableList<TransportdienstDTO> transportdienstDTOList = FXCollections.observableList(dc.getTransportdienstenDTO());
		long geselecteerdeTransportdienstDTOId = dc.getTransportdienst(bestellingDTO.getTransportdienstID()).getId();
		//cmbTransportdienst.getSelectionModel().clearSelection();
		//cmbTransportdienst.getItems().clear();
		//cmbTransportdienst.valueProperty().set(null);
		cmbTransportdienst.setItems(transportdienstDTOList);
		cmbTransportdienst.setValue(filterTransportdienst(transportdienstDTOList, geselecteerdeTransportdienstDTOId));
		cmbTransportdienst.setCellFactory(new Callback<ListView<TransportdienstDTO>, ListCell<TransportdienstDTO>>() {
            @Override
            public ListCell<TransportdienstDTO> call(ListView<TransportdienstDTO> param) {
                return new ListCell<TransportdienstDTO>() {
                    @Override
                    protected void updateItem(TransportdienstDTO item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                        } else {
                            setText(item.getNaam()); // use the name property of the TransportdienstDTO object
                        }
                    }
                };
            }
        });
		cmbTransportdienst.setButtonCell(new ListCell<TransportdienstDTO>() {
            @Override
            protected void updateItem(TransportdienstDTO item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getNaam()); // use the name property of the TransportdienstDTO object
                }
            }
        });
		if(bestellingDTO.getStatus().toUpperCase().equals(VERWERKT)) {
			cmbTransportdienst.setDisable(false);
			btnWijzigBestelling.setDisable(false);
		}
		else {
			cmbTransportdienst.setDisable(true);
			//cmbTransportdienst.setStyle("-fx-opacity: 1.0;-fx-background-color: #EC4842;-fx-text-fill: #000000;");
			btnWijzigBestelling.setDisable(true);
			btnWijzigBestelling.setStyle("-fx-opacity: 1.0;");
		}
		lblTrackTraceGegevens.setText("ToDo");
		if(bestellingDTO.getStatus().toUpperCase().equals(VERWERKT)) {
			lblTrackTraceGegevens.setDisable(false);
			btnWijzigBestelling.setDisable(false);
		}
		else {
			lblTrackTraceGegevens.setDisable(true);
			lblTrackTraceGegevens.setStyle("-fx-opacity: 1.0;-fx-background-color: #EC4842;");
			btnWijzigBestelling.setDisable(true);
			btnWijzigBestelling.setStyle("-fx-opacity: 1.0;");
		}
	}
	
	private TransportdienstDTO filterTransportdienst(ObservableList<TransportdienstDTO> transportdienstDTOList, long geselecteerdeTransportdienstDTOId) {
		return transportdienstDTOList.stream().filter(td -> (td.getId() == geselecteerdeTransportdienstDTOId)).findFirst().get();
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
