package net.stefanfuchs.jslt.intellij.language

import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.openapi.options.colors.ColorSettingsPage
import javax.swing.Icon

class JsltColorSettingsPage : ColorSettingsPage {
    override fun getIcon(): Icon {
        return JsltIcons.FileType
    }

    override fun getHighlighter(): SyntaxHighlighter {
        return JsltSyntaxHighlighter()
    }

    override fun getDemoText(): String =
        JsltColorSettingsPage::class.java.getResource("/settings-sample.jslt")!!.readText()

    override fun getAdditionalHighlightingTagToDescriptorMap(): Map<String, TextAttributesKey>? {
        return null
    }

    override fun getAttributeDescriptors(): Array<AttributesDescriptor> {
        return DESCRIPTORS
    }

    override fun getColorDescriptors(): Array<ColorDescriptor> {
        return ColorDescriptor.EMPTY_ARRAY
    }

    override fun getDisplayName(): String {
        return "JSLT"
    }

    companion object {
        private val DESCRIPTORS = arrayOf(
            AttributesDescriptor("Assign", JsltSyntaxHighlighter.ASSIGN),
            AttributesDescriptor("Bad Character", JsltSyntaxHighlighter.BAD_CHARACTER),
            AttributesDescriptor("Brackets", JsltSyntaxHighlighter.BRACKETS),
            AttributesDescriptor("Colon", JsltSyntaxHighlighter.COLON),
            AttributesDescriptor("Comma", JsltSyntaxHighlighter.COMMA),
            AttributesDescriptor("Comment", JsltSyntaxHighlighter.COMMENT),
            AttributesDescriptor("Unused Identifier", JsltSyntaxHighlighter.UNUSED_IDENTIFIER),
            AttributesDescriptor("Comparator", JsltSyntaxHighlighter.COMPARATOR),
            AttributesDescriptor("Dot", JsltSyntaxHighlighter.DOT),
            AttributesDescriptor("Function Name", JsltSyntaxHighlighter.FUNCTION_NAME),
            AttributesDescriptor("Buildin Function", JsltSyntaxHighlighter.BUILDIN_FUNCTION_NAME),
            AttributesDescriptor("Function Parameter", JsltSyntaxHighlighter.PARAMETER),
            AttributesDescriptor("Identifier", JsltSyntaxHighlighter.IDENTIFIER),
            AttributesDescriptor("Keyword", JsltSyntaxHighlighter.KEYWORD),
            AttributesDescriptor("Local Variable", JsltSyntaxHighlighter.LOCAL_VARIABLE),
            AttributesDescriptor("Number", JsltSyntaxHighlighter.NUMBER),
            AttributesDescriptor("Object Braces", JsltSyntaxHighlighter.OBJECT_BRACES),
            AttributesDescriptor("Operator", JsltSyntaxHighlighter.OPERATOR),
            AttributesDescriptor("Parenthesis", JsltSyntaxHighlighter.PARENTHESIS),
            AttributesDescriptor("String", JsltSyntaxHighlighter.STRING),
            AttributesDescriptor("Variable Declaration", JsltSyntaxHighlighter.VARIABLE_DECL),
            AttributesDescriptor("Variable", JsltSyntaxHighlighter.GLOBAL_VARIABLE),
        )
    }
}