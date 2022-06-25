package net.stefanfuchs.jslt.intellij.language;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import net.stefanfuchs.jslt.intellij.language.psi.JsltTypes;
import com.intellij.psi.TokenType;

%%

%class JsltLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}


WHITE_SPACE=[\ \t\n\r\f]
SIGN = ["+"-]?
EXP = [eE]
DIGITS=([0-9])+
INTEGER=(-)?(0|[1-9]([0-9])*)
DECIMAL={INTEGER}(\.{DIGITS}|(\.{DIGITS})?{EXP}{SIGN}?{DIGITS})

// STRING
HEX_DIGIT = [0-9a-fA-F]
VALID_SET_CHAR = [^\u0000-\u001F\"\\]
// valid_escape_seq = \\\" | \\\\ | \\/ | \\b | \\f | \\n | \\r | \\r | \\t | (\\u({hex_digit}{4}))
// or in a more condensed form:
VALID_ESCAPE_SEQ = \\([\"\\/bfnrt]|(u({HEX_DIGIT}{4})))
VALID_CHARACTER = {VALID_SET_CHAR} | {VALID_ESCAPE_SEQ}
STRING = \"{VALID_CHARACTER}*\"

NULL="null"
FALSE="false"
TRUE="true"
IDENT=([A-Za-z_])([A-Za-z0-9_\-])*
PIDENT={IDENT} ":" {IDENT}
VARIABLE="$" {IDENT}

SINGLE_LINE_COMMENT="//"[^\r\n]*


%state IMPORTS
%state LETS
%state FUNC_DECLARATION
%state FUNC_DECLARATION_PARAMS

%%

<YYINITIAL> {SINGLE_LINE_COMMENT}                           { return JsltTypes.COMMENT; }
<YYINITIAL> "import"                                        { yybegin(IMPORTS); return JsltTypes.IMPORT; }
<IMPORTS> {STRING}                                          { return JsltTypes.IMPORT_FILE_STRING; }
<IMPORTS> "as"                                              { return JsltTypes.AS; }
<IMPORTS> {IDENT}                                           { yybegin(YYINITIAL); return JsltTypes.IMPORT_ALIAS; }

<YYINITIAL> "let"                                           { yybegin(LETS); return JsltTypes.LET; }
<LETS> {IDENT}                                              { return JsltTypes.VARIABLE_DECL; }
<LETS> "="                                                  { yybegin(YYINITIAL); return JsltTypes.ASSIGN; }

<YYINITIAL> "def"                                           { yybegin(FUNC_DECLARATION); return JsltTypes.DEF; }
<FUNC_DECLARATION> {IDENT}                                  { return JsltTypes.FUNCTION_DECL_NAME; }
<FUNC_DECLARATION> "("                                      { yybegin(FUNC_DECLARATION_PARAMS); return JsltTypes.LPAREN; }
<FUNC_DECLARATION_PARAMS> {IDENT}                           { return JsltTypes.FUNCTION_DECL_PARAM; }
<FUNC_DECLARATION_PARAMS> ","                               { return JsltTypes.COMMA; }
<FUNC_DECLARATION_PARAMS> ")"                               { yybegin(YYINITIAL); return JsltTypes.RPAREN; }

<YYINITIAL> {INTEGER}                                       { return JsltTypes.INTEGER; }
<YYINITIAL> {DECIMAL}                                       { return JsltTypes.DECIMAL; }
<YYINITIAL> {STRING}                                        { return JsltTypes.STRING; }
<YYINITIAL> "["                                             { return JsltTypes.LBRACKET; }
<YYINITIAL> "]"                                             { return JsltTypes.RBRACKET; }
<YYINITIAL> ","                                             { return JsltTypes.COMMA; }
<YYINITIAL> ":"                                             { return JsltTypes.COLON; }
<YYINITIAL> "{"                                             { return JsltTypes.LCURLY; }
<YYINITIAL> "}"                                             { return JsltTypes.RCURLY; }
<YYINITIAL> "null"                                          { return JsltTypes.NULL; }
<YYINITIAL> "true"                                          { return JsltTypes.TRUE; }
<YYINITIAL> "false"                                         { return JsltTypes.FALSE; }
<YYINITIAL> "or"                                            { return JsltTypes.OR; }
<YYINITIAL> "and"                                           { return JsltTypes.AND; }
<YYINITIAL> "."                                             { return JsltTypes.DOT; }
<YYINITIAL> "if"                                            { return JsltTypes.IF; }
<YYINITIAL> "else"                                          { return JsltTypes.ELSE; }
<YYINITIAL> "("                                             { return JsltTypes.LPAREN; }
<YYINITIAL> ")"                                             { return JsltTypes.RPAREN; }
<YYINITIAL> "="                                             { return JsltTypes.ASSIGN; }
<YYINITIAL> "=="                                            { return JsltTypes.EQUALS; }
<YYINITIAL> "!="                                            { return JsltTypes.UNEQUALS; }
<YYINITIAL> ">="                                            { return JsltTypes.BIGOREQ; }
<YYINITIAL> ">"                                             { return JsltTypes.BIGGER; }
<YYINITIAL> "<"                                             { return JsltTypes.SMALLER; }
<YYINITIAL> "<="                                            { return JsltTypes.SMALLOREQ; }
<YYINITIAL> "+"                                             { return JsltTypes.PLUS; }
<YYINITIAL> "-"                                             { return JsltTypes.MINUS; }
<YYINITIAL> "*"                                             { return JsltTypes.STAR; }
<YYINITIAL> "/"                                             { return JsltTypes.SLASH; }
<YYINITIAL> "|"                                             { return JsltTypes.PIPE; }
<YYINITIAL> "for"                                           { return JsltTypes.FOR; }
<YYINITIAL> {IDENT}                                         { return JsltTypes.IDENT; }
<YYINITIAL> {PIDENT}                                        { return JsltTypes.PIDENT; }
<YYINITIAL> {VARIABLE}                                      { return JsltTypes.VARIABLE; }

{WHITE_SPACE}+                                              { return TokenType.WHITE_SPACE; }

[^]                                                         { return TokenType.BAD_CHARACTER; }
