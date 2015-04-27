package de.muenchen.selenipo.view;

import java.io.File;

import javafx.fxml.FXML;
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
			System.out.println(fxModel.getPoGenerics().size());
			mainApp.showPoOverview();
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
