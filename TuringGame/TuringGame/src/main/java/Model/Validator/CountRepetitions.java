package Model.Validator;

import Model.Code;
import Model.TuringException;

/**
 * The CountRepetitions class is a type of Validator that counts the maximum number of repetitions
 * of any digit in a code. It compares this maximum count with that of a secret code.
 */
public class CountRepetitions extends Validator {

    /**
     * Constructor for CountRepetitions.
     * Initializes a new CountRepetitions validator with a specific number and the secret code.
     *
     * @param nbValidator The number of the validator, which must be a specific value for this validator type.
     * @param codeSecret The secret code against which comparisons are to be made.
     * @throws TuringException If the validator number is not valid for this type of validator.
     */
    public CountRepetitions(int nbValidator, Code codeSecret) {
        super(nbValidator, codeSecret);
        if (nbValidator != 20) {
            throw new TuringException("Invalid validator number: " + this.nbValidator);
        }
    }


    /**
     * Verifies the given code by finding the maximum number of repetitions of any digit and comparing it with the secret code.
     *
     * @param code The code to be verified.
     * @return true if the maximum number of repetitions of any digit in the code matches that in the secret code, false otherwise.
     * @throws TuringException If the provided code is null or contains digits outside the 1-5 range.
     */
    @Override
    public boolean verify(Code code) {
        if (code==null){
            throw new TuringException("The code is null:");
        }
        // Trouvez le nombre maximal de répétitions dans le code secret et dans le code utilisateur.
        int maxRepetitionsUser = findMaxRepetitions(code);
        int maxRepetitionsSecret = findMaxRepetitions(this.codeSecret);

        // Retournez vrai si les deux codes ont le même nombre maximal de répétitions pour un chiffre.
        return maxRepetitionsSecret == maxRepetitionsUser;
    }

    // Méthode privée pour trouver le nombre maximal de répétitions pour un chiffre dans un code donné.
    private int findMaxRepetitions(Code code) {
        int[] repetitions = new int[5]; // Pour les chiffres de 1 à 5.
        for (int i = 0; i < code.length(); i++) {
            int digit = code.getDigit(i);
            if (digit < 1 || digit > 5) {
                throw new TuringException("Code contains digits outside 1-5 range: " + digit);
            }
            repetitions[digit - 1]++; // -1, car les indices de tableau commencent à 0.
        }

        int maxRepetition = 0;
        for (int rep : repetitions) {
            if (rep > maxRepetition) {
                maxRepetition = rep;
            }
        }

        return maxRepetition;
    }


    /**
     * Provides a string representation of the validator's category.
     *
     * @return A string describing the validator's rule related to counting digit repetitions.
     */
    @Override
    public String stringCategory() {
        // Simplified string representation of the repetition category
        return "no double | double digit | triple digit |";
    }
}
