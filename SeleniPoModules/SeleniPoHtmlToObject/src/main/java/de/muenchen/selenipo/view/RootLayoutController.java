package de.muenchen.selenipo.view;

import javafx.fxml.FXML;

import org.apache.log4j.Logger;

import de.muenchen.selenipo.MainApp;
import de.muenchen.selenipo.impl.persistanceModel.PoModelImpl;

public class RootLayoutController {
	private static final Logger logger = Logger
			.getLogger(RootLayoutController.class);

	private MainApp mainApp;

	@FXML
	public void handleSave() {
		logger.debug("Save..");
		PoModelImpl poModelImpl = mainApp.getConverterService().convertToImpl(
				mainApp.getPoModelFx());
		mainApp.getConverterService().persistToXml("POSAVE.xml", poModelImpl);
	}

	@FXML
	public void handleLoad() {
		logger.debug("Load..");
		PoModelImpl loadFromXml = (PoModelImpl) mainApp.getConverterService()
				.loadFromXml("POSAVE.xml");
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
