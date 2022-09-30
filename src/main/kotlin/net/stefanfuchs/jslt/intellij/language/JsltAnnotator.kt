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
            is JsltLetVariableDecl -> annotateLetVariableDecl(element, holder)
            is JsltFunctionName -> annotateFunctionName(element, holder)
            is JsltFunctionDeclNameDecl -> annotateFunctionDeclNameDecl(element, holder)
            is JsltFunctionDeclParamDecl -> annotateFunctionDeclParamDecl(element, holder)
            is JsltImportDeclaration -> annotateImportDecl(element, holder)
            is JsltPair -> annotatePair(element, holder)
        }

    }

    private fun annotateVariableUsage(element: JsltVariableUsage, holder: AnnotationHolder) {
        val varDecl = element.reference.resolve()
        if (varDecl == null) {
            holder.newAnnotation(HighlightSeverity.WARNING, "Unknown variable").create()
        } else if (varDecl.elementType == JsltTypes.FUNCTION_DECL_PARAM_DECL) {
            holder
                .newAnnotation(HighlightSeverity.INFORMATION, "Function parameter")
                .range(element.textRange)
                .textAttributes(JsltSyntaxHighlighter.PARAMETER)
                .create()
        } else {
            val letAssignment = varDecl.parent
            if (letAssignment.parent !is JsltFile) {
                holder
                    .newAnnotation(HighlightSeverity.INFORMATION, "Local variable")
                    .range(element.textRange)
                    .textAttributes(JsltSyntaxHighlighter.LOCAL_VARIABLE)
                    .create()
            }
        }
    }

    private fun annotateLetVariableDecl(element: JsltLetVariableDecl, holder: AnnotationHolder) {
        val letAssignment = element.parent
        val nameCount = letAssignment.parent.childrenOfType<JsltLetAssignment>().count { it.name == element.name }
        if (nameCount > 1) {
            holder
                .newAnnotation(HighlightSeverity.ERROR, "Duplicate variable declaration")
                .create()
        }
    }

    private fun annotateFunctionName(element: JsltFunctionName, holder: AnnotationHolder) {
        val funcDecl = element.reference.resolve()
        if (funcDecl !is JsltFunctionDeclNameDecl) {
            if (element.name in BuiltinFunctions.functions.keys) {
                holder
                    .newAnnotation(HighlightSeverity.INFORMATION, "Buildin function")
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
                    } else {
                        val importedFile = importDecl.node.findChildByType(JsltTypes.IMPORT_FILE_STRING)?.text
                        holder
                            .newAnnotation(HighlightSeverity.ERROR, "Function not found in imported file $importedFile")
                            .create()
                    }
                }

            }
        }
    }

    private fun annotateFunctionDeclNameDecl(element: JsltFunctionDeclNameDecl, holder: AnnotationHolder) {
        val nameCount = element.containingFile.childrenOfType<JsltFunctionDecl>().count { it.name == element.name }
        if (nameCount > 1) {
            holder.newAnnotation(HighlightSeverity.ERROR, "Duplicate function declaration").create()
        }
    }

    private fun annotateFunctionDeclParamDecl(element: JsltFunctionDeclParamDecl, holder: AnnotationHolder) {
        val paramNameCount = (element.parent as JsltFunctionDeclParamList)
            .functionDeclParamDeclList
            .count { it.name == element.name }
        if (paramNameCount > 1) {
            holder.newAnnotation(HighlightSeverity.ERROR, "Duplicate parameter declaration").create()
        }
    }

    private fun annotateImportDecl(element: JsltImportDeclaration, holder: AnnotationHolder) {
        if (element.reference.resolve() == null) {
            holder
                .newAnnotation(HighlightSeverity.ERROR, "Referenced import file not found!")
                .range(element.reference.absoluteRange)
                .create()
        }
        val aliasCount = element.parent.children.count { (it as JsltImportDeclaration).name == element.name }
        if (aliasCount > 1) {
            holder
                .newAnnotation(HighlightSeverity.ERROR, "Duplicate import alias")
                .range(element.nameIdentifier!!.textRange)
                .create()
        }
    }

    private fun annotatePair(element: JsltPair, holder: AnnotationHolder) {
        val elemParent = element.parent
        if (elemParent is JsltPairs) {
            val keyCount = elemParent
                .pairList
                .map { it.exprList.first().text }
                .count { it == element.exprList.first().text }
            if (keyCount > 1) {
                holder
                    .newAnnotation(HighlightSeverity.ERROR, "Duplicate key")
                    .range(element.exprList.first().textRange)
                    .create()
            }
        }
    }

}
