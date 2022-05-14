@file:JvmName("JsltPsiImplUtil")
@file:JvmMultifileClass

package net.stefanfuchs.jslt.intellij.language.psi.util

import com.intellij.icons.AllIcons
import com.intellij.lang.ASTNode
import com.intellij.navigation.ItemPresentation
import com.intellij.psi.PsiElement
import net.stefanfuchs.jslt.intellij.language.psi.*
import javax.swing.Icon



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
