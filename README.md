# #️⃣ StrEvaluate
> Dead simple, small, fast expression evaluation in **Java**.
```Java
// Evaluating expressions is as easy as:
String expression = "(1 + 10) ^ 2";
double result = new StrEvaluate.eval(expression);
```
## Define your own functions
```Java
// Define your own functions that can take in a variable number of arguments and return a result
Function MAX = new Function("max", 2) {
    public double run(double... args) {
        if (args[0] > args[1]) 
            return args[0];
        else 
            return args[1];
    }
};

StrEvaluate evaluator = new StrEvaluate();
evaluator.addFunction(MAX);

double result = evaluator.eval("max(1, 1 + 1)"); // Returns to 2.0
```
