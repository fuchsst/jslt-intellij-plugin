package net.stefanfuchs.jslt.intellij.language.psi

import com.intellij.psi.tree.IElementType
import net.stefanfuchs.jslt.intellij.language.JsltLanguage
import org.jetbrains.annotations.NonNls


class JsltTokenType(@NonNls debugName: String) :
    IElementType(debugName, JsltLanguage) {
    override fun toString(): String {
        return "JsltTokenType." + super.toString()
    }
}

class JsltElementType(@NonNls debugName: String) : IElementType(debugName, JsltLanguage)