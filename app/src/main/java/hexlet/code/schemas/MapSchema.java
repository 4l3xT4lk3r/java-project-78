package hexlet.code.schemas;

import java.util.*;
import java.util.function.Predicate;

public final class MapSchema extends BaseSchema {
    public List<Predicate<Map>> checks = new ArrayList<>();
    private Map<String, BaseSchema> valuesSchemas = new HashMap<>();

    @Override
    public boolean isValid(Object data) {
        if (!Objects.isNull(data) && !(data instanceof Map)) {
            return false;
        }
        if (checks.size() == 0) {
            return true;
        }
        Map<Object, Object> map = (data == null) ? null : (Map) data;
        return checks.stream().allMatch(p -> p.test(map));
    }

    public MapSchema required() {
        checks.add(Objects::nonNull);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> schemas) {
        Predicate<Map> p = m -> schemas.entrySet()
                .stream()
                .filter(e -> m.containsKey(e.getKey()))
                .allMatch(e -> e.getValue().isValid(m.get(e.getKey())));
        checks.add(p);
        return this;
    }

    public MapSchema sizeof(int size) {
        checks.add(m -> m.size() == size);
        return this;
    }

}
