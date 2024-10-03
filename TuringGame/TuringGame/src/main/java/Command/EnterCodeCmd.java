package Command;

import Model.Problem;

/**
 * The EnterCodeCmd class is a concrete implementation of the Command interface.
 * It represents a command to enter a code into a problem during a specific round.
 */
public class EnterCodeCmd implements Command {
    private final Problem problem;
    private final int code;

    /**
     * Constructor for EnterCodeCmd.
     * Initializes a new command with a specified problem and code to be entered.
     *
     * @param problem The problem context in which the code is to be entered.
     * @param userCode The code to be entered into the problem.
     */
    public EnterCodeCmd(Problem problem, int userCode) {
        this.problem = problem;
        this.code = userCode;
    }

    /**
     * Executes the command to enter a code into the current round of the problem.
     */
    @Override
    public void execute() {
        problem.addCodeAtRound(code);
    }

    /**
     * Reverts the action of entering a code in the current round of the problem.
     */
    @Override
    public void unExecute() {
        problem.removeCodeAtRound();
    }

    public String toString() {
        return "- enter code " + code;
    }
}
