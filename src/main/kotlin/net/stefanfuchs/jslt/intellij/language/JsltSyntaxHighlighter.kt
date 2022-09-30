package net.stefanfuchs.jslt.intellij.language

// Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.HighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType
import net.stefanfuchs.jslt.intellij.language.psi.JsltTypes


class JsltSyntaxHighlighter : SyntaxHighlighterBase() {
    override fun getHighlightingLexer(): Lexer {
        return JsltLexerAdapter()
    }

    override fun getTokenHighlights(tokenType: IElementType): Array<TextAttributesKey> {
        return when (tokenType) {
            JsltTypes.ASSIGN -> {
                arrayOf(ASSIGN)
            }

            JsltTypes.BIGGER, JsltTypes.BIGOREQ, JsltTypes.EQUALS, JsltTypes.SMALLER, JsltTypes.SMALLOREQ, JsltTypes.UNEQUALS -> {
                arrayOf(COMPARATOR)
            }

            JsltTypes.COMMENT -> arrayOf(COMMENT)
            JsltTypes.DECIMAL, JsltTypes.INTEGER -> arrayOf(NUMBER)
            JsltTypes.FUNCTION_DECL_NAME, JsltTypes.FUNCTION_NAME -> arrayOf(FUNCTION_NAME)
            JsltTypes.FUNCTION_DECL_PARAM -> arrayOf(PARAMETER)
            JsltTypes.IDENT, JsltTypes.PIDENT -> arrayOf(IDENTIFIER)
            JsltTypes.AND, JsltTypes.AS, JsltTypes.DEF, JsltTypes.ELSE, JsltTypes.FALSE, JsltTypes.FOR, JsltTypes.IF,
            JsltTypes.IMPORT, JsltTypes.LET, JsltTypes.NULL, JsltTypes.OR, JsltTypes.TRUE,
            -> arrayOf(KEYWORD)

            JsltTypes.MINUS, JsltTypes.PIPE, JsltTypes.PLUS, JsltTypes.SLASH, JsltTypes.STAR -> arrayOf(OPERATOR)
            JsltTypes.LBRACKET, JsltTypes.RBRACKET -> arrayOf(BRACKETS)
            JsltTypes.LPAREN, JsltTypes.RPAREN -> arrayOf(PARENTHESIS)
            JsltTypes.LCURLY, JsltTypes.RCURLY -> arrayOf(OBJECT_BRACES)
            JsltTypes.STRING -> arrayOf(STRING)
            JsltTypes.VARIABLE_DECL -> arrayOf(VARIABLE_DECL)
            JsltTypes.VARIABLE -> arrayOf(GLOBAL_VARIABLE)
            JsltTypes.COLON -> arrayOf(COLON)
            JsltTypes.COMMA -> arrayOf(COMMA)
            JsltTypes.DOT -> arrayOf(DOT)
            TokenType.BAD_CHARACTER -> arrayOf(BAD_CHARACTER)
            else -> emptyArray()
        }
    }

    companion object {
        val ASSIGN =
            TextAttributesKey.createTextAttributesKey("JSLT_ASSIGN", DefaultLanguageHighlighterColors.OPERATION_SIGN)
        val BAD_CHARACTER =
            TextAttributesKey.createTextAttributesKey("JSLT_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER)
        val BRACKETS =
            TextAttributesKey.createTextAttributesKey("JSLT_BRACKETS", DefaultLanguageHighlighterColors.BRACKETS)
        val COLON = TextAttributesKey.createTextAttributesKey("JSLT_COLON", DefaultLanguageHighlighterColors.DOT)
        val COMMA = TextAttributesKey.createTextAttributesKey("JSLT_COMMA", DefaultLanguageHighlighterColors.COMMA)
        val COMMENT =
            TextAttributesKey.createTextAttributesKey("JSLT_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT)
        val COMPARATOR = TextAttributesKey.createTextAttributesKey(
            "JSLT_COMPARATOR",
            DefaultLanguageHighlighterColors.OPERATION_SIGN
        )
        val DOT = TextAttributesKey.createTextAttributesKey("JSLT_DOT", DefaultLanguageHighlighterColors.DOT)
        val FUNCTION_NAME = TextAttributesKey.createTextAttributesKey(
            "JSLT_FUNCTION_DECLARATION",
            DefaultLanguageHighlighterColors.FUNCTION_DECLARATION
        )
        val BUILDIN_FUNCTION_NAME = TextAttributesKey.createTextAttributesKey(
            "JSLT_BUILDIN_FUNCTION",
            DefaultLanguageHighlighterColors.CLASS_NAME
        )
        val IDENTIFIER =
            TextAttributesKey.createTextAttributesKey("JSLT_IDENTIFIER", DefaultLanguageHighlighterColors.IDENTIFIER)
        val KEYWORD =
            TextAttributesKey.createTextAttributesKey("JSLT_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD)
        val NUMBER = TextAttributesKey.createTextAttributesKey("JSLT_NUMBER", DefaultLanguageHighlighterColors.NUMBER)
        val OBJECT_BRACES =
            TextAttributesKey.createTextAttributesKey("JSLT_OBJECT_BRACES", DefaultLanguageHighlighterColors.BRACES)
        val OPERATOR =
            TextAttributesKey.createTextAttributesKey("JSLT_OPERATOR", DefaultLanguageHighlighterColors.OPERATION_SIGN)
        val PARAMETER =
            TextAttributesKey.createTextAttributesKey("JSLT_PARAMETER", DefaultLanguageHighlighterColors.PARAMETER)
        val PARENTHESIS =
            TextAttributesKey.createTextAttributesKey("JSLT_PARENTHESIS", DefaultLanguageHighlighterColors.PARENTHESES)
        val STRING = TextAttributesKey.createTextAttributesKey("JSLT_STRING", DefaultLanguageHighlighterColors.STRING)
        val VARIABLE_DECL =
            TextAttributesKey.createTextAttributesKey(
                "JSLT_VARIABLE_DECL",
                DefaultLanguageHighlighterColors.GLOBAL_VARIABLE
            )
        val GLOBAL_VARIABLE =
            TextAttributesKey.createTextAttributesKey(
                "JSLT_GLOBAL_VARIABLE",
                DefaultLanguageHighlighterColors.GLOBAL_VARIABLE
            )
        val LOCAL_VARIABLE =
            TextAttributesKey.createTextAttributesKey(
                "JSLT_LOCAL_VARIABLE",
                DefaultLanguageHighlighterColors.LOCAL_VARIABLE
            )
        val UNUSED_IDENTIFIER =
            TextAttributesKey.createTextAttributesKey("JSLT_UNUSED", DefaultLanguageHighlighterColors.LINE_COMMENT)
    }
}
