package Model;

import static org.junit.jupiter.api.Assertions.*;

import Loader.Loader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class ProblemTest {

    private Problem problem;
    private final int validProblemNumber = 1; // Adjust with a valid problem number

    @BeforeEach
    void setUp() {
        problem = new Problem(validProblemNumber);
    }

    @Test
    void testConstructor() {
        assertNotNull(problem);
        assertEquals(1, problem.getCurrentRound());
        assertFalse(problem.getValidators().isEmpty());
    }

    @Test
    void testNextRound() {
        problem.nextRound();
        assertEquals(2, problem.getCurrentRound());
    }

    @Test
    void testPreviousRound() {
        problem.nextRound();
        problem.previousRound();
        assertEquals(1, problem.getCurrentRound());
    }

    @Test
    void testPreviousRound_AtFirstRound() {
        problem.previousRound();
        assertEquals(0, problem.getCurrentRound());
    }



    @Test
    void testAddCodeAtRound_InvalidScenario() {
        problem.addCodeAtRound(123);
        problem.testAValidator(0); // Assuming this is a valid call
        assertThrows(TuringException.class, () -> problem.addCodeAtRound(234));
    }

    @Test
    void testRemoveCodeAtRound() {
        problem.addCodeAtRound(123);
        problem.removeCodeAtRound();
        assertTrue(problem.stringPreviousRoundsCodes().contains("No code entered"));
    }

    @Test
    void testGuessCode_Correct() {
        int correctCode = Loader.getCodeElementFromLine(Loader.getLineByLineNumber(validProblemNumber));
        assertTrue(problem.guessCode(correctCode));
    }

    @Test
    void testGuessCode_Incorrect() {
        assertFalse(problem.guessCode(312)); // Assuming 312 is not correct for this problem
    }

    @Test
    void testTestAValidator_Success() {
        problem.addCodeAtRound(Loader.getCodeElementFromLine(Loader.getLineByLineNumber(validProblemNumber)));
        assertTrue(problem.testAValidator(0));
    }

    @Test
    void testUntestValidator() {
        problem.addCodeAtRound(123);
        problem.testAValidator(0); // Assuming this is a valid call
        problem.untestValidator(0);
        assertFalse(problem.getValidatorsOk().contains(problem.getValidators().get(0)));
    }

    @Test
    void testGetValidators() {
        assertNotNull(problem.getValidators());
        assertFalse(problem.getValidators().isEmpty());
    }

    @Test
    void testGetValidatorsOk() {
        problem.addCodeAtRound(241);
        problem.testAValidator(0); // Assuming this is a valid call
        assertTrue(problem.getValidatorsOk().contains(problem.getValidators().get(0)));
    }

    @Test
    void testGetValidatorsFalse() {
        problem.addCodeAtRound(123);
        problem.testAValidator(0); // Assuming this is a valid call
        assertTrue(problem.getValidatorsFalse().contains(problem.getValidators().get(0)));
    }

    @Test
    void testGetNbValidatorsTested() {
        problem.addCodeAtRound(123);
        problem.testAValidator(0); // Assuming this is a valid call
        assertEquals(1, problem.getNbValidatorsTested());
    }

    @Test
    void testStringPreviousRoundsCodes() {
        problem.addCodeAtRound(123);
        assertTrue(problem.stringPreviousRoundsCodes().contains("ROUND 0: code : 123"));
    }


}

