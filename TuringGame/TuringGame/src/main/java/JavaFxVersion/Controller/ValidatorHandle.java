package JavaFxVersion.Controller;
import Model.GameFacade;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.util.List;

/**
 * The ValidatorHandle class implements EventHandler to manage actions on validator buttons in a JavaFX application.
 * It processes button clicks to select validators in the game.
 */
public class ValidatorHandle implements EventHandler<ActionEvent> {
    private GameFacade gameFacade;
    private List<Button> buttons;


    /**
     * Constructor for ValidatorHandle.
     * Initializes the handler with the game facade and a list of validator buttons.
     *
     * @param gameFacade The facade of the game logic.
     * @param buttons A list of Buttons representing the validator buttons in the game.
     */
    public ValidatorHandle(GameFacade gameFacade, List<Button> buttons) {
        this.gameFacade = gameFacade;
        this.buttons = buttons;
    }


    /**
     * Handles ActionEvents triggered by validator buttons.
     * Identifies the clicked button and triggers the corresponding action in the game logic.
     *
     * @param actionEvent The action event to be handled.
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        if (source instanceof Button) {
            Button clickedButton = (Button) source;
            int index = buttons.indexOf(clickedButton);
            if (index != -1) {
                gameFacade.selectValidator(index);

            }
        }
    }
}
