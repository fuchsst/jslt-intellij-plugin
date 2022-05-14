@file:JvmName("JsltPsiImplUtil")
@file:JvmMultifileClass

package net.stefanfuchs.jslt.intellij.language.psi.util

import com.intellij.icons.AllIcons
import com.intellij.lang.ASTNode
import com.intellij.navigation.ItemPresentation
import com.intellij.psi.PsiElement
import net.stefanfuchs.jslt.intellij.language.psi.*
import javax.swing.Icon


fun getName(element: JsltImportDeclaration): String? {
    val keyNode: ASTNode? = element.node.findChildByType(JsltTypes.IMPORT_ALIAS)
    return keyNode?.text
}

fun getPresentation(element: JsltImportDeclaration): ItemPresentation {
    return object : ItemPresentation {
        override fun getPresentableText(): String = element.name ?: "<missing import alias>"
        override fun getLocationString(): String? = element.containingFile?.name
        override fun getIcon(unused: Boolean): Icon = AllIcons.Nodes.Module
    }
}
