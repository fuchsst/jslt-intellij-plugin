@file:JvmName("JsltPsiImplUtil")
@file:JvmMultifileClass

package net.stefanfuchs.jslt.intellij.language.psi.util

import com.intellij.icons.AllIcons
import com.intellij.lang.ASTNode
import com.intellij.navigation.ItemPresentation
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import net.stefanfuchs.jslt.intellij.language.JsltImportDeclarationReference
import net.stefanfuchs.jslt.intellij.language.psi.JsltElementFactory
import net.stefanfuchs.jslt.intellij.language.psi.JsltFunctionName
import net.stefanfuchs.jslt.intellij.language.psi.JsltImportDeclaration
import net.stefanfuchs.jslt.intellij.language.psi.JsltTypes
import javax.swing.Icon


fun getName(element: JsltImportDeclaration): String? {
    val importAliasASTNode: ASTNode? = element.node.findChildByType(JsltTypes.IMPORT_ALIAS)
    return importAliasASTNode?.text
}

fun setName(element: JsltImportDeclaration, newName: String): PsiElement {
    val importAliasASTNode: ASTNode? = element.node.findChildByType(JsltTypes.IMPORT_ALIAS)
    if (importAliasASTNode != null) {
        val newImportDecl = JsltElementFactory.createImportDecl(element.project, newName)
        val newImportDeclASTNode: ASTNode = newImportDecl.node.findChildByType(JsltTypes.IMPORT_ALIAS)!!
        element.node.replaceChild(importAliasASTNode, newImportDeclASTNode)
    }
    return element
}

fun getNameIdentifier(element: JsltImportDeclaration): PsiElement? {
    val importAliasASTNode: ASTNode? = element.node.findChildByType(JsltTypes.IMPORT_ALIAS)
    return importAliasASTNode?.psi
}

fun getPresentation(element: JsltImportDeclaration): ItemPresentation {
    return object : ItemPresentation {
        override fun getPresentableText(): String = element.name ?: "<missing import alias>"
        override fun getLocationString(): String? = element.containingFile?.name
        override fun getIcon(unused: Boolean): Icon = AllIcons.Nodes.Module
    }
}

fun getReference(element: JsltImportDeclaration): PsiReference {
    val importFileASTNode: ASTNode? = element.node.findChildByType(JsltTypes.IMPORT_FILE_STRING)

    val startOffset = importFileASTNode?.textRange?.startOffset?.minus(element.textRange.startOffset)?.plus(1)
    val endOffset = importFileASTNode?.textRange?.endOffset?.minus(element.textRange.startOffset)?.minus(1)
    return JsltImportDeclarationReference(element, TextRange(startOffset ?: 0, endOffset ?: (element.textLength - 1)))
}

fun isReferenceTo(element: JsltImportDeclaration, otherElement: PsiElement): Boolean {
    return otherElement is JsltFunctionName &&
            element.name == otherElement.importAlias &&
            otherElement.reference.resolve() == element
}