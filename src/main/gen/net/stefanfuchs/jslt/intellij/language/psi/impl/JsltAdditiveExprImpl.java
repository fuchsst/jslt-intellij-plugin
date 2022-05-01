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

public class JsltAdditiveExprImpl extends ASTWrapperPsiElement implements JsltAdditiveExpr {

  public JsltAdditiveExprImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JsltVisitor visitor) {
    visitor.visitAdditiveExpr(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof JsltVisitor) accept((JsltVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<JsltAdditiveOperator> getAdditiveOperatorList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, JsltAdditiveOperator.class);
  }

  @Override
  @NotNull
  public List<JsltMultiplicativeExpr> getMultiplicativeExprList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, JsltMultiplicativeExpr.class);
  }

}