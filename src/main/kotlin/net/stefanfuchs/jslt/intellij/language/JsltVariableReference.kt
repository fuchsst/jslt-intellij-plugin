package net.stefanfuchs.jslt.intellij.language

import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReferenceBase
import com.intellij.psi.util.elementType
import com.intellij.psi.util.findParentOfType
import net.stefanfuchs.jslt.intellij.language.psi.JsltFunctionDecl
import net.stefanfuchs.jslt.intellij.language.psi.JsltLetAssignment
import net.stefanfuchs.jslt.intellij.language.psi.JsltTypes
import net.stefanfuchs.jslt.intellij.language.psi.JsltVariableUsage

class JsltVariableReference(element: PsiElement, textRange: TextRange) :
    PsiReferenceBase<PsiElement?>(element, textRange) {

    private val variableName = element.text.substring(textRange.startOffset, textRange.endOffset)

    override fun resolve(): PsiElement? {
        val file = myElement!!.containingFile
        val parentFunction = myElement.findParentOfType<JsltFunctionDecl>()
        if (parentFunction != null) {
            val localVariableDecl = parentFunction
                .functionBody
                .letAssignmentList
                .firstOrNull { it.name == variableName }
                ?.letVariableDecl
            if (localVariableDecl != null) {
                return localVariableDecl
            } else {
                val functionParamDecl = parentFunction
                    .functionDeclParamList
                    ?.functionDeclParamDeclList
                    ?.firstOrNull { it.name == variableName }
                if (functionParamDecl != null) {
                    return functionParamDecl
                }
            }
        }
        val letAssignment: JsltLetAssignment? = file
            .children
            .filter { it.elementType == JsltTypes.LET_ASSIGNMENT }
            .map { it as JsltLetAssignment }
            .firstOrNull { it.name == (myElement as JsltVariableUsage).name }
        return letAssignment?.letVariableDecl
    }

    override fun handleElementRename(newElementName: String): PsiElement {
        return (myElement as JsltVariableUsage).setName(newElementName)
    }

}