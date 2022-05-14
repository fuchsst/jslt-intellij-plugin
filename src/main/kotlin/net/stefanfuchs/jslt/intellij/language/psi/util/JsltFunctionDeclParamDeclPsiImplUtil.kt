@file:JvmName("JsltPsiImplUtil")
@file:JvmMultifileClass

package net.stefanfuchs.jslt.intellij.language.psi.util

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import net.stefanfuchs.jslt.intellij.language.psi.JsltFunctionDeclParamDecl
import net.stefanfuchs.jslt.intellij.language.psi.JsltTypes
import net.stefanfuchs.jslt.intellij.language.psi.JsltElementFactory


fun getName(element: JsltFunctionDeclParamDecl): String? {
    val paramNode: ASTNode? = element.node.findChildByType(JsltTypes.FUNCTION_DECL_PARAM)
    return paramNode?.text
}


fun setName(element: JsltFunctionDeclParamDecl, newName: String): PsiElement {
    val paramASTNode: ASTNode? = element.node.findChildByType(JsltTypes.FUNCTION_DECL_PARAM)
    if (paramASTNode != null) {
        val newParamDecl = JsltElementFactory.createFunctionDeclParamDecl(element.project, newName)
        val newParamASTNode: ASTNode = newParamDecl.node.findChildByType(JsltTypes.FUNCTION_DECL_PARAM)!!
        element.node.replaceChild(paramASTNode, newParamASTNode)
    }
    return element
}