package net.stefanfuchs.jslt.intellij.language

import com.intellij.lang.refactoring.RefactoringSupportProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.util.elementType
import net.stefanfuchs.jslt.intellij.language.psi.JsltTypes

class JsltRefactoringSupportProvider : RefactoringSupportProvider() {
    override fun isMemberInplaceRenameAvailable(elementToRename: PsiElement, context: PsiElement?): Boolean {
        return elementToRename.elementType == JsltTypes.LET_VARIABLE_DECL ||
                elementToRename.elementType == JsltTypes.FUNCTION_DECL_NAME ||
                elementToRename.elementType == JsltTypes.FUNCTION_DECL_PARAM_DECL
    }
}