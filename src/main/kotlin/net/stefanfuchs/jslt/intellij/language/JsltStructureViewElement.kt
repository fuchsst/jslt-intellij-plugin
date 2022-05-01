package net.stefanfuchs.jslt.intellij.language

import com.intellij.ide.projectView.PresentationData
import com.intellij.ide.structureView.StructureViewTreeElement
import com.intellij.ide.util.treeView.smartTree.SortableTreeElement
import com.intellij.ide.util.treeView.smartTree.TreeElement
import com.intellij.navigation.ItemPresentation
import com.intellij.psi.NavigatablePsiElement
import com.intellij.psi.PsiElement
import net.stefanfuchs.jslt.intellij.language.psi.*
import net.stefanfuchs.jslt.intellij.language.psi.impl.*


class JsltStructureViewElement(private val myElement: NavigatablePsiElement) :
    StructureViewTreeElement, SortableTreeElement {

    override fun getValue(): Any = myElement

    override fun navigate(requestFocus: Boolean) {
        myElement.navigate(requestFocus)
    }

    override fun canNavigate(): Boolean = myElement.canNavigate()

    override fun canNavigateToSource(): Boolean = myElement.canNavigateToSource()

    override fun getAlphaSortKey(): String = myElement.name ?: ""

    override fun getPresentation(): ItemPresentation = myElement.presentation ?: PresentationData()

    override fun getChildren(): Array<TreeElement> {
        if (myElement is JsltFile ||
            myElement is JsltFunctionDecl ||
            myElement is JsltLetAssignment ||
            myElement is JsltArray ||
            myElement is JsltArrayElem ||
            myElement is JsltObject ||
            myElement is JsltObjectComprehension ||
            myElement is JsltPair ||
            myElement is JsltMatcher
        ) {
            val result = mutableListOf<JsltStructureViewElement>()
            var child: PsiElement? = myElement.firstChild
            while (child != null) {
                when (child) {
                    is JsltImportDeclarations -> {
                        child.children.forEach { result.add(JsltStructureViewElement(it as JsltImportDeclarationImpl)) }
                    }
                    is JsltLetAssignmentImpl -> {
                        result.add(JsltStructureViewElement(child))
                    }
                    is JsltFunctionDeclImpl -> {
                        result.add(JsltStructureViewElement(child))
                    }
                    is JsltMatcherImpl -> {
                        result.add(JsltStructureViewElement(child))
                    }
                    is JsltPairImpl -> {
                        if (myElement !is JsltPairImpl) {
                            val entries = mutableListOf<PsiElement>()
                            findPairsInPair(child, entries)
                            entries.forEach { result.add(JsltStructureViewElement(it as NavigatablePsiElement)) }
                        }
                    }
                    is JsltArrayImpl, is JsltArrayElemImpl -> {
                        findArrayOrObjectInArray(child)
                            .forEach { result.add(JsltStructureViewElement(it as NavigatablePsiElement)) }
                    }
                    is JsltExprImpl -> { // Arrays and Objects are "hidden" in an expression
                        val exprChild = findArrayOrObjectInExpr(child)
                        if (exprChild != null) {
                            result.add(JsltStructureViewElement(exprChild as NavigatablePsiElement))

                        }
                    }
                }
                child = child.nextSibling
            }

            return result.toTypedArray()
        } else {
            println(myElement::class.java)
            return emptyArray()
        }
    }

    private fun findPairsInPair(pair: PsiElement, resultList: MutableList<PsiElement>) {
        resultList.add(pair)
        val subPair = pair.children.firstOrNull { it is JsltPairImpl || it is JsltMatcherImpl }
        if (subPair != null) {
            findPairsInPair(subPair, resultList)
        }
    }

    private fun findArrayOrObjectInArray(child: PsiElement): List<PsiElement> {
        return child.children.flatMap {
            if (it is JsltExprImpl) {
                arrayListOf(findArrayOrObjectInExpr(it))
            } else {
                findArrayOrObjectInArray(it)
            }
        }.filterNotNull()
    }

    private fun findArrayOrObjectInExpr(child: PsiElement): PsiElement? {
        var exprChild = child.firstChild
        while (exprChild != null &&
            exprChild !is JsltArray &&
            exprChild !is JsltObject &&
            exprChild !is JsltObjectComprehension
        ) {
            exprChild = exprChild.firstChild
        }
        return exprChild
    }
}