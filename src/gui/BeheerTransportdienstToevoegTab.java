package gui;

import domein.DomeinController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import service.ValidationService;

public class BeheerTransportdienstToevoegTab extends Tab {
	private GridPane gridPaneToevoegen;
	private DomeinController dc;
	
	public BeheerTransportdienstToevoegTab(DomeinController dc) {
		super("Toevoegen");
		this.dc = dc;
		buildGui();
	}

	private void buildGui() {
		gridPaneToevoegen = new GridPane();
		Label lblTitel = new Label("TOEVOEGEN TRANSPORTDIENST");
		Label lblNaamTransportdienst = new Label("Naam transportdienst:");
		Label lblContactpersoonVoornaam = new Label("Contactpersoon voornaam:");
		Label lblContactpersoonFamilienaam = new Label("Contactpersoon familienaam:");
		Label lblTelefoonnummer = new Label("Telefoonnummer:");
		Label lblEmailadres = new Label("Emailadres: ");
		Label lblTrackAndTrace = new Label("TRACK AND TRACE CODE");
		Label lblVerificatiecode = new Label("Verificatiecode:");
		Label lblBarcodeLengteCode = new Label("Aantal karakters van de code:");
		Label lblBarcodePrefix = new Label("Track and Trace prefix: ");
		Label lblIsBarcodeEnkelCijfers = new Label("Bestaat de code enkel uit cijfers:");
		TextField txtNaamTransportdienst = new TextField();
		TextField txtContactpersoonVoornaam = new TextField();
		TextField txtContactpersoonFamilienaam = new TextField();
		TextField txtTelefoonnummer = new TextField();
		TextField txtEmailadres = new TextField();
		TextField txtBarcodePrefix = new TextField();
		ChoiceBox<String> cbVerificatiecode = new ChoiceBox<>();
		ChoiceBox<Integer> cbBarcodeLengte = new ChoiceBox<>();
		CheckBox cbIsBarcodeEnkelCijfers = new CheckBox();
		Button btnToevoegen = new Button("TOEVOEGEN TRANSPORTDIENST");

		// Styling gripane elementen
		btnToevoegen.setFont(Font.font("Arial", FontWeight.BOLD, 24));
		lblTitel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
		lblTrackAndTrace.setFont(Font.font("Arial", 24));

		// Opties toevoegen aan gridPane elementen
		cbVerificatiecode.getItems().add("Postcode");
		cbVerificatiecode.getItems().add("Orderid");

		for (int i = 1; i < 21; i++) {
			cbBarcodeLengte.getItems().add(i);
		}

		// Opbouw gridPane tabblad Toevoegen
		gridPaneToevoegen.setHgap(10);
		gridPaneToevoegen.setVgap(10);
		gridPaneToevoegen.setPadding(new Insets(10, 10, 10, 10));

		gridPaneToevoegen.add(lblTitel, 0, 0, 2, 1);
		gridPaneToevoegen.add(lblNaamTransportdienst, 0, 1, 1, 1);
		gridPaneToevoegen.add(txtNaamTransportdienst, 1, 1, 1, 1);
		gridPaneToevoegen.add(lblContactpersoonVoornaam, 0, 2, 1, 1);
		gridPaneToevoegen.add(txtContactpersoonVoornaam, 1, 2, 1, 1);
		gridPaneToevoegen.add(lblContactpersoonFamilienaam, 0, 3, 1, 1);
		gridPaneToevoegen.add(txtContactpersoonFamilienaam, 1, 3, 1, 1);
		gridPaneToevoegen.add(lblTelefoonnummer, 0, 4, 1, 1);
		gridPaneToevoegen.add(txtTelefoonnummer, 1, 4, 1, 1);
		gridPaneToevoegen.add(lblEmailadres, 0, 5, 1, 1);
		gridPaneToevoegen.add(txtEmailadres, 1, 5, 1, 1);
		gridPaneToevoegen.add(lblTrackAndTrace, 0, 6, 2, 1);
		gridPaneToevoegen.add(lblVerificatiecode, 0, 7, 1, 1);
		gridPaneToevoegen.add(cbVerificatiecode, 1, 7, 1, 1);
		gridPaneToevoegen.add(lblBarcodeLengteCode, 0, 8, 1, 1);
		gridPaneToevoegen.add(cbBarcodeLengte, 1, 8, 1, 1);
		gridPaneToevoegen.add(lblBarcodePrefix, 0, 9, 1, 1);
		gridPaneToevoegen.add(txtBarcodePrefix, 1, 9, 1, 1);
		gridPaneToevoegen.add(lblIsBarcodeEnkelCijfers, 0, 10, 1, 1);
		gridPaneToevoegen.add(cbIsBarcodeEnkelCijfers, 1, 10, 1, 1);
		gridPaneToevoegen.add(btnToevoegen, 0, 11, 2, 2);
		
		btnToevoegen.setOnAction(evt -> {

			try {
				String naamTransportdienst = txtNaamTransportdienst.getText();
				int barcodeLengte = (Integer) cbBarcodeLengte.getValue();
				boolean isBarcodeEnkelCijfers = cbIsBarcodeEnkelCijfers.isSelected();
				String barcodePrefix = txtBarcodePrefix.getText();
				String verificatiecode = (String) cbVerificatiecode.getValue();
				String contactVoornaam = txtContactpersoonVoornaam.getText();
				String contactFamilienaam = txtContactpersoonFamilienaam.getText();
				String contactTelefoon = txtTelefoonnummer.getText();
				String contactEmailadres = txtEmailadres.getText();

				// TODO implementeren bedrijfsId
				int bedrijfsId = 1;

				// Validatie input formulier
				ValidationService.controleerNietBlanco(naamTransportdienst);
				ValidationService.controleerGroterDanNul(barcodeLengte);
				ValidationService.controleerNietBlanco(barcodePrefix);
				ValidationService.controleerNietBlanco(contactVoornaam);
				ValidationService.controleerNietBlanco(contactFamilienaam);
				ValidationService.controleerTelefoonnummer(contactTelefoon);
				ValidationService.controleerEmail(contactEmailadres);

				// Aanmaken van een transportdienst
				dc.maakTransportdienst(naamTransportdienst, barcodeLengte, isBarcodeEnkelCijfers, barcodePrefix,
						verificatiecode, contactVoornaam, contactFamilienaam, contactTelefoon, contactEmailadres,
						bedrijfsId);

			} catch (IllegalArgumentException e) {
				// TODO degelijk errorbericht aanmaken
				System.out.println(e);
			}

			//TODO bekijken hoe je de list kan overbrengen bekijk de slides hiervoor 
			//list = FXCollections.observableArrayList(dc.getTransportdienstenDTO());
			//tableViewTransportdienst.setItems(list);
		});
		super.setContent(gridPaneToevoegen);
	}
	
	

	public GridPane getGridPaneToevoegen() {
		return gridPaneToevoegen;
	}
	
}
