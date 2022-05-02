package net.stefanfuchs.jslt.intellij.language

import com.intellij.formatting.*
import com.intellij.psi.codeStyle.CodeStyleSettings
import net.stefanfuchs.jslt.intellij.language.psi.JsltTypes

class JsltFormattingModelBuilder : FormattingModelBuilder {
    override fun createModel(formattingContext: FormattingContext): FormattingModel {
        val codeStyleSettings = formattingContext.codeStyleSettings
        return FormattingModelProvider
            .createFormattingModelForPsiFile(formattingContext.containingFile,
                JsltBlock(formattingContext.node,
                    Wrap.createWrap(WrapType.NONE, false),
                    Alignment.createAlignment(),
                    createSpaceBuilder(codeStyleSettings)),
                codeStyleSettings)
    }

    companion object {
        private fun createSpaceBuilder(settings: CodeStyleSettings): SpacingBuilder {
            return SpacingBuilder(settings, JsltLanguage)
                .formatImportSpacing(settings)
                .formatLetAssignmentSpacing(settings)
                .formatFunctionDeclarationSpacing(settings)
                .formatIfSpacing(settings)
                .formatElseSpacing(settings)
                .formatForSpacing(settings)
                .formatArraySpacing(settings)
                .formatObjectSpacing(settings)
                .formatPairSpacing(settings)
                .formatFunctionCallSpacing(settings)
                .formatOperatorsSpacing(settings)
        }

        private fun SpacingBuilder.formatImportSpacing(settings: CodeStyleSettings): SpacingBuilder =
            this.after(JsltTypes.IMPORT_DECLARATIONS)
//                .spacing(0,0,
//                    settings.getCommonSettings(JsltLanguage.id).BLANK_LINES_AFTER_IMPORTS,
//                    true,
//                    settings.getCommonSettings(JsltLanguage.id).KEEP_BLANK_LINES_IN_DECLARATIONS)
                .blankLines(settings.getCommonSettings(JsltLanguage.id).BLANK_LINES_AFTER_IMPORTS)


        private fun SpacingBuilder.formatLetAssignmentSpacing(settings: CodeStyleSettings): SpacingBuilder =
            this.around(JsltTypes.ASSIGN)
                .spaceIf(settings.getCommonSettings(JsltLanguage.id).SPACE_AROUND_ASSIGNMENT_OPERATORS)

        private fun SpacingBuilder.formatFunctionDeclarationSpacing(settings: CodeStyleSettings): SpacingBuilder =
            this

        private fun SpacingBuilder.formatIfSpacing(settings: CodeStyleSettings): SpacingBuilder =
            this

        private fun SpacingBuilder.formatElseSpacing(settings: CodeStyleSettings): SpacingBuilder =
            this

        private fun SpacingBuilder.formatForSpacing(settings: CodeStyleSettings): SpacingBuilder =
            this

        private fun SpacingBuilder.formatArraySpacing(settings: CodeStyleSettings): SpacingBuilder =
            this

        private fun SpacingBuilder.formatObjectSpacing(settings: CodeStyleSettings): SpacingBuilder =
            this

        private fun SpacingBuilder.formatPairSpacing(settings: CodeStyleSettings): SpacingBuilder =
            this

        private fun SpacingBuilder.formatFunctionCallSpacing(settings: CodeStyleSettings): SpacingBuilder =
            this

        private fun SpacingBuilder.formatOperatorsSpacing(settings: CodeStyleSettings): SpacingBuilder =
            this
    }
}