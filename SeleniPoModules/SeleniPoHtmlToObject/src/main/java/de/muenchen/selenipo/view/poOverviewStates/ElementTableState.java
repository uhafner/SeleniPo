package de.muenchen.selenipo.view.poOverviewStates;

import javafx.scene.control.TableView;

import org.apache.log4j.Logger;

import de.muenchen.selenipo.model.ElementFx;
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
		System.out.println("Elem");

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
	public void handelEdit() {
		// TODO Auto-generated method stub

	}

}
