package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class TestValidator {


    @Test
    public void testValidator1() {
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
    public void testValidator2() {
        Validator v = new Validator();
        NumberSchema schema = v.number();
        Boolean actual1 = schema.isValid(null);
        Assertions.assertEquals(true, actual1);

        final int numPositive = 10;
        final int numNegative = -10;
        schema.required();
        Boolean actual2 = schema.isValid(null);
        Assertions.assertEquals(false, actual2);
        Boolean actual3 = schema.isValid(numPositive);
        Assertions.assertEquals(true, actual3);
        Boolean actual4 = schema.isValid("5");
        Assertions.assertEquals(false, actual4);

        Boolean actual5 = schema.positive().isValid(numPositive);
        Assertions.assertEquals(true, actual5);
        Boolean actual6 = schema.isValid(numNegative);
        Assertions.assertEquals(false, actual6);

        final int rageMin = 5;
        final int rageMax = 10;
        schema.range(rageMin, rageMax);
        Boolean actual7 = schema.isValid(rageMin);
        Assertions.assertEquals(true, actual7);
        Boolean actual8 = schema.isValid(rageMax);
        Assertions.assertEquals(true, actual8);
        Boolean actual9 = schema.isValid(0);
        Assertions.assertEquals(false, actual9);
        Boolean actual10 = schema.isValid(rageMax + rageMin);
        Assertions.assertEquals(false, actual10);

    }

    @Test
    public void testValidator3() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        Boolean actual1 = schema.isValid(null);
        Assertions.assertEquals(true, actual1);
        schema.required();
        Boolean actual2 = schema.isValid(null);
        Assertions.assertEquals(false, actual2);
        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        Boolean actual3 = schema.isValid(data);
        Assertions.assertEquals(true, actual3);
        schema.sizeof(2);
        data.put("key2", "value2");
        Boolean actual4 = schema.isValid(data);
        Assertions.assertEquals(true, actual4);
        data.put("key3", "value3");
        Boolean actual5 = schema.isValid(data);
        Assertions.assertEquals(false, actual5);
    }

    @Test
    public void testValidator4() {

        Validator v = new Validator();

        MapSchema schema = v.map();

// shape - позволяет описывать валидацию для значений объекта Map по ключам.
        Map<String, BaseSchema<Object>> schemas = new HashMap<>();
        schemas.put("name", v.string().required());
        schemas.put("age", v.number().positive());
        schema.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", 1);
        schema.isValid(human1); // true

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null); // true
        schema.isValid(human2);

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);
        schema.isValid(human3); // false

        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        human4.put("age", -1);
        schema.isValid(human4); // false
    }
}
