package de.muenchen.selenipo.view;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import de.muenchen.selenipo.Selector;
import de.muenchen.selenipo.model.ElementFx;

public class ElementEditDialogController {

	@FXML
	private TextField identefierField;
	@FXML
	private TextField locatorField;
	@FXML
	private ComboBox<Selector> typeComboBox;

	private Stage dialogStage;
	private ElementFx elementFx;
	private boolean okClicked = false;

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		typeComboBox.setItems(FXCollections.observableArrayList(Selector
				.values()));
	}

	/**
	 * Sets the stage of this dialog.
	 * 
	 * @param dialogStage
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	/**
	 * Sets the person to be edited in the dialog.
	 * 
	 * @param person
	 */
	public void setElement(ElementFx element) {
		this.elementFx = element;

		identefierField.setText(element.getIdentefier());
		locatorField.setText(element.getLocator());
		typeComboBox.getSelectionModel().select(element.getType());
	}

	/**
	 * Returns true if the user clicked OK, false otherwise.
	 * 
	 * @return
	 */
	public boolean isOkClicked() {
		return okClicked;
	}

	/**
	 * Called when the user clicks ok.
	 */
	@FXML
	private void handleOk() {
		if (isInputValid()) {
			elementFx.setIdentefier(identefierField.getText());
			elementFx.setLocator(locatorField.getText());
			elementFx.setType(typeComboBox.getSelectionModel()
					.getSelectedItem());
			okClicked = true;
			dialogStage.close();
		}
	}

	/**
	 * Called when the user clicks cancel.
	 */
	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

	/**
	 * Validates the user input in the text fields.
	 * 
	 * @return true if the input is valid
	 */
	private boolean isInputValid() {
		String errorMessage = "";

		if (errorMessage.length() == 0) {
			return true;
		} else {
			// Show the error message.
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText(errorMessage);

			alert.showAndWait();

			return false;
		}
	}
}
