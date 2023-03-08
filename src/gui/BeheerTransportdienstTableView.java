package gui;

import java.util.List;

import domein.DomeinController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import repository.TransportdienstDTO;

public class BeheerTransportdienstTableView extends TableView<TransportdienstDTO> {

	private TableColumn<TransportdienstDTO, String> naamKolom;
	private TableColumn<TransportdienstDTO, Boolean> statusKolom;
	private ObservableList<TransportdienstDTO> list;
	private TransportdienstDTO selectedTransportdienstDTO;
	private DomeinController dc;

	public BeheerTransportdienstTableView(DomeinController dc, List<TransportdienstDTO> transportdienstDTOLijst) {
		this.dc = dc;
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
		
		this.setRowFactory(tv -> {
			TableRow<TransportdienstDTO> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {

					selectedTransportdienstDTO = row.getItem();
					//TODO methode toevoegen om deze in de raadpleeg of aanpas tab te zetten

				}
			});
			return row;
		});
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
