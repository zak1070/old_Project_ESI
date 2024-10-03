package Model.Validator;

import static org.junit.jupiter.api.Assertions.*;
import Model.Code;

import org.junit.jupiter.api.Test;


public class CountDigitTest {

    @Test
    void testValidator8NoOneInCodeAndSecret() {
        Code codeSecret = new Code(235);
        CountDigit validator = new CountDigit(8, codeSecret);
        assertTrue(validator.verify(new Code(455)));
    }

    @Test
    void testValidator8OneOneInCodeAndSecret() {
        Code codeSecret = new Code(143);
        CountDigit validator = new CountDigit(8, codeSecret);
        assertTrue(validator.verify(new Code(321)));
    }

    @Test
    void testValidator8TwoOnesInCodeAndSecret() {
        Code codeSecret = new Code(511);
        CountDigit validator = new CountDigit(8, codeSecret);
        assertTrue(validator.verify(new Code(311)));
    }

    @Test
    void testValidator8ThreeOnesInCodeAndSecret() {
        Code codeSecret = new Code(111);
        CountDigit validator = new CountDigit(8, codeSecret);
        assertTrue(validator.verify(new Code(111)));
    }

    @Test
    void testValidator9NoThreeInCodeAndSecret() {
        Code codeSecret = new Code(254);
        CountDigit validator = new CountDigit(9, codeSecret);
        assertTrue(validator.verify(new Code(421)));
    }

    @Test
    void testValidator9OneThreeInCodeAndSecret() {
        Code codeSecret = new Code(353);
        CountDigit validator = new CountDigit(9, codeSecret);
        assertTrue(validator.verify(new Code(313)));
    }

    @Test
    void testValidator9TwoThreesInCodeAndSecret() {
        Code codeSecret = new Code(353);
        CountDigit validator = new CountDigit(9, codeSecret);
        assertFalse(validator.verify(new Code(333)));
    }

    @Test
    void testValidator10NoFourInCodeAndSecret() {
        Code codeSecret = new Code(145);
        CountDigit validator = new CountDigit(10, codeSecret);
        assertFalse(validator.verify(new Code(312)));
    }

    @Test
    void testValidator10OneFourInCodeAndSecret() {
        Code codeSecret = new Code(451);
        CountDigit validator = new CountDigit(10, codeSecret);
        assertTrue(validator.verify(new Code(124)));
    }

    @Test
    void testValidator10TwoFoursInCodeAndSecret() {
        Code codeSecret = new Code(344);
        CountDigit validator = new CountDigit(10, codeSecret);
        assertFalse(validator.verify(new Code(444)));
    }

    @Test
    void testInvalidValidatorNumber() {
        Code codeSecret = new Code(123);
        CountDigit validator = new CountDigit(99, codeSecret);
        assertThrows(IllegalArgumentException.class, () -> validator.verify(new Code(321)));
    }

    @Test
    void testStringCategoryValidator8() {
        CountDigit validator = new CountDigit(8, new Code(123));
        assertEquals("aucun 1 | un 1 | deux 1 | trois 1", validator.stringCategory());
    }


    @Test
    void testStringCategoryValidator10() {
        CountDigit validator = new CountDigit(10, new Code(123));
        assertEquals("aucun 4 | un 4 | deux 4 | trois 4", validator.stringCategory());
    }
}
