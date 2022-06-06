// This is a generated file. Not intended for manual editing.
package net.stefanfuchs.jslt.intellij.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.navigation.ItemPresentation;

public interface JsltLetAssignment extends PsiNamedElement {

  @NotNull
  JsltExpr getExpr();

  @NotNull
  JsltLetVariableDecl getLetVariableDecl();

  @Nullable
  String getName();

  @NotNull
  PsiElement setName(@NotNull String newName);

  @NotNull
  ItemPresentation getPresentation();

}
