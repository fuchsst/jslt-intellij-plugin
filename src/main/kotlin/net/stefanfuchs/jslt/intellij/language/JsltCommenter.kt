package net.stefanfuchs.jslt.intellij.language

import com.intellij.lang.Commenter


class JsltCommenter : Commenter {
    override fun getLineCommentPrefix(): String? {
        return "// "
    }

    override fun getBlockCommentPrefix(): String? {
        return null
    }

    override fun getBlockCommentSuffix(): String? {
        return null
    }

    override fun getCommentedBlockCommentPrefix(): String? {
        return null
    }

    override fun getCommentedBlockCommentSuffix(): String? {
        return null
    }
}