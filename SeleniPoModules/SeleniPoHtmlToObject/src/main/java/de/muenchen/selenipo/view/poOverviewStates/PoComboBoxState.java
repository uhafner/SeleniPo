package de.muenchen.selenipo.view.poOverviewStates;

import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ComboBox;

import org.apache.log4j.Logger;

import de.muenchen.selenipo.model.ElementFx;
import de.muenchen.selenipo.model.PoGenericFx;
import de.muenchen.selenipo.view.PoOverviewController;
import de.muenchen.selenipo.view.PoOverviewState;

public class PoComboBoxState implements PoOverviewState {

	private static final Logger logger = Logger
			.getLogger(PoComboBoxState.class);

	PoOverviewController poOverviewController;

	public PoComboBoxState(PoOverviewController poOverviewController) {
		super();
		this.poOverviewController = poOverviewController;
	}

	@Override
	public void handleNew() {
		PoGenericFx poGenericFx = new PoGenericFx("");
		boolean okClicked = poOverviewController.getMainApp().showPoEditDialog(
				poGenericFx);
		if (okClicked) {
			poOverviewController.getMainApp().getPoModelFx().getPoGenericsFx()
					.add(poGenericFx);
		}
	}

	@Override
	public void handleDelete() {
		ComboBox<PoGenericFx> poComboBox = poOverviewController.getPoComboBox();
		int selectedPoComboboxIndex = poComboBox.getSelectionModel()
				.getSelectedIndex();
		logger.debug("selectedPoComboboxIndex: [" + selectedPoComboboxIndex
				+ "]");
		if (selectedPoComboboxIndex >= 0) {
			String customText = "Element: ["
					+ poComboBox.getItems().get(selectedPoComboboxIndex)
							.getIdentifier() + "]";
			Alert createDialog = poOverviewController.createConfirmDelete(
					poOverviewController.getMainApp().getPrimaryStage(),
					customText);
			if (createDialog.getResult().getButtonData()
					.equals(ButtonData.OK_DONE)) {
				poComboBox.setValue(null);
				poComboBox.getItems().remove(selectedPoComboboxIndex);
				logger.debug("Removed index [" + selectedPoComboboxIndex
						+ "] from poCombobox.");
			}
		} else {
			poOverviewController.createNoElementSelectedAlert(
					poOverviewController.getMainApp().getPrimaryStage(),
					"po comboBox");
		}
	}

	@Override
	public void handleEdit() {
		ComboBox<PoGenericFx> poComboBox = poOverviewController.getPoComboBox();
		PoGenericFx poGenericFx = poComboBox.getSelectionModel()
				.getSelectedItem();
		if (poGenericFx != null) {
			boolean okClicked = poOverviewController.getMainApp()
					.showPoEditDialog(poGenericFx);
		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(poOverviewController.getMainApp().getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No Element Selected");
			alert.setContentText("Please select a pageObject from the Dropdown.");

			alert.showAndWait();
		}
	}

}
