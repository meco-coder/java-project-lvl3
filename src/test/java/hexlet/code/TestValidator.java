package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestValidator {


    @Test
    public void TestValidator1() {
        Validator v = new Validator();
        StringSchema schema = v.string();

        Boolean actual9 = schema.isValid("");
        Assertions.assertEquals(true, actual9);
        Boolean actual10 = schema.isValid(null);
        Assertions.assertEquals(true, actual10);

        schema.minLength(2);
        Boolean actual1 = schema.isValid("");
        Assertions.assertEquals(false, actual1);
        Boolean actual2 = schema.isValid(null);
        Assertions.assertEquals(false, actual2);

        schema.required();
        Boolean actual3 = schema.isValid("Hello, world!");
        Assertions.assertEquals(true, actual3);
        Boolean actual4 = schema.isValid("hexlet");
        Assertions.assertEquals(true, actual4);
        Boolean actual5 = schema.isValid(null);
        Assertions.assertEquals(false, actual5);
        Boolean actual6 = schema.isValid("");
        Assertions.assertEquals(false, actual6);

        Boolean actual7 = schema.contains("world").isValid("Hello, world!");
        Assertions.assertEquals(true, actual7);
        Boolean actual8 = schema.contains("Worlds").isValid("Hello, world!");
        Assertions.assertEquals(false, actual8);

    }

    @Test
    public void TestValidator2() {
        Validator v = new Validator();
        NumberSchema schema = v.number();
        Boolean actual1 = schema.isValid(null);
        Assertions.assertEquals(true, actual1);

        schema.required();
        Boolean actual2 = schema.isValid(null);
        Assertions.assertEquals(false, actual2);
        Boolean actual3 = schema.isValid(10);
        Assertions.assertEquals(true, actual3);
        Boolean actual4 = schema.isValid("5");
        Assertions.assertEquals(false, actual4);

        Boolean actual5 = schema.positive().isValid(10);
        Assertions.assertEquals(true, actual5);
        Boolean actual6 = schema.isValid(-10);
        Assertions.assertEquals(false, actual6);

        schema.range(5, 10);
        Boolean actual7 = schema.isValid(5);
        Assertions.assertEquals(true, actual7);
        Boolean actual8 = schema.isValid(10);
        Assertions.assertEquals(true, actual8);
        Boolean actual9 = schema.isValid(4);
        Assertions.assertEquals(false, actual9);
        Boolean actual10 = schema.isValid(11);
        Assertions.assertEquals(false, actual10);

    }
}
