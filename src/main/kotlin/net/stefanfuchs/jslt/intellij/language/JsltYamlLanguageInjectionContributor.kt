package net.stefanfuchs.jslt.intellij.language

import com.intellij.lang.injection.general.Injection
import com.intellij.lang.injection.general.LanguageInjectionContributor
import com.intellij.lang.injection.general.SimpleInjection
import com.intellij.psi.PsiElement
import org.jetbrains.yaml.psi.impl.YAMLScalarTextImpl


class JsltYamlLanguageInjectionContributor : LanguageInjectionContributor {

    override fun getInjection(context: PsiElement): Injection? {
        return if (context is YAMLScalarTextImpl && context.textValue.startsWith("// JSLT")) {
            SimpleInjection(JsltLanguage, "", "", null)
        } else {
            null
        }
    }

}