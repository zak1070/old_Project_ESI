package Model;

import Command.CommandManager;
import Command.EnterCodeCmd;
import Command.ChooseValidatorCmd;
import Command.NextRoundCmd;
import Util.Observable;
import Util.Observer;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * The Game class represents the main logic and state of the game.
 * It manages the game's progression, including starting a game, entering codes,
 * choosing validators, and managing rounds. It also implements the Observable interface
 * to update observers with changes in game state.
 */
public class Game implements Observable {
    private Problem problem;
    private CommandManager commandManager;
    private Set<Observer> observers = new HashSet<>();

    private boolean gameOver;
    private boolean codeDiscovered;

    /**
     * Constructor for Game.
     * Initializes the command manager and sets the game's initial state.
     */
    public Game() {
        this.commandManager = new CommandManager();
        this.gameOver = false;
        this.codeDiscovered = false;
    }

    /**
     * Starts a new game with the given problem index.
     * Initializes the problem and notifies observers of the change.
     * Randomly selects a problem index if the provided index is 0.
     *
     * @param problemIndex The index of the problem to start. Randomly selected if set to 0.
     * @throws TuringException If the provided index is invalid.
     */
    public void startGame(int problemIndex) {
        if (problemIndex > 16 || problemIndex < 0) {
            throw new TuringException("Invalid index to start the game");
        }
        if (problemIndex == 0) {
            Random random = new Random();
            problemIndex = random.nextInt(1, 17);
        }

        this.problem = new Problem(problemIndex);
        notifyObservers();
    }

    /**
     * Enters a code into the current round of the problem.
     * Executes the EnterCodeCmd command and notifies observers.
     *
     * @param code The code to be entered.
     */
    public void enterCode(int code) {
        if (!gameOver) {
            commandManager.executeCommand(new EnterCodeCmd(problem, code));
            notifyObservers();
        }
    }

    /**
     * Chooses a validator based on the given index.
     * Executes the ChooseValidatorCmd command and notifies observers.
     *
     * @param validatorIndex The index of the validator to be chosen.
     */
    public void chooseValidator(int validatorIndex) {
        if (!gameOver) {
            commandManager.executeCommand(new ChooseValidatorCmd(problem, validatorIndex));
            notifyObservers();
        }
    }

    /**
     * Advances to the next round in the problem.
     * Executes the NextRoundCmd command and notifies observers.
     */
    public void nextRound() {
        if (!gameOver) {
            commandManager.executeCommand(new NextRoundCmd(problem));
            notifyObservers();
        }
    }

    /**
     * Makes a guess of the secret code.
     * Ends the game and updates the state accordingly, then notifies observers.
     *
     * @param code The guessed code.
     */
    public void guessCode(int code) {
        if (!gameOver) {
            codeDiscovered = problem.guessCode(code);
            gameOver = true;
            notifyObservers();
        }
    }

    /**
     * Checks if the game is over.
     *
     * @return true if the game is over, false otherwise.
     */
    public boolean isGameOver() {
        return this.gameOver;
    }

    /**
     * Undoes the last command.
     * Calls undo on the command manager and notifies observers.
     */
    public void undo() {
        commandManager.undo();
        notifyObservers();
    }

    /**
     * Redoes the last undone command.
     * Calls redo on the command manager and notifies observers.
     */
    public void redo() {
        commandManager.redo();
        notifyObservers();
    }

    /**
     * Gives up on the current game.
     * Sets the game state to over and not discovered, then notifies observers.
     */
    public void giveUp() {
        gameOver = true;
        codeDiscovered = false;
        notifyObservers();
    }

    /**
     * Notifies all observers about a change in the game state.
     * Constructs a new GameState object and updates each observer.
     */
    @Override
    public void notifyObservers() {
        GameState state = new GameState(gameOver, codeDiscovered, problem.getCurrentRound(),
                problem.getNbValidatorsTested(), commandManager.getRedoStackSize(), problem.getValidators(),
                problem.getValidatorsOk(), problem.getValidatorsFalse(), problem.stringPreviousRoundsCodes());

        for (Observer observer : observers) {
            observer.update(this, state);
        }
    }

    /**
     * Adds an observer to the list of observers.
     *
     * @param observer The observer to be added.
     */
    @Override
    public void addObserver(Observer observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    /**
     * Removes an observer from the list of observers.
     *
     * @param observer The observer to be removed.
     */
    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

}
