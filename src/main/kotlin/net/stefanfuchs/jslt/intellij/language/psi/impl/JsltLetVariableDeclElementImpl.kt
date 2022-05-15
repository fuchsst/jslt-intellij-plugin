package net.stefanfuchs.jslt.intellij.language.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import net.stefanfuchs.jslt.intellij.language.psi.JsltLetVariableDeclElement


abstract class JsltLetVariableDeclElementImpl(node: ASTNode) : ASTWrapperPsiElement(node), JsltLetVariableDeclElement