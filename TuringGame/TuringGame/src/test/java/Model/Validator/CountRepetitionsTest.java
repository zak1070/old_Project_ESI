package Model.Validator;

import static org.junit.jupiter.api.Assertions.*;
import Model.Code;

import Model.TuringException;
import org.junit.jupiter.api.Test;


public class CountRepetitionsTest {

    @Test
    void testValidator20NoDoubleInCodeAndSecret() {
        Code codeSecret = new Code(134);
        CountRepetitions validator = new CountRepetitions(20, codeSecret);
        assertTrue(validator.verify(new Code(542)));
    }

    @Test
    void testValidator20DoubleInCodeAndSecret() {
        Code codeSecret = new Code(112);
        CountRepetitions validator = new CountRepetitions(20, codeSecret);
        assertTrue(validator.verify(new Code(211)));
    }

    @Test
    void testValidator20TripleInCodeAndSecret() {
        Code codeSecret = new Code(111);
        CountRepetitions validator = new CountRepetitions(20, codeSecret);
        assertTrue(validator.verify(new Code(111)));
    }

    @Test
    void testValidator20NoDoubleInCodeOneDoubleInSecret() {
        Code codeSecret = new Code(112);
        CountRepetitions validator = new CountRepetitions(20, codeSecret);
        assertFalse(validator.verify(new Code(542)));
    }

    @Test
    void testValidator20TripleInCodeOneDoubleInSecret() {
        Code codeSecret = new Code(112);
        CountRepetitions validator = new CountRepetitions(20, codeSecret);
        assertFalse(validator.verify(new Code(111)));
    }

    @Test
    void testValidator20TripleInCodeTwoDoubleInSecret() {
        Code codeSecret = new Code(112);
        CountRepetitions validator = new CountRepetitions(20, codeSecret);
        assertFalse(validator.verify(new Code(111)));
    }

    @Test
    void testValidator20NoDoubleInCodeTwoDoubleInSecret() {
        Code codeSecret = new Code(112);
        CountRepetitions validator = new CountRepetitions(20, codeSecret);
        assertFalse(validator.verify(new Code(213)));
    }

    @Test
    void testInvalidValidatorNumber() {
        Code codeSecret = new Code(123);

        assertThrows(TuringException.class, () ->new CountRepetitions(99, codeSecret));
    }

    @Test
    void testStringCategoryValidator20() {
        CountRepetitions validator = new CountRepetitions(20, new Code(123));
        assertEquals("no double | double digit | triple digit |", validator.stringCategory());
    }
}
