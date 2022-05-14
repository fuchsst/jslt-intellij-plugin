// This is a generated file. Not intended for manual editing.
package net.stefanfuchs.jslt.intellij.language.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static net.stefanfuchs.jslt.intellij.language.psi.JsltTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import net.stefanfuchs.jslt.intellij.language.psi.*;
import net.stefanfuchs.jslt.intellij.language.psi.util.JsltPsiImplUtil;

public class JsltBaseExprImpl extends ASTWrapperPsiElement implements JsltBaseExpr {

  public JsltBaseExprImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JsltVisitor visitor) {
    visitor.visitBaseExpr(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof JsltVisitor) accept((JsltVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public JsltArray getArray() {
    return findChildByClass(JsltArray.class);
  }

  @Override
  @Nullable
  public JsltChainable getChainable() {
    return findChildByClass(JsltChainable.class);
  }

  @Override
  @Nullable
  public JsltIfStatement getIfStatement() {
    return findChildByClass(JsltIfStatement.class);
  }

  @Override
  @Nullable
  public JsltObject getObject() {
    return findChildByClass(JsltObject.class);
  }

  @Override
  @Nullable
  public JsltObjectComprehension getObjectComprehension() {
    return findChildByClass(JsltObjectComprehension.class);
  }

  @Override
  @Nullable
  public JsltParenthesisExpr getParenthesisExpr() {
    return findChildByClass(JsltParenthesisExpr.class);
  }

}
