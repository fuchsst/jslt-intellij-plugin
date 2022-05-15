package net.stefanfuchs.jslt.intellij.language.psi

import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFileFactory
import com.intellij.util.IncorrectOperationException
import net.stefanfuchs.jslt.intellij.language.JsltFile
import net.stefanfuchs.jslt.intellij.language.JsltFileType

object JsltElementFactory {
    fun createVariableUsage(project: Project?, name: String): JsltVariableUsage {
        val file: JsltFile = createFile(project, "\$$name")
        val expr = file.findChildByClass(JsltExpr::class.java)
        var node = expr?.node
        while (node != null) {
            if (node.elementType == JsltTypes.VARIABLE_USAGE) {
                return node.psi as JsltVariableUsage
            }
            node = node.firstChildNode
        }
        throw IncorrectOperationException("Could not build AST Node!")
    }

    fun createImportDecl(project: Project?, name: String): JsltImportDeclaration {
        val file: JsltFile = createFile(project, "import \"dummy.jslt\" as $name")
        return file.firstChild as JsltImportDeclaration
    }

    fun createLetVariableDecl(project: Project?, name: String): JsltLetVariableDecl {
        val file: JsltFile = createFile(project, "let $name = 1")
        return (file.firstChild as JsltLetAssignment).letVariableDecl
    }

    fun createFunctionDeclParamDecl(project: Project?, name: String): JsltFunctionDeclParamDecl {
        val file: JsltFile = createFile(project, "def fun($name)")
        return (file.firstChild as JsltFunctionDecl)
            .functionDeclParamList!!
            .functionDeclParamDeclList
            .first() as JsltFunctionDeclParamDecl
    }

    private fun createFile(project: Project?, text: String): JsltFile {
        val name = "dummy.jslt"
        return PsiFileFactory.getInstance(project).createFileFromText(name, JsltFileType.INSTANCE, text) as JsltFile
    }
}