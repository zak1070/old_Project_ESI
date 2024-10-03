package JavaFxVersion.View;

import Util.Observable;
import Util.Observer;
import Model.GameState;
import Model.Validator.Validator;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * ValidatorsView extends FlowPane and implements Observer to display validator buttons.
 * Each button represents a validator in the game, with different styles indicating their states.
 */
public class ValidatorsView extends FlowPane implements Observer {
    private final List<Button> buttons;

    /**
     * Constructs a ValidatorsView and subscribes it to the provided Observable.
     * Sets up the layout and spacing of the FlowPane.
     *
     * @param observable The observable object to observe, typically the game state.
     */
    public ValidatorsView(Observable observable) {
        this.buttons = new ArrayList<>();
        observable.addObserver(this);
        setHgap(20);
        setVgap(20);
        setAlignment(Pos.CENTER);
        setPadding(new Insets(10));

    }

    /**
     * Updates the view based on changes in the observed GameState.
     * Creates validator buttons and updates their styles based on their states.
     *
     * @param observable The observable object, expected to be an instance of GameState.
     * @param obj        The object containing the updated state, expected to be an instance of GameState.
     */
    @Override
    public void update(Observable observable, Object obj) {
        if (obj instanceof GameState gameState) {
            addValidatorsVBox(gameState.validatorOfProblem());

            List<Validator> validatorList = gameState.validatorOfProblem();

            for (int i = 0; i < validatorList.size(); i++) {
                Validator validator = validatorList.get(i);
                Button button = buttons.get(i);

                if (isValidatorFalse(validator, gameState)) {
                    setButtonStyle(button, "-fx-background-color: red;");
                    button.setDisable(true);

                } else if (isValidatorTrue(validator, gameState)) {
                    setButtonStyle(button, "-fx-background-color: green;");
                    button.setDisable(true);

                } else {
                    setButtonStyle(button, "-fx-background-color: white;");
                    button.setDisable(false);
                }

            }
        }
    }


    /**
     * Sets the style for a validator button with additional customizations.
     *
     * @param button          The button to style.
     * @param additionalStyle Additional CSS styles to apply to the button.
     */
    private void setButtonStyle(Button button, String additionalStyle) {
        button.setStyle("-fx-background-radius: 40px; " + // Bords arrondis
                "-fx-effect: dropshadow(three-pass-box, rgba(0, 0, 0, 1), 2, 2, 1, 0);" +
                "-fx-text-fill: black; " +
                "-fx-font-family: 'Comic Sans MS'; " +
                "-fx-font-size: 14px; " +
                additionalStyle);
    }

    /**
     * Checks if the given validator is in the list of validators with a successful validation.
     *
     * @param validator The validator to check.
     * @param gameState The current game state.
     * @return true if the validator is in the list of successful validators, false otherwise.
     */
    private boolean isValidatorTrue(Validator validator, GameState gameState) {
        return gameState.validatorOK().contains(validator);

    }

    /**
     * Checks if the given validator is in the list of validators with an unsuccessful validation.
     *
     * @param validator The validator to check.
     * @param gameState The current game state.
     * @return true if the validator is in the list of unsuccessful validators, false otherwise.
     */
    private boolean isValidatorFalse(Validator validator, GameState gameState) {
        return gameState.validatorFalse().contains(validator);
    }

    /**
     * Adds a VBox containing the validator images and buttons for each validator in the list.
     *
     * @param validators The list of validators to display.
     */
    private void addValidatorsVBox(List<Validator> validators) {
        if (buttons.size() < validators.size()) {
            for (int i = 0; i < validators.size(); i++) {
                VBox vbox = new VBox(10);
                vbox.setAlignment(Pos.CENTER);

                // Configuration de l'image du robot
                Image imageRobot = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/robots/robot" + (char) ('A' + i) + ".png")));
                ImageView imageViewRobot = new ImageView(imageRobot);
                configureImageViewRobot(imageViewRobot);

                // Création du bouton
                Button button = new Button(String.valueOf((char) ('A' + i)));
                buttons.add(button);

                // Configuration de l'image de la carte
                Image imageCard = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Card/card" + validators.get(i).getNbValidator() + ".png")));
                ImageView imageViewCard = new ImageView(imageCard);
                configureImageViewCard(imageViewCard);

                // Ajout des éléments à vbox
                vbox.getChildren().addAll(imageViewRobot, button, imageViewCard);

                // Ajout de vbox au parent
                getChildren().add(vbox);
            }
        }
    }

    /**
     * Configures the style and properties of the ImageView for a robot image.
     *
     * @param imageView The ImageView to configure.
     */
    private void configureImageViewRobot(ImageView imageView) {
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        imageView.setPreserveRatio(true);
        imageView.setStyle("-fx-effect: dropshadow(gaussian, black, 10, 0.5, 2, 2); ");
    }


    /**
     * Configures the style and properties of the ImageView for a validator card image.
     *
     * @param imageView The ImageView to configure.
     */
    private void configureImageViewCard(ImageView imageView) {
        imageView.setFitWidth(300);
        imageView.setFitHeight(200);
        imageView.setPreserveRatio(true);
        imageView.setStyle("-fx-effect: dropshadow(gaussian, black, 10, 0.5, 2, 2); " +
                "-fx-background-color: rgba(0, 0, 0, 0);"
        );
    }


    /**
     * Gets the list of validator buttons.
     *
     * @return A list of Buttons representing validators.
     */
    public List<Button> getButtons() {
        return buttons;
    }
}
