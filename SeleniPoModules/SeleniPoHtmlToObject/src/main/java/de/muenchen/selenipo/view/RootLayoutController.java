package de.muenchen.selenipo.view;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import org.apache.log4j.Logger;

import de.muenchen.selenipo.MainApp;
import de.muenchen.selenipo.impl.fxModel.PoModelFx;
import de.muenchen.selenipo.impl.persistanceModel.PoModelImpl;

public class RootLayoutController {
	private static final Logger logger = Logger
			.getLogger(RootLayoutController.class);

	private MainApp mainApp;

	@FXML
	public void handleSave() {
		logger.debug("Save..");
		FileChooser fileChooser = new FileChooser();

		// Set extension filter
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
				"XML files (*.xml)", "*.xml");
		fileChooser.getExtensionFilters().add(extFilter);

		// Show save file dialog
		File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());

		if (file != null) {
			// Make sure it has the correct extension
			if (!file.getPath().endsWith(".xml")) {
				file = new File(file.getPath() + ".xml");
			}
			PoModelImpl poModelImpl = mainApp.getConverterService()
					.convertToImpl(mainApp.getPoModelFx());
			mainApp.getConverterService().persistToXml(file, poModelImpl);
		}
	}

	@FXML
	public void handleLoad() {
		logger.debug("Load..");
		FileChooser fileChooser = new FileChooser();

		// Set extension filter
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
				"XML files (*.xml)", "*.xml");
		fileChooser.getExtensionFilters().add(extFilter);

		// Show save file dialog
		File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

		if (file != null) {
			PoModelImpl loadFromXml = (PoModelImpl) mainApp
					.getConverterService().loadFromXml(file);
			PoModelFx fxModel = mainApp.getConverterService().convertToFxModel(
					loadFromXml);
			mainApp.setPoModelFx(fxModel);
			mainApp.showPoOverview();
		}
	}

	@FXML
	public void handleGenerate() {
		logger.debug("generate..");
		DirectoryChooser dirChooser = new DirectoryChooser();
		File dir = dirChooser.showDialog(mainApp.getPrimaryStage());
		if (dir != null) {
			try {
				mainApp.getGeneratorService().generatePageObjects(
						mainApp.getPoModelFx(), dir.getAbsolutePath());
			} catch (IOException e) {
				// Show the error message.
				Alert alert = new Alert(AlertType.ERROR);
				alert.initOwner(mainApp.getPrimaryStage());
				alert.setTitle("Fehler beim Generieren");
				alert.setHeaderText("Stacktrace:");
				alert.setContentText(e.toString());

				alert.showAndWait();

			}
		}
	}

	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;

	}
}
