package team1.searchengine.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import team1.searchengine.app.controller.HomeController;
import team1.searchengine.configuration.EnvironmentMap;
import team1.searchengine.database.preProcessor.ComitteePopulator;
import team1.searchengine.logging.Logger;

public class AppMain extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;
	
	/**
	 * Start the main app
	 */
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("MSD TEAM1");

		initRootView();
		loadHomeView();
	}

	/**
	 * Load the Root view item
	 */
	private void initRootView() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(AppMain.class.getResource("view/RootView.fxml"));
			rootLayout = loader.load();

			Scene scene = new Scene(rootLayout);
			scene.getStylesheets().add(AppMain.class.getResource("view/app.css").toString());
			
			primaryStage.setScene(scene);
			primaryStage.setMaximized(true);
			primaryStage.getIcons().add(new Image(AppMain.class.getResource("view/icon.png").toString()));
			primaryStage.show();
		} catch (Exception ex) {
			Logger.log("Error loading Root view" + ex.getMessage(), "UI-ERROR");
		}
	}

	/**
	 * load Home View
	 */
	private void loadHomeView() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(AppMain.class.getResource("view/HomeView.fxml"));
			AnchorPane homeView = loader.load();
			rootLayout.setCenter(homeView);

			HomeController homeCtrl = loader.getController();
			homeCtrl.init();
		} catch (Exception ex) {
			Logger.log("Error loading Home view" + ex.getMessage(), "UI-ERROR");
		}

	}	

	/**
	 * Starting point of the application
	 * @param args
	 */
	public static void main(String[] args) {
		ComitteePopulator.populate("committees");
		EnvironmentMap.setEnvVar("prod", "blah");
		launch(args);
	}
	
}
