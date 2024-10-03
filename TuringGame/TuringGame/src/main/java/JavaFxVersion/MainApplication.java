package JavaFxVersion;

import JavaFxVersion.Controller.GameController;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * MainApplication is the entry point for the JavaFX application.
 * It extends the Application class and sets up the primary stage of the application.
 */
public class MainApplication extends Application {

    /**
     * Starts the application by setting up the primary stage and initializing the GameController.
     *
     * @param primaryStage The primary stage for this application, onto which
     *                     the application scene can be set.
     */
    @Override
    public void start(Stage primaryStage) {
        GameController controller = new GameController(primaryStage);
        controller.run(primaryStage);
    }

    /**
     * The main method to launch the JavaFX application.
     *
     * @param args Command line arguments passed to the application.
     *             Not used in this implementation.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
