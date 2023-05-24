package hexlet.code.schemas;

import java.util.Objects;

public final class NumberSchema extends BaseSchema {

    private boolean dataCanBeNull = true;

    private boolean numberMustBePositive = false;

    private int[] withinRange;

    public NumberSchema positive() {
        numberMustBePositive = true;
        return this;
    }

    public NumberSchema required() {
        dataCanBeNull = false;
        return this;
    }

    public boolean checkDataForNull(Object data) {
        return dataCanBeNull || data != null;
    }

    public NumberSchema range(int minNumber, int maxNumber) {
        withinRange = new int[]{minNumber, maxNumber};
        return this;
    }

    @Override
    public boolean isValid(Object data) {
        if (!Objects.isNull(data) && !(data instanceof Integer)) {
            return false;
        }
        Integer number = (data == null) ? null : (Integer) data;
        return checkDataForNull(number) && checkNumberForPositive(number) && checkNumberForRange(number);
    }

    private boolean checkNumberForPositive(Integer number) {
        if (number == null) {
            return true;
        }
        return !numberMustBePositive || number > 0;
    }

    private boolean checkNumberForRange(Integer number) {
        if (withinRange != null && number == null) {
            return false;
        } else if (withinRange == null) {
            return true;
        }
        return number >= withinRange[0] && number <= withinRange[1];
    }

}
