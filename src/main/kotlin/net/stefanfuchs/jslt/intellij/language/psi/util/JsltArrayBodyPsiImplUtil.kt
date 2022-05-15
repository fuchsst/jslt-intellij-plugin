@file:JvmName("JsltPsiImplUtil")
@file:JvmMultifileClass

package net.stefanfuchs.jslt.intellij.language.psi.util

import net.stefanfuchs.jslt.intellij.language.psi.JsltArrayBody
import net.stefanfuchs.jslt.intellij.language.psi.JsltExpr


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
