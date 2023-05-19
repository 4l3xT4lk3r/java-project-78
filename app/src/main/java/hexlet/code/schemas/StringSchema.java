package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class StringSchema {
    private boolean notNullNotEmpty = false;
    private int minLength = 0;
    private List<String> substrings = new ArrayList<>();

    public StringSchema required() {
        notNullNotEmpty = true;
        return this;
    }

    public StringSchema minLength(int length) {
        minLength = length;
        return this;
    }

    public StringSchema contains(String substring) {
        substrings.add(substring);
        return this;
    }

    public boolean isValid(Object data) {
        if (!Objects.isNull(data) && !(data instanceof String)) {
            return false;
        }
        String string = (data == null) ? null : (String) data;
        return checkNullAndEmpty(string) && checkMinLength(string) && checkSubstrings(string);
    }

    private boolean checkSubstrings(String string) {
        if (substrings.size() == 0) {
            return true;
        }
        return substrings.stream().allMatch(string::contains);
    }

    private boolean checkNullAndEmpty(String string) {
        return !notNullNotEmpty || (string != null && !string.equals(""));
    }

    private boolean checkMinLength(String string) {
        int length = (string == null) ? 0 : string.length();
        return length >= minLength;
    }
}
