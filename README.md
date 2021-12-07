### Hexlet tests and linter status:
[![Actions Status](https://github.com/meco-coder/java-project-lvl3/workflows/hexlet-check/badge.svg)](https://github.com/meco-coder/java-project-lvl3/actions)
[![Maintainability](https://api.codeclimate.com/v1/badges/873b5df4a981f49afb10/maintainability)](https://codeclimate.com/github/meco-coder/java-project-lvl3/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/873b5df4a981f49afb10/test_coverage)](https://codeclimate.com/github/meco-coder/java-project-lvl3/test_coverage)
![Hexlet project](https://github.com/meco-coder/java-project-lvl3/actions/workflows/main.yml/badge.svg)


#Validator
Data validator is a library that can be used to check the correctness of any data. There are many such libraries in every language, since almost all programs work with external data that need to be checked for correctness. First of all, we are talking about the data of forms filled in by users. The project is based on the yup library. 

##Usage

`import hexlet.code.Validator;
import hexlet.code.schemas.StringSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.BaseSchema;

Validator v = new Validator();`