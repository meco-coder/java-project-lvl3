package hexlet.code.schemas;

import java.util.function.Predicate;

public class StringSchema extends BaseSchema<Object> {

    public StringSchema() {
    }

    public final StringSchema minLength(final int stringLength) {
        Predicate<Object> isMinLength = x -> {
            if (x instanceof String) {
                return ((String) x).length() >= stringLength;
            }
            return false;
        };
        setPredicates(isMinLength);
        return this;
    }

    public final StringSchema required() {
        Predicate<Object> isRequired = x -> x instanceof String;
        setPredicates(isRequired);
        return this;
    }

    public final StringSchema contains(final String str) {
        Predicate<Object> isContains = x -> {
            if (x instanceof String) {
                return ((String) x).contains(str);
            }
            return false;
        };
        setPredicates(isContains);
        return this;
    }

}
