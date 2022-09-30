package net.stefanfuchs.jslt.intellij.language

import com.intellij.lang.Language
import com.intellij.psi.codeStyle.CodeStyleSettingsCustomizable
import com.intellij.psi.codeStyle.LanguageCodeStyleSettingsProvider

class JsltLanguageCodeStyleSettingsProvider : LanguageCodeStyleSettingsProvider() {
    override fun getLanguage(): Language = JsltLanguage

    override fun customizeSettings(consumer: CodeStyleSettingsCustomizable, settingsType: SettingsType) =
        when (settingsType) {
            SettingsType.SPACING_SETTINGS -> {
                consumer.showStandardOptions(
                    CodeStyleSettingsCustomizable.SpacingOption.SPACE_AROUND_ASSIGNMENT_OPERATORS.name,
                    CodeStyleSettingsCustomizable.SpacingOption.SPACE_AROUND_RELATIONAL_OPERATORS.name,
                    CodeStyleSettingsCustomizable.SpacingOption.SPACE_AROUND_ADDITIVE_OPERATORS.name,
                    CodeStyleSettingsCustomizable.SpacingOption.SPACE_AROUND_MULTIPLICATIVE_OPERATORS.name,
                    CodeStyleSettingsCustomizable.SpacingOption.SPACE_AROUND_LAMBDA_ARROW.name,
                    CodeStyleSettingsCustomizable.SpacingOption.SPACE_AFTER_COMMA.name,
                    CodeStyleSettingsCustomizable.SpacingOption.SPACE_BEFORE_COMMA.name,
                    CodeStyleSettingsCustomizable.SpacingOption.SPACE_WITHIN_PARENTHESES.name,
                    CodeStyleSettingsCustomizable.SpacingOption.SPACE_WITHIN_BRACKETS.name,
                    CodeStyleSettingsCustomizable.SpacingOption.SPACE_WITHIN_BRACES.name,
                    CodeStyleSettingsCustomizable.SpacingOption.SPACE_BEFORE_IF_PARENTHESES.name,
                    CodeStyleSettingsCustomizable.SpacingOption.SPACE_BEFORE_FOR_PARENTHESES.name,
                    CodeStyleSettingsCustomizable.SpacingOption.SPACE_BEFORE_COLON.name,
                    CodeStyleSettingsCustomizable.SpacingOption.SPACE_AFTER_COLON.name,
                    CodeStyleSettingsCustomizable.SpacingOption.SPACE_WITHIN_EMPTY_METHOD_CALL_PARENTHESES.name
                )

                consumer.renameStandardOption(
                    CodeStyleSettingsCustomizable.SpacingOption.SPACE_AROUND_ASSIGNMENT_OPERATORS.name,
                    "Assignment operators (':', '=')"
                )
                consumer.renameStandardOption(
                    CodeStyleSettingsCustomizable.SpacingOption.SPACE_AROUND_LAMBDA_ARROW.name,
                    "Context operator ('|')"
                )
                consumer.renameStandardOption(
                    CodeStyleSettingsCustomizable.SpacingOption.SPACE_WITHIN_BRACKETS.name,
                    "Array brackets"
                )
                consumer.renameStandardOption(
                    CodeStyleSettingsCustomizable.SpacingOption.SPACE_WITHIN_BRACES.name,
                    "Object braces"
                )

            }

            SettingsType.INDENT_SETTINGS -> {
                consumer.showStandardOptions(
                    CodeStyleSettingsCustomizable.IndentOption.INDENT_SIZE.name,
                    CodeStyleSettingsCustomizable.IndentOption.CONTINUATION_INDENT_SIZE.name,
                    CodeStyleSettingsCustomizable.IndentOption.USE_RELATIVE_INDENTS.name
                )
            }

            SettingsType.WRAPPING_AND_BRACES_SETTINGS -> {
                consumer.showStandardOptions(
                    CodeStyleSettingsCustomizable.WrappingOrBraceOption.ALIGN_MULTILINE_ASSIGNMENT.name,
                    CodeStyleSettingsCustomizable.WrappingOrBraceOption.ALIGN_MULTILINE_PARAMETERS.name,
                    CodeStyleSettingsCustomizable.WrappingOrBraceOption.ALIGN_MULTILINE_PARAMETERS_IN_CALLS.name,
                    CodeStyleSettingsCustomizable.WrappingOrBraceOption.ALIGN_MULTILINE_PARENTHESIZED_EXPRESSION.name,
                    CodeStyleSettingsCustomizable.WrappingOrBraceOption.ASSIGNMENT_WRAP.name,
                    CodeStyleSettingsCustomizable.WrappingOrBraceOption.BRACE_STYLE.name,
                    CodeStyleSettingsCustomizable.WrappingOrBraceOption.CALL_PARAMETERS_LPAREN_ON_NEXT_LINE.name,
                    CodeStyleSettingsCustomizable.WrappingOrBraceOption.CALL_PARAMETERS_RPAREN_ON_NEXT_LINE.name,
                    CodeStyleSettingsCustomizable.WrappingOrBraceOption.CALL_PARAMETERS_WRAP.name,
                    CodeStyleSettingsCustomizable.WrappingOrBraceOption.KEEP_LINE_BREAKS.name,
                    CodeStyleSettingsCustomizable.WrappingOrBraceOption.KEEP_MULTIPLE_EXPRESSIONS_IN_ONE_LINE.name,
                    CodeStyleSettingsCustomizable.WrappingOrBraceOption.KEEP_SIMPLE_BLOCKS_IN_ONE_LINE.name,
                    CodeStyleSettingsCustomizable.WrappingOrBraceOption.KEEP_SIMPLE_METHODS_IN_ONE_LINE.name,
                    CodeStyleSettingsCustomizable.WrappingOrBraceOption.METHOD_BRACE_STYLE.name,
                    CodeStyleSettingsCustomizable.WrappingOrBraceOption.METHOD_CALL_CHAIN_WRAP.name,
                    CodeStyleSettingsCustomizable.WrappingOrBraceOption.RIGHT_MARGIN.name,
                    CodeStyleSettingsCustomizable.WrappingOrBraceOption.SPECIAL_ELSE_IF_TREATMENT.name,
                    CodeStyleSettingsCustomizable.WrappingOrBraceOption.WRAP_LONG_LINES.name
                )

            }

            SettingsType.BLANK_LINES_SETTINGS -> {
                consumer.showStandardOptions(
                    CodeStyleSettingsCustomizable.BlankLinesOption.BLANK_LINES_AFTER_IMPORTS.name,
                    CodeStyleSettingsCustomizable.BlankLinesOption.BLANK_LINES_AROUND_METHOD.name
                )
            }

            else -> {}
        }

    override fun getCodeSample(settingsType: SettingsType): String =
        JsltLanguageCodeStyleSettingsProvider::class.java.getResource("/settings-sample.jslt")!!.readText()

}
