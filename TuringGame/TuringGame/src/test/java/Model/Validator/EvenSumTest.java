package Model.Validator;

import static org.junit.jupiter.api.Assertions.*;
import Model.Code;

import org.junit.jupiter.api.Test;


public class EvenSumTest {

    @Test
    void testValidator18EvenSumInCodeAndSecret() {
        Code codeSecret = new Code(242);
        EvenSum validator = new EvenSum(18, codeSecret);
        assertTrue(validator.verify(new Code(235)));
    }

    @Test
    void testValidator18OddSumInCodeAndSecret() {
        Code codeSecret = new Code(135);
        EvenSum validator = new EvenSum(18, codeSecret);
        assertTrue(validator.verify(new Code(144)));
    }

    @Test
    void testValidator18EvenSumInCodeOddSumInSecret() {
        Code codeSecret = new Code(135);
        EvenSum validator = new EvenSum(18, codeSecret);
        assertFalse(validator.verify(new Code(222)));
    }

    @Test
    void testValidator18OddSumInCodeEvenSumInSecret() {
        Code codeSecret = new Code(141);
        EvenSum validator = new EvenSum(18, codeSecret);
        assertFalse(validator.verify(new Code(245)));
    }

    @Test
    void testInvalidValidatorNumber() {
        Code codeSecret = new Code(123);
        EvenSum validator = new EvenSum(99, codeSecret);
        assertThrows(IllegalArgumentException.class, () -> validator.verify(new Code(321)));
    }

    @Test
    void testStringCategoryValidator18() {
        EvenSum validator = new EvenSum(18, new Code(123));
        assertEquals("the sum of digits are even | the sum of digits are not even", validator.stringCategory());
    }



    @Test
    void testValidator18OddSumInCodeOddSumInSecret() {
        Code codeSecret = new Code(135);
        EvenSum validator = new EvenSum(18, codeSecret);
        assertTrue(validator.verify(new Code(111)));
    }

    @Test
    void testValidator18EvenSumInCodeEvenSumInSecret() {
        Code codeSecret = new Code(244);
        EvenSum validator = new EvenSum(18, codeSecret);
        assertTrue(validator.verify(new Code(112)));
    }


}
