package net.stefanfuchs.jslt.intellij.language

import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.patterns.PlatformPatterns
import com.intellij.util.ProcessingContext
import net.stefanfuchs.jslt.intellij.language.psi.JsltTypes

class JsltCompletionContributor : CompletionContributor() {
    init {
        extend(CompletionType.BASIC, PlatformPatterns.psiElement(JsltTypes.NULL),
            object : CompletionProvider<CompletionParameters>() {
                public override fun addCompletions(
                    parameters: CompletionParameters,
                    context: ProcessingContext,
                    resultSet: CompletionResultSet,
                ) {
                    resultSet.addElement(LookupElementBuilder.create("null"))
                }
            }
        )
    }
}