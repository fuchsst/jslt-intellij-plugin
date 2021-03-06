@file:JvmName("JsltPsiImplUtil")
@file:JvmMultifileClass

package net.stefanfuchs.jslt.intellij.language.psi.util

import com.intellij.icons.AllIcons
import com.intellij.lang.ASTNode
import com.intellij.navigation.ItemPresentation
import net.stefanfuchs.jslt.intellij.language.psi.JsltPair
import net.stefanfuchs.jslt.intellij.language.psi.JsltTypes
import javax.swing.Icon


fun getName(element: JsltPair): String? {
    val keyNode: ASTNode? = element.node.findChildByType(JsltTypes.EXPR)
    return keyNode?.text?.trim('"')
}


fun getPresentation(element: JsltPair): ItemPresentation {
    return object : ItemPresentation {
        override fun getPresentableText(): String = element.name ?: "<missing key>"
        override fun getLocationString(): String? = element.containingFile?.name
        override fun getIcon(unused: Boolean): Icon = AllIcons.Nodes.Field
    }
}
