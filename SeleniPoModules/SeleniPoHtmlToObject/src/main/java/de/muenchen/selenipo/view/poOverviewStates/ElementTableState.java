package de.muenchen.selenipo.view.poOverviewStates;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableView;

import org.apache.log4j.Logger;

import de.muenchen.selenipo.Selector;
import de.muenchen.selenipo.impl.fxModel.ElementFx;
import de.muenchen.selenipo.view.PoOverviewController;
import de.muenchen.selenipo.view.PoOverviewState;

public class ElementTableState implements PoOverviewState {
	private static final Logger logger = Logger
			.getLogger(ElementTableState.class);

	PoOverviewController poOverviewController;

	public ElementTableState(PoOverviewController poOverviewController) {
		super();
		this.poOverviewController = poOverviewController;
	}

	@Override
	public void handleNew() {
		if (poOverviewController.getPoComboBox().getSelectionModel()
				.getSelectedItem() != null) {
			ElementFx element = new ElementFx("", null, "");
			boolean okClicked = poOverviewController.getMainApp()
					.showElementEditDialog(element);
			if (okClicked) {
				poOverviewController
						.getMainApp()
						.getPoModelFx()
						.getPoGenericsFx()
						.get(poOverviewController.getPoComboBox()
								.getSelectionModel().getSelectedIndex())
						.getElementsFx().add(element);
			}
		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(poOverviewController.getMainApp().getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No pageObject Selected");
			alert.setContentText("Please select a pageObject in the dropdown.");

			alert.showAndWait();
		}

	}

	@Override
	public void handleDelete() {
		TableView<ElementFx> elementTable = poOverviewController
				.getElementTable();
		int selectedElementTableIndex = poOverviewController.getElementTable()
				.getSelectionModel().getSelectedIndex();
		logger.debug("selectedElementTableIndex: [" + selectedElementTableIndex
				+ "]");
		if (selectedElementTableIndex >= 0) {
			elementTable.getItems().remove(selectedElementTableIndex);
			logger.debug("Removed index [" + selectedElementTableIndex
					+ "] from elementTable.");
		} else {
			poOverviewController.createNoElementSelectedAlert(
					poOverviewController.getMainApp().getPrimaryStage(),
					"element Table");
		}

	}

	@Override
	public void handleEdit() {
		TableView<ElementFx> elementTable = poOverviewController
				.getElementTable();
		ElementFx element = elementTable.getSelectionModel().getSelectedItem();
		if (element != null) {
			boolean okClicked = poOverviewController.getMainApp()
					.showElementEditDialog(element);
		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(poOverviewController.getMainApp().getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No Element Selected");
			alert.setContentText("Please select an element in the table.");

			alert.showAndWait();
		}

	}

}
