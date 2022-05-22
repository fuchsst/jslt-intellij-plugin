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
import com.intellij.navigation.ItemPresentation;

public class JsltFunctionDeclImpl extends ASTWrapperPsiElement implements JsltFunctionDecl {

  public JsltFunctionDeclImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JsltVisitor visitor) {
    visitor.visitFunctionDecl(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof JsltVisitor) accept((JsltVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public JsltFunctionBody getFunctionBody() {
    return findNotNullChildByClass(JsltFunctionBody.class);
  }

  @Override
  @NotNull
  public JsltFunctionDeclNameDecl getFunctionDeclNameDecl() {
    return findNotNullChildByClass(JsltFunctionDeclNameDecl.class);
  }

  @Override
  @Nullable
  public JsltFunctionDeclParamList getFunctionDeclParamList() {
    return findChildByClass(JsltFunctionDeclParamList.class);
  }

  @Override
  @Nullable
  public String getName() {
    return JsltPsiImplUtil.getName(this);
  }

  @Override
  @Nullable
  public PsiElement getNameIdentifier() {
    return JsltPsiImplUtil.getNameIdentifier(this);
  }

  @Override
  @NotNull
  public ItemPresentation getPresentation() {
    return JsltPsiImplUtil.getPresentation(this);
  }

  @Override
  @NotNull
  public JsltExpr getExpr() {
    return JsltPsiImplUtil.getExpr(this);
  }

}
