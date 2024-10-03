package Model.Validator;

import Model.Code;
import Model.TuringException;

/**
 * The CountEven class is a type of Validator that counts the number of even digits in a code.
 * It compares this count with that of a secret code to determine if the validation condition is met.
 */
public class CountEven extends Validator {


    /**
     * Constructor for CountEven.
     * Initializes a new CountEven validator with a specific number and the secret code.
     *
     * @param nbValidator The number of the validator, which is used to determine the validation rule.
     * @param codeSecret  The secret code against which the comparison is to be made.
     */
    public CountEven(int nbValidator, Code codeSecret) {
        super(nbValidator, codeSecret);
    }


    /**
     * Verifies the given code by counting the even digits and comparing this count with the secret code.
     *
     * @param code The code to be verified.
     * @return true if the count of even digits in the code matches that in the secret code, false otherwise.
     * @throws TuringException          If the provided code is null.
     * @throws IllegalArgumentException If the validator number is not recognized.
     */
    @Override
    public boolean verify(Code code) {
        if (code == null) {
            throw new TuringException("The code is null:");
        }
        if (this.nbValidator == 17) {
            // Comptez les chiffres pairs dans le code secret et le code utilisateur.
            int evenCountUser = countEvenDigits(code);
            int evenCountSecret = countEvenDigits(this.codeSecret);

            return evenCountSecret == evenCountUser;
        } else {
            throw new IllegalArgumentException("Invalid validator number: " + this.nbValidator);
        }
    }

    private int countEvenDigits(Code code) {
        int count = 0;
        for (int i = 0; i < code.length(); i++) {
            if (code.getDigit(i) % 2 == 0) {
                count++;
            }
        }
        return count;
    }

    /**
     * Provides a string representation of the validator's category.
     *
     * @return A string describing the validator's rule related to counting even numbers.
     */
    @Override
    public String stringCategory() {
        return "zero even numbers | one even number | two even numbers | three even numbers";
    }
}
