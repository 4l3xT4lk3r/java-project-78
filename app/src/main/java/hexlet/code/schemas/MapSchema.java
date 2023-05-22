package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;

public final class MapSchema extends BaseSchema {

    private boolean isDataCanBeNull = true;

    private int mapSize = -1;

    @Override
    public boolean isValid(Object data) {
        if (!Objects.isNull(data) && !(data instanceof Map)) {
            return false;
        }
        Map map = (data == null) ? null : (Map) data;
        return checkDataNullness(map) && checkMapSize(map);
    }

    public MapSchema required() {
        isDataCanBeNull = false;
        return this;
    }

    public MapSchema sizeof(int size) {
        this.mapSize = size;
        return this;
    }


    private boolean checkDataNullness(Map data) {
        return isDataCanBeNull || data != null;
    }

    private boolean checkMapSize(Map data) {
        if (mapSize == -1) {
            return true;
        }
        int size = (data == null) ? 0 : data.size();
        return size == mapSize;
    }
}
