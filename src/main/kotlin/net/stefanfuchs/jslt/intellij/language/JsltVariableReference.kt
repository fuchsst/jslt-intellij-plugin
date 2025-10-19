package net.stefanfuchs.jslt.intellij.language

import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.icons.AllIcons
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNameIdentifierOwner
import com.intellij.psi.PsiNamedElement
import com.intellij.psi.PsiReferenceBase
import com.intellij.psi.util.elementType
import net.stefanfuchs.jslt.intellij.language.psi.*


class JsltVariableReference(element: PsiElement, textRange: TextRange) :
    PsiReferenceBase<PsiElement?>(element, textRange) {

    private val variableName = element.text.substring(textRange.startOffset, textRange.endOffset)

    override fun resolve(): PsiElement? {
        var parent = myElement?.parent
        while (parent != null) {
            if (parent is JsltFile ||
                parent is JsltArrayForBody ||
                parent is JsltObjectBody ||
                parent is JsltObjectComprehensionForBody
            ) {
                val letAssignment = findGlobalVariableDeclaration(parent)
                if (letAssignment != null) {
                    return letAssignment.letVariableDecl
                }
            } else if (parent is JsltFunctionBody) {
                // Check for let assignments in FunctionBody (used in if/else branches)
                val letAssignment = parent.letAssignmentList.firstOrNull { it.name == variableName }
                if (letAssignment != null) {
                    return letAssignment.letVariableDecl
                }
            } else if (parent is JsltFunctionDecl) {
                val varOrParam = findVariableDeclarationInFunction(parent)
                if (varOrParam != null) {
                    return varOrParam
                }
            }
            parent = parent.parent
        }
        return null
    }

    override fun getVariants(): Array<LookupElement> {
        val variableDeclList: MutableList<PsiNamedElement> = ArrayList()

        var parent = element.parent
        while (parent != null) {
            if (parent is JsltFile ||
                parent is JsltArrayForBody ||
                parent is JsltObjectBody ||
                parent is JsltObjectComprehensionForBody
            ) {
                val letAssignment = parent
                    .children
                    .filter { it.elementType == JsltTypes.LET_ASSIGNMENT }
                    .map { it as JsltLetAssignment }
                    .filter { letAssignment -> letAssignment.name !in variableDeclList.map { it.name } }

                variableDeclList.addAll(letAssignment)
            } else if (parent is JsltFunctionBody) {
                // Include let assignments from FunctionBody (used in if/else branches)
                val letAssignment = parent
                    .letAssignmentList
                    .filter { letAssignment -> letAssignment.name !in variableDeclList.map { it.name } }
                
                variableDeclList.addAll(letAssignment)
            } else if (parent is JsltFunctionDecl) {
                val localVariableDecl = parent
                    .functionBody
                    ?.letAssignmentList
                    ?.filter { letAssignment -> letAssignment.name !in variableDeclList.map { it.name } }

                variableDeclList.addAll(localVariableDecl ?: emptyList())

                val functionParamDecl = parent
                    .functionDeclParamList
                    ?.functionDeclParamDeclList
                    ?.filter { param -> param.name !in variableDeclList.map { it.name } }
                if (functionParamDecl?.isNotEmpty() == true) {
                    variableDeclList.addAll(functionParamDecl)
                }
            }
            parent = parent.parent
        }

        return variableDeclList
            .map {
                val icon = if (it is JsltLetAssignment) {
                    AllIcons.Nodes.Variable
                } else {
                    AllIcons.Nodes.Parameter
                }

                LookupElementBuilder
                    .createWithSmartPointer(it.name!!, it)
                    .withIcon(icon)
                    .withLookupString(it.name!!)
                    .withTypeText(it.containingFile.name)
            }.toTypedArray()
    }

    private fun findGlobalVariableDeclaration(parentElem: PsiElement): JsltLetAssignment? = parentElem
        .children
        .filter { it.elementType == JsltTypes.LET_ASSIGNMENT }
        .map { it as JsltLetAssignment }
        .firstOrNull { it.name == (myElement as JsltVariableUsage).name }

    private fun findVariableDeclarationInFunction(functionDecl: JsltFunctionDecl): PsiNameIdentifierOwner? {
        val localVariableDecl = functionDecl
            .functionBody
            ?.letAssignmentList
            ?.firstOrNull { it.name == variableName }
            ?.letVariableDecl
        if (localVariableDecl != null) {
            return localVariableDecl
        } else {
            val functionParamDecl = functionDecl
                .functionDeclParamList
                ?.functionDeclParamDeclList
                ?.firstOrNull { it.name == variableName }
            if (functionParamDecl != null) {
                return functionParamDecl
            }
        }
        return null
    }

    override fun handleElementRename(newElementName: String): PsiElement {
        return (myElement as JsltVariableUsage).setName(newElementName)
    }

}
