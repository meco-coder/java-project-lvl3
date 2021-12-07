package hexlet.code.schemas;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;


public abstract class BaseSchema<T1> {
    private final List<Predicate<Object>> predicates = new ArrayList<>();

    public final void setPredicates(Predicate<Object> predicate) {
        this.predicates.add(predicate);
    }

    public BaseSchema() {
    }

    /**
     * @param value the value that is passed to the method
     * @return return false or true
     */
    public Boolean isValid(T1 value) {
        if (predicates.size() == 0) {
            return true;
        }
        List<Boolean> resultIsValid = new ArrayList<>();
        for (Predicate<Object> result : predicates) {
            resultIsValid.add(result.test(value));
        }
        return !resultIsValid.contains(false);

    }
}
