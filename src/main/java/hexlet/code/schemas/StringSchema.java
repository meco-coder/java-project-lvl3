package hexlet.code.schemas;

import java.util.function.Predicate;

public class StringSchema extends BaseSchema<Object> {

    public StringSchema() {
    }

    public final StringSchema minLength(final int stringLength) {
        final Predicate<Object> minLength = x -> {
            if (x instanceof String) {
                return ((String) x).length() >= stringLength;
            }
            return false;
        };
        setPredicates(minLength);
        return this;
    }

    public final StringSchema required() {
        final Predicate<Object> isRequired = x -> x instanceof String && ((String) x).length() != 0;
        setPredicates(isRequired);
        return this;
    }

    public final StringSchema contains(final String str) {
        final Predicate<Object> contains = x -> {
            if (x instanceof String) {
                return ((String) x).contains(str);
            }
            return false;
        };
        setPredicates(contains);
        return this;
    }

}
