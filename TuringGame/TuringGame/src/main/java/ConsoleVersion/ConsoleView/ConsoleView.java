package ConsoleVersion.ConsoleView;

import Loader.Loader;
import Model.GameState;
import Model.Validator.Validator;
import Util.Observable;
import Util.Observer;

import java.util.List;

/**
 * The ConsoleView class implements the Observer interface and provides a console-based view for the game.
 * It displays the game state, error messages, and other relevant information to the user.
 */
public class ConsoleView implements Observer {
    private final static String GREEN = "\u001B[32m";
    private final static String RESET = "\u001B[0m";
    private final static String ORANGE = "\u001B[38;5;208m";
    private final static String BLUE = "\u001B[38;5;21m";
    private final static String LIGHT_BLUE = "\u001B[94m"; // Light Blue
    private final static String YELLOW = "\u001B[33m"; // Yellow
    private final static String PURPLE = "\u001B[35m"; // Purple

    /**
     * Constructor for ConsoleView.
     * Registers this view as an observer to the given observable.
     *
     * @param observable The observable object to observe.
     */
    public ConsoleView(Observable observable) {
        observable.addObserver(this);
    }


    /**
     * Updates the view based on changes in the observable.
     * Displays the game state when notified.
     *
     * @param observable The observable object.
     * @param obj        The updated object, expected to be an instance of GameState.
     */
    @Override
    public void update(Observable observable, Object obj) {
        if (obj instanceof GameState) {
            GameState state = (GameState) obj;
            displayState(state);
        }
    }

    /**
     * Displays the current game state in the console.
     *
     * @param state The current game state.
     */
    private void displayState(GameState state) {
        if (!state.gameOver()) {
            System.out.println(stringListValidator(state.validatorOfProblem(), state));
            System.out.println("Round: " + state.round());
            System.out.println("Validators tested ok: " + state.nbValidatorsTested());
            System.out.println("Previous codes:"+"(" +LIGHT_BLUE+"B"+YELLOW+"Y"+PURPLE+"P"+RESET+")");
            System.out.println(state.previousCodes());

            System.out.println("Press help for help :");
        } else displayGameOver(state.isCodeFounded(), state.round(), state.nbValidatorsTested());
    }

    /**
     * Displays a game over message in the console.
     *
     * @param success          Whether the game was won or lost.
     * @param round            The round at which the game ended.
     * @param validatorsTested The number of validators tested.
     */
    public void displayGameOver(Boolean success, int round, int validatorsTested) {
        System.out.print(BLUE);
        System.out.println("Le jeu est terminé !");
        if (success) {
            System.out.println("Vous avez gagné");
            System.out.println("round:" + round);
            System.out.println("validators tested :" + validatorsTested);
            System.out.print(BLUE);
        } else {
            System.out.println("Vous avez perdu");
            System.out.print(BLUE);
        }
    }


    /**
     * Displays an error message in the console.
     *
     * @param message The error message to display.
     */
    public void displayError(String message) {
        // Afficher un message d'erreur
        System.err.print("Error : " + message);
    }


    /**
     * Constructs a string representing a list of validators and their status.
     *
     * @param validators The list of validators.
     * @param gameState  The current game state.
     * @return A string representation of the list of validators.
     */
    private String stringListValidator(List<Validator> validators, GameState gameState) {
        StringBuilder validatorsList = new StringBuilder();
        validatorsList.append("List of Validators:\n");
        for (int i = 0; i < validators.size(); i++) {
            Validator validator = validators.get(i);
            validatorsList.append(i).append(": ")
                    .append((char) ('A' + i))
                    .append("  ")
                    .append(validator.stringCategory())
                    .append(isValidatorTested(validator, gameState) ? " ##TRUE##" : "")
                    .append("\n");
        }
        return validatorsList.toString();
    }


    /**
     * Displays help information for game commands in the console.
     */
    private boolean isValidatorTested(Validator validator, GameState gameState) {
        return gameState.validatorOK().contains(validator);
    }


    /**
     * Displays a list of problems available in the game.
     */
    public void showHelp() {
        System.out.println(GREEN);
        System.out.println("Help for game commands:");
        System.out.println("  - code [1-5][1-5][1-5]: Enter a three-digit code where each digit is between 1 and 5 (inclusive).");
        System.out.println("  - choose validator [n]: Select a validator where [n] is the index of the validator.");
        System.out.println("  - next round: Move to the next round of the game.");
        System.out.println("  - guess code: Check if the entered code is correct.");
        System.out.println("  - help: Display this help message.");
        System.out.println("  - exit: Quit the game.");
        System.out.println("  - undo: Undo the last action performed.");
        System.out.println("  - redo: Redo the action that you just undone.");
        System.out.println(RESET);
    }

