@file:JvmName("JsltPsiImplUtil")
@file:JvmMultifileClass

package net.stefanfuchs.jslt.intellij.language.psi.util

import com.intellij.icons.AllIcons
import com.intellij.lang.ASTNode
import com.intellij.navigation.ItemPresentation
import com.intellij.psi.PsiElement
import net.stefanfuchs.jslt.intellij.language.psi.*
import javax.swing.Icon



fun getExpressions(element: JsltArrayBody): List<JsltExpr> {
    val result = mutableListOf<JsltExpr>()
    if (element.parenthesisExpr?.expr != null) {
        result.add(element.parenthesisExpr!!.expr)
    }
    if (element.arrayForBody?.expr != null) {
        result.add(element.arrayForBody!!.expr)
    }
    if (element.arrayForBody?.parenthesisExpr?.expr != null) {
        result.add(element.arrayForBody!!.parenthesisExpr!!.expr)
    }
    if (element.arrayElements != null) {
        element.arrayElements?.exprList?.forEach {
            result.add(it)
        }
    }
    return result
}
