package Model.Validator;
import Model.Code;
import Model.TuringException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CompareSumTest {


    @Test
    void testCompareSumWithLowerValue() {
        Code code = new Code(153);
        CompareSum compareSum = new CompareSum(19, code);
        assertFalse(compareSum.verify(new Code(212)));
    }

    @Test
    void testCompareSumWithEqualValue() {
        Code code = new Code(345);
        CompareSum compareSum = new CompareSum(19, code);
        assertTrue(compareSum.verify(new Code(251)));
    }

    @Test
    void testCompareSumWithHigherValue() {
        Code code = new Code(432);
        CompareSum compareSum = new CompareSum(19, code);
        assertFalse(compareSum.verify(new Code(111)));
    }

    @Test
    void testInvalidValidatorNumber() {
        Code code = new Code(543);

        assertThrows(TuringException.class, () ->new CompareSum(99, code));
    }

    @Test
    void testCompareSumWithFixedValue() {
        Code code = new Code(155);
        CompareSum compareSum = new CompareSum(19, code);
        assertTrue(compareSum.verify(new Code(243)));
    }

    @Test
    void testCompareSumWithFixedValueHigher() {
        Code code = new Code(111);
        CompareSum compareSum = new CompareSum(19, code);
        assertFalse(compareSum.verify(new Code(453)));
    }

    @Test
    void testCompareSumWithInvalidCode() {
        Code code = new Code(222);

        assertThrows(TuringException.class, () ->   new CompareSum(10, code));
    }

    @Test
    void testCompareSumWithMinimumValues() {
        Code code = new Code(111);
        CompareSum compareSum = new CompareSum(19, code);
        assertTrue( compareSum.verify(new Code(111)));
    }

    @Test
    void testCompareSumWithMaximumValues() {
        Code code = new Code(555);
        CompareSum compareSum = new CompareSum(19, code);
        assertTrue( compareSum.verify(new Code(555)));
    }

    @Test
    void testCompareSumWithRandomValues() {
        Code code = new Code(252);
        CompareSum compareSum = new CompareSum(19, code);
        assertTrue(compareSum.verify(new Code(433)));
    }

}
