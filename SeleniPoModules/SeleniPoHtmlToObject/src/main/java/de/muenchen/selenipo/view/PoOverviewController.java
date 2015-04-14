package de.muenchen.selenipo.view;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import de.muenchen.selenipo.MainApp;
import de.muenchen.selenipo.model.ElementFx;
import de.muenchen.selenipo.model.PoGenericFx;
import de.muenchen.selenipo.model.TransitionFx;

public class PoOverviewController {

	@FXML
	private TableView<ElementFx> elementTable;
	@FXML
	private TableColumn<ElementFx, String> elemIdentefierColumn;
	@FXML
	private TableColumn<ElementFx, String> elemSelectorTypeColumn;
	@FXML
	private TableColumn<ElementFx, String> elemLocatorColumn;

	@FXML
	private TableView<TransitionFx> transitionTable;
	@FXML
	private TableColumn<TransitionFx, String> transIdentefierColumn;
	@FXML
	private TableColumn<TransitionFx, String> transSelectorTypeColumn;
	@FXML
	private TableColumn<TransitionFx, String> transLocatorColumn;
	@FXML
	private TableColumn<TransitionFx, String> transDestinationColumn;

	@FXML
	private ComboBox<PoGenericFx> poComboBox;

	// Reference to the main application.
	private MainApp mainApp;

	/**
	 * The constructor. The constructor is called before the initialize()
	 * method.
	 */
	public PoOverviewController() {
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		elemLocatorColumn.setCellValueFactory(cellData -> cellData.getValue()
				.locatorProperty());
		elemSelectorTypeColumn.setCellValueFactory(cellData -> cellData
				.getValue().typeProperty().asString());
		elemIdentefierColumn.setCellValueFactory(cellData -> cellData
				.getValue().identefierProperty());

		transLocatorColumn.setCellValueFactory(cellData -> cellData.getValue()
				.locatorProperty());
		transSelectorTypeColumn.setCellValueFactory(cellData -> cellData
				.getValue().typeProperty().asString());
		transIdentefierColumn.setCellValueFactory(cellData -> cellData
				.getValue().identefierProperty());
		transDestinationColumn.setCellValueFactory(cellData -> cellData
				.getValue().destinationProperty().asString());
	}

	@FXML
	private void poComboBoxAction() {
		int selectedIndex = poComboBox.getSelectionModel().getSelectedIndex();

		elementTable.setItems(null);
		transitionTable.setItems(null);

		elementTable.setItems(mainApp.getPoModelFx().getPoGenericsFx()
				.get(selectedIndex).getElementsFx());
		transitionTable.setItems(mainApp.getPoModelFx().getPoGenericsFx()
				.get(selectedIndex).getTransitionsFx());
	}

	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		elementTable.setItems(mainApp.getPoModelFx().getPoGenericsFx().get(0)
				.getElementsFx());
		poComboBox.setItems(mainApp.getPoModelFx().getPoGenericsFx());

	}

}
