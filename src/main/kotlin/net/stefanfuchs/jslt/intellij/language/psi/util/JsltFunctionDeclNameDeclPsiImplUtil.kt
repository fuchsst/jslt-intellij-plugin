@file:JvmName("JsltPsiImplUtil")
@file:JvmMultifileClass

package net.stefanfuchs.jslt.intellij.language.psi.util

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import net.stefanfuchs.jslt.intellij.language.psi.JsltElementFactory
import net.stefanfuchs.jslt.intellij.language.psi.JsltFunctionDeclNameDecl
import net.stefanfuchs.jslt.intellij.language.psi.JsltFunctionName
import net.stefanfuchs.jslt.intellij.language.psi.JsltTypes


fun getName(element: JsltFunctionDeclNameDecl): String? {
    val functionNameASTNode: ASTNode? = element.node.findChildByType(JsltTypes.FUNCTION_DECL_NAME)
    return functionNameASTNode?.text
}

fun setName(element: JsltFunctionDeclNameDecl, newName: String): PsiElement {
    val functionNameASTNode: ASTNode? = element.node.findChildByType(JsltTypes.FUNCTION_DECL_NAME)
    if (functionNameASTNode != null) {
        val newFunctionNameDecl = JsltElementFactory.createFunctionDeclNameDecl(element.project, newName)
        val newFunctionNameASTNode: ASTNode = newFunctionNameDecl.node.findChildByType(JsltTypes.FUNCTION_DECL_NAME)!!
        element.node.replaceChild(functionNameASTNode, newFunctionNameASTNode)
    }
    return element
}

fun getNameIdentifier(element: JsltFunctionDeclNameDecl): PsiElement? {
    val functionNameASTNode: ASTNode? = element.node.findChildByType(JsltTypes.FUNCTION_DECL_NAME)
    return functionNameASTNode?.psi
}

fun isReferenceTo(element: JsltFunctionDeclNameDecl, otherElement: PsiElement): Boolean {
    println(element.toString() + ": " + element.name)
    if (otherElement is JsltFunctionName)
        println(otherElement.toString() + ": " + otherElement.name)
    return otherElement is JsltFunctionName &&
            element.name == otherElement.name &&
            otherElement.reference.resolve() == element
}