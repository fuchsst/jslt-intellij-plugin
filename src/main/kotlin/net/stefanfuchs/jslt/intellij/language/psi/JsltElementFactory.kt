package net.stefanfuchs.jslt.intellij.language.psi

import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFileFactory
import com.intellij.psi.util.childrenOfType
import com.intellij.util.IncorrectOperationException
import net.stefanfuchs.jslt.intellij.language.JsltFile
import net.stefanfuchs.jslt.intellij.language.JsltFileType

object JsltElementFactory {
    fun createVariableUsage(project: Project?, name: String): JsltVariableUsage {
        val file: JsltFile = createFile(project, "\$$name")
        var node = file.childrenOfType<JsltExpr>().firstOrNull()?.firstChild
        while (node != null) {
            if (node is JsltVariableUsage) {
                return node
            }
            node = node.firstChild
        }
        throw IncorrectOperationException("Could not build AST Node!")
    }

    fun createFunctionCallName(project: Project?, alias: String?, name: String): JsltFunctionName {
        val file: JsltFile = if (alias != null) {
            createFile(project, "$alias:$name()")
        } else {
            createFile(project, "$name()")
        }
        var node = file.childrenOfType<JsltExpr>().firstOrNull()?.firstChild
        while (node != null) {
            if (node is JsltFunctionName) {
                return node
            }
            node = node.firstChild
        }
        throw IncorrectOperationException("Could not build AST Node!")
    }

    fun createImportDecl(project: Project?, name: String): JsltImportDeclaration {
        val file: JsltFile = createFile(project, "import \"dummy.jslt\" as $name")
        val importDeclarations = file.childrenOfType<JsltImportDeclarations>().first()
        return importDeclarations.importDeclarationList.first()
    }

    fun createLetVariableDecl(project: Project?, name: String): JsltLetVariableDecl {
        val file: JsltFile = createFile(project, "let $name = 1")
        val letAssignment = file.childrenOfType<JsltLetAssignment>().first()
        return letAssignment.letVariableDecl
    }

    fun createFunctionDeclParamDecl(project: Project?, name: String): JsltFunctionDeclParamDecl {
        val file: JsltFile = createFile(project, "def fun($name) 1")
        val funcDecl = file.childrenOfType<JsltFunctionDecl>().first()
        return funcDecl
            .functionDeclParamList!!
            .functionDeclParamDeclList
            .first() as JsltFunctionDeclParamDecl
    }

    fun createFunctionDeclNameDecl(project: Project?, name: String): JsltFunctionDeclNameDecl {
        val file: JsltFile = createFile(project, "def $name() 1")
        val funcDecl = file.childrenOfType<JsltFunctionDecl>().first()
        return funcDecl.functionDeclNameDecl
    }

    private fun createFile(project: Project?, text: String): JsltFile {
        val name = "dummy.jslt"
        return PsiFileFactory.getInstance(project).createFileFromText(name, JsltFileType.INSTANCE, text) as JsltFile
    }
}