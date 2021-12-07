package hexlet.code.schemas;

import java.util.function.Predicate;

public class NumberSchema extends BaseSchema<Object> {

    public NumberSchema() {
    }

    public final NumberSchema range(final Integer minNum, final Integer maxNum) {
        Predicate<Object> rangeIsValid = x -> {
            if (x instanceof Integer) {
                return (int) x >= minNum && (int) x <= maxNum;
            }
            return false;
        };
        setPredicates(rangeIsValid);
        return this;
    }

    public final NumberSchema required() {
        Predicate<Object> isRequired = x -> x instanceof Integer;
        setPredicates(isRequired);
        return this;
    }

    public final NumberSchema positive() {
        Predicate<Object> isPositive = x -> {
            if (x instanceof Integer) {
                return (int) x != 0 && (int) x > 0;
            } else {
                return x == null;
            }
        };
        setPredicates(isPositive);
        return this;
    }
}
