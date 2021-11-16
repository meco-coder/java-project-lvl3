package hexlet.code.schemas;

import java.util.Objects;

public class NumberSchema {
    private static String parameter = null;
    private static Integer min;
    private static Integer max;

    public NumberSchema(Integer min, Integer max) {
        this.min = min;
        this.max = max;
    }

    public NumberSchema() {

    }

    public NumberSchema range(Integer min, Integer max) {
        parameter = "range";
        return new NumberSchema(min, max);
    }

    public NumberSchema required() {
        parameter = "required";
        return new NumberSchema();
    }

    public NumberSchema positive() {
        parameter = "positive";
        return new NumberSchema();
    }

    public static Boolean isValid(Object num) {
        if (parameter == null) {
            return true;
        }
        switch (parameter) {
            case "required":
                return num != null && !(num instanceof String);
            case "positive":
                if (num == null) {
                    return false;
                }
                return ((int) num >= 0);
            case "range":
                if (num == null) {
                    return false;
                }
                return ((int) num >= min && (int) num <= max);
            default:
                throw new RuntimeException();
        }
    }

}
