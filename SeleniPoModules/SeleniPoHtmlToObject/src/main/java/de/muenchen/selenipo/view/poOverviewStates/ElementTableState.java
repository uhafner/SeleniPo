package de.muenchen.selenipo.view.poOverviewStates;

import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.util.Callback;

import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import de.muenchen.selenipo.Selector;
import de.muenchen.selenipo.impl.fxModel.ElementFx;
import de.muenchen.selenipo.view.PoOverviewController;
import de.muenchen.selenipo.view.PoOverviewController.Colour;
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
			poOverviewController.createNoElementSelectedAlert(
					poOverviewController.getMainApp().getPrimaryStage(),
					"element Table");
		}

	}

	@Override
	public void handleTest() {
		TableView<ElementFx> elementTable = poOverviewController
				.getElementTable();

		ElementFx element = elementTable.getSelectionModel().getSelectedItem();
		if (element != null) {
			WebDriver driver = poOverviewController.getMainApp().getWebDriver();
			Selector type = element.getType();
			String locator = element.getLocator();
			try {
				WebElement webElement = driver.findElement(type.by(locator));
				poOverviewController.getElementColour().put(
						elementTable.getSelectionModel().getSelectedIndex(),
						Colour.GREEN);

			} catch (NoSuchElementException e) {
				poOverviewController.getElementColour().put(
						elementTable.getSelectionModel().getSelectedIndex(),
						Colour.RED);
			}

		} else {
			poOverviewController.createNoElementSelectedAlert(
					poOverviewController.getMainApp().getPrimaryStage(),
					"element Table");
		}
	}
}
