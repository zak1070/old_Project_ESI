package Model.Validator;

import Model.Code;


/**
 * The Validator class is an abstract base class for different types of validators.
 * Each validator is responsible for checking specific conditions in a code against a secret code.
 */
public abstract class Validator {
    protected int nbValidator;
    protected Code codeSecret;


    /**
     * Constructor for Validator.
     * Initializes a new Validator with a specific number and a secret code.
     *
     * @param nbValidator The number of the validator, which identifies the specific type of validation to be performed.
     * @param codeSecret The secret code used for validation purposes.
     */
    public Validator(int nbValidator, Code codeSecret) {
        this.nbValidator = nbValidator;
        this.codeSecret = codeSecret;
    }


    /**
     * Abstract method to verify a code against the secret code.
     * This method must be implemented by subclasses to define specific validation logic.
     *
     * @param codeSecret The code to be verified.
     * @return true if the code meets the validation criteria, false otherwise.
     */
    public abstract boolean verify(Code codeSecret);


    /**
     * Abstract method to provide a string representation of the validator's category.
     * This method must be implemented by subclasses to describe the validation rule.
     *
     * @return A string describing the validator's rule.
     */
    public abstract String stringCategory();

    /**
     * Gets the number of the validator.
     *
     * @return The number identifying the type of validator.
     */
    public int getNbValidator() {
        return this.nbValidator;
    }
}
