package de.muenchen.selenipo.view;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.print.PageOrientation;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import de.muenchen.selenipo.MainApp;
import de.muenchen.selenipo.Selector;
import de.muenchen.selenipo.model.ElementFx;
import de.muenchen.selenipo.model.PoGenericFx;
import de.muenchen.selenipo.model.TransitionFx;

public class TransitionEditDialogController {

	@FXML
	private TextField identefierField;
	@FXML
	private TextField locatorField;
	@FXML
	private ComboBox<Selector> typeComboBox;
	@FXML
	private ComboBox<PoGenericFx> poComboBox;

	private Stage dialogStage;
	private TransitionFx transitionFx;
	private boolean okClicked = false;
	private MainApp mainApp;

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
	public void setTransition(TransitionFx transition) {
		this.transitionFx = transition;

		identefierField.setText(transition.getIdentefier());
		locatorField.setText(transition.getLocator());
		typeComboBox.getSelectionModel().select(transition.getType());
		poComboBox.getSelectionModel().select(
				transition.destinationProperty().get());
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
			transitionFx.setIdentefier(identefierField.getText());
			transitionFx.setLocator(locatorField.getText());
			transitionFx.setType(typeComboBox.getSelectionModel()
					.getSelectedItem());
			transitionFx.setDestination(poComboBox.getSelectionModel()
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

	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		poComboBox.setItems(mainApp.getPoModelFx().getPoGenericsFx());
	}
}
