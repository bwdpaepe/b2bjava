package gui;

import java.io.IOException;
import java.util.List;

import domein.DomeinController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableRow;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import repository.TransportdienstDTO;
import repository.UserDTO;

public class BeheerTransportdienstSchermController extends Pane {

	@FXML
	private Label welkomNaam;

<<<<<<< HEAD
	private BeheerTransportdienstTableView tableViewTransportdienst;
=======
	private final BeheerTransportdienstTableView tableViewTransportdienst;
	private TransportdienstDTO selectedTransportdienstDTO;
>>>>>>> refs/heads/beheren_transportdienst_v2
	private BeheerTransportdienstTabPane tabPane;
	private DomeinController dc;


	public BeheerTransportdienstSchermController(DomeinController dc, List<TransportdienstDTO> transportdienstDTOLijst,
			UserDTO user) {



		this.getChildren().add(tableViewTransportdienst);
		this.getChildren().add(tabPane);
		
		tableViewTransportdienst.setRowFactory(tv -> {
			TableRow<TransportdienstDTO> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {

					selectedTransportdienstDTO = row.getItem();
					//TODO methode toevoegen om deze in de raadpleeg of aanpas tab te zetten
					tabPane.raadpleegTabGui(selectedTransportdienstDTO);
					

				}
			});
			return row;
		});
		


	}
}
