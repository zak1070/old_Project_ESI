package Model.Validator;

import Model.Code;
import Model.TuringException;

/**
 * The CompareSum class is a type of Validator that compares the sum of certain digits of a code
 * against a fixed value. It is specifically designed to handle a particular validation rule.
 */
public class CompareSum extends Validator {

    private static final int COMPARISON_VALUE = 6; // The value against which the sum of digits is compared

    /**
     * Constructor for CompareSum.
     * Initializes a new CompareSum validator with a specific number and the secret code.
     *
     * @param nbValidator The number of the validator, which must be a specific value for this validator type.
     * @param secretCode The secret code against which comparisons are to be made.
     * @throws TuringException If the validator number is not valid for this type of validator.
     */
    public CompareSum(int nbValidator, Code secretCode) {
        super(nbValidator, secretCode);
        validateValidatorNumber();
    }

    /**
     * Verifies the given code against the secret code by comparing the sums of their digits.
     *
     * @param code The code to be verified.
     * @return true if the sum of the digits of the code and the secret code satisfy the validator's condition,
     *         false otherwise.
     * @throws TuringException If the provided code is null.
     */
    @Override
    public boolean verify(Code code) {
        if (code == null){
            throw new TuringException("The code is null:");
        }

        int sumCode = calculateSum(code);
        int sumSecret = calculateSum(this.codeSecret);

        return compareSums(sumSecret, sumCode);
    }

    private int calculateSum(Code code) {
        return code.getDigit(0) + code.getDigit(1);
    }

    private boolean compareSums(int sumSecret, int sumCode) {
        return compareSumWithFixedValue(sumSecret) == compareSumWithFixedValue(sumCode);
    }

    private int compareSumWithFixedValue(int sum) {
        return Integer.compare(sum, COMPARISON_VALUE);
    }

    private void validateValidatorNumber() {
        if (this.nbValidator != 19) {
            throw new TuringException("Invalid validator number: " + this.nbValidator);
        }
    }



    /**
     * Provides a string representation of the validator's category.
     *
     * @return A string describing the validator's rule related to the sum of digits.
     */
    @Override
    public String stringCategory() {
        return "the sum of blue and yellow is less than 6 | the sum of blue and yellow is equal to 6 | the sum of blue and yellow is greater than 6";
    }
}
