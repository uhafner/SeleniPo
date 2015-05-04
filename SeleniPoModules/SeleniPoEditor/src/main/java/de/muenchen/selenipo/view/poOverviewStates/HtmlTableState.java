package de.muenchen.selenipo.view.poOverviewStates;

import javafx.scene.control.TableView;

import org.apache.log4j.Logger;

import de.muenchen.selenipo.impl.fxModel.ElementFx;
import de.muenchen.selenipo.view.PoOverviewController;
import de.muenchen.selenipo.view.PoOverviewState;

public class HtmlTableState implements PoOverviewState {

	private static final Logger logger = Logger.getLogger(HtmlTableState.class);

	PoOverviewController poOverviewController;

	public HtmlTableState(PoOverviewController poOverviewController) {
		super();
		this.poOverviewController = poOverviewController;
	}

	@Override
	public void handleNew() {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleDelete() {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleEdit() {
		TableView<ElementFx> htmlTable = poOverviewController.getHtmlTable();
		ElementFx element = htmlTable.getSelectionModel().getSelectedItem();
		if (element != null) {
			boolean okClicked = poOverviewController.getMainApp()
					.showElementEditDialog(element);
		} else {
			poOverviewController.createNoElementSelectedAlert(
					poOverviewController.getMainApp().getPrimaryStage(),
					"htmlElement Table");
		}

	}

	@Override
	public boolean handleTest() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean handleTestWithMessage() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean handleTestAndClick() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void handleMoveHtmlToElement() {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleMoveHtmlToTransition() {
		// TODO Auto-generated method stub

	}

}
