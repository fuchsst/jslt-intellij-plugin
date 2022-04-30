package net.stefanfuchs.jslt.intellij.language

import com.intellij.lang.BracePair
import com.intellij.lang.PairedBraceMatcher
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IElementType
import net.stefanfuchs.jslt.intellij.language.psi.JsltTypes

class JsltPairedBraceMatcher : PairedBraceMatcher {
    override fun getPairs(): Array<BracePair> = arrayOf(
        BracePair(JsltTypes.LPAREN, JsltTypes.RPAREN, false),
        BracePair(JsltTypes.LBRACKET, JsltTypes.RBRACKET, false),
        BracePair(JsltTypes.LCURLY, JsltTypes.RCURLY, false)
    )

    override fun isPairedBracesAllowedBeforeType(lbraceType: IElementType, contextType: IElementType?) = true

    override fun getCodeConstructStart(file: PsiFile?, openingBraceOffset: Int): Int = openingBraceOffset
}