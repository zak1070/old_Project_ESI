package Model.Validator;

import Model.Code;
import Model.TuringException;

/**
 * The Extremum class is a type of Validator that identifies the smallest or largest digit in a code.
 * It supports validation based on whether the same digit is the extremum (either smallest or largest) in both the user's code and the secret code.
 */
public class Extremum extends Validator {

    /**
     * Constructor for Extremum.
     * Initializes a new Extremum validator with a specific number and the secret code.
     *
     * @param nbValidator The number of the validator, which determines the rule for identifying the extremum.
     * @param codeSecret The secret code against which comparisons are to be made.
     */
    public Extremum(int nbValidator,Code codeSecret) {
        super(nbValidator, codeSecret);
    }


    /**
     * Verifies the given code by finding the smallest or largest digit, and compares it with the secret code.
     *
     * @param code The code to be verified.
     * @return true if the extremum digit in the code matches the extremum in the secret code, false otherwise.
     * @throws TuringException If the provided code is null.
     * @throws IllegalArgumentException If the validator number is not recognized.
     */
    @Override
    public boolean verify(Code code) {
        if (code==null){
            throw new TuringException("The code is null:");
        }
        int indexInCode;
        return switch (this.nbValidator) {
            case 14 -> {
                indexInCode = findIndexOfExtremum(code, true);
                yield indexInCode == findIndexOfExtremum(this.codeSecret, true);
                // Determine which digit is strictly the smallest in the secret code
                // Check if the same digit is the smallest in the user's code
            }
            case 15 -> {
                indexInCode = findIndexOfExtremum(code, false);
                yield indexInCode == findIndexOfExtremum(this.codeSecret, false);
                // Determine which digit is strictly the largest in the secret code
                // Check if the same digit is the largest in the user's code
            }
            default -> throw new IllegalArgumentException("Invalid validator number: " + this.nbValidator);
        };
    }

private int findIndexOfExtremum(Code code, boolean findSmallest) {
    if (code.length() == 0) return -1; // Gère le cas où le code est vide

    int extremumValue = code.getDigit(0);
    int extremumIndex = 0;
    boolean duplicateFound = false; // Indicateur pour détecter les doublons

    // Parcours à partir du deuxième élément
    for (int i = 1; i < code.length(); i++) {
        int currentValue = code.getDigit(i);
        if ((findSmallest && currentValue < extremumValue) || (!findSmallest && currentValue > extremumValue)) {
            extremumValue = currentValue;
            extremumIndex = i;
            duplicateFound = false; // Réinitialise le marqueur de doublon lorsqu'un nouvel extremum est trouvé
        } else if (currentValue == extremumValue) {
            duplicateFound = true; // Marque un doublon si la même valeur extrême est trouvée
        }
    }

    return duplicateFound ? -1 : extremumIndex; // Retourne -1 s'il y a un doublon, sinon l'index de l'extremum
}

    /**
     * Provides a string representation of the validator's category.
     *
     * @return A string describing the validator's rule related to identifying the extremum digit.
     * @throws IllegalArgumentException If the validator number is not valid.
     */
    @Override
    public String stringCategory() {
        return switch (this.nbValidator) {
            case 14 -> "blue is smaller than both purple and yellow | "
                    + "yellow is smaller than both blue and purple | "
                    + "purple is smaller than both blue and yellow";
            case 15 -> "blue is larger than both purple and yellow | "
                    + "yellow is larger than both blue and purple | "
                    + "purple is larger than both blue and yellow";
            default -> throw new IllegalArgumentException("Invalid validator number: " + this.nbValidator);
        };
    }
}
