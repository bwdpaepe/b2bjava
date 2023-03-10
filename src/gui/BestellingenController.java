package gui;

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
	TableColumn<BestellingDTO, Number> klantColumn;
	@FXML 
	TableColumn<BestellingDTO, Number> transportdienstColumn;
	@FXML 
	TableColumn<BestellingDTO, Date> datumColumn;
	

	
	
	public BestellingenController() {


	}	
	
	public void loadBestellingen() {
		System.out.println("Bestellingen");
		ObservableList<BestellingDTO> bestellingList = FXCollections.observableList(dc.getBestellingen());
		System.out.print("lijst" + bestellingList);
		orderColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOrderID()));
		statusColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus()));
		klantColumn.setCellValueFactory(cellData -> new SimpleLongProperty(cellData.getValue().getKlant()));
		transportdienstColumn.setCellValueFactory(cellData -> new SimpleLongProperty(cellData.getValue().getTransportdienst()));
		datumColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<Date>(cellData.getValue().getDatumGeplaatst()));
		tvBestellingen.setItems(bestellingList);

		
	}
	
	public void setParams(DomeinController dc) {
		
		this.dc = dc;
	}

}
