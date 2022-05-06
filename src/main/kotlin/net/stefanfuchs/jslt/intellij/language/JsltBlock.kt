package net.stefanfuchs.jslt.intellij.language

import com.intellij.formatting.*
import com.intellij.lang.ASTNode
import com.intellij.psi.TokenType
import com.intellij.psi.formatter.common.AbstractBlock
import net.stefanfuchs.jslt.intellij.language.psi.JsltTypes

open class JsltBlock internal constructor(
    node: ASTNode, wrap: Wrap?, alignment: Alignment?, private val spacingBuilder: SpacingBuilder,
) : AbstractBlock(node, wrap, alignment) {

    open class JsltBodyBlock internal constructor(
        node: ASTNode,
        wrap: Wrap?,
        alignment: Alignment?,
        private val spacingBuilder: SpacingBuilder,
    ) : JsltBlock(node, wrap, alignment, spacingBuilder) {
        override fun getIndent(): Indent? = Indent.getSmartIndent(Indent.Type.NORMAL)
    }

    override fun buildChildren(): List<Block> {
        val blocks: MutableList<Block> = ArrayList()
        var child = myNode.firstChildNode
        while (child != null) {
            val block: Block? = when (child.elementType) {
                JsltTypes.ARRAY_BODY,
                JsltTypes.ARRAY_FOR_BODY,
                JsltTypes.OBJECT_BODY,
                JsltTypes.OBJECT_COMPREHENSION_BODY,
                JsltTypes.OBJECT_COMPREHENSION_FOR_BODY,
                JsltTypes.FUNCTION_BODY,
                -> JsltBodyBlock(
                    node = child,
                    wrap = wrap,
                    alignment = Alignment.createAlignment(),
                    spacingBuilder = spacingBuilder
                )
                TokenType.WHITE_SPACE -> {
                    null
                }
                else -> {
                    JsltBlock(
                        node = child,
                        wrap = wrap,
                        alignment = Alignment.createAlignment(),
                        spacingBuilder = spacingBuilder)
                }
            }
            if (block != null) {
                blocks.add(block)
            }
            child = child.treeNext
        }
        return blocks
    }

    override fun getIndent(): Indent? = Indent.getNoneIndent()

    override fun getSpacing(child1: Block?, child2: Block): Spacing? = spacingBuilder.getSpacing(this, child1, child2)

    override fun isLeaf(): Boolean = myNode.firstChildNode == null

}