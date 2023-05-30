package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class BaseSchema {
    private final List<Predicate<Object>> checks = new ArrayList<>();

    public final List<Predicate<Object>> getChecks() {
        return checks;
    }

    public final void addCheck(Predicate<Object> predicate) {
        checks.add(predicate);
    }

    public final boolean isValid(Object data) {
        if (getChecks().size() == 0) {
            return true;
        }
        return getChecks().stream().allMatch(p -> p.test(data));
    }

}
