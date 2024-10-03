package Command;

import Model.Problem;

/**
 * The NextRoundCmd class is a concrete implementation of the Command interface.
 * It represents a command to advance to the next round in a problem.
 */
public class NextRoundCmd implements Command {
    private final Problem problem;

    /**
     * Constructor for NextRoundCmd.
     * Initializes a new command with a specified problem.
     *
     * @param problem The problem context in which the round advancement is to be performed.
     */
    public NextRoundCmd(Problem problem) {
        this.problem = problem;
    }

    /**
     * Executes the command to advance to the next round of the problem.
     */
    @Override
    public void execute() {
        problem.nextRound();
    }

    /**
     * Reverts the action of advancing to the next round by going back to the previous round in the problem.
     */
    @Override
    public void unExecute() {
        problem.previousRound();
    }

    public String toString() {
        return "- Next round  " ;
    }
}
