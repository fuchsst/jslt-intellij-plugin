package net.stefanfuchs.jslt.intellij.language

import com.intellij.ide.projectView.PresentationData
import com.intellij.ide.structureView.StructureViewTreeElement
import com.intellij.ide.util.treeView.smartTree.SortableTreeElement
import com.intellij.ide.util.treeView.smartTree.TreeElement
import com.intellij.navigation.ItemPresentation
import com.intellij.psi.NavigatablePsiElement
import com.intellij.psi.PsiElement
import net.stefanfuchs.jslt.intellij.language.psi.*
import net.stefanfuchs.jslt.intellij.language.psi.impl.JsltExprImpl
import net.stefanfuchs.jslt.intellij.language.psi.impl.JsltImportDeclarationImpl


class JsltStructureViewElement(private val myElement: NavigatablePsiElement) : StructureViewTreeElement,
    SortableTreeElement {

    override fun getValue(): Any = myElement

    override fun navigate(requestFocus: Boolean) {
        myElement.navigate(requestFocus)
    }

    override fun canNavigate(): Boolean = myElement.canNavigate()

    override fun canNavigateToSource(): Boolean = myElement.canNavigateToSource()

    override fun getAlphaSortKey(): String = myElement.name ?: ""

    override fun getPresentation(): ItemPresentation = myElement.presentation ?: PresentationData()

    override fun getChildren(): Array<TreeElement> {
        val result = mutableListOf<JsltStructureViewElement>()
        if (myElement is JsltFile ||
            myElement is JsltLetAssignment ||
            myElement is JsltFunctionDecl ||
            myElement is JsltArray ||
            myElement is JsltObject ||
            myElement is JsltObjectComprehension ||
            myElement is JsltIfStatement ||
            myElement is JsltElseBranch ||
            myElement is JsltElseIfBranch
        ) {
            var child: PsiElement? = myElement.firstChild
            while (child != null) {
                when (child) {
                    is JsltImportDeclarations -> {
                        child.children.forEach { result.add(JsltStructureViewElement(it as JsltImportDeclarationImpl)) }
                    }
                    is JsltLetAssignment -> {
                        result.add(JsltStructureViewElement(child as NavigatablePsiElement))
                    }
                    is JsltFunctionDecl -> {
                        result.add(JsltStructureViewElement(child as NavigatablePsiElement))
                    }
                    is JsltFunctionBody -> {
                        child.letAssignmentList.forEach { result.add(JsltStructureViewElement(it as NavigatablePsiElement)) }
                        addIfArrayOrObjectInExpr(child.expr, result)
                    }
                    is JsltExprImpl -> {
                        addIfArrayOrObjectInExpr(child, result)
                    }
                    is JsltArrayBody -> {
                        addArrayBodyChild(child, result)
                    }
                    is JsltObjectBody -> {
                        addObjectBodyChild(child, result)
                    }
                    is JsltObjectComprehensionBody -> {
                        addObjectComprehensionBodyChild(child, result)
                    }
                }
                child = child.nextSibling
            }
        }
        return result.toTypedArray()
    }

    private fun addArrayBodyChild(
        child: JsltArrayBody,
        result: MutableList<JsltStructureViewElement>,
    ) {
        child.arrayFor?.arrayForBody?.letAssignmentList?.forEach {
            result.add(JsltStructureViewElement(it as NavigatablePsiElement))
        }
        child.expressions.forEach {
            addIfArrayOrObjectInExpr(it, result)
        }
    }

    private fun addObjectBodyChild(
        child: JsltObjectBody,
        result: MutableList<JsltStructureViewElement>,
    ) {
        child.letAssignmentList.forEach {
            result.add(JsltStructureViewElement(it as NavigatablePsiElement))
        }
        child.pairs?.pairList?.forEach {
            result.add(JsltStructureViewElement(it as NavigatablePsiElement))
        }
        if (child.pairs?.matcher != null) {
            result.add(JsltStructureViewElement(child.pairs?.matcher as NavigatablePsiElement))
        }
        if (child.matcher != null) {
            result.add(JsltStructureViewElement(child.matcher as NavigatablePsiElement))
        }
    }

    private fun addObjectComprehensionBodyChild(
        child: JsltObjectComprehensionBody,
        result: MutableList<JsltStructureViewElement>,
    ) {
        addIfArrayOrObjectInExpr(child.parenthesisExpr.expr, result)
        child.objectComprehensionForBody.letAssignmentList.forEach {
            result.add(JsltStructureViewElement(it as NavigatablePsiElement))
        }
        child.objectComprehensionForBody.pair.exprList.forEach {
            addIfArrayOrObjectInExpr(it, result)
        }
        if (child.objectComprehensionForBody.parenthesisExpr?.expr != null) {
            result.add(JsltStructureViewElement(child.objectComprehensionForBody.parenthesisExpr!!.expr as NavigatablePsiElement))
        }
    }

    private fun addIfArrayOrObjectInExpr(child: PsiElement?, targetResultList: MutableList<JsltStructureViewElement>) {
        var exprChild = child?.firstChild
        while (
            exprChild != null &&
            exprChild !is JsltArray &&
            exprChild !is JsltObject &&
            exprChild !is JsltObjectComprehension
        ) {
            exprChild = exprChild.firstChild
        }
        if (exprChild != null) {
            targetResultList.add(JsltStructureViewElement(exprChild as NavigatablePsiElement))
        }
    }
}
