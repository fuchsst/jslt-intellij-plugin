package net.stefanfuchs.jslt.intellij.language.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import net.stefanfuchs.jslt.intellij.language.psi.JsltFunctionDeclParamDeclElement


abstract class JsltFunctionDeclParamDeclElementImpl(node: ASTNode) : ASTWrapperPsiElement(node),
    JsltFunctionDeclParamDeclElement