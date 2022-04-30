package net.stefanfuchs.jslt.intellij.language.psi.impl

import com.intellij.lang.ASTNode
import net.stefanfuchs.jslt.intellij.language.psi.JsltLetAssignment
import net.stefanfuchs.jslt.intellij.language.psi.JsltTypes


object JsltPsiImplUtil {

    @JvmStatic
    fun getVariableName(element: JsltLetAssignment): String? {
        val keyNode: ASTNode? = element.node.findChildByType(JsltTypes.VARIABLE_DECL)
        return keyNode?.text
    }

}

