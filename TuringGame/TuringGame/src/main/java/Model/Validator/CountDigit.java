package Model.Validator;

import Model.Code;
import Model.TuringException;

/**
 * The CountDigit class is a type of Validator that counts the occurrences of a specific digit in the code.
 * It supports different validation rules based on the validator number.
 */
public class CountDigit extends Validator {
    /**
     * Constructor for CountDigit.
     * Initializes a new CountDigit validator with a specific number and the secret code.
     *
     * @param nbValidator The number of the validator, which determines the rule for counting digit occurrences.
     * @param codeSecret The secret code against which comparisons are to be made.
     */
    public CountDigit(int nbValidator,  Code codeSecret) {
        super(nbValidator, codeSecret);
    }


    /**
     * Verifies the given code by counting the occurrences of a specific digit and comparing it with the secret code.
     *
     * @param code The code to be verified.
     * @return true if the count of the specified digit in the code matches that in the secret code, false otherwise.
     * @throws TuringException If the provided code is null.
     * @throws IllegalArgumentException If the validator number is not recognized.
     */
    @Override
    public boolean verify(Code code) {
        if (code==null){
            throw new TuringException("The code is null:");
        }
        int countInSecret;
        int countInCode;
        switch (this.nbValidator) {
            case 8:
                countInCode = countOccurrence(code, 1);
                countInSecret = countOccurrence(this.codeSecret, 1);
                return countInSecret == countInCode;
            case 9:
                countInCode = countOccurrence(code, 3);
                countInSecret = countOccurrence(this.codeSecret, 3);
                return countInSecret == countInCode;
            case 10:
                countInCode = countOccurrence(code, 4);
                countInSecret = countOccurrence(this.codeSecret, 4);
                return countInSecret == countInCode;
            default:
                throw new IllegalArgumentException("Invalid validator number: " + this.nbValidator);
        }
    }

    /**
     * Provides a string representation of the validator's category based on its number.
     *
     * @return A string describing the validator's rule related to counting specific digit occurrences.
     * @throws IllegalArgumentException If the validator number is not valid.
     */
    @Override
    public String stringCategory() {
        return switch (this.nbValidator) {
            case 8 -> "no 1 | one 1 | two 1 | three 1";
            case 9 -> "no 3 | one 3 | two 3 | three 3";
            case 10 -> "no 4 | one 4 | two 4 | three 4";
            default -> throw new IllegalArgumentException("Invalid comparator number.");
        };
    }

    private int countOccurrence(Code code, int value) {
        int count = 0;
        for (int i = 0; i < code.length(); i++) {
            if (code.getDigit(i) == value) {
                count++;
            }
        }
        return count;
    }

}
