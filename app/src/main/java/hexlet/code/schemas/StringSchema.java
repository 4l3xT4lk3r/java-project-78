package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;


public final class StringSchema extends BaseSchema {
    private List<Predicate<String>> checks = new ArrayList<>();
    @Override
    public boolean isValid(Object data) {
        if (!(data instanceof String) && !Objects.isNull(data)) {
            return false;
        }
        if (checks.size() == 0) {
            return true;
        }
        return checks.stream().allMatch(p -> p.test((String) data));
    }

    public StringSchema required() {
        checks.add(Objects::nonNull);
        checks.add(s -> !s.equals(""));
        return this;
    }

    public StringSchema minLength(int length) {
        checks.add(s -> s.length() >= length);
        return this;
    }

    public StringSchema contains(String substring) {
        checks.add(s -> s.contains(substring));
        return this;
    }
}

