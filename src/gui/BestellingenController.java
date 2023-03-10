package gui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import domein.DomeinController;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
	

	
	
	public BestellingenController() {


	}	
	
	public void loadBestellingen() {
		System.out.println("Bestellingen");
		ObservableList<BestellingDTO> bestellingList = FXCollections.observableList(dc.getBestellingen());
		orderColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOrderID()));
		statusColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus()));
		klantColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getKlantNaam()));
		transportdienstColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTransportdienstNaam()));
		datumColumn.setCellValueFactory(cellData -> new SimpleStringProperty(DateFormat.getDateInstance().format(cellData.getValue().getDatumGeplaatst())));
		tvBestellingen.setItems(bestellingList);

		
	}
	
	public void setParams(DomeinController dc) {
		
		this.dc = dc;
	}

}
