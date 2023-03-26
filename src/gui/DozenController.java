package gui;

import java.awt.event.ActionEvent;
import java.util.Date;

import javax.swing.event.ChangeListener;

import domein.DomeinController;
import javafx.animation.FadeTransition;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.util.Duration;
import repository.BestellingDetailsDTO;
import repository.DoosDTO;
import repository.KlantLijstEntryDTO;
import service.ValidationService;

public class DozenController {

	private DomeinController dc;
	private Boolean formError = false;
	@FXML
	private TableView<DoosDTO> tvDozen;
	@FXML
	TableColumn<DoosDTO, String> naamColumn;
	@FXML
	TableColumn<DoosDTO, String> typeColumn;
	@FXML
	TableColumn<DoosDTO, Number> lengteColumn;
	@FXML
	TableColumn<DoosDTO, Number> breedteColumn;
	@FXML
	TableColumn<DoosDTO, Number> hoogteColumn;
	@FXML
	TableColumn<DoosDTO, Number> prijsColumn;
	@FXML
	TableColumn<DoosDTO, String> isActiefColumn;
	@FXML
	TableColumn<DoosDTO, Boolean> editColumn;
	@FXML
	private TextField tfNaam;
	@FXML
	private ComboBox<String> cbType;
	@FXML
	private TextField tfLengte;
	@FXML
	private TextField tfBreedte;
	@FXML
	private TextField tfHoogte;
	@FXML
	private TextField tfPrijs;
	@FXML
	private Button addButton;
	@FXML
	private Pane errorPane;
	@FXML
	private Text errorMessage;

	public DozenController() {
		// TODO Auto-generated constructor stub
	}

	public void setParams(DomeinController dc) {
		this.dc = dc;
		addButton.setOnAction(event -> {

			voegDoosToe();

		});

	}

