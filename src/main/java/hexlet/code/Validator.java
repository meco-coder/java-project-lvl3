package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;

public class Validator {
    private static String status;

    public static StringSchema string() {
        status = "string";
        return new StringSchema();
    }

    public static NumberSchema number() {
        status = "number";
        return new NumberSchema();
    }
}
