@file:JvmName("JsltPsiImplUtil")
@file:JvmMultifileClass

package net.stefanfuchs.jslt.intellij.language.psi.util

import com.intellij.icons.AllIcons
import com.intellij.lang.ASTNode
import com.intellij.navigation.ItemPresentation
import com.intellij.psi.PsiElement
import net.stefanfuchs.jslt.intellij.language.psi.JsltExpr
import net.stefanfuchs.jslt.intellij.language.psi.JsltFunctionDecl
import net.stefanfuchs.jslt.intellij.language.psi.JsltTypes
import javax.swing.Icon


fun getName(element: JsltFunctionDecl): String? {
    val functionNameASTNode: ASTNode? = element.node.findChildByType(JsltTypes.FUNCTION_DECL_NAME_DECL)
    return functionNameASTNode?.text
}

fun getNameIdentifier(element: JsltFunctionDecl): PsiElement? {
    val functionNameASTNode: ASTNode? = element.node.findChildByType(JsltTypes.FUNCTION_DECL_NAME_DECL)
    return functionNameASTNode?.psi
}


fun getExpr(element: JsltFunctionDecl): JsltExpr? =
    element.functionBody?.expr


fun getPresentation(element: JsltFunctionDecl): ItemPresentation {
    return object : ItemPresentation {
        override fun getPresentableText(): String = element.name ?: "<missing function name>"
        override fun getLocationString(): String? = element.containingFile?.name
        override fun getIcon(unused: Boolean): Icon = AllIcons.Nodes.Method
    }
}
