package hexlet.code.schemas;

import java.util.Objects;

public final class NumberSchema extends BaseSchema {

    private boolean notNull = false;

    private boolean isPositive = false;

    private int[] range;

    public NumberSchema required() {
        notNull = true;
        return this;
    }

    public NumberSchema positive() {
        isPositive = true;
        return this;
    }

    public NumberSchema range(int minNumber, int maxNumber) {
        range = new int[]{minNumber, maxNumber};
        return this;
    }

    @Override
    public boolean isValid(Object data) {
        if (!Objects.isNull(data) && !(data instanceof Integer)) {
            return false;
        }
        Integer number = (data == null) ? null : (Integer) data;
        return checkNull(number) && checkPositive(number) && checkRange(number);
    }

    private boolean checkNull(Integer number) {
        return !notNull || number != null;
    }

    private boolean checkPositive(Integer number) {
        if (number == null) {
            return true;
        }
        return isPositive && number > 0;
    }

    private boolean checkRange(Integer number) {
        if (range != null && number == null) {
            return false;
        } else if (range == null) {
            return true;
        }
        return number >= range[0] && number <= range[1];
    }

}
