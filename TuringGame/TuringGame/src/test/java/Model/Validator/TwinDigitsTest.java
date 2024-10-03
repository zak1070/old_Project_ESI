package Model.Validator;

import static org.junit.jupiter.api.Assertions.*;

import Model.Code;
import org.junit.jupiter.api.Test;

public class TwinDigitsTest {

    @Test
    void testValidatorCreationWithValidNumber() {
        Code codeSecret = new Code(123);
        assertDoesNotThrow(() -> new TwinDigits(21, codeSecret));
    }

    @Test
    void testValidatorCreationWithInvalidNumber() {
        Code codeSecret = new Code(123);
        assertThrows(IllegalArgumentException.class, () -> new TwinDigits(20, codeSecret));
    }

    @Test
    void testVerifyWithTwinDigitsPresent() {
        Code codeSecret = new Code(112);
        TwinDigits validator = new TwinDigits(21, codeSecret);
        assertTrue(validator.verify(new Code(221)));
    }

    @Test
    void testVerifyWithNoTwinDigits() {
        Code codeSecret = new Code(123);
        TwinDigits validator = new TwinDigits(21, codeSecret);
        assertFalse(validator.verify(new Code(321)));
    }


    @Test
    void testVerifyWithDifferentTwinDigits() {
        Code codeSecret = new Code(112);
        TwinDigits validator = new TwinDigits(21, codeSecret);
        assertTrue(validator.verify(new Code(223)));
    }

    @Test
    void testVerifyWithMoreThanTwoRepetitions() {
        Code codeSecret = new Code(111);
        TwinDigits validator = new TwinDigits(21, codeSecret);
        assertFalse(validator.verify(new Code(111)));
    }

    @Test
    void testVerifyWithNoRepetitions() {
        Code codeSecret = new Code(123);
        TwinDigits validator = new TwinDigits(21, codeSecret);
        assertFalse(validator.verify(new Code(453)));
    }

    @Test
    void testVerifyWithSameDigitRepeatedTwice() {
        Code codeSecret = new Code(224);
        TwinDigits validator = new TwinDigits(21, codeSecret);
        assertTrue(validator.verify(new Code(442)));
    }

    @Test
    void testVerifyWithDifferentOrder() {
        Code codeSecret = new Code(112);
        TwinDigits validator = new TwinDigits(21, codeSecret);
        assertTrue(validator.verify(new Code(211)));
    }

    @Test
    void testVerifyWithDifferentDigits() {
        Code codeSecret = new Code(123);
        TwinDigits validator = new TwinDigits(21, codeSecret);
        assertFalse(validator.verify(new Code(234)));
    }

    // Autres tests pour couvrir tous les scénarios possibles...

}
