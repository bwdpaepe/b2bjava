package gui;

import domein.DomeinController;
import javafx.animation.FadeTransition;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import repository.KlantLijstEntryDTO;
import repository.MedewerkerDTO;
import repository.MedewerkerListEntryDTO;
import service.ValidationService;

public class MedewerkerController {
	private DomeinController dc;
	private Boolean voegtoeError = false;
	private Boolean wijzigError = false;

	// Tableview ------------------------------------------
	@FXML
	private TableView<MedewerkerListEntryDTO> tvMedewerkers;
	@FXML
	private TableColumn<MedewerkerListEntryDTO, String> tvVoornaam;
	@FXML
	private TableColumn<MedewerkerListEntryDTO, String> tvFamilienaam;
	@FXML
	private TableColumn<MedewerkerListEntryDTO, String> tvFunctie;
	@FXML
	private TableColumn<MedewerkerListEntryDTO, String> tvIsActief;
	@FXML
	private TextField tvZoekMedewerker;
	// Notification Pane---------------------------------------
	@FXML
	private Pane paneNotification;
	@FXML
	private Text textNotification;
	// DETAILS-----------------------------------------------
	@FXML
	private Text textMedewerker;
	@FXML
	private TextField tfDetailsVoornaam;
	@FXML
	private TextField tfDetailsFamilienaam;
	@FXML
	private TextField tfDetailsEmail;
	@FXML
	private TextField tfDetailsTelefoonnummer;
	@FXML
	private TextField tfDetailsPersoneelnummer;
	@FXML
	private ComboBox<String> cbDetailsFunctie;
	@FXML
	private ComboBox<String> cbDetailsIsActief;
	@FXML
	private TextArea taDetailsAdres;
	@FXML
	private Button btnWijzigMedewerker;
	@FXML
	private Button btnNieuweMedewerker;
	// TOEVOEGEN------------------------------------------------
	@FXML
	private Pane voegToePane;
	@FXML
	private TextField tfVoegtoeVoornaam;
	@FXML
	private TextField tfVoegtoeFamilienaam;
	@FXML
	private TextField tfVoegtoeEmail;
	@FXML
	private TextField tfVoegtoeTelefoonnummer;
	@FXML
	private TextField tfVoegtoePaswoord;
	@FXML
	private TextArea taVoegtoeAdres;
	@FXML 
	private ComboBox<String> cbVoegtoeFunctie;
	@FXML
	private Button btnVoegtoe;
	@FXML
	private Button btnTerug;
	

	public MedewerkerController() {

	}

	public void setParams(DomeinController dc) {
		this.dc = dc;
	}

