package net.stefanfuchs.jslt.intellij.language

import com.intellij.lexer.FlexAdapter

class JsltLexerAdapter : FlexAdapter(JsltLexer(null))