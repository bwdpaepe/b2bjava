package gui;

import java.util.Date;

import domein.DomeinController;
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
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import repository.BestellingDetailsDTO;
import repository.DoosDTO;
import repository.KlantLijstEntryDTO;

public class DozenController {
	
	private DomeinController dc;
	
	
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
	TableColumn<Button, Button> deleteColumn;


	public DozenController() {
		// TODO Auto-generated constructor stub
	}
	
	
	public void setParams(DomeinController dc) {
		this.dc = dc;
	}
	
	public void loadDozen() {
	    //System.out.println("Klanten lijst loading");
	    ObservableList<DoosDTO> dozenList = FXCollections.observableList(dc.getDozen());

	    naamColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNaam()));
	    typeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDoosType()));
	    lengteColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getLengte()));
	    breedteColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getBreedte()));
	    hoogteColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getHoogte()));
	    prijsColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrijs()));
	    isActiefColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().isActief()? "Ja" : "Nee"));
	    
	    FilteredList<DoosDTO> filteredDozenList = new FilteredList<>(dozenList, p -> true);
	    
	    SortedList<DoosDTO> sortedDozenList = new SortedList<>(filteredDozenList);
	    sortedDozenList.comparatorProperty().bind(tvDozen.comparatorProperty());
	    
	    tvDozen.setItems(sortedDozenList);
	    
	    editColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DoosDTO, Boolean>, ObservableValue<Boolean>>() {

			@Override
			public ObservableValue<Boolean> call(CellDataFeatures<DoosDTO, Boolean> features) {
				return new SimpleBooleanProperty(features.getValue() != null);
			}
	    });
	    
	    editColumn.setCellFactory(new Callback<TableColumn<DoosDTO,Boolean>, TableCell<DoosDTO,Boolean>>() {
	    	@Override public TableCell<DoosDTO, Boolean> call(TableColumn<DoosDTO, Boolean> doosBooleanTableColumn){
	    		return new ButtonCell(tvDozen);
	    	}
		});




	}
	
	private class ButtonCell extends TableCell<DoosDTO, Boolean>{
		final Button button = new Button("Wijzig");
		final StackPane paddedButton = new StackPane();
		
		
		private ButtonCell(TableView<DoosDTO> table){
		      paddedButton.setPadding(new Insets(3));
		      paddedButton.getChildren().add(button);
		      button.getStyleClass().add("tvButton");
			
		}
		
		
		
	    @Override protected void updateItem(Boolean item, boolean empty) {
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




