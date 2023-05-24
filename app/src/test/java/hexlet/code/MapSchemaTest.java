package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapSchemaTest {
    @Test
    public void testMapSchemaForNull() {
        MapSchema schema = new Validator().map();
        assertTrue(schema.isValid(null));
        schema.required();
        assertFalse(schema.isValid(null));

        assertTrue(schema.isValid(new HashMap<>()));
        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        assertTrue(schema.isValid(data));
        schema.sizeof(2);
        assertFalse(schema.isValid(data));
        data.put("key2", "value2");
        assertTrue(schema.isValid(data));

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
        schemas.put("password",  new Validator().string().required().contains("ya"));
        human5.put("password", "yatata");
        assertFalse(schema.isValid(human5));
    }
}
