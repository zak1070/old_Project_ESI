package Model.Validator;

import Model.Code;
import Model.TuringException;

/**
 * The MajorityEven class is a type of Validator that checks if the majority of the digits in a code are even.
 * It compares this condition between the user's code and the secret code.
 */
public class MajorityEven extends Validator {


    /**
     * Constructor for MajorityEven.
     * Initializes a new MajorityEven validator with a specific number and the secret code.
     *
     * @param nbValidator The number of the validator, which must be a specific value for this validator type.
     * @param codeSecret The secret code against which the comparison is to be made.
     */
    public MajorityEven(int nbValidator,  Code codeSecret) {
        super(nbValidator, codeSecret);
    }


    /**
     * Verifies if the majority of the digits of the given code are even, and compares it with the secret code.
     *
     * @param code The code to be verified.
     * @return true if both the user's code and the secret code have a majority of even digits, false otherwise.
     * @throws TuringException If the provided code is null or if the validator number is not recognized.
     */
    @Override
    public boolean verify(Code code) {
        if (code==null){
            throw new TuringException("The code is null:");
        }
        int evenCountCode = countEvenDigits(code);
        int evenCountSecret = countEvenDigits(this.codeSecret);


        if (this.nbValidator == 16) {
            // Vérifie si la majorité des chiffres dans le code secret et dans le code utilisateur sont pairs
            return isMajorityEven(evenCountCode, code.length()) ==
                    isMajorityEven(evenCountSecret, this.codeSecret.length());
        }
        // Autres cas peuvent être ajoutés ici si nécessaire
        throw new TuringException("Invalid validator number: " + this.nbValidator);
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

    private boolean isMajorityEven(int evenCount, int totalDigits) {
        return evenCount > totalDigits / 2;
    }


    /**
     * Provides a string representation of the validator's category.
     *
     * @return A string describing the validator's rule related to the majority of even digits.
     */
    @Override
    public String stringCategory() {
           return "Determines if the majority of digits in the code are even \n" +
                   "even > odd | even < odd";
        }
}
