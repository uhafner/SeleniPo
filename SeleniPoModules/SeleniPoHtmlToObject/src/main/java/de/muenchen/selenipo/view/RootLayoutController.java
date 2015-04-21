package de.muenchen.selenipo.view;

import javafx.fxml.FXML;

import org.apache.log4j.Logger;

import de.muenchen.selenipo.MainApp;
import de.muenchen.selenipo.PoModel;
import de.muenchen.selenipo.model.PoModelFx;

public class RootLayoutController {
	private static final Logger logger = Logger
			.getLogger(RootLayoutController.class);

	private MainApp mainApp;

	@FXML
	public void handleSave() {
		logger.debug("Save..");

		PoModelFx poModelFx = new PoModelFx();
		mainApp.getPersistService().persistToXml("POSAVE.xml",
				(PoModel) poModelFx);
	}

	@FXML
	public void handleLoad() {
		logger.debug("Load..");
		Object loadFromXml = mainApp.getPersistService().loadFromXml(
				"POSAVE.xml");
		System.out.println(loadFromXml);
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
