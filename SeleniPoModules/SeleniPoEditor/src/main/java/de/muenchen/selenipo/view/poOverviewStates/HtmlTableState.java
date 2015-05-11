package de.muenchen.selenipo.view.poOverviewStates;

import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;

import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import de.muenchen.selenipo.Selector;
import de.muenchen.selenipo.impl.fxModel.ElementFx;
import de.muenchen.selenipo.impl.fxModel.PoGenericFx;
import de.muenchen.selenipo.impl.fxModel.TransitionFx;
import de.muenchen.selenipo.view.PoOverviewController;
import de.muenchen.selenipo.view.PoOverviewState;
import de.muenchen.selenipo.view.PoOverviewController.Colour;

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

	}

	@Override
	public boolean handleTest() {
		TableView<ElementFx> htmlTable = poOverviewController.getHtmlTable();

		ElementFx element = htmlTable.getSelectionModel().getSelectedItem();
		if (element != null) {
			WebDriver driver = poOverviewController.getMainApp().getWebDriver();
			Selector type = element.getType();
			String locator = element.getLocator();
			try {
				WebElement webElement = driver.findElement(type.by(locator));
				poOverviewController.getHtmlColour().put(
						htmlTable.getSelectionModel().getSelectedIndex(),
						Colour.GREEN);

				return true;
			} catch (NoSuchElementException e) {
				poOverviewController.getHtmlColour().put(
						htmlTable.getSelectionModel().getSelectedIndex(),
						Colour.RED);
				return false;
			}

		} else {
			poOverviewController.createNoElementSelectedAlert(
					poOverviewController.getMainApp().getPrimaryStage(),
					"html Table");
			return false;
		}
	}

	@Override
	public boolean handleTestWithMessage() {
		TableView<ElementFx> htmlTable = poOverviewController.getHtmlTable();

		ElementFx element = htmlTable.getSelectionModel().getSelectedItem();
		if (element != null) {
			WebDriver driver = poOverviewController.getMainApp().getWebDriver();
			Selector type = element.getType();
			String locator = element.getLocator();
			try {
				WebElement webElement = driver.findElement(type.by(locator));
				poOverviewController.getHtmlColour().put(
						htmlTable.getSelectionModel().getSelectedIndex(),
						Colour.GREEN);
				// Meldung anzeigen
				poOverviewController.createWebElementInfo(poOverviewController
						.getMainApp().getPrimaryStage(), webElement, driver);
				return true;

			} catch (NoSuchElementException e) {
				poOverviewController.getHtmlColour().put(
						htmlTable.getSelectionModel().getSelectedIndex(),
						Colour.RED);
				// Meldung anzeigen
				poOverviewController.createElementNotFoundAlert(
						poOverviewController.getMainApp().getPrimaryStage(), e);
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
			TableView<ElementFx> htmlTable = poOverviewController
					.getHtmlTable();
			ElementFx element = htmlTable.getSelectionModel().getSelectedItem();
			WebDriver driver = poOverviewController.getMainApp().getWebDriver();
			Selector type = element.getType();
			String locator = element.getLocator();
			WebElement webElement = driver.findElement(type.by(locator));
			webElement.click();
		}
		return handleTest;
	}

	@Override
	public void handleMoveHtmlToElement() {
		TableView<ElementFx> htmlTable = poOverviewController.getHtmlTable();
		ElementFx element = htmlTable.getSelectionModel().getSelectedItem();
		if (element != null) {
			PoGenericFx selectedPo = poOverviewController.getPoComboBox()
					.getSelectionModel().getSelectedItem();
			if (selectedPo != null) {
				selectedPo.getElementsFx().add(element);
			} else {
				poOverviewController
						.createNoPoSelectedAlert(poOverviewController
								.getMainApp().getPrimaryStage());
			}
		} else {
			poOverviewController.createNoElementSelectedAlert(
					poOverviewController.getMainApp().getPrimaryStage(),
					"htmlElement Table");
		}

	}

	@Override
	public void handleMoveHtmlToTransition() {
		TableView<ElementFx> htmlTable = poOverviewController.getHtmlTable();
		ElementFx element = htmlTable.getSelectionModel().getSelectedItem();
		if (element != null) {
			PoGenericFx selectedPo = poOverviewController.getPoComboBox()
					.getSelectionModel().getSelectedItem();
			if (selectedPo != null) {
				TransitionFx transFx = poOverviewController.getMainApp()
						.getConverterService()
						.convertElementToTransitionFx(element);
				boolean okClicked = poOverviewController.getMainApp()
						.showTransitionEditDialog(transFx);
				if (okClicked) {
					selectedPo.getTransitionsFx().add(transFx);
				}
			} else {
				poOverviewController
						.createNoPoSelectedAlert(poOverviewController
								.getMainApp().getPrimaryStage());
			}
		} else {
			poOverviewController.createNoElementSelectedAlert(
					poOverviewController.getMainApp().getPrimaryStage(),
					"htmlElement Table");
		}

	}

}
