@file:JvmName("JsltPsiImplUtil")
@file:JvmMultifileClass

package net.stefanfuchs.jslt.intellij.language.psi.impl

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import net.stefanfuchs.jslt.intellij.language.psi.JsltTypes
import net.stefanfuchs.jslt.intellij.language.psi.JsltVariableElementFactory
import net.stefanfuchs.jslt.intellij.language.psi.JsltVariableUsage


fun getName(element: JsltVariableUsage): String? {
    val keyNode: ASTNode? = element.node.findChildByType(JsltTypes.VARIABLE)
    return keyNode?.text?.substringAfter('$')
}


fun setName(element: JsltVariableUsage, newName: String): PsiElement {
    val variableASTNode: ASTNode? = element.node.findChildByType(JsltTypes.VARIABLE)
    if (variableASTNode != null) {
        val newVariableUsage = JsltVariableElementFactory.createVariableUsage(element.project, newName)
        val newAliasASTNode: ASTNode = newVariableUsage.node.findChildByType(JsltTypes.VARIABLE)!!
        element.node.replaceChild(variableASTNode, newAliasASTNode)
    }
    return element
}