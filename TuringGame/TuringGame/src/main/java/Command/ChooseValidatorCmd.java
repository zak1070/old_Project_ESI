package Command;

import Model.Problem;

/**
 * The ChooseValidatorCmd class is a concrete implementation of the Command interface.
 * It represents a command to choose a validator in a given problem context.
 */
public class ChooseValidatorCmd implements Command {
    private final Problem problem;
    private final int validator;

    /**
     * Constructor for ChooseValidatorCmd.
     * Initializes a new command with a specified problem and validator.
     *
     * @param problem   The problem context in which the validator is to be chosen.
     * @param validator The index of the validator to be chosen.
     */
    public ChooseValidatorCmd(Problem problem, int validator) {
        this.problem = problem;
        this.validator = validator;
    }

    /**
     * Executes the command to choose a validator in the specified problem context.
     * It invokes the testAValidator method on the problem with the given validator index.
     */
    @Override
    public void execute() {
        problem.testAValidator(validator);
    }

    /**
     * Reverts the action of choosing a validator in the specified problem context.
     * It invokes the untestValidator method on the problem to undo the validator selection.
     */
    @Override
    public void unExecute() {
        problem.untestValidator(validator);
    }

   /* public String toString() {
        return "- Choose validator " + validator;
    }*/
}

