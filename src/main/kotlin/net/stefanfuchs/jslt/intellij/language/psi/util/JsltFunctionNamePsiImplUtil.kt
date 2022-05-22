@file:JvmName("JsltPsiImplUtil")
@file:JvmMultifileClass

package net.stefanfuchs.jslt.intellij.language.psi.util

import com.intellij.lang.ASTNode
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import net.stefanfuchs.jslt.intellij.language.JsltFunctionNameReference
import net.stefanfuchs.jslt.intellij.language.psi.JsltElementFactory
import net.stefanfuchs.jslt.intellij.language.psi.JsltFunctionName
import net.stefanfuchs.jslt.intellij.language.psi.JsltTypes

fun getName(element: JsltFunctionName): String? {
    val functionNameASTNode: ASTNode? = element.node.firstChildNode
    return when (functionNameASTNode?.elementType) {
        JsltTypes.IDENT -> functionNameASTNode?.text
        JsltTypes.PIDENT -> functionNameASTNode?.text?.substringAfter(':')
        else -> null
    }
}

fun getNameIdentifier(element: JsltFunctionName): PsiElement? {
    val functionNameASTNode: ASTNode? = element.node.firstChildNode
    return functionNameASTNode?.psi
}

fun setName(element: JsltFunctionName, newName: String): PsiElement {
    val functionNameASTNode: ASTNode? = element.node.firstChildNode

    if (functionNameASTNode != null) {
        val alias = element.importAlias
        val newFunctionName = JsltElementFactory.createFunctionCallName(element.project, alias, newName)
        val newFunctionNameASTNode: ASTNode = newFunctionName.node.firstChildNode!!
        element.node.replaceChild(functionNameASTNode, newFunctionNameASTNode)
        element.identifyingElement
    }
    return element
}

fun getImportAlias(element: JsltFunctionName): String? {
    val functionNameASTNode: ASTNode? = element.node.firstChildNode
    return when (functionNameASTNode?.elementType) {
        JsltTypes.PIDENT -> functionNameASTNode?.text?.substringBefore(':')
        else -> null
    }
}

fun getReference(element: JsltFunctionName): PsiReference {
    return JsltFunctionNameReference(element, TextRange(0, element.text.length))
}