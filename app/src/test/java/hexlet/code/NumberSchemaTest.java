package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class NumberSchemaTest {

    private static NumberSchema schema;

    @BeforeEach
    public void init() {
        schema = new Validator().number();
    }

    @Test
    public void testNumberSchemaCanBeNull() {
        assertTrue(schema.isValid(null));
        assertTrue(schema.positive().isValid(null));
    }

    @Test
    public void testNumberSchemaCanNotBeNull() {
        schema.required();
        assertFalse(schema.isValid(null));
    }

    @Test
    public void testNumberSchemaWrongData() {
        assertFalse(schema.isValid("5"));
    }

    @Test
    public void testNumberSchemaRange() {
        assertTrue(schema.isValid(Integer.MAX_VALUE));
        schema.required().range(Integer.BYTES, Integer.SIZE);
        assertTrue(schema.isValid(Integer.BYTES));
        assertTrue(schema.isValid(Integer.SIZE));
        assertFalse(schema.isValid(Long.SIZE));
        assertFalse(schema.isValid(Long.MAX_VALUE));
    }

}
