// This is a generated file. Not intended for manual editing.
package net.stefanfuchs.jslt.intellij.language.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import net.stefanfuchs.jslt.intellij.language.psi.impl.*;

public interface JsltTypes {

  IElementType ADDITIVE_EXPR = new JsltElementType("ADDITIVE_EXPR");
  IElementType ADDITIVE_OPERATOR = new JsltElementType("ADDITIVE_OPERATOR");
  IElementType AND_EXPR = new JsltElementType("AND_EXPR");
  IElementType ARRAY = new JsltElementType("ARRAY");
  IElementType ARRAY_BODY = new JsltElementType("ARRAY_BODY");
  IElementType ARRAY_ELEMENTS = new JsltElementType("ARRAY_ELEMENTS");
  IElementType ARRAY_FOR_BODY = new JsltElementType("ARRAY_FOR_BODY");
  IElementType ARRAY_SLICING = new JsltElementType("ARRAY_SLICING");
  IElementType ARRAY_SLICING_BODY = new JsltElementType("ARRAY_SLICING_BODY");
  IElementType BASE_EXPR = new JsltElementType("BASE_EXPR");
  IElementType CHAINABLE = new JsltElementType("CHAINABLE");
  IElementType CHAIN_LINK = new JsltElementType("CHAIN_LINK");
  IElementType COLON_EXPR = new JsltElementType("COLON_EXPR");
  IElementType COMPARATIVE_EXPR = new JsltElementType("COMPARATIVE_EXPR");
  IElementType COMPARATOR = new JsltElementType("COMPARATOR");
  IElementType DOT_KEY = new JsltElementType("DOT_KEY");
  IElementType ELSE_BRANCH = new JsltElementType("ELSE_BRANCH");
  IElementType EXPR = new JsltElementType("EXPR");
  IElementType FUNCTION_BODY = new JsltElementType("FUNCTION_BODY");
  IElementType FUNCTION_CALL = new JsltElementType("FUNCTION_CALL");
  IElementType FUNCTION_DECL = new JsltElementType("FUNCTION_DECL");
  IElementType FUNCTION_NAME = new JsltElementType("FUNCTION_NAME");
  IElementType IF_STATEMENT = new JsltElementType("IF_STATEMENT");
  IElementType IMPORT_DECLARATION = new JsltElementType("IMPORT_DECLARATION");
  IElementType IMPORT_DECLARATIONS = new JsltElementType("IMPORT_DECLARATIONS");
  IElementType LET_ASSIGNMENT = new JsltElementType("LET_ASSIGNMENT");
  IElementType MATCHER = new JsltElementType("MATCHER");
  IElementType MATCHER_MINUS = new JsltElementType("MATCHER_MINUS");
  IElementType MULTIPLICATIVE_EXPR = new JsltElementType("MULTIPLICATIVE_EXPR");
  IElementType MULTIPLICATIVE_OPERATOR = new JsltElementType("MULTIPLICATIVE_OPERATOR");
  IElementType OBJECT = new JsltElementType("OBJECT");
  IElementType OBJECT_BODY = new JsltElementType("OBJECT_BODY");
  IElementType OBJECT_COMPREHENSION = new JsltElementType("OBJECT_COMPREHENSION");
  IElementType OBJECT_COMPREHENSION_BODY = new JsltElementType("OBJECT_COMPREHENSION_BODY");
  IElementType OBJECT_COMPREHENSION_FOR_BODY = new JsltElementType("OBJECT_COMPREHENSION_FOR_BODY");
  IElementType OR_EXPR = new JsltElementType("OR_EXPR");
  IElementType PAIR = new JsltElementType("PAIR");
  IElementType PAIRS = new JsltElementType("PAIRS");
  IElementType PARENTHESIS_EXPR = new JsltElementType("PARENTHESIS_EXPR");
  IElementType PIPE_OPERATOR = new JsltElementType("PIPE_OPERATOR");

