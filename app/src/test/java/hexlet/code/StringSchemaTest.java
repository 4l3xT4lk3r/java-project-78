package hexlet.code;

import hexlet.code.schemas.StringSchema;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class StringSchemaTest {

    private static StringSchema schema;

    @BeforeEach
    public void init() {
        schema = new Validator().string();
    }

    @Test
    public void testStringSchemaCanBeNullOrEmpty() {
        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid(null));
    }

    @Test
    public void testStringSchemaCanNotBeNullOrEmpty() {
        schema.required();
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
    }

    @Test
    public void testStringSchemaWrongData() {
        assertFalse(schema.isValid(Integer.MAX_VALUE));
    }

    @Test
    public void testStringSchemaSubstrings() {
        assertTrue(schema.isValid("what does the fox say"));
        assertTrue(schema.isValid("hexlet"));
        assertTrue(schema.contains("wh").isValid("what does the fox say"));
        assertTrue(schema.contains("what").isValid("what does the fox say"));
        assertFalse(schema.contains("whatthe").isValid("what does the fox say"));
        assertFalse(schema.contains("reason").isValid(null));
    }

    @Test
    public void testStringSchemaLength() {
        assertTrue(schema.minLength(Byte.SIZE).isValid("what does the fox say"));
        assertFalse(schema.minLength(Byte.MAX_VALUE).isValid("what does the fox say"));
        assertFalse(schema.minLength(Byte.SIZE).isValid(null));
    }

}
