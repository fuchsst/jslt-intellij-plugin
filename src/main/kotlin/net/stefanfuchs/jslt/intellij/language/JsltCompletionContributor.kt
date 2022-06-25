package net.stefanfuchs.jslt.intellij.language

import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.icons.AllIcons
import com.intellij.patterns.PlatformPatterns
import com.intellij.psi.util.childrenOfType
import com.intellij.util.ProcessingContext
import net.stefanfuchs.jslt.intellij.language.psi.JsltFunctionDecl
import net.stefanfuchs.jslt.intellij.language.psi.JsltTypes

class JsltCompletionContributor : CompletionContributor() {
    init {
        extend(CompletionType.BASIC, PlatformPatterns.psiElement(JsltTypes.IDENT),
            object : CompletionProvider<CompletionParameters>() {
                public override fun addCompletions(
                    parameters: CompletionParameters,
                    context: ProcessingContext,
                    resultSet: CompletionResultSet,
                ) {
                    resultSet.addElement(LookupElementBuilder.create("false"))
                    resultSet.addElement(LookupElementBuilder.create("true"))
                    resultSet.addElement(LookupElementBuilder.create("null"))
                    println(parameters
                        .originalFile.name)
                    parameters
                        .originalFile
                        .childrenOfType<JsltFunctionDecl>()
                        .forEach {
                            resultSet.addElement(
                                LookupElementBuilder
                                    .create(it.name!!)
                                    .withIcon(AllIcons.Nodes.Method)
                                    .withTypeText(it.containingFile.name)
                                    .appendTailText("()", true)
                            )
                        }
                }
            }
        )

//        extend(CompletionType.BASIC, PlatformPatterns.psiElement(JsltTypes.FALSE),
//            object : CompletionProvider<CompletionParameters>() {
//                public override fun addCompletions(
//                    parameters: CompletionParameters,
//                    context: ProcessingContext,
//                    resultSet: CompletionResultSet,
//                ) {
//                    resultSet.addElement(LookupElementBuilder.create("false"))
//                }
//            }
//        )
//        extend(CompletionType.BASIC, PlatformPatterns.psiElement(JsltTypes.TRUE),
//            object : CompletionProvider<CompletionParameters>() {
//                public override fun addCompletions(
//                    parameters: CompletionParameters,
//                    context: ProcessingContext,
//                    resultSet: CompletionResultSet,
//                ) {
//                    resultSet.addElement(LookupElementBuilder.create("true"))
//                }
//            }
//        )
//        extend(CompletionType.BASIC, PlatformPatterns.psiElement(JsltTypes.NULL),
//            object : CompletionProvider<CompletionParameters>() {
//                public override fun addCompletions(
//                    parameters: CompletionParameters,
//                    context: ProcessingContext,
//                    resultSet: CompletionResultSet,
//                ) {
//                    resultSet.addElement(LookupElementBuilder.create("null"))
//                }
//            }
//        )

//        extend(CompletionType.BASIC, PlatformPatterns.psiElement(JsltTypes.IDENT),
//            object : CompletionProvider<CompletionParameters>() {
//                public override fun addCompletions(
//                    parameters: CompletionParameters,
//                    context: ProcessingContext,
//                    resultSet: CompletionResultSet,
//                ) {
//                    println(parameters.originalFile)
//                    parameters
//                        .originalFile
//                        .childrenOfType<JsltFunctionDecl>()
//                        .forEach {
//                            resultSet.addElement(
//                                LookupElementBuilder
//                                    .create(it.name!!)
//                                    .withIcon(AllIcons.Nodes.Method)
//                                    .withTypeText(it.containingFile.name)
//                                    .appendTailText("()", true)
//                            )
//                        }
//                }
//            }
//        )
    }
}