	public void loadMedewerkers() {
		ObservableList<MedewerkerListEntryDTO> medewerkerList = FXCollections
				.observableList(dc.findAllMedewerkersByBedrijfId());

		tvVoornaam.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().getVoornaam()));
		tvFamilienaam.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().getFamilienaam()));
		tvFunctie.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().getFunctie()));
		tvIsActief.setCellValueFactory(
				celldata -> new SimpleStringProperty(celldata.getValue().isActief() ? "Ja" : "Nee"));

		FilteredList<MedewerkerListEntryDTO> filteredMedewerkerList = new FilteredList<>(medewerkerList, p -> true);

		tvZoekMedewerker.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredMedewerkerList.setPredicate(medewerker -> {
				// If search field is empty, show all entries
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				// Zoektekst naar lowercase
				String lowerCaseFilter = newValue.toLowerCase();
				// controleer of klantnaam de zoektekst bevat
				if (medewerker.getFamilienaam().toLowerCase().contains(lowerCaseFilter)) {
					return true;
				}
				return false;
			});
		});

		SortedList<MedewerkerListEntryDTO> sortedMedewerkerList = new SortedList<>(filteredMedewerkerList);
		sortedMedewerkerList.comparatorProperty().bind(tvMedewerkers.comparatorProperty());
		tvMedewerkers.setItems(sortedMedewerkerList);
		tvMedewerkers.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue == null && oldValue != null) {
				loadSelectedMedewerkerDetails(oldValue);
			}
			if(newValue != null) {
				loadSelectedMedewerkerDetails(newValue);
			}
			

		});
		btnNieuweMedewerker.setOnAction(event -> openVoegtoeScherm());

	}

	private void loadSelectedMedewerkerDetails(MedewerkerListEntryDTO mwelDTO) {
		MedewerkerDTO mwDTO = dc.findMedewerkerById(mwelDTO.getId());

		textMedewerker.setText(mwDTO.getFamilienaam() + " " + mwDTO.getVoornaam());
		tfDetailsVoornaam.setText(mwDTO.getVoornaam());
		tfDetailsFamilienaam.setText(mwDTO.getFamilienaam());
		tfDetailsEmail.setText(mwDTO.getEmail());
		cbDetailsFunctie.setValue(mwDTO.getFunctie());
		taDetailsAdres.setText(mwDTO.getAdres());
		tfDetailsTelefoonnummer.setText(mwDTO.getTelefoonnummer());
		tfDetailsPersoneelnummer.setText(Integer.toString(mwDTO.getPersoneelsNr()));
		cbDetailsIsActief.setValue(mwDTO.getIsActief() ? "Ja" : "Nee");

		ObservableList<String> functieOptions = FXCollections.observableArrayList("Admin", "Magazijnier", "Aankoper");
		cbDetailsFunctie.setItems(functieOptions);

		ObservableList<String> actiefOptions = FXCollections.observableArrayList("Ja", "Nee");
		cbDetailsIsActief.setItems(actiefOptions);

		btnWijzigMedewerker.setOnAction(event -> medewerkerWijzigen(mwDTO.getID()));

	}

	private void medewerkerWijzigen(long id) {
		Boolean isActief = false;

		try {
			ValidationService.controleerNietBlanco(tfDetailsVoornaam.getText());
			ValidationService.controleerNietBlanco(tfDetailsFamilienaam.getText());
			ValidationService.controleerNietBlanco(tfDetailsEmail.getText());
			ValidationService.controleerNietBlanco(taDetailsAdres.getText());
			ValidationService.controleerNietBlanco(tfDetailsTelefoonnummer.getText());
		} catch (Exception e) {
			wijzigError = true;
			notifyUser("Vul alle velden in AUB", "error");
		}

		if (!wijzigError) {
			try {
				ValidationService.controleerTelefoonnummer(tfDetailsTelefoonnummer.getText());
			} catch (Exception e) {
				notifyUser("Gelieve een geldig telefoonnummer in te vullen", "error");
			}

		}
		
		if (!wijzigError) {
			try {
				ValidationService.controleerEmail(tfDetailsEmail.getText());
			} catch (Exception e) {
				notifyUser("Gelieve een geldig emailadres in te vullen", "error");
			}
		}

		if (!wijzigError) {
			try {
				switch (cbDetailsIsActief.getValue()) {
				case "Ja" -> isActief = true;
				case "Nee" -> isActief = false;
				}

				dc.updateMedewerker(id, tfDetailsVoornaam.getText(), tfDetailsFamilienaam.getText(),
						tfDetailsEmail.getText(), taDetailsAdres.getText(), tfDetailsTelefoonnummer.getText(),
						cbDetailsFunctie.getValue(), isActief);
				notifyUser("Medewerker succesvol gewijzigd", "success");
				loadMedewerkers();
			} catch (Exception e) {
				notifyUser("Er ging iets mis met het wijzigen van de werknemer, gelieve de software admin te contacteren", "error");
			}
		}
		wijzigError = false;
	}
	
	
	
	
	
	
	private void openVoegtoeScherm() {
		btnTerug.setOnAction(event -> sluitVoegtoeScherm());
		btnVoegtoe.setOnAction(event -> voegMedewerkerToe());
		ObservableList<String> functieOptions = FXCollections.observableArrayList("Admin", "Magazijnier", "Aankoper");
		cbVoegtoeFunctie.setItems(functieOptions);
		voegToePane.setVisible(true);
	}
	
	private void sluitVoegtoeScherm() {
		voegToePane.setVisible(false);
	}
	
	private void voegMedewerkerToe() {
		try {
			ValidationService.controleerNietBlanco(tfVoegtoeVoornaam.getText());
			ValidationService.controleerNietBlanco(tfVoegtoeFamilienaam.getText());
			ValidationService.controleerNietBlanco(tfVoegtoeEmail.getText());
			ValidationService.controleerNietBlanco(tfVoegtoePaswoord.getText());
			ValidationService.controleerNietBlanco(taVoegtoeAdres.getText());
			ValidationService.controleerNietBlanco(tfVoegtoeTelefoonnummer.getText());
			ValidationService.controleerNietBlanco(cbVoegtoeFunctie.getValue());
		} catch (Exception e) {
			voegtoeError = true;
			notifyUser("Alle velden dienen ingevuld te worden", "error");
		}
		
		if (!voegtoeError) {
			try {
				ValidationService.controleerTelefoonnummer(tfVoegtoeTelefoonnummer.getText());
			} catch (Exception e) {
				voegtoeError = true;
				notifyUser("Gelieve een geldig telefoonnummer in te vullen", "error");
			}
		}
		
		if (!voegtoeError) {
			try {
				ValidationService.controleerEmail(tfVoegtoeEmail.getText());
			} catch (Exception e) {
				voegtoeError = true;

				notifyUser("Gelieve een geldig Emailadres te kiezen", "error");
			}
			
		}
		
		if (!voegtoeError) {
			try {
				ValidationService.controleerWachtwoord(tfVoegtoePaswoord.getText());
			} catch (Exception e) {
				voegtoeError = true;

				notifyUser("Paswoord moet langer zijn dan 8 karakters", "error");
			}
		}
		
		if (!voegtoeError) {
			try {
				dc.maakMedewerker(tfVoegtoeVoornaam.getText(), tfVoegtoeFamilienaam.getText(), tfVoegtoeEmail.getText(), tfVoegtoePaswoord.getText(), taVoegtoeAdres.getText(), tfVoegtoeTelefoonnummer.getText(), cbVoegtoeFunctie.getValue());
				notifyUser("Medewerker werd succesvol toegevoegd", "success");
				loadMedewerkers();
				sluitVoegtoeScherm();
			} catch (Exception e) {
				voegtoeError = true;

				notifyUser("Er ging iets mis bij het toevoegen, neem contact op met je sofwate administrator", "error");
			}
			
		}
		voegtoeError = false;

	}
	

	private void notifyUser(String notification, String type) {

		textNotification.setText(notification);
		switch (type.toLowerCase()) {
		case "error" -> paneNotification.setStyle("-fx-background-color: #EEB2B2");
		case "success" -> paneNotification.setStyle("-fx-background-color: #99EDC3");

		}
		paneNotification.setVisible(true);
		FadeTransition ft = new FadeTransition(Duration.millis(5000), paneNotification);
		ft.setFromValue(1);
		ft.setToValue(0);
		ft.play();
		ft.setOnFinished(event -> {
			paneNotification.setVisible(false);
			paneNotification.setStyle(null);
			textNotification.setText(null);
		});
	}

}
