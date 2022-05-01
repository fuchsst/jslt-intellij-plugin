package net.stefanfuchs.jslt.intellij.language

import com.intellij.ide.structureView.StructureViewModel.ElementInfoProvider
import com.intellij.ide.structureView.StructureViewModelBase
import com.intellij.ide.structureView.StructureViewTreeElement
import com.intellij.ide.util.treeView.smartTree.Sorter
import com.intellij.psi.PsiFile
import net.stefanfuchs.jslt.intellij.language.psi.JsltDotKey
import net.stefanfuchs.jslt.intellij.language.psi.JsltImportDeclaration
import net.stefanfuchs.jslt.intellij.language.psi.JsltObject

class JsltStructureViewModel(psiFile: PsiFile) :
    StructureViewModelBase(psiFile, JsltStructureViewElement(psiFile)), ElementInfoProvider {
    override fun getSorters(): Array<Sorter> {
        return arrayOf(Sorter.ALPHA_SORTER)
    }

    override fun isAlwaysShowsPlus(element: StructureViewTreeElement): Boolean {
        return false
    }

    override fun isAlwaysLeaf(element: StructureViewTreeElement): Boolean {
        return element.value is JsltDotKey || element.value is JsltImportDeclaration
    }
}