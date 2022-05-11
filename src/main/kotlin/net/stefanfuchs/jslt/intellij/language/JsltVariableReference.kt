package net.stefanfuchs.jslt.intellij.language

import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.openapi.util.TextRange
import com.intellij.psi.*
import com.intellij.psi.util.elementType
import net.stefanfuchs.jslt.intellij.language.psi.JsltImportDeclaration
import net.stefanfuchs.jslt.intellij.language.psi.JsltTypes

class JsltVariableReference(element: PsiElement, textRange: TextRange) :
    PsiReferenceBase<PsiElement?>(element, textRange), PsiPolyVariantReference {
    private val alias: String

    init {
        alias = element.text.substring(textRange.startOffset, textRange.endOffset)
    }

    override fun multiResolve(incompleteCode: Boolean): Array<ResolveResult> {
        val file = myElement!!.containingFile

        // collect declarations
        val results=mutableListOf<PsiElementResolveResult>()
        var currentNode = file.firstChild.node
        while (currentNode != null) {
            if (currentNode.elementType == JsltTypes.VARIABLE) {
                results.add(PsiElementResolveResult(currentNode.psi))
            }
            currentNode = currentNode.treeNext
        }
        return results.toTypedArray()
    }

    override fun resolve(): PsiElement? {
        val resolveResults = multiResolve(false)
        return if (resolveResults.size == 1) resolveResults[0].element else null
    }

    override fun getVariants(): Array<Any> {
        val project = myElement!!.containingFile
        val variants: MutableList<LookupElement> = ArrayList()
//        val properties: List<SimpleProperty> = JsltUtil.findProperties(project)
//        for (property in properties) {
//            if (property.getKey() != null && property.getKey().length() > 0) {
//                variants.add(LookupElementBuilder
//                    .create(property).withIcon(JsltIcons.FileType)
//                    .withTypeText(property.getContainingFile().getName())
//                )
//            }
//        }
        return variants.toTypedArray()
    }
}