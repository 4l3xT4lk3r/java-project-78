package hexlet.code.schemas;

import java.util.Objects;

public final class StringSchema extends BaseSchema {
    public StringSchema() {
        addCheck(s -> Objects.isNull(s) || s instanceof String);
    }

    public StringSchema required() {
        addCheck(Objects::nonNull);
        addCheck(s -> !s.equals(""));
        return this;
    }

    public StringSchema minLength(int length) {
        addCheck(s -> ((String) s).length() >= length);
        return this;
    }

    public StringSchema contains(String substring) {
        addCheck(s -> ((String) s).contains(substring));
        return this;
    }
}

