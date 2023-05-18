package gui;

import java.text.DateFormat;
import java.util.Arrays;

import javax.naming.SizeLimitExceededException;
import javax.persistence.EntityNotFoundException;

import domein.DomeinController;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import repository.BesteldProductDTO;
import repository.BestellingDTO;
import repository.DoosDTO;
import repository.TransportdienstDTO;
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
	private Button btnTrackAndTrace;
	@FXML
	private Label vLblDetailsBestelling;
	@FXML
	private Button btnVerwerkBestelling;
	@FXML
	private Label vLblDozenVoorVerpakking;
	@FXML
	private Label vLblNaamKlant;
	@FXML
	private Label vLblNaamAankoper;
	@FXML
	private Label vLblEmailAankoper;
	@FXML
	private Label vLblOrderId;
	@FXML
	private Label vLblDatumGeplaatst;
	@FXML
	private Label vLblLeveradres;
	@FXML
	private Label vLblStatus;
	@FXML
	private Label vLblTotaleOrderbedrag;
	@FXML
	private ComboBox<TransportdienstDTO> vCmbTransportdienst;
	@FXML
	private Label vLblTrackTraceGegevens;
	
	@FXML
	private Label zLblDetailsBestelling;
	@FXML
	private Button btnVerzendBestelling;
	@FXML
	private Label zLblDozenVoorVerpakking;
	@FXML
	private Label zLblNaamKlant;
	@FXML
	private Label zLblNaamAankoper;
	@FXML
	private Label zLblEmailAankoper;
	@FXML
	private Label zLblOrderId;
	@FXML
	private Label zLblDatumGeplaatst;
	@FXML
	private Label zLblLeveradres;
	@FXML
	private Label zLblStatus;
	@FXML
	private Label zLblTotaleOrderbedrag;
	@FXML
	private Label zLblTransportdienst;
	@FXML
	private Label zLblTrackTraceGegevens;
	
	
	@FXML
	private Label uLblDetailsBestelling;
	@FXML
	private Button btnUitBestelling;
	@FXML
	private Label uLblDozenVoorVerpakking;
	@FXML
	private Label uLblNaamKlant;
	@FXML
	private Label uLblNaamAankoper;
	@FXML
	private Label uLblEmailAankoper;
	@FXML
	private Label uLblOrderId;
	@FXML
	private Label uLblDatumGeplaatst;
	@FXML
	private Label uLblLeveradres;
	@FXML
	private Label uLblStatus;
	@FXML
	private Label uLblTotaleOrderbedrag;
	@FXML
	private Label uLblTransportdienst;
	@FXML
	private Label uLblTrackTraceGegevens;
	
	
	@FXML
	private Label lLblDetailsBestelling;
	@FXML
	private Button btnLeverBestelling;
	@FXML
	private Label lLblDozenVoorVerpakking;
	@FXML
	private Label lLblNaamKlant;
	@FXML
	private Label lLblNaamAankoper;
	@FXML
	private Label lLblEmailAankoper;
	@FXML
	private Label lLblOrderId;
	@FXML
	private Label lLblDatumGeplaatst;
	@FXML
	private Label lLblLeveradres;
	@FXML
	private Label lLblStatus;
	@FXML
	private Label lLblTotaleOrderbedrag;
	@FXML
	private Label lLblTransportdienst;
	@FXML
	private Label lLblTrackTraceGegevens;
	
	
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
	
	@FXML ChoiceBox<String> choiceStatus;
	
	@FXML TabPane bestellingenTabPane;
	
	@FXML 
	private Tab bestellingRaadplegenTab;
	
	@FXML 
	private Tab bestellingVerwerkenTab;
	@FXML 
	private Tab bestellingVerzendenTab;
	
	@FXML 
	private Tab bestellingUitTab;
	@FXML 
	private Tab bestellingLeverenTab;
	

	
	private final String VERWERKT = "VERWERKT";
	private final String GEPLAATST = "GEPLAATST";
	private final String VERZONDEN = "VERZONDEN";
	private final String UIT_VOOR_LEVERING = "UIT_VOOR_LEVERING";
	private final String GELEVERD = "GELEVERD";
	
	private BestellingDTO selectedBestellingDTO;
	
	private Alert melding = new Alert(AlertType.NONE);
	
	private int indexSelectedRow;

	public BestellingenController() {
		

	}
	
	public void loadBestellingen() {

		//assign values to columns
		try {
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
		  
			ObservableList<String> statusList = FXCollections.observableList(Arrays.asList(dc.getGebruikteStatussen()));
			choiceStatus.setItems(statusList);
			//Adding action to the choice box
			choiceStatus.getSelectionModel().selectedItemProperty().addListener(
					(observable, oldValue, newValue) -> {
						filteredList.setPredicate(bestelling -> {
				            // If search field is empty, show all entries
				            if (newValue == null || newValue.isEmpty() || newValue.toUpperCase().equals("ALLES")) {
				                return true;
				            }
				            // Zoektekst naar lowercase
				            String upperCaseFilter = newValue.toUpperCase();
				            // controleer of orderID de zoektekst bevat
				            if (bestelling.getStatus().toUpperCase().equals(upperCaseFilter)) {
				                return true;
				            }
				            return false;
				        });
		      });
			
			
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
		catch(EntityNotFoundException e) {
			toonMelding(AlertType.ERROR, e.getMessage());
		}

	}

	public void setParams(DomeinController dc) {

		this.dc = dc;
	}
	
	public void addListenerRijSelecteren() {
		// Een rij selecteren
		tvBestellingen.getSelectionModel().selectedItemProperty().
			addListener((observableValue, oldBestellingDTO, newBestellingDTO) -> {
				if (newBestellingDTO != null) {
					//***set selected BestellingDTO***
					selectedBestellingDTO = newBestellingDTO;
					
					//***vul het detail raadplegen op***
					/*try {
						maakVisueelDetailBestellingRaadplegen(newBestellingDTO);
					}
					catch(EntityNotFoundException e) {
						toonMelding(AlertType.ERROR, e.getMessage());
					}*/
					
					//***vul het detail verwerken op***
					//***we doen dit alleen maar als de status GEPLAATST is***
					//***als de status VERWERKT is, dan disablen we tab 'verwerken' en tonen we een melding***
					String status = selectedBestellingDTO.getStatus();
					switch (status.toUpperCase()) {
						case GEPLAATST -> {
							bestellingRaadplegenTab.setDisable(false);
							bestellingVerwerkenTab.setDisable(false);
							bestellingVerzendenTab.setDisable(true);
							bestellingUitTab.setDisable(true);
							bestellingLeverenTab.setDisable(true);
							try {
								maakVisueelDetailBestellingRaadplegen(newBestellingDTO);
								maakVisueelDetailBestellingVerwerken(newBestellingDTO);
							}
							catch(EntityNotFoundException e) {
								toonMelding(AlertType.ERROR, e.getMessage());
							}
						}
					
						case VERWERKT -> {
							bestellingRaadplegenTab.setDisable(false);
							bestellingVerwerkenTab.setDisable(true);
							bestellingVerzendenTab.setDisable(false);
							bestellingUitTab.setDisable(true);
							bestellingLeverenTab.setDisable(true);
							try {
								maakVisueelDetailBestellingRaadplegen(newBestellingDTO);
								maakVisueelDetailBestellingVerzenden(newBestellingDTO);
							}
							catch(EntityNotFoundException e) {
								toonMelding(AlertType.ERROR, e.getMessage());
							}
						}
						case VERZONDEN -> {
							bestellingRaadplegenTab.setDisable(true);
							bestellingVerwerkenTab.setDisable(true);
							bestellingVerzendenTab.setDisable(true);
							bestellingUitTab.setDisable(false);
							bestellingLeverenTab.setDisable(true);
							try {
								maakVisueelDetailBestellingUit(newBestellingDTO);
							}
							catch(EntityNotFoundException e) {
								toonMelding(AlertType.ERROR, e.getMessage());
							}
						}
						case UIT_VOOR_LEVERING -> {
							bestellingRaadplegenTab.setDisable(true);
							bestellingVerwerkenTab.setDisable(true);
							bestellingVerzendenTab.setDisable(true);
							bestellingUitTab.setDisable(true);
							bestellingLeverenTab.setDisable(false);
							try {
								maakVisueelDetailBestellingLeveren(newBestellingDTO);
							}
							catch(EntityNotFoundException e) {
								toonMelding(AlertType.ERROR, e.getMessage());
							}
						}
						case GELEVERD -> {
							bestellingRaadplegenTab.setDisable(true);
							bestellingVerwerkenTab.setDisable(true);
							bestellingVerzendenTab.setDisable(true);
							bestellingUitTab.setDisable(true);
							bestellingLeverenTab.setDisable(true);
							try {
								maakVisueelDetailBestellingVerwerken(newBestellingDTO);
							}
							catch(EntityNotFoundException e) {
								toonMelding(AlertType.ERROR, e.getMessage());
							}
						}
					
					}
					
					/*if(status.toUpperCase().equals(VERWERKT)) {
						bestellingVerwerkenTab.setDisable(true);						
					}
					else {
						bestellingVerwerkenTab.setDisable(false);
						try {
							maakVisueelDetailBestellingVerwerken(newBestellingDTO);
						}
						catch(EntityNotFoundException e) {
							toonMelding(AlertType.ERROR, e.getMessage());
						}
					}*/
					
					//***Overzicht bestelde producten van deze bestelling***
					loadBesteldeProducten(newBestellingDTO);
				}
			});
		indexSelectedRow = tvBestellingen.getSelectionModel().getSelectedIndex();
	}
	
	// Event Listener on Button[#btnWijzigBestelling].onAction
	@FXML
	public void wijzigBestelling(ActionEvent event) {
		long geselecteerdeBestellingDTOId = selectedBestellingDTO.getId();
		TransportdienstDTO geselecteerdeTransportdienstDTO = cmbTransportdienst.getValue();
		long geselecteerdeTransportdienstDTOId = geselecteerdeTransportdienstDTO.getId();
		
		try{
			dc.wijzigBestelling(geselecteerdeBestellingDTOId, geselecteerdeTransportdienstDTOId);
		}
		catch(SizeLimitExceededException e) {
			toonMelding(AlertType.ERROR, e.getMessage());
		}
		catch(IllegalArgumentException e) {
			toonMelding(AlertType.ERROR, e.getMessage());
		}
		
		//***toon gewijzigd BestellingDTO op het scherm***
		BestellingDTO gewijzigdeDetailBestellingDTO = dc.getBestelling(geselecteerdeBestellingDTOId);
		try {
			maakVisueelDetailBestellingRaadplegen(gewijzigdeDetailBestellingDTO);
			maakVisueelDetailBestellingVerzenden(gewijzigdeDetailBestellingDTO);
		}
		catch(EntityNotFoundException e) {
			toonMelding(AlertType.ERROR, e.getMessage());
		}
		//***laadt de lijst met bestellingen***
		try {
			loadBestellingen();
		}
		catch(EntityNotFoundException e) {
			toonMelding(AlertType.ERROR, e.getMessage());
		}
		
		tvBestellingen.getSelectionModel().select(indexSelectedRow);
		
		
	}
	
	// Event Listeren on Button[#btnTrackAndTrace].onAction
	@FXML
	public void genereerTrackAndTrace(ActionEvent event) {
		long geselecteerdeBestellingDTOId = selectedBestellingDTO.getId();
		
		try {
			String ttc = dc.wijzigTrackAndTraceCode(geselecteerdeBestellingDTOId);
			lblTrackTraceGegevens.setText(ttc);
		}
		catch(SizeLimitExceededException e) {
			toonMelding(AlertType.ERROR, e.getMessage());
		}
		catch(IllegalArgumentException e) {
			toonMelding(AlertType.ERROR, e.getMessage());
		}
		
		//***toon gewijzigd BestellingDTO op het scherm***
		/*BestellingDTO gewijzigdeDetailBestellingDTO = dc.getBestelling(geselecteerdeBestellingDTOId);
		try {
			maakVisueelDetailBestellingRaadplegen(gewijzigdeDetailBestellingDTO);
		}
		catch(EntityNotFoundException e) {
			toonMelding(AlertType.ERROR, e.getMessage());
		}*/
				
	}
	
	// Event Listener on Button[#btnVerwerkBestelling].onAction
	@FXML
	public void verwerkBestelling(ActionEvent event) {
		long geselecteerdeBestellingDTOId = selectedBestellingDTO.getId();
		System.out.println(geselecteerdeBestellingDTOId);
		TransportdienstDTO geselecteerdeTransportdienstDTO = vCmbTransportdienst.getValue();
		long geselecteerdeTransportdienstDTOId = geselecteerdeTransportdienstDTO.getId();
		
		try{
			dc.verwerkBestelling(geselecteerdeBestellingDTOId, geselecteerdeTransportdienstDTOId);
		}
		catch(SizeLimitExceededException e) {
			toonMelding(AlertType.ERROR, e.getMessage());
		}
		catch(IllegalArgumentException e) {
			toonMelding(AlertType.ERROR, e.getMessage());
		}
		
		//***toon gewijzigd BestellingDTO op het scherm***
		BestellingDTO gewijzigdeDetailBestellingDTO = dc.getBestelling(geselecteerdeBestellingDTOId);
		try {
			maakVisueelDetailBestellingRaadplegen(gewijzigdeDetailBestellingDTO);
			maakVisueelDetailBestellingVerzenden(gewijzigdeDetailBestellingDTO);
		}
		catch(EntityNotFoundException e) {
			toonMelding(AlertType.ERROR, e.getMessage());
		}
		//***spring naar de tab 'raadplegen'***
		bestellingenTabPane.getSelectionModel().select(bestellingRaadplegenTab);
		//***disable de tab 'verwerken'
		bestellingVerwerkenTab.setDisable(true);
		//***enable de tab 'verzenden'
		bestellingVerzendenTab.setDisable(false);
		
		//***laadt de lijst met bestellingen***
		try {
			loadBestellingen();
		}
		catch(EntityNotFoundException e) {
			toonMelding(AlertType.ERROR, e.getMessage());
		}
		
		tvBestellingen.getSelectionModel().select(indexSelectedRow);
	}
	
	// Event Listener on Button[#btnVerzendBestelling].onAction
		@FXML
		public void verzendBestelling(ActionEvent event) {
			long geselecteerdeBestellingDTOId = selectedBestellingDTO.getId();
			
			try{
				dc.verzendBestelling(geselecteerdeBestellingDTOId);
			}
			catch(IllegalArgumentException e) {
				toonMelding(AlertType.ERROR, e.getMessage());
			}
			
			//***toon gewijzigd BestellingDTO op het scherm***
			BestellingDTO gewijzigdeDetailBestellingDTO = dc.getBestelling(geselecteerdeBestellingDTOId);
			try {
				maakVisueelDetailBestellingUit(gewijzigdeDetailBestellingDTO);
			}
			catch(EntityNotFoundException e) {
				toonMelding(AlertType.ERROR, e.getMessage());
			}
			//***spring naar de tab 'uit'***
			bestellingUitTab.setDisable(false);
			bestellingenTabPane.getSelectionModel().select(bestellingUitTab);
			//***disable de tab 'raadplegen' en 'verzenden'
			bestellingRaadplegenTab.setDisable(true);
			bestellingVerzendenTab.setDisable(true);
			
			//***laadt de lijst met bestellingen***
			try {
				loadBestellingen();
			}
			catch(EntityNotFoundException e) {
				toonMelding(AlertType.ERROR, e.getMessage());
			}
			
			tvBestellingen.getSelectionModel().select(indexSelectedRow);
		}
	
		// Event Listener on Button[#btnUitBestelling].onAction
		@FXML
		public void uitBestelling(ActionEvent event) {
			long geselecteerdeBestellingDTOId = selectedBestellingDTO.getId();
			
			try{
				dc.uitBestelling(geselecteerdeBestellingDTOId);
			}
			catch(IllegalArgumentException e) {
				toonMelding(AlertType.ERROR, e.getMessage());
			}
			
			//***toon gewijzigd BestellingDTO op het scherm***
			BestellingDTO gewijzigdeDetailBestellingDTO = dc.getBestelling(geselecteerdeBestellingDTOId);
			try {
				maakVisueelDetailBestellingLeveren(gewijzigdeDetailBestellingDTO);
			}
			catch(EntityNotFoundException e) {
				toonMelding(AlertType.ERROR, e.getMessage());
			}
			//***spring naar de tab 'leveren'***
			bestellingLeverenTab.setDisable(false);
			bestellingenTabPane.getSelectionModel().select(bestellingLeverenTab);
			//***disable de tab 'uit'
			bestellingUitTab.setDisable(true);
			
			
			//***laadt de lijst met bestellingen***
			try {
				loadBestellingen();
			}
			catch(EntityNotFoundException e) {
				toonMelding(AlertType.ERROR, e.getMessage());
			}
			
			tvBestellingen.getSelectionModel().select(indexSelectedRow);
		}
			
		// Event Listener on Button[#btnLeverBestelling].onAction
		@FXML
		public void leverBestelling(ActionEvent event) {
			long geselecteerdeBestellingDTOId = selectedBestellingDTO.getId();
			
			try{
				dc.leverBestelling(geselecteerdeBestellingDTOId);
			}
			catch(IllegalArgumentException e) {
				toonMelding(AlertType.ERROR, e.getMessage());
			}
			
			//***toon gewijzigd BestellingDTO op het scherm***
			BestellingDTO gewijzigdeDetailBestellingDTO = dc.getBestelling(geselecteerdeBestellingDTOId);
			try {
				maakVisueelDetailBestellingLeveren(gewijzigdeDetailBestellingDTO);
			}
			catch(EntityNotFoundException e) {
				toonMelding(AlertType.ERROR, e.getMessage());
			}
			//***disable knop leveren
			btnLeverBestelling.setDisable(true);
			
			//***laadt de lijst met bestellingen***
			try {
				loadBestellingen();
			}
			catch(EntityNotFoundException e) {
				toonMelding(AlertType.ERROR, e.getMessage());
			}
			
			tvBestellingen.getSelectionModel().select(indexSelectedRow);
		}
			
		public void disableDetail() {
		cmbTransportdienst.setDisable(true);
		cmbTransportdienst.setStyle("-fx-opacity: 1.0;-fx-text-fill: #000000;");
		btnWijzigBestelling.setDisable(true);
		btnWijzigBestelling.setStyle("-fx-opacity: 1.0;");
		btnTrackAndTrace.setDisable(true);
		btnTrackAndTrace.setStyle("-fx-opacity: 1.0;");
		vCmbTransportdienst.setDisable(true);
		vCmbTransportdienst.setStyle("-fx-opacity: 1.0;-fx-text-fill: #000000;");
		btnVerwerkBestelling.setDisable(true);
		btnVerwerkBestelling.setStyle("-fx-opacity: 1.0;");
	}
	
	private void maakVisueelDetailBestellingRaadplegen(BestellingDTO bestellingDTO) {
		lblDetailsBestelling.setText(String.format("Detail bestelling: %s", bestellingDTO.getOrderID()));
		lblDozenVoorVerpakking.setText(maakVisueelDoosType(bestellingDTO));
		lblNaamKlant.setText(bestellingDTO.getKlantNaam());
		lblNaamAankoper.setText(maakVisueelNaamAankoper(bestellingDTO));
		lblEmailAankoper.setText(maakVisueelEmailAankoper(bestellingDTO));
		lblOrderId.setText(bestellingDTO.getOrderID());
		lblDatumGeplaatst.setText(DateFormat.getDateInstance().format(bestellingDTO.getDatumGeplaatst()));
		lblLeveradres.setText(maakVisueelLeveradres(bestellingDTO));
		lblStatus.setText(bestellingDTO.getStatus());
		lblTotaleOrderbedrag.setText(String.format("%.2f€", bestellingDTO.getTotaalbedrag()));
		// transportdiensten van bedrijf van actueel ingelogde gebruiker
		ObservableList<TransportdienstDTO> transportdienstDTOList = FXCollections.observableList(dc.getTransportdienstenDTO());
		Long transportdienstId = bestellingDTO.getTransportdienstID();
		Long geselecteerdeTransportdienstDTOId = (transportdienstId != null) ? transportdienstId.longValue() : null;
		cmbTransportdienst.setItems(transportdienstDTOList);
		if (geselecteerdeTransportdienstDTOId != null) {
		    cmbTransportdienst.setValue(filterTransportdienst(transportdienstDTOList, geselecteerdeTransportdienstDTOId));
		} else {
			cmbTransportdienst.setValue(null);
		}
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
		lblTrackTraceGegevens.setText(bestellingDTO.getTrackAndTraceCode());
		
		// pas de status van de knoppen aan
		if(bestellingDTO.getStatus().toUpperCase().equals(VERWERKT)) {
			cmbTransportdienst.setDisable(false);
			btnWijzigBestelling.setDisable(false);
			btnTrackAndTrace.setDisable(false);
		}
		else {
			cmbTransportdienst.setDisable(true);
			cmbTransportdienst.setStyle("-fx-opacity: 1.0;-fx-text-fill: #000000;");
			btnWijzigBestelling.setDisable(true);
			btnWijzigBestelling.setStyle("-fx-opacity: 1.0;");
			btnTrackAndTrace.setDisable(true);
			btnTrackAndTrace.setStyle("-fx-opacity: 1.0;");
		}
		
	}
	
	private void maakVisueelDetailBestellingVerwerken(BestellingDTO bestellingDTO) {
		vLblDetailsBestelling.setText(String.format("Detail bestelling: %s", bestellingDTO.getOrderID()));
		vLblDozenVoorVerpakking.setText(maakVisueelDoosType(bestellingDTO));
		vLblNaamKlant.setText(bestellingDTO.getKlantNaam());
		vLblNaamAankoper.setText(maakVisueelNaamAankoper(bestellingDTO));
		vLblEmailAankoper.setText(maakVisueelEmailAankoper(bestellingDTO));
		vLblOrderId.setText(bestellingDTO.getOrderID());
		vLblDatumGeplaatst.setText(DateFormat.getDateInstance().format(bestellingDTO.getDatumGeplaatst()));
		vLblLeveradres.setText(maakVisueelLeveradres(bestellingDTO));
		vLblStatus.setText(bestellingDTO.getStatus());
		vLblTotaleOrderbedrag.setText(String.format("%.2f€", bestellingDTO.getTotaalbedrag()));
		// transportdiensten van bedrijf van actueel ingelogde gebruiker
		ObservableList<TransportdienstDTO> transportdienstDTOList = FXCollections.observableList(dc.getTransportdienstenDTO());
		Long transportdienstId = bestellingDTO.getTransportdienstID();
		Long geselecteerdeTransportdienstDTOId = (transportdienstId != null) ? transportdienstId.longValue() : null;
		vCmbTransportdienst.setItems(transportdienstDTOList);
		if (geselecteerdeTransportdienstDTOId != null) {
		    cmbTransportdienst.setValue(filterTransportdienst(transportdienstDTOList, geselecteerdeTransportdienstDTOId));
		} else {
			cmbTransportdienst.setValue(null);
		}
		vCmbTransportdienst.setCellFactory(new Callback<ListView<TransportdienstDTO>, ListCell<TransportdienstDTO>>() {
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
		vCmbTransportdienst.setButtonCell(new ListCell<TransportdienstDTO>() {
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
		vLblTrackTraceGegevens.setText("Nog niet beschikbaar");
		// pas de status van de knoppen aan
		vCmbTransportdienst.setDisable(false);
		btnVerwerkBestelling.setDisable(false);
		
	}
	
	private void maakVisueelDetailBestellingVerzenden(BestellingDTO bestellingDTO) {
		zLblDetailsBestelling.setText(String.format("Detail bestelling: %s", bestellingDTO.getOrderID()));
		zLblDozenVoorVerpakking.setText(maakVisueelDoosType(bestellingDTO));
		zLblNaamKlant.setText(bestellingDTO.getKlantNaam());
		zLblNaamAankoper.setText(maakVisueelNaamAankoper(bestellingDTO));
		zLblEmailAankoper.setText(maakVisueelEmailAankoper(bestellingDTO));
		zLblOrderId.setText(bestellingDTO.getOrderID());
		zLblDatumGeplaatst.setText(DateFormat.getDateInstance().format(bestellingDTO.getDatumGeplaatst()));
		zLblLeveradres.setText(maakVisueelLeveradres(bestellingDTO));
		zLblStatus.setText(bestellingDTO.getStatus());
		zLblTotaleOrderbedrag.setText(String.format("%.2f€", bestellingDTO.getTotaalbedrag()));
		zLblTransportdienst.setText(dc.getTransportdienst(bestellingDTO.getTransportdienstID()).getNaam());
		zLblTrackTraceGegevens.setText(bestellingDTO.getTrackAndTraceCode());
		/*btnVerwerkBestelling.setDisable(false);*/
		
	}
	
	private void maakVisueelDetailBestellingUit(BestellingDTO bestellingDTO) {
		uLblDetailsBestelling.setText(String.format("Detail bestelling: %s", bestellingDTO.getOrderID()));
		uLblDozenVoorVerpakking.setText(maakVisueelDoosType(bestellingDTO));
		uLblNaamKlant.setText(bestellingDTO.getKlantNaam());
		uLblNaamAankoper.setText(maakVisueelNaamAankoper(bestellingDTO));
		uLblEmailAankoper.setText(maakVisueelEmailAankoper(bestellingDTO));
		uLblOrderId.setText(bestellingDTO.getOrderID());
		uLblDatumGeplaatst.setText(DateFormat.getDateInstance().format(bestellingDTO.getDatumGeplaatst()));
		uLblLeveradres.setText(maakVisueelLeveradres(bestellingDTO));
		uLblStatus.setText(bestellingDTO.getStatus());
		uLblTotaleOrderbedrag.setText(String.format("%.2f€", bestellingDTO.getTotaalbedrag()));
		uLblTransportdienst.setText(dc.getTransportdienst(bestellingDTO.getTransportdienstID()).getNaam());
		uLblTrackTraceGegevens.setText(bestellingDTO.getTrackAndTraceCode());
		/*btnVerwerkBestelling.setDisable(false);*/
		
	}
	
	private void maakVisueelDetailBestellingLeveren(BestellingDTO bestellingDTO) {
		lLblDetailsBestelling.setText(String.format("Detail bestelling: %s", bestellingDTO.getOrderID()));
		lLblDozenVoorVerpakking.setText(maakVisueelDoosType(bestellingDTO));
		lLblNaamKlant.setText(bestellingDTO.getKlantNaam());
		lLblNaamAankoper.setText(maakVisueelNaamAankoper(bestellingDTO));
		lLblEmailAankoper.setText(maakVisueelEmailAankoper(bestellingDTO));
		lLblOrderId.setText(bestellingDTO.getOrderID());
		lLblDatumGeplaatst.setText(DateFormat.getDateInstance().format(bestellingDTO.getDatumGeplaatst()));
		lLblLeveradres.setText(maakVisueelLeveradres(bestellingDTO));
		lLblStatus.setText(bestellingDTO.getStatus());
		lLblTotaleOrderbedrag.setText(String.format("%.2f€", bestellingDTO.getTotaalbedrag()));
		lLblTransportdienst.setText(dc.getTransportdienst(bestellingDTO.getTransportdienstID()).getNaam());
		lLblTrackTraceGegevens.setText(bestellingDTO.getTrackAndTraceCode());
		/*btnVerwerkBestelling.setDisable(false);*/
		
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
		return (doosDTO.getDoosType() + " (Naam: " + doosDTO.getNaam() + ", prijs: " + String.format("%.2f€", doosDTO.getPrijs()) + ")");
	}
	
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
	
	private void toonMelding(AlertType type, String boodschap) {
		melding.setAlertType(type);
		melding.setContentText(boodschap);
		melding.show();
	}

}
