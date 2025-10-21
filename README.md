# JSLT IntelliJ Language Plugin

## Features

* Syntax Highlighting
  * color by token type
  * differentiate global/local/parameter variables
  * differentiate buildin/def functions
  * highlight JSLT in YAML multiline text (">") if first line of text start with `// JSLT`
* Line comment code 
* Parenthesis Matching
* Code Folding
* Code Auto Format (Ctrl+Alt+L)
* Code Navigation
  * Code Outline
  * Goto imported File
  * Jump to variable declaration
  * Jump to function declaration
  * Show variable usages
  * Show function usages
* Refactoring: rename variables/parameter/functions
* Code Completion
  * keyword suggestions (let, if, else, for, import, def, etc.)
  * built-in functions with inline documentation (40+ functions)
  * user-defined functions with parameter count
  * meaningful error messages for invalid tokens and parse errors
* Validations
  * error for non-existing file referenced in import
  * error for non-existing import alias referenced by function
  * error for non-existing function referenced via import alias
  * error for undeclared variables
  * error for duplicate declared variables
  * error for duplicate declared functions
  * error for duplicate function parameters
  * error for duplicate declared import aliases
  * error for duplicate key in object
  * error if number of parameters in function call does not match function signature
  * warn for call of unknown function
  * warn on unused import alias
  * warn on unused variable declaration
  * warn on unused function declaration
  * warn on unused function parameter
* JSLT Execution
  * dialog box that allows to select/write an input JSON, a JSLT transformation and shows the output
  * "Execute JSLT Transformation" action in context menu
  * "Execute JSLT With..." submenu showing available JSON/JSLT files in the project (grouped by directory)

## ToDO

* Syntax Highlighting
    * highlight JSLT in multiline strings in Java/Scala/Kotlin files if first line of text start with `// JSLT`

## Know issues


## Build Notes

The parser code generated from the src/main/grammar/jslt.bnf by grammarKit has to run twice as it does not recognise the Util-Classes, that are compiled later.
Therefore, the build is not completely Gradle based and the generated code is checked into Git.
Run the "Generate Parser Code" from the context menu of the bnf-file within IntelliJ (twice) before build if the grammar changed.

The build task throws an exception on the `buildSearchableOptions` task (`Process 'command '.../.gradle/caches/modules-2/files-2.1/com.jetbrains/jbre/jbr_jcef-11_0_14_1-linux-x64-b2043.25/extracted/jbr/bin/java'' finished with non-zero exit value 3`) on the first run.
Execute the task a second time, and it should build.