package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class StringSchema extends BaseSchema {
    private boolean dataCanBeNull = true;
    private int minStringLength = 0;
    private List<String> containsSubstring = new ArrayList<>();

    public StringSchema required() {
        dataCanBeNull = false;
        return this;
    }

    public StringSchema minLength(int length) {
        minStringLength = length;
        return this;
    }

    public StringSchema contains(String substring) {
        containsSubstring.add(substring);
        return this;
    }


    public boolean checkDataForNull(Object data) {
        return (data != null && !data.equals("")) || dataCanBeNull;
    }

    private boolean checkStringForSubstrings(String string) {
        if (containsSubstring.size() == 0) {
            return true;
        }
        return containsSubstring.stream().allMatch(string::contains);
    }


    private boolean checkStringForMinLength(String string) {
        int length = (string == null) ? 0 : string.length();
        return length >= minStringLength;
    }

    public boolean isValid(Object data) {
        if (!Objects.isNull(data) && !(data instanceof String)) {
            return false;
        }
        String string = (data == null) ? null : (String) data;
        return checkDataForNull(string) && checkStringForMinLength(string) && checkStringForSubstrings(string);
    }
}
