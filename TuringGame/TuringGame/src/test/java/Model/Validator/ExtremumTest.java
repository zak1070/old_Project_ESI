package Model.Validator;

import static org.junit.jupiter.api.Assertions.*;
import Model.Code;

import org.junit.jupiter.api.Test;


public class ExtremumTest {

    @Test
    void testValidator14BlueIsSmallerThanBothPurpleAndYellow() {
        Code codeSecret = new Code(124);
        Extremum validator = new Extremum(14, codeSecret);
        assertFalse(validator.verify(new Code(325)));
    }
    @Test
    void testValidator14Ok() {
        Code codeSecret = new Code(234);
        Extremum validator = new Extremum(15, codeSecret);
        assertTrue(validator.verify(new Code(315)));
    }

    @Test
    void testValidator14WithDuplicates() {
        Code codeSecret = new Code(232);
        Extremum validator = new Extremum(15, codeSecret);
        assertFalse(validator.verify(new Code(315)));
    }
    @Test
    void testValidator14YellowIsSmallerThanBothBlueAndPurple() {
        Code codeSecret = new Code(432);
        Extremum validator = new Extremum(14, codeSecret);
        assertTrue(validator.verify(new Code(251)));
    }

    @Test
    void testValidator14PurpleIsSmallerThanBothBlueAndYellow() {
        Code codeSecret = new Code(315);
        Extremum validator = new Extremum(14, codeSecret);
        assertTrue(validator.verify(new Code(412)));
    }

    @Test
    void testValidator14NoExtremumInCodeAndSecret() {
        Code codeSecret = new Code(111);
        Extremum validator = new Extremum(14, codeSecret);
        assertTrue(validator.verify(new Code(111)));
    }

    @Test
    void testValidator15BlueIsLargerThanBothPurpleAndYellow() {
        Code codeSecret = new Code(432);
        Extremum validator = new Extremum(15, codeSecret);
        assertTrue(validator.verify(new Code(321)));
    }

    @Test
    void testValidator15YellowIsLargerThanBothBlueAndPurple() {
        Code codeSecret = new Code(124);
        Extremum validator = new Extremum(15, codeSecret);
        assertTrue(validator.verify(new Code(325)));
    }

    @Test
    void testValidator15PurpleIsLargerThanBothBlueAndYellow() {
        Code codeSecret = new Code(315);
        Extremum validator = new Extremum(15, codeSecret);
        assertTrue(validator.verify(new Code(224)));
    }

    @Test
    void testValidator15NoExtremumInCodeAndSecret() {
        Code codeSecret = new Code(111);
        Extremum validator = new Extremum(15, codeSecret);
        assertTrue(validator.verify(new Code(111)));
    }

    @Test
    void testInvalidValidatorNumber() {
        Code codeSecret = new Code(123);
        Extremum validator = new Extremum(99, codeSecret);
        assertThrows(IllegalArgumentException.class, () -> validator.verify(new Code(321)));
    }

    @Test
    void testStringCategoryValidator14() {
        Extremum validator = new Extremum(14, new Code(123));
        assertEquals("blue is smaller than both purple and yellow | "
                + "yellow is smaller than both blue and purple | "
                + "purple is smaller than both blue and yellow", validator.stringCategory());
    }

    @Test
    void testStringCategoryValidator15() {
        Extremum validator = new Extremum(15, new Code(123));
        assertEquals("blue is larger than both purple and yellow | "
                + "yellow is larger than both blue and purple | "
                + "purple is larger than both blue and yellow", validator.stringCategory());
    }
}
