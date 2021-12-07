### Hexlet tests and linter status:
[![Actions Status](https://github.com/meco-coder/java-project-lvl3/workflows/hexlet-check/badge.svg)](https://github.com/meco-coder/java-project-lvl3/actions)
[![Maintainability](https://api.codeclimate.com/v1/badges/873b5df4a981f49afb10/maintainability)](https://codeclimate.com/github/meco-coder/java-project-lvl3/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/873b5df4a981f49afb10/test_coverage)](https://codeclimate.com/github/meco-coder/java-project-lvl3/test_coverage)
![Hexlet project](https://github.com/meco-coder/java-project-lvl3/actions/workflows/main.yml/badge.svg)


## Validator
Data validator is a library that can be used to check the correctness of any data. There are many such libraries in every language, since almost all programs work with external data that need to be checked for correctness. First of all, we are talking about the data of forms filled in by users. The project is based on the yup library.

## Usage

```java
import hexlet.code.Validator;
import hexlet.code.schemas.StringSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.BaseSchema;

Validator v = new Validator();

// String
StringSchema schema = v.string();

schema.isValid(""); // true
schema.isValid(null); // true

schema.required().contains("world");
schema.isValid(hello, world!); // true

schema.minLength(15);
schema.isValid(world); // false

//Number
NumberSchema schema = v.number();

schema.isValid(null); // true

schema.required();

schema.isValid(null); // false
schema.isValid(10) // true
schema.isValid("5"); // false

schema.positive().isValid(10); // true
schema.isValid(-10); // false

schema.range(-5, 10);

schema.isValid(-5); // false
schema.isValid(10); // true
schema.isValid(1); // true
schema.isValid(11); // false

//Map object with structure validation support 

Map<String, BaseSchema> schemas = new HashMap<>();
schemas.put("name", v.string().required());
schemas.put("age", v.number().positive());

MapSchema schema = v.map().sizeof(2).shape(schemas);

Map<String, Object> human1 = new HashMap<>();
human1.put("name", "Kolya");
human1.put("age", 100);
schema.isValid(human1); // true

Map<String, Object> human2 = new HashMap<>();
human2.put("name", "");
human2.put("age", null);
schema.isValid(human1); // false
```