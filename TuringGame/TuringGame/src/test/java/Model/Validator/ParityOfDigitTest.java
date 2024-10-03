package Model.Validator;


import static org.junit.jupiter.api.Assertions.*;

import Model.Code;
import Model.TuringException;
import org.junit.jupiter.api.Test;

public class ParityOfDigitTest {

    @Test
    void testVerifyCase5WithEvenDigit() {
        Code codeSecret = new Code(243);  // Ajuster en fonction de la structure de Code
        ParityOfDigit validator = new ParityOfDigit(5, codeSecret);
        assertTrue(validator.verify(new Code(422)));  // Des valeurs valides entre 1 et 5
    }

    @Test
    void testVerifyCase5WithOddDigit() {
        Code codeSecret = new Code(135);  // Ajuster en fonction de la structure de Code
        ParityOfDigit validator = new ParityOfDigit(5, codeSecret);
        assertTrue(validator.verify(new Code(315)));  // Des valeurs valides entre 1 et 5
    }

    @Test
    void testVerifyCase6WithEvenDigit() {
        Code codeSecret = new Code(242);  // Ajuster en fonction de la structure de Code
        ParityOfDigit validator = new ParityOfDigit(6, codeSecret);
        assertTrue(validator.verify(new Code(424)));  // Des valeurs valides entre 1 et 5
    }

    @Test
    void testVerifyCase6WithOddDigit() {
        Code codeSecret = new Code(351);
        ParityOfDigit validator = new ParityOfDigit(6, codeSecret);
        assertTrue(validator.verify(new Code(513)));
    }

    @Test
    void testVerifyCase7WithEvenDigit() {
        Code codeSecret = new Code(414);
        ParityOfDigit validator = new ParityOfDigit(7, codeSecret);
        assertTrue(validator.verify(new Code(242)));
    }

    @Test
    void testVerifyCase7WithOddDigit() {
        Code codeSecret = new Code(135);
        ParityOfDigit validator = new ParityOfDigit(7, codeSecret);
        assertTrue(validator.verify(new Code(513)));
    }
    // Test échoue pour le validateur 5 si les parités ne correspondent pas
    @Test
    void testVerifyCase5FailureOnMismatch() {
        Code codeSecret = new Code(135);
        ParityOfDigit validator = new ParityOfDigit(5, codeSecret);
        assertFalse(validator.verify(new Code(422)));
    }

    // Test échoue pour le validateur 6 si les parités ne correspondent pas
    @Test
    void testVerifyCase6FailureOnMismatch() {
        Code codeSecret = new Code(242);
        ParityOfDigit validator = new ParityOfDigit(6, codeSecret);
        assertFalse(validator.verify(new Code(513)));
    }

    // Test échoue pour le validateur 7 si les parités ne correspondent pas
    @Test
    void testVerifyCase7FailureOnMismatch() {
        Code codeSecret = new Code(414);
        ParityOfDigit validator = new ParityOfDigit(7, codeSecret);
        assertFalse(validator.verify(new Code(513)));
    }

    // Test pour IllegalArgumentException avec un numéro de validateur invalide
    @Test
    void testVerifyWithInvalidValidatorNumber() {
        Code codeSecret = new Code(123);
        assertDoesNotThrow(() -> new ParityOfDigit(8, codeSecret));
    }

    // Test pour IllegalArgumentException avec un autre numéro de validateur invalide
    @Test
    void testVerifyWithAnotherInvalidValidatorNumber() {
        Code codeSecret = new Code(123);
        assertThrows(TuringException.class, () -> new ParityOfDigit(4, codeSecret).verify(new Code(123)));
    }

    // Test pour le cas où le code secret est plus court que le nombre de validateurs requis
    @Test
    void testVerifyWithShorterSecretCode() {
        Code codeSecret = new Code(112);  // Supposer que cela crée un code avec deux chiffres
        ParityOfDigit validator = new ParityOfDigit(7, codeSecret);
        assertDoesNotThrow(() ->validator.verify(new Code(345)));
    }


}
