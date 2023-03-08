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
import repository.TransportdienstDTO;

public class BeheerTransportdienstRaadpleegTab extends Tab {

	private DomeinController dc;

	public BeheerTransportdienstRaadpleegTab(DomeinController dc) {
		super("Raadplegen");
		this.dc = dc;
		buildGui();
	}

	private void buildGui() {
		GridPane gridPaneRaadplegen = new GridPane();
		Label lblTitelRaadpleegTab = new Label("RAADPLEGEN TRANSPORTDIENST");

		Label lblNaamTransportdienstRaadpleegTab = new Label("Naam transportdienst:");
		Label lblContactpersoonVoornaamRaadpleegTab = new Label("Contactpersoon voornaam:");
		Label lblContactpersoonFamilienaamRaadpleegTab = new Label("Contactpersoon familienaam:");
		Label lblTelefoonnummerRaadpleegTab = new Label("Telefoonnummer:");
		Label lblEmailadresRaadpleegTab = new Label("Emailadres: ");
		Label lblTrackAndTraceRaadpleegTab = new Label("TRACK AND TRACE CODE");
		Label lblVerificatiecodeRaadpleegTab = new Label("Verificatiecode:");
		Label lblBarcodeLengteCodeRaadpleegTab = new Label("Aantal karakters van de code:");
		Label lblBarcodePrefixRaadpleegTab = new Label("Track and Trace prefix: ");
		Label lblIsBarcodeEnkelCijfersRaadpleegTab = new Label("Bestaat de code enkel uit cijfers:");

		TextField txtNaamTransportdienstRaadpleegTab = new TextField();
		TextField txtContactpersoonVoornaamRaadpleegTab = new TextField();
		TextField txtContactpersoonFamilienaamRaadpleegTab = new TextField();
		TextField txtTelefoonnummerRaadpleegTab = new TextField();
		TextField txtEmailadresRaadpleegTab = new TextField();
		TextField txtBarcodePrefixRaadpleegTab = new TextField();
		ChoiceBox<String> cbVerificatiecodeRaadpleegTab = new ChoiceBox<>();
		ChoiceBox<Integer> cbBarcodeLengteRaadpleegTab = new ChoiceBox<>();
		CheckBox cbIsBarcodeEnkelCijfersRaadpleegTab = new CheckBox();
		Button btnAanpassen = new Button("AANPASSEN TRANSPORTDIENST");

		// Styling gripane elementen
		btnAanpassen.setFont(Font.font("Arial", FontWeight.BOLD, 24));
		lblTitelRaadpleegTab.setFont(Font.font("Arial", FontWeight.BOLD, 24));
		lblTrackAndTraceRaadpleegTab.setFont(Font.font("Arial", 24));

		// Opties toevoegen aan gridPane elementen
		cbVerificatiecodeRaadpleegTab.getItems().add("Postcode");
		cbVerificatiecodeRaadpleegTab.getItems().add("Orderid");

		for (int i = 1; i < 21; i++) {
			cbBarcodeLengteRaadpleegTab.getItems().add(i);
		}

		// Opbouw gridPane tabblad Toevoegen
		gridPaneRaadplegen.setHgap(10);
		gridPaneRaadplegen.setVgap(10);
		gridPaneRaadplegen.setPadding(new Insets(10, 10, 10, 10));

		gridPaneRaadplegen.add(lblTitelRaadpleegTab, 0, 0, 2, 1);
		gridPaneRaadplegen.add(lblNaamTransportdienstRaadpleegTab, 0, 1, 1, 1);
		gridPaneRaadplegen.add(txtNaamTransportdienstRaadpleegTab, 1, 1, 1, 1);
		gridPaneRaadplegen.add(lblContactpersoonVoornaamRaadpleegTab, 0, 2, 1, 1);
		gridPaneRaadplegen.add(txtContactpersoonVoornaamRaadpleegTab, 1, 2, 1, 1);
		gridPaneRaadplegen.add(lblContactpersoonFamilienaamRaadpleegTab, 0, 3, 1, 1);
		gridPaneRaadplegen.add(txtContactpersoonFamilienaamRaadpleegTab, 1, 3, 1, 1);
		gridPaneRaadplegen.add(lblTelefoonnummerRaadpleegTab, 0, 4, 1, 1);
		gridPaneRaadplegen.add(txtTelefoonnummerRaadpleegTab, 1, 4, 1, 1);
		gridPaneRaadplegen.add(lblEmailadresRaadpleegTab, 0, 5, 1, 1);
		gridPaneRaadplegen.add(txtEmailadresRaadpleegTab, 1, 5, 1, 1);
		gridPaneRaadplegen.add(lblTrackAndTraceRaadpleegTab, 0, 6, 2, 1);
		gridPaneRaadplegen.add(lblVerificatiecodeRaadpleegTab, 0, 7, 1, 1);
		gridPaneRaadplegen.add(cbVerificatiecodeRaadpleegTab, 1, 7, 1, 1);
		gridPaneRaadplegen.add(lblBarcodeLengteCodeRaadpleegTab, 0, 8, 1, 1);
		gridPaneRaadplegen.add(cbBarcodeLengteRaadpleegTab, 1, 8, 1, 1);
		gridPaneRaadplegen.add(lblBarcodePrefixRaadpleegTab, 0, 9, 1, 1);
		gridPaneRaadplegen.add(txtBarcodePrefixRaadpleegTab, 1, 9, 1, 1);
		gridPaneRaadplegen.add(lblIsBarcodeEnkelCijfersRaadpleegTab, 0, 10, 1, 1);
		gridPaneRaadplegen.add(cbIsBarcodeEnkelCijfersRaadpleegTab, 1, 10, 1, 1);
		gridPaneRaadplegen.add(btnAanpassen, 0, 11, 2, 2);

		// TODO deze 4 velden een label toevoegen met de waarde van de geselecteerde td,
		// bij drukken button aanpassen kan je deze zaken terug visible zetten
		txtNaamTransportdienstRaadpleegTab.setVisible(false);
		txtContactpersoonVoornaamRaadpleegTab.setVisible(false);
		txtContactpersoonFamilienaamRaadpleegTab.setVisible(false);
		txtTelefoonnummerRaadpleegTab.setVisible(false);
		txtEmailadresRaadpleegTab.setVisible(false);
		cbVerificatiecodeRaadpleegTab.setVisible(false);
		txtBarcodePrefixRaadpleegTab.setVisible(false);
		cbBarcodeLengteRaadpleegTab.setVisible(false);
		cbIsBarcodeEnkelCijfersRaadpleegTab.setVisible(false);

		this.setContent(gridPaneRaadplegen);

		btnAanpassen.setOnAction(evt -> {
			// TODO drukken button moeten alle velden editable zijn
			// Placeholder moet nog een worden toegevoegd
			// Event van tablview moet nog gemaakt worden
			// Aanpassen in een aparte tab met op basis van de geselecteerde DTO
		});

		// Dubbel klik op rij om het item te selecteren en in de tabpane recths te zien
		// Dit moet in de tableview en daar een methode aanroepen die je hier
		// implementeert

	}

