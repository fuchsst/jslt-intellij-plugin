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

public class JsltObjectComprehensionForBodyImpl extends ASTWrapperPsiElement implements JsltObjectComprehensionForBody {

  public JsltObjectComprehensionForBodyImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JsltVisitor visitor) {
    visitor.visitObjectComprehensionForBody(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof JsltVisitor) accept((JsltVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<JsltLetAssignment> getLetAssignmentList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, JsltLetAssignment.class);
  }

  @Override
  @NotNull
  public JsltPair getPair() {
    return findNotNullChildByClass(JsltPair.class);
  }

  @Override
  @Nullable
  public JsltParenthesisExpr getParenthesisExpr() {
    return findChildByClass(JsltParenthesisExpr.class);
  }

}
