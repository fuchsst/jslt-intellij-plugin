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

public class JsltArrayBodyImpl extends ASTWrapperPsiElement implements JsltArrayBody {

  public JsltArrayBodyImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JsltVisitor visitor) {
    visitor.visitArrayBody(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof JsltVisitor) accept((JsltVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public JsltArrayElements getArrayElements() {
    return findChildByClass(JsltArrayElements.class);
  }

  @Override
  @Nullable
  public JsltArrayFor getArrayFor() {
    return findChildByClass(JsltArrayFor.class);
  }

  @Override
  @NotNull
  public List<JsltExpr> getExpressions() {
    return JsltPsiImplUtil.getExpressions(this);
  }

}
