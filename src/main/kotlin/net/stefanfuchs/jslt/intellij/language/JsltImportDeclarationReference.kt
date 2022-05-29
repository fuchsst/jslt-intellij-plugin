package net.stefanfuchs.jslt.intellij.language

import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiManager
import com.intellij.psi.PsiReferenceBase

class JsltImportDeclarationReference(element: PsiElement, textRange: TextRange) :
    PsiReferenceBase<PsiElement?>(element, textRange) {

    private val refFilename = element.text.substring(textRange.startOffset, textRange.endOffset)

    override fun resolve(): PsiElement? {
        val sourceFile = myElement!!.containingFile

        val referencedFile = sourceFile?.virtualFile?.parent?.findFileByRelativePath(refFilename)

        return if (referencedFile != null) {
            PsiManager.getInstance(myElement.project).findFile(referencedFile)
        } else {
            null
        }
    }

}