	public void loadDozen() {
		// System.out.println("Klanten lijst loading");
		ObservableList<DoosDTO> dozenList = FXCollections.observableList(dc.getDozen());

		naamColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNaam()));
		typeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDoosType()));
		lengteColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getLengte()));
		breedteColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getBreedte()));
		hoogteColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getHoogte()));
		prijsColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrijs()));
		isActiefColumn.setCellValueFactory(
				cellData -> new SimpleStringProperty(cellData.getValue().isActief() ? "Ja" : "Nee"));

		FilteredList<DoosDTO> filteredDozenList = new FilteredList<>(dozenList, p -> true);

		SortedList<DoosDTO> sortedDozenList = new SortedList<>(filteredDozenList);
		sortedDozenList.comparatorProperty().bind(tvDozen.comparatorProperty());

		tvDozen.setItems(sortedDozenList);

		editColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<DoosDTO, Boolean>, ObservableValue<Boolean>>() {

					@Override
					public ObservableValue<Boolean> call(CellDataFeatures<DoosDTO, Boolean> features) {
						return new SimpleBooleanProperty(features.getValue() != null);
					}
				});

		editColumn.setCellFactory(new Callback<TableColumn<DoosDTO, Boolean>, TableCell<DoosDTO, Boolean>>() {
			@Override
			public TableCell<DoosDTO, Boolean> call(TableColumn<DoosDTO, Boolean> doosBooleanTableColumn) {
				return new ButtonCell(tvDozen);
			}
		});

		TextField[] textFields = { tfLengte, tfBreedte, tfHoogte, tfPrijs };
		setFieldNumericalOnly(textFields);

		ObservableList<String> typeOptions = FXCollections.observableArrayList("Standaard", "Custom");
		cbType.setItems(typeOptions);

	}

	private void voegDoosToe() {
		String naam = tfNaam.getText();
		String type = cbType.getValue();
		String lengte = tfLengte.getText();
		String breedte = tfBreedte.getText();
		String hoogte = tfHoogte.getText();
		String prijs = tfPrijs.getText();
		double prijsd = 0;
		double lengted = 0;
		double breedted = 0;
		double hoogted = 0;
		
		while(!formError) {

				try {
					ValidationService.controleerNietBlanco(naam);
					ValidationService.controleerNietBlanco(type);
				} catch (Exception e) {
					formError = true;
					System.out.print("stringfout");
					errorMessage.setText("Controleer dat de naam en het type correct zijn");
					errorPane.setVisible(true);
					FadeTransition ft = new FadeTransition(Duration.millis(5000), errorPane);
					ft.setFromValue(1);
					ft.setToValue(0);
					ft.play();
					ft.setOnFinished(event -> errorPane.setVisible(false));
					
				}
			

			
			if (formError == false) {
				try {
					prijsd = Double.parseDouble(prijs);
					lengted = Double.parseDouble(lengte);
					breedted = Double.parseDouble(breedte);
					hoogted = Double.parseDouble(hoogte);
					ValidationService.controleerGroterDanNul(prijsd);
					ValidationService.controleerGroterDanNul(lengted);
					ValidationService.controleerGroterDanNul(breedted);
					ValidationService.controleerGroterDanNul(hoogted);
				} catch (Exception e) {
					formError = true;
					errorMessage.setText("Controleer dat de numerieke gegevens correct werden ingevoerd");
					errorPane.setVisible(true);
					FadeTransition ft = new FadeTransition(Duration.millis(5000), errorPane);
					ft.setFromValue(1);
					ft.setToValue(0);
					ft.play();
					ft.setOnFinished(event -> errorPane.setVisible(false));
				}
			}
			else {
				//errorMessage.setText(null);
				formError = false;
				break;
			}


			if (formError == false) {
				try {
					dc.maakDoos(naam, type, lengted, breedted, hoogted, prijsd);
					System.out.print(lengted + " + " +  breedted + " + " +  hoogted);
					loadDozen();
					tfNaam.setText(null);
					cbType.setValue(null);
					tfLengte.setText(null);
					tfBreedte.setText(null);
					tfHoogte.setText(null);
					tfPrijs.setText(null);
					
					//hergebruik van errorPane, nog aan te passen naar mss notification pane 
					errorMessage.setText("Succesvol toegevoegd");
					errorPane.setStyle("-fx-background-color: #99EDC3");
					errorPane.setVisible(true);
					FadeTransition ft = new FadeTransition(Duration.millis(5000), errorPane);
					ft.setFromValue(1);
					ft.setToValue(0);
					ft.play();
					ft.setOnFinished(event -> {
						errorPane.setVisible(false);
						errorPane.setStyle("-fx-background-color: #EEB2B2");
						errorMessage.setText(null);
					});

					
					
				} catch (Exception e) {

					errorMessage.setText("Er ging iets mis bij het maken van de doos, controleer dat de naam niet reeds in gebruik is");
					errorPane.setVisible(true);
					FadeTransition ft = new FadeTransition(Duration.millis(5000), errorPane);
					ft.setFromValue(1);
					ft.setToValue(0);
					ft.play();
					ft.setOnFinished(event -> errorPane.setVisible(false));



				}
				break;
				

			}
			else {
				//errorMessage.setText(null);
				formError = false;
				break;
			}
		}
		
		if (formError == true) {
			errorPane.setVisible(true);
			FadeTransition ft = new FadeTransition(Duration.millis(5000), errorPane);
			ft.setFromValue(1);
			ft.setToValue(0);
			ft.play();
			ft.setOnFinished(event -> errorPane.setVisible(false));


		}


	}

	private void setFieldNumericalOnly(TextField[] textFields) {
		for (TextField textField : textFields) {
			textField.textProperty().addListener((observable, OldValue, newValue) -> {

				if ( newValue != null && !newValue.matches("^[+]?(([1-9]\\d*)|0)(\\.\\d*)?")) {
					textField.setText(newValue.replaceAll("[^\\d+\\.?\\d*$]?", ""));
					textField.setStyle("-fx-background-color: red");
					errorMessage.setText( "De ingevoerde waarden voor lengte, breedte, hoogte en/of prijs zijn geen geldige getallen");
					formError = true;
				} else {
					textField.setStyle("-fx-background-color: #818589");
					errorMessage.setText(null);
					formError = false;
				}

			});

		}
	}

	private class ButtonCell extends TableCell<DoosDTO, Boolean> {
		final Button button = new Button("Wijzig");
		final StackPane paddedButton = new StackPane();

		private ButtonCell(TableView<DoosDTO> table) {
			paddedButton.setPadding(new Insets(3));
			paddedButton.getChildren().add(button);
			button.getStyleClass().add("tvButton");

		}

		@Override
		protected void updateItem(Boolean item, boolean empty) {
			super.updateItem(item, empty);
			if (!empty) {
				setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
				setGraphic(paddedButton);
			} else {
				setGraphic(null);
			}
		}
	}
}
