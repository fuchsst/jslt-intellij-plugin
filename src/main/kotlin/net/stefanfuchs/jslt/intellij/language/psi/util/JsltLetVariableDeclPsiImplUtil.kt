@file:JvmName("JsltPsiImplUtil")
@file:JvmMultifileClass

package net.stefanfuchs.jslt.intellij.language.psi.util

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import net.stefanfuchs.jslt.intellij.language.psi.JsltElementFactory
import net.stefanfuchs.jslt.intellij.language.psi.JsltLetVariableDecl
import net.stefanfuchs.jslt.intellij.language.psi.JsltTypes
import net.stefanfuchs.jslt.intellij.language.psi.JsltVariableUsage


fun getName(element: JsltLetVariableDecl): String? {
    val keyNode: ASTNode? = element.node.findChildByType(JsltTypes.VARIABLE_DECL)
    return keyNode?.text
}

fun setName(element: JsltLetVariableDecl, newName: String): PsiElement {
    val variableDeclASTNode: ASTNode? = element.node.findChildByType(JsltTypes.VARIABLE_DECL)
    if (variableDeclASTNode != null) {
        val letVariableDecl = JsltElementFactory.createLetVariableDecl(element.project, newName)
        val newVariableDeclASTNode: ASTNode = letVariableDecl.node.findChildByType(JsltTypes.VARIABLE_DECL)!!
        element.node.replaceChild(variableDeclASTNode, newVariableDeclASTNode)
    }
    return element
}

fun getNameIdentifier(element: JsltLetVariableDecl): PsiElement? {
    val variableDeclASTNode: ASTNode? = element.node.findChildByType(JsltTypes.VARIABLE_DECL)
    return variableDeclASTNode?.psi
}


fun isReferenceTo(element: JsltLetVariableDecl, otherElement: PsiElement): Boolean {
    return otherElement is JsltVariableUsage &&
            element.name == otherElement.name &&
            otherElement.reference.resolve() == element
}
