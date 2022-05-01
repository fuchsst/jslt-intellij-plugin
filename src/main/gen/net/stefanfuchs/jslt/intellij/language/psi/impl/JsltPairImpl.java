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
import com.intellij.navigation.ItemPresentation;

public class JsltPairImpl extends ASTWrapperPsiElement implements JsltPair {

  public JsltPairImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JsltVisitor visitor) {
    visitor.visitPair(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof JsltVisitor) accept((JsltVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<JsltExpr> getExprList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, JsltExpr.class);
  }

  @Override
  @Nullable
  public JsltMatcher getMatcher() {
    return findChildByClass(JsltMatcher.class);
  }

  @Override
  @Nullable
  public JsltPair getPair() {
    return findChildByClass(JsltPair.class);
  }

  @Override
  @Nullable
  public String getName() {
    return JsltPsiImplUtil.getName(this);
  }

  @Override
  @NotNull
  public ItemPresentation getPresentation() {
    return JsltPsiImplUtil.getPresentation(this);
  }

}