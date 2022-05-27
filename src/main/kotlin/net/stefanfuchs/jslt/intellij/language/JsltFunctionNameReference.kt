package net.stefanfuchs.jslt.intellij.language

import com.intellij.openapi.util.TextRange
import com.intellij.psi.*
import com.intellij.psi.util.childrenOfType
import com.intellij.psi.util.elementType
import net.stefanfuchs.jslt.intellij.language.psi.*

class JsltFunctionNameReference(element: PsiElement, textRange: TextRange) :
    PsiReferenceBase<PsiElement?>(element, textRange), PsiPolyVariantReference {

    private val importAlias: String? = (element as JsltFunctionName).importAlias
    private val functionName = (element as JsltFunctionName).name

    override fun resolve(): PsiElement? {
        val resolveResults = multiResolve(false)
        return resolveResults.firstOrNull { it.element is JsltFunctionDeclNameDecl }?.element
    }

    override fun multiResolve(incompleteCode: Boolean): Array<ResolveResult> {
        val file = myElement!!.containingFile

        if (importAlias != null) {
            val importDeclarations = file.childrenOfType<JsltImportDeclarations>().firstOrNull()
            if (importDeclarations != null) {
                val importDecl = importDeclarations.importDeclarationList.firstOrNull { it.name == importAlias }
                if (importDecl != null) {
                    val result: MutableList<ResolveResult> =
                        arrayListOf(PsiElementResolveResult(importDecl.nameIdentifier as PsiElement))
                    val importFile = importDecl.reference.resolve() as PsiFile?
                    if (importFile != null) {
                        val functionName = findFunctionDeclNameInFile(importFile)
                        if (functionName != null) {
                            result.add(PsiElementResolveResult(functionName as PsiElement))
                        }
                    }
                    return result.toTypedArray()
                }
            }
        } else {
            val functionName = findFunctionDeclNameInFile(file)
            if (functionName != null) {
                return arrayOf(PsiElementResolveResult(findFunctionDeclNameInFile(file) as PsiElement))
            }

        }

        return emptyArray()
    }

    private fun findFunctionDeclNameInFile(file: PsiFile): JsltFunctionDeclNameDecl? {
        val functionDecl = file
            .children
            .filter { it.elementType == JsltTypes.FUNCTION_DECL }
            .firstOrNull { (it as JsltFunctionDecl).name == functionName }
        return (functionDecl as JsltFunctionDecl?)?.functionDeclNameDecl
    }

    override fun handleElementRename(newElementName: String): PsiElement {
        return (myElement as JsltFunctionName).setName(newElementName)
    }


}