package ConsoleVersion.Controller;

import ConsoleVersion.ConsoleView.ConsoleView;
import Model.GameFacade;
import Model.TuringException;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * The Controller class is responsible for handling user input and executing corresponding actions in the game.
 * It interacts with the GameFacade to control the game's flow and updates the view accordingly.
 */
public class Controller {

    private static final Pattern ENTER_CODE = Pattern.compile("^code\\s+[1-5]{3}(\\s+)*$");
    private static final Pattern CHOOSE_VALIDATOR = Pattern.compile("^choose\\s+validator\\s+\\d+(\\s+)*$");
    private static final Pattern NEXT_ROUND = Pattern.compile("^next\\s+round(\\s+)*$");
    private static final Pattern CHECK_CODE = Pattern.compile("^guess\\s+code(\\s+)*$");
    private static final Pattern HELP = Pattern.compile("^help(\\s+)*$");
    private static final Pattern EXIT = Pattern.compile("^exit(\\s+)*$");

    private static final Pattern UNDO = Pattern.compile("^undo(\\s+)*$");
    private static final Pattern REDO = Pattern.compile("^redo(\\s+)*$");


    private final GameFacade game;
    private final ConsoleView view;


    /**
     * Constructor for Controller.
     * Initializes a new Controller with a given GameFacade instance and registers the ConsoleView.
     *
     * @param game The GameFacade instance used to interact with the game logic.
     */
    public Controller(GameFacade game) {
        this.game = game;
        this.view = new ConsoleView(game.getGame());

    }

    /**
     * The askAInt method asks the user to input an integer and returns it.
     * If the input is not an integer, an error message is displayed and the user is prompted again.
     *
     * @param message the message to display to the user
     * @return the integer entered by the user
     */
    public int askForInt(String message) {
        System.out.print(message);
        Scanner clavier = new Scanner(System.in);

        while (!clavier.hasNextInt()) {
            clavier.next();
            System.out.println();
            this.view.displayError("Invalid input. " + message);
        }
        System.out.println();
        return clavier.nextInt();
    }


    /**
     * Starts the game, displaying the initial state and problem options. Handles the selection of the problem by the user.
     */
    public void startGame() {
        view.display_start();
        try {
            // Wait for 2 seconds (2000 milliseconds) before proceeding.
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.err.println("Interrupted while waiting: " + e.getMessage());
            // Optional: handle the interruption as needed.
        }
        view.displayProblems();
        int problem = askForInt("Choice a problem (press 0 for random): ");
        try {
            game.startNewGame(problem);
        } catch (TuringException te) {
            view.displayError(te.getMessage());
        }

    }

    /**
     * The askAString method asks the user to input a string and returns it.
     *
     * @param message the message to display to the user
     * @return the string entered by the user
     */
    private static String askForString(String message) {
        System.out.print(message);
        Scanner clavier = new Scanner(System.in);

        String input = clavier.nextLine();
        System.out.println();
        return input;
    }

    /**
     * Recognizes the type of command input by the user based on predefined patterns.
     *
     * @param command The user input command.
     * @return The recognized command type.
     * @throws TuringException If the command is not recognized.
     */
    private String recognizeCommand(String command) {
        if (ENTER_CODE.matcher(command).matches()) {
            return "enterCode";
        } else if (CHOOSE_VALIDATOR.matcher(command).matches()) {
            return "chooseValidator";
        } else if (NEXT_ROUND.matcher(command).matches()) {
            return "nextRound";
        } else if (CHECK_CODE.matcher(command).matches()) {
            return "checkCode";
        } else if (HELP.matcher(command).matches()) {
            return "help";
        } else if (EXIT.matcher(command).matches()) {
            return "exit";
        } else if (UNDO.matcher(command).matches()) {
            return "undo";
        } else if (REDO.matcher(command).matches()) {
            return "redo";
        } else {
            throw new TuringException("Press help for help :");
        }
    }



    /**
     * Executes the recognized command based on the user input.
     *
     * @param command The user input command to be executed.
     */
    private void executeCommand(String command) {
        command = command.toLowerCase();
        String commandType = recognizeCommand(command);

        switch (commandType) {
            case "enterCode":
                executeEnterCode(command);
                break;
            case "chooseValidator":
                executeChooseValidator(command);
                break;
            case "nextRound":
                executeNextRound(command);
                break;
            case "checkCode":
                executeCheckCode(command);
                break;
            case "help":
                executeHelp(command);
                break;
            case "exit":
                executeExit(command);
                break;
            case "undo":
                executeUndo(command);
                break;
            case "redo":
                executeRedo(command);
                break;
            default:
                throw new TuringException("Press help for help");

        }
    }

    private void executeEnterCode(String command) {
        Matcher matcher = ENTER_CODE.matcher(command);
        if (matcher.matches()) {
            int code = Integer.parseInt(command.substring(5, 8));
            game.enterUserCode(code);
        }
    }

    private void executeChooseValidator(String command) {
        Matcher matcher = CHOOSE_VALIDATOR.matcher(command);
        if (matcher.matches()) {
            int validatorId = Integer.parseInt(command.split("\\s+")[2]);
            game.selectValidator(validatorId);
        }
    }

    private void executeNextRound(String command) {
        if (NEXT_ROUND.matcher(command).matches()) {
            game.nextRound();

        }
    }

    private void executeCheckCode(String command) {
        if (CHECK_CODE.matcher(command).matches()) {
           int userCode = askForInt("Enter your code -> ");
            game.guessCode(userCode);
        }
    }


    private void executeHelp(String command) {
        if (HELP.matcher(command).matches()) {
            view.showHelp();
        }
    }

    private void executeUndo(String command) {
        if (UNDO.matcher(command).matches()) {
            game.undo();
        }
    }

    private void executeRedo(String command) {
        if (REDO.matcher(command).matches()) {
            game.redo();
        }
    }

    private void executeExit(String command) {
        if (EXIT.matcher(command).matches()) {
            game.exitGame();
        }
    }

    /**
     * The main game loop. Continuously prompts for and executes commands until the game is over.
     */
    public void run() {
        try {
            if (game.isGameOver()) {
                return; // Ends recursion if the game is over
            }
            String command = askForString(">");
            executeCommand(command);

        } catch (TuringException te) {
            view.displayError(te.getMessage());
        }
        run();
    }

}



