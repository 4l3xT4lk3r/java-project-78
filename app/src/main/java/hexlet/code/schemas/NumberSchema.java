package hexlet.code.schemas;

import java.util.Objects;

public final class NumberSchema extends BaseSchema {

    public NumberSchema() {
        addCheck((s -> Objects.isNull(s) || s instanceof Integer));
    }

    public NumberSchema positive() {
        addCheck(i -> Objects.isNull(i) || (Integer) i > 0);
        return this;
    }

    public NumberSchema required() {
        super.required();
        return this;
    }

    public NumberSchema range(int minNumber, int maxNumber) {
        addCheck(i -> Objects.nonNull(i) && (Integer) i >= minNumber && (Integer) i <= maxNumber);
        return this;
    }

}
