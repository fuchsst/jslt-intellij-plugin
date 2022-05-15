@file:JvmName("JsltPsiImplUtil")
@file:JvmMultifileClass

package net.stefanfuchs.jslt.intellij.language.psi.util

import com.intellij.lang.ASTNode
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import net.stefanfuchs.jslt.intellij.language.JsltVariableReference
import net.stefanfuchs.jslt.intellij.language.psi.*


fun getName(element: JsltVariableUsage): String? {
    val variableASTNode: ASTNode? = element.node.findChildByType(JsltTypes.VARIABLE)
    return variableASTNode?.text?.substringAfter('$')
}


fun setName(element: JsltVariableUsage, newName: String): PsiElement {
    val variableASTNode: ASTNode? = element.node.findChildByType(JsltTypes.VARIABLE)
    if (variableASTNode != null) {
        val newVariableUsage = JsltElementFactory.createVariableUsage(element.project, newName)
        val newVariableASTNode: ASTNode = newVariableUsage.node.findChildByType(JsltTypes.VARIABLE)!!
        element.node.replaceChild(variableASTNode, newVariableASTNode)
    }
    return element
}

fun getNameIdentifier(element: JsltVariableUsage): PsiElement? {
    val variableASTNode: ASTNode? = element.node.findChildByType(JsltTypes.VARIABLE)
    return variableASTNode?.psi
}

fun getReference(element: JsltVariableUsage) : PsiReference {
    return JsltVariableReference(element, TextRange(1, element.text.length))
}