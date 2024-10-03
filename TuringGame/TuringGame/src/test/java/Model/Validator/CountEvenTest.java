package Model.Validator;

import static org.junit.jupiter.api.Assertions.*;
import Model.Code;

import org.junit.jupiter.api.Test;


public class CountEvenTest {

    @Test
    void testValidator17ZeroEvenInCodeAndSecret() {
        Code codeSecret = new Code(135);
        CountEven validator = new CountEven(17, codeSecret);
        assertTrue(validator.verify(new Code(311)));
    }

    @Test
    void testValidator17OneEvenInCodeAndSecret() {
        Code codeSecret = new Code(213);
        CountEven validator = new CountEven(17, codeSecret);
        assertTrue(validator.verify(new Code(312)));
    }

    @Test
    void testValidator17TwoEvenInCodeAndSecret() {
        Code codeSecret = new Code(135);
        CountEven validator = new CountEven(17, codeSecret);
        assertFalse(validator.verify(new Code(241)));
    }

    @Test
    void testValidator17ThreeEvenInCodeAndSecret() {
        Code codeSecret = new Code(244);
        CountEven validator = new CountEven(17, codeSecret);
        assertTrue(validator.verify(new Code(242)));
    }
    @Test
    void testValidator17ThreeEvenInCodeAndSecretWrong() {
        Code codeSecret = new Code(214);
        CountEven validator = new CountEven(17, codeSecret);
        assertFalse(validator.verify(new Code(242)));
    }

    @Test
    void testInvalidValidatorNumber() {
        Code codeSecret = new Code(123);
        CountEven validator = new CountEven(99, codeSecret);
        assertThrows(IllegalArgumentException.class, () -> validator.verify(new Code(321)));
    }

    @Test
    void testStringCategoryValidator17() {
        CountEven validator = new CountEven(17, new Code(123));
        assertEquals("zero even numbers | one even number | two even numbers | three even numbers", validator.stringCategory());
    }


    @Test
    void testValidator17ZeroEvenInCodeOneEvenInSecret() {
        Code codeSecret = new Code(125);
        CountEven validator = new CountEven(17, codeSecret);
        assertFalse(validator.verify(new Code(351)));
    }

    @Test
    void testValidator17TwoEvenInCodeOneEvenInSecret() {
        Code codeSecret = new Code(245);
        CountEven validator = new CountEven(17, codeSecret);
        assertTrue(validator.verify(new Code(412)));
    }

    @Test
    void testValidator17TwoEvenInCodeTwoEvenInSecret() {
        Code codeSecret = new Code(432);
        CountEven validator = new CountEven(17, codeSecret);
        assertTrue(validator.verify(new Code(234)));
    }

    @Test
    void testValidator17OneEvenInCodeTwoEvenInSecret() {
        Code codeSecret = new Code(245);
        CountEven validator = new CountEven(17, codeSecret);
        assertFalse(validator.verify(new Code(332)));
    }

    @Test
    void testValidator17ThreeEvenInCodeTwoEvenInSecret() {
        Code codeSecret = new Code(245);
        CountEven validator = new CountEven(17, codeSecret);
        assertFalse(validator.verify(new Code(444)));
    }



}
