package Model.Validator;
import Model.Code;

import Model.TuringException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CompareOneDigitTest {

    private Code secretCode;
    private CompareOneDigit validator;

    @BeforeEach
    void setUp() {
        // Initialisez secretCode avec le code secret pour tous les tests
        secretCode = new Code(141);
    }

    @Test
    void testValidator1WithCorrectCode() {
        validator = new CompareOneDigit(1, secretCode);
        Code userCode = new Code(111);
        assertTrue(validator.verify(userCode), "Validator 1 should return true when the first digit is 1.");
    }

    @Test
    void testValidator1WithIncorrectCode() {
        validator = new CompareOneDigit(1, secretCode);
        Code userCode = new Code(211);
        assertFalse(validator.verify(userCode), "Validator 1 should return false when the first digit is not 1.");
    }

    // Add more tests for other scenarios for Validator 1

    @Test
    void testValidator2WithCorrectCodeLessThan3() {
        validator = new CompareOneDigit(2, secretCode);
        Code userCode = new Code(211);
        assertTrue(validator.verify(userCode), "Validator 2 should return true when the first digit is less than 3.");
    }

    @Test
    void testValidator2WithCorrectCodeEquals3() {
        validator = new CompareOneDigit(2, secretCode);
        Code userCode = new Code(311);
        assertFalse(validator.verify(userCode), "Validator 2 should return true when the first digit is 3.");
    }

    @Test
    void testValidator2WithCorrectCodeMoreThan3() {
        validator = new CompareOneDigit(2, secretCode);
        Code userCode = new Code(411);
        assertFalse(validator.verify(userCode), "Validator 2 should return false when the first digit is more than 3.");
    }



    @Test
    public void test_first_equals() {
        Code secret = new Code(123);
        CompareOneDigit validator= new CompareOneDigit(1, secret);


        Code guessLessThan = new Code(134);
        assertTrue(validator.verify(guessLessThan));
    }

    @Test
    public void test_first_false() {
        Code secret = new Code(223);
        CompareOneDigit validator= new CompareOneDigit(1, secret);


        Code guessLessThan = new Code(134);
        assertFalse(validator.verify(guessLessThan));
    }

    @Test
    public void testEquals_3() {
        Code secret = new Code(253);
        CompareOneDigit validator= new CompareOneDigit(3, secret);

        Code guessEquals = new Code(253);
        assertTrue(validator.verify(guessEquals));
    }

    @Test
    public void test_wrong_code() {
        assertThrows(TuringException.class, () -> {  Code secret = new Code(789);});

    }

    @Test
    public void test_wrong_code2() {
        assertThrows(TuringException.class, () -> {new Code(216);});


    }
}

