package net.stefanfuchs.jslt.intellij.language.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import net.stefanfuchs.jslt.intellij.language.psi.JsltFunctionDeclNameDeclElement


abstract class JsltFunctionDeclNameDeclElementImpl(node: ASTNode) : ASTWrapperPsiElement(node),
    JsltFunctionDeclNameDeclElement