package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;

public final class MapSchema extends BaseSchema {

    public MapSchema() {
        addCheck(s -> Objects.isNull(s) || s instanceof Map<?, ?>);
    }

    public MapSchema required() {
        super.required();
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> schemas) {
        addCheck(m -> Objects.nonNull(m) && Objects.nonNull(schemas) && schemas.entrySet()
                .stream()
                .filter(e -> ((Map) m).containsKey(e.getKey()))
                .allMatch(e -> e.getValue().isValid(((Map) m).get(e.getKey()))));
        return this;
    }

    public MapSchema sizeof(int size) {
        addCheck(m -> Objects.nonNull(m) && ((Map) m).size() == size);
        return this;
    }

}
