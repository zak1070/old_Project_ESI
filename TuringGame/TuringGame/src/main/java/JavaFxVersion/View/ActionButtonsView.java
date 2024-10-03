package JavaFxVersion.View;

import JavaFxVersion.View.Util.Style;
import Model.GameState;
import Util.Observable;
import Util.Observer;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;

/**
 * The ActionButtonsView class extends HBox and implements Observer to display action buttons
 * and labels in a JavaFX application. It observes changes in the game state and updates its display accordingly.
 */
public class ActionButtonsView extends HBox implements Observer {

    Button nextRoundButton;
    Button undoButton;
    Button redoButton;

    Label roundLbl;
    Label validatorLbl;
   // Label validatorsTrueLbl;

    /**
     * Constructor for ActionButtonsView.
     * Initializes the buttons and labels and adds them to the HBox.
     *
     * @param observable The game state observable that this view will observe.
     */
    public ActionButtonsView(Observable observable) {
        observable.addObserver(this);
        setSpacing(5); // Espace entre les boutons
        // Initialisation et configuration des boutons et labels
        initButtons();
        initLabels();

        // Ajouter les boutons et labels au HBox
        getChildren().addAll(nextRoundButton, undoButton, redoButton, roundLbl, validatorLbl/*,validatorsTrueLbl*/);
    }


    /**
     * Initializes the action buttons (Next Round, Undo, Redo) and configures their styles and events.
     */
    private void initButtons() {
        nextRoundButton = new Button(" Next Round");
        undoButton = new Button(" UNDO");
        redoButton = new Button(" REDO");

        // Appliquer le style et les événements pour chaque bouton
        configureButton(nextRoundButton);
        configureButton(undoButton);
        configureButton(redoButton);
    }

    /**
     * Initializes the labels for displaying the current round and number of validators tested.
     */
    private void initLabels() {
        roundLbl = new Label(" Round: ");
        validatorLbl = new Label(" Validator tested: ");
      //  validatorsTrueLbl = new Label("Validators true: ");
        // Appliquer le style pour chaque label
        Style.setLabelStyle(roundLbl);
        Style.setLabelStyle(validatorLbl);
      //  Style.setLabelStyle(validatorsTrueLbl);
    }

    /**
     * Configures a given button with standard styles and mouse event handlers.
     *
     * @param button The button to be configured.
     */
    private void configureButton(Button button) {
        Style.setBtnStyle(button);
        button.setOnMousePressed(event -> Style.setPressedBtnStyle(button));
        button.setOnMouseReleased(event -> Style.setBtnStyle(button));
    }

    /**
     * Gets the 'Next Round' button.
     *
     * @return The 'Next Round' button.
     */
    public Button getNextRoundButton() {
        return nextRoundButton;
    }


    /**
     * Gets the 'Undo' button.
     *
     * @return The 'Undo' button.
     */
    public Button getUndoButton() {
        return undoButton;
    }

    /**
     * Gets the 'Redo' button.
     *
     * @return The 'Redo' button.
     */
    public Button getRedoButton() {
        return redoButton;
    }


    /**
     * Updates the view based on changes in the observed GameState.
     *
     * @param observable The observable object.
     * @param obj        The updated object, expected to be an instance of GameState.
     */
    @Override
    public void update(Observable observable, Object obj) {
        if (obj instanceof GameState) {
            GameState gameState = (GameState) obj;
            this.roundLbl.setText("Round : " + gameState.round());
            this.validatorLbl.setText("Validator tested : " + gameState.nbValidatorsTested());
          //  this.validatorsTrueLbl.setText("Validators True : "+ gameState.nbValidatorTrue());
        }
    }
}


