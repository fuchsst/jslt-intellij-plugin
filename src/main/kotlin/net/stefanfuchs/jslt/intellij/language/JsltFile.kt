package net.stefanfuchs.jslt.intellij.language

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider

class JsltFile(viewProvider: FileViewProvider) :
    PsiFileBase(viewProvider, JsltLanguage) {

    override fun getFileType(): FileType {
        return JsltFileType.INSTANCE
    }

    override fun toString(): String {
        return "JSLT File"
    }
}