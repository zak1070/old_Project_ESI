package Model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CodeTest {

    @Test
    void testConstructor_ValidCode() {
        Code code = new Code(123);
        assertNotNull(code);
        assertEquals(123, code.getCode());
    }

    @Test
    void testConstructor_InvalidCode() {
        assertThrows(TuringException.class, () -> new Code(526));
    }

    @Test
    void testGetHundreds() {
        Code code = new Code(345);
        assertEquals(3, code.getDigit(0));
    }

    @Test
    void testGetTens() {
        Code code = new Code(345);
        assertEquals(4, code.getDigit(1));
    }

    @Test
    void testGetUnits() {
        Code code = new Code(345);
        assertEquals(5, code.getDigit(2));
    }

    @Test
    void testToString() {
        Code code = new Code(345);
        assertEquals("345", code.toString());
    }

    @Test
    void testGetDigit() {
        Code code = new Code(345);
        assertEquals(3, code.getDigit(0));
        assertEquals(4, code.getDigit(1));
        assertEquals(5, code.getDigit(2));
    }

    @Test
    void testGetDigit_InvalidPosition() {
        Code code = new Code(345);
        assertThrows(IllegalArgumentException.class, () -> code.getDigit(3));
    }

    @Test
    void testLength() {
        Code code = new Code(345);
        assertEquals(3, code.length());
    }

    // Additional tests for boundary values (e.g., 111, 555)
    @Test
    void testBoundaryValues() {
        assertDoesNotThrow(() -> new Code(111));
        assertDoesNotThrow(() -> new Code(555));
    }
}
