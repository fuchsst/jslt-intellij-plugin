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

    override fun getDemoText(): String {
        return """
                // some comment
                
                let idparts = split(.id, "-")
                let xxx = [for (${"\$idparts"}) "x" * size(.)]
                
                def myFunc(param1, param2)
                  if (${"\$param1"} > ${"\$param2"})
                     ${"\$param1"} + 10
                   else
                     ${"\$param2"} - 10.0e6
                
                {
                  "id" : join(${"\$xxx"}, "-"),
                  "type" : "Anonymized-View",
                  * : .
                }
                """.trimIndent()
    }

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
            AttributesDescriptor("Comparator", JsltSyntaxHighlighter.COMPARATOR),
            AttributesDescriptor("Dot", JsltSyntaxHighlighter.DOT),
            AttributesDescriptor("Function Declaration", JsltSyntaxHighlighter.FUNCTION_DECLARATION),
            AttributesDescriptor("Identifier", JsltSyntaxHighlighter.IDENTIFIER),
            AttributesDescriptor("Keyword", JsltSyntaxHighlighter.KEYWORD),
            AttributesDescriptor("Number", JsltSyntaxHighlighter.NUMBER),
            AttributesDescriptor("Object Braces", JsltSyntaxHighlighter.OBJECT_BRACES),
            AttributesDescriptor("Operator", JsltSyntaxHighlighter.OPERATOR),
            AttributesDescriptor("Function Parameter", JsltSyntaxHighlighter.PARAMETER),
            AttributesDescriptor("Parenthesis", JsltSyntaxHighlighter.PARENTHESIS),
            AttributesDescriptor("String", JsltSyntaxHighlighter.STRING),
            AttributesDescriptor("Variable", JsltSyntaxHighlighter.VARIABLE),
        )
    }
}