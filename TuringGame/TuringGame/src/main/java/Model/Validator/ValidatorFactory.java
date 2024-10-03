package Model.Validator;

import Model.Code;


/**
 * The ValidatorFactory class is a factory class responsible for creating Validator objects.
 * It provides a static method to instantiate different types of Validators based on a validator number.
 */
public class ValidatorFactory {


    /**
     * Creates a Validator object of the appropriate type based on the given validator number.
     * Each validator number corresponds to a specific type of Validator.
     *
     * @param validatorNumber The number that identifies the type of Validator to be created.
     * @param secretCode      The secret code to be used by the Validator for validation purposes.
     * @return A Validator object corresponding to the specified validator number.
     * @throws IllegalArgumentException If an unknown validator number is provided.
     */
    public static Validator createValidator(int validatorNumber, Code secretCode) {
        return switch (validatorNumber) {
            case 1, 2, 3, 4 -> new CompareOneDigit(validatorNumber, secretCode);
            case 5, 6, 7 -> new ParityOfDigit(validatorNumber, secretCode);
            case 8, 9, 10 -> new CountDigit(validatorNumber, secretCode);
            case 11, 12, 13 -> new CompareTwoDigits(validatorNumber, secretCode);
            case 14, 15 -> new Extremum(validatorNumber, secretCode);
            case 16 -> new MajorityEven(validatorNumber, secretCode);
            case 17 -> new CountEven(validatorNumber, secretCode);
            case 18 -> new EvenSum(validatorNumber, secretCode);
            case 19 -> new CompareSum(validatorNumber, secretCode);
            case 20 -> new CountRepetitions(validatorNumber, secretCode);
            case 21 -> new TwinDigits(validatorNumber, secretCode);
            case 22 -> new OrderOfDigits(validatorNumber, secretCode);

            default -> throw new IllegalArgumentException("Numéro de validateur inconnu.");
        };
    }


}

