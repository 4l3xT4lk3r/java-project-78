package hexlet.code.schemas;

public abstract class BaseSchema {
    private boolean dataCanBeNull = true;

    public final boolean isDataCanBeNull() {
        return dataCanBeNull;
    }

    protected abstract boolean isValid(Object data);

    public final BaseSchema required() {
        dataCanBeNull = false;
        return this;
    }

    /**
     * Check is data can be null.
     @param data any Object data
     @return true if dataCanBeNull is true or data != null
     */
    public boolean checkDataForNull(Object data) {
        return dataCanBeNull || data != null;
    }

}
