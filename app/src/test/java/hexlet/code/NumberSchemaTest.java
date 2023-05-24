package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NumberSchemaTest {
    @Test
    public void testNumberSchema() {
        NumberSchema schema = new Validator().number();
        assertTrue(schema.isValid(null));
        assertTrue(schema.positive().isValid(null));
        schema.required();
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid("5"));
        assertTrue(schema.isValid(Integer.MAX_VALUE));
        assertFalse(schema.isValid(Integer.MIN_VALUE));
        assertFalse(schema.isValid(0));
        schema.range(Integer.BYTES, Integer.SIZE);
        assertTrue(schema.isValid(Integer.BYTES));
        assertTrue(schema.isValid(Integer.SIZE));
        assertFalse(schema.isValid(Long.SIZE));
        assertFalse(schema.isValid(Long.MAX_VALUE));
    }
}
