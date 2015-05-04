package de.muenchen.selenipo.view.poOverviewStates;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableView;

import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import de.muenchen.selenipo.Selector;
import de.muenchen.selenipo.impl.fxModel.ElementFx;
import de.muenchen.selenipo.impl.fxModel.TransitionFx;
import de.muenchen.selenipo.view.PoOverviewController;
import de.muenchen.selenipo.view.PoOverviewController.Colour;
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
			poOverviewController.createNoElementSelectedAlert(
					poOverviewController.getMainApp().getPrimaryStage(),
					"transition table");
		}
	}

	@Override
	public boolean handleTest() {
		TableView<TransitionFx> transitionTable = poOverviewController
				.getTransitionTable();

		TransitionFx transition = transitionTable.getSelectionModel()
				.getSelectedItem();
		if (transition != null) {
			WebDriver driver = poOverviewController.getMainApp().getWebDriver();
			Selector type = transition.getType();
			String locator = transition.getLocator();
			try {
				WebElement webElement = driver.findElement(type.by(locator));
				poOverviewController.getTransitionColour().put(
						transitionTable.getSelectionModel().getSelectedIndex(),
						Colour.GREEN);
				return true;

			} catch (NoSuchElementException e) {
				poOverviewController.getTransitionColour().put(
						transitionTable.getSelectionModel().getSelectedIndex(),
						Colour.RED);
				return false;
			}

		} else {
			poOverviewController.createNoElementSelectedAlert(
					poOverviewController.getMainApp().getPrimaryStage(),
					"transition Table");
			return false;
		}
	}

	@Override
	public boolean handleTestWithMessage() {
		TableView<TransitionFx> transitionTable = poOverviewController
				.getTransitionTable();

		TransitionFx transition = transitionTable.getSelectionModel()
				.getSelectedItem();
		if (transition != null) {
			WebDriver driver = poOverviewController.getMainApp().getWebDriver();
			Selector type = transition.getType();
			String locator = transition.getLocator();
			try {
				WebElement webElement = driver.findElement(type.by(locator));
				poOverviewController.getTransitionColour().put(
						transitionTable.getSelectionModel().getSelectedIndex(),
						Colour.GREEN);
				// Meldung anzeigen
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.initOwner(poOverviewController.getMainApp()
						.getPrimaryStage());
				alert.setTitle("Element Gefunden");
				alert.setHeaderText("Element Tag: " + webElement.getTagName());
				alert.setContentText("Text: " + webElement.getText());

				alert.showAndWait();
				return true;

			} catch (NoSuchElementException e) {
				poOverviewController.getTransitionColour().put(
						transitionTable.getSelectionModel().getSelectedIndex(),
						Colour.RED);
				// Meldung anzeigen
				Alert alert = new Alert(AlertType.WARNING);
				alert.initOwner(poOverviewController.getMainApp()
						.getPrimaryStage());
				alert.setTitle("Element nicht Gefunden");
				alert.setHeaderText("Stacktrace:");
				alert.setContentText(e.toString());

				alert.showAndWait();
				return false;
			}

		} else {
			poOverviewController.createNoElementSelectedAlert(
					poOverviewController.getMainApp().getPrimaryStage(),
					"transition Table");
			return false;
		}
	}

	@Override
	public boolean handleTestAndClick() {
		boolean handleTest = handleTest();
		if (handleTest) {
			TableView<TransitionFx> transitionTable = poOverviewController
					.getTransitionTable();
			ElementFx transition = transitionTable.getSelectionModel()
					.getSelectedItem();
			WebDriver driver = poOverviewController.getMainApp().getWebDriver();
			Selector type = transition.getType();
			String locator = transition.getLocator();
			WebElement webElement = driver.findElement(type.by(locator));
			webElement.click();
		}
		return handleTest;
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
