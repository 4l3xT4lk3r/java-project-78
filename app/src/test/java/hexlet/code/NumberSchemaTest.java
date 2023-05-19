package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NumberSchemaTest {

    public static final int GOOD_NUMBER_1 = 10;
    public static final int BAD_NUMBER_1 = -10;
    public static final int BAD_NUMBER_2 = 0;
    public static final int BAD_NUMBER_3 = 4;
    public static final int BAD_NUMBER_4 = 11;

    public static final int MIN_NUMBER_RANGE = 5;
    public static final int MAX_NUMBER_RANGE = 10;

    @Test
    public void testNumberSchema() {
        Validator validator = new Validator();
        NumberSchema schema = validator.number();
        assertTrue(schema.isValid(null));
        assertTrue(schema.positive().isValid(null));
        schema.required();
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid("5"));
        assertTrue(schema.isValid(GOOD_NUMBER_1));
        assertFalse(schema.isValid(BAD_NUMBER_1));
        assertFalse(schema.isValid(BAD_NUMBER_2));
        schema.range(MIN_NUMBER_RANGE, MAX_NUMBER_RANGE);
        assertTrue(schema.isValid(MIN_NUMBER_RANGE));
        assertTrue(schema.isValid(MAX_NUMBER_RANGE));
        assertFalse(schema.isValid(BAD_NUMBER_3));
        assertFalse(schema.isValid(BAD_NUMBER_4));
    }
}
