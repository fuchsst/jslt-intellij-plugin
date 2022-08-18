package net.stefanfuchs.jslt.intellij.language

import com.intellij.application.options.CodeStyleAbstractConfigurable
import com.intellij.application.options.CodeStyleAbstractPanel
import com.intellij.application.options.TabbedLanguageCodeStylePanel
import com.intellij.psi.codeStyle.CodeStyleConfigurable
import com.intellij.psi.codeStyle.CodeStyleSettings
import com.intellij.psi.codeStyle.CodeStyleSettingsProvider
import com.intellij.psi.codeStyle.CustomCodeStyleSettings
import org.jetbrains.annotations.NotNull

class JsltCodeStyleSettingsProvider : CodeStyleSettingsProvider() {
    override fun createCustomSettings(settings: CodeStyleSettings): CustomCodeStyleSettings {
        return JsltCodeStyleSettings(settings)
    }

    override fun getConfigurableDisplayName(): String {
        return "JSLT"
    }

    override fun createConfigurable(
        @NotNull settings: CodeStyleSettings,
        modelSettings: CodeStyleSettings,
    ): CodeStyleConfigurable {
        return object : CodeStyleAbstractConfigurable(settings, modelSettings, this.configurableDisplayName) {
            override fun createPanel(settings: CodeStyleSettings): CodeStyleAbstractPanel {
                return JsltCodeStyleMainPanel(currentSettings, settings)
            }
        }
    }

    private class JsltCodeStyleMainPanel(currentSettings: CodeStyleSettings?, settings: CodeStyleSettings) :
        TabbedLanguageCodeStylePanel(JsltLanguage, currentSettings, settings)
}
