package hexlet.code;

import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;

public class Validator {

    public static StringSchema string() {
        return new StringSchema(null);
    }

    public static NumberSchema number() {
        return new NumberSchema(null);
    }

    public static MapSchema map() {
        return new MapSchema<>(null);
    }
}
