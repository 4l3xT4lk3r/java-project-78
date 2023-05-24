package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

public final class MapSchema extends BaseSchema {
    private int mapSize = -1;
    private Map<String, BaseSchema> valuesSchemas = new HashMap<>();

    @Override
    public boolean isValid(Object data) {
        if (!Objects.isNull(data) && !(data instanceof Map)) {
            return false;
        }
        Map<Object, Object> map = (data == null) ? null : (Map) data;
        return checkDataForNull(map) && checkMapForSize(map) && checkMapValuesForSchemas(map);
    }

    public MapSchema shape(Map<String, BaseSchema> schemas) {
        this.valuesSchemas = schemas;
        return this;
    }

    public MapSchema sizeof(int size) {
        this.mapSize = size;
        return this;
    }

    private boolean checkMapForSize(Map<Object, Object> data) {
        if (mapSize == -1) {
            return true;
        }
        int size = (data == null) ? 0 : data.size();
        return size == mapSize;
    }

    private boolean checkMapValuesForSchemas(Map<Object, Object> data) {
        if (valuesSchemas.size() == 0) {
            return true;
        }
        for (Map.Entry<String, BaseSchema> entry : valuesSchemas.entrySet()) {
            boolean res = Stream.of(data).map(m -> m.get(entry.getKey())).allMatch(o -> entry.getValue().isValid(o));
            if (!res) {
                return false;
            }
        }
        return true;
    }
}
