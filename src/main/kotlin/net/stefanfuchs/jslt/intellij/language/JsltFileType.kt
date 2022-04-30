package net.stefanfuchs.jslt.intellij.language

import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon

class JsltFileType private constructor() : LanguageFileType(JsltLanguage) {
    override fun getName(): String {
        return "Jslt File"
    }

    override fun getDescription(): String {
        return "JSLT transformation file"
    }

    override fun getDefaultExtension(): String {
        return "jslt"
    }

    override fun getIcon(): Icon {
        return JsltIcons.FileType
    }

    companion object {
        val INSTANCE = JsltFileType()
    }
}