package net.stefanfuchs.jslt.intellij.language

import com.intellij.lang.Language
import com.intellij.openapi.util.IconLoader


object JsltLanguage : Language("JSLT")

class JsltIcons {
    companion object {
        @JvmField
        val FileType = IconLoader.getIcon("/icons/jslt.png", JsltIcons::class.java)
    }
}
