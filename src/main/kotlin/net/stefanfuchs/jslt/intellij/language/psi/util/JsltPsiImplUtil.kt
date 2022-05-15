@file:JvmName("JsltPsiImplUtil")
@file:JvmMultifileClass

package net.stefanfuchs.jslt.intellij.language.psi.util

import com.intellij.icons.AllIcons
import com.intellij.navigation.ItemPresentation
import net.stefanfuchs.jslt.intellij.language.psi.JsltArray
import net.stefanfuchs.jslt.intellij.language.psi.JsltMatcher
import net.stefanfuchs.jslt.intellij.language.psi.JsltObject
import net.stefanfuchs.jslt.intellij.language.psi.JsltObjectComprehension
import javax.swing.Icon


fun getPresentation(element: JsltObject): ItemPresentation {
    return object : ItemPresentation {
        override fun getPresentableText(): String = "object"
        override fun getLocationString(): String? = element.containingFile?.name
        override fun getIcon(unused: Boolean): Icon = AllIcons.Json.Object
    }
}

fun getPresentation(element: JsltObjectComprehension): ItemPresentation {
    return object : ItemPresentation {
        override fun getPresentableText(): String = "object comprehension"
        override fun getLocationString(): String? = element.containingFile?.name
        override fun getIcon(unused: Boolean): Icon = AllIcons.Nodes.MultipleTypeDefinitions
    }
}

fun getPresentation(element: JsltArray): ItemPresentation {
    return object : ItemPresentation {
        override fun getPresentableText(): String = "array"
        override fun getLocationString(): String? = element.containingFile?.name
        override fun getIcon(unused: Boolean): Icon = AllIcons.Json.Array
    }
}

fun getPresentation(element: JsltMatcher): ItemPresentation {
    return object : ItemPresentation {
        override fun getPresentableText(): String = element.text
        override fun getLocationString(): String? = element.containingFile?.name
        override fun getIcon(unused: Boolean): Icon = AllIcons.Nodes.ClassInitializer
    }
}
