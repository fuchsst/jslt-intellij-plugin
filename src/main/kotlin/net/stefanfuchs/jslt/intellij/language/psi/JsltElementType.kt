package net.stefanfuchs.jslt.intellij.language.psi

import com.intellij.psi.tree.IElementType
import net.stefanfuchs.jslt.intellij.language.JsltLanguage
import org.jetbrains.annotations.NonNls

class JsltElementType(@NonNls debugName: String) : IElementType(debugName, JsltLanguage)