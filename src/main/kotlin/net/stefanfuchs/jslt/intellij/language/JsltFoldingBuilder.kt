package net.stefanfuchs.jslt.intellij.language

import com.intellij.lang.ASTNode
import com.intellij.lang.folding.FoldingBuilderEx
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.TokenType
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.psi.util.elementType
import net.stefanfuchs.jslt.intellij.language.psi.*

class JsltFoldingBuilder : FoldingBuilderEx(), DumbAware {
    override fun buildFoldRegions(root: PsiElement, document: Document, quick: Boolean): Array<FoldingDescriptor> {
        val importDescriptors = PsiTreeUtil.findChildrenOfType(root, JsltImportDeclarations::class.java).map {
            val group = FoldingGroup.newGroup("imports: $it")
            FoldingDescriptor(it.node, TextRange(it.textRange.startOffset, it.textRange.endOffset), group)
        }

        val functionDescriptors = PsiTreeUtil.findChildrenOfType(root, JsltFunctionDecl::class.java).map {
            val group = FoldingGroup.newGroup("function " +
                    it.children.firstOrNull { childNode -> childNode.elementType == JsltTypes.FUNCTION_DECL_NAME }?.text
            )
            FoldingDescriptor(it.node, TextRange(it.textRange.startOffset, it.textRange.endOffset), group)
        }

        val objectDescriptors = PsiTreeUtil.findChildrenOfType(root, JsltObject::class.java).map {
            val group = FoldingGroup.newGroup("object: $it")
            FoldingDescriptor(it.node, TextRange(it.textRange.startOffset, it.textRange.endOffset), group)
        }

        val arrayDescriptors = PsiTreeUtil.findChildrenOfType(root, JsltArray::class.java).map {
            val group = FoldingGroup.newGroup("array: $it")
            FoldingDescriptor(it.node, TextRange(it.textRange.startOffset, it.textRange.endOffset), group)
        }

        return (importDescriptors + functionDescriptors + objectDescriptors + arrayDescriptors).toTypedArray()
    }

    override fun getPlaceholderText(node: ASTNode): String? {
        return when (node.elementType) {
            JsltTypes.IMPORT_DECLARATIONS -> "import ..."
            JsltTypes.FUNCTION_DECL -> {
                val sb = StringBuilder()
                var currentNode = node.firstChildNode
                while (currentNode != null) {
                    when (currentNode.elementType) {
                        JsltTypes.DEF -> sb.append("def ")
                        JsltTypes.FUNCTION_DECL_NAME -> sb.append(currentNode.text)
                        JsltTypes.LPAREN -> sb.append("(")
                        JsltTypes.FUNCTION_DECL_PARAM -> sb.append(currentNode.text)
                        JsltTypes.COMMA -> sb.append(", ")
                        JsltTypes.RPAREN -> sb.append(") ...")
                    }
                    currentNode = currentNode.treeNext
                }
                sb.toString()
            }
            JsltTypes.OBJECT -> {
                val firstPairNode = node.findChildByType(JsltTypes.PAIR)
                val firstMatcherNode = node.findChildByType(JsltTypes.MATCHER)
                if (firstPairNode != null) {
                    val keyNode = firstPairNode.firstChildNode
                    "{ ${keyNode.text} : ...}"
                } else if (firstMatcherNode != null) {
                    var currentNode = firstMatcherNode.firstChildNode
                    val sb = StringBuilder()
                    while (currentNode != null && currentNode.elementType != JsltTypes.COLON) {
                        if (currentNode.elementType == TokenType.WHITE_SPACE) {
                            sb.append(" ") // only add one space, using text would add all if there are multiple spaces
                        } else {
                            sb.append(currentNode.text)
                        }
                        currentNode = currentNode.treeNext
                    }
                    "{ $sb : ... }"
                } else {
                    "{ ... }"
                }

            }
            JsltTypes.ARRAY -> {
                "[ ... ]"
            }
            else -> "..." + node.elementType
        }
    }

    override fun isCollapsedByDefault(node: ASTNode): Boolean {
        return false
    }
}