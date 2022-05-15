@file:JvmName("JsltPsiImplUtil")
@file:JvmMultifileClass

package net.stefanfuchs.jslt.intellij.language.psi.util

import com.intellij.icons.AllIcons
import com.intellij.lang.ASTNode
import com.intellij.navigation.ItemPresentation
import net.stefanfuchs.jslt.intellij.language.psi.JsltLetAssignment
import net.stefanfuchs.jslt.intellij.language.psi.JsltTypes
import javax.swing.Icon


fun getName(element: JsltLetAssignment): String? {
    val keyNode: ASTNode? = element.node.findChildByType(JsltTypes.LET_VARIABLE_DECL)
    return keyNode?.text
}

fun getPresentation(element: JsltLetAssignment): ItemPresentation {
    return object : ItemPresentation {
        override fun getPresentableText(): String = element.name ?: "<missing variable name>"
        override fun getLocationString(): String? = element.containingFile?.name
        override fun getIcon(unused: Boolean): Icon = AllIcons.Nodes.Variable
    }
}
