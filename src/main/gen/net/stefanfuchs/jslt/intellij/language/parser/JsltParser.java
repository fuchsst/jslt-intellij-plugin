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
  // LBRACKET ArrayBody RBRACKET
  public static boolean Array(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Array")) return false;
    if (!nextTokenIs(b, LBRACKET)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACKET);
    r = r && ArrayBody(b, l + 1);
    r = r && consumeToken(b, RBRACKET);
    exit_section_(b, m, ARRAY, r);
    return r;
  }

  /* ********************************************************** */
  // (FOR ParenthesisExpr ArrayForBody) | ArrayElements?
  public static boolean ArrayBody(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ArrayBody")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ARRAY_BODY, "<array body>");
    r = ArrayBody_0(b, l + 1);
    if (!r) r = ArrayBody_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // FOR ParenthesisExpr ArrayForBody
  private static boolean ArrayBody_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ArrayBody_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, FOR);
    r = r && ParenthesisExpr(b, l + 1);
    r = r && ArrayForBody(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ArrayElements?
  private static boolean ArrayBody_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ArrayBody_1")) return false;
    ArrayElements(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // Expr (COMMA Expr)*
  public static boolean ArrayElements(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ArrayElements")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ARRAY_ELEMENTS, "<array elements>");
    r = Expr(b, l + 1);
    r = r && ArrayElements_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (COMMA Expr)*
  private static boolean ArrayElements_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ArrayElements_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!ArrayElements_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ArrayElements_1", c)) break;
    }
    return true;
  }

  // COMMA Expr
  private static boolean ArrayElements_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ArrayElements_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && Expr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // LetAssignment* Expr (IF ParenthesisExpr)?
  public static boolean ArrayForBody(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ArrayForBody")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ARRAY_FOR_BODY, "<array for body>");
    r = ArrayForBody_0(b, l + 1);
    r = r && Expr(b, l + 1);
    r = r && ArrayForBody_2(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // LetAssignment*
  private static boolean ArrayForBody_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ArrayForBody_0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!LetAssignment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ArrayForBody_0", c)) break;
    }
    return true;
  }

  // (IF ParenthesisExpr)?
  private static boolean ArrayForBody_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ArrayForBody_2")) return false;
    ArrayForBody_2_0(b, l + 1);
    return true;
  }

  // IF ParenthesisExpr
  private static boolean ArrayForBody_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ArrayForBody_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IF);
    r = r && ParenthesisExpr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // LBRACKET ArraySlicingBody RBRACKET
  public static boolean ArraySlicing(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ArraySlicing")) return false;
    if (!nextTokenIs(b, LBRACKET)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACKET);
    r = r && ArraySlicingBody(b, l + 1);
    r = r && consumeToken(b, RBRACKET);
    exit_section_(b, m, ARRAY_SLICING, r);
    return r;
  }

  /* ********************************************************** */
  // Expr (ColonExpr Expr?)? | ColonExpr Expr
  public static boolean ArraySlicingBody(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ArraySlicingBody")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ARRAY_SLICING_BODY, "<array slicing body>");
    r = ArraySlicingBody_0(b, l + 1);
    if (!r) r = ArraySlicingBody_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // Expr (ColonExpr Expr?)?
  private static boolean ArraySlicingBody_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ArraySlicingBody_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Expr(b, l + 1);
    r = r && ArraySlicingBody_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (ColonExpr Expr?)?
  private static boolean ArraySlicingBody_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ArraySlicingBody_0_1")) return false;
    ArraySlicingBody_0_1_0(b, l + 1);
    return true;
  }

  // ColonExpr Expr?
  private static boolean ArraySlicingBody_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ArraySlicingBody_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ColonExpr(b, l + 1);
    r = r && ArraySlicingBody_0_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // Expr?
  private static boolean ArraySlicingBody_0_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ArraySlicingBody_0_1_0_1")) return false;
    Expr(b, l + 1);
    return true;
  }

  // ColonExpr Expr
  private static boolean ArraySlicingBody_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ArraySlicingBody_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ColonExpr(b, l + 1);
    r = r && Expr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // NULL | INTEGER | DECIMAL | STRING | TRUE | FALSE |
  //                 Chainable | ParenthesisExpr | IfStatement |
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
    if (!r) r = ParenthesisExpr(b, l + 1);
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
  // (FunctionCall | VariableUsage | DOT (IDENT | STRING)?)
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

  // FunctionCall | VariableUsage | DOT (IDENT | STRING)?
  private static boolean Chainable_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Chainable_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = FunctionCall(b, l + 1);
    if (!r) r = VariableUsage(b, l + 1);
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
  // ELSE FunctionBody
  public static boolean ElseBranch(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ElseBranch")) return false;
    if (!nextTokenIs(b, ELSE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ELSE);
    r = r && FunctionBody(b, l + 1);
    exit_section_(b, m, ELSE_BRANCH, r);
    return r;
  }

  /* ********************************************************** */
  // ELSE IF ParenthesisExpr FunctionBody
  public static boolean ElseIfBranch(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ElseIfBranch")) return false;
    if (!nextTokenIs(b, ELSE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, ELSE, IF);
    r = r && ParenthesisExpr(b, l + 1);
    r = r && FunctionBody(b, l + 1);
    exit_section_(b, m, ELSE_IF_BRANCH, r);
    return r;
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
  // LetAssignment* Expr
  public static boolean FunctionBody(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionBody")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FUNCTION_BODY, "<function body>");
    r = FunctionBody_0(b, l + 1);
    r = r && Expr(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // LetAssignment*
  private static boolean FunctionBody_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionBody_0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!LetAssignment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "FunctionBody_0", c)) break;
    }
    return true;
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
  // DEF FunctionDeclNameDecl LPAREN FunctionDeclParamList? RPAREN
  //                      FunctionBody
  public static boolean FunctionDecl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionDecl")) return false;
    if (!nextTokenIs(b, DEF)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DEF);
    r = r && FunctionDeclNameDecl(b, l + 1);
    r = r && consumeToken(b, LPAREN);
    r = r && FunctionDecl_3(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    r = r && FunctionBody(b, l + 1);
    exit_section_(b, m, FUNCTION_DECL, r);
    return r;
  }

  // FunctionDeclParamList?
  private static boolean FunctionDecl_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionDecl_3")) return false;
    FunctionDeclParamList(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // FUNCTION_DECL_NAME
  public static boolean FunctionDeclNameDecl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionDeclNameDecl")) return false;
    if (!nextTokenIs(b, FUNCTION_DECL_NAME)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, FUNCTION_DECL_NAME);
    exit_section_(b, m, FUNCTION_DECL_NAME_DECL, r);
    return r;
  }

  /* ********************************************************** */
  // FUNCTION_DECL_PARAM
  public static boolean FunctionDeclParamDecl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionDeclParamDecl")) return false;
    if (!nextTokenIs(b, FUNCTION_DECL_PARAM)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, FUNCTION_DECL_PARAM);
    exit_section_(b, m, FUNCTION_DECL_PARAM_DECL, r);
    return r;
  }

  /* ********************************************************** */
  // FunctionDeclParamDecl (COMMA FunctionDeclParamDecl)*
  public static boolean FunctionDeclParamList(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionDeclParamList")) return false;
    if (!nextTokenIs(b, FUNCTION_DECL_PARAM)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = FunctionDeclParamDecl(b, l + 1);
    r = r && FunctionDeclParamList_1(b, l + 1);
    exit_section_(b, m, FUNCTION_DECL_PARAM_LIST, r);
    return r;
  }

  // (COMMA FunctionDeclParamDecl)*
  private static boolean FunctionDeclParamList_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionDeclParamList_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!FunctionDeclParamList_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "FunctionDeclParamList_1", c)) break;
    }
    return true;
  }

  // COMMA FunctionDeclParamDecl
  private static boolean FunctionDeclParamList_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionDeclParamList_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && FunctionDeclParamDecl(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
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
  // IF ParenthesisExpr
  //                     FunctionBody
  //                   ElseIfBranch*
  //                   ElseBranch?
  public static boolean IfStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IfStatement")) return false;
    if (!nextTokenIs(b, IF)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IF);
    r = r && ParenthesisExpr(b, l + 1);
    r = r && FunctionBody(b, l + 1);
    r = r && IfStatement_3(b, l + 1);
    r = r && IfStatement_4(b, l + 1);
    exit_section_(b, m, IF_STATEMENT, r);
    return r;
  }

  // ElseIfBranch*
  private static boolean IfStatement_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IfStatement_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!ElseIfBranch(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "IfStatement_3", c)) break;
    }
    return true;
  }

  // ElseBranch?
  private static boolean IfStatement_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IfStatement_4")) return false;
    ElseBranch(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // IMPORT IMPORT_FILE_STRING AS IMPORT_ALIAS
  public static boolean ImportDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ImportDeclaration")) return false;
    if (!nextTokenIs(b, IMPORT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, IMPORT, IMPORT_FILE_STRING, AS, IMPORT_ALIAS);
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
  // LET LetVariableDecl ASSIGN Expr
  public static boolean LetAssignment(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LetAssignment")) return false;
    if (!nextTokenIs(b, LET)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LET);
    r = r && LetVariableDecl(b, l + 1);
    r = r && consumeToken(b, ASSIGN);
    r = r && Expr(b, l + 1);
    exit_section_(b, m, LET_ASSIGNMENT, r);
    return r;
  }

  /* ********************************************************** */
  // VARIABLE_DECL
  public static boolean LetVariableDecl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LetVariableDecl")) return false;
    if (!nextTokenIs(b, VARIABLE_DECL)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, VARIABLE_DECL);
    exit_section_(b, m, LET_VARIABLE_DECL, r);
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
  // LCURLY ObjectBody RCURLY
  public static boolean Object(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Object")) return false;
    if (!nextTokenIs(b, LCURLY)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LCURLY);
    r = r && ObjectBody(b, l + 1);
    r = r && consumeToken(b, RCURLY);
    exit_section_(b, m, OBJECT, r);
    return r;
  }

  /* ********************************************************** */
  // LetAssignment* (Pairs | Matcher)?
  public static boolean ObjectBody(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ObjectBody")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, OBJECT_BODY, "<object body>");
    r = ObjectBody_0(b, l + 1);
    r = r && ObjectBody_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // LetAssignment*
  private static boolean ObjectBody_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ObjectBody_0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!LetAssignment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ObjectBody_0", c)) break;
    }
    return true;
  }

  // (Pairs | Matcher)?
  private static boolean ObjectBody_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ObjectBody_1")) return false;
    ObjectBody_1_0(b, l + 1);
    return true;
  }

  // Pairs | Matcher
  private static boolean ObjectBody_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ObjectBody_1_0")) return false;
    boolean r;
    r = Pairs(b, l + 1);
    if (!r) r = Matcher(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // LCURLY ObjectComprehensionBody RCURLY
  public static boolean ObjectComprehension(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ObjectComprehension")) return false;
    if (!nextTokenIs(b, LCURLY)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LCURLY);
    r = r && ObjectComprehensionBody(b, l + 1);
    r = r && consumeToken(b, RCURLY);
    exit_section_(b, m, OBJECT_COMPREHENSION, r);
    return r;
  }

  /* ********************************************************** */
  // FOR ParenthesisExpr ObjectComprehensionForBody
  public static boolean ObjectComprehensionBody(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ObjectComprehensionBody")) return false;
    if (!nextTokenIs(b, FOR)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, FOR);
    r = r && ParenthesisExpr(b, l + 1);
    r = r && ObjectComprehensionForBody(b, l + 1);
    exit_section_(b, m, OBJECT_COMPREHENSION_BODY, r);
    return r;
  }

  /* ********************************************************** */
  // LetAssignment* Pair (IF ParenthesisExpr)?
  public static boolean ObjectComprehensionForBody(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ObjectComprehensionForBody")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, OBJECT_COMPREHENSION_FOR_BODY, "<object comprehension for body>");
    r = ObjectComprehensionForBody_0(b, l + 1);
    r = r && Pair(b, l + 1);
    r = r && ObjectComprehensionForBody_2(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // LetAssignment*
  private static boolean ObjectComprehensionForBody_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ObjectComprehensionForBody_0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!LetAssignment(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ObjectComprehensionForBody_0", c)) break;
    }
    return true;
  }

  // (IF ParenthesisExpr)?
  private static boolean ObjectComprehensionForBody_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ObjectComprehensionForBody_2")) return false;
    ObjectComprehensionForBody_2_0(b, l + 1);
    return true;
  }

  // IF ParenthesisExpr
  private static boolean ObjectComprehensionForBody_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ObjectComprehensionForBody_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IF);
    r = r && ParenthesisExpr(b, l + 1);
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
  public static boolean Pair(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Pair")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PAIR, "<pair>");
    r = Expr(b, l + 1);
    r = r && consumeToken(b, COLON);
    r = r && Expr(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // Pair
  //                (COMMA Pair)*
  //                (COMMA Matcher)?
  public static boolean Pairs(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Pairs")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PAIRS, "<pairs>");
    r = Pair(b, l + 1);
    r = r && Pairs_1(b, l + 1);
    r = r && Pairs_2(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (COMMA Pair)*
  private static boolean Pairs_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Pairs_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!Pairs_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Pairs_1", c)) break;
    }
    return true;
  }

  // COMMA Pair
  private static boolean Pairs_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Pairs_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && Pair(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (COMMA Matcher)?
  private static boolean Pairs_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Pairs_2")) return false;
    Pairs_2_0(b, l + 1);
    return true;
  }

  // COMMA Matcher
  private static boolean Pairs_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Pairs_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && Matcher(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // LPAREN Expr RPAREN
  public static boolean ParenthesisExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ParenthesisExpr")) return false;
    if (!nextTokenIs(b, LPAREN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LPAREN);
    r = r && Expr(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, PARENTHESIS_EXPR, r);
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
  // VARIABLE
  public static boolean VariableUsage(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VariableUsage")) return false;
    if (!nextTokenIs(b, VARIABLE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, VARIABLE);
    exit_section_(b, m, VARIABLE_USAGE, r);
    return r;
  }

  /* ********************************************************** */
  // COMMENT*
  //     ImportDeclarations?
  //     (LetAssignment | FunctionDecl)*
  //     Expr? <<eof>>
  static boolean jsltFile(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jsltFile")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = jsltFile_0(b, l + 1);
    r = r && jsltFile_1(b, l + 1);
    r = r && jsltFile_2(b, l + 1);
    r = r && jsltFile_3(b, l + 1);
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

  // Expr?
  private static boolean jsltFile_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jsltFile_3")) return false;
    Expr(b, l + 1);
    return true;
  }

}