  IElementType AND = new JsltTokenType("AND");
  IElementType AS = new JsltTokenType("AS");
  IElementType ASSIGN = new JsltTokenType("ASSIGN");
  IElementType BIGGER = new JsltTokenType("BIGGER");
  IElementType BIGOREQ = new JsltTokenType("BIGOREQ");
  IElementType COLON = new JsltTokenType("COLON");
  IElementType COMMA = new JsltTokenType("COMMA");
  IElementType COMMENT = new JsltTokenType("COMMENT");
  IElementType DECIMAL = new JsltTokenType("DECIMAL");
  IElementType DEF = new JsltTokenType("DEF");
  IElementType DOT = new JsltTokenType("DOT");
  IElementType ELSE = new JsltTokenType("ELSE");
  IElementType EQUALS = new JsltTokenType("EQUALS");
  IElementType FALSE = new JsltTokenType("FALSE");
  IElementType FOR = new JsltTokenType("FOR");
  IElementType FUNCTION_DECL_NAME = new JsltTokenType("FUNCTION_DECL_NAME");
  IElementType FUNCTION_DECL_PARAM = new JsltTokenType("FUNCTION_DECL_PARAM");
  IElementType IDENT = new JsltTokenType("IDENT");
  IElementType IF = new JsltTokenType("IF");
  IElementType IMPORT = new JsltTokenType("IMPORT");
  IElementType INTEGER = new JsltTokenType("INTEGER");
  IElementType LBRACKET = new JsltTokenType("LBRACKET");
  IElementType LCURLY = new JsltTokenType("LCURLY");
  IElementType LET = new JsltTokenType("LET");
  IElementType LPAREN = new JsltTokenType("LPAREN");
  IElementType MINUS = new JsltTokenType("MINUS");
  IElementType NULL = new JsltTokenType("NULL");
  IElementType OR = new JsltTokenType("OR");
  IElementType PIDENT = new JsltTokenType("PIDENT");
  IElementType PIPE = new JsltTokenType("PIPE");
  IElementType PLUS = new JsltTokenType("PLUS");
  IElementType RBRACKET = new JsltTokenType("RBRACKET");
  IElementType RCURLY = new JsltTokenType("RCURLY");
  IElementType RPAREN = new JsltTokenType("RPAREN");
  IElementType SLASH = new JsltTokenType("SLASH");
  IElementType SMALLER = new JsltTokenType("SMALLER");
  IElementType SMALLOREQ = new JsltTokenType("SMALLOREQ");
  IElementType STAR = new JsltTokenType("STAR");
  IElementType STRING = new JsltTokenType("STRING");
  IElementType TRUE = new JsltTokenType("TRUE");
  IElementType UNEQUALS = new JsltTokenType("UNEQUALS");
  IElementType VARIABLE = new JsltTokenType("VARIABLE");
  IElementType VARIABLE_DECL = new JsltTokenType("VARIABLE_DECL");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == ADDITIVE_EXPR) {
        return new JsltAdditiveExprImpl(node);
      }
      else if (type == ADDITIVE_OPERATOR) {
        return new JsltAdditiveOperatorImpl(node);
      }
      else if (type == AND_EXPR) {
        return new JsltAndExprImpl(node);
      }
      else if (type == ARRAY) {
        return new JsltArrayImpl(node);
      }
      else if (type == ARRAY_BODY) {
        return new JsltArrayBodyImpl(node);
      }
      else if (type == ARRAY_ELEMENTS) {
        return new JsltArrayElementsImpl(node);
      }
      else if (type == ARRAY_FOR_BODY) {
        return new JsltArrayForBodyImpl(node);
      }
      else if (type == ARRAY_SLICING) {
        return new JsltArraySlicingImpl(node);
      }
      else if (type == ARRAY_SLICING_BODY) {
        return new JsltArraySlicingBodyImpl(node);
      }
      else if (type == BASE_EXPR) {
        return new JsltBaseExprImpl(node);
      }
      else if (type == CHAINABLE) {
        return new JsltChainableImpl(node);
      }
      else if (type == CHAIN_LINK) {
        return new JsltChainLinkImpl(node);
      }
      else if (type == COLON_EXPR) {
        return new JsltColonExprImpl(node);
      }
      else if (type == COMPARATIVE_EXPR) {
        return new JsltComparativeExprImpl(node);
      }
      else if (type == COMPARATOR) {
        return new JsltComparatorImpl(node);
      }
      else if (type == DOT_KEY) {
        return new JsltDotKeyImpl(node);
      }
      else if (type == ELSE_BRANCH) {
        return new JsltElseBranchImpl(node);
      }
      else if (type == EXPR) {
        return new JsltExprImpl(node);
      }
      else if (type == FUNCTION_BODY) {
        return new JsltFunctionBodyImpl(node);
      }
      else if (type == FUNCTION_CALL) {
        return new JsltFunctionCallImpl(node);
      }
      else if (type == FUNCTION_DECL) {
        return new JsltFunctionDeclImpl(node);
      }
      else if (type == FUNCTION_NAME) {
        return new JsltFunctionNameImpl(node);
      }
      else if (type == IF_STATEMENT) {
        return new JsltIfStatementImpl(node);
      }
      else if (type == IMPORT_DECLARATION) {
        return new JsltImportDeclarationImpl(node);
      }
      else if (type == IMPORT_DECLARATIONS) {
        return new JsltImportDeclarationsImpl(node);
      }
      else if (type == LET_ASSIGNMENT) {
        return new JsltLetAssignmentImpl(node);
      }
      else if (type == MATCHER) {
        return new JsltMatcherImpl(node);
      }
      else if (type == MATCHER_MINUS) {
        return new JsltMatcherMinusImpl(node);
      }
      else if (type == MULTIPLICATIVE_EXPR) {
        return new JsltMultiplicativeExprImpl(node);
      }
      else if (type == MULTIPLICATIVE_OPERATOR) {
        return new JsltMultiplicativeOperatorImpl(node);
      }
      else if (type == OBJECT) {
        return new JsltObjectImpl(node);
      }
      else if (type == OBJECT_BODY) {
        return new JsltObjectBodyImpl(node);
      }
      else if (type == OBJECT_COMPREHENSION) {
        return new JsltObjectComprehensionImpl(node);
      }
      else if (type == OBJECT_COMPREHENSION_BODY) {
        return new JsltObjectComprehensionBodyImpl(node);
      }
      else if (type == OBJECT_COMPREHENSION_FOR_BODY) {
        return new JsltObjectComprehensionForBodyImpl(node);
      }
      else if (type == OR_EXPR) {
        return new JsltOrExprImpl(node);
      }
      else if (type == PAIR) {
        return new JsltPairImpl(node);
      }
      else if (type == PAIRS) {
        return new JsltPairsImpl(node);
      }
      else if (type == PARENTHESIS_EXPR) {
        return new JsltParenthesisExprImpl(node);
      }
      else if (type == PIPE_OPERATOR) {
        return new JsltPipeOperatorImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
