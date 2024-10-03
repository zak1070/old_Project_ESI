package Model;

import Loader.Loader;
import Model.Validator.Validator;
import Model.Validator.ValidatorFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The Problem class represents a specific problem or challenge involving a secret code and a set of validators.
 * It manages the rounds of validation, the secret code, and the validators involved in the problem.
 */
public class Problem {

    private List<Validator> validators;
    private Code secretCode;
    private List<Round> rounds;
    private int currentRound;

    /**
     * Constructor for Problem.
     * Initializes the problem using a specific problem number.
     *
     * @param problemNumber The number identifying the specific problem to be initialized.
     */
    public Problem(int problemNumber) {
        initializeProblem(problemNumber);
    }

    /**
     * Initializes the problem with the given problem number.
     * Loads the problem data, sets up the secret code and validators, and starts the first round.
     *
     * @param problemNumber The number identifying the specific problem.
     */
    private void initializeProblem(int problemNumber) {
        String problemData = Loader.getLineByLineNumber(problemNumber);
        int secretCodeValue = Loader.getCodeElementFromLine(problemData);
        this.secretCode = new Code(secretCodeValue);

        int[] validatorNumbers = Loader.getValidatorsFromLine(problemData);
        this.validators = createValidators(validatorNumbers);
        this.rounds = new ArrayList<>();
        nextRound();
        this.currentRound = 0;
    }

    /**
     * Creates validators based on an array of validator numbers.
     *
     * @param validatorNumbers An array of integers representing validator numbers.
     * @return A list of Validator objects created based on the validator numbers.
     */
    private List<Validator> createValidators(int[] validatorNumbers) {
        List<Validator> validatorList = new ArrayList<>();
        for (int validatorNumber : validatorNumbers) {
            Validator validator = ValidatorFactory.createValidator(validatorNumber, this.secretCode);
            validatorList.add(validator);
        }
        return validatorList;
    }

    /**
     * Advances to the next round in the problem.
     * Adds a new Round to the rounds list and increments the current round counter.
     */
    public void nextRound() {
        this.rounds.add(new Round());
        this.currentRound++;
    }

    /**
     * Goes back to the previous round in the problem.
     * Removes the last round from the rounds list and decrements the current round counter.
     */
    public void previousRound() {
        if (!rounds.isEmpty()) {
            this.rounds.removeLast();
            this.currentRound--;
        }
    }

    /**
     * Adds a code to the current round.
     * Throws a TuringException if a code is added after testing a validator.
     *
     * @param code The code to be added to the current round.
     * @throws TuringException If a code is added after a validator has been tested.
     */
    public void addCodeAtRound(int code) {
        Round currentRound = this.rounds.get(this.currentRound);
        if (currentRound.getTestedValidatorsCount() != 0) {
            throw new TuringException("You can't change the code after testing a validator.");
        }
        currentRound.addCode(code);
    }

    /**
     * Removes the code from the current round.
     */
    public void removeCodeAtRound() {
        this.rounds.get(currentRound).removeCode();
    }

    /**
     * Attempts to guess the secret code.
     *
     * @param code The code guessed by the user.
     * @return true if the guessed code matches the secret code, false otherwise.
     */
    public boolean guessCode(int code) {
        Code usercode = new Code(code);
        return secretCode.getCode() == usercode.getCode();
    }

    /**
     * Tests a validator at the current round.
     *
     * @param index The index of the validator to be tested.
     * @return true if the validator successfully validates the code, false otherwise.
     */
    public boolean testAValidator(int index) {
        Validator validator = validators.get(index);
        return this.rounds.get(currentRound).testValidator(validator);
    }

    /**
     * Untests a validator at the current round.
     *
     * @param index The index of the validator to be untested.
     */
    public void untestValidator(int index) {
        Validator validator = validators.get(index);
        this.rounds.get(currentRound).untestValidator(validator);
    }

    /**
     * Gets an unmodifiable list of validators.
     *
     * @return An unmodifiable list of all validators.
     */
    public List<Validator> getValidators() {
        return Collections.unmodifiableList(validators);
    }

    /**
     * Gets an unmodifiable list of validators that successfully validated the code in the current round.
     *
     * @return An unmodifiable list of validators that returned true during testing in the current round.
     */
    public List<Validator> getValidatorsOk() {
        return rounds.get(currentRound).getTestedValidatorsOk();
    }

    /**
     * Gets an unmodifiable list of validators that failed to validate the code in the current round.
     *
     * @return An unmodifiable list of validators that returned false during testing in the current round.
     */
    public List<Validator> getValidatorsFalse() {
        return (rounds.get(currentRound).getTestedValidatorsFalse());
    }

    /**
     * Calculates the total number of validators tested across all rounds.
     *
     * @return The total number of validators tested.
     */
    public int getNbValidatorsTested() {
        int validatorsTested = 0;
        for (Round round : rounds) {
            validatorsTested += round.getTestedValidatorsCount();
        }
        return validatorsTested;
    }

    /*
    public int getNbValidatorTrue() {
        int nbValidatorTrue = 0;
        for (Round round : rounds) {
            nbValidatorTrue += round.getTestedValidatorsOk().size();
        }
        return nbValidatorTrue;
    }*/

    /**
     * Retrieves the current round number.
     *
     * @return The number of the current round.
     */
    public int getCurrentRound() {
        return currentRound + 1;
    }

    /**
     * Generates a string representation of the codes from previous rounds.
     *
     * @return A string containing the codes and the validators that validated them in previous rounds.
     */
    public String stringPreviousRoundsCodes() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < rounds.size(); i++) {
            Round round = rounds.get(i);
            result.append("ROUND ")
                    .append(i + 1)
                    .append(": code : ")
                    .append(round.getCurrentCodeString())
                    .append("    - Validated by: ");
            List<Validator> validatorsOk = round.getTestedValidatorsOk();
            for (int j = 0; j < validatorsOk.size(); j++) {
                char validatorChar = (char) ('A' + validators.indexOf(validatorsOk.get(j)));
                result.append(validatorChar);
                if (j < validatorsOk.size() - 1) {
                    result.append(", ");
                }
            }
            result.append("\n");
        }
        return result.toString();
    }
}
