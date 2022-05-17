@file:JvmName("JsltPsiImplUtil")
@file:JvmMultifileClass

package net.stefanfuchs.jslt.intellij.language.psi.util

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import net.stefanfuchs.jslt.intellij.language.psi.JsltElementFactory
import net.stefanfuchs.jslt.intellij.language.psi.JsltFunctionDeclParamDecl
import net.stefanfuchs.jslt.intellij.language.psi.JsltTypes
import net.stefanfuchs.jslt.intellij.language.psi.JsltVariableUsage


fun getName(element: JsltFunctionDeclParamDecl): String? {
    val paramASTNode: ASTNode? = element.node.findChildByType(JsltTypes.FUNCTION_DECL_PARAM)
    return paramASTNode?.text
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

fun getNameIdentifier(element: JsltFunctionDeclParamDecl): PsiElement? {
    val paramASTNode: ASTNode? = element.node.findChildByType(JsltTypes.FUNCTION_DECL_PARAM)
    return paramASTNode?.psi
}

fun isReferenceTo(element: JsltFunctionDeclParamDecl, otherElement: PsiElement): Boolean {
    return otherElement is JsltVariableUsage &&
            element.name == otherElement.name &&
            otherElement.reference.resolve() == element
}