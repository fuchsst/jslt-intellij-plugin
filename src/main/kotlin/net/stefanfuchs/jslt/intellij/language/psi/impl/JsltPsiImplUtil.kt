@file:JvmName("JsltPsiImplUtil")

package net.stefanfuchs.jslt.intellij.language.psi.impl

import com.intellij.icons.AllIcons
import com.intellij.lang.ASTNode
import com.intellij.navigation.ItemPresentation
import net.stefanfuchs.jslt.intellij.language.psi.*
import javax.swing.Icon


fun getName(element: JsltImportDeclaration): String? {
    val keyNode: ASTNode? = element.node.findChildByType(JsltTypes.IDENT)
    return keyNode?.text
}

fun getName(element: JsltLetAssignment): String? {
    val keyNode: ASTNode? = element.node.findChildByType(JsltTypes.VARIABLE_DECL)
    return keyNode?.text
}

fun getName(element: JsltFunctionDecl): String? {
    val keyNode: ASTNode? = element.node.findChildByType(JsltTypes.FUNCTION_DECL_NAME)
    return keyNode?.text
}

fun getName(element: JsltPair): String? {
    val keyNode: ASTNode? = element.node.findChildByType(JsltTypes.EXPR)
    return keyNode?.text
}

fun getPresentation(element: JsltImportDeclaration): ItemPresentation {
    return object : ItemPresentation {
        override fun getPresentableText(): String = element.name ?: "<missing import alias>"
        override fun getLocationString(): String? = element.containingFile?.name
        override fun getIcon(unused: Boolean): Icon = AllIcons.Nodes.Module
    }
}

fun getPresentation(element: JsltLetAssignment): ItemPresentation {
    return object : ItemPresentation {
        override fun getPresentableText(): String = element.name ?: "<missing variable name>"
        override fun getLocationString(): String? = element.containingFile?.name
        override fun getIcon(unused: Boolean): Icon = AllIcons.Nodes.Variable
    }
}

fun getPresentation(element: JsltFunctionDecl): ItemPresentation {
    return object : ItemPresentation {
        override fun getPresentableText(): String = element.name ?: "<missing function name>"
        override fun getLocationString(): String? = element.containingFile?.name
        override fun getIcon(unused: Boolean): Icon = AllIcons.Nodes.Function
    }
}

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

fun getPresentation(element: JsltPair): ItemPresentation {
    return object : ItemPresentation {
        override fun getPresentableText(): String = element.name ?: "<missing key>"
        override fun getLocationString(): String? = element.containingFile?.name
        override fun getIcon(unused: Boolean): Icon = AllIcons.Nodes.Field
    }
}

fun getPresentation(element: JsltMatcher): ItemPresentation {
    return object : ItemPresentation {
        override fun getPresentableText(): String = element.text
        override fun getLocationString(): String? = element.containingFile?.name
        override fun getIcon(unused: Boolean): Icon = AllIcons.Nodes.ClassInitializer
    }
}
