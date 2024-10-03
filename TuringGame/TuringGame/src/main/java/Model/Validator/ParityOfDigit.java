package Model.Validator;

import Model.Code;
import Model.TuringException;


/**
 * The ParityOfDigit class is a type of Validator that checks the parity (even or odd) of a specific digit in a code.
 * It compares this parity with that of the same position in the secret code.
 */
public class ParityOfDigit extends Validator {


    /**
     * Constructor for ParityOfDigit.
     * Initializes a new ParityOfDigit validator with a specific number and the secret code.
     *
     * @param nbValidator The number of the validator, which determines the position of the digit to check for parity.
     * @param codeSecret  The secret code against which the comparison is to be made.
     */
    public ParityOfDigit(int nbValidator, Code codeSecret) {
        super(nbValidator, codeSecret);
    }


    /**
     * Verifies if the parity of a specific digit in the given code matches that of the same digit in the secret code.
     *
     * @param code The code to be verified.
     * @return true if the parity of the specified digit in the code matches that in the secret code, false otherwise.
     * @throws TuringException If the provided code is null or if the validator number is not recognized.
     */
    @Override
    public boolean verify(Code code) {
        if (code == null) {
            throw new TuringException("The code is null:");
        }
        int digitToCompare;
        int digitOfSecret;
        switch (this.nbValidator) {
            case 5:
                digitOfSecret = codeSecret.getDigit(0);
                digitToCompare = code.getDigit(0);
                return isEven(digitOfSecret, digitToCompare);
            case 6:
                digitOfSecret = codeSecret.getDigit(1);
                digitToCompare = code.getDigit(1);
                return isEven(digitOfSecret, digitToCompare);
            case 7:
                digitOfSecret = codeSecret.getDigit(2);
                digitToCompare = code.getDigit(2);
                return isEven(digitOfSecret, digitToCompare);
            default:
                throw new TuringException("Invalid comparator number.");
        }
    }

    private boolean isEven(int secretDigit, int digit) {
        if (secretDigit % 2 == 0) {
            return digit % 2 == 0;
        } else return digit % 2 == 1;
    }


    /**
     * Provides a string representation of the validator's category.
     *
     * @return A string describing the validator's rule related to the parity of a specific digit.
     * @throws TuringException If the validator number is not valid.
     */
    @Override
    public String stringCategory() {
        return switch (this.nbValidator) {
            case 5 -> "first digit is even";
            case 6 -> "second digit is even";
            case 7 -> "third digit is even";
            default -> throw new TuringException("Invalid comparator number.");
        };
    }
}
