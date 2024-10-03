package Model.Validator;

import Model.Code;
import Model.TuringException;

/**
 * The TwinDigits class is a type of Validator that checks if there is a digit appearing exactly twice (a twin) in both the user's code and the secret code.
 */
public class TwinDigits extends Validator {


    /**
     * Constructor for TwinDigits.
     * Initializes a new TwinDigits validator with a specific number and the secret code.
     *
     * @param nbValidator The number of the validator, which must be a specific value for this validator type.
     * @param codeSecret The secret code against which the comparison is to be made.
     * @throws IllegalArgumentException If the validator number is not valid for this type of validator.
     */
    public TwinDigits(int nbValidator,  Code codeSecret) {
        super(nbValidator, codeSecret);
        if (nbValidator != 21) {
            throw new IllegalArgumentException("Invalid validator number: " + this.nbValidator);
        }
    }

    /**
     * Verifies if there is a digit appearing exactly twice in both the user's code and the secret code.
     *
     * @param userCode The user's code to be verified.
     * @return true if both the user's code and the secret code contain a digit that appears exactly twice, false otherwise.
     * @throws TuringException If the provided code is null.
     */
    @Override
    public boolean verify(Code userCode) {
        // Count repetitions for both secret and user codes.
        int[] secretRepetitions = countRepetitions(this.codeSecret);
        int[] userRepetitions = countRepetitions(userCode);

        // Check for a digit appearing exactly twice in each code.
        boolean secretTwinFound = false;
        boolean userTwinFound = false;

        // Check for twin digits in the secret code.
        for (int i = 1; i < secretRepetitions.length; i++) {
            if (secretRepetitions[i] == 2) {
                secretTwinFound = true;
                break;
            }
        }

        // Check for twin digits in the user code.
        for (int i = 1; i < userRepetitions.length; i++) {
            if (userRepetitions[i] == 2) {
                userTwinFound = true;
                break;
            }
        }

        return secretTwinFound && userTwinFound; // True if both codes have a digit that appears exactly twice.
    }

    private int[] countRepetitions(Code code) {
        if (code==null){
            throw new TuringException("The code is null:");
        }
        int[] repetitions = new int[6]; // Array for digits 1 to 5.
        for (int i = 0; i < code.length(); i++) {
            int digit = code.getDigit(i);
            repetitions[digit]++;
        }
        return repetitions;
    }



    /**
     * Provides a string representation of the validator's category.
     *
     * @return A string describing the validator's rule related to the presence of twin digits in the code.
     */
    @Override
    public String stringCategory() {

        return "Determines whether a code digit is present in exactly two copies in the code (but not three)\n" +
                "no pair | a pair";
    }
}
