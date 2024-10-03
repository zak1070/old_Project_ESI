package Model.Validator;

import Model.Code;
import Model.TuringException;


/**
 * The OrderOfDigits class is a type of Validator that checks the order of the digits in a code.
 * It compares whether the user's code has the same digit order (ascending, descending, or no specific order) as the secret code.
 */
public class OrderOfDigits extends Validator {

    /**
     * Constructor for OrderOfDigits.
     * Initializes a new OrderOfDigits validator with a specific number and the secret code.
     *
     * @param nbValidator The number of the validator, which must be a specific value for this validator type.
     * @param codeSecret The secret code against which the comparison is to be made.
     * @throws TuringException If the validator number is not valid for this type of validator.
     */
    public OrderOfDigits(int nbValidator, Code codeSecret) {
        super(nbValidator, codeSecret);
        if (nbValidator != 22) {
            throw new TuringException("Invalid validator number: " + this.nbValidator);
        }
    }


    /**
     * Verifies if the order of digits in the given code matches that of the secret code.
     *
     * @param userCode The code to be verified.
     * @return true if both the user's code and the secret code have the same order, false otherwise.
     * @throws TuringException If the provided code is null.
     */
    @Override
    public boolean verify(Code userCode) {
        if (userCode == null) {
            throw new TuringException("The code is null:");
        }
        // Retrieve digits from the secret code
        int secretFirstDigit = this.codeSecret.getDigit(0);
        int secretSecondDigit = this.codeSecret.getDigit(1);
        int secretThirdDigit = this.codeSecret.getDigit(2);

        // Retrieve digits from the user code
        int userFirstDigit = userCode.getDigit(0);
        int userSecondDigit = userCode.getDigit(1);
        int userThirdDigit = userCode.getDigit(2);

        // Check the order of the secret code
        boolean secretIsAscending = secretFirstDigit < secretSecondDigit && secretSecondDigit < secretThirdDigit;
        boolean secretIsDescending = secretFirstDigit > secretSecondDigit && secretSecondDigit > secretThirdDigit;

        // Check the order of the user code
        boolean userIsAscending = userFirstDigit < userSecondDigit && userSecondDigit < userThirdDigit;
        boolean userIsDescending = userFirstDigit > userSecondDigit && userSecondDigit > userThirdDigit;

        // If the secret code has a specific order, check if the user code has the same order
        if (secretIsAscending) {
            return userIsAscending;
        } else if (secretIsDescending) {
            return userIsDescending;
        }

        // If the secret code has no specific order, check that the user code also has no order
        return !userIsAscending && !userIsDescending;
    }


    /**
     * Provides a string representation of the validator's category.
     *
     * @return A string describing the validator's rule related to the order of digits.
     */
    @Override
    public String stringCategory() {
        return "ascending order | descending order | no order";
    }


}
