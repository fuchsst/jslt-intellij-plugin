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

public class JsltObjectComprehensionBodyImpl extends ASTWrapperPsiElement implements JsltObjectComprehensionBody {

  public JsltObjectComprehensionBodyImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JsltVisitor visitor) {
    visitor.visitObjectComprehensionBody(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof JsltVisitor) accept((JsltVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public JsltObjectComprehensionForBody getObjectComprehensionForBody() {
    return findNotNullChildByClass(JsltObjectComprehensionForBody.class);
  }

  @Override
  @NotNull
  public JsltParenthesisExpr getParenthesisExpr() {
    return findNotNullChildByClass(JsltParenthesisExpr.class);
  }

}
