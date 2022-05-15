@file:JvmName("JsltPsiImplUtil")
@file:JvmMultifileClass

package net.stefanfuchs.jslt.intellij.language.psi.util

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import net.stefanfuchs.jslt.intellij.language.psi.JsltElementFactory
import net.stefanfuchs.jslt.intellij.language.psi.JsltLetVariableDecl
import net.stefanfuchs.jslt.intellij.language.psi.JsltTypes


fun getName(element: JsltLetVariableDecl): String? {
    val keyNode: ASTNode? = element.node.findChildByType(JsltTypes.VARIABLE_DECL)
    return keyNode?.text
}

fun setName(element: JsltLetVariableDecl, newAlias: String): PsiElement {
    val variableDeclASTNode: ASTNode? = element.node.findChildByType(JsltTypes.VARIABLE_DECL)
    if (variableDeclASTNode != null) {
        val letVariableDecl = JsltElementFactory.createLetVariableDecl(element.project, newAlias)
        val newVariableDeclASTNode: ASTNode = letVariableDecl.node.findChildByType(JsltTypes.VARIABLE_DECL)!!
        element.node.replaceChild(variableDeclASTNode, newVariableDeclASTNode)
    }
    return element
}

fun getNameIdentifier(element: JsltLetVariableDecl): PsiElement? {
    val keyNode: ASTNode? = element.node.findChildByType(JsltTypes.VARIABLE_DECL)
    return keyNode?.psi
}
