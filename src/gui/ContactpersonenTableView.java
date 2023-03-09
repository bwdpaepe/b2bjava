package gui;

import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import repository.ContactpersoonDTO;
import repository.TransportdienstDTO;

public class ContactpersonenTableView extends TableView<ContactpersoonDTO> {

	private Set<ContactpersoonDTO> contactpersonen;
	private TableColumn<ContactpersoonDTO, String> voornaamKolom;
	private TableColumn<ContactpersoonDTO, String> familienaamKolom;
	private TableColumn<ContactpersoonDTO, String> telefoonnummerKolom;
	private TableColumn<ContactpersoonDTO, String> emailadresKolom;
	private ObservableList<ContactpersoonDTO> list;

	public ContactpersonenTableView(Set<ContactpersoonDTO> contactpersonen) {
		super();
		this.contactpersonen = contactpersonen;
		voornaamKolom = new TableColumn<ContactpersoonDTO, String>("Voornaam");
		familienaamKolom = new TableColumn<ContactpersoonDTO, String>("Familienaam");
		telefoonnummerKolom = new TableColumn<ContactpersoonDTO, String>("Telefoonnummer");
		emailadresKolom = new TableColumn<ContactpersoonDTO, String>("Emailadres");
		voornaamKolom.setCellValueFactory(new PropertyValueFactory<ContactpersoonDTO, String>("voornaam"));
		familienaamKolom.setCellValueFactory(new PropertyValueFactory<ContactpersoonDTO, String>("familienaam"));
		telefoonnummerKolom.setCellValueFactory(new PropertyValueFactory<ContactpersoonDTO, String>("telefoonnummer"));
		emailadresKolom.setCellValueFactory(new PropertyValueFactory<ContactpersoonDTO, String>("emailAdres"));
		this.setItems(FXCollections.observableArrayList(contactpersonen));
		this.getColumns().add(voornaamKolom);
		this.getColumns().add(familienaamKolom);
		this.getColumns().add(telefoonnummerKolom);
		this.getColumns().add(emailadresKolom);
		buidlGui();
	}

	private void buidlGui() {
		// this.prefHeightProperty().set(900);
		this.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}

}
