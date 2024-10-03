package Model.Validator;

import static org.junit.jupiter.api.Assertions.*;
import Model.Code;
import Model.TuringException;
import org.junit.jupiter.api.Test;

public class MajorityEvenTest {

    @Test
    void testValidator16_MajorityEvenInBothCodeAndSecret() {
        Code codeSecret = new Code(242);
        MajorityEven validator = new MajorityEven(16, codeSecret);
        assertTrue(validator.verify(new Code(245)));
    }

    @Test
    void testValidator16_NoMajorityEvenInEitherCodeOrSecret() {
        Code codeSecret = new Code(135);
        MajorityEven validator = new MajorityEven(16, codeSecret);
        assertTrue(validator.verify(new Code(135)));
    }

    @Test
    void testValidator16_MajorityEvenInCodeOnly() {
        Code codeSecret = new Code(135);
        MajorityEven validator = new MajorityEven(16, codeSecret);
        assertFalse(validator.verify(new Code(214)));
    }

    @Test
    void testValidator16_MajorityEvenInSecretOnly() {
        Code codeSecret = new Code(242);
        MajorityEven validator = new MajorityEven(16, codeSecret);
        assertFalse(validator.verify(new Code(135)));
    }

    @Test
    void testValidator16_InvalidValidatorNumber() {
        Code codeSecret = new Code(123);
        MajorityEven validator = new MajorityEven(99, codeSecret);
        assertThrows(TuringException.class, () -> validator.verify(new Code(321)));
    }


    @Test
    void testValidator16_MajorityEvenInBothCodeAndSecret_Alternate() {
        Code codeSecret = new Code(122);
        MajorityEven validator = new MajorityEven(16, codeSecret);
        assertTrue(validator.verify(new Code(441)));
    }

    @Test
    void testValidator16_NoMajorityEvenInEitherCodeOrSecret_Alternate() {
        Code codeSecret = new Code(125);
        MajorityEven validator = new MajorityEven(16, codeSecret);
        assertTrue(validator.verify(new Code(134)));
    }

    @Test
    void testValidator16_MajorityEvenInCodeOnly_Alternate() {
        Code codeSecret = new Code(134);
        MajorityEven validator = new MajorityEven(16, codeSecret);
        assertFalse(validator.verify(new Code(234)));
    }

    @Test
    void testValidator16_MajorityEvenInSecretOnly_Alternate() {
        Code codeSecret = new Code(244);
        MajorityEven validator = new MajorityEven(16, codeSecret);
        assertFalse(validator.verify(new Code(231)));
    }
}
