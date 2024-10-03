package Model.Validator;

import Model.Code;
import Model.TuringException;

/**
 * The EvenSum class is a type of Validator that checks if the sum of the digits of a code is even.
 * It compares this condition between the user's code and the secret code.
 */
public class EvenSum extends Validator {

    /**
     * Constructor for EvenSum.
     * Initializes a new EvenSum validator with a specific number and the secret code.
     *
     * @param nbValidator The number of the validator, which must be a specific value for this validator type.
     * @param codeSecret The secret code against which the comparison is to be made.
     */
    public EvenSum(int nbValidator,  Code codeSecret) {
        super(nbValidator, codeSecret);
    }


    /**
     * Verifies if the sum of the digits of the given code is even, and compares it with the secret code.
     *
     * @param code The code to be verified.
     * @return true if both the user's code and the secret code have even sums, false otherwise.
     * @throws TuringException If the provided code is null.
     * @throws IllegalArgumentException If the validator number is not recognized.
     */
    @Override
    public boolean verify(Code code) {
        if (code==null){
            throw new TuringException("The code is null:");
        }
        // Calculer la parité de la somme des chiffres du code secret et du code utilisateur.
        boolean isUserCodeSumEven = isSumEven(code);
        boolean isSecretSumEven  = isSumEven(this.codeSecret);

        // Si le nbValidator est 18, comparez la parité de la somme des chiffres du code secret avec celle du code utilisateur.
        if (this.nbValidator == 18) {
            return isSecretSumEven == isUserCodeSumEven;
        }

        throw new IllegalArgumentException("Invalid validator number: " + this.nbValidator);
    }

    private boolean isSumEven(Code code) {
        int sum = 0;
        for (int i = 0; i < code.length(); i++) {
            sum += code.getDigit(i);
        }
        return sum % 2 == 0; // La somme est paire si le reste de la division par 2 est 0.
    }

    /**
     * Provides a string representation of the validator's category.
     *
     * @return A string describing the validator's rule related to the evenness of the sum of digits.
     */
    @Override
    public String stringCategory() {
        return "the sum of digits is even | the sum of digits is not even";
    }
}
