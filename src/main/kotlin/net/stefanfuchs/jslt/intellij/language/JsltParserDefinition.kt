package net.stefanfuchs.jslt.intellij.language

import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.lang.ParserDefinition.SpaceRequirements
import com.intellij.lang.PsiParser
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet
import net.stefanfuchs.jslt.intellij.language.parser.JsltParser
import net.stefanfuchs.jslt.intellij.language.psi.JsltTypes


class JsltParserDefinition : ParserDefinition {

    override fun createLexer(project: Project): Lexer = JsltLexerAdapter()
    override fun getWhitespaceTokens(): TokenSet = WHITE_SPACES
    override fun getCommentTokens(): TokenSet = COMMENTS
    override fun getStringLiteralElements(): TokenSet = TokenSet.EMPTY
    override fun createParser(project: Project?): PsiParser = JsltParser()
    override fun getFileNodeType(): IFileElementType = FILE
    override fun createFile(viewProvider: FileViewProvider): PsiFile = JsltFile(viewProvider)
    override fun spaceExistenceTypeBetweenTokens(left: ASTNode?, right: ASTNode?) = SpaceRequirements.MAY
    override fun createElement(node: ASTNode?): PsiElement = JsltTypes.Factory.createElement(node)

    companion object {
        val WHITE_SPACES = TokenSet.create(TokenType.WHITE_SPACE)
        val COMMENTS = TokenSet.create(JsltTypes.COMMENT)
        val FILE = IFileElementType(JsltLanguage)
    }
}