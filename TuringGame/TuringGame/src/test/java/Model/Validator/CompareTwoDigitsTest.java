package Model.Validator;
import Model.Code;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CompareTwoDigitsTest {

    @Test
    void testValidator11YellowLessThanPurple() {
        Code codeSecret = new Code(314);
        CompareTwoDigits validator = new CompareTwoDigits(11, codeSecret);
        assertTrue(validator.verify(new Code(215)));
    }

    @Test
    void testValidator11YellowEqualToPurple() {
        Code codeSecret = new Code(123);
        CompareTwoDigits validator = new CompareTwoDigits(11, codeSecret);
        assertFalse(validator.verify(new Code(321)));
    }

    @Test
    void testValidator11BlueGreaterThanPurple() {
        Code codeSecret = new Code(512);
        CompareTwoDigits validator = new CompareTwoDigits(11, codeSecret);
        assertFalse(validator.verify(new Code(354)));
    }

    @Test
    void testValidator12BlueLessThanPurple() {
        Code codeSecret = new Code(314);
        CompareTwoDigits validator = new CompareTwoDigits(12, codeSecret);
        assertTrue(validator.verify(new Code(113)));
    }

    @Test
    void testValidator12BlueLessThanPurpleWrong() {
        Code codeSecret = new Code(514);
        CompareTwoDigits validator = new CompareTwoDigits(12, codeSecret);
        assertFalse(validator.verify(new Code(313)));
    }

    @Test
    void testValidator12YellowEqualToPurple() {
        Code codeSecret = new Code(212);
        CompareTwoDigits validator = new CompareTwoDigits(12, codeSecret);
        assertTrue(validator.verify(new Code(111)));
    }

    @Test
    void testValidator12YellowGreaterThanPurple() {
        Code codeSecret = new Code(512);
        CompareTwoDigits validator = new CompareTwoDigits(12, codeSecret);
        assertFalse(validator.verify(new Code(345)));
    }

    @Test
    void testValidator13YellowLessThanPurple() {
        Code codeSecret = new Code(314);
        CompareTwoDigits validator = new CompareTwoDigits(13, codeSecret);
        assertTrue(validator.verify(new Code(415)));
    }

    @Test
    void testValidator13YellowEqualToPurple() {
        Code codeSecret = new Code(133);
        CompareTwoDigits validator = new CompareTwoDigits(13, codeSecret);
        assertTrue(validator.verify(new Code(211)));
    }

    @Test
    void testValidator13YellowGreaterThanPurple() {
        Code codeSecret = new Code(532);
        CompareTwoDigits validator = new CompareTwoDigits(13, codeSecret);
        assertFalse(validator.verify(new Code(434)));
    }

    @Test
    void testInvalidValidatorNumber() {
        Code codeSecret = new Code(123);
        CompareTwoDigits validator = new CompareTwoDigits(99, codeSecret);
        assertThrows(IllegalArgumentException.class, () -> validator.verify(new Code(321)));
    }
}
