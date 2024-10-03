package JavaFxVersion.View;

import Model.GameState;
import Util.Observable;
import Util.Observer;
/**
 * EndView implements Observer to display an end-of-game message based on the game state.
 * It observes changes in GameState and updates the message accordingly.
 */
public class EndView implements Observer {
    private String msg;

    /**
     * Constructs an EndView and registers it as an observer to the provided Observable.
     *
     * @param observable The Observable object (typically GameState) to observe.
     */
    public EndView(Observable observable) {
        observable.addObserver(this);
        this.msg = "";
    }


    /**
     * Updates the end game message based on the observed GameState.
     * Sets a winning message if the code is found, or a losing message otherwise.
     *
     * @param observable The observable object, expected to be an instance of GameState.
     * @param obj The object containing the updated state, expected to be an instance of GameState.
     */
    @Override
    public void update(Observable observable, Object obj) {
        if (obj instanceof GameState) {
            GameState gameState = (GameState) obj;
            if (gameState.gameOver()) {
                if (gameState.isCodeFounded()) {
                    msg = "AND THE WINNER ISSSSSSS.......\n" +
                            "YOUUUUUUUUUU\n" +
                            "Round     : " + gameState.round() + "\n" +
                            "Validators: " + gameState.nbValidatorsTested();
                }
                else {
                    msg = "GAME OVER YOU'RE A LOOOOOOOOSER ";
                }
            }
        }
    }

    /**
     * Gets the current end game message.
     *
     * @return The end game message string.
     */
    public String getMsg() {
        return msg;
    }
}