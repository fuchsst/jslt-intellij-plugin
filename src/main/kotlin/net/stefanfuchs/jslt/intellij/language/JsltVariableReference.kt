package net.stefanfuchs.jslt.intellij.language

import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNameIdentifierOwner
import com.intellij.psi.PsiReferenceBase
import com.intellij.psi.util.elementType
import com.intellij.psi.util.findParentOfType
import net.stefanfuchs.jslt.intellij.language.psi.*

class JsltVariableReference(element: PsiElement, textRange: TextRange) :
    PsiReferenceBase<PsiElement?>(element, textRange) {

    private val variableName = element.text.substring(textRange.startOffset, textRange.endOffset)

    override fun resolve(): PsiElement? {
        return findVariableDeclarationInFunction() ?: findGlobalVariableDeclaration()
    }

    private fun findGlobalVariableDeclaration(): JsltLetVariableDecl? {
        var parent = myElement?.parent
        while (parent != null) {
            if (parent is JsltFile ||
                parent is JsltArrayForBody ||
                parent is JsltObjectBody ||
                parent is JsltObjectComprehensionForBody
            ) {
                val letAssignment: JsltLetAssignment? = parent
                    .children
                    .filter { it.elementType == JsltTypes.LET_ASSIGNMENT }
                    .map { it as JsltLetAssignment }
                    .firstOrNull { it.name == (myElement as JsltVariableUsage).name }
                if (letAssignment != null) {
                    return letAssignment.letVariableDecl
                }
            }
            parent = parent.parent
        }
        return null
    }

    private fun findVariableDeclarationInFunction(): PsiNameIdentifierOwner? {
        val parentFunction = myElement?.findParentOfType<JsltFunctionDecl>()
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
        return null
    }

    override fun handleElementRename(newElementName: String): PsiElement {
        return (myElement as JsltVariableUsage).setName(newElementName)
    }

}