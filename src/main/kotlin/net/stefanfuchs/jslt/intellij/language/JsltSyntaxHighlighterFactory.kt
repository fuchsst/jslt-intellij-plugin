package net.stefanfuchs.jslt.intellij.language

import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile

class JsltSyntaxHighlighterFactory : SyntaxHighlighterFactory() {
    override fun getSyntaxHighlighter(project: Project?, virtualFile: VirtualFile?): JsltSyntaxHighlighter {
        return JsltSyntaxHighlighter()
    }
}