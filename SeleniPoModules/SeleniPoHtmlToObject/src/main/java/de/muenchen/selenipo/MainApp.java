package de.muenchen.selenipo;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import org.apache.log4j.Logger;

import de.muenchen.selenipo.model.ElementFx;
import de.muenchen.selenipo.model.PoGenericFx;
import de.muenchen.selenipo.model.PoModelFx;
import de.muenchen.selenipo.model.TransitionFx;
import de.muenchen.selenipo.view.ElementEditDialogController;
import de.muenchen.selenipo.view.PoOverviewController;
import de.muenchen.selenipo.view.TransitionEditDialogController;

public class MainApp extends Application {

	private static final Logger logger = Logger.getLogger(MainApp.class);

	private Stage primaryStage;
	private BorderPane rootLayout;

	private PoModelFx poModelFx;

	public PoModelFx getPoModelFx() {
		return poModelFx;
	}

	/**
	 * Constructor
	 */
	public MainApp() {
		this.poModelFx = new PoModelFx();
		PoGenericFx welcomePo = new PoGenericFx("WelcomePo");
		PoGenericFx listPo = new PoGenericFx("listPo");
		TransitionFx bEnter = new TransitionFx("bEnter", Selector.LINK,
				"bEnter", listPo);
		ElementFx h1 = new ElementFx("eH1", Selector.XPATH, "//h1");
		TransitionFx index = new TransitionFx("tIndex", Selector.LINK,
				"ToDo-App", welcomePo);
		welcomePo.getTransitionsFx().add(bEnter);
		welcomePo.getElementsFx().add(h1);
		listPo.getTransitionsFx().add(index);
		getPoModelFx().getPoGenericsFx().add(welcomePo);
		getPoModelFx().getPoGenericsFx().add(listPo);
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
			dialogStage.setTitle("Edit Person");
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
			dialogStage.setTitle("Edit Person");
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
