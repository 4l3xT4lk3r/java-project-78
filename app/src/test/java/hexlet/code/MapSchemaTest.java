package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class MapSchemaTest {
    private static MapSchema schema;

    @BeforeEach
    public void init() {
        schema = new Validator().map();
    }

    @Test
    public void testMapSchemaCanBeNull() {
        assertTrue(schema.isValid(null));
        assertFalse(schema.sizeof(2).isValid(null));
    }

    @Test
    public void testMapSchemaCanNotBeNull() {
        schema.required();
        assertFalse(schema.isValid(null));
    }

    @Test
    public void testMapSchemaCertainSize() {
        assertTrue(schema.isValid(new HashMap<>()));
        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        assertTrue(schema.isValid(data));
        schema.sizeof(2);
        assertFalse(schema.isValid(data));
        data.put("key2", "value2");
        assertTrue(schema.isValid(data));
    }

    @Test
    public void testMapSchemaValidShapes() {
        Map<String, BaseSchema> schemas = new HashMap<>();
        schema.shape(schemas);

        schemas.put("name", new Validator().string().required());
        schemas.put("age", new Validator().number().positive());

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", Integer.SIZE);
        assertTrue(schema.isValid(human1));

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);
        assertTrue(schema.isValid(human2));

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);
        assertFalse(schema.isValid(human3));

        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        human4.put("age", Integer.MIN_VALUE);
        assertFalse(schema.isValid(human4));

        Map<String, Object> human5 = new HashMap<>();
        human5.put("name", "Valya");
        human5.put("age", Integer.MAX_VALUE);
        schemas.put("password", new Validator().string().required().contains("ya"));
        human5.put("password", "yatata");
        assertTrue(schema.isValid(human5));
    }

    @Test
    public void testMapSchemaNullShapes() {
        schema.shape(null);
        assertFalse(schema.isValid(null));
        Map<String, Object> kernel = new HashMap<>();
        kernel.put("version", "5.4");
        assertFalse(schema.isValid(kernel));
    }
}
