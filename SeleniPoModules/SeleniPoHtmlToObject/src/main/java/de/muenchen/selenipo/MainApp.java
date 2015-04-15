package de.muenchen.selenipo;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import de.muenchen.selenipo.model.ElementFx;
import de.muenchen.selenipo.model.PoGenericFx;
import de.muenchen.selenipo.model.PoModelFx;
import de.muenchen.selenipo.model.TransitionFx;
import de.muenchen.selenipo.view.PoOverviewController;

public class MainApp extends Application {

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
