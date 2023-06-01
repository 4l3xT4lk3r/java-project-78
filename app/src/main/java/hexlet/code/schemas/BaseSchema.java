package hexlet.code.schemas;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public abstract class BaseSchema {
    private final List<Predicate<Object>> checks = new ArrayList<>();

    final void addCheck(Predicate<Object> predicate) {
        checks.add(predicate);
    }

    /**
     * Add predicate for checking that data is not null.
     * @return reference to the current object
     */
    public BaseSchema required() {
        addCheck(Objects::nonNull);
        return this;
    }

    public final boolean isValid(Object data) {
        return checks.stream().allMatch(p -> p.test(data));
    }

}
