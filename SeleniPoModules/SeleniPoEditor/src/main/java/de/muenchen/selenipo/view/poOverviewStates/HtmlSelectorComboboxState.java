package de.muenchen.selenipo.view.poOverviewStates;

import org.apache.log4j.Logger;

import de.muenchen.selenipo.view.PoOverviewController;
import de.muenchen.selenipo.view.PoOverviewState;

public class HtmlSelectorComboboxState implements PoOverviewState {

	private static final Logger logger = Logger
			.getLogger(HtmlSelectorComboboxState.class);

	PoOverviewController poOverviewController;

	public HtmlSelectorComboboxState(PoOverviewController poOverviewController) {
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
		// TODO Auto-generated method stub

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
