package net.stefanfuchs.jslt.intellij.language.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import net.stefanfuchs.jslt.intellij.language.psi.JsltImportDeclElement


abstract class JsltImportDeclElementImpl(node: ASTNode) : ASTWrapperPsiElement(node), JsltImportDeclElement