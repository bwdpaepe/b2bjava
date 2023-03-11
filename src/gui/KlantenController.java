package gui;



import domein.DomeinController;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
	TableColumn<KlantLijstEntryDTO, String> aantalBestellingenColumn;
	
	public KlantenController() {

	}	
	

	public void setParams(DomeinController dc) {
		this.dc = dc;
	}
	
	public void loadKlanten() {
		System.out.println("Klanten lijst loading");
		ObservableList<KlantLijstEntryDTO> klantenList = FXCollections.observableList(dc.geefLijstVanKlantenMetAantalOpenstaandeBestellingen());
		
		naamColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getKlantNaam()));
		aantalBestellingenColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAantalOpenBestellingen().toString()));
		
		tvKlantenLijst.setItems(klantenList);
	}
}
