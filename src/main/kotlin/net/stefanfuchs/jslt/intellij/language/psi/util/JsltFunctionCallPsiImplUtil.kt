@file:JvmName("JsltPsiImplUtil")
@file:JvmMultifileClass

package net.stefanfuchs.jslt.intellij.language.psi.util

import com.intellij.lang.ASTNode
import net.stefanfuchs.jslt.intellij.language.psi.JsltFunctionCall
import net.stefanfuchs.jslt.intellij.language.psi.JsltTypes


fun getName(element: JsltFunctionCall): String? {
    val functionNameASTNode: ASTNode? = element.node.findChildByType(JsltTypes.FUNCTION_NAME)?.firstChildNode
    return when (functionNameASTNode?.elementType) {
        JsltTypes.IDENT -> functionNameASTNode?.text
        JsltTypes.PIDENT -> functionNameASTNode?.text?.substringAfter(':')
        else -> null
    }
}

fun getImportAlias(element: JsltFunctionCall): String? {
    val functionNameASTNode: ASTNode? = element.node.findChildByType(JsltTypes.FUNCTION_NAME)?.firstChildNode
    return when (functionNameASTNode?.elementType) {
        JsltTypes.PIDENT -> functionNameASTNode?.text?.substringBefore(':')
        else -> null
    }
}

//JsltFunctionCall: setImportAlias setName
