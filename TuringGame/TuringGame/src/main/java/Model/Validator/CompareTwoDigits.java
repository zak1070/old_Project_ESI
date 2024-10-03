package Model.Validator;

import Model.Code;
import Model.TuringException;


/**
 * The CompareTwoDigits class is a type of Validator that compares two specific digits of a code
 * to determine if one is greater than, less than, or equal to the other. It supports different
 * validation rules based on the validator number.
 */
public class CompareTwoDigits extends Validator {


    /**
     * Constructor for CompareTwoDigits.
     * Initializes a new CompareTwoDigits validator with a specific number and the secret code.
     *
     * @param nbValidator The number of the validator, which determines the rule for comparison.
     * @param codeSecret The secret code against which comparisons are to be made.
     */
    public CompareTwoDigits(int nbValidator, Code codeSecret) {
        super(nbValidator, codeSecret);
    }




    /**
     * Verifies the given code against the secret code based on the validator's number.
     * The method of verification varies depending on the validator number and compares two specific digits.
     *
     * @param code The code to be verified.
     * @return true if the code satisfies the validator's condition, false otherwise.
     * @throws TuringException If the provided code is null.
     * @throws IllegalArgumentException If the validator number is not recognized.
     */
    @Override
    public boolean verify(Code code) {
        if (code==null){
            throw new TuringException("The code is null:");
        }
        return switch (this.nbValidator) {
            case 11 -> compare(codeSecret.getDigit(0), codeSecret.getDigit(1), code.getDigit(0), code.getDigit(1));
            case 12 -> compare(codeSecret.getDigit(0), codeSecret.getDigit(2), code.getDigit(0), code.getDigit(2));
            case 13 -> compare(codeSecret.getDigit(1), codeSecret.getDigit(2), code.getDigit(1), code.getDigit(2));
            default -> throw new IllegalArgumentException("Invalid validator number: " + this.nbValidator);
        };
    }

    /**
     * Provides a string representation of the validator's category based on its number.
     *
     * @return A string describing the validator's rule related to the comparison of two digits.
     * @throws IllegalArgumentException If the validator number is not valid.
     */
    @Override
    public String stringCategory() {
        return switch (this.nbValidator) {
            case 11 -> "blue is less than yellow | blue is equal to yellow | blue is greater than yellow";
            case 12 -> "blue is less than purple | blue is equal to purple | blue is greater than purple";
            case 13 -> "yellow is less than purple | yellow is equal to purple | yellow is greater than purple";
            default -> throw new IllegalArgumentException("Invalid validator number: " + this.nbValidator);
        };
    }

    private boolean compare(int secretDigit1, int secretDigit2, int codeDigit1, int codeDigit2) {
        if (secretDigit1 > secretDigit2) {
            return codeDigit1 > codeDigit2;
        } else if (secretDigit1 == secretDigit2) {
            return codeDigit1 == codeDigit2;
        } else {
            return codeDigit1 < codeDigit2;
        }
    }

}
