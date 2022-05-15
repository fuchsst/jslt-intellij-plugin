// Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package net.stefanfuchs.jslt.intellij.language

import com.intellij.lang.cacheBuilder.DefaultWordsScanner
import com.intellij.lang.cacheBuilder.WordsScanner
import com.intellij.lang.findUsages.FindUsagesProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNamedElement
import com.intellij.psi.tree.TokenSet
import net.stefanfuchs.jslt.intellij.language.psi.JsltFunctionDecl
import net.stefanfuchs.jslt.intellij.language.psi.JsltLetAssignment
import net.stefanfuchs.jslt.intellij.language.psi.JsltTypes
import net.stefanfuchs.jslt.intellij.language.psi.JsltVariableUsage

class JsltFindUsagesProvider : FindUsagesProvider {

    override fun getWordsScanner(): WordsScanner {
        return DefaultWordsScanner(
            JsltLexerAdapter(),
            TokenSet.create(
                JsltTypes.FUNCTION_DECL_NAME,
                JsltTypes.FUNCTION_DECL_PARAM,
                JsltTypes.IMPORT_ALIAS,
                JsltTypes.VARIABLE_DECL,
                JsltTypes.VARIABLE_USAGE),
            TokenSet.create(JsltTypes.COMMENT),
            TokenSet.create(
                JsltTypes.TRUE,
                JsltTypes.FALSE,
                JsltTypes.NULL,
                JsltTypes.INTEGER,
                JsltTypes.DECIMAL,
                JsltTypes.STRING),
            TokenSet.create(JsltTypes.IMPORT_FILE_STRING),
            TokenSet.EMPTY)
    }

    override fun canFindUsagesFor(psiElement: PsiElement): Boolean {
        return psiElement is PsiNamedElement
    }

    override fun getHelpId(psiElement: PsiElement): String? {
        return null
    }

    override fun getType(element: PsiElement): String {
        return when (element) {
            is JsltFunctionDecl -> "Function declaration"
            is JsltLetAssignment -> "Variable declaration"
            is JsltVariableUsage -> "Variable"
            else -> ""
        }
    }

    override fun getDescriptiveName(element: PsiElement): String {
        return when (element) {
            is JsltFunctionDecl -> element.name ?: ""
            else -> ""
        }
    }

    override fun getNodeText(element: PsiElement, useFullName: Boolean): String {
        return element.text
    }
}