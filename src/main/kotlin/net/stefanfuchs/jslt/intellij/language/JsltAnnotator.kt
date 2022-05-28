package net.stefanfuchs.jslt.intellij.language

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.psi.PsiElement
import com.intellij.psi.util.childrenOfType
import com.intellij.psi.util.elementType
import com.schibsted.spt.data.jslt.impl.BuiltinFunctions
import net.stefanfuchs.jslt.intellij.language.psi.*


class JsltAnnotator : Annotator {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        when (element) {
            is JsltVariableUsage -> annotateVariableUsage(element, holder)
            is JsltFunctionName -> annotateFunctionName(element, holder)
            is JsltImportDeclaration -> annotateImportDecl(element, holder)
        }

    }

    private fun annotateVariableUsage(element: JsltVariableUsage, holder: AnnotationHolder) {
        val varDecl = element.reference.resolve()
        if (varDecl == null) {
            holder.newAnnotation(HighlightSeverity.ERROR, "Undefined variable").create()
        } else if (varDecl.elementType == JsltTypes.FUNCTION_DECL_PARAM_DECL) {
            val functionDeclParamList = varDecl.parent
            val functionDecl = functionDeclParamList?.parent
            val functionName = (functionDecl as JsltFunctionDecl).name
            holder
                .newAnnotation(HighlightSeverity.INFORMATION, "Function parameter of $functionName")
                .range(element.textRange)
                .textAttributes(JsltSyntaxHighlighter.PARAMETER)
                .create()
        } else {
            val letAssignment = varDecl.parent
            val functionBody = letAssignment?.parent
            if (functionBody?.elementType == JsltTypes.FUNCTION_BODY) {
                val functionDecl = functionBody?.parent
                val functionName = (functionDecl as JsltFunctionDecl).name
                holder
                    .newAnnotation(HighlightSeverity.INFORMATION, "Local variable of $functionName")
                    .range(element.textRange)
                    .textAttributes(JsltSyntaxHighlighter.LOCAL_VARIABLE)
                    .create()
            }
        }
    }

    private fun annotateFunctionName(element: JsltFunctionName, holder: AnnotationHolder) {
        val funcDecl = element.reference.resolve()
        if (funcDecl == null) {
            if (element.name in BuiltinFunctions.functions.keys) {
                holder
                    .newAnnotation(HighlightSeverity.INFORMATION, "Buildin Function")
                    .range(element.textRange)
                    .textAttributes(JsltSyntaxHighlighter.BUILDIN_FUNCTION_NAME)
                    .create()
            } else {
                if (element.firstChild.elementType == JsltTypes.IDENT) {
                    holder.newAnnotation(HighlightSeverity.WARNING, "Undefined function").create()
                } else {
                    val importAlias = element.importAlias
                    val importDecl = element
                        .containingFile
                        .childrenOfType<JsltImportDeclarations>()
                        .firstOrNull()
                        ?.importDeclarationList
                        ?.firstOrNull { it.name == importAlias }
                    if (importDecl == null) {
                        holder
                            .newAnnotation(HighlightSeverity.ERROR, "Undefined import alias $importAlias")
                            .create()
                    }
                }

            }
        }
    }

    private fun annotateImportDecl(element: JsltImportDeclaration, holder: AnnotationHolder) {
        if (element.reference.resolve() == null) {
            holder.newAnnotation(HighlightSeverity.ERROR, "Referenced import file not found!").create()
        }
    }

}