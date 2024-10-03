package JavaFxVersion.View;

import Model.GameState;
import Util.Observable;
import Util.Observer;
import javafx.scene.control.TextArea;

/**
 * MessageAreaView extends TextArea and implements Observer to display messages
 * related to the game state.
 */
public class MessageAreaView extends TextArea implements Observer {

    /**
     * Constructs a MessageAreaView and subscribes it to the provided Observable.
     * Configures the appearance and behavior of the TextArea.
     *
     * @param observable The Observable object to observe, typically the game state.
     */
    public MessageAreaView(Observable observable) {
        observable.addObserver(this);

        // Paramètres pour TextArea
        this.setEditable(false);
        this.setStyle(
                "-fx-font-size: 14px;" +
                        "-fx-font-family: 'Comic Sans MS'; " +
                        "-fx-border-radius: 5px;" +
                        "-fx-text-fill: grey;" +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,10), 5, 0, 0, 5);" +
                        "-fx-padding: 10px"
        );
        this.setWrapText(true);
        this.setMaxHeight(Double.MAX_VALUE);
        this.setMaxWidth(400);
    }

    /**
     * Updates the message area based on changes in the observed GameState.
     * Displays previous codes from the GameState.
     *
     * @param observable The observable object, expected to be an instance of GameState.
     * @param obj The object containing the updated state, expected to be an instance of GameState.
     */
    @Override
    public void update(Observable observable, Object obj) {
        if (obj instanceof GameState) {
            GameState state = (GameState) obj;
            this.setText(state.previousCodes());
        }
    }
}
