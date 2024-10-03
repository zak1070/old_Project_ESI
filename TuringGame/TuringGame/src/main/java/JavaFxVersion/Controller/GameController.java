package JavaFxVersion.Controller;

import JavaFxVersion.View.CustomAlert;
import JavaFxVersion.View.EndView;
import JavaFxVersion.View.GameView;
import JavaFxVersion.View.ProblemChoiceView;
import Model.GameFacade;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;

/**
 * The GameController class manages the overall flow and interaction of the game in a JavaFX application.
 * It coordinates between the game view, the game facade, and event handling.
 */
public class GameController {
    private  GameFacade gameFacade;
    private GameView gameView;
    private final EndView endView;
    private EventHandlerManager eventHandlerManager;
   // private Stage primaryStage;

    /**
     * Constructor for GameController.
     * Initializes the game controller with a primary stage.
     *
     * @param primaryStage The primary stage of the JavaFX application.
     */
    public GameController(Stage primaryStage) {
        this.gameFacade = new GameFacade();
        this.gameView = new GameView(gameFacade.getGame());
        this.endView = new EndView(gameFacade.getGame());
        this.eventHandlerManager = new EventHandlerManager(gameFacade, gameView, this);
 //       this.primaryStage = primaryStage;

        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.getIcons().add(new Image(Objects.requireNonNull
                (getClass().getResourceAsStream("/pictures/turingLogo.png"))));
        primaryStage.setTitle("TURING GAME");

    }


    /**
     * Initiates and runs the game. This includes showing the welcome message,
     * the problem selection screen, and setting up event handlers.
     *
     * @param primaryStage The primary stage of the JavaFX application.
     */
    public void run(Stage primaryStage) {
        showWelcomeMessage();
        showProblemSelectionScreen(primaryStage);
        eventHandlerManager.setupEventHandlers(gameFacade, gameView);

    }

    private void showWelcomeMessage() {
        displayAlert(Alert.AlertType.INFORMATION, "Bienvenue", "Bienvenue dans le Turing Game", "Cliquez sur OK pour choisir un problème.");
    }

    private void showProblemSelectionScreen(Stage primaryStage) {
        // Création d'une ChoiceBox pour la liste des problèmes
        ProblemChoiceView problemPane = new ProblemChoiceView();

        problemPane.getSelectButton().setOnAction(e -> {
            String selectedProblem = problemPane.getProblemsChoiceBox().getValue();
            if (selectedProblem != null) {
                if (selectedProblem.startsWith("0")) {
                    selectedProblem = "0";
                } else {
                    // Extrait le numéro à partir du format " -1, Difficulty : 1, Luck: 3".
                    selectedProblem = selectedProblem.split("-")[0].trim();
                }
                eventHandlerManager.handleProblemSelection(primaryStage, selectedProblem);
            }
        });

        // Configuration de la scène principale
        primaryStage.setScene(new Scene(problemPane, 1000, 600));
        primaryStage.setTitle("Choix du Problème");
        primaryStage.show();
    }

    /**
     * Ends the game and exits the application platform.
     */
    public void endGame() {
        // Assuming endView.getMsg() returns an appropriate message
       displayAlert(Alert.AlertType.INFORMATION, "Turing Game", "The game is finished", endView.getMsg());
       Platform.exit();
      /*  CustomAlert restartAlert = new CustomAlert(Alert.AlertType.INFORMATION, "Restart a game?", "Restart a game", "Do you want restart a game");

        restartAlert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
        ButtonType result = restartAlert.showAndWait().orElse(ButtonType.CANCEL);

        if (result == ButtonType.YES) {
            this.gameFacade=new GameFacade();
            this.gameView = new GameView(gameFacade.getGame());

            this.eventHandlerManager = new EventHandlerManager(gameFacade, gameView, this);
            run(primaryStage);
        } else {
            Platform.exit();
        }*/
    }

    /**
     * Handles the action when the player decides to give up the game.
     */
    public void giveUp() {
        // Assuming endView.getMsg() returns an appropriate message
        displayAlert(Alert.AlertType.INFORMATION, "Turing Game", "The game is finished", "you're a looser...");
        Platform.exit();
    }

    /**
     * Displays a custom alert dialog with specified parameters.
     *
     * @param alertType The type of the alert.
     * @param title     The title of the alert.
     * @param header    The header text of the alert.
     * @param content   The content text of the alert.
     */
    public void displayAlert(Alert.AlertType alertType, String title, String header, String content) {
        CustomAlert alert = new CustomAlert(alertType, title, header, content);
        alert.showAndWait();
    }

    /**
     * Sets up the game scene for a specific problem number.
     * Configures the scene and updates the stage with game view components.
     *
     * @param primaryStage  The primary stage of the JavaFX application.
     * @param problemNumber The number of the problem to set up.
     */
    public void setupGameScene(Stage primaryStage, int problemNumber) {
        if (primaryStage.isShowing()) {
            primaryStage.close();
        }

        // Configurer et afficher la nouvelle scène
        Scene gameScene = new Scene(gameView, 1000, 800);
        primaryStage.setScene(gameScene);

        gameScene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.F) {
                primaryStage.setFullScreen(!primaryStage.isFullScreen());
            }
        });

        primaryStage.setTitle("Turing Game - Problème " + problemNumber);
        primaryStage.setFullScreen(true);
        eventHandlerManager.setupValidatorHandlers(this.gameView.getValidatorsView().getButtons());
        eventHandlerManager.setupTfdCodeHandler();
        // Afficher le primaryStage avec la nouvelle scène
        primaryStage.show();
    }

}
