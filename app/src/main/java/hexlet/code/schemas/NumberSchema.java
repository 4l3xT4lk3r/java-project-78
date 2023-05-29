package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public final class NumberSchema extends BaseSchema {

    public List<Predicate<Integer>> checks = new ArrayList<>();

    public NumberSchema positive() {
        checks.add(i -> Objects.isNull(i) || i > 0);
        return this;
    }

    public NumberSchema required() {
        checks.add(Objects::nonNull);
        return this;
    }

    public NumberSchema range(int minNumber, int maxNumber) {
        checks.add(i -> Objects.nonNull(i) && i >= minNumber && i <= maxNumber);
        return this;
    }

    @Override
    public boolean isValid(Object data) {
        if (!Objects.isNull(data) && !(data instanceof Integer)) {
            return false;
        }
        if (checks.size() == 0) {
            return true;
        }
        Integer number = (data == null) ? null : (Integer) data;
        return checks.stream().allMatch(p -> p.test(number));
    }

}
