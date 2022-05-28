package net.stefanfuchs.jslt.intellij.language

import com.intellij.patterns.ElementPattern
import com.intellij.patterns.PlatformPatterns.psiElement
import com.intellij.psi.PsiElement
import com.intellij.refactoring.rename.RenameInputValidator
import com.intellij.util.ProcessingContext
import net.stefanfuchs.jslt.intellij.language.psi.*


abstract class JsltIdentRenameInputValidator : RenameInputValidator {
    override fun isInputValid(
        newName: String,
        element: PsiElement,
        context: ProcessingContext,
    ): Boolean {
        return newName.matches("[a-zA-Z][A-Za-z\\d_\\-]*".toRegex())
    }
}

class JsltLetVariableDeclRenameInputValidator : JsltIdentRenameInputValidator() {
    override fun getPattern(): ElementPattern<out PsiElement?> = psiElement(JsltLetVariableDecl::class.java)
}

class JsltVariableUsageRenameInputValidator : JsltIdentRenameInputValidator() {
    override fun getPattern(): ElementPattern<out PsiElement?> = psiElement(JsltVariableUsage::class.java)
}

class JsltFunctionDeclNameDeclRenameInputValidator : JsltIdentRenameInputValidator() {
    override fun getPattern(): ElementPattern<out PsiElement?> = psiElement(JsltFunctionDeclNameDecl::class.java)
}

class JsltFunctionDeclParamDeclRenameInputValidator : JsltIdentRenameInputValidator() {
    override fun getPattern(): ElementPattern<out PsiElement?> = psiElement(JsltFunctionDeclParamDecl::class.java)
}

class JsltFunctionNameRenameInputValidator : JsltIdentRenameInputValidator() {
    override fun getPattern(): ElementPattern<out PsiElement?> = psiElement(JsltFunctionName::class.java)
}