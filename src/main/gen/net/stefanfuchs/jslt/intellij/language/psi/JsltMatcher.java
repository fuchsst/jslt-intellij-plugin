// This is a generated file. Not intended for manual editing.
package net.stefanfuchs.jslt.intellij.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.navigation.ItemPresentation;

public interface JsltMatcher extends PsiElement {

  @NotNull
  JsltExpr getExpr();

  @Nullable
  JsltMatcherMinus getMatcherMinus();

  @NotNull
  ItemPresentation getPresentation();

}
