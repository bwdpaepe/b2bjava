package gui;

import java.util.List;

import domein.DomeinController;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import repository.TransportdienstDTO;

public class BeheerTransportdienstTableView extends TableView<TransportdienstDTO> {

	private TableColumn<TransportdienstDTO, String> naamKolom;
	private TableColumn<TransportdienstDTO, Boolean> statusKolom;
	private TransportdienstDTO selectedTransportdienstDTO;
	private DomeinController dc;

	public BeheerTransportdienstTableView(DomeinController dc, List<TransportdienstDTO> transportdienstDTOLijst) {
		this.dc = dc;
		naamKolom = new TableColumn<TransportdienstDTO, String>("Naam");
		statusKolom = new TableColumn<TransportdienstDTO, Boolean>("Status");
		naamKolom.setCellValueFactory(new PropertyValueFactory<TransportdienstDTO, String>("naam"));
		statusKolom.setCellValueFactory(new PropertyValueFactory<TransportdienstDTO, Boolean>("isActief"));
		this.setItems(FXCollections.observableArrayList(transportdienstDTOLijst));
		this.getColumns().add(naamKolom);
		this.getColumns().add(statusKolom);
		this.setLayoutX(85);
		this.setLayoutY(170);
		this.prefHeightProperty().set(900);
		this.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

	}

	public TableColumn<TransportdienstDTO, String> getNaamKolom() {
		return naamKolom;
	}

	public void setNaamKolom(TableColumn<TransportdienstDTO, String> naamKolom) {
		this.naamKolom = naamKolom;
	}

	public TableColumn<TransportdienstDTO, Boolean> getStatusKolom() {
		return statusKolom;
	}

	public void setStatusKolom(TableColumn<TransportdienstDTO, Boolean> statusKolom) {
		this.statusKolom = statusKolom;
	}

}
