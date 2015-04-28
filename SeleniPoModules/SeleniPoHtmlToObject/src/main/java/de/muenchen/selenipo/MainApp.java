package de.muenchen.selenipo;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import de.muenchen.selenipo.config.ConverterConfig;
import de.muenchen.selenipo.config.GeneratorConfig;
import de.muenchen.selenipo.impl.fxModel.ElementFx;
import de.muenchen.selenipo.impl.fxModel.PoGenericFx;
import de.muenchen.selenipo.impl.fxModel.PoModelFx;
import de.muenchen.selenipo.impl.fxModel.TransitionFx;
import de.muenchen.selenipo.view.ElementEditDialogController;
import de.muenchen.selenipo.view.PoEditDialogController;
import de.muenchen.selenipo.view.PoOverviewController;
import de.muenchen.selenipo.view.RootLayoutController;
import de.muenchen.selenipo.view.TransitionEditDialogController;

public class MainApp extends Application {

	private static final Logger logger = Logger.getLogger(MainApp.class);

	private Stage primaryStage;
	private BorderPane rootLayout;

	private WebDriver webDriver;
	private PoModelFx poModelFx;
	private ConverterService converterService;
	private GeneratorService generatorService;
	
	final ObservableList<Integer> highlightRows = FXCollections
			.observableArrayList();

	public PoModelFx getPoModelFx() {
		return poModelFx;
	}

	public void setPoModelFx(PoModelFx poModelFx) {
		this.poModelFx = poModelFx;
	}

	/**
	 * Constructor
	 */
	public MainApp() {
		poModelFx = new PoModelFx();
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				ConverterConfig.class, GeneratorConfig.class);
		converterService = context.getBean(ConverterService.class);
		generatorService = context.getBean(GeneratorService.class);
		webDriver = null;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("PageObject Generator");

		initRootLayout();
		showPoOverview();

	}

	/**
	 * Initializes the root layout.
	 */
	public void initRootLayout() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class
					.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			// Give the controller access to the main app.
			RootLayoutController controller = loader.getController();
			controller.setMainApp(this);
			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Shows the person overview inside the root layout.
	 */
	public void showPoOverview() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class
					.getResource("view/PoOverview.fxml"));
			AnchorPane personOverview = (AnchorPane) loader.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(personOverview);

			// Give the controller access to the main app.
			PoOverviewController controller = loader.getController();
			controller.setMainApp(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Opens a dialog to edit a selected Element. If the user clicks OK, the
	 * changes are saved into the provided person object and true is returned.
	 * 
	 * @param element
	 *            the elemnet object to be edited
	 * @return true if the user clicked OK, false otherwise.
	 */
	public boolean showElementEditDialog(ElementFx element) {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class
					.getResource("view/ElementEditDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Element");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the person into the controller.
			ElementEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setElement(element);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Opens a dialog to edit a selected Transition. If the user clicks OK, the
	 * changes are saved into the provided person object and true is returned.
	 * 
	 * @param transition
	 *            the transition object to be edited
	 * @return true if the user clicked OK, false otherwise.
	 */
	public boolean showTransitionEditDialog(TransitionFx transition) {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class
					.getResource("view/TransitionEditDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Transition");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the person into the controller.
			TransitionEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setTransition(transition);
			controller.setMainApp(this);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Opens a dialog to edit a selected Po. If the user clicks OK, the changes
	 * are saved into the provided person object and true is returned.
	 * 
	 * @param PoGenericFx
	 *            The Po object
	 * @return true if the user clicked OK, false otherwise.
	 */
	public boolean showPoEditDialog(PoGenericFx poGenericFx) {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class
					.getResource("view/PoEditDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit pageObject");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the person into the controller.
			PoEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setPo(poGenericFx);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public ConverterService getConverterService() {
		return converterService;
	}

	public void setConverterService(ConverterService converterService) {
		this.converterService = converterService;
	}

	public GeneratorService getGeneratorService() {
		return generatorService;
	}

	public void setGeneratorService(GeneratorService generatorService) {
		this.generatorService = generatorService;
	}

	public WebDriver getWebDriver() {
		return webDriver;
	}

	public void setWebDriver(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	/**
	 * Returns the main stage.
	 * 
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}

}