	// TODO verder uitwerken
	private void raadpleegTabOpbouwen(TransportdienstDTO selectedTransportdienstDTO, Tab raadpleegTab) {
		// tabPane.getSelectionModel().select(raadpleegTab);
		// lblNaamTransportdienstRaadpleegTabtd.setText(selectedTransportdienstDTO.getNaam());
		// TODO kijken hoe te doen via streams
		// lblContactpersoonVoornaamRaadpleegTabtd.setText(selectedTransportdienstDTO.get);;
		// lblContactpersoonFamilienaamRaadpleegTabtd
		// lblTelefoonnummerRaadpleegTabtd.setText(selectedTransportdienstDTO.get);
		// lblEmailadresRaadpleegTabtd.setText(selectedTransportdienstDTO);
//		lblVerificatiecodeRaadpleegTabtd.setText(selectedTransportdienstDTO.getVerificatieCodeString());
//		lblBarcodePrefixRaadpleegTabtd.setText(selectedTransportdienstDTO.getBarcodePrefix());
//		lblBarcodeLengteRaadpleegTabtd.setText(String.valueOf(selectedTransportdienstDTO.getBarcodeLengte()));
//		lblIsBarcodeEnkelCijfersRaadpleegTabtd
//				.setText(String.valueOf(selectedTransportdienstDTO.isBarcodeEnkelCijfers()));

	}

}
