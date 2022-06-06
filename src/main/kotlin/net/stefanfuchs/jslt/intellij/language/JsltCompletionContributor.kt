package net.stefanfuchs.jslt.intellij.language

import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.patterns.PlatformPatterns
import com.intellij.psi.TokenType
import com.intellij.util.ProcessingContext

class JsltCompletionContributor : CompletionContributor() {
    init {
        extend(CompletionType.BASIC, PlatformPatterns.psiElement(TokenType.ERROR_ELEMENT),
            object : CompletionProvider<CompletionParameters>() {
                public override fun addCompletions(
                    parameters: CompletionParameters,
                    context: ProcessingContext,
                    resultSet: CompletionResultSet,
                ) {
                    resultSet.addElement(LookupElementBuilder.create("null"))
                    resultSet.addElement(LookupElementBuilder.create("true"))
                    resultSet.addElement(LookupElementBuilder.create("false"))

                }
            }
        )
    }
}
