package net.stefanfuchs.jslt.intellij.language

import com.intellij.lang.injection.InjectedLanguageManager
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiManager
import com.intellij.psi.PsiReferenceBase

class JsltImportDeclarationReference(element: PsiElement, textRange: TextRange) :
    PsiReferenceBase<PsiElement?>(element, textRange) {

    private val refFilename = element.text.substring(textRange.startOffset, textRange.endOffset)

    override fun resolve(): PsiElement? {
        val containingFile =
            if (InjectedLanguageManager
                    .getInstance(element.project)
                    .isInjectedFragment(element.containingFile)
            ) {
                InjectedLanguageManager
                    .getInstance(element.project)
                    .getInjectionHost(element)
                    ?.containingFile
            } else {
                element.containingFile
            }

        val referencedFile = containingFile?.parent?.virtualFile?.findFileByRelativePath(refFilename)

        return if (referencedFile != null) {
            PsiManager.getInstance(element.project).findFile(referencedFile)
        } else {
            null
        }
    }

}