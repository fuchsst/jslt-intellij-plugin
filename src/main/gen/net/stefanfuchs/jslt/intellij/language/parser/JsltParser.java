// This is a generated file. Not intended for manual editing.
package net.stefanfuchs.jslt.intellij.language.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static net.stefanfuchs.jslt.intellij.language.psi.JsltTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class JsltParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    r = parse_root_(t, b);
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b) {
    return parse_root_(t, b, 0);
  }

  static boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return jsltFile(b, l + 1);
  }

  /* ********************************************************** */
  // MultiplicativeExpr (AdditiveOperator MultiplicativeExpr)*
  public static boolean AdditiveExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AdditiveExpr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ADDITIVE_EXPR, "<additive expr>");
    r = MultiplicativeExpr(b, l + 1);
    r = r && AdditiveExpr_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (AdditiveOperator MultiplicativeExpr)*
  private static boolean AdditiveExpr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AdditiveExpr_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!AdditiveExpr_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "AdditiveExpr_1", c)) break;
    }
    return true;
  }

  // AdditiveOperator MultiplicativeExpr
  private static boolean AdditiveExpr_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AdditiveExpr_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = AdditiveOperator(b, l + 1);
    r = r && MultiplicativeExpr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // PLUS | MINUS
  public static boolean AdditiveOperator(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AdditiveOperator")) return false;
    if (!nextTokenIs(b, "<additive operator>", MINUS, PLUS)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ADDITIVE_OPERATOR, "<additive operator>");
    r = consumeToken(b, PLUS);
    if (!r) r = consumeToken(b, MINUS);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // ComparativeExpr (AND AndExpr())?
  public static boolean AndExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AndExpr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, AND_EXPR, "<and expr>");
    r = ComparativeExpr(b, l + 1);
    r = r && AndExpr_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (AND AndExpr())?
  private static boolean AndExpr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AndExpr_1")) return false;
    AndExpr_1_0(b, l + 1);
    return true;
  }

  // AND AndExpr()
  private static boolean AndExpr_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AndExpr_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, AND);
    r = r && AndExpr(b, l + 1);
    r = r && AndExpr_1_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ()
  private static boolean AndExpr_1_0_2(PsiBuilder b, int l) {
    return true;
  }

  /* ********************************************************** */
  // LBRACKET
  //               (
  //                (FOR LPAREN Expr RPAREN LetAssignment* Expr (IF LPAREN Expr RPAREN)?)
  //               | ArrayElem?
  //               )
  //             RBRACKET
  public static boolean Array(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Array")) return false;
    if (!nextTokenIs(b, LBRACKET)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACKET);
    r = r && Array_1(b, l + 1);
    r = r && consumeToken(b, RBRACKET);
    exit_section_(b, m, ARRAY, r);
    return r;
  }

  // (FOR LPAREN Expr RPAREN LetAssignment* Expr (IF LPAREN Expr RPAREN)?)
  //               | ArrayElem?
  private static boolean Array_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Array_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Array_1_0(b, l + 1);
    if (!r) r = Array_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // FOR LPAREN Expr RPAREN LetAssignment* Expr (IF LPAREN Expr RPAREN)?
  private static boolean Array_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Array_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, FOR, LPAREN);
    r = r && Expr(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    r = r && Array_1_0_4(b, l + 1);
    r = r && Expr(b, l + 1);
    r = r && Array_1_0_6(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // LetAssignment*
  private static boolean Array_1_0_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Array_1_0_4")) return false;
    while (true) {
      int c = current_position_(b);
      if (!LetAssignment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Array_1_0_4", c)) break;
    }
    return true;
  }

  // (IF LPAREN Expr RPAREN)?
  private static boolean Array_1_0_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Array_1_0_6")) return false;
    Array_1_0_6_0(b, l + 1);
    return true;
  }

  // IF LPAREN Expr RPAREN
  private static boolean Array_1_0_6_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Array_1_0_6_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, IF, LPAREN);
    r = r && Expr(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, null, r);
    return r;
  }

  // ArrayElem?
  private static boolean Array_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Array_1_1")) return false;
    ArrayElem(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // Expr
  //                 (COMMA ArrayElem?)?
  public static boolean ArrayElem(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ArrayElem")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ARRAY_ELEM, "<array elem>");
    r = Expr(b, l + 1);
    r = r && ArrayElem_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (COMMA ArrayElem?)?
  private static boolean ArrayElem_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ArrayElem_1")) return false;
    ArrayElem_1_0(b, l + 1);
    return true;
  }

  // COMMA ArrayElem?
  private static boolean ArrayElem_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ArrayElem_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && ArrayElem_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ArrayElem?
  private static boolean ArrayElem_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ArrayElem_1_0_1")) return false;
    ArrayElem(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // LBRACKET
  //                    (
  //                      Expr (ColonExpr Expr?)? |
  //                      ColonExpr Expr
  //                    )
  //                    RBRACKET
  public static boolean ArraySlicing(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ArraySlicing")) return false;
    if (!nextTokenIs(b, LBRACKET)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACKET);
    r = r && ArraySlicing_1(b, l + 1);
    r = r && consumeToken(b, RBRACKET);
    exit_section_(b, m, ARRAY_SLICING, r);
    return r;
  }

  // Expr (ColonExpr Expr?)? |
  //                      ColonExpr Expr
  private static boolean ArraySlicing_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ArraySlicing_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ArraySlicing_1_0(b, l + 1);
    if (!r) r = ArraySlicing_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // Expr (ColonExpr Expr?)?
  private static boolean ArraySlicing_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ArraySlicing_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Expr(b, l + 1);
    r = r && ArraySlicing_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (ColonExpr Expr?)?
  private static boolean ArraySlicing_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ArraySlicing_1_0_1")) return false;
    ArraySlicing_1_0_1_0(b, l + 1);
    return true;
  }

  // ColonExpr Expr?
  private static boolean ArraySlicing_1_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ArraySlicing_1_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ColonExpr(b, l + 1);
    r = r && ArraySlicing_1_0_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // Expr?
  private static boolean ArraySlicing_1_0_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ArraySlicing_1_0_1_0_1")) return false;
    Expr(b, l + 1);
    return true;
  }

  // ColonExpr Expr
  private static boolean ArraySlicing_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ArraySlicing_1_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ColonExpr(b, l + 1);
    r = r && Expr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // NULL | INTEGER | DECIMAL | STRING | TRUE | FALSE |
  //                 Chainable | Parenthesis | IfStatement |
  //                 Array |
  //                 (Object | ObjectComprehension)
  public static boolean BaseExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BaseExpr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, BASE_EXPR, "<base expr>");
    r = consumeToken(b, NULL);
    if (!r) r = consumeToken(b, INTEGER);
    if (!r) r = consumeToken(b, DECIMAL);
    if (!r) r = consumeToken(b, STRING);
    if (!r) r = consumeToken(b, TRUE);
    if (!r) r = consumeToken(b, FALSE);
    if (!r) r = Chainable(b, l + 1);
    if (!r) r = Parenthesis(b, l + 1);
    if (!r) r = IfStatement(b, l + 1);
    if (!r) r = Array(b, l + 1);
    if (!r) r = BaseExpr_10(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // Object | ObjectComprehension
  private static boolean BaseExpr_10(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BaseExpr_10")) return false;
    boolean r;
    r = Object(b, l + 1);
    if (!r) r = ObjectComprehension(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // (DotKey | ArraySlicing)
  //                 (ChainLink)?
  public static boolean ChainLink(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ChainLink")) return false;
    if (!nextTokenIs(b, "<chain link>", DOT, LBRACKET)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, CHAIN_LINK, "<chain link>");
    r = ChainLink_0(b, l + 1);
    r = r && ChainLink_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // DotKey | ArraySlicing
  private static boolean ChainLink_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ChainLink_0")) return false;
    boolean r;
    r = DotKey(b, l + 1);
    if (!r) r = ArraySlicing(b, l + 1);
    return r;
  }

  // (ChainLink)?
  private static boolean ChainLink_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ChainLink_1")) return false;
    ChainLink_1_0(b, l + 1);
    return true;
  }

  // (ChainLink)
  private static boolean ChainLink_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ChainLink_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ChainLink(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // (FunctionCall | VARIABLE | DOT (IDENT | STRING)?)
  //                 (ChainLink)?
  public static boolean Chainable(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Chainable")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CHAINABLE, "<chainable>");
    r = Chainable_0(b, l + 1);
    r = r && Chainable_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // FunctionCall | VARIABLE | DOT (IDENT | STRING)?
  private static boolean Chainable_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Chainable_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = FunctionCall(b, l + 1);
    if (!r) r = consumeToken(b, VARIABLE);
    if (!r) r = Chainable_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // DOT (IDENT | STRING)?
  private static boolean Chainable_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Chainable_0_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DOT);
    r = r && Chainable_0_2_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (IDENT | STRING)?
  private static boolean Chainable_0_2_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Chainable_0_2_1")) return false;
    Chainable_0_2_1_0(b, l + 1);
    return true;
  }

  // IDENT | STRING
  private static boolean Chainable_0_2_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Chainable_0_2_1_0")) return false;
    boolean r;
    r = consumeToken(b, IDENT);
    if (!r) r = consumeToken(b, STRING);
    return r;
  }

  // (ChainLink)?
  private static boolean Chainable_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Chainable_1")) return false;
    Chainable_1_0(b, l + 1);
    return true;
  }

  // (ChainLink)
  private static boolean Chainable_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Chainable_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ChainLink(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // COLON
  public static boolean ColonExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ColonExpr")) return false;
    if (!nextTokenIs(b, COLON)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COLON);
    exit_section_(b, m, COLON_EXPR, r);
    return r;
  }

  /* ********************************************************** */
  // AdditiveExpr (Comparator AdditiveExpr)?
  public static boolean ComparativeExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ComparativeExpr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, COMPARATIVE_EXPR, "<comparative expr>");
    r = AdditiveExpr(b, l + 1);
    r = r && ComparativeExpr_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (Comparator AdditiveExpr)?
  private static boolean ComparativeExpr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ComparativeExpr_1")) return false;
    ComparativeExpr_1_0(b, l + 1);
    return true;
  }

  // Comparator AdditiveExpr
  private static boolean ComparativeExpr_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ComparativeExpr_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Comparator(b, l + 1);
    r = r && AdditiveExpr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // EQUALS | UNEQUALS | BIGOREQ | BIGGER | SMALLER | SMALLOREQ
  public static boolean Comparator(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Comparator")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, COMPARATOR, "<comparator>");
    r = consumeToken(b, EQUALS);
    if (!r) r = consumeToken(b, UNEQUALS);
    if (!r) r = consumeToken(b, BIGOREQ);
    if (!r) r = consumeToken(b, BIGGER);
    if (!r) r = consumeToken(b, SMALLER);
    if (!r) r = consumeToken(b, SMALLOREQ);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // DOT (IDENT | STRING)
  public static boolean DotKey(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DotKey")) return false;
    if (!nextTokenIs(b, DOT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DOT);
    r = r && DotKey_1(b, l + 1);
    exit_section_(b, m, DOT_KEY, r);
    return r;
  }

  // IDENT | STRING
  private static boolean DotKey_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DotKey_1")) return false;
    boolean r;
    r = consumeToken(b, IDENT);
    if (!r) r = consumeToken(b, STRING);
    return r;
  }

  /* ********************************************************** */
  // ELSE
  //                    LetAssignment*
  //                    Expr
  public static boolean ElseBranch(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ElseBranch")) return false;
    if (!nextTokenIs(b, ELSE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ELSE);
    r = r && ElseBranch_1(b, l + 1);
    r = r && Expr(b, l + 1);
    exit_section_(b, m, ELSE_BRANCH, r);
    return r;
  }

  // LetAssignment*
  private static boolean ElseBranch_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ElseBranch_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!LetAssignment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ElseBranch_1", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // OrExpr (PipeOperator OrExpr)*
  public static boolean Expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, EXPR, "<expr>");
    r = OrExpr(b, l + 1);
    r = r && Expr_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (PipeOperator OrExpr)*
  private static boolean Expr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expr_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!Expr_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Expr_1", c)) break;
    }
    return true;
  }

  // PipeOperator OrExpr
  private static boolean Expr_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expr_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = PipeOperator(b, l + 1);
    r = r && OrExpr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // FunctionName LPAREN (Expr (COMMA Expr)*)? RPAREN
  public static boolean FunctionCall(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionCall")) return false;
    if (!nextTokenIs(b, "<function call>", IDENT, PIDENT)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FUNCTION_CALL, "<function call>");
    r = FunctionName(b, l + 1);
    r = r && consumeToken(b, LPAREN);
    r = r && FunctionCall_2(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (Expr (COMMA Expr)*)?
  private static boolean FunctionCall_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionCall_2")) return false;
    FunctionCall_2_0(b, l + 1);
    return true;
  }

  // Expr (COMMA Expr)*
  private static boolean FunctionCall_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionCall_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Expr(b, l + 1);
    r = r && FunctionCall_2_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (COMMA Expr)*
  private static boolean FunctionCall_2_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionCall_2_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!FunctionCall_2_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "FunctionCall_2_0_1", c)) break;
    }
    return true;
  }

  // COMMA Expr
  private static boolean FunctionCall_2_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionCall_2_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && Expr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // DEF FUNCTION_DECL_NAME LPAREN (FUNCTION_DECL_PARAM (COMMA FUNCTION_DECL_PARAM)*)? RPAREN
  //                      LetAssignment*
  //                      Expr
  public static boolean FunctionDecl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionDecl")) return false;
    if (!nextTokenIs(b, DEF)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, DEF, FUNCTION_DECL_NAME, LPAREN);
    r = r && FunctionDecl_3(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    r = r && FunctionDecl_5(b, l + 1);
    r = r && Expr(b, l + 1);
    exit_section_(b, m, FUNCTION_DECL, r);
    return r;
  }

  // (FUNCTION_DECL_PARAM (COMMA FUNCTION_DECL_PARAM)*)?
  private static boolean FunctionDecl_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionDecl_3")) return false;
    FunctionDecl_3_0(b, l + 1);
    return true;
  }

  // FUNCTION_DECL_PARAM (COMMA FUNCTION_DECL_PARAM)*
  private static boolean FunctionDecl_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionDecl_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, FUNCTION_DECL_PARAM);
    r = r && FunctionDecl_3_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (COMMA FUNCTION_DECL_PARAM)*
  private static boolean FunctionDecl_3_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionDecl_3_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!FunctionDecl_3_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "FunctionDecl_3_0_1", c)) break;
    }
    return true;
  }

  // COMMA FUNCTION_DECL_PARAM
  private static boolean FunctionDecl_3_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionDecl_3_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, COMMA, FUNCTION_DECL_PARAM);
    exit_section_(b, m, null, r);
    return r;
  }

  // LetAssignment*
  private static boolean FunctionDecl_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionDecl_5")) return false;
    while (true) {
      int c = current_position_(b);
      if (!LetAssignment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "FunctionDecl_5", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // IDENT | PIDENT
  public static boolean FunctionName(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionName")) return false;
    if (!nextTokenIs(b, "<function name>", IDENT, PIDENT)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FUNCTION_NAME, "<function name>");
    r = consumeToken(b, IDENT);
    if (!r) r = consumeToken(b, PIDENT);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // IF LPAREN Expr RPAREN
  //                     LetAssignment*
  //                     Expr
  //                     ElseBranch?
  public static boolean IfStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IfStatement")) return false;
    if (!nextTokenIs(b, IF)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, IF, LPAREN);
    r = r && Expr(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    r = r && IfStatement_4(b, l + 1);
    r = r && Expr(b, l + 1);
    r = r && IfStatement_6(b, l + 1);
    exit_section_(b, m, IF_STATEMENT, r);
    return r;
  }

  // LetAssignment*
  private static boolean IfStatement_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IfStatement_4")) return false;
    while (true) {
      int c = current_position_(b);
      if (!LetAssignment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "IfStatement_4", c)) break;
    }
    return true;
  }

  // ElseBranch?
  private static boolean IfStatement_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IfStatement_6")) return false;
    ElseBranch(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // IMPORT STRING AS IDENT
  public static boolean ImportDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportDeclaration")) return false;
    if (!nextTokenIs(b, IMPORT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, IMPORT, STRING, AS, IDENT);
    exit_section_(b, m, IMPORT_DECLARATION, r);
    return r;
  }

  /* ********************************************************** */
  // ImportDeclaration*
  public static boolean ImportDeclarations(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportDeclarations")) return false;
    Marker m = enter_section_(b, l, _NONE_, IMPORT_DECLARATIONS, "<import declarations>");
    while (true) {
      int c = current_position_(b);
      if (!ImportDeclaration(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ImportDeclarations", c)) break;
    }
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  /* ********************************************************** */
  // LET VARIABLE_DECL ASSIGN Expr
  public static boolean LetAssignment(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LetAssignment")) return false;
    if (!nextTokenIs(b, LET)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, LET, VARIABLE_DECL, ASSIGN);
    r = r && Expr(b, l + 1);
    exit_section_(b, m, LET_ASSIGNMENT, r);
    return r;
  }

  /* ********************************************************** */
  // STAR MatcherMinus? COLON Expr
  public static boolean Matcher(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Matcher")) return false;
    if (!nextTokenIs(b, STAR)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, STAR);
    r = r && Matcher_1(b, l + 1);
    r = r && consumeToken(b, COLON);
    r = r && Expr(b, l + 1);
    exit_section_(b, m, MATCHER, r);
    return r;
  }

  // MatcherMinus?
  private static boolean Matcher_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Matcher_1")) return false;
    MatcherMinus(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // MINUS (IDENT | STRING) (COMMA (IDENT | STRING))*
  public static boolean MatcherMinus(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MatcherMinus")) return false;
    if (!nextTokenIs(b, MINUS)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, MINUS);
    r = r && MatcherMinus_1(b, l + 1);
    r = r && MatcherMinus_2(b, l + 1);
    exit_section_(b, m, MATCHER_MINUS, r);
    return r;
  }

  // IDENT | STRING
  private static boolean MatcherMinus_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MatcherMinus_1")) return false;
    boolean r;
    r = consumeToken(b, IDENT);
    if (!r) r = consumeToken(b, STRING);
    return r;
  }

  // (COMMA (IDENT | STRING))*
  private static boolean MatcherMinus_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MatcherMinus_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!MatcherMinus_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "MatcherMinus_2", c)) break;
    }
    return true;
  }

  // COMMA (IDENT | STRING)
  private static boolean MatcherMinus_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MatcherMinus_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && MatcherMinus_2_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // IDENT | STRING
  private static boolean MatcherMinus_2_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MatcherMinus_2_0_1")) return false;
    boolean r;
    r = consumeToken(b, IDENT);
    if (!r) r = consumeToken(b, STRING);
    return r;
  }

  /* ********************************************************** */
  // BaseExpr (MultiplicativeOperator BaseExpr)*
  public static boolean MultiplicativeExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MultiplicativeExpr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, MULTIPLICATIVE_EXPR, "<multiplicative expr>");
    r = BaseExpr(b, l + 1);
    r = r && MultiplicativeExpr_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (MultiplicativeOperator BaseExpr)*
  private static boolean MultiplicativeExpr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MultiplicativeExpr_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!MultiplicativeExpr_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "MultiplicativeExpr_1", c)) break;
    }
    return true;
  }

  // MultiplicativeOperator BaseExpr
  private static boolean MultiplicativeExpr_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MultiplicativeExpr_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = MultiplicativeOperator(b, l + 1);
    r = r && BaseExpr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // STAR | SLASH
  public static boolean MultiplicativeOperator(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MultiplicativeOperator")) return false;
    if (!nextTokenIs(b, "<multiplicative operator>", SLASH, STAR)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, MULTIPLICATIVE_OPERATOR, "<multiplicative operator>");
    r = consumeToken(b, STAR);
    if (!r) r = consumeToken(b, SLASH);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // LCURLY LetAssignment* (Pair | Matcher)? RCURLY
  public static boolean Object(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Object")) return false;
    if (!nextTokenIs(b, LCURLY)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LCURLY);
    r = r && Object_1(b, l + 1);
    r = r && Object_2(b, l + 1);
    r = r && consumeToken(b, RCURLY);
    exit_section_(b, m, OBJECT, r);
    return r;
  }

  // LetAssignment*
  private static boolean Object_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Object_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!LetAssignment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Object_1", c)) break;
    }
    return true;
  }

  // (Pair | Matcher)?
  private static boolean Object_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Object_2")) return false;
    Object_2_0(b, l + 1);
    return true;
  }

  // Pair | Matcher
  private static boolean Object_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Object_2_0")) return false;
    boolean r;
    r = Pair(b, l + 1);
    if (!r) r = Matcher(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // LCURLY
  //                             FOR LPAREN Expr RPAREN
  //                               LetAssignment*
  //                               Expr COLON Expr
  //                               (IF LPAREN Expr RPAREN)?
  //                           RCURLY
  public static boolean ObjectComprehension(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ObjectComprehension")) return false;
    if (!nextTokenIs(b, LCURLY)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, LCURLY, FOR, LPAREN);
    r = r && Expr(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    r = r && ObjectComprehension_5(b, l + 1);
    r = r && Expr(b, l + 1);
    r = r && consumeToken(b, COLON);
    r = r && Expr(b, l + 1);
    r = r && ObjectComprehension_9(b, l + 1);
    r = r && consumeToken(b, RCURLY);
    exit_section_(b, m, OBJECT_COMPREHENSION, r);
    return r;
  }

  // LetAssignment*
  private static boolean ObjectComprehension_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ObjectComprehension_5")) return false;
    while (true) {
      int c = current_position_(b);
      if (!LetAssignment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ObjectComprehension_5", c)) break;
    }
    return true;
  }

  // (IF LPAREN Expr RPAREN)?
  private static boolean ObjectComprehension_9(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ObjectComprehension_9")) return false;
    ObjectComprehension_9_0(b, l + 1);
    return true;
  }

  // IF LPAREN Expr RPAREN
  private static boolean ObjectComprehension_9_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ObjectComprehension_9_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, IF, LPAREN);
    r = r && Expr(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // AndExpr (OR OrExpr)?
  public static boolean OrExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "OrExpr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, OR_EXPR, "<or expr>");
    r = AndExpr(b, l + 1);
    r = r && OrExpr_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (OR OrExpr)?
  private static boolean OrExpr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "OrExpr_1")) return false;
    OrExpr_1_0(b, l + 1);
    return true;
  }

  // OR OrExpr
  private static boolean OrExpr_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "OrExpr_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OR);
    r = r && OrExpr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // Expr COLON Expr
  //            (COMMA (Pair | Matcher))?
  public static boolean Pair(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Pair")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PAIR, "<pair>");
    r = Expr(b, l + 1);
    r = r && consumeToken(b, COLON);
    r = r && Expr(b, l + 1);
    r = r && Pair_3(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (COMMA (Pair | Matcher))?
  private static boolean Pair_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Pair_3")) return false;
    Pair_3_0(b, l + 1);
    return true;
  }

  // COMMA (Pair | Matcher)
  private static boolean Pair_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Pair_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && Pair_3_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // Pair | Matcher
  private static boolean Pair_3_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Pair_3_0_1")) return false;
    boolean r;
    r = Pair(b, l + 1);
    if (!r) r = Matcher(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // LPAREN Expr RPAREN
  public static boolean Parenthesis(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Parenthesis")) return false;
    if (!nextTokenIs(b, LPAREN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LPAREN);
    r = r && Expr(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, PARENTHESIS, r);
    return r;
  }

  /* ********************************************************** */
  // PIPE
  public static boolean PipeOperator(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PipeOperator")) return false;
    if (!nextTokenIs(b, PIPE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PIPE);
    exit_section_(b, m, PIPE_OPERATOR, r);
    return r;
  }

  /* ********************************************************** */
  // COMMENT*
  //     ImportDeclarations?
  //     (LetAssignment | FunctionDecl)*
  //     Expr <<eof>>
  static boolean jsltFile(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jsltFile")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = jsltFile_0(b, l + 1);
    r = r && jsltFile_1(b, l + 1);
    r = r && jsltFile_2(b, l + 1);
    r = r && Expr(b, l + 1);
    r = r && eof(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // COMMENT*
  private static boolean jsltFile_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jsltFile_0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, COMMENT)) break;
      if (!empty_element_parsed_guard_(b, "jsltFile_0", c)) break;
    }
    return true;
  }

  // ImportDeclarations?
  private static boolean jsltFile_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jsltFile_1")) return false;
    ImportDeclarations(b, l + 1);
    return true;
  }

  // (LetAssignment | FunctionDecl)*
  private static boolean jsltFile_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jsltFile_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!jsltFile_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "jsltFile_2", c)) break;
    }
    return true;
  }

  // LetAssignment | FunctionDecl
  private static boolean jsltFile_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jsltFile_2_0")) return false;
    boolean r;
    r = LetAssignment(b, l + 1);
    if (!r) r = FunctionDecl(b, l + 1);
    return r;
  }

}
