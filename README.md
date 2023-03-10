# #️⃣ StrEvaluate
> Dead simple, small, fast expression evaluation in **Java**.

**StrEvaluate** has no external dependencies. Releases are avalible via `Github Packages` or `.jar` file found under _"releases"_. To add and use StrEvaluate as a dependency use:
```Xml
<dependency>
  <groupId>com.h_ansen</groupId>
  <artifactId>strevaluate</artifactId>
  <version>1.0-SNAPSHOT</version>
</dependency>
```

After you have StrEvaluate as a dependency or jar evaluating expressions is as easy as:
```Java
String expression = "(1 + 10) ^ 2";
double result = new StrEvaluate.eval(expression);

String expression = "1 + 2 == 3";
boolean result = new StrEvaluate.evalAsBool(expression)
```

## Supported Operators
| **Operator** | **Name**              |
|--------------|-----------------------|
| `+`          | Plus                  |
| `-`          | Infix Minus           |
| `-n`         | Prefix Minus/Negitive |
| `*`          | Multiplication        |
| `/`          | Division              |
| `^`          | Exponential           |
| `(` and `)`  | Grouping              |
| `==`         | Equality              |
| `!=`         | Inequality            |


## Define your own functions
Functions can take in a variable number of arguments and return a result. StrEvaluate includes no built-in functions so your free to create only the functions you need!
```Java

Function MAX = new Function("max", 2) {
    public double run(double... args) {
        if (args[0] > args[1]) {
            return args[0];
        } else { 
            return args[1];
        }
    }
};

StrEvaluate evaluator = new StrEvaluate();
evaluator.addFunction(MAX);

double result = evaluator.eval("max(1, 1 + 1)"); // Returns to 2.0
```
