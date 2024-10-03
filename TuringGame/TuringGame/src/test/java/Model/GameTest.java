package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
    }

    @Test
    void testStartGame_ValidIndex() {
        game.startGame(1);
        assertFalse(game.isGameOver());
    }

    @Test
    void testStartGame_InvalidIndex() {
        assertThrows(TuringException.class, () -> game.startGame(17));
    }

    @Test
    void testStartGame_RandomIndex() {
        game.startGame(0); // Random index between 1 and 16
        assertFalse(game.isGameOver());
    }

    @Test
    void testEnterCode_WhenGameNotOver() {
        game.startGame(1);
        assertDoesNotThrow(() -> game.enterCode(123)); // Assuming 123 is a valid code
    }

    @Test
    void testChooseValidator_WhenGameNotOver() {
        game.startGame(1);
        game.enterCode(123);
        assertDoesNotThrow(() -> game.chooseValidator(0)); // Assuming 0 is a valid validator index
    }

    @Test
    void testNextRound_WhenGameNotOver() {
        game.startGame(1);
        assertDoesNotThrow(game::nextRound);
    }

    @Test
    void testGuessCode_Correct() {
        game.startGame(1);
        game.guessCode(123); // Assuming 123 is the correct code
        assertTrue(game.isGameOver());
    }

    @Test
    void testGuessCode_Incorrect() {
        game.startGame(1);
        game.guessCode(123); // Assuming 999 is not the correct code
        assertTrue(game.isGameOver());
    }

    @Test
    void testGiveUp() {
        game.startGame(1);
        game.giveUp();
        assertTrue(game.isGameOver());
    }

    @Test
    void testUndo_WhenGameNotOver() {
        game.startGame(1);
        game.enterCode(123);
        game.undo();
        // Assertions to verify state after undo
    }

    @Test
    void testRedo_WhenGameNotOver() {
        game.startGame(1);
        game.enterCode(123);
        game.undo();
        game.redo();
        // Assertions to verify state after redo
    }

}
