import labs.util.Validator;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ValidatorTest {
    private static Validator validator;

    @BeforeClass
    public static void init() {
        validator = new Validator();
    }

    @Test
    public void testHitInArea() {
        assertTrue(validator.isHit(1, 1, 2));
        assertTrue(validator.isHit(0, -1, 2));
        assertFalse(validator.isHit(-1, 1, 3));
    }
}