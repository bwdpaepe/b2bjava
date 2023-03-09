package gui;

import java.util.Set;

import domein.Contactpersoon;
import domein.DomeinController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import repository.ContactpersoonDTO;
import repository.TransportdienstDTO;

public class BeheerTransportdienstRaadpleegTab extends Tab {

	private DomeinController dc;
	private Label lblNaamTransportdienstRaadpleegTabtd = new Label();
	private Label lblVerificatiecodeRaadpleegTabtd = new Label();
	private Label lblBarcodeLengteRaadpleegTabtd = new Label();
	private Label lblBarcodePrefixRaadpleegTabtd = new Label();
	private Label lblIsBarcodeEnkelCijfersRaadpleegTabtd = new Label();
	private TransportdienstDTO selectedTransportdienstDTO;
	private GridPane gridPaneRaadplegen = new GridPane();
	private Button btnAanpassen = new Button("AANPASSEN TRANSPORTDIENST");

	public BeheerTransportdienstRaadpleegTab(DomeinController dc) {
		super("Raadplegen");
		this.dc = dc;
		buildGui();
	}

	private void buildGui() {

		Label lblTitelRaadpleegTab = new Label("RAADPLEGEN TRANSPORTDIENST");

		Label lblNaamTransportdienstRaadpleegTab = new Label("Naam transportdienst:");
		Label lblTrackAndTraceRaadpleegTab = new Label("Contactpersonen");
		Label lblVerificatiecodeRaadpleegTab = new Label("Verificatiecode:");
		Label lblBarcodeLengteCodeRaadpleegTab = new Label("Aantal karakters van de code:");
		Label lblBarcodePrefixRaadpleegTab = new Label("Track and Trace prefix: ");
		Label lblIsBarcodeEnkelCijfersRaadpleegTab = new Label("Bestaat de code enkel uit cijfers:");
		ChoiceBox<String> cbVerificatiecodeRaadpleegTab = new ChoiceBox<>();
		ChoiceBox<Integer> cbBarcodeLengteRaadpleegTab = new ChoiceBox<>();
		CheckBox cbIsBarcodeEnkelCijfersRaadpleegTab = new CheckBox();

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
		gridPaneRaadplegen.add(lblNaamTransportdienstRaadpleegTabtd, 1, 1, 1, 1);
		gridPaneRaadplegen.add(lblVerificatiecodeRaadpleegTab, 0, 2, 1, 1);
		gridPaneRaadplegen.add(lblVerificatiecodeRaadpleegTabtd, 1, 2, 1, 1);
		gridPaneRaadplegen.add(lblBarcodeLengteCodeRaadpleegTab, 0, 3, 1, 1);
		gridPaneRaadplegen.add(lblBarcodeLengteRaadpleegTabtd, 1, 3, 1, 1);
		gridPaneRaadplegen.add(lblBarcodePrefixRaadpleegTab, 0, 4, 1, 1);
		gridPaneRaadplegen.add(lblBarcodePrefixRaadpleegTabtd, 1, 4, 1, 1);
		gridPaneRaadplegen.add(lblIsBarcodeEnkelCijfersRaadpleegTab, 0, 5, 1, 1);
		gridPaneRaadplegen.add(lblIsBarcodeEnkelCijfersRaadpleegTabtd, 1, 5, 1, 1);

		gridPaneRaadplegen.add(lblTrackAndTraceRaadpleegTab, 0, 6, 2, 1);

		this.setContent(gridPaneRaadplegen);

		btnAanpassen.setOnAction(evt -> {
			// TODO drukken button moeten alle velden editable zijn
			// Placeholder moet nog een worden toegevoegd
			// Event van tablview moet nog gemaakt worden
			// Aanpassen in een aparte tab met op basis van de geselecteerde DTO
		});
	}

	public void raadpleegTabGui(TransportdienstDTO selectedTransportdienstDTO) {
		Set<ContactpersoonDTO> contactpersonen = selectedTransportdienstDTO.getContactpersonen();
		contactpersonen.add(new ContactpersoonDTO(new Contactpersoon("Ian", "daelman", "test@test.com", "+12414241")));

		ContactpersonenTableView grid = new ContactpersonenTableView(contactpersonen);
		gridPaneRaadplegen.add(grid, 0, gridPaneRaadplegen.getRowCount(), 2, 2);
		gridPaneRaadplegen.add(btnAanpassen, 0, gridPaneRaadplegen.getRowCount(), 2, 2);

	}

}
