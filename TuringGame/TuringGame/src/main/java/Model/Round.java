package Model;

import Model.Validator.Validator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The Round class represents a round in a validation process. It manages validators,
 * a current code, and tracks the state and results of the validation tests.
 */
public class Round {
    private List<Validator> testedValidatorsOk;
    private List<Validator> testedValidatorsFalse;
    private Code currentCode;
    private int testedValidatorsCount;

    /**
     * Constructor for Round.
     * Initializes lists to keep track of validators and sets the count of tested validators to 0.
     */
    public Round() {
        testedValidatorsFalse = new ArrayList<>();
        this.testedValidatorsOk = new ArrayList<>();
        this.testedValidatorsCount = 0;
    }

    /**
     * Adds a new code to the round.
     * Throws a TuringException if a code is added after starting validator tests.
     *
     * @param code The integer value of the code to be added.
     * @throws TuringException If a code is added after validator tests have started.
     */
    public void addCode(int code) {
        if (testedValidatorsCount != 0) {
            throw new TuringException("Code can't be added after starting validator tests.");
        }
        this.currentCode = new Code(code);
    }

    /**
     * Removes the current code from the round.
     */
    public void removeCode() {
        this.currentCode = null;
    }

    /**
     * Tests a validator against the current code.
     * Increments the tested validators count and adds the validator to the appropriate list based on the result.
     * Throws a TuringException if more than 3 validators have already been tested.
     *
     * @param validator The Validator to be tested.
     * @return boolean indicating whether the validator successfully validated the current code.
     * @throws TuringException If more than 3 validators have already been tested.
     */
    public boolean testValidator(Validator validator) {
        if (testedValidatorsCount == 3) {
            throw new TuringException("Maximum of 3 validators tested for a round.");
        }
        if (currentCode== null){
            throw  new TuringException("The code is null .");
        }

        boolean isValidatorSuccessful = validator.verify(currentCode);
        testedValidatorsCount++;

        if (isValidatorSuccessful) {
            testedValidatorsOk.add(validator);
        } else {
            testedValidatorsFalse.add(validator);
        }

        return isValidatorSuccessful;
    }

    /**
     * Reverses the testing of a validator.
     * Removes the validator from the tested list and decrements the tested validators count.
     * Throws a TuringException if no validators have been tested yet.
     *
     * @param validator The Validator to be untested.
     * @throws TuringException If no validators have been tested yet.
     */
    public void untestValidator(Validator validator) {
        if (testedValidatorsCount == 0) {
            throw new TuringException("No validators have been tested yet.");
        }
        if (testedValidatorsOk.contains(validator)) {
            testedValidatorsOk.remove(validator);
        } else if (testedValidatorsFalse.contains(validator)) {
            testedValidatorsFalse.remove(validator);
        }

        testedValidatorsCount--;
    }

    /**
     * Returns an unmodifiable list of validators that successfully validated the current code.
     *
     * @return List of validators that returned true during testing.
     */
    public List<Validator> getTestedValidatorsOk() {
        return Collections.unmodifiableList(testedValidatorsOk);
    }

    /**
     * Returns an unmodifiable list of validators that failed to validate the current code.
     *
     * @return List of validators that returned false during testing.
     */
    public List<Validator> getTestedValidatorsFalse() {
        return Collections.unmodifiableList(testedValidatorsFalse);
    }

    /**
     * Retrieves the integer value of the current code.
     * Throws a TuringException if no code has been entered.
     *
     * @return The integer value of the current code.
     * @throws TuringException If no code has been entered.
     */
    public int getCurrentCodeValue() {
        if (this.currentCode == null) {
            throw new TuringException("No code has been entered.");
        }
        return this.currentCode.getCode();
    }

    /**
     * Returns the string representation of the current code.
     * If no code is entered, returns a default string indicating this.
     *
     * @return String representation of the current code or a message indicating no code is entered.
     */
    public String getCurrentCodeString() {
        if (this.currentCode == null) {
            return "No code entered";
        }
        return this.currentCode.toString();
    }

    /**
     * Gets the count of validators that have been tested in this round.
     *
     * @return The number of validators that have been tested.
     */
    public int getTestedValidatorsCount() {
        return this.testedValidatorsCount;
    }
}
