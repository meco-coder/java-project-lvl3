package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;

public class Validator {

    public StringSchema string() {
        return new StringSchema(null);
    }

    public NumberSchema number() {
        return new NumberSchema(null);
    }

    public MapSchema<Object> map() {
        return new MapSchema<>(null);
    }
}
