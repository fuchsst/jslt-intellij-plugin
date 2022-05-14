@file:JvmName("JsltPsiImplUtil")
@file:JvmMultifileClass

package net.stefanfuchs.jslt.intellij.language.psi.util

import com.intellij.icons.AllIcons
import com.intellij.lang.ASTNode
import com.intellij.navigation.ItemPresentation
import com.intellij.psi.PsiElement
import net.stefanfuchs.jslt.intellij.language.psi.*
import javax.swing.Icon



fun getName(element: JsltLetAssignment): String? {
    val keyNode: ASTNode? = element.node.findChildByType(JsltTypes.VARIABLE_DECL)
    return keyNode?.text
}

fun setName(element: JsltLetAssignment, newAlias: String): PsiElement {
    val aliasASTNode: ASTNode? = element.node.findChildByType(JsltTypes.VARIABLE_DECL)
    if (aliasASTNode != null) {
        val letAssignment = JsltElementFactory.createLetAssignment(element.project, newAlias)
        val newAliasASTNode: ASTNode = letAssignment.node.findChildByType(JsltTypes.VARIABLE_DECL)!!
        element.node.replaceChild(aliasASTNode, newAliasASTNode)
    }
    return element
}

fun getNameIdentifier(element: JsltLetAssignment): PsiElement? {
    val keyNode: ASTNode? = element.node.findChildByType(JsltTypes.VARIABLE_DECL)
    return keyNode?.psi
}


fun getPresentation(element: JsltLetAssignment): ItemPresentation {
    return object : ItemPresentation {
        override fun getPresentableText(): String = element.name ?: "<missing variable name>"
        override fun getLocationString(): String? = element.containingFile?.name
        override fun getIcon(unused: Boolean): Icon = AllIcons.Nodes.Variable
    }
}
