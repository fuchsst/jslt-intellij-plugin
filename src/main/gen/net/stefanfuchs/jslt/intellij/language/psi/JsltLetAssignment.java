// This is a generated file. Not intended for manual editing.
package net.stefanfuchs.jslt.intellij.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.navigation.ItemPresentation;

public interface JsltLetAssignment extends JsltLetAssignmentElement {

  @NotNull
  JsltExpr getExpr();

  @Nullable
  String getName();

  @NotNull
  PsiElement setName(@NotNull String newAlias);

  @Nullable
  PsiElement getNameIdentifier();

  @NotNull
  ItemPresentation getPresentation();

}
