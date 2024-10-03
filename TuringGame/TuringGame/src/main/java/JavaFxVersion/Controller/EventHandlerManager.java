package JavaFxVersion.Controller;

import JavaFxVersion.View.CustomAlert;
import JavaFxVersion.View.GameView;
import Model.GameFacade;
import Model.TuringException;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.util.List;

/**
 * The EventHandlerManager class manages the setup of event handlers for the game's GUI components.
 * It is responsible for linking user actions to the underlying game logic.
 */
public class EventHandlerManager {
    private final GameFacade gameFacade;
    private final GameView gameView;
    private final GameController gameController;

    /**
     * Constructor for EventHandlerManager.
     * Initializes the manager with the game facade, game view, and game controller.
     *
     * @param gameFacade     The GameFacade instance used for game logic operations.
     * @param gameView       The GameView instance used for the game's user interface.
     * @param gameController The GameController instance used for controlling the game flow.
     */
    public EventHandlerManager(GameFacade gameFacade, GameView gameView, GameController gameController) {
        this.gameFacade = gameFacade;
        this.gameView = gameView;
        this.gameController = gameController;
    }

    /**
     * Sets up event handlers for all interactive components of the game's GUI.
     * This includes code input handlers and action button handlers.
     */
    public void setupEventHandlers(GameFacade facade,GameView view) {
        setupCodeInputHandlers(facade,view);
        setupActionButtonsHandlers(facade,view);
    }

    private void setupCodeInputHandlers(GameFacade gameFacade,GameView gameView) {
        CodeInputHandler code_handler = new CodeInputHandler(
                gameFacade,
                gameView.getCodeInputView().getNumberButtons(),
                gameView.getCodeInputView().getSelectedValues(),
                gameView.getCodeInputView().getEnterButton()
        );

        setupNumberButtonHandlers(code_handler);
        setupEnterButtonHandler(code_handler);
    }

    private void setupNumberButtonHandlers(CodeInputHandler code_handler) {
        Button[][] numberBtn = gameView.getCodeInputView().getNumberButtons();
        for (Button[] buttons : numberBtn) {
            for (int col = 0; col < numberBtn[0].length; col++) {
                Button numberButton = buttons[col];
                numberButton.setOnAction(event -> code_handler.handle(event));
            }
        }
    }

    private void setupEnterButtonHandler(CodeInputHandler code_handler) {
        Button enter_button = gameView.getCodeInputView().getEnterButton();
        enter_button.setOnAction(event -> {
            try {
                code_handler.handle(event);
            } catch (TuringException te) {
                gameController.displayAlert(Alert.AlertType.ERROR, "Turing Game", "Error", te.getMessage());
            }
        });
    }

    /**
     * Sets up event handlers for validator buttons.
     * Each validator button is linked to its corresponding action in the game logic.
     *
     * @param validators A list of validator buttons to set up handlers for.
     */
    public void setupValidatorHandlers(List<Button> validators) {
        ValidatorHandle validatorHandle = new ValidatorHandle(gameFacade, validators);

        for (Button validator : validators) {
            validator.setOnAction(event -> {
                try {
                    validatorHandle.handle(event);
                } catch (TuringException te) {
                    gameController.displayAlert(Alert.AlertType.ERROR, "Turing Game", "Error", te.getMessage());
                }
            });
        }
    }

