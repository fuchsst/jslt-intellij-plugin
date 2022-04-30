# JSLT IntelliJ Language Plugin

## Features

* Basic Syntax Highlighting
* Comment code lines (Strg+/)

## ToDO

* Code Folding
* Parenthesis Matching
* Code Auto Format (Ctrl+Alt+L)
* Advanced Syntax Highlighting
  * differentiate global/local/parameter variables
  * differentiate buildin/def functions
* Code Completion
  * more meaningful error message when an invalid token was detected
  * inline docs for buildin-functions
  * suggested tokes on Ctrl+Space
* Code Navigation
  * Jump to variable declaration
  * Show variable usages
  * Goto imported File
  * Code Outline
* Validations
  * warn for undeclared variables
  * warn for call of unknown function
  * error for duplicate declared variables
  * error for duplicate declared import aliases
* JSLT execution
  * dialog box that allows to select/write an input JSON, a JSLT transformation and shows the output
  * apply JSLT from contextmenu (opens the dialog box)
    * when used on JSLT-file, submenu with <empty> and a list of all JSON files in the project (structured by module/directory?)  
    * when used on JSON-file, submenu with <empty> and a list of all JSLT files in the project (structured by module/directory?)