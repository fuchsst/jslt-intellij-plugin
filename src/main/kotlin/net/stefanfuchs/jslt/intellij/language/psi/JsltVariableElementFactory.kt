package net.stefanfuchs.jslt.intellij.language.psi

import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFileFactory
import com.intellij.util.IncorrectOperationException
import net.stefanfuchs.jslt.intellij.language.JsltFile
import net.stefanfuchs.jslt.intellij.language.JsltFileType

object JsltVariableElementFactory {
    fun createVariableUsage(project: Project?, name: String): JsltVariableUsage {
        val file: JsltFile = createFile(project, "\$$name")
        val expr = file.findChildByClass(JsltExpr::class.java)
        var node = expr?.node
        while (node != null) {
            if (node.elementType==JsltTypes.VARIABLE_USAGE) {
                return node.psi as JsltVariableUsage
            }
            node= node.firstChildNode
        }
        throw IncorrectOperationException("Could not build AST Node!")
    }

    fun createVariableDeclaration(project: Project?, name: String): JsltLetAssignment {
        val file: JsltFile = createFile(project, "let $name = 1")
        return file.firstChild as JsltLetAssignment
    }

    private fun createFile(project: Project?, text: String): JsltFile {
        val name = "dummy.jslt"
        return PsiFileFactory.getInstance(project).createFileFromText(name, JsltFileType.INSTANCE, text) as JsltFile
    }
}