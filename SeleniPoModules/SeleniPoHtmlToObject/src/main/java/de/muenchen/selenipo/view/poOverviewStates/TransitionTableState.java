package de.muenchen.selenipo.view.poOverviewStates;

import org.apache.log4j.Logger;

import javafx.scene.control.TableView;
import de.muenchen.selenipo.model.TransitionFx;
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
		System.out.println("Trans");

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
	public void handelEdit() {
		// TODO Auto-generated method stub

	}

}
