@file:JvmName("JsltPsiImplUtil")
@file:JvmMultifileClass

package net.stefanfuchs.jslt.intellij.language.psi.impl

import com.intellij.icons.AllIcons
import com.intellij.lang.ASTNode
import com.intellij.navigation.ItemPresentation
import com.intellij.psi.PsiElement
import net.stefanfuchs.jslt.intellij.language.psi.*
import javax.swing.Icon



fun getName(element: JsltFunctionDecl): String? {
    val keyNode: ASTNode? = element.node.findChildByType(JsltTypes.FUNCTION_DECL_NAME)
    return keyNode?.text
}

fun getName(element: JsltLetAssignment): String? {
    val keyNode: ASTNode? = element.node.findChildByType(JsltTypes.VARIABLE_DECL)
    return keyNode?.text
}

fun setName(element: JsltLetAssignment, newAlias: String): PsiElement {
    val aliasASTNode: ASTNode? = element.node.findChildByType(JsltTypes.VARIABLE_DECL)
    if (aliasASTNode != null) {
        val letAssignment = JsltVariableElementFactory.createVariableDeclaration(element.project, newAlias)
        val newAliasASTNode: ASTNode = letAssignment.node.findChildByType(JsltTypes.VARIABLE_DECL)!!
        element.node.replaceChild(aliasASTNode, newAliasASTNode)
    }
    return element
}

fun getNameIdentifier(element: JsltLetAssignment): PsiElement? {
    val keyNode: ASTNode? = element.node.findChildByType(JsltTypes.VARIABLE_DECL)
    return keyNode?.psi
}


fun getName(element: JsltFunctionCall): String? {
    val functionNameASTNode: ASTNode? = element.node.findChildByType(JsltTypes.FUNCTION_NAME)?.firstChildNode
    return when (functionNameASTNode?.elementType) {
        JsltTypes.IDENT -> functionNameASTNode?.text
        JsltTypes.PIDENT -> functionNameASTNode?.text?.substringAfter(':')
        else -> null
    }
}

fun getImportAlias(element: JsltFunctionCall): String? {
    val functionNameASTNode: ASTNode? = element.node.findChildByType(JsltTypes.FUNCTION_NAME)?.firstChildNode
    return when (functionNameASTNode?.elementType) {
        JsltTypes.PIDENT -> functionNameASTNode?.text?.substringBefore(':')
        else -> null
    }
}

//JsltFunctionCall: setImportAlias setName


fun getExpressions(element: JsltArrayBody): List<JsltExpr> {
    val result = mutableListOf<JsltExpr>()
    if (element.parenthesisExpr?.expr != null) {
        result.add(element.parenthesisExpr!!.expr)
    }
    if (element.arrayForBody?.expr != null) {
        result.add(element.arrayForBody!!.expr)
    }
    if (element.arrayForBody?.parenthesisExpr?.expr != null) {
        result.add(element.arrayForBody!!.parenthesisExpr!!.expr)
    }
    if (element.arrayElements != null) {
        element.arrayElements?.exprList?.forEach {
            result.add(it)
        }
    }
    return result
}

fun getExpr(element: JsltFunctionDecl): JsltExpr =
    element.functionBody.expr


fun getName(element: JsltPair): String? {
    val keyNode: ASTNode? = element.node.findChildByType(JsltTypes.EXPR)
    return keyNode?.text?.trim('"')
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
        override fun getIcon(unused: Boolean): Icon = AllIcons.Nodes.Method
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
