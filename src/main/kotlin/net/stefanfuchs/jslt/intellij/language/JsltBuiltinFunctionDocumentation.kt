package net.stefanfuchs.jslt.intellij.language

/**
 * Documentation for JSLT built-in functions based on official documentation:
 * https://github.com/schibsted/jslt/blob/master/functions.md
 */
object JsltBuiltinFunctionDocumentation {
    
    private val functionDocs = mapOf(
        // General functions
        "contains" to "contains(element, sequence) -> boolean\nTrue if element is contained in sequence (array, string, or object)",
        "size" to "size(sequence) -> integer\nReturns the number of elements in the sequence (array, object, or string)",
        "error" to "error(message)\nHalts the transform with an error message",
        "fallback" to "fallback(arg1, arg2, ...) -> value\nReturns the first argument that is not null, [], or {}",
        "min" to "min(arg1, arg2) -> value\nReturns the argument that compares as the smallest",
        "max" to "max(arg1, arg2) -> value\nReturns the argument that compares as the largest",
        
        // Numeric functions
        "is-number" to "is-number(object) -> boolean\nTrue iff the argument is a number",
        "is-integer" to "is-integer(object) -> boolean\nTrue iff the argument is an integral number",
        "is-decimal" to "is-decimal(object) -> boolean\nTrue iff the argument is a floating-point number",
        "number" to "number(object, fallback?) -> integer|float\nConverts the argument into a number, if possible",
        "round" to "round(float) -> integer\nRounds its argument to the nearest integer",
        "floor" to "floor(float) -> integer\nRounds its argument to the nearest integer equal to or less than float",
        "ceiling" to "ceiling(float) -> integer\nRounds its argument to the nearest integer equal to or greater than float",
        "random" to "random() -> float\nReturns a random number between 0.0 (inclusive) and 1.0 (exclusive)",
        "sum" to "sum(array) -> number\nReturns the sum of all values in the array",
        "mod" to "mod(a, d) -> integer\nReturns the result of a mod d (the remainder of integer division)",
        "hash-int" to "hash-int(object) -> int\nComputes a hash code for the input and returns it as an integer",
        
        // String functions
        "is-string" to "is-string(object) -> boolean\nTrue iff the argument is a string",
        "string" to "string(object) -> string\nConverts the object to a string representation",
        "test" to "test(input, regexp) -> boolean\nTrue iff the regexp matches the input string",
        "capture" to "capture(input, regexp) -> object\nCaptures groups from the regexp match into an object",
        "split" to "split(input, regexp) -> array\nSplits the input string on the regexp into an array of strings",
        "join" to "join(array, separator) -> string\nJoins the array of values into a single string with separator",
        "lowercase" to "lowercase(string) -> string\nConverts the string to lowercase",
        "uppercase" to "uppercase(string) -> string\nConverts the string to uppercase",
        "sha256-hex" to "sha256-hex(string) -> string\nComputes SHA-256 hash of the string and returns it as hex",
        "starts-with" to "starts-with(tested, prefix) -> boolean\nTrue iff tested starts with prefix",
        "ends-with" to "ends-with(tested, suffix) -> boolean\nTrue iff tested ends with suffix",
        "from-json" to "from-json(string, fallback?) -> value\nParses a JSON string into a value",
        "to-json" to "to-json(value) -> string\nSerializes value into JSON string",
        "replace" to "replace(value, regexp, out) -> string\nReplaces matches of regexp in value with out",
        "trim" to "trim(string) -> string\nRemoves leading and trailing whitespace from string",
        "uuid" to "uuid(long, long) -> string\nCreates a UUID from two long values",
        
        // Boolean functions
        "boolean" to "boolean(value) -> boolean\nConverts the value to a boolean",
        "not" to "not(boolean) -> boolean\nReturns the logical negation of the boolean",
        "is-boolean" to "is-boolean(value) -> boolean\nTrue iff the value is a boolean",
        
        // Object functions
        "is-object" to "is-object(value) -> boolean\nTrue iff the value is an object",
        "get-key" to "get-key(object, key, fallback?) -> value\nGets the value for key from object, or fallback if not found",
        
        // Array functions
        "array" to "array(value) -> array\nConverts the value to an array",
        "is-array" to "is-array(value) -> boolean\nTrue iff the value is an array",
        "flatten" to "flatten(array) -> array\nFlattens nested arrays into a single-level array",
        "all" to "all(array) -> boolean\nTrue iff all values in array are true",
        "any" to "any(array) -> boolean\nTrue iff any value in array is true",
        "zip" to "zip(array1, array2) -> array\nCombines two arrays element-wise into pairs",
        "zip-with-index" to "zip-with-index(array) -> array\nAdds index to each element, creating [index, value] pairs",
        "index-of" to "index-of(array, value) -> integer\nReturns the index of value in array, or -1 if not found",
        
        // Time functions
        "now" to "now() -> double\nReturns current time as seconds since Unix epoch",
        "parse-time" to "parse-time(time, format, fallback?) -> double\nParses time string according to format into seconds since epoch",
        "format-time" to "format-time(timestamp, format, timezone?) -> string\nFormats timestamp (seconds since epoch) using format and timezone",
        
        // URL functions
        "parse-url" to "parse-url(url) -> object\nParses a URL string into an object with components"
    )
    
    /**
     * Get documentation for a built-in function
     */
    fun getDocumentation(functionName: String): String? {
        return functionDocs[functionName]
    }
    
    /**
     * Get short description for code completion
     */
    fun getShortDescription(functionName: String): String {
        val fullDoc = functionDocs[functionName] ?: return ""
        // Return the description part (after the signature and newline)
        val lines = fullDoc.split("\n")
        return if (lines.size > 1) lines[1] else ""
    }
    
    /**
     * Get all documented function names
     */
    fun getAllFunctionNames(): Set<String> {
        return functionDocs.keys
    }
}
