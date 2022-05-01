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

public class JsltChainLinkImpl extends ASTWrapperPsiElement implements JsltChainLink {

  public JsltChainLinkImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JsltVisitor visitor) {
    visitor.visitChainLink(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof JsltVisitor) accept((JsltVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public JsltArraySlicing getArraySlicing() {
    return findChildByClass(JsltArraySlicing.class);
  }

  @Override
  @Nullable
  public JsltChainLink getChainLink() {
    return findChildByClass(JsltChainLink.class);
  }

  @Override
  @Nullable
  public JsltDotKey getDotKey() {
    return findChildByClass(JsltDotKey.class);
  }

}
