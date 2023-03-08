package gui;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import repository.TransportdienstDTO;

public class BeheerTransportdienstTableView extends TableView<TransportdienstDTO> {

	private TableColumn<TransportdienstDTO, String> naamKolom;
	private TableColumn<TransportdienstDTO, Boolean> statusKolom;
	private ObservableList<TransportdienstDTO> list;

	public BeheerTransportdienstTableView(List<TransportdienstDTO> transportdienstDTOLijst) {
		naamKolom = new TableColumn<TransportdienstDTO, String>("Naam");
		statusKolom = new TableColumn<TransportdienstDTO, Boolean>("Status");
		naamKolom.setCellValueFactory(new PropertyValueFactory<TransportdienstDTO, String>("naam"));
		statusKolom.setCellValueFactory(new PropertyValueFactory<TransportdienstDTO, Boolean>("isActief"));
		super.setItems(FXCollections.observableArrayList(transportdienstDTOLijst));
		super.getColumns().add(naamKolom);
		super.getColumns().add(statusKolom);
		super.setLayoutX(85);
		super.setLayoutY(170);
		super.prefHeightProperty().set(900);
		super.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
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

	public ObservableList<TransportdienstDTO> getList() {
		return list;
	}

	public void setList(ObservableList<TransportdienstDTO> list) {
		super.setItems(list);
	}

}
