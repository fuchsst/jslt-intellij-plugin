package net.stefanfuchs.jslt.intellij.language

import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.icons.AllIcons
import com.intellij.patterns.PlatformPatterns
import com.intellij.psi.util.childrenOfType
import com.intellij.util.ProcessingContext
import com.schibsted.spt.data.jslt.impl.BuiltinFunctions
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
                    // Add language keywords
                    resultSet.addElement(LookupElementBuilder.create("false").bold())
                    resultSet.addElement(LookupElementBuilder.create("true").bold())
                    resultSet.addElement(LookupElementBuilder.create("null").bold())
                    resultSet.addElement(LookupElementBuilder.create("let").bold())
                    resultSet.addElement(LookupElementBuilder.create("if").bold())
                    resultSet.addElement(LookupElementBuilder.create("else").bold())
                    resultSet.addElement(LookupElementBuilder.create("for").bold())
                    resultSet.addElement(LookupElementBuilder.create("import").bold())
                    resultSet.addElement(LookupElementBuilder.create("def").bold())

                    // Add built-in functions with documentation
                    // Combine functions from JSLT library and our documentation
                    val allFunctions = (BuiltinFunctions.functions.keys + 
                        JsltBuiltinFunctionDocumentation.getAllFunctionNames()).toSet().sorted()
                    
                    allFunctions.forEach { funcName ->
                        val doc = JsltBuiltinFunctionDocumentation.getShortDescription(funcName)
                        resultSet.addElement(
                            LookupElementBuilder
                                .create(funcName)
                                .withIcon(AllIcons.Nodes.Function)
                                .withTypeText("built-in", true)
                                .appendTailText("()", true)
                                .withTailText(if (doc.isNotEmpty()) " - $doc" else "", true)
                        )
                    }

                    // Add user-defined functions
                    parameters
                        .originalFile
                        .childrenOfType<JsltFunctionDecl>()
                        .forEach {
                            val paramCount = it.functionDeclParamList?.functionDeclParamDeclList?.size ?: 0
                            val paramText = if (paramCount == 0) "()" else "($paramCount param${if (paramCount > 1) "s" else ""})"
                            resultSet.addElement(
                                LookupElementBuilder
                                    .create(it.name!!)
                                    .withIcon(AllIcons.Nodes.Method)
                                    .withTypeText(it.containingFile.name)
                                    .appendTailText(paramText, true)
                            )
                        }
                }
            }
        )
    }
}
