package de.muenchen.selenipo.view.poOverviewStates;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableView;

import org.apache.log4j.Logger;

import de.muenchen.selenipo.impl.fxModel.ElementFx;
import de.muenchen.selenipo.impl.fxModel.TransitionFx;
import de.muenchen.selenipo.view.PoOverviewController;
import de.muenchen.selenipo.view.PoOverviewState;

public class TransitionTableState implements PoOverviewState {

	private static final Logger logger = Logger
			.getLogger(TransitionTableState.class);

	PoOverviewController poOverviewController;

	public TransitionTableState(PoOverviewController poOverviewController) {
		super();
		this.poOverviewController = poOverviewController;
	}

	@Override
	public void handleNew() {
		if (poOverviewController.getPoComboBox().getSelectionModel()
				.getSelectedItem() != null) {
			TransitionFx transition = new TransitionFx("", null, "", null);
			boolean okClicked = poOverviewController.getMainApp()
					.showTransitionEditDialog(transition);
			if (okClicked) {
				poOverviewController
						.getMainApp()
						.getPoModelFx()
						.getPoGenericsFx()
						.get(poOverviewController.getPoComboBox()
								.getSelectionModel().getSelectedIndex())
						.getTransitionsFx().add(transition);
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
		TableView<TransitionFx> transitionTable = poOverviewController
				.getTransitionTable();
		int selectedTransitionTableIndex = transitionTable.getSelectionModel()
				.getSelectedIndex();
		logger.debug("selectedTransitionTableIndex: ["
				+ selectedTransitionTableIndex + "]");
		if (selectedTransitionTableIndex >= 0) {
			transitionTable.getItems().remove(selectedTransitionTableIndex);
			logger.debug("Removed index [" + selectedTransitionTableIndex
					+ "] from transitionTable.");
		} else {
			poOverviewController.createNoElementSelectedAlert(
					poOverviewController.getMainApp().getPrimaryStage(),
					"transition table");
		}

	}

	@Override
	public void handleEdit() {
		TableView<TransitionFx> transitionTable = poOverviewController
				.getTransitionTable();
		TransitionFx transition = transitionTable.getSelectionModel()
				.getSelectedItem();
		if (transition != null) {
			boolean okClicked = poOverviewController.getMainApp()
					.showTransitionEditDialog(transition);
		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(poOverviewController.getMainApp().getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No Element Selected");
			alert.setContentText("Please select a transition in the table.");

			alert.showAndWait();
		}
	}

}