    public void displayProblems() {
        System.out.println(GREEN + "List of problems: ");
        System.out.println(Loader.listAllProblems() + RESET);
    }


    /**
     * Displays a welcome message when the game starts.
     */
    public void display_start() {

        System.out.println("\n" + ORANGE +
                "__/\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\__/\\\\\\________/\\\\\\____/\\\\\\\\\\\\\\\\\\______/\\\\\\\\\\\\\\\\\\\\\\__/\\\\\\\\\\_____/\\\\\\_____/\\\\\\\\\\\\\\\\\\\\\\\\_______________/\\\\\\\\\\\\\\\\\\\\\\\\_____/\\\\\\\\\\\\\\\\\\_____/\\\\\\\\____________/\\\\\\\\__/\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\_        \n" +
                " _\\///////\\\\\\/////__\\/\\\\\\_______\\/\\\\\\__/\\\\\\///////\\\\\\___\\/////\\\\\\///__\\/\\\\\\\\\\\\___\\/\\\\\\___/\\\\\\//////////______________/\\\\\\//////////____/\\\\\\\\\\\\\\\\\\\\\\\\\\__\\/\\\\\\\\\\\\________/\\\\\\\\\\\\_\\/\\\\\\///////////__       \n" +
                "  _______\\/\\\\\\_______\\/\\\\\\_______\\/\\\\\\_\\/\\\\\\_____\\/\\\\\\_______\\/\\\\\\_____\\/\\\\\\/\\\\\\__\\/\\\\\\__/\\\\\\________________________/\\\\\\______________/\\\\\\/////////\\\\\\_\\/\\\\\\//\\\\\\____/\\\\\\//\\\\\\_\\/\\\\\\_____________      \n" +
                "   _______\\/\\\\\\_______\\/\\\\\\_______\\/\\\\\\_\\/\\\\\\\\\\\\\\\\\\\\\\/________\\/\\\\\\_____\\/\\\\\\//\\\\\\_\\/\\\\\\_\\/\\\\\\____/\\\\\\\\\\\\\\___________\\/\\\\\\____/\\\\\\\\\\\\\\_\\/\\\\\\_______\\/\\\\\\_\\/\\\\\\\\///\\\\\\/\\\\\\/_\\/\\\\\\_\\/\\\\\\\\\\\\\\\\\\\\\\_____     \n" +
                "    _______\\/\\\\\\_______\\/\\\\\\_______\\/\\\\\\_\\/\\\\\\//////\\\\\\________\\/\\\\\\_____\\/\\\\\\\\//\\\\\\\\/\\\\\\_\\/\\\\\\___\\/////\\\\\\___________\\/\\\\\\___\\/////\\\\\\_\\/\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\_\\/\\\\\\__\\///\\\\\\/___\\/\\\\\\_\\/\\\\\\///////______    \n" +
                "     _______\\/\\\\\\_______\\/\\\\\\_______\\/\\\\\\_\\/\\\\\\____\\//\\\\\\_______\\/\\\\\\_____\\/\\\\\\_\\//\\\\\\/\\\\\\_\\/\\\\\\_______\\/\\\\\\___________\\/\\\\\\_______\\/\\\\\\_\\/\\\\\\/////////\\\\\\_\\/\\\\\\____\\///_____\\/\\\\\\_\\/\\\\\\_____________   \n" +
                "      _______\\/\\\\\\_______\\//\\\\\\______/\\\\\\__\\/\\\\\\_____\\//\\\\\\______\\/\\\\\\_____\\/\\\\\\__\\//\\\\\\\\\\\\_\\/\\\\\\_______\\/\\\\\\___________\\/\\\\\\_______\\/\\\\\\_\\/\\\\\\_______\\/\\\\\\_\\/\\\\\\_____________\\/\\\\\\_\\/\\\\\\_____________  \n" +
                "       _______\\/\\\\\\________\\///\\\\\\\\\\\\\\\\\\/___\\/\\\\\\______\\//\\\\\\__/\\\\\\\\\\\\\\\\\\\\\\_\\/\\\\\\___\\//\\\\\\\\\\_\\//\\\\\\\\\\\\\\\\\\\\\\\\/____________\\//\\\\\\\\\\\\\\\\\\\\\\\\/__\\/\\\\\\_______\\/\\\\\\_\\/\\\\\\_____________\\/\\\\\\_\\/\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\_ \n" +
                "        _______\\///___________\\/////////_____\\///________\\///__\\///////////__\\///_____\\/////___\\////////////_______________\\////////////____\\///________\\///__\\///______________\\///__\\///////////////__\n" +
                "WELCOME TO THE TURING GAME" + RESET);
    }
}