    /**
     * Configures the behavior for the code input text field to handle the "Enter" key press.
     * When the "Enter" key is pressed, this method attempts to parse the text from the text field
     * as an integer and use it as a code guess in the game. It handles both valid and invalid inputs
     * by calling the appropriate methods in the game facade and displaying alerts for errors.
     */
    public void setupTfdCodeHandler() {
        // Gets the text field for code input from the game view
        TextField codeTextField = gameView.getCodeTfd();

        // Sets an event handler for key presses on the text field
        codeTextField.setOnKeyPressed(event -> {
            // Checks if the pressed key is the "Enter" key
            if (event.getCode() == KeyCode.ENTER) {
                // Retrieves the text from the text field
                String codeText = codeTextField.getText();

                // Checks if the text field is not empty
                if (!codeText.isEmpty()) {
                    try {
                        // Parses the text to an integer and uses it as a code guess
                        int intCode = Integer.parseInt(codeText);
                        gameFacade.guessCode(intCode); // Guess the code
                        gameController.endGame(); // Ends the game after guessing the code
                    } catch (TuringException te) {
                        // Displays an alert if a TuringException is caught
                        gameController.displayAlert(Alert.AlertType.ERROR, "Turing Game", "Error", te.getMessage());
                        codeTextField.requestFocus(); // Refocuses on the text field
                    }
                } else {
                    // Displays an alert if no code is entered
                    gameController.displayAlert(Alert.AlertType.ERROR, "Turing Game error", "No code entered", "Please enter a code");
                    codeTextField.requestFocus(); // Refocuses on the text field
                }
            }
        });
    }


    private void setupActionButtonsHandlers(GameFacade gameFacade,GameView gameView) {
        Button guessCodeButton = gameView.getGuessCodeButton();
        Button nextRoundButton = gameView.getActionButtonsView().getNextRoundButton();
        Button undoButton = gameView.getActionButtonsView().getUndoButton();
        Button redoButton = gameView.getActionButtonsView().getRedoButton();
        MenuItem giveUpItem = gameView.getGiveUPItm();

        guessCodeButton.setOnAction(event -> {
            String code = gameView.getCodeTfd().getText();
            if (!code.isEmpty()) {
                try {
                    int intCode = Integer.parseInt(code);
                    gameFacade.guessCode(intCode);
                    gameController.endGame();
                } catch (TuringException te) {
                    gameController.displayAlert(Alert.AlertType.ERROR, "Turing Game", "Error", te.getMessage());
                }
            } else {
                gameController.displayAlert(Alert.AlertType.ERROR, "Turing Game error", "No code entered", "Please enter a code");
            }

        });
        nextRoundButton.setOnAction(event -> {
            try {
                gameFacade.nextRound();
            } catch (TuringException te) {
                gameController.displayAlert(Alert.AlertType.ERROR, "Turing Game", "Error", te.getMessage());
            }
        });
        undoButton.setOnAction(event -> {
            try {
                gameFacade.undo();
            } catch (TuringException te) {
                gameController.displayAlert(Alert.AlertType.ERROR, "Turing Game", "Error", te.getMessage());
            }
        });
        redoButton.setOnAction(event -> {
            try {
                gameFacade.redo();
            } catch (TuringException te) {
                gameController.displayAlert(Alert.AlertType.ERROR, "Turing Game", "Error", te.getMessage());
            }
        });

        giveUpItem.setOnAction(event -> gameController.giveUp());
    }


    /**
     * Handles the selection of a problem from the game's problem selection interface.
     * Validates the input and initiates the game with the selected problem.
     *
     * @param primaryStage The primary stage of the JavaFX application.
     * @param input        The user input representing the problem number.
     */
    public void handleProblemSelection(Stage primaryStage, String input) {
        try {
            int problemNumber = Integer.parseInt(input);
            validateProblemNumber(problemNumber);
            gameFacade.startNewGame(problemNumber);
            gameController.setupGameScene(primaryStage, problemNumber);
        } catch (NumberFormatException e) {
            CustomAlert alert = new CustomAlert(Alert.AlertType.ERROR, "Turing Game Error", "Erreur", "Entrée non valide. Veuillez entrer un nombre.");
            alert.showAndWait();
        } catch (TuringException e) {
            CustomAlert alert = new CustomAlert(Alert.AlertType.ERROR, "Turing Game Error", "Erreur", e.getMessage());
            alert.showAndWait();
        }
    }

    private void validateProblemNumber(int number) {
        if (number < 0 || number > 16) {
            throw new IllegalArgumentException("Numéro de problème invalide. Veuillez entrer un nombre entre 1 et 16 ou 0 pour aléatoire.");
        }
    }

}
