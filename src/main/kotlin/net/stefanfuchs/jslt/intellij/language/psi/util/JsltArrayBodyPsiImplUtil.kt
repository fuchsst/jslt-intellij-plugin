@file:JvmName("JsltPsiImplUtil")
@file:JvmMultifileClass

package net.stefanfuchs.jslt.intellij.language.psi.util

import net.stefanfuchs.jslt.intellij.language.psi.JsltArrayBody
import net.stefanfuchs.jslt.intellij.language.psi.JsltExpr


fun getExpressions(element: JsltArrayBody): List<JsltExpr> {
    val result = mutableListOf<JsltExpr>()
    if (element.arrayFor?.parenthesisExpr?.expr != null) {
        result.add(element.arrayFor!!.parenthesisExpr.expr!!)
    }
    if (element.arrayFor?.arrayForBody?.expr != null) {
        result.add(element.arrayFor?.arrayForBody!!.expr)
    }
    if (element.arrayFor?.arrayForBody?.parenthesisExpr?.expr != null) {
        result.add(element.arrayFor!!.arrayForBody.parenthesisExpr!!.expr!!)
    }
    if (element.arrayElements != null) {
        element.arrayElements?.exprList?.forEach {
            result.add(it)
        }
    }
    return result
}
