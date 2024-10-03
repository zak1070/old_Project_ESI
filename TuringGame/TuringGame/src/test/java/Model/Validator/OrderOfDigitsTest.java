package Model.Validator;

import static org.junit.jupiter.api.Assertions.*;
import Model.Code;
import Model.TuringException;

import org.junit.jupiter.api.Test;


public class OrderOfDigitsTest {

    @Test
    void testValidator22AscendingOrder() {
        Code codeSecret = new Code(123);
        OrderOfDigits validator = new OrderOfDigits(22, codeSecret);
        assertTrue(validator.verify(new Code(345)));
    }

    @Test
    void testValidator22DescendingOrder() {
        Code codeSecret = new Code(543);
        OrderOfDigits validator = new OrderOfDigits(22, codeSecret);
        assertTrue(validator.verify(new Code(321)));
    }

    @Test
    void testValidator22NoSpecificOrder() {
        Code codeSecret = new Code(312);
        OrderOfDigits validator = new OrderOfDigits(22, codeSecret);
        assertTrue(validator.verify(new Code(213)));
    }

    @Test
    void testValidator22InvalidOrder() {
        Code codeSecret = new Code(123);
        OrderOfDigits validator = new OrderOfDigits(22, codeSecret);
        assertFalse(validator.verify(new Code(321)));
    }

    @Test
    void testInvalidValidatorNumber() {
        Code codeSecret = new Code(123);
        assertThrows(TuringException.class, () -> new OrderOfDigits(99, codeSecret));
    }

    @Test
    void testStringCategoryValidator22() {
        OrderOfDigits validator = new OrderOfDigits(22, new Code(123));
        assertNull(validator.stringCategory());
    }

    @Test
    void testValidatorWithIdenticalCodes() {
        Code codeSecret = new Code(234);
        OrderOfDigits validator = new OrderOfDigits(22, codeSecret);
        assertTrue(validator.verify(new Code(234)));
    }
    @Test
    void testValidatorWithDifferentCodes() {
        Code codeSecret = new Code(235);
        OrderOfDigits validator = new OrderOfDigits(22, codeSecret);
        assertTrue(validator.verify(new Code(123)));
    }
    @Test
    void testValidatorWithRepeatedDigitsInSecretCode() {
        Code codeSecret = new Code(245);
        OrderOfDigits validator = new OrderOfDigits(22, codeSecret);
        assertTrue(validator.verify(new Code(123)));
    }
    @Test
    void testValidatorWithRepeatedDigitsInInputCode() {
        Code codeSecret = new Code(123);
        OrderOfDigits validator = new OrderOfDigits(22, codeSecret);
        assertFalse(validator.verify(new Code(122)));
    }



}
