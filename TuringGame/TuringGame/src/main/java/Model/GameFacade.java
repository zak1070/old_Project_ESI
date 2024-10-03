package Model;

/**
 * The GameFacade class provides a simplified interface to the underlying Game class,
 * making it easier to interact with the game's functionality.
 * It acts as a facade by hiding the complexities of the Game class and offering a more straightforward set of methods.
 */
public class GameFacade {
    private Game game;

    /**
     * Constructor for GameFacade.
     * Initializes a new Game instance.
     */
    public GameFacade() {
        this.game = new Game();
    }

    /**
     * Starts a new game with the specified problem number.
     *
     * @param problemNumber The number of the problem to start the game with.
     */
    public void startNewGame(int problemNumber) {
        game.startGame(problemNumber);
    }

    /**
     * Enters a user code into the current game.
     *
     * @param code The code entered by the user.
     */
    public void enterUserCode(int code) {
        game.enterCode(code);
    }

    /**
     * Selects a validator based on the given index in the current game.
     *
     * @param validatorIndex The index of the validator to be selected.
     */
    public void selectValidator(int validatorIndex) {
        game.chooseValidator(validatorIndex);
    }

    /**
     * Advances to the next round in the current game.
     */
    public void nextRound() {
        game.nextRound();
    }

    /**
     * Makes a guess of the secret code in the current game.
     *
     * @param code The guessed code.
     */
    public void guessCode(int code) {
        game.guessCode(code);
    }

    /**
     * Checks if the current game is over.
     *
     * @return true if the game is over, false otherwise.
     */
    public boolean isGameOver() {
        return game.isGameOver();
    }

    /**
     * Undoes the last action in the current game.
     */
    public void undo() {
        game.undo();
    }

    /**
     * Redoes the last undone action in the current game.
     */
    public void redo() {
        game.redo();
    }

    /**
     * Ends the current game.
     */
    public void exitGame() {
        game.giveUp();
    }

    /**
     * Retrieves the underlying Game instance.
     *
     * @return The Game instance being managed by this facade.
     */
    public Game getGame() {
        return this.game;
    }
}
