# JSLT IntelliJ Language Plugin

## Features

* Syntax Highlighting
  * color by token type 
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
  

## ToDO

* Syntax Highlighting
  * differentiate global/local/parameter variables
  * differentiate buildin/def functions
* Code Completion
  * meaningful error message when an invalid token was detected
  * inline docs for buildin-functions
  * suggested tokes on Ctrl+Space
* Code Navigation
  * recognize JSLT (by first line marked with `#! JSLT`) in multiline strings in Java/Scala/Kotlin/yaml files
* Validations
  * warn for undeclared variables
  * warn for call of unknown function
  * error for duplicate declared variables
  * error for duplicate declared import aliases
  * error for non existing file referenced in import
  * error for non existing import alias referenced by function
  * error for non existing function referenced via import alias 
  * error for duplicate key in object
* JSLT execution
  * dialog box that allows to select/write an input JSON, a JSLT transformation and shows the output
  * apply JSLT from contextmenu (opens the dialog box)
    * when used on JSLT-file, submenu with <empty> and a list of all JSON files in the project (structured by module/directory?)  
    * when used on JSON-file, submenu with <empty> and a list of all JSLT files in the project (structured by module/directory?)