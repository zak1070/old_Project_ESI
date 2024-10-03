package Model.Validator;

import Model.Code;
import Model.TuringException;

/**
 * The CompareOneDigit class is a specific type of Validator that compares a single digit of a given code
 * to a specific value or condition. It supports different validation rules based on the validator number.
 */
public class CompareOneDigit extends Validator {

    /**
     * Constructor for CompareOneDigit.
     * Initializes a new CompareOneDigit validator with a specific number and secret code.
     *
     * @param nbValidator The number of the validator. Determines the rule for comparison.
     * @param codeSecret The secret code against which comparisons are to be made.
     */
    public CompareOneDigit(int nbValidator, Code codeSecret) {
        super(nbValidator, codeSecret);
    }


    /**
     * Verifies the given code against the secret code based on the validator's number.
     * The method of verification varies depending on the validator number.
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
        int secretDigit;
        int digitOfCode;
        switch (this.nbValidator) {

            case 1:
                secretDigit = this.codeSecret.getDigit(0);
                digitOfCode = code.getDigit(0);

                return comp(digitOfCode, secretDigit,1);
            case 2:
                secretDigit = this.codeSecret.getDigit(0);
                digitOfCode = code.getDigit(0);

                return comp(digitOfCode, secretDigit,3);
            case 3:
                secretDigit = this.codeSecret.getDigit(1);
                digitOfCode = code.getDigit(1);

                return comp(digitOfCode, secretDigit,3);
            case 4:
                secretDigit = this.codeSecret.getDigit(1);
                digitOfCode = code.getDigit(1);

                return comp(digitOfCode, secretDigit,4);
            default:
                // Consider handling this case or throwing an exception
                throw new IllegalArgumentException("Invalid validator number: " + this.nbValidator);
        }
    }


    /**
     * Provides a string representation of the validator's category based on its number.
     *
     * @return A string describing the validator's rule.
     * @throws IllegalArgumentException If the validator number is not valid.
     */
    @Override
    public  String stringCategory(){
        return switch (nbValidator) {
            case 1 -> "blue = 1 | blue > 1";
            case 2 -> "blue < 3 | blue = 3 | blue > 3";
            case 3 -> "yellow < 3 | yellow = 3 | yellow > 3";
            case 4 -> "yellow < 4 | yellow = 4 | yellow > 4";
            default -> throw new IllegalArgumentException("Numéro de validateur non valide: " + nbValidator);
        };
    }


    private boolean comp( int codeDigit, int secretDigit,int nbToCompare) {
        if (secretDigit == nbToCompare) {
            return codeDigit == nbToCompare;
        } else if (secretDigit > nbToCompare) {
            return codeDigit > nbToCompare;
        } else return codeDigit < nbToCompare;
    }


}