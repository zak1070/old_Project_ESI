package Model;


import Model.Validator.Validator;
import Model.Validator.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RoundTest {

    private Round round;
    private Code secretCode; // Adjust as per your Code class

    @BeforeEach
    void setUp() {
        round = new Round();
        secretCode = new Code(134); // Example code, modify as needed
    }

    @Test
    void testConstructor() {
        assertNotNull(round);
        assertTrue(round.getTestedValidatorsOk().isEmpty());
        assertTrue(round.getTestedValidatorsFalse().isEmpty());
        assertEquals(0, round.getTestedValidatorsCount());
    }

    @Test
    void testAddCode_Success() {
        round.addCode(123);
        assertEquals(123, round.getCurrentCodeValue());
    }

    @Test
    void testAddCode_AfterValidatorTest() {
        round.addCode(123);
        Validator validator = ValidatorFactory.createValidator(1, secretCode);
        round.testValidator(validator);
        assertThrows(TuringException.class, () -> round.addCode(456));
    }

    @Test
    void testRemoveCode_Success() {
        round.addCode(123);
        round.removeCode();
        assertThrows(TuringException.class, round::getCurrentCodeValue);
    }

    @Test
    void testValidator_Success() {
        round.addCode(secretCode.getCode());
        Validator validator = ValidatorFactory.createValidator(1, secretCode);
        assertTrue(round.testValidator(validator));
        assertTrue(round.getTestedValidatorsOk().contains(validator));
        assertEquals(1, round.getTestedValidatorsCount());
    }

    @Test
    void testValidator_Failure() {
        int userCode= 231;
        round.addCode(userCode);
        Validator validator = ValidatorFactory.createValidator(5, secretCode);
        assertFalse(round.testValidator(validator));
        assertTrue(round.getTestedValidatorsFalse().contains(validator));
        assertEquals(1, round.getTestedValidatorsCount());
    }

    @Test
    void testValidator_ExceedsLimit() {
        round.addCode(secretCode.getCode());
        for (int i = 1; i <= 3; i++) {
            Validator validator = ValidatorFactory.createValidator(i, secretCode);
            round.testValidator(validator);
        }
        Validator anotherValidator = ValidatorFactory.createValidator(4, secretCode);
        assertThrows(TuringException.class, () -> round.testValidator(anotherValidator));
    }

    @Test
    void testUntestValidator_Successful() {
        round.addCode(secretCode.getCode());
        Validator validator = ValidatorFactory.createValidator(1, secretCode);
        round.testValidator(validator);
        round.untestValidator(validator);
        assertFalse(round.getTestedValidatorsOk().contains(validator));
        assertEquals(0, round.getTestedValidatorsCount());
    }

    @Test
    void testUntestValidator_NoValidatorsTested() {
        Validator validator = ValidatorFactory.createValidator(1, secretCode);
        assertThrows(TuringException.class, () -> round.untestValidator(validator));
    }

    @Test
    void testGetCurrentCodeValue_CodeSet() {
        round.addCode(secretCode.getCode());
        assertEquals(secretCode.getCode(), round.getCurrentCodeValue());
    }

    @Test
    void testGetCurrentCodeValue_NoCode() {
        assertThrows(TuringException.class, round::getCurrentCodeValue);
    }
}
