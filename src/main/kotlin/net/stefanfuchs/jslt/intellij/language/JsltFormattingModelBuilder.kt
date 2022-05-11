package net.stefanfuchs.jslt.intellij.language

import com.intellij.formatting.*
import com.intellij.lang.ASTNode
import com.intellij.psi.TokenType
import com.intellij.psi.codeStyle.CodeStyleSettings
import com.intellij.psi.tree.TokenSet
import net.stefanfuchs.jslt.intellij.language.psi.JsltTypes

class JsltFormattingModelBuilder : FormattingModelBuilder {
    override fun createModel(formattingContext: FormattingContext): FormattingModel {
        val codeStyleSettings = formattingContext.codeStyleSettings
        val wrapType = if (codeStyleSettings.WRAP_LONG_LINES)
            WrapType.CHOP_DOWN_IF_LONG
        else
            WrapType.NONE

        return FormattingModelProvider
            .createFormattingModelForPsiFile(formattingContext.containingFile,
                JsltBlock(formattingContext.node,
                    Wrap.createWrap(wrapType, false),
                    Alignment.createAlignment(),
                    createSpaceBuilder(codeStyleSettings)),
                codeStyleSettings)
    }

    companion object {
        private fun createSpaceBuilder(settings: CodeStyleSettings): SpacingBuilder {
            val commonSettings = settings.getCommonSettings(JsltLanguage.id)
            return SpacingBuilder(settings, JsltLanguage)
                .around(JsltTypes.ASSIGN)
                .spaceIf(commonSettings.SPACE_AROUND_ASSIGNMENT_OPERATORS)
                .around(JsltTypes.AS)
                .spaceIf(commonSettings.SPACE_AROUND_ASSIGNMENT_OPERATORS)
                .before(JsltTypes.COLON)
                .spaceIf(commonSettings.SPACE_BEFORE_COLON)
                .after(JsltTypes.COLON)
                .spaceIf(commonSettings.SPACE_AFTER_COLON)
                .before(JsltTypes.COMMA)
                .spaceIf(commonSettings.SPACE_BEFORE_COMMA)
                .after(JsltTypes.COMMA)
                .spaceIf(commonSettings.SPACE_AFTER_COMMA)
                .around(JsltTypes.COMPARATOR)
                .spaceIf(commonSettings.SPACE_AROUND_RELATIONAL_OPERATORS)
                .around(JsltTypes.ADDITIVE_OPERATOR)
                .spaceIf(commonSettings.SPACE_AROUND_ADDITIVE_OPERATORS)
                .around(JsltTypes.MULTIPLICATIVE_OPERATOR)
                .spaceIf(commonSettings.SPACE_AROUND_MULTIPLICATIVE_OPERATORS)
                .around(JsltTypes.PIPE_OPERATOR)
                .spaceIf(commonSettings.SPACE_AROUND_LAMBDA_ARROW)
                .after(JsltTypes.IMPORT_DECLARATIONS)
                .blankLines(commonSettings.BLANK_LINES_AFTER_IMPORTS)
                .around(JsltTypes.FUNCTION_DECL)
                .blankLines(commonSettings.BLANK_LINES_AROUND_METHOD)
                .withinPair(JsltTypes.LBRACKET, JsltTypes.RBRACKET)
                .spaceIf(commonSettings.SPACE_WITHIN_BRACKETS)
                .withinPair(JsltTypes.LCURLY, JsltTypes.RCURLY)
                .spaceIf(commonSettings.SPACE_WITHIN_BRACES)
                .withinPair(JsltTypes.LPAREN, JsltTypes.RPAREN)
                .spaceIf(commonSettings.SPACE_WITHIN_PARENTHESES)
                .after(JsltTypes.IF)
                .spaceIf(commonSettings.SPACE_BEFORE_IF_PARENTHESES)
                .after(JsltTypes.FOR)
                .spaceIf(commonSettings.SPACE_BEFORE_FOR_PARENTHESES)
                .before(JsltTypes.ARRAY_FOR_BODY)
                .spaces(1)
                .before(JsltTypes.OBJECT_COMPREHENSION_FOR_BODY)
                .spaces(1)
                .after(JsltTypes.LET)
                .spaces(1)
                .after(JsltTypes.DEF)
                .spaces(1)
                .after(JsltTypes.IMPORT)
                .spaces(1)
        }
    }
}